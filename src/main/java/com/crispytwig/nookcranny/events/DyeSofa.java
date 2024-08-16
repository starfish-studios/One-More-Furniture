package com.crispytwig.nookcranny.events;

import com.crispytwig.nookcranny.blocks.SofaBlock;
import com.crispytwig.nookcranny.registry.NCBlocks;
import net.fabricmc.fabric.api.event.player.UseBlockCallback;
import net.minecraft.Util;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
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

            level.setBlockAndUpdate(pos, COLOR_MAP.get(color.getId()).setValue(BlockStateProperties.HORIZONTAL_FACING, blockState.getValue(BlockStateProperties.HORIZONTAL_FACING)));
            level.playSound(null, pos, SoundEvents.DYE_USE, player.getSoundSource(), 1.0F, 1.0F);
            for(int i = 0; i < 5; ++i) {
                double d = level.random.nextGaussian() * 0.025;
                double e = level.random.nextGaussian() * 0.025;
                double f = level.random.nextGaussian() * 0.025;
                level.addParticle(ParticleTypes.POOF, (double)pos.getX() + 0.5, (double)pos.getY() + 0.5, (double)pos.getZ() + 0.5, d, e, f);
            }

            return InteractionResult.SUCCESS;
        }
        return InteractionResult.PASS;
    }
}
