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

public class ChairCushionModel extends Model {

    public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(YetAnotherFurniture.id( "chair_cushion_converted"), "main");
    private final ModelPart cushion;

    public ChairCushionModel(ModelPart root) {
        super(RenderType::entityCutout);
        this.cushion = root.getChild("cushion");
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();

        partdefinition.addOrReplaceChild("cushion", CubeListBuilder.create().texOffs(0, 0).addBox(-15.02F, -11.02F, 0.98F, 14.04F, 3.04F, 14.04F, new CubeDeformation(0.0F)), PartPose.offset(8.0F, 24.0F, -8.0F));

        return LayerDefinition.create(meshdefinition, 64, 64);
    }

    @Override
    public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, int color) {
        cushion.render(poseStack, vertexConsumer, packedLight, packedOverlay, color);
    }
}