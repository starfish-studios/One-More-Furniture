package com.crispytwig.nookcranny.blocks;

import com.crispytwig.nookcranny.blocks.properties.ColorList;
import com.crispytwig.nookcranny.registry.NCTags;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.level.pathfinder.PathComputationType;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.Nullable;

public class TableBlock extends HalfTransparentBlock implements SimpleWaterloggedBlock {
    public static final DirectionProperty FACING = BlockStateProperties.HORIZONTAL_FACING;
    public static final BooleanProperty LEG1 = BooleanProperty.create("leg_1");
    public static final BooleanProperty LEG2 = BooleanProperty.create("leg_2");
    public static final BooleanProperty LEG3 = BooleanProperty.create("leg_3");
    public static final BooleanProperty LEG4 = BooleanProperty.create("leg_4");
    public static final BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;
    public static final BooleanProperty UPDATE = BooleanProperty.create("update");
    public static final EnumProperty<ColorList> TABLECLOTH = EnumProperty.create("tablecloth", ColorList.class);

    protected static final VoxelShape TOP = Block.box(0.0D, 13.0D, 0.0D, 16.0D, 16.0D, 16.0D);
    protected static final VoxelShape TOP_CLOTHED = Block.box(0.0D, 7.0D, 0.0D, 16.0D, 16.0D, 16.0D);
    protected static final VoxelShape LEG_1 = Block.box(12.0D, 0.0D, 1.0D, 15.0D, 13.0D, 4.0D);
    protected static final VoxelShape LEG_2 = Block.box(12.0D, 0.0D, 12.0D, 15.0D, 13.0D, 15.0D);
    protected static final VoxelShape LEG_3 = Block.box(1.0D, 0.0D, 12.0D, 4.0D, 13.0D, 15.0D);
    protected static final VoxelShape LEG_4 = Block.box(1.0D, 0.0D, 1.0D, 4.0D, 13.0D, 4.0D);

    public TableBlock(Properties properties) {
        super(properties);
        registerDefaultState(this.stateDefinition.any()
                .setValue(FACING, Direction.NORTH)
                .setValue(LEG1, true)
                .setValue(LEG2, true)
                .setValue(LEG3, true)
                .setValue(LEG4, true)
                .setValue(WATERLOGGED, false)
                .setValue(TABLECLOTH, ColorList.EMPTY));
    }

    @Override
    public VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        VoxelShape shape = Shapes.empty();
        if (state.getValue(TABLECLOTH ) != ColorList.EMPTY) shape = Shapes.or(shape, TOP_CLOTHED);
        if (state.getValue(LEG1)) shape = Shapes.or(shape, LEG_1);
        if (state.getValue(LEG2)) shape = Shapes.or(shape, LEG_2);
        if (state.getValue(LEG3)) shape = Shapes.or(shape, LEG_3);
        if (state.getValue(LEG4)) shape = Shapes.or(shape, LEG_4);
        shape = Shapes.or(shape, TOP);
        return shape;
    }

    @Nullable
    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        boolean waterlogged = context.getLevel().getFluidState(context.getClickedPos()).getType() == Fluids.WATER;
        BlockState state = this.defaultBlockState()
                .setValue(FACING, context.getHorizontalDirection().getOpposite())
                .setValue(WATERLOGGED, waterlogged);
        return getConnections(state, context.getLevel(), context.getClickedPos());
    }

    @Override
    public BlockState updateShape(BlockState state, Direction direction, BlockState neighborState, LevelAccessor level, BlockPos currentPos, BlockPos neighborPos) {
        if (state.getValue(WATERLOGGED)) level.scheduleTick(currentPos, Fluids.WATER, Fluids.WATER.getTickDelay(level));
        return getConnections(state, level, currentPos);
    }

    @Override
    public FluidState getFluidState(BlockState state) {
        return state.getValue(WATERLOGGED) ? Fluids.WATER.getSource(false) : super.getFluidState(state);
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(FACING, LEG1, LEG2, LEG3, LEG4, UPDATE, WATERLOGGED, TABLECLOTH);
    }

    @Override
    public boolean isPathfindable(BlockState state, BlockGetter level, BlockPos pos, PathComputationType type) {
        return false;
    }

    @Override
    public BlockState rotate(BlockState state, Rotation rotation) {
        boolean leg1 = state.getValue(LEG1);
        boolean leg2 = state.getValue(LEG2);
        boolean leg3 = state.getValue(LEG3);
        boolean leg4 = state.getValue(LEG4);
        return switch(rotation) {
            case NONE -> state.setValue(FACING, rotation.rotate(state.getValue(FACING)));
            case CLOCKWISE_90 -> state
                    .setValue(FACING, rotation.rotate(state.getValue(FACING)))
                    .setValue(LEG1, leg4)
                    .setValue(LEG2, leg1)
                    .setValue(LEG3, leg2)
                    .setValue(LEG4, leg3);
            case CLOCKWISE_180 -> state
                    .setValue(FACING, rotation.rotate(state.getValue(FACING)))
                    .setValue(LEG1, leg3)
                    .setValue(LEG2, leg4)
                    .setValue(LEG3, leg1).setValue(LEG4, leg2);
            case COUNTERCLOCKWISE_90 -> state
                    .setValue(FACING, rotation.rotate(state.getValue(FACING)))
                    .setValue(LEG1, leg2)
                    .setValue(LEG2, leg3)
                    .setValue(LEG3, leg4)
                    .setValue(LEG4, leg1);
        };
    }

    @Override
    public BlockState mirror(BlockState state, Mirror mirror) {
        return state.rotate(mirror.getRotation(state.getValue(FACING)));
    }

    public BlockState getConnections(BlockState state, LevelAccessor level, BlockPos pos) {
        boolean n = validConnection(level.getBlockState(pos.north()));
        boolean e = validConnection(level.getBlockState(pos.east()));
        boolean s = validConnection(level.getBlockState(pos.south()));
        boolean w = validConnection(level.getBlockState(pos.west()));
        boolean leg1 = (!n && !e) || (n && e && !(validConnection(level.getBlockState(pos.north().east()))));
        boolean leg2 = (!e && !s) || (e && s && !(validConnection(level.getBlockState(pos.south().east()))));
        boolean leg3 = (!s && !w) || (s && w && !(validConnection(level.getBlockState(pos.south().west()))));
        boolean leg4 = (!n && !w) || (n && w && !(validConnection(level.getBlockState(pos.north().west()))));
        boolean update = ((n ? 1 : 0) + (e ? 1 : 0) + (s ? 1 : 0) + (w ? 1 : 0)) % 2 == 0;
        return state.setValue(LEG1, leg1).setValue(LEG2, leg2).setValue(LEG3, leg3).setValue(LEG4, leg4).setValue(UPDATE, update);
    }

    public boolean validConnection(BlockState state) {
        return state.is(NCTags.BlockTags.TABLES_CONNECTABLE);
    }
}
