package com.crispytwig.omf.block;

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
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class CurtainBlock extends Block implements SimpleWaterloggedBlock {
    public static final BooleanProperty OPEN = BooleanProperty.create("open");
    public static final EnumProperty<CurtainShape> SHAPE = EnumProperty.create("shape", CurtainShape.class);
    public static final DirectionProperty FACING = HorizontalDirectionalBlock.FACING;
    public static final BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;

    private static final VoxelShape SHAPE_S = Block.box(0.0, 0.0, 0.0, 16.0, 16.0, 2.0);
    private static final VoxelShape SHAPE_E = Block.box(0.0, 0.0, 0.0, 2.0, 16.0, 16.0);
    private static final VoxelShape SHAPE_N = Block.box(0.0, 0.0, 14.0, 16.0, 16.0, 16.0);
    private static final VoxelShape SHAPE_W = Block.box(14.0, 0.0, 0.0, 16.0, 16.0, 16.0);

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
        TOP("curtain_top"),
        TOP_SINGLE("curtain_top_single");

        private final String name;

        CurtainShape(String name) {
            this.name = name;
        }

        @Override
        public @NotNull String getSerializedName() {
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
    public @NotNull BlockState updateShape(BlockState state,
                                           @NotNull Direction direction,
                                           @NotNull BlockState neighborState,
                                           @NotNull LevelAccessor level,
                                           @NotNull BlockPos pos,
                                           @NotNull BlockPos neighborPos) {
        if (state.getValue(WATERLOGGED)) {
            level.scheduleTick(pos, Fluids.WATER, Fluids.WATER.getTickDelay(level));
        }
        if (direction.getAxis().isHorizontal() || direction == Direction.UP || direction == Direction.DOWN) {
            if (level instanceof Level realLevel) {
                updateCurtainGroup(realLevel, pos);
                if (neighborState.getBlock() instanceof CurtainBlock) {
                    updateCurtainGroup(realLevel, neighborPos);
                }
            }
        }
        return state;
    }

    @Override
    public @NotNull InteractionResult use(@NotNull BlockState state,
                                          Level level,
                                          @NotNull BlockPos pos,
                                          @NotNull Player player,
                                          @NotNull InteractionHand hand,
                                          @NotNull BlockHitResult hit) {
        if (level.isClientSide()) {
            return InteractionResult.SUCCESS;
        }
        boolean currentlyOpen = state.getValue(OPEN);
        List<BlockPos> group = new ArrayList<>();
        collectConnectedCurtains(level, pos, state.getValue(FACING), group);
        for (BlockPos p : group) {
            BlockState s = level.getBlockState(p);
            if (s.getBlock() instanceof CurtainBlock) {
                BlockState toggled = s.setValue(OPEN, !currentlyOpen);
                if (!s.equals(toggled)) {
                    level.setBlock(p, toggled, 2);
                }
            }
        }
        for (BlockPos p : group) {
            BlockState s = level.getBlockState(p);
            if (s.getBlock() instanceof CurtainBlock cBlock) {
                BlockState newState = s.setValue(SHAPE, cBlock.computeShape(s, level, p));
                if (!s.equals(newState)) {
                    level.setBlock(p, newState, 2);
                }
            }
        }
        return InteractionResult.SUCCESS;
    }

    @Override
    public VoxelShape getShape(BlockState state,
                               @NotNull BlockGetter worldIn,
                               @NotNull BlockPos pos,
                               @NotNull CollisionContext context) {
        Direction facing = state.getValue(FACING);
        return switch (facing) {
            case NORTH  -> SHAPE_N;
            case EAST -> SHAPE_E;
            case WEST -> SHAPE_W;
            default -> SHAPE_S;
        };
    }

    @Override
    public @NotNull VoxelShape getCollisionShape(BlockState state,
                                                 @NotNull BlockGetter worldIn,
                                                 @NotNull BlockPos pos,
                                                 @NotNull CollisionContext context) {
        if (state.getValue(OPEN)) {
            return Shapes.empty();
        }
        return this.getShape(state, worldIn, pos, context);
    }

    private void updateCurtainGroup(Level level, BlockPos origin) {
        BlockState originState = level.getBlockState(origin);
        if (!(originState.getBlock() instanceof CurtainBlock)) {
            return;
        }
        List<BlockPos> group = new ArrayList<>();
        collectConnectedCurtains(level, origin, originState.getValue(FACING), group);
        for (BlockPos p : group) {
            BlockState oldState = level.getBlockState(p);
            if (oldState.getBlock() instanceof CurtainBlock cBlock) {
                BlockState newState = oldState.setValue(SHAPE, cBlock.computeShape(oldState, level, p));
                if (!oldState.equals(newState)) {
                    level.setBlock(p, newState, 2);
                }
            }
        }
    }

    private void collectConnectedCurtains(Level level, BlockPos pos, Direction facing, List<BlockPos> group) {
        if (group.contains(pos)) return;
        group.add(pos);
        Direction left = facing.getCounterClockWise();
        Direction right = facing.getClockWise();
        BlockPos above = pos.above();
        if (isSameCurtain(level, above, facing)) {
            collectConnectedCurtains(level, above, facing, group);
        }
        BlockPos below = pos.below();
        if (isSameCurtain(level, below, facing)) {
            collectConnectedCurtains(level, below, facing, group);
        }
        BlockPos leftPos = pos.relative(left);
        if (isSameCurtain(level, leftPos, facing)) {
            collectConnectedCurtains(level, leftPos, facing, group);
        }
        BlockPos rightPos = pos.relative(right);
        if (isSameCurtain(level, rightPos, facing)) {
            collectConnectedCurtains(level, rightPos, facing, group);
        }
    }

    private boolean isSameCurtain(Level level, BlockPos pos, Direction facing) {
        if (!level.isLoaded(pos)) return false;
        BlockState s = level.getBlockState(pos);
        return s.getBlock() instanceof CurtainBlock && s.getValue(FACING) == facing;
    }

    public CurtainShape computeShape(BlockState state, Level level, BlockPos pos) {
        // Directions
        Direction facing = state.getValue(FACING);
        Direction leftDir = facing.getCounterClockWise();
        Direction rightDir = facing.getClockWise();
        BlockPos abovePos = pos.above();
        BlockPos belowPos = pos.below();
        BlockPos leftPos = pos.relative(leftDir);
        BlockPos rightPos = pos.relative(rightDir);

        boolean connectedAbove = isSameCurtain(level, abovePos, facing);
        boolean connectedBelow = isSameCurtain(level, belowPos, facing);
        boolean connectedLeft  = isSameCurtain(level, leftPos, facing);
        boolean connectedRight = isSameCurtain(level, rightPos, facing);

        if (!connectedAbove && !connectedBelow && !connectedLeft && !connectedRight) {
            return CurtainShape.SINGLE;
        }

        BlockState rightState = level.getBlockState(rightPos);
        BlockState leftState = level.getBlockState(leftPos);
        BlockState aboveState = level.getBlockState(abovePos);
        BlockState belowState = level.getBlockState(belowPos);

        boolean noLeftCurtain = !connectedLeft;
        boolean noRightCurtain = !connectedRight;
        boolean noBelowCurtain = !connectedBelow;
        boolean noAboveCurtain = !connectedAbove;

        boolean rightIsOpenAndRight = rightState.getBlock() instanceof CurtainBlock &&
                rightState.getValue(OPEN) &&
                rightState.getValue(SHAPE) == CurtainShape.RIGHT;

        boolean leftIsOpenAndLeft = leftState.getBlock() instanceof CurtainBlock &&
                leftState.getValue(OPEN) &&
                leftState.getValue(SHAPE) == CurtainShape.LEFT;

        boolean aboveIsOpenAndMiddle = aboveState.getBlock() instanceof CurtainBlock &&
                aboveState.getValue(OPEN) &&
                aboveState.getValue(SHAPE) == CurtainShape.MIDDLE;

        boolean aboveIsOpenAndLeft = aboveState.getBlock() instanceof CurtainBlock &&
                aboveState.getValue(OPEN) &&
                aboveState.getValue(SHAPE) == CurtainShape.LEFT;

        boolean belowIsOpenAndLeft = belowState.getBlock() instanceof CurtainBlock &&
                belowState.getValue(OPEN) &&
                belowState.getValue(SHAPE) == CurtainShape.LEFT;

        boolean belowIsOpenAndRight = belowState.getBlock() instanceof CurtainBlock &&
                belowState.getValue(OPEN) &&
                belowState.getValue(SHAPE) == CurtainShape.RIGHT;

        boolean aboveIsOpenAndRight = aboveState.getBlock() instanceof CurtainBlock &&
                aboveState.getValue(OPEN) &&
                aboveState.getValue(SHAPE) == CurtainShape.RIGHT;

        boolean aboveIsOpenAndTop = aboveState.getBlock() instanceof CurtainBlock &&
                aboveState.getValue(OPEN) &&
                aboveState.getValue(SHAPE) == CurtainShape.TOP;

        boolean rightIsOpenAndTop = rightState.getBlock() instanceof CurtainBlock &&
                rightState.getValue(OPEN) &&
                rightState.getValue(SHAPE) == CurtainShape.TOP;

        boolean leftIsOpenAndTop = leftState.getBlock() instanceof CurtainBlock &&
                leftState.getValue(OPEN) &&
                leftState.getValue(SHAPE) == CurtainShape.TOP;

        boolean rightIsOpenAndCorner = rightState.getBlock() instanceof CurtainBlock &&
                rightState.getValue(OPEN) &&
                rightState.getValue(SHAPE) == CurtainShape.CORNER_RIGHT;

        boolean leftIsOpenAndCorner = leftState.getBlock() instanceof CurtainBlock &&
                leftState.getValue(OPEN) &&
                leftState.getValue(SHAPE) == CurtainShape.CORNER_LEFT;

        boolean rightIsOpenAndMiddle = rightState.getBlock() instanceof CurtainBlock &&
                rightState.getValue(OPEN) &&
                rightState.getValue(SHAPE) == CurtainShape.MIDDLE;

        boolean leftIsOpenAndMiddle = leftState.getBlock() instanceof CurtainBlock &&
                leftState.getValue(OPEN) &&
                leftState.getValue(SHAPE) == CurtainShape.MIDDLE;

        // Connections M
        if (noLeftCurtain && rightIsOpenAndRight && aboveIsOpenAndMiddle && noBelowCurtain) {
            return CurtainShape.BOTTOM_MIDDLE;
        }
        if (noRightCurtain && leftIsOpenAndLeft && aboveIsOpenAndMiddle && noBelowCurtain) {
            return CurtainShape.BOTTOM_MIDDLE;
        }
        if (noRightCurtain && noLeftCurtain && aboveIsOpenAndLeft && noBelowCurtain) {
            return CurtainShape.BOTTOM_LEFT;
        }
        if (noRightCurtain && noLeftCurtain && aboveIsOpenAndRight && noBelowCurtain) {
            return CurtainShape.BOTTOM_RIGHT;
        }
        if (rightIsOpenAndTop && leftIsOpenAndCorner && noAboveCurtain && noBelowCurtain) {
            return CurtainShape.BOTTOM_SINGLE;
        }
        if (noRightCurtain && noLeftCurtain && aboveIsOpenAndLeft && belowIsOpenAndLeft) {
            return CurtainShape.LEFT;
        }
        if (rightIsOpenAndTop && leftIsOpenAndTop && noAboveCurtain && noBelowCurtain) {
            return CurtainShape.TOP;
        }
        if (rightIsOpenAndCorner && leftIsOpenAndCorner && noAboveCurtain && noBelowCurtain) {
            return CurtainShape.TOP;
        }
        if (rightIsOpenAndRight && noLeftCurtain && aboveIsOpenAndTop && noBelowCurtain) {
            return CurtainShape.MIDDLE;
        }
        if (noRightCurtain && leftIsOpenAndLeft && aboveIsOpenAndTop && noBelowCurtain) {
            return CurtainShape.MIDDLE;
        }

        if (rightIsOpenAndMiddle && noLeftCurtain && aboveIsOpenAndTop && noBelowCurtain) {
            return CurtainShape.MIDDLE;
        }

        if (noRightCurtain && leftIsOpenAndMiddle && aboveIsOpenAndTop && noBelowCurtain) {
            return CurtainShape.MIDDLE;
        }

        if (rightIsOpenAndRight && noLeftCurtain && aboveIsOpenAndLeft && noBelowCurtain) {
            return CurtainShape.MIDDLE;
        }

        if (noRightCurtain && leftIsOpenAndLeft && aboveIsOpenAndRight && noBelowCurtain) {
            return CurtainShape.MIDDLE;
        }

        if (rightIsOpenAndRight && noLeftCurtain && aboveIsOpenAndMiddle && belowIsOpenAndLeft) {
            return CurtainShape.MIDDLE;
        }

        if (noRightCurtain && leftIsOpenAndLeft && aboveIsOpenAndMiddle && belowIsOpenAndRight) {
            return CurtainShape.MIDDLE;
        }

        // Dynamic connections
        if (!connectedBelow && connectedAbove && connectedLeft && connectedRight) {
            return CurtainShape.BOTTOM_MIDDLE;
        }
        if (!connectedAbove && connectedBelow && !connectedLeft && !connectedRight) {
            return CurtainShape.TOP_SINGLE;
        }
        if (!connectedAbove && !connectedBelow) {
            if (connectedLeft || connectedRight) {
                return CurtainShape.SINGLE;
            }
            return CurtainShape.SINGLE;
        }
        if (!connectedAbove) {
            if (connectedLeft && connectedRight) return CurtainShape.TOP;
            if (!connectedLeft) return CurtainShape.CORNER_LEFT;
            if (!connectedRight) return CurtainShape.CORNER_RIGHT;
            return CurtainShape.TOP;
        }
        if (!connectedBelow) {
            if (connectedLeft && connectedRight) return CurtainShape.BOTTOM_MIDDLE;
            if (connectedLeft) {
                BlockState aboveNeighbor = level.getBlockState(abovePos);
                BlockState leftNeighbor = level.getBlockState(leftPos);
                if (aboveNeighbor.getBlock() instanceof CurtainBlock &&
                        aboveNeighbor.getValue(SHAPE) == CurtainShape.MIDDLE &&
                        leftNeighbor.getBlock() instanceof CurtainBlock &&
                        leftNeighbor.getValue(SHAPE) == CurtainShape.BOTTOM_LEFT) {
                    return CurtainShape.BOTTOM_MIDDLE;
                }
                return CurtainShape.BOTTOM_RIGHT;
            }
            if (connectedRight) {
                BlockState aboveNeighbor = level.getBlockState(abovePos);
                BlockState rightNeighbor = level.getBlockState(rightPos);
                if (aboveNeighbor.getBlock() instanceof CurtainBlock &&
                        aboveNeighbor.getValue(SHAPE) == CurtainShape.MIDDLE &&
                        rightNeighbor.getBlock() instanceof CurtainBlock &&
                        rightNeighbor.getValue(SHAPE) == CurtainShape.BOTTOM_RIGHT) {
                    return CurtainShape.BOTTOM_MIDDLE;
                }
                return CurtainShape.BOTTOM_LEFT;
            }
            return CurtainShape.BOTTOM_SINGLE;
        }
        if (connectedLeft && connectedRight) return CurtainShape.MIDDLE;
        if (!connectedLeft && connectedRight) return CurtainShape.LEFT;
        if (connectedLeft) return CurtainShape.RIGHT;
        return CurtainShape.SINGLE;
    }
}
