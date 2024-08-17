package com.crispytwig.nookcranny.client.renderer;

import com.crispytwig.nookcranny.blocks.entities.SpigotBlockEntity;
import com.mojang.blaze3d.vertex.PoseStack;
import net.fabricmc.api.Environment;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.resources.ResourceLocation;

import static com.crispytwig.nookcranny.NookAndCranny.MOD_ID;

@Environment(net.fabricmc.api.EnvType.CLIENT)
public class SpigotBlockRenderer implements BlockEntityRenderer<SpigotBlockEntity> {
    public static final ResourceLocation TEXTURE = new ResourceLocation(MOD_ID, "block/spigot");

    @Override
    public void render(SpigotBlockEntity blockEntity, float f, PoseStack poseStack, MultiBufferSource multiBufferSource, int i, int j) {
    }
}
