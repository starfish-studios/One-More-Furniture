package com.crispytwig.nookcranny.client.renderer.blockentity;

import com.crispytwig.nookcranny.NookAndCranny;
import com.crispytwig.nookcranny.blocks.entities.TableBlockEntity;
import com.crispytwig.nookcranny.client.model.TableBlockEntityModel;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;

public class TableBlockEntityRenderer implements BlockEntityRenderer<TableBlockEntity> {

    private final TableBlockEntityModel model;

    public TableBlockEntityRenderer(BlockEntityRendererProvider.Context context) {
        model = new TableBlockEntityModel(context.bakeLayer(TableBlockEntityModel.LAYER_LOCATION));
    }

    @Override
    public void render(TableBlockEntity blockEntity, float partialTick, PoseStack poseStack, MultiBufferSource buffer, int packedLight, int packedOverlay) {
        var leg1 = blockEntity.hasLeg(1);
        var leg2 = blockEntity.hasLeg(2);
        var leg3 = blockEntity.hasLeg(3);
        var leg4 = blockEntity.hasLeg(4);

        model.leg1.visible = leg1;
        model.leg2.visible = leg2;
        model.leg3.visible = leg3;
        model.leg4.visible = leg4;
        VertexConsumer vertexConsumer = buffer.getBuffer(RenderType.entityTranslucent(getTextureLocation(blockEntity)));

        poseStack.pushPose();
        poseStack.translate(0.5, 1.5, 0.5);
        poseStack.scale(-1, -1, 1);
        model.renderToBuffer(poseStack, vertexConsumer, packedLight, packedOverlay, 1f, 1f, 1f, 1f);

        poseStack.popPose();
    }

    private ResourceLocation getTextureLocation(TableBlockEntity blockEntity) {
        var state = blockEntity.getBlockState().getBlock();

        return new ResourceLocation(NookAndCranny.MOD_ID, "textures/block/table/" + BuiltInRegistries.BLOCK.getKey(state).getPath() + ".png");
    }
}
