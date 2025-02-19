package com.crispytwig.nookcranny.blocks;

import com.crispytwig.nookcranny.util.block.BlockPart;
import com.crispytwig.nookcranny.blocks.entities.ShelfBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.Containers;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.*;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.Nullable;

public class ShelfBlock extends BaseEntityBlock implements SimpleWaterloggedBlock {
//    public static final EnumProperty<HorizontalConnectionType> TYPE = NCBlockProperties.HORIZONTAL_CONNECTION_TYPE;
    public static final EnumProperty<SlabType> HALF = EnumProperty.create("half", SlabType.class);
    public static final DirectionProperty FACING = BlockStateProperties.HORIZONTAL_FACING;
    public static final BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;

    public static final VoxelShape NORTH_TOP_AAAB = Block.box(0.0, 8.0, 9.0, 16.0, 16.0, 16.0);
    public static final VoxelShape NORTH_BOTTOM_AABB = Block.box(0.0, 0.0, 9.0, 16.0, 8.0, 16.0);
    public static final VoxelShape EAST_TOP_AAAB = Block.box(0.0, 8.0, 0.0, 7.0, 16.0, 16.0);
    public static final VoxelShape EAST_BOTTOM_AABB = Block.box(0.0, 0.0, 0.0, 7.0, 8.0, 16.0);
    public static final VoxelShape SOUTH_TOP_AAAB = Block.box(0.0, 8.0, 0.0, 16.0, 16.0, 7.0);
    public static final VoxelShape SOUTH_BOTTOM_AABB = Block.box(0.0, 0.0, 0.0, 16.0, 8.0, 7.0);
    public static final VoxelShape WEST_TOP_AAAB = Block.box(9.0, 8.0, 0.0, 16.0, 16.0, 16.0);
    public static final VoxelShape WEST_BOTTOM_AABB = Block.box(9.0, 0.0, 0.0, 16.0, 8.0, 16.0);

    public ShelfBlock(Properties properties) {
        super(properties);
        this.defaultBlockState()
//                .setValue(TYPE, HorizontalConnectionType.SINGLE)
                .setValue(HALF, SlabType.BOTTOM)
                .setValue(FACING, Direction.NORTH)
                .setValue(WATERLOGGED, false);
    }
    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return new ShelfBlockEntity(pos, state);
    }

    public RenderShape getRenderShape(BlockState state) {
        return RenderShape.MODEL;
    }

    @Override
    public boolean canBeReplaced(BlockState state, BlockPlaceContext useContext) {
        ItemStack itemStack = useContext.getItemInHand();
        SlabType slabType = state.getValue(HALF);
        if (slabType == SlabType.DOUBLE || !itemStack.is(this.asItem())) return false;

        if (!useContext.replacingClickedOnBlock()) return true;

        boolean bl = useContext.getClickLocation().y - (double)useContext.getClickedPos().getY() > 0.5;
        Direction direction = useContext.getClickedFace();
        if (slabType == SlabType.BOTTOM) return direction == Direction.UP || bl && direction.getAxis().isHorizontal();

        return direction == Direction.DOWN || !bl && direction.getAxis().isHorizontal();
    }

    @Override
    public InteractionResult use(BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hit) {
        //if (hit.getDirection() != Direction.UP) return InteractionResult.PASS;
        BlockEntity blockentity = level.getBlockEntity(pos);
        if (!(blockentity instanceof ShelfBlockEntity shelfBE)) return InteractionResult.PASS;

        Direction facing = state.getValue(FACING);
        int slot = BlockPart.get2D(pos, hit.getLocation(), Direction.UP, facing.getClockWise(), 2, 2);

        ItemStack stack = player.getItemInHand(hand);
        if (!stack.isEmpty()) {
            if (!level.isClientSide && shelfBE.placeItem(player.getAbilities().instabuild ? stack.copy() : stack, slot)) {
                level.playSound(null, pos, SoundEvents.ITEM_FRAME_ADD_ITEM, SoundSource.BLOCKS, 1.0F, 1.0F);
                return InteractionResult.SUCCESS;
            }
            return InteractionResult.CONSUME;
        }

        if (shelfBE.removeItem(slot, player, level)) return InteractionResult.SUCCESS;

        return InteractionResult.PASS;
    }

    @Override
    public void onRemove(BlockState state, Level level, BlockPos pos, BlockState newState, boolean isMoving) {
        if (state.is(newState.getBlock())) return;

        BlockEntity blockentity = level.getBlockEntity(pos);
        if (blockentity instanceof ShelfBlockEntity shelfBE) Containers.dropContents(level, pos, shelfBE.getItems());
        super.onRemove(state, level, pos, newState, isMoving);
    }

    @Override
    public VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        Direction direction = state.getValue(FACING);

        VoxelShape shape = Shapes.empty();
        if (state.getValue(HALF) == SlabType.TOP) {
            if (direction == Direction.NORTH) shape = NORTH_TOP_AAAB;
            if (direction == Direction.EAST) shape = EAST_TOP_AAAB;
            if (direction == Direction.SOUTH) shape = SOUTH_TOP_AAAB;
            if (direction == Direction.WEST) shape = WEST_TOP_AAAB;
        } else if (state.getValue(HALF) == SlabType.BOTTOM) {
            if (direction == Direction.NORTH) shape = NORTH_BOTTOM_AABB;
            if (direction == Direction.EAST) shape = EAST_BOTTOM_AABB;
            if (direction == Direction.SOUTH) shape = SOUTH_BOTTOM_AABB;
            if (direction == Direction.WEST) shape = WEST_BOTTOM_AABB;
        } else {
            if (direction == Direction.NORTH) shape = Shapes.or(NORTH_TOP_AAAB, NORTH_BOTTOM_AABB);
            if (direction == Direction.EAST) shape = Shapes.or(EAST_TOP_AAAB, EAST_BOTTOM_AABB);
            if (direction == Direction.SOUTH) shape = Shapes.or(SOUTH_TOP_AAAB, SOUTH_BOTTOM_AABB);
            if (direction == Direction.WEST) shape = Shapes.or(WEST_TOP_AAAB, WEST_BOTTOM_AABB);
        }
        return shape;
    }

