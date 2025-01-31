package com.crispytwig.nookcranny.client.gui.screens;

import com.crispytwig.nookcranny.inventory.MailboxMenu;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.EditBox;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;

import java.awt.*;

import static com.crispytwig.nookcranny.NookAndCranny.MOD_ID;

@Environment(EnvType.CLIENT)
public class MailboxScreen extends AbstractContainerScreen<MailboxMenu> {
    private static final ResourceLocation CONTAINER_LOCATION = new ResourceLocation(MOD_ID, "textures/gui/mailbox.png");

    private EditBox targetString;
    private final int boxX = 148;
    private final int boxY = 35;
    private final int boxWidth = 97;
    private final int boxHeight = 16;

    public Component statusMessage = Component.translatable("container.mailbox.send");

    public MailboxScreen(MailboxMenu mailboxMenu, Inventory inventory, Component component) {
        super(mailboxMenu, inventory, component);
        this.imageWidth = 252;
        // Move the label down by 16 pixels.
        this.titleLabelY += 16;
    }

    protected void init() {
        super.init();
        this.titleLabelX = (this.imageWidth - this.font.width(this.title)) / 2 - 56;

        int i = (this.width - this.imageWidth) / 2;
        int j = (this.height - this.imageHeight) / 2;

        int padding = 4;
        this.targetString = new EditBox(
                this.font,
                i + boxX + padding,
                j + boxY + padding,
                boxWidth - padding * 2,
                boxHeight,
                Component.translatable("container.mailbox.send")
        );
        this.targetString.setCanLoseFocus(false);
        this.targetString.setTextColor(-1);
        this.targetString.setTextColorUneditable(-1);
        this.targetString.setBordered(false);
        this.targetString.setMaxLength(50);
        this.targetString.setResponder(this::onNameChanged);
        this.targetString.setValue(menu.mailboxBlockEntity.targetString);
        this.addWidget(this.targetString);
        this.setInitialFocus(this.targetString);
        this.targetString.setEditable(true);
    }

    private void onNameChanged(String s) {
        menu.updateTargetString(s);
    }

    @Override
    public boolean keyPressed(int keyCode, int scanCode, int modifiers) {
        if (keyCode == 256) {
            this.minecraft.player.closeContainer();
        }
        if (this.targetString.keyPressed(keyCode, scanCode, modifiers) || this.targetString.canConsumeInput()) {
            return true;
        }
        return super.keyPressed(keyCode, scanCode, modifiers);
    }

    public void renderFg(GuiGraphics guiGraphics, int mouseX, int mouseY, float f) {
        int x = (this.width - this.imageWidth) / 2  + boxX;
        int y = (this.height - this.imageHeight) / 2 + boxY;
        int width = x + boxWidth;
        int height = y + boxHeight;
        guiGraphics.fill(x, y, width, height, 0, Color.BLACK.getRGB());
        this.targetString.render(guiGraphics, mouseX, mouseY, f);
    }

    @Override
    protected void renderLabels(GuiGraphics guiGraphics, int mouseX, int mouseY) {
        super.renderLabels(guiGraphics, mouseX, mouseY);
        var data = menu.mailboxBlockEntity.failedToSend;

        guiGraphics.drawString(this.font, statusMessage,
                this.titleLabelX + 32 * 3 + 21 + (data ? 6 : 0),
                this.titleLabelY,
                4210752, false
        );

        if (data) {
            guiGraphics.blit(
                    CONTAINER_LOCATION,
                    this.titleLabelX + 32 * 3 + 21,
                    this.titleLabelY,
                    175,
                    34,
                    16,
                    16

            );
        }
    }

    @Override
    public void resize(Minecraft minecraft, int i, int j) {
        String string = this.targetString.getValue();
        this.init(minecraft, i, j);
        this.targetString.setValue(string);
    }

    @Override
    public void render(GuiGraphics guiGraphics, int i, int j, float f) {
        this.renderBackground(guiGraphics);
        super.render(guiGraphics, i, j, f);
        this.renderFg(guiGraphics, i, j, f);
        this.renderTooltip(guiGraphics, i, j);
    }

    @Override
    protected void renderBg(GuiGraphics guiGraphics, float f, int i, int j) {
        int k = (this.width - this.imageWidth) / 2;
        int l = (this.height - this.imageHeight) / 2;
        guiGraphics.blit(CONTAINER_LOCATION, k, l, 0, 0, this.imageWidth, this.imageHeight);
    }

    @Override
    protected void containerTick() {
        super.containerTick();
        targetString.tick();
    }
}