package com.crispytwig.nookcranny.events;

import com.crispytwig.nookcranny.blocks.SofaBlock;
import com.crispytwig.nookcranny.registry.NCBlocks;
import com.crispytwig.nookcranny.registry.NCParticles;
import net.fabricmc.fabric.api.event.player.UseBlockCallback;
import net.minecraft.Util;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.BlockParticleOption;
import net.minecraft.core.particles.DustParticleOptions;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.item.DyeItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.Vec3;

import java.util.HashMap;
import java.util.Map;

public class DyeSofa implements UseBlockCallback {

    private static final Map<Integer, BlockState> COLOR_MAP = Util.make(new HashMap<>(), (map) -> {
        map.put(DyeColor.WHITE.getId(), NCBlocks.WHITE_SOFA.defaultBlockState());
        map.put(DyeColor.ORANGE.getId(), NCBlocks.ORANGE_SOFA.defaultBlockState());
        map.put(DyeColor.MAGENTA.getId(), NCBlocks.MAGENTA_SOFA.defaultBlockState());
        map.put(DyeColor.LIGHT_BLUE.getId(), NCBlocks.LIGHT_BLUE_SOFA.defaultBlockState());
        map.put(DyeColor.YELLOW.getId(), NCBlocks.YELLOW_SOFA.defaultBlockState());
        map.put(DyeColor.LIME.getId(), NCBlocks.LIME_SOFA.defaultBlockState());
        map.put(DyeColor.PINK.getId(), NCBlocks.PINK_SOFA.defaultBlockState());
        map.put(DyeColor.GRAY.getId(), NCBlocks.GRAY_SOFA.defaultBlockState());
        map.put(DyeColor.LIGHT_GRAY.getId(), NCBlocks.LIGHT_GRAY_SOFA.defaultBlockState());
        map.put(DyeColor.CYAN.getId(), NCBlocks.CYAN_SOFA.defaultBlockState());
        map.put(DyeColor.PURPLE.getId(), NCBlocks.PURPLE_SOFA.defaultBlockState());
        map.put(DyeColor.BLUE.getId(), NCBlocks.BLUE_SOFA.defaultBlockState());
        map.put(DyeColor.BROWN.getId(), NCBlocks.BROWN_SOFA.defaultBlockState());
        map.put(DyeColor.GREEN.getId(), NCBlocks.GREEN_SOFA.defaultBlockState());
        map.put(DyeColor.RED.getId(), NCBlocks.RED_SOFA.defaultBlockState());
        map.put(DyeColor.BLACK.getId(), NCBlocks.BLACK_SOFA.defaultBlockState());
    });

    @Override
    public InteractionResult interact(Player player, Level level, InteractionHand hand, BlockHitResult hitResult) {
        BlockPos pos = hitResult.getBlockPos();
        BlockState blockState = level.getBlockState(pos);
        Block block = blockState.getBlock();
        ItemStack itemStack = player.getItemInHand(hand);
        Item item = itemStack.getItem();
        if (item instanceof DyeItem && COLOR_MAP.get(((DyeItem) item).getDyeColor().getId()) != block.defaultBlockState() && block instanceof SofaBlock) {
            DyeColor color = ((DyeItem) item).getDyeColor();

            level.setBlockAndUpdate(pos, COLOR_MAP.get(color.getId())
                    .setValue(BlockStateProperties.HORIZONTAL_FACING, blockState.getValue(BlockStateProperties.HORIZONTAL_FACING))
                    .setValue(SofaBlock.SHAPE, blockState.getValue(SofaBlock.SHAPE))
            );
            level.playSound(null, pos, SoundEvents.DYE_USE, player.getSoundSource(), 1.0F, 1.0F);
            for(int j = 0; j < 10; ++j) {
                double g = level.random.nextGaussian() * 0.2;
                double h = level.random.nextGaussian() * 0.1;
                double i = level.random.nextGaussian() * 0.2;

                DustParticleOptions dustParticleOptions = new DustParticleOptions(Vec3.fromRGB24(color.getTextColor()).toVector3f(), 1.0F);

                if (!level.isClientSide) {
                    ServerLevel serverLevel = (ServerLevel) level;
                    serverLevel.sendParticles(dustParticleOptions, (double) pos.getX() + 0.5, (double) pos.getY() + 0.8, (double) pos.getZ() + 0.5, 1, g, h, i, 0.0D);
                }
            }

            return InteractionResult.SUCCESS;
        }
        return InteractionResult.PASS;
    }
}
