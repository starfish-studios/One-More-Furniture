package com.starfish_studios.yaf.client.renderer.blockentity;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Axis;
import com.starfish_studios.yaf.YetAnotherFurniture;
import com.starfish_studios.yaf.block.entity.ChairBlockEntity;
import com.starfish_studios.yaf.block.entity.TableBlockEntity;
import com.starfish_studios.yaf.block.properties.ColorList;
import com.starfish_studios.yaf.client.model.ChairBlockEntityModel;
import com.starfish_studios.yaf.client.model.ChairCushionModel;
import com.starfish_studios.yaf.client.model.TableBlockEntityModel;
import com.starfish_studios.yaf.client.model.TableclothModel;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;

import java.util.Locale;

public class ChairBlockEntityRenderer implements BlockEntityRenderer<ChairBlockEntity> {

    private final ChairBlockEntityModel model;
    private final ChairCushionModel cloth;

    public ChairBlockEntityRenderer(BlockEntityRendererProvider.Context context) {
        model = new ChairBlockEntityModel(context.bakeLayer(ChairBlockEntityModel.LAYER_LOCATION));
        cloth = new ChairCushionModel(context.bakeLayer(ChairCushionModel.LAYER_LOCATION));
    }

    @Override
    public void render(ChairBlockEntity blockEntity, float partialTick, PoseStack poseStack, MultiBufferSource buffer, int packedLight, int packedOverlay) {

        VertexConsumer vertexConsumer = buffer.getBuffer(RenderType.entityTranslucent(getTextureLocation(blockEntity)));

        model.back.visible = blockEntity.hasBack();

        poseStack.pushPose();
        poseStack.translate(0.5, 1.5, 0.5);
        poseStack.scale(-1, -1, 1);
        poseStack.mulPose(Axis.YP.rotationDegrees(180 + blockEntity.getBlockState().getValue(BlockStateProperties.HORIZONTAL_FACING).toYRot()));
        model.renderToBuffer(poseStack, vertexConsumer, packedLight, packedOverlay, 1f, 1f, 1f, 1f);
        if (blockEntity.getColor() != ColorList.EMPTY) {
            VertexConsumer clothConsumer = buffer.getBuffer(RenderType.entityTranslucent(getClothTextureLocation(blockEntity)));
            cloth.renderToBuffer(poseStack, clothConsumer, packedLight, packedOverlay, 1f, 1f, 1f, 1f);
        }

        poseStack.popPose();
    }

    private ResourceLocation getTextureLocation(ChairBlockEntity blockEntity) {
        var state = blockEntity.getBlockState().getBlock();
        var name = BuiltInRegistries.BLOCK.getKey(state).getPath();
        var type = blockEntity.getChairType().id;

        return new ResourceLocation(YetAnotherFurniture.MOD_ID, "textures/entity/chair/" + name + type + ".png");
    }

    private ResourceLocation getClothTextureLocation(ChairBlockEntity blockEntity) {
        var name = blockEntity.getColor().getSerializedName().toLowerCase(Locale.ROOT);

        return new ResourceLocation(YetAnotherFurniture.MOD_ID, "textures/entity/chair/" + name + "_cushion.png");
    }
}