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

public class TableBlockEntityModel extends Model {

    public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(new ResourceLocation(YetAnotherFurniture.MOD_ID, "table_full"), "main");

    private final ModelPart top;
    public final ModelPart leg1;
    public final ModelPart leg2;
    public final ModelPart leg3;
    public final ModelPart leg4;

    public TableBlockEntityModel(ModelPart root) {
        super(RenderType::entityCutout);
        this.top = root.getChild("top");
        this.leg1 = root.getChild("leg1");
        this.leg2 = root.getChild("leg2");
        this.leg3 = root.getChild("leg3");
        this.leg4 = root.getChild("leg4");
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();

        partdefinition.addOrReplaceChild("top", CubeListBuilder.create().texOffs(0, 0).mirror().addBox(-16.0F, -2.0F, 0.0F, 16.0F, 3.0F, 16.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(8.0F, 10.0F, -8.0F));

        PartDefinition leg2 = partdefinition.addOrReplaceChild("leg4", CubeListBuilder.create().texOffs(0, 8).addBox(-7.0F, -14.0F, 1.5F, 4.0F, 4.0F, 0.0F, new CubeDeformation(0.0F)).texOffs(0, 4).addBox(-1.0F, -14.0F, 3.0F, 0.0F, 4.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(7.0F, 24.0F, -7.0F));
        leg2.addOrReplaceChild("cube_r1", CubeListBuilder.create().texOffs(48, 0).addBox(-1.5F, -6.5F, -1.5F, 3.0F, 13.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.5F, -6.5F, 1.5F, 0.0F, -1.5708F, 0.0F));

        PartDefinition leg4 = partdefinition.addOrReplaceChild("leg1", CubeListBuilder.create().texOffs(0, 12).addBox(-4.0F, -6.0F, -5.5F, 4.0F, 4.0F, 0.0F, new CubeDeformation(0.0F)).texOffs(0, 3).addBox(-6.0F, -6.0F, -4.0F, 0.0F, 4.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 16.0F, 0.0F));
        leg4.addOrReplaceChild("cube_r2", CubeListBuilder.create().texOffs(48, 0).mirror().addBox(-1.5F, -6.5F, -1.5F, 3.0F, 13.0F, 3.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-5.5F, 1.5F, -5.5F, 0.0F, 1.5708F, 0.0F));

        partdefinition.addOrReplaceChild("leg2", CubeListBuilder.create().texOffs(48, 0).mirror().addBox(-7.0F, -5.0F, 4.0F, 3.0F, 13.0F, 3.0F, new CubeDeformation(0.0F)).mirror(false)
                .texOffs(0, 8).addBox(-6.0F, -6.0F, 0.0F, 0.0F, 4.0F, 4.0F, new CubeDeformation(0.0F))
                .texOffs(0, 12).addBox(-4.0F, -6.0F, 5.5F, 4.0F, 4.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 16.0F, 0.0F));

        partdefinition.addOrReplaceChild("leg3", CubeListBuilder.create().texOffs(48, 0).addBox(4.0F, -5.0F, 4.0F, 3.0F, 13.0F, 3.0F, new CubeDeformation(0.0F))
                .texOffs(0, 8).addBox(6.0F, -6.0F, 0.0F, 0.0F, 4.0F, 4.0F, new CubeDeformation(0.0F))
                .texOffs(0, 8).addBox(0.0F, -6.0F, 5.5F, 4.0F, 4.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 16.0F, 0.0F));

        return LayerDefinition.create(meshdefinition, 64, 64);
    }

    @Override
    public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
        top.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
        leg1.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
        leg2.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
        leg3.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
        leg4.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
    }
}