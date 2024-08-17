package com.crispytwig.nookcranny.registry;

import com.crispytwig.nookcranny.NookAndCranny;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.*;

public class NCItems {
    public static final Item MOD_ICON = register("mod_icon", new Item(new FabricItemSettings().maxCount(1).rarity(Rarity.EPIC).fireproof()));

    public static final Item OAK_TABLE = register("oak_table", new BlockItem(NCBlocks.OAK_TABLE, new FabricItemSettings()));
    public static final Item SPRUCE_TABLE = register("spruce_table", new BlockItem(NCBlocks.SPRUCE_TABLE, new FabricItemSettings()));
    public static final Item BIRCH_TABLE = register("birch_table", new BlockItem(NCBlocks.BIRCH_TABLE, new FabricItemSettings()));
    public static final Item JUNGLE_TABLE = register("jungle_table", new BlockItem(NCBlocks.JUNGLE_TABLE, new FabricItemSettings()));
    public static final Item ACACIA_TABLE = register("acacia_table", new BlockItem(NCBlocks.ACACIA_TABLE, new FabricItemSettings()));
    public static final Item CHERRY_TABLE = register("cherry_table", new BlockItem(NCBlocks.CHERRY_TABLE, new FabricItemSettings()));
    public static final Item DARK_OAK_TABLE = register("dark_oak_table", new BlockItem(NCBlocks.DARK_OAK_TABLE, new FabricItemSettings()));
    public static final Item MANGROVE_TABLE = register("mangrove_table", new BlockItem(NCBlocks.MANGROVE_TABLE, new FabricItemSettings()));
    public static final Item BAMBOO_TABLE = register("bamboo_table", new BlockItem(NCBlocks.BAMBOO_TABLE, new FabricItemSettings()));

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
        return Registry.register(BuiltInRegistries.ITEM, new ResourceLocation(NookAndCranny.MOD_ID, id), item);
    }
}
