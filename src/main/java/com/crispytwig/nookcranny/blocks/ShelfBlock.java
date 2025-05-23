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
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.AttachFace;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.level.block.state.properties.SlabType;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class ShelfBlock extends BaseEntityBlock implements SimpleWaterloggedBlock {
    public static final EnumProperty<SlabType> HALF = EnumProperty.create("half", SlabType.class);
    public static final DirectionProperty FACING = BlockStateProperties.HORIZONTAL_FACING;
    public static final BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;
    public static final EnumProperty<AttachFace> FACE = BlockStateProperties.ATTACH_FACE;
    public static final VoxelShape NORTH_TOP_AAAB = Block.box(0.0, 8.0, 9.0, 16.0, 16.0, 16.0);
    public static final VoxelShape NORTH_BOTTOM_AABB = Block.box(0.0, 0.0, 9.0, 16.0, 8.0, 16.0);
    public static final VoxelShape EAST_TOP_AAAB = Block.box(0.0, 8.0, 0.0, 7.0, 16.0, 16.0);
    public static final VoxelShape EAST_BOTTOM_AABB = Block.box(0.0, 0.0, 0.0, 7.0, 8.0, 16.0);
    public static final VoxelShape SOUTH_TOP_AAAB = Block.box(0.0, 8.0, 0.0, 16.0, 16.0, 7.0);
    public static final VoxelShape SOUTH_BOTTOM_AABB = Block.box(0.0, 0.0, 0.0, 16.0, 8.0, 7.0);
    public static final VoxelShape WEST_TOP_AAAB = Block.box(9.0, 8.0, 0.0, 16.0, 16.0, 16.0);
    public static final VoxelShape WEST_BOTTOM_AABB = Block.box(9.0, 0.0, 0.0, 16.0, 8.0, 16.0);
    public static final VoxelShape NORTH_FLOOR_AABB = Block.box(0.0, 0.0, 4.0, 16.0, 16.0, 12.0);
    public static final VoxelShape WEST_FLOOR_AABB = Block.box(4.0, 0.0, 0.0, 12.0, 16.0, 16.0);
    public static final VoxelShape SOUTH_FLOOR_AABB = Block.box(0.0, 0.0, 4.0, 16.0, 16.0, 12.0);
    public static final VoxelShape EAST_FLOOR_AABB = Block.box(4.0, 0.0, 0.0, 12.0, 16.0, 16.0);
    public static final VoxelShape NORTH_FLOOR_SHORT_AABB = Block.box(0.0, 0.0, 4.0, 16.0, 8.0, 12.0);
    public static final VoxelShape WEST_FLOOR_SHORT_AABB = Block.box(4.0, 0.0, 0.0, 12.0, 8.0, 16.0);
    public static final VoxelShape SOUTH_FLOOR_SHORT_AABB = Block.box(0.0, 0.0, 4.0, 16.0, 8.0, 12.0);
    public static final VoxelShape EAST_FLOOR_SHORT_AABB = Block.box(4.0, 0.0, 0.0, 12.0, 8.0, 16.0);
    public static final VoxelShape NORTH_CEILING_AABB = Block.box(0.0, 8.0, 4.0, 16.0, 16.0, 12.0);
    public static final VoxelShape WEST_CEILING_AABB = Block.box(4.0, 8.0, 0.0, 12.0, 16.0, 16.0);
    public static final VoxelShape SOUTH_CEILING_AABB = Block.box(0.0, 8.0, 4.0, 16.0, 16.0, 12.0);
    public static final VoxelShape EAST_CEILING_AABB = Block.box(4.0, 8.0, 0.0, 12.0, 16.0, 16.0);

    public ShelfBlock(Properties properties) {
        super(properties);
        this.defaultBlockState().setValue(HALF, SlabType.BOTTOM).setValue(FACING, Direction.NORTH).setValue(FACE, AttachFace.WALL).setValue(WATERLOGGED, false);
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return new ShelfBlockEntity(pos, state);
    }

    public @NotNull RenderShape getRenderShape(BlockState state) {
        return RenderShape.MODEL;
    }

    @Override
    public boolean canBeReplaced(BlockState state, BlockPlaceContext useContext) {
        ItemStack itemStack = useContext.getItemInHand();
        SlabType slabType = state.getValue(HALF);
        if (slabType == SlabType.DOUBLE || !itemStack.is(this.asItem()))
            return false;
        if (!useContext.replacingClickedOnBlock())
            return true;
        Direction direction = useContext.getClickedFace();
        if (slabType == SlabType.BOTTOM)
            return direction == Direction.UP || direction.getAxis().isHorizontal();
        return direction == Direction.DOWN || direction.getAxis().isHorizontal();
    }

    @Override
    public @NotNull InteractionResult use(BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hit) {
        BlockEntity blockentity = level.getBlockEntity(pos);
        if (!(blockentity instanceof ShelfBlockEntity shelfBE))
            return InteractionResult.PASS;
        Direction facing = state.getValue(FACING);
        int slot = BlockPart.get2D(pos, hit.getLocation(), Direction.UP, facing.getClockWise(), 2, 2);
        ItemStack handStack = player.getItemInHand(hand);
        ItemStack shelfStack = shelfBE.getItems().get(slot);
        if (handStack.is(this.asItem()))
            return InteractionResult.PASS;
        if (handStack.isEmpty()) {
            if (!shelfStack.isEmpty()) {
                player.setItemSlot(EquipmentSlot.MAINHAND, shelfStack.copy());
                shelfBE.getItems().set(slot, ItemStack.EMPTY);
                shelfBE.markUpdated();
                level.playSound(null, pos, SoundEvents.ITEM_PICKUP, SoundSource.BLOCKS, 1.0F, 1.0F);
                return InteractionResult.SUCCESS;
            } else {
                return InteractionResult.PASS;
            }
        }
        if (shelfStack.isEmpty()) {
            shelfBE.getItems().set(slot, handStack.split(handStack.getCount()));
            shelfBE.markUpdated();
            level.playSound(null, pos, SoundEvents.ITEM_FRAME_ADD_ITEM, SoundSource.BLOCKS, 1.0F, 1.0F);
            return InteractionResult.SUCCESS;
        }
        if (ItemStack.isSameItem(handStack, shelfStack)) {
            int room = shelfStack.getMaxStackSize() - shelfStack.getCount();
            if (room <= 0)
                return InteractionResult.PASS;
            int transfer = Math.min(handStack.getCount(), room);
            handStack.shrink(transfer);
            shelfStack.grow(transfer);
            shelfBE.markUpdated();
            level.playSound(null, pos, SoundEvents.ITEM_FRAME_ADD_ITEM, SoundSource.BLOCKS, 1.0F, 1.0F);
            return InteractionResult.SUCCESS;
        } else {
            ItemStack temp = shelfStack.copy();
            shelfBE.getItems().set(slot, handStack.copy());
            player.setItemSlot(EquipmentSlot.MAINHAND, temp);
            shelfBE.markUpdated();
            level.playSound(null, pos, SoundEvents.ITEM_PICKUP, SoundSource.BLOCKS, 1.0F, 1.0F);
            return InteractionResult.SUCCESS;
        }
    }

    @Override
    public void attack(BlockState state, Level level, BlockPos pos, Player player) {
        BlockEntity blockentity = level.getBlockEntity(pos);
        if (!(blockentity instanceof ShelfBlockEntity shelfBE))
            return;
        Vec3 eyePos = player.getEyePosition(1.0F);
        Vec3 lookVec = player.getLookAngle();
        Vec3 end = eyePos.add(lookVec.scale(5.0));
        ClipContext context = new ClipContext(eyePos, end, ClipContext.Block.OUTLINE, ClipContext.Fluid.NONE, player);
        BlockHitResult hitResult = level.clip(context);
        if (!pos.equals(hitResult.getBlockPos()))
            return;
        Direction facing = state.getValue(FACING);
        int slot = BlockPart.get2D(pos, hitResult.getLocation(), Direction.UP, facing.getClockWise(), 2, 2);
        boolean removeAll = player.isShiftKeyDown();
        if (shelfBE.removeItem(slot, player, level, removeAll))
            level.playSound(null, pos, SoundEvents.ITEM_PICKUP, SoundSource.BLOCKS, 1.0F, 1.0F);
    }

    @Override
    public void onRemove(BlockState state, Level level, BlockPos pos, BlockState newState, boolean isMoving) {
        if (state.is(newState.getBlock()))
            return;
        BlockEntity blockentity = level.getBlockEntity(pos);
        if (blockentity instanceof ShelfBlockEntity shelfBE)
            Containers.dropContents(level, pos, shelfBE.getItems());
        super.onRemove(state, level, pos, newState, isMoving);
    }

    @Override
    public @NotNull VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        Direction direction = state.getValue(FACING);
        VoxelShape shape = Shapes.empty();
        if (state.getValue(FACE) == AttachFace.FLOOR) {
            if (state.getValue(HALF) == SlabType.BOTTOM) {
                return switch (direction) {
                    case EAST -> EAST_FLOOR_SHORT_AABB;
                    case WEST -> WEST_FLOOR_SHORT_AABB;
                    case NORTH -> NORTH_FLOOR_SHORT_AABB;
                    default -> SOUTH_FLOOR_SHORT_AABB;
                };
            }
            return switch (direction) {
                case EAST -> EAST_FLOOR_AABB;
                case WEST -> WEST_FLOOR_AABB;
                case NORTH -> NORTH_FLOOR_AABB;
                default -> SOUTH_FLOOR_AABB;
            };
        }
        if (state.getValue(FACE) == AttachFace.CEILING) {
            if (state.getValue(HALF) == SlabType.TOP) {
                return switch (direction) {
                    case EAST -> EAST_CEILING_AABB;
                    case WEST -> WEST_CEILING_AABB;
                    case NORTH -> NORTH_CEILING_AABB;
                    default -> SOUTH_CEILING_AABB;
                };
            }
            return switch (direction) {
                case EAST -> EAST_FLOOR_AABB;
                case WEST -> WEST_FLOOR_AABB;
                case NORTH -> NORTH_FLOOR_AABB;
                default -> SOUTH_FLOOR_AABB;
            };
        }
        if (state.getValue(HALF) == SlabType.TOP) {
            if (direction == Direction.NORTH)
                shape = NORTH_TOP_AAAB;
            if (direction == Direction.EAST)
                shape = EAST_TOP_AAAB;
            if (direction == Direction.SOUTH)
                shape = SOUTH_TOP_AAAB;
            if (direction == Direction.WEST)
                shape = WEST_TOP_AAAB;
        } else if (state.getValue(HALF) == SlabType.BOTTOM) {
            if (direction == Direction.NORTH)
                shape = NORTH_BOTTOM_AABB;
            if (direction == Direction.EAST)
                shape = EAST_BOTTOM_AABB;
            if (direction == Direction.SOUTH)
                shape = SOUTH_BOTTOM_AABB;
            if (direction == Direction.WEST)
                shape = WEST_BOTTOM_AABB;
        } else {
            if (direction == Direction.NORTH)
                shape = Shapes.or(NORTH_TOP_AAAB, NORTH_BOTTOM_AABB);
            if (direction == Direction.EAST)
                shape = Shapes.or(EAST_TOP_AAAB, EAST_BOTTOM_AABB);
            if (direction == Direction.SOUTH)
                shape = Shapes.or(SOUTH_TOP_AAAB, SOUTH_BOTTOM_AABB);
            if (direction == Direction.WEST)
                shape = Shapes.or(WEST_TOP_AAAB, WEST_BOTTOM_AABB);
        }
        return shape;
    }

    @Override
    public @NotNull FluidState getFluidState(BlockState state) {
        return state.getValue(WATERLOGGED) ? Fluids.WATER.getSource(false) : super.getFluidState(state);
    }

    @Override
    public @NotNull BlockState rotate(BlockState state, Rotation rotation) {
        return state.setValue(FACING, rotation.rotate(state.getValue(FACING)));
    }

    @Override
    public @NotNull BlockState mirror(BlockState state, Mirror mirror) {
        return state.rotate(mirror.getRotation(state.getValue(FACING)));
    }

    @Nullable
    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        BlockPos clickedPos = context.getClickedPos();
        BlockState stateExisting = context.getLevel().getBlockState(clickedPos);
        if (stateExisting.is(this))
            return stateExisting.setValue(HALF, SlabType.DOUBLE);
        BlockState blockState = this.defaultBlockState();
        boolean waterlogged = context.getLevel().getFluidState(clickedPos).getType() == Fluids.WATER;
        BlockState state = blockState.setValue(HALF, SlabType.BOTTOM).setValue(FACING, context.getHorizontalDirection().getOpposite()).setValue(WATERLOGGED, waterlogged);
        Direction direction = context.getClickedFace();
        if (direction == Direction.DOWN) {
            state = state.setValue(FACE, AttachFace.CEILING);
        } else if (direction == Direction.UP) {
            state = state.setValue(FACE, AttachFace.FLOOR).setValue(HALF, SlabType.BOTTOM);
        } else {
            state = state.setValue(FACE, AttachFace.WALL);
        }
        return direction != Direction.DOWN && (direction == Direction.UP || !(context.getClickLocation().y - (double) clickedPos.getY() > 0.5)) ? state : state.setValue(HALF, SlabType.TOP);
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(HALF, FACING, WATERLOGGED, FACE);
    }
}
