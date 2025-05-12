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

public class TallStoolBlockEntityModel extends Model {
    public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(YetAnotherFurniture.id( "tall_stool"), "main");
    private final ModelPart group;

    public TallStoolBlockEntityModel(ModelPart root) {
        super(RenderType::entityCutout);
        this.group = root.getChild("group");
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();

        PartDefinition group = partdefinition.addOrReplaceChild("group", CubeListBuilder.create().texOffs(0, 0).addBox(-6.0F, -16.0F, -9.0F, 12.0F, 2.0F, 12.0F, new CubeDeformation(0.0F))
                .texOffs(0, 14).addBox(-3.0F, -8.0F, -6.0F, 6.0F, 2.0F, 6.0F, new CubeDeformation(0.0F))
                .texOffs(0, 22).addBox(2.0F, -14.0F, -7.0F, 2.0F, 14.0F, 2.0F, new CubeDeformation(0.0F))
                .texOffs(0, 22).mirror().addBox(-4.0F, -14.0F, -7.0F, 2.0F, 14.0F, 2.0F, new CubeDeformation(0.0F)).mirror(false)
                .texOffs(0, 22).mirror().addBox(2.0F, -14.0F, -1.0F, 2.0F, 14.0F, 2.0F, new CubeDeformation(0.0F)).mirror(false)
                .texOffs(0, 22).addBox(-4.0F, -14.0F, -1.0F, 2.0F, 14.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 24.0F, 3.0F));

        return LayerDefinition.create(meshdefinition, 64, 64);
    }

    @Override
    public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, int color) {
        group.render(poseStack, vertexConsumer, packedLight, packedOverlay, color);
    }
}