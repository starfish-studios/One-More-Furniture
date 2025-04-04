package com.crispytwig.omf.block;

import com.crispytwig.omf.OMFConfig;
import com.crispytwig.omf.block.entity.FlowerBasketBlockEntity;
import com.crispytwig.omf.util.block.BlockPart;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.tags.TagKey;
import net.minecraft.world.Containers;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.AttachFace;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraft.world.level.pathfinder.PathComputationType;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class FlowerBasketBlock extends BaseEntityBlock {
    public static final DirectionProperty FACING = BlockStateProperties.HORIZONTAL_FACING;
    public static final EnumProperty<AttachFace> FACE = BlockStateProperties.ATTACH_FACE;

    protected static final VoxelShape SHAPE = Block.box(5.0, 0.0, 5.0, 11.0, 6.0, 11.0);

    protected static final VoxelShape FLOOR_AABB = Block.box(3.0, 0.0, 3.0, 13.0, 6.0, 13.0);
    protected static final VoxelShape CEILING_AABB = Block.box(3.0, 2.0, 3.0, 13.0, 8.0, 13.0);
    protected static final VoxelShape WALL_NORTH_AABB = Block.box(0.0, 4.0, 10.0, 16.0, 10.0, 16.0);
    protected static final VoxelShape WALL_SOUTH_AABB = Block.box(0.0, 4.0, 0.0, 16.0, 10.0, 6.0);
    protected static final VoxelShape WALL_EAST_AABB = Block.box(0.0, 4.0, 0.0, 6.0, 10.0, 16.0);
    protected static final VoxelShape WALL_WEST_AABB = Block.box(10.0, 4.0, 0.0, 16.0, 10.0, 16.0);

    public FlowerBasketBlock(Properties properties) {
        super(properties);
        this.registerDefaultState(this.stateDefinition.any()
                .setValue(FACING, Direction.NORTH)
                .setValue(FACE, AttachFace.FLOOR));
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(@NotNull BlockPos pos, @NotNull BlockState state) {
        return new FlowerBasketBlockEntity(pos, state);
    }

    @Override
    @SuppressWarnings("deprecation")
    public @NotNull VoxelShape getShape(@NotNull BlockState state, @NotNull BlockGetter getter, @NotNull BlockPos pos, @NotNull CollisionContext context) {
        AttachFace face = state.getValue(FACE);
        if (face == AttachFace.FLOOR) {
            return FLOOR_AABB;
        } else if (face == AttachFace.CEILING) {
            return CEILING_AABB;
        } else {
            return switch (state.getValue(FACING)) {
                case DOWN, UP -> SHAPE;
                case NORTH -> WALL_NORTH_AABB;
                case SOUTH -> WALL_SOUTH_AABB;
                case EAST -> WALL_EAST_AABB;
                case WEST -> WALL_WEST_AABB;
            };
        }
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(FACING, FACE);
    }

    @Override
    public @NotNull RenderShape getRenderShape(@NotNull BlockState state) {
        return RenderShape.MODEL;
    }

    @Override
    @SuppressWarnings("deprecation")
    public void onRemove(BlockState state, Level level, BlockPos pos, BlockState newState, boolean isMoving) {
        if (state.is(newState.getBlock())) return;

        BlockEntity blockEntity = level.getBlockEntity(pos);
        if (blockEntity instanceof FlowerBasketBlockEntity flowerBoxBE)
            Containers.dropContents(level, pos, flowerBoxBE.getItems());
        super.onRemove(state, level, pos, newState, isMoving);
    }

    @Nullable
    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        Direction clickedFace = context.getClickedFace();
        if (clickedFace.getAxis() == Direction.Axis.Y) {
            return this.defaultBlockState()
                    .setValue(FACE, clickedFace == Direction.UP ? AttachFace.FLOOR : AttachFace.CEILING)
                    .setValue(FACING, context.getHorizontalDirection());
        } else {
            return this.defaultBlockState()
                    .setValue(FACE, AttachFace.WALL)
                    .setValue(FACING, clickedFace);
        }
    }

    @Override
    @SuppressWarnings("deprecation")
    public @NotNull BlockState rotate(BlockState state, Rotation rotation) {
        return state.setValue(FACING, rotation.rotate(state.getValue(FACING)));
    }

    @Override
    @SuppressWarnings("deprecation")
    public boolean canSurvive(BlockState state, LevelReader world, BlockPos pos) {
        return true;
    }

    @Override
    @SuppressWarnings("deprecation")
    public boolean isPathfindable(BlockState state, BlockGetter getter, BlockPos pos, PathComputationType type) {
        return false;
    }

    @Override
    @SuppressWarnings("deprecation")
    public @NotNull InteractionResult use(@NotNull BlockState state, Level level, @NotNull BlockPos pos, @NotNull Player player, @NotNull InteractionHand hand, @NotNull BlockHitResult hit) {
        BlockEntity blockEntity = level.getBlockEntity(pos);
        if (!(blockEntity instanceof FlowerBasketBlockEntity flowerBoxBE)) return InteractionResult.PASS;

        ItemStack heldStack = player.getItemInHand(hand);
        if (heldStack.isEmpty()) {
            return InteractionResult.PASS;
        }

        if (heldStack.getItem() != Items.SHEARS) {
            boolean validFlower = false;
            for (String entry : OMFConfig.flowerBoxItems) {
                if (entry.startsWith("#")) {
                    String tagName = entry.substring(1);
                    TagKey<Item> tag = TagKey.create(Registries.ITEM, new ResourceLocation(tagName));
                    if (heldStack.is(tag)) {
                        validFlower = true;
                        break;
                    }
                } else {
                    ResourceLocation itemId = Registries.ITEM.toString().equals(entry) ? null : new ResourceLocation(entry);
                    if (itemId != null && itemId.toString().equals(entry)) {
                        validFlower = true;
                        break;
                    }
                }
            }
            if (!validFlower) {
                return InteractionResult.PASS;
            }
        }

        AttachFace attachFace = state.getValue(FACE);

        if (heldStack.getItem() == Items.SHEARS) {
            int slot = (attachFace == AttachFace.WALL
                    ? BlockPart.get1D(pos, hit.getLocation(), state.getValue(FACING).getClockWise(), 2)
                    : 0);
            ItemStack flowerStack = flowerBoxBE.getItemFromSlot(slot).getDefaultInstance();
            if (!flowerStack.isEmpty()) {
                flowerBoxBE.removeFlower(slot);
                level.addFreshEntity(new ItemEntity(level, pos.getX() + 0.5, pos.getY() + 0.5, pos.getZ() + 0.5, flowerStack.copy()));
                level.playSound(null, pos, SoundEvents.SHEEP_SHEAR, SoundSource.BLOCKS, 1.0f, 1.0f);
                heldStack.hurtAndBreak(1, player, (p) -> p.broadcastBreakEvent(hand));
                return InteractionResult.SUCCESS;
            } else {
                return InteractionResult.PASS;
            }
        }

        int slot = (attachFace == AttachFace.WALL
                ? BlockPart.get1D(pos, hit.getLocation(), state.getValue(FACING).getClockWise(), 2)
                : 0);
        ItemStack currentFlower = flowerBoxBE.getItemFromSlot(slot).getDefaultInstance();

        if (currentFlower.isEmpty()) {
            if (flowerBoxBE.placeFlower(player.getAbilities().instabuild ? heldStack.copy() : heldStack, slot)) {
                if (!player.getAbilities().instabuild) {
                    heldStack.shrink(1);
                }
                return InteractionResult.SUCCESS;
            }
        } else {
            if (heldStack.getItem() != currentFlower.getItem()) {
                flowerBoxBE.removeFlower(slot);
                level.addFreshEntity(new ItemEntity(level, pos.getX() + 0.5, pos.getY() + 0.5, pos.getZ() + 0.5, currentFlower.copy()));
                if (flowerBoxBE.placeFlower(player.getAbilities().instabuild ? heldStack.copy() : heldStack, slot)) {
                    if (!player.getAbilities().instabuild) {
                        heldStack.shrink(1);
                    }
                    return InteractionResult.SUCCESS;
                }
            }
        }

        return InteractionResult.CONSUME;
    }

}

