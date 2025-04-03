package com.crispytwig.omf.events;

import com.crispytwig.omf.block.SofaBlock;
import com.crispytwig.omf.registry.OMFDyeColor;
import com.crispytwig.omf.registry.OMFItems;
import dev.architectury.event.EventResult;
import net.minecraft.Util;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.DustParticleOptions;
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

public class DyeSofa {

    public static final Map<Integer, BlockState> COLOR_MAP = Util.make(new HashMap<>(), (map) -> {
        map.put(DyeColor.WHITE.getId(), OMFItems.SOFAS.get(OMFDyeColor.WHITE).get().getBlock().defaultBlockState());
        map.put(DyeColor.ORANGE.getId(), OMFItems.SOFAS.get(OMFDyeColor.ORANGE).get().getBlock().defaultBlockState());
        map.put(DyeColor.MAGENTA.getId(), OMFItems.SOFAS.get(OMFDyeColor.MAGENTA).get().getBlock().defaultBlockState());
        map.put(DyeColor.LIGHT_BLUE.getId(), OMFItems.SOFAS.get(OMFDyeColor.LIGHT_BLUE).get().getBlock().defaultBlockState());
        map.put(DyeColor.YELLOW.getId(), OMFItems.SOFAS.get(OMFDyeColor.YELLOW).get().getBlock().defaultBlockState());
        map.put(DyeColor.LIME.getId(), OMFItems.SOFAS.get(OMFDyeColor.LIME).get().getBlock().defaultBlockState());
        map.put(DyeColor.PINK.getId(), OMFItems.SOFAS.get(OMFDyeColor.PINK).get().getBlock().defaultBlockState());
        map.put(DyeColor.GRAY.getId(),OMFItems.SOFAS.get(OMFDyeColor.GRAY).get().getBlock().defaultBlockState());
        map.put(DyeColor.LIGHT_GRAY.getId(), OMFItems.SOFAS.get(OMFDyeColor.LIGHT_GRAY).get().getBlock().defaultBlockState());
        map.put(DyeColor.CYAN.getId(), OMFItems.SOFAS.get(OMFDyeColor.CYAN).get().getBlock().defaultBlockState());
        map.put(DyeColor.PURPLE.getId(), OMFItems.SOFAS.get(OMFDyeColor.PURPLE).get().getBlock().defaultBlockState());
        map.put(DyeColor.BLUE.getId(), OMFItems.SOFAS.get(OMFDyeColor.BLUE).get().getBlock().defaultBlockState());
        map.put(DyeColor.BROWN.getId(), OMFItems.SOFAS.get(OMFDyeColor.BROWN).get().getBlock().defaultBlockState());
        map.put(DyeColor.GREEN.getId(), OMFItems.SOFAS.get(OMFDyeColor.GREEN).get().getBlock().defaultBlockState());
        map.put(DyeColor.RED.getId(), OMFItems.SOFAS.get(OMFDyeColor.RED).get().getBlock().defaultBlockState());
        map.put(DyeColor.BLACK.getId(), OMFItems.SOFAS.get(OMFDyeColor.BLACK).get().getBlock().defaultBlockState());
    });

    public static EventResult interact(Player player, InteractionHand hand, BlockPos pos, Direction direction) {
        var level = player.level();
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

            return EventResult.interruptTrue();
        }
        return EventResult.pass();
    }
}
