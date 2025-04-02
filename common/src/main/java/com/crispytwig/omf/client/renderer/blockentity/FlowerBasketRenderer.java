package com.crispytwig.omf.client.renderer.blockentity;

import com.crispytwig.omf.block.FlowerBasketBlock;
import com.crispytwig.omf.block.entity.FlowerBasketBlockEntity;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.block.BlockRenderDispatcher;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.core.Direction;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.DoublePlantBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.DoubleBlockHalf;

@Environment(value= EnvType.CLIENT)
public class FlowerBasketRenderer implements BlockEntityRenderer<FlowerBasketBlockEntity> {
    private final BlockRenderDispatcher blockRenderer;

    public FlowerBasketRenderer(BlockEntityRendererProvider.Context context) {
        this.blockRenderer = context.getBlockRenderDispatcher();
    }

    @Override
    public void render(FlowerBasketBlockEntity blockEntity, float partialTick, PoseStack poseStack, MultiBufferSource bufferSource, int packedLight, int packedOverlay) {
        Direction facing = blockEntity.getBlockState().getValue(FlowerBasketBlock.FACING).getOpposite();
        float rotation = -facing.toYRot();
        BlockState lower;
        BlockState upper = null;


        poseStack.pushPose();

        poseStack.scale(0.7f, 0.7f, 0.7f);
        poseStack.translate(0f, 0.4f, 0f);

        for (int i = 0; i < 2; i++) {
            Item item = blockEntity.getItemFromSlot(i);
            if (item == Items.AIR) continue;

            Block block = ((BlockItem) item).getBlock();
            if (block instanceof DoublePlantBlock) {
                lower = block.defaultBlockState().setValue(DoublePlantBlock.HALF, DoubleBlockHalf.LOWER);
                upper = block.defaultBlockState().setValue(DoublePlantBlock.HALF, DoubleBlockHalf.UPPER);
            } else {
                lower = block.defaultBlockState();
            }

            poseStack.pushPose();
            poseStack.mulPose(Axis.YP.rotationDegrees(rotation)); // rotate based on direction
            poseStack.translate(0.6f - 0.8 * i, 0.001f * i, 0.2f); // position each flower at left and right
            poseStack.translate(0f, 0.001f * i, 0.001f * i); // prevent z-clipping
            poseStack.translate(0f, 0.05f, 0f);

            switch (blockEntity.getBlockState().getValue(FlowerBasketBlock.FACING)) { // correct position based on direction
                case EAST -> poseStack.translate(0f, 0f, -1.4f);
                case WEST -> poseStack.translate(-1.4f, 0f, 0);
                case SOUTH -> poseStack.translate(-1.4f, 0f, -1.4f);
            }
//            if (blockEntity.getBlockState().getValue(FlowerBasketBlock.ATTACHED)) { // correct position when attached
//                poseStack.translate(0f, 0.9f, 0.36f);
//            }

            blockRenderer.renderSingleBlock(lower, poseStack, bufferSource, packedLight, OverlayTexture.NO_OVERLAY);
            if (upper != null) {
                poseStack.translate(0f, 1.0f, 0f);
                blockRenderer.renderSingleBlock(upper, poseStack, bufferSource, packedLight, OverlayTexture.NO_OVERLAY);
            }
            poseStack.popPose();

        }

        poseStack.popPose();
    }
}
