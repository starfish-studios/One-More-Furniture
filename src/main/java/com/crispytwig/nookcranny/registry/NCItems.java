package com.crispytwig.nookcranny.registry;

import com.crispytwig.nookcranny.NookAndCranny;
import com.crispytwig.nookcranny.blocks.CabinetBlock;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.*;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import static net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings.copyOf;

public class NCItems {

    public static final List<String> ITEM_NAMES = new ArrayList<>();

    public static final Item MOD_ICON = register("mod_icon", new Item(new FabricItemSettings().maxCount(1).rarity(Rarity.EPIC).fireproof()));

//    public static final Item PLATE = register("plate", new BlockItem(NCBlocks.PLATE, new FabricItemSettings()));

    public static final Item COPPER_SAW = register("copper_saw", new Item(new FabricItemSettings().maxCount(1).durability(128)));

    public static final Item OAK_DRAWER = register("oak_drawer", new BlockItem(NCBlocks.OAK_DRAWER, new FabricItemSettings()));
    public static final Item SPRUCE_DRAWER = register("spruce_drawer", new BlockItem(NCBlocks.SPRUCE_DRAWER, new FabricItemSettings()));
    public static final Item BIRCH_DRAWER = register("birch_drawer", new BlockItem(NCBlocks.BIRCH_DRAWER, new FabricItemSettings()));
    public static final Item JUNGLE_DRAWER = register("jungle_drawer", new BlockItem(NCBlocks.JUNGLE_DRAWER, new FabricItemSettings()));
    public static final Item ACACIA_DRAWER = register("acacia_drawer", new BlockItem(NCBlocks.ACACIA_DRAWER, new FabricItemSettings()));
    public static final Item CHERRY_DRAWER = register("cherry_drawer", new BlockItem(NCBlocks.CHERRY_DRAWER, new FabricItemSettings()));
    public static final Item DARK_OAK_DRAWER = register("dark_oak_drawer", new BlockItem(NCBlocks.DARK_OAK_DRAWER, new FabricItemSettings()));
    public static final Item MANGROVE_DRAWER = register("mangrove_drawer", new BlockItem(NCBlocks.MANGROVE_DRAWER, new FabricItemSettings()));
    public static final Item BAMBOO_DRAWER = register("bamboo_drawer", new BlockItem(NCBlocks.BAMBOO_DRAWER, new FabricItemSettings()));
    public static final Item CRIMSON_DRAWER = register("crimson_drawer", new BlockItem(NCBlocks.CRIMSON_DRAWER, new FabricItemSettings()));
    public static final Item WARPED_DRAWER = register("warped_drawer", new BlockItem(NCBlocks.WARPED_DRAWER, new FabricItemSettings()));

    public static final Item OAK_TALL_STOOL = register("oak_tall_stool", new BlockItem(NCBlocks.OAK_TALL_STOOL, new FabricItemSettings()));
    public static final Item SPRUCE_TALL_STOOL = register("spruce_tall_stool", new BlockItem(NCBlocks.SPRUCE_TALL_STOOL, new FabricItemSettings()));
    public static final Item BIRCH_TALL_STOOL = register("birch_tall_stool", new BlockItem(NCBlocks.BIRCH_TALL_STOOL, new FabricItemSettings()));
    public static final Item JUNGLE_TALL_STOOL = register("jungle_tall_stool", new BlockItem(NCBlocks.JUNGLE_TALL_STOOL, new FabricItemSettings()));
    public static final Item ACACIA_TALL_STOOL = register("acacia_tall_stool", new BlockItem(NCBlocks.ACACIA_TALL_STOOL, new FabricItemSettings()));
    public static final Item CHERRY_TALL_STOOL = register("cherry_tall_stool", new BlockItem(NCBlocks.CHERRY_TALL_STOOL, new FabricItemSettings()));
    public static final Item DARK_OAK_TALL_STOOL = register("dark_oak_tall_stool", new BlockItem(NCBlocks.DARK_OAK_TALL_STOOL, new FabricItemSettings()));
    public static final Item MANGROVE_TALL_STOOL = register("mangrove_tall_stool", new BlockItem(NCBlocks.MANGROVE_TALL_STOOL, new FabricItemSettings()));
    public static final Item BAMBOO_TALL_STOOL = register("bamboo_tall_stool", new BlockItem(NCBlocks.BAMBOO_TALL_STOOL, new FabricItemSettings()));
    public static final Item CRIMSON_TALL_STOOL = register("crimson_tall_stool", new BlockItem(NCBlocks.CRIMSON_TALL_STOOL, new FabricItemSettings()));
    public static final Item WARPED_TALL_STOOL = register("warped_tall_stool", new BlockItem(NCBlocks.WARPED_TALL_STOOL, new FabricItemSettings()));

