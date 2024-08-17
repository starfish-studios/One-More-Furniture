package com.crispytwig.nookcranny.registry;

import com.crispytwig.nookcranny.NookAndCranny;
import com.crispytwig.nookcranny.blocks.SpigotBlock;
import com.crispytwig.nookcranny.blocks.SofaBlock;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;

import static net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings.*;

public class NCBlocks {

    public static final Block BLACK_SOFA = register("black_sofa", new SofaBlock(DyeColor.BLACK, copyOf(Blocks.BLACK_WOOL).noOcclusion()));
    public static final Block BLUE_SOFA = register("blue_sofa", new SofaBlock(DyeColor.BLUE, copyOf(Blocks.BLUE_WOOL).noOcclusion()));
    public static final Block BROWN_SOFA = register("brown_sofa", new SofaBlock(DyeColor.BROWN, copyOf(Blocks.BROWN_WOOL).noOcclusion()));
    public static final Block CYAN_SOFA = register("cyan_sofa", new SofaBlock(DyeColor.CYAN, copyOf(Blocks.CYAN_WOOL).noOcclusion()));
    public static final Block SPIGOT = register("spigot", new SpigotBlock(copyOf(Blocks.CAULDRON).noOcclusion().randomTicks()));
    public static final Block GRAY_SOFA = register("gray_sofa", new SofaBlock(DyeColor.GRAY, copyOf(Blocks.GRAY_WOOL).noOcclusion()));
    public static final Block GREEN_SOFA = register("green_sofa", new SofaBlock(DyeColor.GREEN, copyOf(Blocks.GREEN_WOOL).noOcclusion()));
    public static final Block LIGHT_BLUE_SOFA = register("light_blue_sofa", new SofaBlock(DyeColor.LIGHT_BLUE, copyOf(Blocks.LIGHT_BLUE_WOOL).noOcclusion()));
    public static final Block LIGHT_GRAY_SOFA = register("light_gray_sofa", new SofaBlock(DyeColor.LIGHT_GRAY, copyOf(Blocks.LIGHT_GRAY_WOOL).noOcclusion()));
    public static final Block LIME_SOFA = register("lime_sofa", new SofaBlock(DyeColor.LIME, copyOf(Blocks.LIME_WOOL).noOcclusion()));
    public static final Block MAGENTA_SOFA = register("magenta_sofa", new SofaBlock(DyeColor.MAGENTA, copyOf(Blocks.MAGENTA_WOOL).noOcclusion()));
    public static final Block ORANGE_SOFA = register("orange_sofa", new SofaBlock(DyeColor.ORANGE, copyOf(Blocks.ORANGE_WOOL).noOcclusion()));
    public static final Block PINK_SOFA = register("pink_sofa", new SofaBlock(DyeColor.PINK, copyOf(Blocks.PINK_WOOL).noOcclusion()));
    public static final Block PURPLE_SOFA = register("purple_sofa", new SofaBlock(DyeColor.PURPLE, copyOf(Blocks.PURPLE_WOOL).noOcclusion()));
    public static final Block RED_SOFA = register("red_sofa", new SofaBlock(DyeColor.RED, copyOf(Blocks.RED_WOOL).noOcclusion()));
    public static final Block WHITE_SOFA = register("white_sofa", new SofaBlock(DyeColor.WHITE, copyOf(Blocks.WHITE_WOOL).noOcclusion()));
    public static final Block YELLOW_SOFA = register("yellow_sofa", new SofaBlock(DyeColor.YELLOW, copyOf(Blocks.YELLOW_WOOL).noOcclusion()));

    private static Block register(String id, Block block) {
        return Registry.register(BuiltInRegistries.BLOCK, new ResourceLocation(NookAndCranny.MOD_ID, id), block);
    }
}
