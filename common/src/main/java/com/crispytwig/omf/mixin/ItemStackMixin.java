package com.crispytwig.omf.mixin;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(ItemStack.class)
public class ItemStackMixin {

    @Inject(method = "isSameItemSameTags", at = @At("HEAD"))
    private static void acceptMailboxTagCombine(ItemStack stack, ItemStack other, CallbackInfoReturnable<Boolean> cir) {

        CompoundTag tag1 = stack.getTag();
        CompoundTag tag2 = other.getTag();

        if (tag1 != null) {
            tag1.remove("mailboxTooltip");
            tag1.remove("mailboxTooltipTimer");
        }

        if (tag2 != null) {
            tag2.remove("mailboxTooltip");
            tag2.remove("mailboxTooltipTimer");
        }
    }
}