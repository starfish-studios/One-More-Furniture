package com.starfish_studios.yaf.client.renderer.blockentity;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.starfish_studios.yaf.YetAnotherFurniture;
import com.starfish_studios.yaf.block.entity.TallStoolBlockEntity;
import com.starfish_studios.yaf.block.properties.ColorList;
import com.starfish_studios.yaf.client.model.ChairCushionModel;
import com.starfish_studios.yaf.client.model.TallStoolBlockEntityModel;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;

import java.util.Locale;

public class TallStoolBlockEntityRenderer implements BlockEntityRenderer<TallStoolBlockEntity> {

    private final TallStoolBlockEntityModel model;
    private final ChairCushionModel cloth;

    public TallStoolBlockEntityRenderer(BlockEntityRendererProvider.Context context) {
        model = new TallStoolBlockEntityModel(context.bakeLayer(TallStoolBlockEntityModel.LAYER_LOCATION));
        cloth = new ChairCushionModel(context.bakeLayer(ChairCushionModel.LAYER_LOCATION));
    }

    @Override
    public void render(TallStoolBlockEntity blockEntity, float partialTick, PoseStack poseStack, MultiBufferSource buffer, int packedLight, int packedOverlay) {

        VertexConsumer vertexConsumer = buffer.getBuffer(RenderType.entityTranslucent(getTextureLocation(blockEntity)));

        poseStack.pushPose();
        poseStack.translate(0.5, 1.5, 0.5);
        poseStack.scale(-1, -1, 1);
        model.renderToBuffer(poseStack, vertexConsumer, packedLight, packedOverlay, 1f, 1f, 1f, 1f);
        if (blockEntity.getColor() != ColorList.EMPTY) {
            VertexConsumer clothConsumer = buffer.getBuffer(RenderType.entityTranslucent(getClothTextureLocation(blockEntity)));
            poseStack.translate(0.0, -4.0 / 16.0, 0.0);
            poseStack.scale(0.8F, 0.8F, 0.8F);
            cloth.renderToBuffer(poseStack, clothConsumer, packedLight, packedOverlay, 1f, 1f, 1f, 1f);
        }

        poseStack.popPose();
    }

    private ResourceLocation getTextureLocation(TallStoolBlockEntity blockEntity) {
        var state = blockEntity.getBlockState().getBlock();
        var name = BuiltInRegistries.BLOCK.getKey(state).getPath();

        return new ResourceLocation(YetAnotherFurniture.MOD_ID, "textures/entity/tall_stool/" + name + ".png");
    }

    private ResourceLocation getClothTextureLocation(TallStoolBlockEntity blockEntity) {
        var name = blockEntity.getColor().getSerializedName().toLowerCase(Locale.ROOT);

        return new ResourceLocation(YetAnotherFurniture.MOD_ID, "textures/entity/chair/" + name + "_cushion.png");
    }
}