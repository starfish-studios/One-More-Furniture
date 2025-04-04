package com.crispytwig.omf.block;

import com.crispytwig.omf.block.properties.ColorList;
import com.crispytwig.omf.block.properties.Cushionable;
import com.crispytwig.omf.registry.OMFBlockProperties;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Mirror;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.level.block.SimpleWaterloggedBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.Nullable;

public class TallStoolBlock extends SeatBlock implements SimpleWaterloggedBlock, Cushionable {
    public static final DirectionProperty FACING = BlockStateProperties.HORIZONTAL_FACING;
    public static final BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;
    public static final EnumProperty<ColorList> CUSHION = OMFBlockProperties.CUSHION;
    protected static final VoxelShape SHAPE = Shapes.or(
            Block.box(2.0D, 14.0D, 2.0D, 14.0D, 16.0D, 14.0D),
            Block.box(4.0D, 0.0D, 4.0D, 6.0D, 14.0D, 6.0D),
            Block.box(10.0D, 0.0D, 4.0D, 12.0D, 14.0D, 6.0D),
            Block.box(4.0D, 0.0D, 10.0D, 6.0D, 14.0D, 12.0D),
            Block.box(10.0D, 0.0D, 10.0D, 12.0D, 14.0D, 12.0D),
            Block.box(5.0D, 6.0D, 5.0D, 11.0D, 8.0D, 11.0D)
    );
    protected static final VoxelShape CUSHION_AABB = Block.box(2.0D, 13.0D, 2.0D, 14.0D, 16.0D, 14.0D);

    @Override
    public double dyeHeight() {
        return 0.6;
    }

    public TallStoolBlock(Properties properties) {
        super(properties);
        this.registerDefaultState(this.getStateDefinition().any()
                .setValue(FACING, Direction.NORTH)
                .setValue(WATERLOGGED, false)
                .setValue(CUSHION, ColorList.EMPTY));
    }

    @Override
    public VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        VoxelShape shape = SHAPE;
        if (state.getValue(CUSHION) != ColorList.EMPTY) {
            shape = CUSHION_AABB;
        }
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
        return 0.8F;
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
    public void playerDestroy(Level level, Player player, BlockPos pos, BlockState state, @Nullable BlockEntity blockEntity, ItemStack tool) {
        super.playerDestroy(level, player, pos, state, blockEntity, tool);
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
