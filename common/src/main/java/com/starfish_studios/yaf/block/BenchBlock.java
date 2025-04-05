package com.starfish_studios.yaf.block;

import com.starfish_studios.yaf.block.properties.ChangeableBlock;
import com.starfish_studios.yaf.block.properties.HorizontalConnectionType;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.*;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.level.pathfinder.PathComputationType;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.Nullable;

public class BenchBlock extends SeatBlock implements SimpleWaterloggedBlock, ChangeableBlock {
    public static final DirectionProperty FACING = BlockStateProperties.HORIZONTAL_FACING;
    public static final EnumProperty<HorizontalConnectionType> CONNECTION_TYPE = EnumProperty.create("main_type", HorizontalConnectionType.class);
    public static final EnumProperty<HorizontalConnectionType> BACK_TYPE = EnumProperty.create("back_type", HorizontalConnectionType.class);
    public static final BooleanProperty BACK = BooleanProperty.create("back");
    public static final BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;

    protected static final VoxelShape AABB_Z = Block.box(0, 0, 2, 16, 7, 14);
    protected static final VoxelShape AABB_X = Block.box(2, 0, 0, 14, 7, 16);

    protected static final VoxelShape AABB_EAST = Shapes.or(AABB_X, Block.box(2.0D, 7.0D, 0.0D, 4.0D, 16.0D, 16.0D));
    protected static final VoxelShape AABB_WEST = Shapes.or(AABB_X, Block.box(12.0D, 7.0D, 0.0D, 14.0D, 16.0D, 16.0D));
    protected static final VoxelShape AABB_SOUTH = Shapes.or(AABB_Z, Block.box(0.0D, 7.0D, 2.0D, 16.0D, 16.0D, 4.0D));
    protected static final VoxelShape AABB_NORTH = Shapes.or(AABB_Z, Block.box(0.0D, 7.0D, 12.0D, 16.0D, 16.0D, 14.0D));

    public BenchBlock(Properties properties) {
        super(properties);
        registerDefaultState(this.stateDefinition.any()
                .setValue(FACING, Direction.NORTH)
                .setValue(CONNECTION_TYPE, HorizontalConnectionType.SINGLE)
                .setValue(BACK_TYPE, HorizontalConnectionType.SINGLE)
                .setValue(BACK, true)
                .setValue(WATERLOGGED, false));
    }

    @Override
    public float seatHeight(BlockState state) {
        return 0.25F;
    }

    @Override
    public BlockPos primaryDismountLocation(Level level, BlockState state, BlockPos pos) {
        return pos.relative(state.getValue(FACING));
    }

    @Override
    public float setRiderRotation(BlockState state, Entity entity) {
        return state.getValue(FACING).toYRot();
    }

    @Override
    public VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        Direction facing = state.getValue(FACING);

        boolean back = state.getValue(BACK);

        if (!back) {
            return switch (facing) {
                case EAST, WEST -> AABB_X;
                default -> AABB_Z;
            };
        }

        return switch (facing) {
            case EAST -> AABB_EAST;
            case SOUTH -> AABB_SOUTH;
            case WEST -> AABB_WEST;
            default -> AABB_NORTH;
        };
    }

    @Nullable
    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        boolean waterlogged = context.getLevel().getFluidState(context.getClickedPos()).getType() == Fluids.WATER;
        return this.defaultBlockState()
                .setValue(FACING, context.getHorizontalDirection().getOpposite())
                .setValue(WATERLOGGED, waterlogged);
    }

    @Override
    public BlockState updateShape(BlockState state, Direction direction, BlockState neighborState, LevelAccessor level, BlockPos currentPos, BlockPos neighborPos) {
        if (state.getValue(WATERLOGGED)) {
            level.scheduleTick(currentPos, Fluids.WATER, Fluids.WATER.getTickDelay(level));
        }
        Direction facing = state.getValue(FACING);
        if (direction != facing.getClockWise() && direction != facing.getCounterClockWise()) return state;

        BlockState l_state = level.getBlockState(currentPos.relative(facing.getClockWise()));
        BlockState r_state = level.getBlockState(currentPos.relative(facing.getCounterClockWise()));
        boolean l_side = (l_state.getBlock() instanceof BenchBlock && (l_state.getValue(FACING) == facing || l_state.getValue(FACING) == facing.getOpposite()));
        boolean r_side = (r_state.getBlock() instanceof BenchBlock && (r_state.getValue(FACING) == facing || r_state.getValue(FACING) == facing.getOpposite()));
        boolean back_l_side = (l_state.getBlock() instanceof BenchBlock && l_state.getValue(FACING) == facing && l_state.getValue(BACK));
        boolean back_r_side = (r_state.getBlock() instanceof BenchBlock && r_state.getValue(FACING) == facing && r_state.getValue(BACK));

        HorizontalConnectionType connection_type = l_side && r_side ? HorizontalConnectionType.MIDDLE : (r_side ? HorizontalConnectionType.LEFT : (l_side ? HorizontalConnectionType.RIGHT : HorizontalConnectionType.SINGLE));
        HorizontalConnectionType back_type = back_l_side && back_r_side ? HorizontalConnectionType.MIDDLE : (back_r_side ? HorizontalConnectionType.LEFT : (back_l_side ? HorizontalConnectionType.RIGHT : HorizontalConnectionType.SINGLE));
        return state.setValue(CONNECTION_TYPE, connection_type).setValue(BACK_TYPE, back_type);
    }

    @Override
    public InteractionResult use(BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hit) {
        if (tryChangeBlock(BACK, state, level, pos, player, hand)) return InteractionResult.SUCCESS;

        if (hand == InteractionHand.MAIN_HAND) return InteractionResult.FAIL;
        return super.use(state, level, pos, player, hand, hit);
    }

    public FluidState getFluidState(BlockState state) {
        return state.getValue(WATERLOGGED) ? Fluids.WATER.getSource(false) : super.getFluidState(state);
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(FACING, CONNECTION_TYPE, BACK_TYPE, BACK, WATERLOGGED);
    }

    @Override
    public BlockState rotate(BlockState state, Rotation rotation) {
        return state.setValue(FACING, rotation.rotate(state.getValue(FACING)));
    }

    @Override
    public BlockState mirror(BlockState state, Mirror mirror) {
        return state.rotate(mirror.getRotation(state.getValue(FACING)));
    }

    @Override
    public boolean isPathfindable(BlockState state, BlockGetter level, BlockPos pos, PathComputationType type) {
        return false;
    }
}

