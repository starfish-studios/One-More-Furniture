package com.starfish_studios.yaf.block;

import com.mojang.serialization.MapCodec;
import com.starfish_studios.yaf.YAFConfig;
import com.starfish_studios.yaf.block.entity.FlowerBasketBlockEntity;
import com.starfish_studios.yaf.util.block.BlockPart;
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
import net.minecraft.world.ItemInteractionResult;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.BlockItem;
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

    @Override
    protected MapCodec<? extends BaseEntityBlock> codec() {
        return simpleCodec(FlowerBasketBlock::new);
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
    protected boolean isPathfindable(BlockState state, PathComputationType pathComputationType) {
        return false;
    }

    @SuppressWarnings("deprecation")
    public boolean isAllowedFlower(Item item) {
        if (item == null) {
            return false;
        }
        ResourceLocation itemId = ResourceLocation.parse(item.toString());

        for (String entry : YAFConfig.flowerBasketItems) {
            if (entry.startsWith("#")) {
                String tagName = entry.substring(1);
                ResourceLocation tagResource =  ResourceLocation.parse(tagName);
                TagKey<Item> tagKey = TagKey.create(Registries.ITEM, tagResource);

                if (item.builtInRegistryHolder().is(tagKey)) {
                    return true;
                }
            } else {
                if (itemId.toString().equals(entry)) {
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    protected ItemInteractionResult useItemOn(ItemStack stack, BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hitResult) {
        BlockEntity blockEntity = level.getBlockEntity(pos);
        if (!(blockEntity instanceof FlowerBasketBlockEntity flowerBoxBE)) return ItemInteractionResult.PASS_TO_DEFAULT_BLOCK_INTERACTION;

        ItemStack heldStack = player.getItemInHand(hand);
        if (heldStack.isEmpty()) {
            return ItemInteractionResult.PASS_TO_DEFAULT_BLOCK_INTERACTION;
        }

        if (heldStack.getItem() != Items.SHEARS) {
            boolean validFlower = false;
            for (String entry : YAFConfig.flowerBasketItems) {
                if (entry.startsWith("#")) {
                    String tagName = entry.substring(1);
                    TagKey<Item> tag = TagKey.create(Registries.ITEM, ResourceLocation.parse(tagName));
                    if (heldStack.is(tag)) {
                        validFlower = true;
                        break;
                    }
                } else {
                    ResourceLocation itemId = Registries.ITEM.toString().equals(entry) ? null : ResourceLocation.parse(entry);
                    if (itemId != null && itemId.toString().equals(entry)) {
                        validFlower = true;
                        break;
                    }
                }
            }
            if (!validFlower) {
                return ItemInteractionResult.PASS_TO_DEFAULT_BLOCK_INTERACTION;
            }
        }

        AttachFace attachFace = state.getValue(FACE);

        if (heldStack.getItem() == Items.SHEARS) {
            int slot = (attachFace == AttachFace.WALL
                    ? BlockPart.get1D(pos, hitResult.getLocation(), state.getValue(FACING).getClockWise(), 2)
                    : 0);
            ItemStack flowerStack = flowerBoxBE.getItemFromSlot(slot).getDefaultInstance();
            if (!flowerStack.isEmpty()) {
                flowerBoxBE.removeFlower(slot);
                level.addFreshEntity(new ItemEntity(level, pos.getX() + 0.5, pos.getY() + 0.5, pos.getZ() + 0.5, flowerStack.copy()));
                level.playSound(null, pos, SoundEvents.SHEEP_SHEAR, SoundSource.BLOCKS, 1.0f, 1.0f);
                heldStack.hurtAndBreak(1, player, EquipmentSlot.MAINHAND);
                return ItemInteractionResult.SUCCESS;
            } else {
                return ItemInteractionResult.PASS_TO_DEFAULT_BLOCK_INTERACTION;
            }
        }

        int slot = (attachFace == AttachFace.WALL
                ? BlockPart.get1D(pos, hitResult.getLocation(), state.getValue(FACING).getClockWise(), 2)
                : 0);
        ItemStack currentFlower = flowerBoxBE.getItemFromSlot(slot).getDefaultInstance();

        if (currentFlower.isEmpty() && heldStack.getItem() instanceof BlockItem
                && isAllowedFlower(heldStack.getItem())) {
            if (flowerBoxBE.placeFlower(player.getAbilities().instabuild ? heldStack.copy() : heldStack, slot)) {
                if (!player.getAbilities().instabuild) {
                    heldStack.shrink(1);
                }
                return ItemInteractionResult.SUCCESS;
            }
        } else {
            if (!heldStack.getItem().equals(currentFlower.getItem())
                    && heldStack.getItem() instanceof BlockItem
                    && isAllowedFlower(heldStack.getItem())) {
                flowerBoxBE.removeFlower(slot);
                level.addFreshEntity(new ItemEntity(level, pos.getX() + 0.5, pos.getY() + 0.5, pos.getZ() + 0.5, currentFlower.copy()));
                if (flowerBoxBE.placeFlower(player.getAbilities().instabuild ? heldStack.copy() : heldStack, slot)) {
                    if (!player.getAbilities().instabuild) {
                        heldStack.shrink(1);
                    }
                    return ItemInteractionResult.SUCCESS;
                }
            }
        }



        return ItemInteractionResult.CONSUME;
    }
}

