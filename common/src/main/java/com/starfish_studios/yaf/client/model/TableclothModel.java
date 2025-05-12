package com.starfish_studios.yaf.client.model;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.starfish_studios.yaf.YetAnotherFurniture;
import net.minecraft.client.model.Model;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.resources.ResourceLocation;

public class TableclothModel extends Model {

    public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(YetAnotherFurniture.id( "tablecloth"), "main");
    private final ModelPart cloth;

    public TableclothModel(ModelPart root) {
        super(RenderType::entityCutout);
        this.cloth = root.getChild("cloth");
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();

        PartDefinition cloth = partdefinition.addOrReplaceChild("cloth", CubeListBuilder.create().texOffs(0, 0).addBox(-16.02F, -2.02F, -0.02F, 16.04F, 16.04F, 16.04F, new CubeDeformation(0.0F)), PartPose.offset(8.0F, 10.0F, -8.0F));

        return LayerDefinition.create(meshdefinition, 64, 64);
    }

    @Override
    public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, int color) {
        cloth.render(poseStack, vertexConsumer, packedLight, packedOverlay, color);
    }
}