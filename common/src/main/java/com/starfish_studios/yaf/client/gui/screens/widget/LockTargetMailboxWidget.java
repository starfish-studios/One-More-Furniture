package com.starfish_studios.yaf.client.gui.screens.widget;

import com.starfish_studios.yaf.YetAnotherFurniture;
import com.starfish_studios.yaf.inventory.MailboxMenu;
import dev.architectury.networking.NetworkManager;
import io.netty.buffer.Unpooled;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.AbstractWidget;
import net.minecraft.client.gui.narration.NarrationElementOutput;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;


public class LockTargetMailboxWidget extends AbstractWidget {

    public boolean locked = false;
    public MailboxMenu mailboxMenu;

    public LockTargetMailboxWidget(MailboxMenu menu, int x, int y) {
        super(x, y, 12, 12, Component.empty());
        this.mailboxMenu = menu;
    }

    @Override
    protected void renderWidget(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTick) {
        guiGraphics.blit(
                new ResourceLocation(YetAnotherFurniture.MOD_ID, "textures/gui/"+ (locked ? "lock" : "unlock") + ".png"),
                this.getX(),
                this.getY(),
                0,
                0,
                12,
                12,
                12,
                12

        );
    }

    @Override
    public void onClick(double mouseX, double mouseY) {
        super.onClick(mouseX, mouseY);
        locked = !locked;
        FriendlyByteBuf buf = new FriendlyByteBuf(Unpooled.buffer());
        buf.writeBlockPos(this.mailboxMenu.pos);
        buf.writeBoolean(locked);
        NetworkManager.sendToServer(YetAnotherFurniture.lockTargetId, buf);
        this.mailboxMenu.mailboxBlockEntity.lockTarget = locked;
    }

    @Override
    protected void updateWidgetNarration(NarrationElementOutput narrationElementOutput) {

    }
}
