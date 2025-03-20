package com.crispytwig.nookcranny.blocks;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.network.chat.Component;
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
                level.setBlock(p, s.setValue(OPEN, !currentlyOpen), 2);
            }
        }
        for (BlockPos p : group) {
            BlockState s = level.getBlockState(p);
            if (s.getBlock() instanceof CurtainBlock cBlock) {
                BlockState newState = s.setValue(SHAPE, cBlock.computeShape(s, level, p));
                level.setBlock(p, newState, 2);
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
            case NORTH, SOUTH -> SHAPE_NS;
            case EAST, WEST -> SHAPE_EW;
            default -> SHAPE_NS;
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
                level.setBlock(p, newState, 2);
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

        if (!connectedBelow && connectedAbove && connectedLeft && connectedRight) {
            return CurtainShape.BOTTOM_MIDDLE;
        }

        if (!connectedAbove && connectedBelow && !connectedLeft && !connectedRight) {
            return CurtainShape.CURTAIN_TOP_SINGLE;
        }
        if (!connectedAbove && !connectedBelow) {
            if (connectedLeft && connectedRight) return CurtainShape.MIDDLE;
            if (!connectedLeft && connectedRight) return CurtainShape.LEFT;
            if (connectedLeft && !connectedRight) return CurtainShape.RIGHT;
            return CurtainShape.SINGLE;
        }
        if (!connectedAbove) {
            if (connectedLeft && connectedRight) return CurtainShape.CURTAIN_TOP;
            if (!connectedLeft && connectedRight) return CurtainShape.CORNER_LEFT;
            if (connectedLeft && !connectedRight) return CurtainShape.CORNER_RIGHT;
            return CurtainShape.CURTAIN_TOP;
        }
        if (!connectedBelow) {
            if (connectedLeft && connectedRight) return CurtainShape.BOTTOM_MIDDLE;
            if (connectedLeft && !connectedRight) {
                BlockState aboveState = level.getBlockState(abovePos);
                BlockState leftState = level.getBlockState(leftPos);
                if (aboveState.getBlock() instanceof CurtainBlock &&
                        aboveState.getValue(SHAPE) == CurtainShape.MIDDLE &&
                        leftState.getBlock() instanceof CurtainBlock &&
                        leftState.getValue(SHAPE) == CurtainShape.BOTTOM_LEFT) {
                    return CurtainShape.BOTTOM_MIDDLE;
                }
                return CurtainShape.BOTTOM_RIGHT;
            }
            if (!connectedLeft && connectedRight) {
                BlockState aboveState = level.getBlockState(abovePos);
                BlockState rightState = level.getBlockState(rightPos);
                if (aboveState.getBlock() instanceof CurtainBlock &&
                        aboveState.getValue(SHAPE) == CurtainShape.MIDDLE &&
                        rightState.getBlock() instanceof CurtainBlock &&
                        rightState.getValue(SHAPE) == CurtainShape.BOTTOM_RIGHT) {
                    return CurtainShape.BOTTOM_MIDDLE;
                }
                return CurtainShape.BOTTOM_LEFT;
            }
            return CurtainShape.BOTTOM_SINGLE;
        }

        if (connectedLeft && connectedRight) return CurtainShape.MIDDLE;
        if (!connectedLeft && connectedRight) return CurtainShape.LEFT;
        if (connectedLeft && !connectedRight) return CurtainShape.RIGHT;
        return CurtainShape.SINGLE;
    }
}