package com.starfish_studios.yaf.block;

import com.starfish_studios.yaf.block.entity.ChairBlockEntity;
import com.starfish_studios.yaf.block.entity.TableBlockEntity;
import com.starfish_studios.yaf.block.properties.ChangeableBlock;
import com.starfish_studios.yaf.block.properties.ColorList;
import com.starfish_studios.yaf.block.properties.Cushionable;
import com.starfish_studios.yaf.events.CushionableEvents;
import com.starfish_studios.yaf.registry.YAFTags;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.Containers;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
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
import net.minecraft.world.level.pathfinder.PathComputationType;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.Nullable;

public class TableBlock extends HalfTransparentBlock implements SimpleWaterloggedBlock, ChangeableBlock, EntityBlock {
    public static final DirectionProperty FACING = BlockStateProperties.HORIZONTAL_FACING;
    public static final BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;
    public static final BooleanProperty UPDATE = BooleanProperty.create("update");
    public static final BooleanProperty SHORT = BooleanProperty.create("short");

    protected static final VoxelShape TOP = Block.box(0.0D, 13.0D, 0.0D, 16.0D, 16.0D, 16.0D);
    protected static final VoxelShape TOP_CLOTHED = Block.box(0.0D, 7.0D, 0.0D, 16.0D, 16.0D, 16.0D);
    protected static final VoxelShape LEG_1 = Block.box(12.0D, 0.0D, 1.0D, 15.0D, 13.0D, 4.0D);
    protected static final VoxelShape LEG_2 = Block.box(12.0D, 0.0D, 12.0D, 15.0D, 13.0D, 15.0D);
    protected static final VoxelShape LEG_3 = Block.box(1.0D, 0.0D, 12.0D, 4.0D, 13.0D, 15.0D);
    protected static final VoxelShape LEG_4 = Block.box(1.0D, 0.0D, 1.0D, 4.0D, 13.0D, 4.0D);
    protected static final VoxelShape[] SHAPES = new VoxelShape[]{
            TOP,
            Shapes.or(TOP, LEG_1),
            Shapes.or(TOP, LEG_2),
            Shapes.or(TOP, LEG_1, LEG_2),
            Shapes.or(TOP, LEG_3),
            Shapes.or(TOP, LEG_1, LEG_3),
            Shapes.or(TOP, LEG_2, LEG_3),
            Shapes.or(TOP, LEG_1, LEG_2, LEG_3),
            Shapes.or(TOP, LEG_4),
            Shapes.or(TOP, LEG_1, LEG_4),
            Shapes.or(TOP, LEG_2, LEG_4),
            Shapes.or(TOP, LEG_1, LEG_2, LEG_4),
            Shapes.or(TOP, LEG_3, LEG_4),
            Shapes.or(TOP, LEG_1, LEG_3, LEG_4),
            Shapes.or(TOP, LEG_2, LEG_3, LEG_4),
            Shapes.or(TOP, LEG_1, LEG_2, LEG_3, LEG_4)
    };

    protected static final VoxelShape TOP_SHORT = Block.box(0.0D, 5.0D, 0.0D, 16.0D, 8.0D, 16.0D);
    protected static final VoxelShape TOP_CLOTHED_SHORT = Block.box(0.0D, 0.0D, 0.0D, 16.0D, 8.0D, 16.0D);
    protected static final VoxelShape LEG_1_SHORT = Block.box(12.0D, 0.0D, 1.0D, 15.0D, 5.0D, 4.0D);
    protected static final VoxelShape LEG_2_SHORT = Block.box(12.0D, 0.0D, 12.0D, 15.0D, 5.0D, 15.0D);
    protected static final VoxelShape LEG_3_SHORT = Block.box(1.0D, 0.0D, 12.0D, 4.0D, 5.0D, 15.0D);
    protected static final VoxelShape LEG_4_SHORT = Block.box(1.0D, 0.0D, 1.0D, 4.0D, 5.0D, 4.0D);
    protected static final VoxelShape[] SHAPES_SHORT = new VoxelShape[]{
            TOP_SHORT,
            Shapes.or(TOP_SHORT, LEG_1_SHORT),
            Shapes.or(TOP_SHORT, LEG_2_SHORT),
            Shapes.or(TOP_SHORT, LEG_1_SHORT, LEG_2_SHORT),
            Shapes.or(TOP_SHORT, LEG_3_SHORT),
            Shapes.or(TOP_SHORT, LEG_1_SHORT, LEG_3_SHORT),
            Shapes.or(TOP_SHORT, LEG_2_SHORT, LEG_3_SHORT),
            Shapes.or(TOP_SHORT, LEG_1_SHORT, LEG_2_SHORT, LEG_3_SHORT),
            Shapes.or(TOP_SHORT, LEG_4_SHORT),
            Shapes.or(TOP_SHORT, LEG_1_SHORT, LEG_4_SHORT),
            Shapes.or(TOP_SHORT, LEG_2_SHORT, LEG_4_SHORT),
            Shapes.or(TOP_SHORT, LEG_1_SHORT, LEG_2_SHORT, LEG_4_SHORT),
            Shapes.or(TOP_SHORT, LEG_3_SHORT),
            Shapes.or(TOP_SHORT, LEG_1_SHORT, LEG_3_SHORT),
            Shapes.or(TOP_SHORT, LEG_2_SHORT, LEG_3_SHORT),
            Shapes.or(TOP_SHORT, LEG_1_SHORT, LEG_2_SHORT, LEG_3_SHORT, LEG_4_SHORT)

    };

