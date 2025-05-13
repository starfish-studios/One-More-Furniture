package com.starfish_studios.yaf.block;

import com.starfish_studios.yaf.block.entity.ChairBlockEntity;
import com.starfish_studios.yaf.block.entity.TableBlockEntity;
import com.starfish_studios.yaf.block.properties.ChairType;
import com.starfish_studios.yaf.block.properties.ChangeableBlock;
import com.starfish_studios.yaf.block.properties.ColorList;
import com.starfish_studios.yaf.block.properties.Cushionable;
import com.starfish_studios.yaf.events.CushionableEvents;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.Containers;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.*;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.Nullable;

public class ChairBlock extends SeatBlock implements SimpleWaterloggedBlock, ChangeableBlock, EntityBlock {
    public static final DirectionProperty FACING = BlockStateProperties.HORIZONTAL_FACING;
    public static final BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;
    public static final BooleanProperty BACK = BooleanProperty.create("back");

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
                .setValue(BACK, true));
    }

    @Override
    public VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, net.minecraft.world.phys.shapes.CollisionContext context) {
        Direction facing = state.getValue(FACING);

        if (!state.getValue(BACK)) {
            return BOTTOM_WITHOUT_CUSHION;
        }

        return switch (facing) {
            case NORTH -> NORTH_WITHOUT_CUSHION;
            case EAST -> EAST_WITHOUT_CUSHION;
            case SOUTH -> SOUTH_WITHOUT_CUSHION;
            case WEST -> WEST_WITHOUT_CUSHION;
            default -> BOTTOM_AABB;
        };
    }

    @Override
    protected InteractionResult useWithoutItem(BlockState state, Level level, BlockPos pos, Player player, BlockHitResult hitResult) {
        if (tryChangeBlock(BACK, state, level, pos, player)) return InteractionResult.SUCCESS;

        return super.useWithoutItem(state, level, pos, player, hitResult);
    }

    @Override
    public boolean tryChangeBlock(Property<?> property, BlockState state, LevelAccessor level, BlockPos pos, Player player) {
        var success = ChangeableBlock.super.tryChangeBlock(property, state, level, pos, player);
        if (success) {
            if (level.getBlockEntity(pos) instanceof ChairBlockEntity entity) {
                entity.setHasBack(!state.getValue(BACK));
            }
        }
        return success;
    }

    @Override
    public RenderShape getRenderShape(BlockState state) {
        return RenderShape.INVISIBLE;
    }

    @Override
    public void onRemove(BlockState state, Level level, BlockPos pos, BlockState newState, boolean movedByPiston) {
        if (level.getBlockEntity(pos) instanceof Cushionable cushionable) {
            cushionable.dropCarpet(level, pos);
        }
        super.onRemove(state, level, pos, newState, movedByPiston);
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> stateDefinition) {
        stateDefinition.add(FACING, WATERLOGGED, BACK);
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

    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return new ChairBlockEntity(pos, state);
    }
}
