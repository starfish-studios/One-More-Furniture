package com.crispytwig.nookcranny.client.renderer.blockentity;

import com.crispytwig.nookcranny.blocks.entities.WindChimeBlockEntity;
import com.crispytwig.nookcranny.client.ChimeModel;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;

public class ChimeBlockEntityRenderer implements BlockEntityRenderer<WindChimeBlockEntity> {

    ChimeModel model;

    public ChimeBlockEntityRenderer(BlockEntityRendererProvider.Context context) {
        model = new ChimeModel(context.bakeLayer(ChimeModel.LAYER_LOCATION));
    }

    @Override
    public void render(WindChimeBlockEntity blockEntity, float partialTick, PoseStack poseStack, MultiBufferSource buffer, int packedLight, int packedOverlay) {
        poseStack.pushPose();


        poseStack.popPose();
    }
}