//    @Override
//    public BlockState updateShape(BlockState state, Direction direction, BlockState neighborState, LevelAccessor level, BlockPos currentPos, BlockPos neighborPos) {
//        if (state.getValue(WATERLOGGED)) level.scheduleTick(currentPos, Fluids.WATER, Fluids.WATER.getTickDelay(level));
//
//        BlockState above = level.getBlockState(currentPos.above());
//        if (direction == Direction.UP && (above.isFaceSturdy(level, currentPos, Direction.DOWN) && !above.getVisualShape(level, currentPos.above(), CollisionContext.empty()).isEmpty())) {
//            if (level.getBlockEntity(currentPos) instanceof ShelfBlockEntity shelfBE) shelfBE.removeAllItems();
//        }
//
//        Direction facing = state.getValue(FACING);
//        Direction dirL = facing.getClockWise();
//        Direction dirR = facing.getCounterClockWise();
//        if (direction != dirL && direction != dirR) return state;
//
//        BlockState l_state = level.getBlockState(currentPos.relative(dirL));
//        BlockState r_state = level.getBlockState(currentPos.relative(dirR));
//        boolean l_side = l_state.getBlock() instanceof ShelfBlock && l_state.getValue(FACING) == facing;
//        boolean r_side = r_state.getBlock() instanceof ShelfBlock && r_state.getValue(FACING) == facing;
//        HorizontalConnectionType type = l_side && r_side ?
//                HorizontalConnectionType.MIDDLE
//                : (r_side ? HorizontalConnectionType.LEFT
//                : (l_side ? HorizontalConnectionType.RIGHT
//                : HorizontalConnectionType.SINGLE));
//        return state.setValue(TYPE, type);
//    }

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

    @Nullable
    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        BlockPos clickedPos = context.getClickedPos();
        BlockState stateExisting = context.getLevel().getBlockState(clickedPos);

        if (stateExisting.is(this)) return stateExisting.setValue(HALF, SlabType.DOUBLE);

        boolean waterlogged = context.getLevel().getFluidState(clickedPos).getType() == Fluids.WATER;
        BlockState state = this.defaultBlockState()
                .setValue(HALF, SlabType.BOTTOM)
                .setValue(FACING, context.getHorizontalDirection().getOpposite())
                .setValue(WATERLOGGED, waterlogged);
        Direction direction = context.getClickedFace();
        return direction != Direction.DOWN && (direction == Direction.UP || !(context.getClickLocation().y - (double)clickedPos.getY() > 0.5)) ? state : state.setValue(HALF, SlabType.TOP);

    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(HALF, FACING, WATERLOGGED/*, TYPE*/);
    }
}
