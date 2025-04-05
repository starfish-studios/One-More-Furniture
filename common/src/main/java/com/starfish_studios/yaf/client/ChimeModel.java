package com.starfish_studios.yaf.client;

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

public class ChimeModel extends Model {

    public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(new ResourceLocation(YetAnotherFurniture.MOD_ID, "chime"), "main");
    public final ModelPart base;
    public final ModelPart chime_bound_1;
    public final ModelPart chime_1;
    public final ModelPart chime_bound_2;
    public final ModelPart chime_2;
    public final ModelPart chime_bound_3;
    public final ModelPart chime_3;
    public final ModelPart chime_bound_4;
    public final ModelPart chime_4;

    public ChimeModel(ModelPart root) {
        super(RenderType::entityTranslucent);
        this.base = root.getChild("base");
        this.chime_bound_1 = base.getChild("chime_bound_1");
        this.chime_1 = chime_bound_1.getChild("chime_1");
        this.chime_bound_2 = base.getChild("chime_bound_2");
        this.chime_2 = chime_bound_2.getChild("chime_2");
        this.chime_bound_3 = base.getChild("chime_bound_3");
        this.chime_3 = chime_bound_3.getChild("chime_3");
        this.chime_bound_4 = base.getChild("chime_bound_4");
        this.chime_4 = chime_bound_4.getChild("chime_4");
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();

        PartDefinition base = partdefinition.addOrReplaceChild("base", CubeListBuilder.create().texOffs(0, 0).addBox(-4.0F, 4.0F, -4.0F, 8.0F, 3.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 8.0F, 0.0F));
        base.addOrReplaceChild("cube_r1", CubeListBuilder.create().texOffs(0, 1).addBox(0.0F, -3.5F, -5.0F, 0.0F, 4.0F, 10.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 3.5F, 0.0F, 0.0F, -0.7854F, 0.0F));
        base.addOrReplaceChild("cube_r2", CubeListBuilder.create().texOffs(0, 5).addBox(0.0F, -3.5F, -5.0F, 0.0F, 4.0F, 10.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 3.5F, 0.0F, 0.0F, 0.7854F, 0.0F));
        PartDefinition chime_bound_1 = base.addOrReplaceChild("chime_bound_1", CubeListBuilder.create().texOffs(23, 10).addBox(0.0F, -2.0F, -0.5F, 0.0F, 9.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(-2.0F, 7.0F, 2.0F));
        chime_bound_1.addOrReplaceChild("chime_1", CubeListBuilder.create().texOffs(0, 19).addBox(-1.0F, 0.0F, -1.0F, 2.0F, 10.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 7.0F, 0.0F));
        PartDefinition chime_bound_2 = base.addOrReplaceChild("chime_bound_2", CubeListBuilder.create().texOffs(4, 0).addBox(0.0F, 0.0F, -0.5F, 0.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(-2.0F, 7.0F, -2.0F));
        chime_bound_2.addOrReplaceChild("chime_2", CubeListBuilder.create().texOffs(16, 19).addBox(-1.0F, 0.0F, -1.0F, 2.0F, 10.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 4.0F, 0.0F));
        PartDefinition chime_bound_3 = base.addOrReplaceChild("chime_bound_3", CubeListBuilder.create().texOffs(2, 0).addBox(0.0F, 0.0F, -0.5F, 0.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(2.0F, 7.0F, 2.0F));
        chime_bound_3.addOrReplaceChild("chime_3", CubeListBuilder.create().texOffs(24, 19).addBox(-1.0F, 0.0F, -1.0F, 2.0F, 10.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 4.0F, 0.0F));
        PartDefinition chime_bound_4 = base.addOrReplaceChild("chime_bound_4", CubeListBuilder.create().texOffs(0, 0).addBox(0.0F, 0.0F, -0.5F, 0.0F, 6.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(2.0F, 7.0F, -2.0F));
        chime_bound_4.addOrReplaceChild("chime_4", CubeListBuilder.create().texOffs(8, 19).addBox(-1.0F, 0.0F, -1.0F, 2.0F, 10.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 6.0F, 0.0F));

        return LayerDefinition.create(meshdefinition, 32, 32);
    }


    @Override
    public void renderToBuffer(PoseStack poseStack, VertexConsumer buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
        base.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
    }

    public ModelPart getChimeBound(int i) {
        return base.getChild("chime_bound_" + i);
    }

    public ModelPart getChime(int i) {
        return getChimeBound(i).getChild("chime_" + i);
    }
}