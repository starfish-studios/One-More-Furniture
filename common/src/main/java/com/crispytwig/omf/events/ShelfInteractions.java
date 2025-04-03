package com.crispytwig.omf.events;

import com.crispytwig.omf.block.ShelfBlock;
import com.crispytwig.omf.block.entity.ShelfBlockEntity;
import com.crispytwig.omf.registry.OMFItems;
import dev.architectury.event.EventResult;
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
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.SlabType;
import net.minecraft.world.phys.BlockHitResult;

public class ShelfInteractions   {

    public static InteractionResult interact(Player player, InteractionHand interactionHand, BlockHitResult blockHitResult) {

        var level = player.level();
        var pos = blockHitResult.getBlockPos();
        BlockState blockState = level.getBlockState(pos);
        ItemStack itemStack = player.getItemInHand(interactionHand);

        if (!level.isClientSide && level.getBlockEntity(pos) instanceof ShelfBlockEntity shelfBlockEntity) {

            if (itemStack.is(OMFItems.COPPER_SAW.get()) && player.isShiftKeyDown() && blockState.getValue(ShelfBlock.HALF) == SlabType.DOUBLE) {

                var loc = blockHitResult.getLocation().y - pos.getY();

                SlabType newSlabType = (loc < 0.5) ? SlabType.TOP : SlabType.BOTTOM;

                BlockState newState = blockState.setValue(ShelfBlock.HALF, newSlabType);
                level.setBlockAndUpdate(pos, newState);

                if (newSlabType == SlabType.TOP) {
                    shelfBlockEntity.dropItem(1, level);
                    shelfBlockEntity.dropItem(3, level);
                } else {
                    shelfBlockEntity.dropItem(0, level);
                    shelfBlockEntity.dropItem(2, level);
                }

                Containers.dropItemStack(level, pos.getX(), pos.getY(), pos.getZ(), new ItemStack(blockState.getBlock().asItem()));

                itemStack.hurtAndBreak(1, player, livingEntity -> livingEntity.broadcastBreakEvent(EquipmentSlot.MAINHAND));

                level.playSound(null, pos, SoundEvents.WOOD_PLACE, SoundSource.BLOCKS, 1.0F, 1.0F);
                return InteractionResult.SUCCESS;
            }
        }

        return InteractionResult.PASS;
    }
}
