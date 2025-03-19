package com.crispytwig.nookcranny.blocks;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.StringRepresentable;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.HorizontalDirectionalBlock;
import net.minecraft.world.level.block.SimpleWaterloggedBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class CurtainBlock extends Block implements SimpleWaterloggedBlock {

    public static final BooleanProperty OPEN = BooleanProperty.create("open");
    public static final EnumProperty<CurtainShape> SHAPE = EnumProperty.create("shape", CurtainShape.class);
    public static final DirectionProperty FACING = HorizontalDirectionalBlock.FACING;
    public static final BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;

    private static final VoxelShape SHAPE_NS = Block.box(0.0, 0.0, 7.0, 16.0, 16.0, 9.0);
    private static final VoxelShape SHAPE_EW = Block.box(7.0, 0.0, 0.0, 9.0, 16.0, 16.0);

    public CurtainBlock(Properties properties) {
        super(properties);
        this.registerDefaultState(this.stateDefinition.any()
                .setValue(FACING, Direction.NORTH)
                .setValue(WATERLOGGED, false)
                .setValue(SHAPE, CurtainShape.SINGLE)
                .setValue(OPEN, false));
    }

    public enum CurtainShape implements StringRepresentable {
        SINGLE("single"),
        LEFT("left"),
        MIDDLE("middle"),
        RIGHT("right"),
        BOTTOM_SINGLE("bottom_single"),
        BOTTOM_LEFT("bottom_left"),
        BOTTOM_MIDDLE("bottom_middle"),
        BOTTOM_RIGHT("bottom_right"),
        CORNER_LEFT("corner_left"),
        CORNER_RIGHT("corner_right"),
        CURTAIN_TOP("curtain_top"),
        CURTAIN_TOP_SINGLE("curtain_top_single");

        private final String name;
        CurtainShape(String name) {
            this.name = name;
        }
        @Override
        public String getSerializedName() {
            return this.name;
        }
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(FACING, WATERLOGGED, SHAPE, OPEN);
    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        BlockPos pos = context.getClickedPos();
        FluidState fluidState = context.getLevel().getFluidState(pos);
        BlockState state = this.defaultBlockState()
                .setValue(FACING, context.getHorizontalDirection().getOpposite())
                .setValue(WATERLOGGED, fluidState.getType() == Fluids.WATER)
                .setValue(OPEN, false);
        return state.setValue(SHAPE, computeShape(state, context.getLevel(), pos));
    }

    @Override
    public @NotNull BlockState updateShape(BlockState state, @NotNull Direction direction, @NotNull BlockState neighborState,
                                           @NotNull LevelAccessor level, @NotNull BlockPos pos, @NotNull BlockPos neighborPos) {
        if (state.getValue(WATERLOGGED)) {
            level.scheduleTick(pos, Fluids.WATER, Fluids.WATER.getTickDelay(level));
        }
        if (direction.getAxis().isHorizontal() || direction == Direction.UP || direction == Direction.DOWN) {
            return state.setValue(SHAPE, computeShape(state, (Level) level, pos));
        }
        return state;
    }

    @Override
    public @NotNull InteractionResult use(@NotNull BlockState state, Level level, @NotNull BlockPos pos, @NotNull Player player,
                                          @NotNull InteractionHand hand, @NotNull BlockHitResult hit) {
        if (level.isClientSide()) {
            return InteractionResult.SUCCESS;
        }
        boolean open = state.getValue(OPEN);
        List<BlockPos> group = new ArrayList<>();
        collectConnectedCurtains(level, pos, state.getValue(FACING), group);
        for (BlockPos p : group) {
            BlockState s = level.getBlockState(p);
            if (s.getBlock() instanceof CurtainBlock) {
                level.setBlock(p, s.setValue(OPEN, !open), 3);
            }
        }
        return InteractionResult.SUCCESS;
    }

    @Override
    public VoxelShape getShape(BlockState state, @NotNull BlockGetter worldIn, @NotNull BlockPos pos, @NotNull CollisionContext context) {
        Direction facing = state.getValue(FACING);
        switch (facing) {
            case NORTH:
            case SOUTH:
                return SHAPE_NS;
            case EAST:
            case WEST:
                return SHAPE_EW;
            default:
                return SHAPE_NS;
        }
    }

    @Override
    public @NotNull VoxelShape getCollisionShape(BlockState state, @NotNull BlockGetter worldIn, @NotNull BlockPos pos, @NotNull CollisionContext context) {
        if (state.getValue(OPEN)) {
            return Shapes.empty();
        }
        return this.getShape(state, worldIn, pos, context);
    }

    private void collectConnectedCurtains(Level level, BlockPos pos, Direction facing, List<BlockPos> group) {
        if (group.contains(pos)) return;
        group.add(pos);
        Direction left = facing.getCounterClockWise();
        Direction right = facing.getClockWise();
        BlockPos posLeft = pos.relative(left);
        BlockPos posRight = pos.relative(right);
        BlockPos posUp = pos.above();
        BlockPos posDown = pos.below();
        if (isConnected(level, posLeft, facing)) {
            collectConnectedCurtains(level, posLeft, facing, group);
        }
        if (isConnected(level, posRight, facing)) {
            collectConnectedCurtains(level, posRight, facing, group);
        }
        if (isConnected(level, posUp, facing)) {
            collectConnectedCurtains(level, posUp, facing, group);
        }
        if (isConnected(level, posDown, facing)) {
            collectConnectedCurtains(level, posDown, facing, group);
        }
    }

    private boolean isConnected(Level level, BlockPos pos, Direction facing) {
        BlockState s = level.getBlockState(pos);
        return s.getBlock() instanceof CurtainBlock && s.getValue(FACING) == facing;
    }

    private boolean hasCurtainAboveIgnoringHoles(Level level, BlockPos pos, Direction facing) {
        for (int y = pos.getY() + 1; y <= level.getMaxBuildHeight(); y++) {
            BlockPos checkPos = new BlockPos(pos.getX(), y, pos.getZ());
            BlockState bs = level.getBlockState(checkPos);
            if (bs.getBlock() instanceof CurtainBlock && bs.getValue(FACING) == facing) {
                return true;
            }
            if (!level.isEmptyBlock(checkPos) && !(bs.getBlock() instanceof CurtainBlock)) {
                return false;
            }
        }
        return false;
    }

    private boolean hasCurtainBelowIgnoringHoles(Level level, BlockPos pos, Direction facing) {
        for (int y = pos.getY() - 1; y >= level.getMinBuildHeight(); y--) {
            BlockPos checkPos = new BlockPos(pos.getX(), y, pos.getZ());
            BlockState bs = level.getBlockState(checkPos);
            if (bs.getBlock() instanceof CurtainBlock && bs.getValue(FACING) == facing) {
                return true;
            }
            if (!level.isEmptyBlock(checkPos) && !(bs.getBlock() instanceof CurtainBlock)) {
                return false;
            }
        }
        return false;
    }

    private CurtainShape computeShape(BlockState state, Level level, BlockPos pos) {
        Direction facing = state.getValue(FACING);
        Direction leftDir = facing.getCounterClockWise();
        Direction rightDir = facing.getClockWise();
        boolean connectedAbove = hasCurtainAboveIgnoringHoles(level, pos, facing);
        boolean connectedBelow = hasCurtainBelowIgnoringHoles(level, pos, facing);
        boolean connectedLeft = isConnected(level, pos.relative(leftDir), facing);
        boolean connectedRight = isConnected(level, pos.relative(rightDir), facing);

        if (!connectedAbove && connectedBelow && !connectedLeft && !connectedRight) {
            return CurtainShape.CURTAIN_TOP_SINGLE;
        }
        if (!connectedAbove && !connectedBelow) {
            if (!connectedLeft && connectedRight) return CurtainShape.LEFT;
            if (connectedLeft && !connectedRight) return CurtainShape.RIGHT;
            if (connectedLeft) return CurtainShape.MIDDLE;
            return CurtainShape.SINGLE;
        }
        if (!connectedAbove) {
            if (!connectedLeft) return CurtainShape.CORNER_LEFT;
            if (!connectedRight) return CurtainShape.CORNER_RIGHT;
            return CurtainShape.CURTAIN_TOP;
        } else if (!connectedBelow) {
            if (!connectedLeft && connectedRight) return CurtainShape.BOTTOM_LEFT;
            if (connectedLeft && !connectedRight) return CurtainShape.BOTTOM_RIGHT;
            if (connectedLeft) return CurtainShape.BOTTOM_MIDDLE;
            return CurtainShape.BOTTOM_SINGLE;
        } else {
            if (!connectedLeft && connectedRight) return CurtainShape.LEFT;
            if (connectedLeft && !connectedRight) return CurtainShape.RIGHT;
            if (connectedLeft) return CurtainShape.MIDDLE;
            return CurtainShape.SINGLE;
        }
    }
}
