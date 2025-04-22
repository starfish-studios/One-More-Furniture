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

public class TallStoolCushionModel extends Model {
    public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(new ResourceLocation(YetAnotherFurniture.MOD_ID, "tall_stool_cushion"), "main");
    private final ModelPart bone;

    public TallStoolCushionModel(ModelPart root) {
        super(RenderType::entityCutout);
        this.bone = root.getChild("bone");
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();
        partdefinition.addOrReplaceChild("bone", CubeListBuilder.create().texOffs(0, 0).addBox(-14.0F, -17.0F, 2.0F, 12.0F, 2.0F, 12.0F, new CubeDeformation(0.2F)), PartPose.offset(8.0F, 24.0F, -8.0F));

        return LayerDefinition.create(meshdefinition, 64, 64);
    }

    @Override
    public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
        bone.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
    }
}