    public static final Item OAK_FLOWER_BASKET = register("oak_flower_basket", new BlockItem(NCBlocks.OAK_FLOWER_BASKET, new FabricItemSettings()));

    public static final Item OAK_LAMP = register("oak_lamp", new BlockItem(NCBlocks.OAK_LAMP, new FabricItemSettings()));
    public static final Item SPRUCE_LAMP = register("spruce_lamp", new BlockItem(NCBlocks.SPRUCE_LAMP, new FabricItemSettings()));
    public static final Item BIRCH_LAMP = register("birch_lamp", new BlockItem(NCBlocks.BIRCH_LAMP, new FabricItemSettings()));
    public static final Item JUNGLE_LAMP = register("jungle_lamp", new BlockItem(NCBlocks.JUNGLE_LAMP, new FabricItemSettings()));
    public static final Item ACACIA_LAMP = register("acacia_lamp", new BlockItem(NCBlocks.ACACIA_LAMP, new FabricItemSettings()));
    public static final Item CHERRY_LAMP = register("cherry_lamp", new BlockItem(NCBlocks.CHERRY_LAMP, new FabricItemSettings()));
    public static final Item DARK_OAK_LAMP = register("dark_oak_lamp", new BlockItem(NCBlocks.DARK_OAK_LAMP, new FabricItemSettings()));
    public static final Item MANGROVE_LAMP = register("mangrove_lamp", new BlockItem(NCBlocks.MANGROVE_LAMP, new FabricItemSettings()));
    public static final Item BAMBOO_LAMP = register("bamboo_lamp", new BlockItem(NCBlocks.BAMBOO_LAMP, new FabricItemSettings()));
    public static final Item CRIMSON_LAMP = register("crimson_lamp", new BlockItem(NCBlocks.CRIMSON_LAMP, new FabricItemSettings()));
    public static final Item WARPED_LAMP = register("warped_lamp", new BlockItem(NCBlocks.WARPED_LAMP, new FabricItemSettings()));

    public static final Item OAK_MAILBOX = register("oak_mailbox", new BlockItem(NCBlocks.OAK_MAILBOX, new FabricItemSettings()));
    public static final Item SPRUCE_MAILBOX = register("spruce_mailbox", new BlockItem(NCBlocks.SPRUCE_MAILBOX, new FabricItemSettings()));
    public static final Item BIRCH_MAILBOX = register("birch_mailbox", new BlockItem(NCBlocks.BIRCH_MAILBOX, new FabricItemSettings()));
    public static final Item JUNGLE_MAILBOX = register("jungle_mailbox", new BlockItem(NCBlocks.JUNGLE_MAILBOX, new FabricItemSettings()));
    public static final Item ACACIA_MAILBOX = register("acacia_mailbox", new BlockItem(NCBlocks.ACACIA_MAILBOX, new FabricItemSettings()));
    public static final Item CHERRY_MAILBOX = register("cherry_mailbox", new BlockItem(NCBlocks.CHERRY_MAILBOX, new FabricItemSettings()));
    public static final Item DARK_OAK_MAILBOX = register("dark_oak_mailbox", new BlockItem(NCBlocks.DARK_OAK_MAILBOX, new FabricItemSettings()));
    public static final Item MANGROVE_MAILBOX = register("mangrove_mailbox", new BlockItem(NCBlocks.MANGROVE_MAILBOX, new FabricItemSettings()));
    public static final Item BAMBOO_MAILBOX = register("bamboo_mailbox", new BlockItem(NCBlocks.BAMBOO_MAILBOX, new FabricItemSettings()));
    public static final Item CRIMSON_MAILBOX = register("crimson_mailbox", new BlockItem(NCBlocks.CRIMSON_MAILBOX, new FabricItemSettings()));
    public static final Item WARPED_MAILBOX = register("warped_mailbox", new BlockItem(NCBlocks.WARPED_MAILBOX, new FabricItemSettings()));

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

    public static final Item WHITE_CURTAIN = register("white_curtain", new BlockItem(NCBlocks.WHITE_CURTAIN, new FabricItemSettings()));

