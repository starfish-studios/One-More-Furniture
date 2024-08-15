package com.crispytwig.nookcranny.registry;

import com.crispytwig.nookcranny.NookAndCranny;
import com.crispytwig.nookcranny.blocks.SofaBlock;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;

public class NCBlocks {
    public static final Block WHITE_SOFA = register("white_sofa", new SofaBlock(FabricBlockSettings.copyOf(Blocks.WHITE_WOOL).noOcclusion()));
    public static final Block ORANGE_SOFA = register("orange_sofa", new SofaBlock(FabricBlockSettings.copyOf(Blocks.ORANGE_WOOL).noOcclusion()));
    public static final Block MAGENTA_SOFA = register("magenta_sofa", new SofaBlock(FabricBlockSettings.copyOf(Blocks.MAGENTA_WOOL).noOcclusion()));
    public static final Block LIGHT_BLUE_SOFA = register("light_blue_sofa", new SofaBlock(FabricBlockSettings.copyOf(Blocks.LIGHT_BLUE_WOOL).noOcclusion()));
    public static final Block YELLOW_SOFA = register("yellow_sofa", new SofaBlock(FabricBlockSettings.copyOf(Blocks.YELLOW_WOOL).noOcclusion()));
    public static final Block LIME_SOFA = register("lime_sofa", new SofaBlock(FabricBlockSettings.copyOf(Blocks.LIME_WOOL).noOcclusion()));
    public static final Block PINK_SOFA = register("pink_sofa", new SofaBlock(FabricBlockSettings.copyOf(Blocks.PINK_WOOL).noOcclusion()));
    public static final Block GRAY_SOFA = register("gray_sofa", new SofaBlock(FabricBlockSettings.copyOf(Blocks.GRAY_WOOL).noOcclusion()));
    public static final Block LIGHT_GRAY_SOFA = register("light_gray_sofa", new SofaBlock(FabricBlockSettings.copyOf(Blocks.LIGHT_GRAY_WOOL).noOcclusion()));
    public static final Block CYAN_SOFA = register("cyan_sofa", new SofaBlock(FabricBlockSettings.copyOf(Blocks.CYAN_WOOL).noOcclusion()));
    public static final Block PURPLE_SOFA = register("purple_sofa", new SofaBlock(FabricBlockSettings.copyOf(Blocks.PURPLE_WOOL).noOcclusion()));
    public static final Block BLUE_SOFA = register("blue_sofa", new SofaBlock(FabricBlockSettings.copyOf(Blocks.BLUE_WOOL).noOcclusion()));
    public static final Block BROWN_SOFA = register("brown_sofa", new SofaBlock(FabricBlockSettings.copyOf(Blocks.BROWN_WOOL).noOcclusion()));
    public static final Block GREEN_SOFA = register("green_sofa", new SofaBlock(FabricBlockSettings.copyOf(Blocks.GREEN_WOOL).noOcclusion()));
    public static final Block RED_SOFA = register("red_sofa", new SofaBlock(FabricBlockSettings.copyOf(Blocks.RED_WOOL).noOcclusion()));
    public static final Block BLACK_SOFA = register("black_sofa", new SofaBlock(FabricBlockSettings.copyOf(Blocks.BLACK_WOOL).noOcclusion()));

    private static Block register(String id, Block block) {
        return Registry.register(BuiltInRegistries.BLOCK, new ResourceLocation(NookAndCranny.MOD_ID, id), block);
    }
}
