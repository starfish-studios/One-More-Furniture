package com.crispytwig.nookcranny.registry;

import com.crispytwig.nookcranny.NookAndCranny;
import com.crispytwig.nookcranny.blocks.*;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;

import static net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings.*;

public class NCBlocks {
//    public static final Block PLATE = register("plate", new PlateBlock(copyOf(Blocks.QUARTZ_BLOCK).noOcclusion().noCollission()));

    // Oak, Spruce, Birch, Jungle, Acacia, Cherry, Dark Oak, Mangrove, Bamboo

    public static final Block OAK_MAILBOX = register("oak_mailbox", new MailboxBlock(copyOf(Blocks.OAK_PLANKS).noOcclusion()));

    public static final Block OAK_SHELF = register("oak_shelf", new ShelfBlock(copyOf(Blocks.OAK_PLANKS).noOcclusion()));
    public static final Block SPRUCE_SHELF = register("spruce_shelf", new ShelfBlock(copyOf(Blocks.SPRUCE_PLANKS).noOcclusion()));
    public static final Block BIRCH_SHELF = register("birch_shelf", new ShelfBlock(copyOf(Blocks.BIRCH_PLANKS).noOcclusion()));
    public static final Block JUNGLE_SHELF = register("jungle_shelf", new ShelfBlock(copyOf(Blocks.JUNGLE_PLANKS).noOcclusion()));
    public static final Block ACACIA_SHELF = register("acacia_shelf", new ShelfBlock(copyOf(Blocks.ACACIA_PLANKS).noOcclusion()));
    public static final Block CHERRY_SHELF = register("cherry_shelf", new ShelfBlock(copyOf(Blocks.CRIMSON_PLANKS).noOcclusion()));
    public static final Block DARK_OAK_SHELF = register("dark_oak_shelf", new ShelfBlock(copyOf(Blocks.DARK_OAK_PLANKS).noOcclusion()));
    public static final Block MANGROVE_SHELF = register("mangrove_shelf", new ShelfBlock(copyOf(Blocks.WARPED_PLANKS).noOcclusion()));
    public static final Block BAMBOO_SHELF = register("bamboo_shelf", new ShelfBlock(copyOf(Blocks.BAMBOO_PLANKS).noOcclusion()));

    public static final Block OAK_NIGHTSTAND = register("oak_nightstand", new NightstandBlock(copyOf(Blocks.OAK_PLANKS).noOcclusion()));

    public static final Block OAK_TABLE = register("oak_table", new TableBlock(copyOf(Blocks.OAK_PLANKS).noOcclusion()));
    public static final Block SPRUCE_TABLE = register("spruce_table", new TableBlock(copyOf(Blocks.SPRUCE_PLANKS).noOcclusion()));
    public static final Block BIRCH_TABLE = register("birch_table", new TableBlock(copyOf(Blocks.BIRCH_PLANKS).noOcclusion()));
    public static final Block JUNGLE_TABLE = register("jungle_table", new TableBlock(copyOf(Blocks.JUNGLE_PLANKS).noOcclusion()));
    public static final Block ACACIA_TABLE = register("acacia_table", new TableBlock(copyOf(Blocks.ACACIA_PLANKS).noOcclusion()));
    public static final Block CHERRY_TABLE = register("cherry_table", new TableBlock(copyOf(Blocks.CRIMSON_PLANKS).noOcclusion()));
    public static final Block DARK_OAK_TABLE = register("dark_oak_table", new TableBlock(copyOf(Blocks.DARK_OAK_PLANKS).noOcclusion()));
    public static final Block MANGROVE_TABLE = register("mangrove_table", new TableBlock(copyOf(Blocks.WARPED_PLANKS).noOcclusion()));
    public static final Block BAMBOO_TABLE = register("bamboo_table", new TableBlock(copyOf(Blocks.BAMBOO_PLANKS).noOcclusion()));

    public static final Block OAK_CHAIR = register("oak_chair", new ChairBlock(copyOf(Blocks.OAK_PLANKS).noOcclusion()));
    public static final Block SPRUCE_CHAIR = register("spruce_chair", new ChairBlock(copyOf(Blocks.SPRUCE_PLANKS).noOcclusion()));
    public static final Block BIRCH_CHAIR = register("birch_chair", new ChairBlock(copyOf(Blocks.BIRCH_PLANKS).noOcclusion()));
    public static final Block JUNGLE_CHAIR = register("jungle_chair", new ChairBlock(copyOf(Blocks.JUNGLE_PLANKS).noOcclusion()));
    public static final Block ACACIA_CHAIR = register("acacia_chair", new ChairBlock(copyOf(Blocks.ACACIA_PLANKS).noOcclusion()));
    public static final Block CHERRY_CHAIR = register("cherry_chair", new ChairBlock(copyOf(Blocks.CRIMSON_PLANKS).noOcclusion()));
    public static final Block DARK_OAK_CHAIR = register("dark_oak_chair", new ChairBlock(copyOf(Blocks.DARK_OAK_PLANKS).noOcclusion()));
    public static final Block MANGROVE_CHAIR = register("mangrove_chair", new ChairBlock(copyOf(Blocks.WARPED_PLANKS).noOcclusion()));
    public static final Block BAMBOO_CHAIR = register("bamboo_chair", new ChairBlock(copyOf(Blocks.BAMBOO_PLANKS).noOcclusion()));


    public static final Block SPIGOT = register("spigot", new SpigotBlock(copyOf(Blocks.CAULDRON).noOcclusion().randomTicks()));

    public static final Block BLACK_SOFA = register("black_sofa", new SofaBlock(DyeColor.BLACK, copyOf(Blocks.BLACK_WOOL).noOcclusion()));
    public static final Block BLUE_SOFA = register("blue_sofa", new SofaBlock(DyeColor.BLUE, copyOf(Blocks.BLUE_WOOL).noOcclusion()));
    public static final Block BROWN_SOFA = register("brown_sofa", new SofaBlock(DyeColor.BROWN, copyOf(Blocks.BROWN_WOOL).noOcclusion()));
    public static final Block CYAN_SOFA = register("cyan_sofa", new SofaBlock(DyeColor.CYAN, copyOf(Blocks.CYAN_WOOL).noOcclusion()));
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
