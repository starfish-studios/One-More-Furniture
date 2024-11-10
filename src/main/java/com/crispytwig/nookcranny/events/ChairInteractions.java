package com.crispytwig.nookcranny.events;

import com.crispytwig.nookcranny.blocks.ChairBlock;
import com.crispytwig.nookcranny.blocks.LampBlock;
import com.crispytwig.nookcranny.blocks.SofaBlock;
import com.crispytwig.nookcranny.blocks.properties.ColorList;
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

public class ChairInteractions implements UseBlockCallback {

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

    public static BlockState getBlockstateForDye(Item item, BlockState blockState) {
        return blockState.setValue(ChairBlock.CUSHION, getColorFor(item));
    }

    public static ColorList getColorFor(Item item) {
        if (item == Items.WHITE_DYE) {
            return ColorList.WHITE;
        } else if (item == Items.ORANGE_DYE) {
            return ColorList.ORANGE;
        } else if (item == Items.MAGENTA_DYE) {
            return ColorList.MAGENTA;
        } else if (item == Items.LIGHT_BLUE_DYE) {
            return ColorList.LIGHT_BLUE;
        } else if (item == Items.YELLOW_DYE) {
            return ColorList.YELLOW;
        } else if (item == Items.LIME_DYE) {
            return ColorList.LIME;
        } else if (item == Items.PINK_DYE) {
            return ColorList.PINK;
        } else if (item == Items.GRAY_DYE) {
            return ColorList.GRAY;
        } else if (item == Items.LIGHT_GRAY_DYE) {
            return ColorList.LIGHT_GRAY;
        } else if (item == Items.CYAN_DYE) {
            return ColorList.CYAN;
        } else if (item == Items.PURPLE_DYE) {
            return ColorList.PURPLE;
        } else if (item == Items.BLUE_DYE) {
            return ColorList.BLUE;
        } else if (item == Items.BROWN_DYE) {
            return ColorList.BROWN;
        } else if (item == Items.GREEN_DYE) {
            return ColorList.GREEN;
        } else if (item == Items.RED_DYE) {
            return ColorList.RED;
        } else if (item == Items.BLACK_DYE) {
            return ColorList.BLACK;
        } else {
            return ColorList.EMPTY;
        }
    }

    public static BlockState getBlockstateForCarpet(Item item, BlockState blockState) {
        return blockState.setValue(ChairBlock.CUSHION, getCarpetFor(item));
    }

    public static ColorList getCarpetFor(Item item) {
        if (item == Items.WHITE_CARPET) {
            return ColorList.WHITE;
        } else if (item == Items.LIGHT_GRAY_CARPET) {
            return ColorList.LIGHT_GRAY;
        } else if (item == Items.GRAY_CARPET) {
            return ColorList.GRAY;
        } else if (item == Items.BLACK_CARPET) {
            return ColorList.BLACK;
        } else if (item == Items.BROWN_CARPET) {
            return ColorList.BROWN;
        } else if (item == Items.RED_CARPET) {
            return ColorList.RED;
        } else if (item == Items.ORANGE_CARPET) {
            return ColorList.ORANGE;
        } else if (item == Items.YELLOW_CARPET) {
            return ColorList.YELLOW;
        } else if (item == Items.LIME_CARPET) {
            return ColorList.LIME;
        } else if (item == Items.GREEN_CARPET) {
            return ColorList.GREEN;
        } else if (item == Items.CYAN_CARPET) {
            return ColorList.CYAN;
        } else if (item == Items.LIGHT_BLUE_CARPET) {
            return ColorList.LIGHT_BLUE;
        } else if (item == Items.BLUE_CARPET) {
            return ColorList.BLUE;
        } else if (item == Items.PURPLE_CARPET) {
            return ColorList.PURPLE;
        } else if (item == Items.MAGENTA_CARPET) {
            return ColorList.MAGENTA;
        } else if (item == Items.PINK_CARPET) {
            return ColorList.PINK;
        } else {
            return ColorList.EMPTY;
        }
    }

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
                    serverLevel.sendParticles(dustParticleOptions, (double) pos.getX() + 0.5, (double) pos.getY() + 0.8, (double) pos.getZ() + 0.5, 1, g, h, i, 0.0D);
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
