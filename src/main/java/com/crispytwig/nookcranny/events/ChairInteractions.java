package com.crispytwig.nookcranny.events;

import com.crispytwig.nookcranny.blocks.ChairBlock;
import com.crispytwig.nookcranny.blocks.LampBlock;
import com.crispytwig.nookcranny.blocks.SofaBlock;
import com.crispytwig.nookcranny.blocks.properties.ColorList;
import com.crispytwig.nookcranny.blocks.properties.Cushionable;
import com.crispytwig.nookcranny.registry.NCBlocks;
import net.fabricmc.fabric.api.event.player.UseBlockCallback;
import net.minecraft.Util;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.DustParticleOptions;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.*;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.Vec3;

import java.util.HashMap;
import java.util.Map;

public class ChairInteractions implements UseBlockCallback, Cushionable {
    public static BlockState getBlockstateForDye(Item item, BlockState blockState) {
        return blockState.setValue(ChairBlock.CUSHION, DYE_MAP.get(item));
    }

    public static BlockState getBlockstateForCarpet(Item item, BlockState blockState) {
        return blockState.setValue(ChairBlock.CUSHION, CARPET_MAP.get(item));
    }

    private static final Map<ColorList, Item> SHEAR_MAP = Util.make(new HashMap<>(), (map) -> {
        map.put(ColorList.WHITE, Items.WHITE_CARPET);
        map.put(ColorList.LIGHT_GRAY, Items.LIGHT_GRAY_CARPET);
        map.put(ColorList.GRAY, Items.GRAY_CARPET);
        map.put(ColorList.BLACK, Items.BLACK_CARPET);
        map.put(ColorList.BROWN, Items.BROWN_CARPET);
        map.put(ColorList.RED, Items.RED_CARPET);
        map.put(ColorList.ORANGE, Items.ORANGE_CARPET);
        map.put(ColorList.YELLOW, Items.YELLOW_CARPET);
        map.put(ColorList.LIME, Items.LIME_CARPET);
        map.put(ColorList.GREEN, Items.GREEN_CARPET);
        map.put(ColorList.CYAN, Items.CYAN_CARPET);
        map.put(ColorList.LIGHT_BLUE, Items.LIGHT_BLUE_CARPET);
        map.put(ColorList.BLUE, Items.BLUE_CARPET);
        map.put(ColorList.PURPLE, Items.PURPLE_CARPET);
        map.put(ColorList.MAGENTA, Items.MAGENTA_CARPET);
        map.put(ColorList.PINK, Items.PINK_CARPET);
    });
    private static final Map<Item, ColorList> DYE_MAP = Util.make(new HashMap<>(), (map) -> {
        map.put(Items.WHITE_DYE, ColorList.WHITE);
        map.put(Items.ORANGE_DYE, ColorList.ORANGE);
        map.put(Items.MAGENTA_DYE, ColorList.MAGENTA);
        map.put(Items.LIGHT_BLUE_DYE, ColorList.LIGHT_BLUE);
        map.put(Items.YELLOW_DYE, ColorList.YELLOW);
        map.put(Items.LIME_DYE, ColorList.LIME);
        map.put(Items.PINK_DYE, ColorList.PINK);
        map.put(Items.GRAY_DYE, ColorList.GRAY);
        map.put(Items.LIGHT_GRAY_DYE, ColorList.LIGHT_GRAY);
        map.put(Items.CYAN_DYE, ColorList.CYAN);
        map.put(Items.PURPLE_DYE, ColorList.PURPLE);
        map.put(Items.BLUE_DYE, ColorList.BLUE);
        map.put(Items.BROWN_DYE, ColorList.BROWN);
        map.put(Items.GREEN_DYE, ColorList.GREEN);
        map.put(Items.RED_DYE, ColorList.RED);
        map.put(Items.BLACK_DYE, ColorList.BLACK);
    });
    private static final Map<Item, ColorList> CARPET_MAP = Util.make(new HashMap<>(), (map) -> {
        map.put(Items.WHITE_CARPET, ColorList.WHITE);
        map.put(Items.LIGHT_GRAY_CARPET, ColorList.LIGHT_GRAY);
        map.put(Items.GRAY_CARPET, ColorList.GRAY);
        map.put(Items.BLACK_CARPET, ColorList.BLACK);
        map.put(Items.BROWN_CARPET, ColorList.BROWN);
        map.put(Items.RED_CARPET, ColorList.RED);
        map.put(Items.ORANGE_CARPET, ColorList.ORANGE);
        map.put(Items.YELLOW_CARPET, ColorList.YELLOW);
        map.put(Items.LIME_CARPET, ColorList.LIME);
        map.put(Items.GREEN_CARPET, ColorList.GREEN);
        map.put(Items.CYAN_CARPET, ColorList.CYAN);
        map.put(Items.LIGHT_BLUE_CARPET, ColorList.LIGHT_BLUE);
        map.put(Items.BLUE_CARPET, ColorList.BLUE);
        map.put(Items.PURPLE_CARPET, ColorList.PURPLE);
        map.put(Items.MAGENTA_CARPET, ColorList.MAGENTA);
        map.put(Items.PINK_CARPET, ColorList.PINK);
    });

