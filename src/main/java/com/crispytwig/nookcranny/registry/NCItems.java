package com.crispytwig.nookcranny.registry;

import com.crispytwig.nookcranny.NookAndCranny;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.*;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class NCItems {

    public static final List<String> ITEM_NAMES = new ArrayList<>();

    public static final Item MOD_ICON = register("mod_icon", new Item(new FabricItemSettings().maxCount(1).rarity(Rarity.EPIC).fireproof()));

//    public static final Item PLATE = register("plate", new BlockItem(NCBlocks.PLATE, new FabricItemSettings()));

    public static final Item COPPER_SAW = register("copper_saw", new Item(new FabricItemSettings().maxCount(1).durability(128)));

    public static final Item OAK_DRAWER = register("oak_drawer", new BlockItem(NCBlocks.OAK_DRAWER, new FabricItemSettings()));

    public static final Item OAK_TALL_STOOL = register("oak_tall_stool", new BlockItem(NCBlocks.OAK_TALL_STOOL, new FabricItemSettings()));

    public static final Item OAK_FLOWER_BASKET = register("oak_flower_basket", new BlockItem(NCBlocks.OAK_FLOWER_BASKET, new FabricItemSettings()));

    public static final Item OAK_LAMP = register("oak_lamp", new BlockItem(NCBlocks.OAK_LAMP, new FabricItemSettings()));

    public static final Item OAK_MAILBOX = register("oak_mailbox", new BlockItem(NCBlocks.OAK_MAILBOX, new FabricItemSettings()));

    public static final Item OAK_NIGHTSTAND = register("oak_nightstand", new BlockItem(NCBlocks.OAK_NIGHTSTAND, new FabricItemSettings()));
    public static final Item SPRUCE_NIGHTSTAND = register("spruce_nightstand", new BlockItem(NCBlocks.SPRUCE_NIGHTSTAND, new FabricItemSettings()));
    public static final Item BIRCH_NIGHTSTAND = register("birch_nightstand", new BlockItem(NCBlocks.BIRCH_NIGHTSTAND, new FabricItemSettings()));
    public static final Item JUNGLE_NIGHTSTAND = register("jungle_nightstand", new BlockItem(NCBlocks.JUNGLE_NIGHTSTAND, new FabricItemSettings()));
    public static final Item ACACIA_NIGHTSTAND = register("acacia_nightstand", new BlockItem(NCBlocks.ACACIA_NIGHTSTAND, new FabricItemSettings()));
    public static final Item CHERRY_NIGHTSTAND = register("cherry_nightstand", new BlockItem(NCBlocks.CHERRY_NIGHTSTAND, new FabricItemSettings()));
    public static final Item DARK_OAK_NIGHTSTAND = register("dark_oak_nightstand", new BlockItem(NCBlocks.DARK_OAK_NIGHTSTAND, new FabricItemSettings()));
    public static final Item MANGROVE_NIGHTSTAND = register("mangrove_nightstand", new BlockItem(NCBlocks.MANGROVE_NIGHTSTAND, new FabricItemSettings()));
    public static final Item BAMBOO_NIGHTSTAND = register("bamboo_nightstand", new BlockItem(NCBlocks.BAMBOO_NIGHTSTAND, new FabricItemSettings()));
    public static final Item CRIMSON_NIGHTSTAND = register("crimson_nightstand", new BlockItem(NCBlocks.CRIMSON_NIGHTSTAND, new FabricItemSettings()));
    public static final Item WARPED_NIGHTSTAND = register("warped_nightstand", new BlockItem(NCBlocks.WARPED_NIGHTSTAND, new FabricItemSettings()));

    public static final Item OAK_BENCH = register("oak_bench", new BlockItem(NCBlocks.OAK_BENCH, new FabricItemSettings()));
    public static final Item SPRUCE_BENCH = register("spruce_bench", new BlockItem(NCBlocks.SPRUCE_BENCH, new FabricItemSettings()));
    public static final Item BIRCH_BENCH = register("birch_bench", new BlockItem(NCBlocks.BIRCH_BENCH, new FabricItemSettings()));
    public static final Item JUNGLE_BENCH = register("jungle_bench", new BlockItem(NCBlocks.JUNGLE_BENCH, new FabricItemSettings()));
    public static final Item ACACIA_BENCH = register("acacia_bench", new BlockItem(NCBlocks.ACACIA_BENCH, new FabricItemSettings()));
    public static final Item CHERRY_BENCH = register("cherry_bench", new BlockItem(NCBlocks.CHERRY_BENCH, new FabricItemSettings()));
    public static final Item DARK_OAK_BENCH = register("dark_oak_bench", new BlockItem(NCBlocks.DARK_OAK_BENCH, new FabricItemSettings()));
    public static final Item MANGROVE_BENCH = register("mangrove_bench", new BlockItem(NCBlocks.MANGROVE_BENCH, new FabricItemSettings()));
    public static final Item BAMBOO_BENCH = register("bamboo_bench", new BlockItem(NCBlocks.BAMBOO_BENCH, new FabricItemSettings()));
    public static final Item CRIMSON_BENCH = register("crimson_bench", new BlockItem(NCBlocks.CRIMSON_BENCH, new FabricItemSettings()));
    public static final Item WARPED_BENCH = register("warped_bench", new BlockItem(NCBlocks.WARPED_BENCH, new FabricItemSettings()));

    public static final Item OAK_SHELF = register("oak_shelf", new BlockItem(NCBlocks.OAK_SHELF, new FabricItemSettings()));
    public static final Item SPRUCE_SHELF = register("spruce_shelf", new BlockItem(NCBlocks.SPRUCE_SHELF, new FabricItemSettings()));
    public static final Item BIRCH_SHELF = register("birch_shelf", new BlockItem(NCBlocks.BIRCH_SHELF, new FabricItemSettings()));
    public static final Item JUNGLE_SHELF = register("jungle_shelf", new BlockItem(NCBlocks.JUNGLE_SHELF, new FabricItemSettings()));
    public static final Item ACACIA_SHELF = register("acacia_shelf", new BlockItem(NCBlocks.ACACIA_SHELF, new FabricItemSettings()));
    public static final Item CHERRY_SHELF = register("cherry_shelf", new BlockItem(NCBlocks.CHERRY_SHELF, new FabricItemSettings()));
    public static final Item DARK_OAK_SHELF = register("dark_oak_shelf", new BlockItem(NCBlocks.DARK_OAK_SHELF, new FabricItemSettings()));
    public static final Item MANGROVE_SHELF = register("mangrove_shelf", new BlockItem(NCBlocks.MANGROVE_SHELF, new FabricItemSettings()));
    public static final Item BAMBOO_SHELF = register("bamboo_shelf", new BlockItem(NCBlocks.BAMBOO_SHELF, new FabricItemSettings()));
    public static final Item CRIMSON_SHELF = register("crimson_shelf", new BlockItem(NCBlocks.CRIMSON_SHELF, new FabricItemSettings()));
    public static final Item WARPED_SHELF = register("warped_shelf", new BlockItem(NCBlocks.WARPED_SHELF, new FabricItemSettings()));

    public static final Item OAK_TABLE = register("oak_table", new BlockItem(NCBlocks.OAK_TABLE, new FabricItemSettings()));
    public static final Item SPRUCE_TABLE = register("spruce_table", new BlockItem(NCBlocks.SPRUCE_TABLE, new FabricItemSettings()));
    public static final Item BIRCH_TABLE = register("birch_table", new BlockItem(NCBlocks.BIRCH_TABLE, new FabricItemSettings()));
    public static final Item JUNGLE_TABLE = register("jungle_table", new BlockItem(NCBlocks.JUNGLE_TABLE, new FabricItemSettings()));
    public static final Item ACACIA_TABLE = register("acacia_table", new BlockItem(NCBlocks.ACACIA_TABLE, new FabricItemSettings()));
    public static final Item CHERRY_TABLE = register("cherry_table", new BlockItem(NCBlocks.CHERRY_TABLE, new FabricItemSettings()));
    public static final Item DARK_OAK_TABLE = register("dark_oak_table", new BlockItem(NCBlocks.DARK_OAK_TABLE, new FabricItemSettings()));
    public static final Item MANGROVE_TABLE = register("mangrove_table", new BlockItem(NCBlocks.MANGROVE_TABLE, new FabricItemSettings()));
    public static final Item BAMBOO_TABLE = register("bamboo_table", new BlockItem(NCBlocks.BAMBOO_TABLE, new FabricItemSettings()));
    public static final Item CRIMSON_TABLE = register("crimson_table", new BlockItem(NCBlocks.CRIMSON_TABLE, new FabricItemSettings()));
    public static final Item WARPED_TABLE = register("warped_table", new BlockItem(NCBlocks.WARPED_TABLE, new FabricItemSettings()));

    public static final Item OAK_CHAIR = register("oak_chair", new BlockItem(NCBlocks.OAK_CHAIR, new FabricItemSettings()));
    public static final Item SPRUCE_CHAIR = register("spruce_chair", new BlockItem(NCBlocks.SPRUCE_CHAIR, new FabricItemSettings()));
    public static final Item BIRCH_CHAIR = register("birch_chair", new BlockItem(NCBlocks.BIRCH_CHAIR, new FabricItemSettings()));
    public static final Item JUNGLE_CHAIR = register("jungle_chair", new BlockItem(NCBlocks.JUNGLE_CHAIR, new FabricItemSettings()));
    public static final Item ACACIA_CHAIR = register("acacia_chair", new BlockItem(NCBlocks.ACACIA_CHAIR, new FabricItemSettings()));
    public static final Item CHERRY_CHAIR = register("cherry_chair", new BlockItem(NCBlocks.CHERRY_CHAIR, new FabricItemSettings()));
    public static final Item DARK_OAK_CHAIR = register("dark_oak_chair", new BlockItem(NCBlocks.DARK_OAK_CHAIR, new FabricItemSettings()));
    public static final Item MANGROVE_CHAIR = register("mangrove_chair", new BlockItem(NCBlocks.MANGROVE_CHAIR, new FabricItemSettings()));
    public static final Item BAMBOO_CHAIR = register("bamboo_chair", new BlockItem(NCBlocks.BAMBOO_CHAIR, new FabricItemSettings()));
    public static final Item CRIMSON_CHAIR = register("crimson_chair", new BlockItem(NCBlocks.CRIMSON_CHAIR, new FabricItemSettings()));
    public static final Item WARPED_CHAIR = register("warped_chair", new BlockItem(NCBlocks.WARPED_CHAIR, new FabricItemSettings()));

    public static final Item SPIGOT = register("spigot", new BlockItem(NCBlocks.SPIGOT, new FabricItemSettings()));

    public static final Item BLACK_SOFA = register("black_sofa", new BlockItem(NCBlocks.BLACK_SOFA, new FabricItemSettings()));
    public static final Item BLUE_SOFA = register("blue_sofa", new BlockItem(NCBlocks.BLUE_SOFA, new FabricItemSettings()));
    public static final Item BROWN_SOFA = register("brown_sofa", new BlockItem(NCBlocks.BROWN_SOFA, new FabricItemSettings()));
    public static final Item CYAN_SOFA = register("cyan_sofa", new BlockItem(NCBlocks.CYAN_SOFA, new FabricItemSettings()));
    public static final Item GRAY_SOFA = register("gray_sofa", new BlockItem(NCBlocks.GRAY_SOFA, new FabricItemSettings()));
    public static final Item GREEN_SOFA = register("green_sofa", new BlockItem(NCBlocks.GREEN_SOFA, new FabricItemSettings()));
    public static final Item LIGHT_BLUE_SOFA = register("light_blue_sofa", new BlockItem(NCBlocks.LIGHT_BLUE_SOFA, new FabricItemSettings()));
    public static final Item LIGHT_GRAY_SOFA = register("light_gray_sofa", new BlockItem(NCBlocks.LIGHT_GRAY_SOFA, new FabricItemSettings()));
    public static final Item LIME_SOFA = register("lime_sofa", new BlockItem(NCBlocks.LIME_SOFA, new FabricItemSettings()));
    public static final Item MAGENTA_SOFA = register("magenta_sofa", new BlockItem(NCBlocks.MAGENTA_SOFA, new FabricItemSettings()));
    public static final Item ORANGE_SOFA = register("orange_sofa", new BlockItem(NCBlocks.ORANGE_SOFA, new FabricItemSettings()));
    public static final Item PINK_SOFA = register("pink_sofa", new BlockItem(NCBlocks.PINK_SOFA, new FabricItemSettings()));
    public static final Item PURPLE_SOFA = register("purple_sofa", new BlockItem(NCBlocks.PURPLE_SOFA, new FabricItemSettings()));
    public static final Item RED_SOFA = register("red_sofa", new BlockItem(NCBlocks.RED_SOFA, new FabricItemSettings()));
    public static final Item WHITE_SOFA = register("white_sofa", new BlockItem(NCBlocks.WHITE_SOFA, new FabricItemSettings()));
    public static final Item YELLOW_SOFA = register("yellow_sofa", new BlockItem(NCBlocks.YELLOW_SOFA, new FabricItemSettings()));

    private static Item register(String id, Item item) {
        ITEM_NAMES.add(id);
        return Registry.register(BuiltInRegistries.ITEM, new ResourceLocation(NookAndCranny.MOD_ID, id), item);
    }
}
