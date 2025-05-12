package com.starfish_studios.yaf.client.renderer.blockentity;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.starfish_studios.yaf.YetAnotherFurniture;
import com.starfish_studios.yaf.block.entity.TableBlockEntity;
import com.starfish_studios.yaf.block.properties.ColorList;
import com.starfish_studios.yaf.client.model.TableBlockEntityModel;
import com.starfish_studios.yaf.client.model.TableclothModel;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;

import java.util.Locale;

public class TableBlockEntityRenderer implements BlockEntityRenderer<TableBlockEntity> {

    private final TableBlockEntityModel model;
    private final TableclothModel cloth;
    private final float oy;

    public TableBlockEntityRenderer(BlockEntityRendererProvider.Context context) {
        model = new TableBlockEntityModel(context.bakeLayer(TableBlockEntityModel.LAYER_LOCATION));
        cloth = new TableclothModel(context.bakeLayer(TableclothModel.LAYER_LOCATION));
        oy = model.top.y;
    }

    @Override
    public void render(TableBlockEntity blockEntity, float partialTick, PoseStack poseStack, MultiBufferSource buffer, int packedLight, int packedOverlay) {
        var leg1 = blockEntity.hasLeg(1);
        var leg2 = blockEntity.hasLeg(2);
        var leg3 = blockEntity.hasLeg(3);
        var leg4 = blockEntity.hasLeg(4);

        var isShort = blockEntity.isShort();

        model.leg1.visible = leg1 && !isShort;
        model.leg2.visible = leg2 && !isShort;
        model.leg3.visible = leg3 && !isShort;
        model.leg4.visible = leg4 && !isShort;

        model.small1.visible = leg1 && isShort;
        model.small2.visible = leg2 && isShort;
        model.small3.visible = leg3 && isShort;
        model.small4.visible = leg4 && isShort;

        var shortY = (isShort ? 8f : 0f);

        model.top.y = oy + shortY;

        VertexConsumer vertexConsumer = buffer.getBuffer(RenderType.entityTranslucent(getTextureLocation(blockEntity)));

        poseStack.pushPose();
        poseStack.translate(0.5, 1.5, 0.5);
        poseStack.scale(-1, -1, 1);
        model.renderToBuffer(poseStack, vertexConsumer, packedLight, packedOverlay, -1);
        if (blockEntity.getColor() != ColorList.EMPTY) {
            VertexConsumer clothConsumer = buffer.getBuffer(RenderType.entityTranslucent(getClothTextureLocation(blockEntity)));
            poseStack.translate(0.0,shortY / 16,0.0);
            cloth.renderToBuffer(poseStack, clothConsumer, packedLight, packedOverlay, -1);
        }

        poseStack.popPose();
    }

    private ResourceLocation getTextureLocation(TableBlockEntity blockEntity) {
        var state = blockEntity.getBlockState().getBlock();
        var name = BuiltInRegistries.BLOCK.getKey(state).getPath();

        return YetAnotherFurniture.id( "textures/entity/table/" + name + ".png");
    }

    private ResourceLocation getClothTextureLocation(TableBlockEntity blockEntity) {
        var name = blockEntity.getColor().getSerializedName().toLowerCase(Locale.ROOT);

        return YetAnotherFurniture.id( "textures/entity/table/" + name + "_tablecloth.png");
    }
}