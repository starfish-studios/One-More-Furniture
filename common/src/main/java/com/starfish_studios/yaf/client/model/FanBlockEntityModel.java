package com.starfish_studios.yaf.client.model;

import com.starfish_studios.yaf.YetAnotherFurniture;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.Model;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.resources.ResourceLocation;

public class FanBlockEntityModel extends Model {
    public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(new ResourceLocation(YetAnotherFurniture.MOD_ID, "ceiling_fan_oak_head"), "main");
    private final ModelPart wings;

    public FanBlockEntityModel(ModelPart root) {
        super(RenderType::entityTranslucent);
        this.wings = root.getChild("wings");
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();

        PartDefinition wings = partdefinition.addOrReplaceChild("wings", CubeListBuilder.create().texOffs(0, 0).addBox(-24.0F, -7.0F, -3.0F, 19.0F, 1.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offset(1.0F, 24.0F, 0.0F));

        wings.addOrReplaceChild("cube_r1", CubeListBuilder.create().texOffs(0, 0).addBox(-23.0F, -1.0F, -3.0F, 19.0F, 1.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.0F, -6.0F, 0.0F, 0.0F, 1.5708F, 0.0F));
        wings.addOrReplaceChild("cube_r2", CubeListBuilder.create().texOffs(0, 0).addBox(-23.0F, -1.0F, -3.0F, 19.0F, 1.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.0F, -6.0F, 0.0F, 0.0F, 3.1416F, 0.0F));
        wings.addOrReplaceChild("cube_r3", CubeListBuilder.create().texOffs(0, 0).addBox(-23.0F, -1.0F, -3.0F, 19.0F, 1.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.0F, -6.0F, 0.0F, 0.0F, -1.5708F, 0.0F));

        return LayerDefinition.create(meshdefinition, 64, 64);
    }

    @Override
    public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
        wings.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
    }
}