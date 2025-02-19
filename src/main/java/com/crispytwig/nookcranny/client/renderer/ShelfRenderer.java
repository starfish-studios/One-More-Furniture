package com.crispytwig.nookcranny.client.renderer;

import com.crispytwig.nookcranny.blocks.ShelfBlock;
import com.crispytwig.nookcranny.blocks.entities.ShelfBlockEntity;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.Camera;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.core.Direction;
import net.minecraft.core.NonNullList;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;

@Environment(value= EnvType.CLIENT)
public class ShelfRenderer implements BlockEntityRenderer<ShelfBlockEntity> {

    public ShelfRenderer(BlockEntityRendererProvider.Context context) {

    }

    @Override
    public void render(ShelfBlockEntity blockEntity, float partialTick, PoseStack poseStack, MultiBufferSource bufferSource, int packedLight, int packedOverlay) {
        Direction direction = blockEntity.getBlockState().getValue(ShelfBlock.FACING);
        float rotation = -direction.toYRot() + 90f;
        NonNullList<ItemStack> items = blockEntity.getItems();
        poseStack.pushPose();
        poseStack.translate(0.5, 0.8, 0.5);
        poseStack.mulPose(Axis.YP.rotationDegrees(rotation));
        for(int j = 0; j < items.size(); j++) {
            ItemStack stack = items.get(j);
            if (stack.isEmpty()) continue;

            int renderCount = getAmount(stack.getCount());
            for (int i = 0; i < renderCount; ++i) {
                float fx = (-0.10375f * (float)(i - 1) * 0.5f) % 0.09f;
                float fy = (-0.04375f * (float)(i - 1) * 0.5f) % 0.09f;
                float fz = (-0.05375f * (float)(i - 1) * 0.5f) % 0.09f;

                poseStack.pushPose();

                poseStack.translate(0.0, (Math.sin((blockEntity.getLevel().getGameTime() + partialTick) / 8.0) / 32.0), 0.0);



                poseStack.translate(0.225 + 0.0 * (j % 2), 0.5 * -(j % 2), -0.225 + 0.4 * (j / 2));
                poseStack.translate(fx, fy, fz);
                poseStack.scale(0.375F, 0.375F, 0.375F);
                poseStack.mulPose(Axis.YP.rotationDegrees(90f));

                Minecraft.getInstance().getItemRenderer().renderStatic(stack, ItemDisplayContext.FIXED, packedLight, packedOverlay, poseStack, bufferSource, blockEntity.getLevel(), 0);

                boolean shouldRender = Minecraft.getInstance().player != null && Minecraft.getInstance().player.isShiftKeyDown();
                if (shouldRender && i == 0) {
                    int count = stack.getCount();
                    if (count > 1) {

                        poseStack.pushPose();
                        poseStack.translate(-0.3, 0.0, -0.75);
                        poseStack.mulPose(Axis.YP.rotationDegrees(- rotation - 90));
                        renderFloatingText(poseStack, bufferSource, String.valueOf(count), 0.03f, packedLight);
                        poseStack.popPose();
                    }
                }

                poseStack.popPose();
            }

        }
        poseStack.popPose();
    }

    public static void renderFloatingText(PoseStack poseStack, MultiBufferSource buffer, String text, float scale, int packedLight) {
        Minecraft minecraft = Minecraft.getInstance();
        Camera camera = minecraft.gameRenderer.getMainCamera();
        if (camera.isInitialized()) {
            Font font = minecraft.font;

            float opacity = Minecraft.getInstance().options.getBackgroundOpacity(0.5F);
            int alpha = (int)(opacity * 255.0F) << 24;

            poseStack.pushPose();

            poseStack.mulPose(Axis.YP.rotationDegrees(-camera.getYRot()));
            poseStack.mulPose(Axis.XP.rotationDegrees(camera.getXRot()));

            poseStack.scale(-scale, -scale, scale);

            float h = (float) (-font.width(text)) / 2.0F;

            //Font.DisplayMode.SEE_THROUGH is what's make it render through blocks, setting it as Normal scissors the render through the item
            font.drawInBatch(text, h, 0.0F, 553648127, false, poseStack.last().pose(), buffer, Font.DisplayMode.SEE_THROUGH, alpha, packedLight);
            font.drawInBatch(text, h, 0.0F, -1, false, poseStack.last().pose(), buffer, Font.DisplayMode.NORMAL, 0, packedLight);

            poseStack.popPose();
        }
    }

    public int getAmount(int count) {
        if (count > 48) return 5;
        if (count > 32) return 4;
        if (count > 16) return 3;
        if (count > 1) return 2;
        return 1;
    }
}