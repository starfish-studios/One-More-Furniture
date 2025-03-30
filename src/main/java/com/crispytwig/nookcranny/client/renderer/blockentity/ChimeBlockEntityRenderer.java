package com.crispytwig.nookcranny.client.renderer.blockentity;

import com.crispytwig.nookcranny.NookAndCranny;
import com.crispytwig.nookcranny.blocks.FanBlock;
import com.crispytwig.nookcranny.blocks.WindChimeBlock;
import com.crispytwig.nookcranny.blocks.entities.FanBlockEntity;
import com.crispytwig.nookcranny.blocks.entities.WindChimeBlockEntity;
import com.crispytwig.nookcranny.client.ChimeModel;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Axis;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.resources.ResourceLocation;

public class ChimeBlockEntityRenderer implements BlockEntityRenderer<WindChimeBlockEntity> {

    ChimeModel model;

    public ChimeBlockEntityRenderer(BlockEntityRendererProvider.Context context) {
        model = new ChimeModel(context.bakeLayer(ChimeModel.LAYER_LOCATION));
    }

    public ResourceLocation getTextureLocation(WindChimeBlockEntity entity) {
        var material = ((WindChimeBlock) entity.getBlockState().getBlock()).material;
        return new ResourceLocation(NookAndCranny.MOD_ID, "textures/block/wind_chime/" + material + "_windchime.png");
    }

    @Override
    public void render(WindChimeBlockEntity blockEntity, float partialTick, PoseStack poseStack, MultiBufferSource buffer, int packedLight, int packedOverlay) {
        poseStack.pushPose();

        float swingAngle = blockEntity.getSwingAngle(partialTick);

        VertexConsumer vertexConsumer = buffer.getBuffer(RenderType.entityTranslucent(getTextureLocation(blockEntity)));

        // Base swings slightly, randomized between Z and X axis
        float baseRotationAxis = blockEntity.hashCode() % 2 == 0 ? 1.0f : 0.5f; // Randomized rotation axis
        poseStack.mulPose(Axis.of(baseRotationAxis, 0.0f, 1.0f).rotationDegrees(swingAngle * 0.3f));

        model.base.render(poseStack, vertexConsumer, packedLight, packedOverlay);

        for (int i = 1; i <= 4; i++) {
            poseStack.pushPose();

            float boundSwingFactor = 0.6f + (i * 0.15f); // Progressive swing
            float axisX = (i % 2 == 0) ? 1.0f : 0.0f; // Alternate rotation axis
            float axisZ = (i % 2 == 0) ? 0.0f : 1.0f;

            poseStack.mulPose(Axis.of(axisX, 0.0f, axisZ).rotationDegrees(swingAngle * boundSwingFactor));

            model.getChimeBound(i).render(poseStack, vertexConsumer, packedLight, packedOverlay);
            model.getChime(i).render(poseStack, vertexConsumer, packedLight, packedOverlay);

            poseStack.popPose();
        }

        poseStack.popPose();
    }
}
