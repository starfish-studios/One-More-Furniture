package com.starfish_studios.yaf.mixin;

import com.starfish_studios.yaf.registry.YAFDataComponents;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(ItemStack.class)
public class ItemStackMixin {

    @Inject(method = "isSameItemSameComponents", at = @At("HEAD"))
    private static void acceptMailboxTagCombine(ItemStack stack, ItemStack other, CallbackInfoReturnable<Boolean> cir) {

        if (stack.has(YAFDataComponents.MAILBOX_STRING.get())) {
            stack.remove(YAFDataComponents.MAILBOX_STRING.get());
        }

        if (stack.has(YAFDataComponents.MAILBOX_TIMER.get())) {
            stack.remove(YAFDataComponents.MAILBOX_TIMER.get());
        }

        if (other.has(YAFDataComponents.MAILBOX_STRING.get())) {
            other.remove(YAFDataComponents.MAILBOX_STRING.get());
        }

        if (other.has(YAFDataComponents.MAILBOX_TIMER.get())) {
            other.remove(YAFDataComponents.MAILBOX_TIMER.get());
        }

    }
}