package com.crispytwig.nookcranny.client.gui.screens;

import com.crispytwig.nookcranny.inventory.MailboxMenu;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;

import static com.crispytwig.nookcranny.NookAndCranny.MOD_ID;

@Environment(EnvType.CLIENT)
public class MailboxScreen extends AbstractContainerScreen<MailboxMenu> {
    private static final ResourceLocation CONTAINER_LOCATION = new ResourceLocation(MOD_ID, "textures/gui/generic_1x5.png");

    public MailboxScreen(MailboxMenu mailboxMenu, Inventory inventory, Component component) {
        super(mailboxMenu, inventory, component);
    }

    protected void init() {
        super.init();
        this.titleLabelX = (this.imageWidth - this.font.width(this.title)) / 2;
        // Move the label down by 16 pixels.
        this.titleLabelY += 16;
    }

    public void render(GuiGraphics guiGraphics, int i, int j, float f) {
        this.renderBackground(guiGraphics);
        super.render(guiGraphics, i, j, f);
        this.renderTooltip(guiGraphics, i, j);
    }

    protected void renderBg(GuiGraphics guiGraphics, float f, int i, int j) {
        int k = (this.width - this.imageWidth) / 2;
        int l = (this.height - this.imageHeight) / 2;
        guiGraphics.blit(CONTAINER_LOCATION, k, l, 0, 0, this.imageWidth, this.imageHeight);
    }
}