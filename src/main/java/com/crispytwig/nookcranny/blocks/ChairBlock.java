package com.crispytwig.nookcranny.blocks;

import com.crispytwig.nookcranny.blocks.properties.ColorList;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.entity.Entity;
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
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.Nullable;

public class ChairBlock extends SeatBlock implements SimpleWaterloggedBlock {
    public static final DirectionProperty FACING = BlockStateProperties.HORIZONTAL_FACING;
    public static final BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;
    public static final EnumProperty<ColorList> CUSHION = EnumProperty.create("cushion", ColorList.class);
    public static final VoxelShape BOTTOM_AABB = Shapes.or(
            Block.box(1.0D, 8.0D, 1.0D, 15.0D, 10.0D, 15.0D),
            Block.box(3.0D, 6.0D, 3.0D, 13.0D, 8.0D, 13.0D),
            Block.box(2.0D, 0.0D, 2.0D, 4.0D, 8.0D, 4.0D),
            Block.box(2.0D, 0.0D, 12.0D, 4.0D, 8.0D, 14.0D),
            Block.box(12.0D, 0.0D, 12.0D, 14.0D, 8.0D, 14.0D),
            Block.box(12.0D, 0.0D, 2.0D, 14.0D, 8.0D, 4.0D));
    public static final VoxelShape CUSHION_AABB = Block.box(1.0D, 10.0D, 1.0D, 15.0D, 11.0D, 15.0D);
    public static final VoxelShape NORTH_AABB = Block.box(2.0D, 10.0D, 13.0D, 14.0D, 22.0D, 15.0D);
    public static final VoxelShape EAST_AABB = Block.box(1.0D, 10.0D, 2.0D, 3.0D, 22.0D, 14.0D);
    public static final VoxelShape SOUTH_AABB = Block.box(2.0D, 10.0D, 1.0D, 14.0D, 22.0D, 3.0D);
    public static final VoxelShape WEST_AABB = Block.box(13.0D, 10.0D, 2.0D, 15.0D, 22.0D, 14.0D);


    public ChairBlock(Properties properties) {
        super(properties);
        this.registerDefaultState(this.getStateDefinition().any()
                .setValue(FACING, Direction.NORTH)
                .setValue(WATERLOGGED, false)
                .setValue(CUSHION, ColorList.EMPTY));
    }

    @Override
    public VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, net.minecraft.world.phys.shapes.CollisionContext context) {
        VoxelShape shape = Shapes.empty();
        if (state.getValue(CUSHION) != ColorList.EMPTY) shape = Shapes.or(shape, CUSHION_AABB);
        if (state.getValue(FACING) == Direction.NORTH) shape = Shapes.or(shape, NORTH_AABB);
        if (state.getValue(FACING) == Direction.EAST) shape = Shapes.or(shape, EAST_AABB);
        if (state.getValue(FACING) == Direction.SOUTH) shape = Shapes.or(shape, SOUTH_AABB);
        if (state.getValue(FACING) == Direction.WEST) shape = Shapes.or(shape, WEST_AABB);
        shape = Shapes.or(shape, BOTTOM_AABB);
        return shape;
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> stateDefinition) {
        stateDefinition.add(FACING, WATERLOGGED, CUSHION);
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
}