    public TableBlock(Properties properties) {
        super(properties);
        registerDefaultState(this.stateDefinition.any()
                .setValue(FACING, Direction.NORTH)
                .setValue(WATERLOGGED, false)
                .setValue(SHORT, false));
    }

    @Override
    public RenderShape getRenderShape(BlockState state) {
        return RenderShape.INVISIBLE;
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return new TableBlockEntity(pos, state);
    }

    @Override
    public InteractionResult use(BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hit) {
        if (tryChangeBlock(SHORT, state, level, pos, player, hand)) return InteractionResult.SUCCESS;

        if (hand == InteractionHand.MAIN_HAND) return InteractionResult.FAIL;
        return super.use(state, level, pos, player, hand, hit);
    }

    @Override
    public boolean tryChangeBlock(Property<?> property, BlockState state, LevelAccessor level, BlockPos pos, Player player, InteractionHand hand) {
        var success = ChangeableBlock.super.tryChangeBlock(property, state, level, pos, player, hand);
        if (success) {
            if (level.getBlockEntity(pos) instanceof TableBlockEntity entity) {
                entity.setShort(!state.getValue(SHORT));
            }
        }
        return success;
    }

    @Override
    public VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        int shape = 0;
        if (level.getBlockEntity(pos) instanceof TableBlockEntity tableBlockEntity) {
            if (tableBlockEntity.hasLeg(1)) shape += 1;
            if (tableBlockEntity.hasLeg(2)) shape += 2;
            if (tableBlockEntity.hasLeg(3)) shape += 4;
            if (tableBlockEntity.hasLeg(4)) shape += 8;
        }

        return state.getValue(SHORT) ? SHAPES_SHORT[shape] : SHAPES[shape];
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
        builder.add(FACING, UPDATE, WATERLOGGED, SHORT);
    }

    @Override
    protected boolean isPathfindable(BlockState state, PathComputationType pathComputationType) {
        return false;
    }

    @Override
    public BlockState rotate(BlockState state, Rotation rotation) {
        return switch(rotation) {
            case NONE, CLOCKWISE_90, CLOCKWISE_180, COUNTERCLOCKWISE_90 -> state.setValue(FACING, rotation.rotate(state.getValue(FACING)));
        };
    }

    @Override
    public BlockState mirror(BlockState state, Mirror mirror) {
        return state.rotate(mirror.getRotation(state.getValue(FACING)));
    }

    public BlockState getConnections(BlockState state, LevelAccessor level, BlockPos pos) {
        boolean n = validConnection(state, level.getBlockState(pos.north()));
        boolean e = validConnection(state, level.getBlockState(pos.east()));
        boolean s = validConnection(state, level.getBlockState(pos.south()));
        boolean w = validConnection(state, level.getBlockState(pos.west()));
        boolean leg1 = (!n && !e) || (n && e && !(validConnection(state, level.getBlockState(pos.north().east()))));
        boolean leg2 = (!e && !s) || (e && s && !(validConnection(state, level.getBlockState(pos.south().east()))));
        boolean leg3 = (!s && !w) || (s && w && !(validConnection(state, level.getBlockState(pos.south().west()))));
        boolean leg4 = (!n && !w) || (n && w && !(validConnection(state, level.getBlockState(pos.north().west()))));
        boolean update = ((n ? 1 : 0) + (e ? 1 : 0) + (s ? 1 : 0) + (w ? 1 : 0)) % 2 == 0;
        if (level.getBlockEntity(pos) instanceof TableBlockEntity tableBlockEntity) {
            tableBlockEntity.setLeg(1, leg1);
            tableBlockEntity.setLeg(2, leg2);
            tableBlockEntity.setLeg(3, leg3);
            tableBlockEntity.setLeg(4, leg4);
        }
        return state.setValue(UPDATE, update);
    }

    public boolean validConnection(BlockState thisState, BlockState state) {
        if (state.hasProperty(SHORT)) {
            return (state.getValue(SHORT) && thisState.getValue(SHORT)) || (!state.getValue(SHORT) && !thisState.getValue(SHORT));
        }
        return state.is(YAFTags.BlockTags.TABLES_CONNECTABLE);
    }

    @Override
    public void onRemove(BlockState state, Level level, BlockPos pos, BlockState newState, boolean movedByPiston) {
        if (level.getBlockEntity(pos) instanceof Cushionable cushionable) {
            cushionable.dropCarpet(level, pos);
        }
        super.onRemove(state, level, pos, newState, movedByPiston);
    }
}