    public static final Item AMETHYST_WIND_CHIMES = register("amethyst_wind_chimes", new BlockItem(NCBlocks.AMETHYST_WIND_CHIMES, new FabricItemSettings()));
    public static final Item BAMBOO_WIND_CHIMES = register("bamboo_wind_chimes", new BlockItem(NCBlocks.BAMBOO_WIND_CHIMES, new FabricItemSettings()));
    public static final Item BAMBOO_STRIPPED_WIND_CHIMES = register("bamboo_stripped_wind_chimes", new BlockItem(NCBlocks.BAMBOO_STRIPPED_WIND_CHIMES, new FabricItemSettings()));
    public static final Item BONE_WIND_CHIMES = register("bone_wind_chimes", new BlockItem(NCBlocks.BONE_WIND_CHIMES, new FabricItemSettings()));
    public static final Item COPPER_WIND_CHIMES = register("copper_wind_chimes", new BlockItem(NCBlocks.COPPER_WIND_CHIMES, new FabricItemSettings()));
    public static final Item ECHO_SHARD_WIND_CHIMES = register("echo_shard_wind_chimes", new BlockItem(NCBlocks.ECHO_SHARD_WIND_CHIMES, new FabricItemSettings()));

    public static final Item OAK_FAN = register("oak_ceiling_fan", new BlockItem(NCBlocks.OAK_FAN, new FabricItemSettings()));
    public static final Item SPRUCE_FAN = register("spruce_ceiling_fan", new BlockItem(NCBlocks.SPRUCE_FAN, new FabricItemSettings()));
    public static final Item BIRCH_FAN = register("birch_ceiling_fan", new BlockItem(NCBlocks.BIRCH_FAN, new FabricItemSettings()));
    public static final Item JUNGLE_FAN = register("jungle_ceiling_fan", new BlockItem(NCBlocks.JUNGLE_FAN, new FabricItemSettings()));
    public static final Item ACACIA_FAN = register("acacia_ceiling_fan", new BlockItem(NCBlocks.ACACIA_FAN, new FabricItemSettings()));
    public static final Item DARK_OAK_FAN = register("dark_oak_ceiling_fan", new BlockItem(NCBlocks.DARK_OAK_FAN, new FabricItemSettings()));
    public static final Item MANGROVE_FAN = register("mangrove_ceiling_fan", new BlockItem(NCBlocks.MANGROVE_FAN, new FabricItemSettings()));
    public static final Item BAMBOO_FAN = register("bamboo_ceiling_fan", new BlockItem(NCBlocks.BAMBOO_FAN, new FabricItemSettings()));
    public static final Item CHERRY_FAN = register("cherry_ceiling_fan", new BlockItem(NCBlocks.CHERRY_FAN, new FabricItemSettings()));
    public static final Item CRIMSON_FAN = register("crimson_ceiling_fan", new BlockItem(NCBlocks.CRIMSON_FAN, new FabricItemSettings()));
    public static final Item WARPED_FAN = register("warped_ceiling_fan", new BlockItem(NCBlocks.WARPED_FAN, new FabricItemSettings()));


    public static final Item OAK_CABINET = register("oak_cabinet", new BlockItem(NCBlocks.OAK_CABINET, new FabricItemSettings()));
    public static final Item SPRUCE_CABINET = register("spruce_cabinet", new BlockItem(NCBlocks.SPRUCE_CABINET, new FabricItemSettings()));
    public static final Item BIRCH_CABINET = register("birch_cabinet", new BlockItem(NCBlocks.BIRCH_CABINET, new FabricItemSettings()));
    public static final Item JUNGLE_CABINET = register("jungle_cabinet", new BlockItem(NCBlocks.JUNGLE_CABINET, new FabricItemSettings()));
    public static final Item ACACIA_CABINET = register("acacia_cabinet", new BlockItem(NCBlocks.ACACIA_CABINET, new FabricItemSettings()));
    public static final Item CHERRY_CABINET = register("cherry_cabinet", new BlockItem(NCBlocks.CHERRY_CABINET, new FabricItemSettings()));
    public static final Item DARK_OAK_CABINET = register("dark_oak_cabinet", new BlockItem(NCBlocks.DARK_OAK_CABINET, new FabricItemSettings()));
    public static final Item MANGROVE_CABINET = register("mangrove_cabinet", new BlockItem(NCBlocks.MANGROVE_CABINET, new FabricItemSettings()));
    public static final Item BAMBOO_CABINET = register("bamboo_cabinet", new BlockItem(NCBlocks.BAMBOO_CABINET, new FabricItemSettings()));
    public static final Item CRIMSON_CABINET = register("crimson_cabinet", new BlockItem(NCBlocks.CRIMSON_CABINET, new FabricItemSettings()));
    public static final Item WARPED_CABINET = register("warped_cabinet", new BlockItem(NCBlocks.WARPED_CABINET, new FabricItemSettings()));

    private static Item register(String id, Item item) {
        ITEM_NAMES.add(id);
        return Registry.register(BuiltInRegistries.ITEM, new ResourceLocation(NookAndCranny.MOD_ID, id), item);
    }
}
