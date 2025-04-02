package com.crispytwig.omf.client.renderer.blockentity;

import com.crispytwig.nookcranny.NookAndCranny;
import com.crispytwig.nookcranny.blocks.FanBlock;
import com.crispytwig.nookcranny.blocks.entities.FanBlockEntity;
import com.crispytwig.nookcranny.client.FanModel;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;

public class FanBlockEntityRenderer implements BlockEntityRenderer<FanBlockEntity> {

    ModelPart model;

    public FanBlockEntityRenderer(BlockEntityRendererProvider.Context context) {
        model = context.bakeLayer(FanModel.LAYER_LOCATION);
    }

    public ResourceLocation getTextureLocation(FanBlockEntity entity) {
        var wood = ((FanBlock) entity.getBlockState().getBlock()).wood;
        return new ResourceLocation(NookAndCranny.MOD_ID, "textures/block/fan/" + wood + "_fan.png");
    }

    @Override
    public void render(FanBlockEntity blockEntity, float partialTick, PoseStack poseStack, MultiBufferSource buffer, int packedLight, int packedOverlay) {
        poseStack.pushPose();

        var dir = blockEntity.getBlockState().getValue(BlockStateProperties.FACING);

        poseStack.translate(0.5, 0.5, 0.5);
        float rotationAngle = blockEntity.getRotationAngle(partialTick);

        switch (dir) {
            case NORTH -> poseStack.mulPose(Axis.XN.rotationDegrees(90));
            case SOUTH -> poseStack.mulPose(Axis.XP.rotationDegrees(90));
            case WEST -> poseStack.mulPose(Axis.ZP.rotationDegrees(90));
            case EAST -> poseStack.mulPose(Axis.ZN.rotationDegrees(90));
            case UP -> {}
            case DOWN -> poseStack.mulPose(Axis.XP.rotationDegrees(180));
        }
        poseStack.mulPose(Axis.YP.rotationDegrees(rotationAngle));
        poseStack.translate(0.0,-1.0,0.0);

        model.render(poseStack,
                buffer.getBuffer(RenderType.entityTranslucent(getTextureLocation(blockEntity))),
                packedLight,
                packedOverlay
        );

        poseStack.popPose();
    }
}
