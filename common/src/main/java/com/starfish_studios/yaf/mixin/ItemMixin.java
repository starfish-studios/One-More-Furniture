package com.starfish_studios.yaf.mixin;

import net.minecraft.ChatFormatting;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.List;

@Mixin(Item.class)
public abstract class ItemMixin {

    @Shadow public abstract String toString();

    @Inject(method = "appendHoverText", at = @At("TAIL"))
    private void addMailboxTooltip(ItemStack stack, Level world, List<Component> tooltip, TooltipFlag flag, CallbackInfo ci) {
        CompoundTag tag = stack.getTag();
        if (tag != null && tag.contains("mailboxTooltip")) {
            String fromText = tag.getString("mailboxTooltip");
            tooltip.add(Component.literal(fromText)
                    .withStyle(ChatFormatting.ITALIC, ChatFormatting.GRAY));
            if (tag.contains("mailboxTooltipTimer")) {
                int timer = tag.getInt("mailboxTooltipTimer");
                String timerDisplay = timer < 0 ? "Not started" : String.valueOf(timer);
                tooltip.add(Component.literal("Time left: " + timerDisplay + " ticks")
                        .withStyle(ChatFormatting.DARK_GRAY));
            }
        }
    }

    @Inject(method = "inventoryTick", at = @At("HEAD"))
    private void mailboxTooltipInventoryTick(ItemStack stack, Level world, Entity entity, int slot, boolean selected, CallbackInfo ci) {
        CompoundTag tag = stack.getTag();
        if (tag == null || !tag.contains("mailboxTooltipTimer")) {
            return;
        }
        if (entity instanceof Player) {
            int timer = tag.getInt("mailboxTooltipTimer");
            if (timer < 0) {
                tag.putInt("mailboxTooltipTimer", 200);
            } else if (timer > 0) {
                timer--;
                tag.putInt("mailboxTooltipTimer", timer);
                if (timer == 0) {
                    removeMailboxTags(stack);
                }
            }
        } else {
            removeMailboxTags(stack);
        }
    }

    @Unique
    private void removeMailboxTags(ItemStack stack) {
        CompoundTag tag = stack.getTag();
        if (tag != null) {
            tag.remove("mailboxTooltip");
            tag.remove("mailboxTooltipTimer");
            if (tag.isEmpty()) {
                stack.setTag(null);
            }
        }
    }
}
