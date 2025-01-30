//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.crispytwig.nookcranny.inventory;

import com.crispytwig.nookcranny.NookAndCranny;
import com.crispytwig.nookcranny.blocks.entities.MailboxBlockEntity;
import com.crispytwig.nookcranny.registry.NCMenus;
import io.netty.buffer.Unpooled;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.minecraft.client.gui.screens.inventory.AnvilScreen;
import net.minecraft.client.gui.screens.inventory.HopperScreen;
import net.minecraft.core.BlockPos;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.Container;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.HopperMenu;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;

public class MailboxMenu extends AbstractContainerMenu {
    private static final int SLOT_COUNT = 5;
    private final Container mailbox;
    private final BlockPos pos;
    public MailboxBlockEntity mailboxBlockEntity;
    public static ResourceLocation packetChannel = new ResourceLocation(NookAndCranny.MOD_ID, "mailbox_sync");


    public MailboxMenu(int i, Inventory inventory, FriendlyByteBuf buf) {
        this(i, inventory, new SimpleContainer(5), buf);
    }

    public Container getContainer() {
        return this.mailbox;
    }

    public MailboxMenu(int i, Inventory inventory, Container container, FriendlyByteBuf buf) {
        super(NCMenus.GENERIC_1X5, i);

        pos = buf.readBlockPos();
        var be = inventory.player.level().getBlockEntity(pos);
        if (be instanceof MailboxBlockEntity entity) {
            this.mailboxBlockEntity = entity;
        }
        this.mailbox = mailboxBlockEntity;
        checkContainerSize(mailbox, 5);

        mailbox.startOpen(inventory.player);
        int j;
        int k;
        for(j = 0; j < 1; ++j) {
            for(k = 0; k < SLOT_COUNT; ++k) {
                this.addSlot(new Slot(mailbox, k + j * SLOT_COUNT, 26 + k * 18, 35 + j * 18));
            }
        }

        for(j = 0; j < 3; ++j) {
            for(k = 0; k < 9; ++k) {
                this.addSlot(new Slot(inventory, k + j * 9 + 9, 8 + k * 18, 84 + j * 18));
            }
        }

        for(j = 0; j < 9; ++j) {
            this.addSlot(new Slot(inventory, j, 8 + j * 18, 142));
        }

    }

    public boolean stillValid(Player player) {
        return this.mailbox.stillValid(player);
    }

    public ItemStack quickMoveStack(Player player, int i) {
        ItemStack itemStack = ItemStack.EMPTY;
        Slot slot = this.slots.get(i);
        if (slot.hasItem()) {
            ItemStack itemStack2 = slot.getItem();
            itemStack = itemStack2.copy();
            if (i < 5) {
                if (!this.moveItemStackTo(itemStack2, SLOT_COUNT, 41, true)) {
                    return ItemStack.EMPTY;
                }
            } else if (!this.moveItemStackTo(itemStack2, 0, 5, false)) {
                return ItemStack.EMPTY;
            }

            if (itemStack2.isEmpty()) {
                slot.setByPlayer(ItemStack.EMPTY);
            } else {
                slot.setChanged();
            }

            if (itemStack2.getCount() == itemStack.getCount()) {
                return ItemStack.EMPTY;
            }

            slot.onTake(player, itemStack2);
        }

        return itemStack;
    }

    public void removed(Player player) {
        super.removed(player);
        this.mailbox.stopOpen(player);
    }

    public void updateTargetString(String s) {
        FriendlyByteBuf buf = new FriendlyByteBuf(Unpooled.buffer());
        buf.writeUtf(s);
        buf.writeBlockPos(pos);
        ClientPlayNetworking.send(packetChannel, buf);
        mailboxBlockEntity.targetString = s;
        mailboxBlockEntity.setChanged();
    }
}
