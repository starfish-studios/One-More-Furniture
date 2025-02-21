package com.crispytwig.nookcranny.blocks;

import com.crispytwig.nookcranny.registry.NCBlocks;
import net.minecraft.Util;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.StringRepresentable;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.*;
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
import net.minecraft.world.phys.BlockHitResult;
import org.jetbrains.annotations.Nullable;

import java.util.HashMap;
import java.util.Map;

public class SofaBlock extends SeatBlock implements SimpleWaterloggedBlock {
    public static final DirectionProperty FACING = BlockStateProperties.HORIZONTAL_FACING;
    public static final BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;
    public static final EnumProperty<SofaShape> SHAPE = EnumProperty.create("shape", SofaShape.class);

    public SofaBlock(@Nullable DyeColor dyeColor, Properties properties) {
        super(properties);

        this.registerDefaultState(this.getStateDefinition().any()
                .setValue(FACING, Direction.NORTH)
                .setValue(WATERLOGGED, false)
                .setValue(SHAPE, SofaShape.SINGLE)
        );
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> stateDefinition) {
        stateDefinition.add(FACING, WATERLOGGED, SHAPE);
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
        if (state.getValue(WATERLOGGED)) {
            level.scheduleTick(currentPos, Fluids.WATER, Fluids.WATER.getTickDelay(level));
        }
        return state.setValue(SHAPE, determineSofaShape(state, level, currentPos));
    }

    private SofaShape determineSofaShape(BlockState state, LevelAccessor level, BlockPos pos) {
        Direction facing = state.getValue(FACING);
        BlockState frontState = level.getBlockState(pos.relative(facing));
        BlockState backState = level.getBlockState(pos.relative(facing.getOpposite()));
        BlockState rightState = level.getBlockState(pos.relative(facing.getClockWise()));
        BlockState leftState = level.getBlockState(pos.relative(facing.getCounterClockWise()));

        if (isSofa(rightState) && isSofa(leftState)) {
            return SofaShape.MIDDLE;
        }

        //TODO rotate one of the corner pieces when we know which the default rotation the corner model will have
        if (isSofa(frontState) && isSofa(rightState)) {
            return SofaShape.CORNER;
        }

        if (isSofa(frontState) && isSofa(leftState)) {
            return SofaShape.CORNER;
        }

        if (isSofa(backState) && isSofa(rightState)) {
            return SofaShape.CORNER_OUTER;
        }

        if (isSofa(backState) && isSofa(leftState)) {
            return SofaShape.CORNER_OUTER;
        }

        if (isSofa(rightState)) {
            return SofaShape.LEFT;
        }

        if (isSofa(leftState)) {
            return SofaShape.RIGHT;
        }

        return SofaShape.SINGLE;
    }

    public boolean isSofa(BlockState state){
        return state.getBlock() instanceof SofaBlock;
    }

    public enum SofaShape implements StringRepresentable {
        SINGLE("single"),
        LEFT("left"),
        RIGHT("right"),
        MIDDLE("middle"),
        CORNER("corner"),
        CORNER_OUTER("corner_outer");

        private final String name;

        private SofaShape(String name) {
            this.name = name;
        }

        public String toString() {
            return this.name;
        }

        @Override
        public String getSerializedName() {
            return this.name;
        }
    }

}
