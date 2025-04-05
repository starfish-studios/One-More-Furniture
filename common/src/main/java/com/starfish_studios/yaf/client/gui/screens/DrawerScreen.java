package com.starfish_studios.yaf.client.gui.screens;

import com.starfish_studios.yaf.YetAnotherFurniture;
import com.starfish_studios.yaf.inventory.DrawerMenu;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;


@Environment(EnvType.CLIENT)
public class DrawerScreen extends AbstractContainerScreen<DrawerMenu> {
    private static final ResourceLocation CONTAINER_LOCATION = new ResourceLocation(YetAnotherFurniture.MOD_ID, "textures/gui/generic_1x5.png");

    public DrawerScreen(DrawerMenu drawerMenu, Inventory inventory, Component component) {
        super(drawerMenu, inventory, component);
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