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

public class ChairBlockEntityModel extends Model {

    public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(new ResourceLocation(YetAnotherFurniture.MOD_ID, "chair"), "main");
    public final ModelPart base;
    public final ModelPart back;

    public ChairBlockEntityModel(ModelPart root) {
        super(RenderType::entityCutout);
        this.base = root.getChild("base");
        this.back = root.getChild("back");
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();

        PartDefinition base = partdefinition.addOrReplaceChild("base", CubeListBuilder.create().texOffs(16, 22).addBox(-14.0F, -0.5833F, 0.25F, 2.0F, 8.0F, 2.0F, new CubeDeformation(0.0F))
                .texOffs(16, 22).mirror().addBox(-4.0F, -0.5833F, 0.25F, 2.0F, 8.0F, 2.0F, new CubeDeformation(0.0F)).mirror(false)
                .texOffs(16, 22).addBox(-4.0F, -0.5833F, 10.25F, 2.0F, 8.0F, 2.0F, new CubeDeformation(0.0F))
                .texOffs(16, 22).addBox(-14.0F, -0.5833F, 10.25F, 2.0F, 8.0F, 2.0F, new CubeDeformation(0.0F))
                .texOffs(0, 18).addBox(-13.0F, -0.5833F, 1.25F, 10.0F, 2.0F, 0.0F, new CubeDeformation(0.0F))
                .texOffs(0, 18).addBox(-13.0F, -0.5833F, 11.25F, 10.0F, 2.0F, 0.0F, new CubeDeformation(0.0F))
                .texOffs(0, 8).addBox(-13.0F, -0.5833F, 1.25F, 0.0F, 2.0F, 10.0F, new CubeDeformation(0.0F))
                .texOffs(0, 8).addBox(-3.0F, -0.5833F, 1.25F, 0.0F, 2.0F, 10.0F, new CubeDeformation(0.0F))
                .texOffs(0, 0).addBox(-15.0F, -2.5833F, -0.75F, 14.0F, 2.0F, 14.0F, new CubeDeformation(0.0F)), PartPose.offset(8.0F, 16.5833F, -6.25F));

        PartDefinition back = partdefinition.addOrReplaceChild("back", CubeListBuilder.create().texOffs(0, 0).addBox(-6.0F, -14.0F, 5.0F, 2.0F, 12.0F, 2.0F, new CubeDeformation(0.0F))
                .texOffs(0, 0).mirror().addBox(4.0F, -14.0F, 5.0F, 2.0F, 12.0F, 2.0F, new CubeDeformation(0.0F)).mirror(false)
                .texOffs(0, 20).addBox(-4.0F, -14.0F, 6.0F, 8.0F, 12.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 16.0F, 0.0F));

        return LayerDefinition.create(meshdefinition, 64, 64);
    }


    @Override
    public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
        base.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
        back.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
    }
}