    @Override
    public InteractionResult interact(Player player, Level level, InteractionHand hand, BlockHitResult hitResult) {
        BlockPos pos = hitResult.getBlockPos();
        BlockState blockState = level.getBlockState(pos);
        Block block = blockState.getBlock();
        ItemStack itemStack = player.getItemInHand(hand);
        Item item = itemStack.getItem();

        if (item instanceof ShearsItem && block instanceof ChairBlock && blockState.getValue(ChairBlock.CUSHION) != ColorList.EMPTY) {
            ColorList cushion = blockState.getValue(ChairBlock.CUSHION);
            Item carpet = SHEAR_MAP.get(cushion);
            if (carpet != null) {
                level.setBlockAndUpdate(pos, blockState.setValue(ChairBlock.CUSHION, ColorList.EMPTY));
                level.addFreshEntity(new ItemEntity(level, pos.getX() + 0.5, pos.getY() + 1.0, pos.getZ() + 0.5, new ItemStack(carpet, 1)));
                level.playSound(null, pos, SoundEvents.SHEEP_SHEAR, player.getSoundSource(), 1.0F, 1.0F);
                return InteractionResult.SUCCESS;
            }
        } else if (item instanceof DyeItem && block instanceof ChairBlock && blockState.getValue(ChairBlock.CUSHION) != ColorList.EMPTY) {
            BlockState newState = getBlockstateForDye(item, blockState);
            level.setBlockAndUpdate(pos, newState);
            DyeColor color = ((DyeItem) item).getDyeColor();
            level.playSound(null, pos, SoundEvents.DYE_USE, player.getSoundSource(), 1.0F, 1.0F);
            for (int j = 0; j < 5; ++j) {
                double g = level.random.nextGaussian() * 0.2;
                double h = level.random.nextGaussian() * 0.1;
                double i = level.random.nextGaussian() * 0.2;

                DustParticleOptions dustParticleOptions = new DustParticleOptions(Vec3.fromRGB24(color.getTextColor()).toVector3f(), 1.0F);

                if (!level.isClientSide) {
                    ServerLevel serverLevel = (ServerLevel) level;
                    serverLevel.sendParticles(dustParticleOptions, (double) pos.getX() + 0.5, dyeHeight(), (double) pos.getZ() + 0.5, 1, g, h, i, 0.0D);
                }
            }

            return InteractionResult.SUCCESS;
        } else if (itemStack.is(ItemTags.WOOL_CARPETS) && block instanceof ChairBlock && blockState.getValue(ChairBlock.CUSHION) == ColorList.EMPTY) {
            BlockState newState = getBlockstateForCarpet(item, blockState);
            level.setBlockAndUpdate(pos, newState.setValue(BlockStateProperties.HORIZONTAL_FACING, blockState.getValue(BlockStateProperties.HORIZONTAL_FACING)));
            level.playSound(null, pos, SoundEvents.WOOL_PLACE, player.getSoundSource(), 1.0F, 1.0F);
            if (!player.isCreative()) {
                itemStack.shrink(1);
            }
            return InteractionResult.SUCCESS;
        }
        return InteractionResult.PASS;
    }
}
