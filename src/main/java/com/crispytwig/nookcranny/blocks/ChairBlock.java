package com.crispytwig.nookcranny.blocks;

import com.crispytwig.nookcranny.blocks.properties.*;
import com.crispytwig.nookcranny.registry.NCItems;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.Containers;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.DyeItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Mirror;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.level.block.SimpleWaterloggedBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.Nullable;

public class ChairBlock extends SeatBlock implements SimpleWaterloggedBlock, Cushionable, ChangeableBlock {
    public static final DirectionProperty FACING = BlockStateProperties.HORIZONTAL_FACING;
    public static final BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;
    public static final EnumProperty<ColorList> CUSHION = EnumProperty.create("cushion", ColorList.class);
    public static final BooleanProperty BACK = BooleanProperty.create("back");
    public static final EnumProperty<ChairType> BACK_TYPE = EnumProperty.create("type", ChairType.class);

    public static final VoxelShape BOTTOM_AABB = Shapes.or(
            Block.box(1.0D, 8.0D, 1.0D, 15.0D, 10.0D, 15.0D),
            Block.box(3.0D, 6.0D, 3.0D, 13.0D, 8.0D, 13.0D),
            Block.box(2.0D, 0.0D, 2.0D, 4.0D, 8.0D, 4.0D),
            Block.box(2.0D, 0.0D, 12.0D, 4.0D, 8.0D, 14.0D),
            Block.box(12.0D, 0.0D, 12.0D, 14.0D, 8.0D, 14.0D),
            Block.box(12.0D, 0.0D, 2.0D, 14.0D, 8.0D, 4.0D));
    public static final VoxelShape CUSHION_AABB = Block.box(1.0D, 10.0D, 1.0D, 15.0D, 11.0D, 15.0D);
    public static final VoxelShape BOTTOM_WITHOUT_CUSHION = BOTTOM_AABB;
    public static final VoxelShape BOTTOM_WITH_CUSHION = Shapes.or(BOTTOM_AABB, CUSHION_AABB);

    public static final VoxelShape NORTH_AABB = Block.box(2.0D, 10.0D, 13.0D, 14.0D, 22.0D, 15.0D);
    public static final VoxelShape NORTH_WITHOUT_CUSHION = Shapes.or(BOTTOM_WITHOUT_CUSHION, NORTH_AABB);
    public static final VoxelShape NORTH_WITH_CUSHION = Shapes.or(BOTTOM_WITH_CUSHION, NORTH_AABB);

    public static final VoxelShape EAST_AABB = Block.box(1.0D, 10.0D, 2.0D, 3.0D, 22.0D, 14.0D);
    public static final VoxelShape EAST_WITHOUT_CUSHION = Shapes.or(BOTTOM_WITHOUT_CUSHION, EAST_AABB);
    public static final VoxelShape EAST_WITH_CUSHION = Shapes.or(BOTTOM_WITH_CUSHION, EAST_AABB);

    public static final VoxelShape SOUTH_AABB = Block.box(2.0D, 10.0D, 1.0D, 14.0D, 22.0D, 3.0D);
    public static final VoxelShape SOUTH_WITHOUT_CUSHION = Shapes.or(BOTTOM_WITHOUT_CUSHION, SOUTH_AABB);
    public static final VoxelShape SOUTH_WITH_CUSHION = Shapes.or(BOTTOM_WITH_CUSHION, SOUTH_AABB);

    public static final VoxelShape WEST_AABB = Block.box(13.0D, 10.0D, 2.0D, 15.0D, 22.0D, 14.0D);
    public static final VoxelShape WEST_WITHOUT_CUSHION = Shapes.or(BOTTOM_WITHOUT_CUSHION, WEST_AABB);
    public static final VoxelShape WEST_WITH_CUSHION = Shapes.or(BOTTOM_WITH_CUSHION, WEST_AABB);

    public ChairBlock(Properties properties) {
        super(properties);
        this.registerDefaultState(this.getStateDefinition().any()
                .setValue(FACING, Direction.NORTH)
                .setValue(WATERLOGGED, false)
                .setValue(BACK, true)
                .setValue(BACK_TYPE, ChairType.TYPE_1)
                .setValue(CUSHION, ColorList.EMPTY));
    }

    @Override
    public VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, net.minecraft.world.phys.shapes.CollisionContext context) {
        boolean hasCushion = state.getValue(CUSHION) != ColorList.EMPTY;
        Direction facing = state.getValue(FACING);

        if (!state.getValue(BACK)) {
            return hasCushion ? BOTTOM_WITH_CUSHION : BOTTOM_WITHOUT_CUSHION;
        }
        // Select the precomputed shape based on the state values
        return switch (facing) {
            case NORTH -> hasCushion ? NORTH_WITH_CUSHION : NORTH_WITHOUT_CUSHION;
            case EAST -> hasCushion ? EAST_WITH_CUSHION : EAST_WITHOUT_CUSHION;
            case SOUTH -> hasCushion ? SOUTH_WITH_CUSHION : SOUTH_WITHOUT_CUSHION;
            case WEST -> hasCushion ? WEST_WITH_CUSHION : WEST_WITHOUT_CUSHION;
            default -> BOTTOM_AABB; // Default in case of unexpected direction
        };
    }

    @Override
    public InteractionResult use(BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hit) {
        if (tryChangeBlock(BACK, state, level, pos, player, hand)) return InteractionResult.SUCCESS;

        if (hand == InteractionHand.MAIN_HAND) return InteractionResult.FAIL;
        return super.use(state, level, pos, player, hand, hit);
    }


    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> stateDefinition) {
        stateDefinition.add(FACING, WATERLOGGED, CUSHION, BACK, BACK_TYPE);
    }

    @Override
    public FluidState getFluidState(BlockState state) {
        return state.getValue(WATERLOGGED) ? Fluids.WATER.getSource(false) : super.getFluidState(state);
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
    public float seatHeight(BlockState state) {
        return 0.4F;
    }

    @Override
    public BlockPos primaryDismountLocation(Level level, BlockState state, BlockPos pos) {
        return pos.relative(state.getValue(FACING));
    }

    @Override
    public float setRiderRotation(BlockState state, Entity entity) {
        return state.getValue(FACING).toYRot();
    }

    @Nullable
    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        boolean waterlogged = context.getLevel().getFluidState(context.getClickedPos()).getType() == Fluids.WATER;

        return this.getStateDefinition().any()
                .setValue(FACING, context.getHorizontalDirection().getOpposite())
                .setValue(BACK_TYPE, ChairType.TYPE_1)
                .setValue(WATERLOGGED, waterlogged);
    }

    @Override
    public boolean propagatesSkylightDown(BlockState blockState, BlockGetter blockGetter, BlockPos blockPos) {
        return true;
    }

    @Override
    public BlockState updateShape(BlockState state, Direction direction, BlockState neighborState, LevelAccessor level, BlockPos currentPos, BlockPos neighborPos) {
        if (state.getValue(WATERLOGGED)) level.scheduleTick(currentPos, Fluids.WATER, Fluids.WATER.getTickDelay(level));
        return super.updateShape(state, direction, neighborState, level, currentPos, neighborPos);
    }
}
