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
import net.minecraft.world.level.block.state.properties.AttachFace;
import net.minecraft.world.level.block.state.properties.DoubleBlockHalf;

@Environment(value = EnvType.CLIENT)
public class FlowerBasketRenderer implements BlockEntityRenderer<FlowerBasketBlockEntity> {
    private final BlockRenderDispatcher blockRenderer;

    public FlowerBasketRenderer(BlockEntityRendererProvider.Context context) {
        this.blockRenderer = context.getBlockRenderDispatcher();
    }

    @Override
    public void render(FlowerBasketBlockEntity blockEntity, float partialTick, PoseStack poseStack, MultiBufferSource bufferSource, int packedLight, int packedOverlay) {
        BlockState state = blockEntity.getBlockState();
        Direction facing = state.getValue(FlowerBasketBlock.FACING).getOpposite();
        float rotation = -facing.toYRot();
        AttachFace attachFace = state.getValue(FlowerBasketBlock.FACE);

        poseStack.pushPose();
        poseStack.scale(0.7f, 0.7f, 0.7f);
        poseStack.translate(0f, 0.4f, 0f);

        if (attachFace == AttachFace.WALL) {
            for (int i = 0; i < 2; i++) {
                Item item = blockEntity.getItemFromSlot(i);
                if (item == Items.AIR) continue;

                Block block = ((BlockItem) item).getBlock();
                BlockState lower;
                BlockState upper = null;
                if (block instanceof DoublePlantBlock) {
                    lower = block.defaultBlockState().setValue(DoublePlantBlock.HALF, DoubleBlockHalf.LOWER);
                    upper = block.defaultBlockState().setValue(DoublePlantBlock.HALF, DoubleBlockHalf.UPPER);
                } else {
                    lower = block.defaultBlockState();
                }

                poseStack.pushPose();
                poseStack.mulPose(Axis.YP.rotationDegrees(rotation));
                poseStack.translate(0.6f - 0.8f * i, 0.001f * i, 0.2f);
                poseStack.translate(0.0f, 0.3f, 0.4f);
                switch (state.getValue(FlowerBasketBlock.FACING)) {
                    case EAST -> poseStack.translate(0f, 0f, -1.4f);
                    case WEST -> poseStack.translate(-1.4f, 0f, 0f);
                    case SOUTH -> poseStack.translate(-1.4f, 0f, -1.4f);
                    default -> { }
                }
                blockRenderer.renderSingleBlock(lower, poseStack, bufferSource, packedLight, OverlayTexture.NO_OVERLAY);
                if (upper != null) {
                    poseStack.translate(0f, 1.0f, 0f);
                    blockRenderer.renderSingleBlock(upper, poseStack, bufferSource, packedLight, OverlayTexture.NO_OVERLAY);
                }
                poseStack.popPose();
            }
        } else {
            if (attachFace == AttachFace.CEILING) {
                poseStack.mulPose(Axis.YP.rotationDegrees(0.1f));
            }
            Item item = blockEntity.getItemFromSlot(0);
            if (item != Items.AIR) {
                Block block = ((BlockItem) item).getBlock();
                BlockState lower;
                BlockState upper = null;
                if (block instanceof DoublePlantBlock) {
                    lower = block.defaultBlockState().setValue(DoublePlantBlock.HALF, DoubleBlockHalf.LOWER);
                    upper = block.defaultBlockState().setValue(DoublePlantBlock.HALF, DoubleBlockHalf.UPPER);
                } else {
                    lower = block.defaultBlockState();
                }

                Direction basketFacing = state.getValue(FlowerBasketBlock.FACING);
                float offsetX = 0.25f;
                float offsetZ = 0.25f;
                if (attachFace == AttachFace.FLOOR) {
                    switch (basketFacing) {
                        case NORTH -> offsetZ = 0.2f;
                        case SOUTH -> {
                            offsetZ = -1.2f;
                            offsetX = -1.2f;
                        }
                        case EAST -> offsetZ = -1.2f;
                        case WEST -> {
                            offsetX = -1.2f;
                            offsetZ = 0.2f;
                        }
                    }
                }


                if (attachFace == AttachFace.FLOOR) {
                    poseStack.pushPose();
                    poseStack.mulPose(Axis.YP.rotationDegrees(rotation));
                    poseStack.translate(offsetX, 0.0f, offsetZ);
                    blockRenderer.renderSingleBlock(lower, poseStack, bufferSource, packedLight, OverlayTexture.NO_OVERLAY);
                    if (upper != null) {
                        poseStack.translate(0f, 1.0f, 0f);
                        blockRenderer.renderSingleBlock(upper, poseStack, bufferSource, packedLight, OverlayTexture.NO_OVERLAY);
                    }
                    poseStack.popPose();
                } else if (attachFace == AttachFace.CEILING) {
                    poseStack.pushPose();
                    poseStack.translate(offsetX, 0.0f, offsetZ);
                    blockRenderer.renderSingleBlock(lower, poseStack, bufferSource, packedLight, OverlayTexture.NO_OVERLAY);
                    if (upper != null) {
                        poseStack.translate(0f, -1.0f, 0f);
                        blockRenderer.renderSingleBlock(upper, poseStack, bufferSource, packedLight, OverlayTexture.NO_OVERLAY);
                    }
                    poseStack.popPose();
                }
            }
        }
        poseStack.popPose();
    }
}
