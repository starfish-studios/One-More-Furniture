package com.crispytwig.nookcranny.events;

import com.crispytwig.nookcranny.blocks.ShelfBlock;
import com.crispytwig.nookcranny.blocks.entities.ShelfBlockEntity;
import com.crispytwig.nookcranny.registry.NCItems;
import net.fabricmc.fabric.api.event.player.UseBlockCallback;
import net.minecraft.core.BlockPos;
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

public class ShelfInteractions implements UseBlockCallback {
    @Override
    public InteractionResult interact(Player player, Level level, InteractionHand interactionHand, BlockHitResult blockHitResult) {

        BlockPos pos = blockHitResult.getBlockPos();
        BlockState blockState = level.getBlockState(pos);
        ItemStack itemStack = player.getItemInHand(interactionHand);

        if (!level.isClientSide && level.getBlockEntity(pos) instanceof ShelfBlockEntity shelfBlockEntity) {

            if (itemStack.is(NCItems.COPPER_SAW) && player.isShiftKeyDown() && blockState.getValue(ShelfBlock.HALF) == SlabType.DOUBLE) {

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
