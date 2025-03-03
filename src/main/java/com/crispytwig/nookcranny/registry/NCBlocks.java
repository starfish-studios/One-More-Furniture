package com.crispytwig.nookcranny.registry;

import com.crispytwig.nookcranny.NookAndCranny;
import com.crispytwig.nookcranny.blocks.*;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;

import java.util.ArrayList;
import java.util.List;

import static net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings.*;

public class NCBlocks {
//    public static final Block PLATE = register("plate", new PlateBlock(copyOf(Blocks.QUARTZ_BLOCK).noOcclusion().noCollission()));

    // Oak, Spruce, Birch, Jungle, Acacia, Cherry, Dark Oak, Mangrove, Bamboo, Crimson, Warped

    //For Datagen
    public static final List<String> BLOCK_NAMES = new ArrayList<>();
    public static final List<Block> BLOCKS = new ArrayList<>();

    public static final Block OAK_DRAWER = register("oak_drawer", new DrawerBlock(copyOf(Blocks.OAK_PLANKS).noOcclusion()));
    public static final Block SPRUCE_DRAWER = register("spruce_drawer", new DrawerBlock(copyOf(Blocks.SPRUCE_PLANKS).noOcclusion()));
    public static final Block BIRCH_DRAWER = register("birch_drawer", new DrawerBlock(copyOf(Blocks.BIRCH_PLANKS).noOcclusion()));
    public static final Block JUNGLE_DRAWER = register("jungle_drawer", new DrawerBlock(copyOf(Blocks.JUNGLE_PLANKS).noOcclusion()));
    public static final Block ACACIA_DRAWER = register("acacia_drawer", new DrawerBlock(copyOf(Blocks.ACACIA_PLANKS).noOcclusion()));
    public static final Block CHERRY_DRAWER = register("cherry_drawer", new DrawerBlock(copyOf(Blocks.CRIMSON_PLANKS).noOcclusion()));
    public static final Block DARK_OAK_DRAWER = register("dark_oak_drawer", new DrawerBlock(copyOf(Blocks.DARK_OAK_PLANKS).noOcclusion()));
    public static final Block MANGROVE_DRAWER = register("mangrove_drawer", new DrawerBlock(copyOf(Blocks.WARPED_PLANKS).noOcclusion()));
    public static final Block BAMBOO_DRAWER = register("bamboo_drawer", new DrawerBlock(copyOf(Blocks.BAMBOO_PLANKS).noOcclusion()));
    public static final Block CRIMSON_DRAWER = register("crimson_drawer", new DrawerBlock(copyOf(Blocks.CRIMSON_PLANKS).noOcclusion()));
    public static final Block WARPED_DRAWER = register("warped_drawer", new DrawerBlock(copyOf(Blocks.WARPED_PLANKS).noOcclusion()));

    public static final Block OAK_TALL_STOOL = register("oak_tall_stool", new TallStoolBlock(copyOf(Blocks.OAK_PLANKS).noOcclusion()));
    public static final Block SPRUCE_TALL_STOOL = register("spruce_tall_stool", new TallStoolBlock(copyOf(Blocks.SPRUCE_PLANKS).noOcclusion()));
    public static final Block BIRCH_TALL_STOOL = register("birch_tall_stool", new TallStoolBlock(copyOf(Blocks.BIRCH_PLANKS).noOcclusion()));
    public static final Block JUNGLE_TALL_STOOL = register("jungle_tall_stool", new TallStoolBlock(copyOf(Blocks.JUNGLE_PLANKS).noOcclusion()));
    public static final Block ACACIA_TALL_STOOL = register("acacia_tall_stool", new TallStoolBlock(copyOf(Blocks.ACACIA_PLANKS).noOcclusion()));
    public static final Block CHERRY_TALL_STOOL = register("cherry_tall_stool", new TallStoolBlock(copyOf(Blocks.CRIMSON_PLANKS).noOcclusion()));
    public static final Block DARK_OAK_TALL_STOOL = register("dark_oak_tall_stool", new TallStoolBlock(copyOf(Blocks.DARK_OAK_PLANKS).noOcclusion()));
    public static final Block MANGROVE_TALL_STOOL = register("mangrove_tall_stool", new TallStoolBlock(copyOf(Blocks.WARPED_PLANKS).noOcclusion()));
    public static final Block BAMBOO_TALL_STOOL = register("bamboo_tall_stool", new TallStoolBlock(copyOf(Blocks.BAMBOO_PLANKS).noOcclusion()));
    public static final Block CRIMSON_TALL_STOOL = register("crimson_tall_stool", new TallStoolBlock(copyOf(Blocks.CRIMSON_PLANKS).noOcclusion()));
    public static final Block WARPED_TALL_STOOL = register("warped_tall_stool", new TallStoolBlock(copyOf(Blocks.WARPED_PLANKS).noOcclusion()));

    public static final Block OAK_BENCH = register("oak_bench", new BenchBlock(copyOf(Blocks.OAK_PLANKS).noOcclusion()));
    public static final Block SPRUCE_BENCH = register("spruce_bench", new BenchBlock(copyOf(Blocks.SPRUCE_PLANKS).noOcclusion()));
    public static final Block BIRCH_BENCH = register("birch_bench", new BenchBlock(copyOf(Blocks.BIRCH_PLANKS).noOcclusion()));
    public static final Block JUNGLE_BENCH = register("jungle_bench", new BenchBlock(copyOf(Blocks.JUNGLE_PLANKS).noOcclusion()));
    public static final Block ACACIA_BENCH = register("acacia_bench", new BenchBlock(copyOf(Blocks.ACACIA_PLANKS).noOcclusion()));
    public static final Block CHERRY_BENCH = register("cherry_bench", new BenchBlock(copyOf(Blocks.CRIMSON_PLANKS).noOcclusion()));
    public static final Block DARK_OAK_BENCH = register("dark_oak_bench", new BenchBlock(copyOf(Blocks.DARK_OAK_PLANKS).noOcclusion()));
    public static final Block MANGROVE_BENCH = register("mangrove_bench", new BenchBlock(copyOf(Blocks.WARPED_PLANKS).noOcclusion()));
    public static final Block BAMBOO_BENCH = register("bamboo_bench", new BenchBlock(copyOf(Blocks.BAMBOO_PLANKS).noOcclusion()));
    public static final Block CRIMSON_BENCH = register("crimson_bench", new BenchBlock(copyOf(Blocks.CRIMSON_PLANKS).noOcclusion()));
    public static final Block WARPED_BENCH = register("warped_bench", new BenchBlock(copyOf(Blocks.WARPED_PLANKS).noOcclusion()));

    public static final Block OAK_FLOWER_BASKET = register("oak_flower_basket", new FlowerBasketBlock(copyOf(Blocks.OAK_PLANKS).noOcclusion()));
    public static final Block SPRUCE_FLOWER_BASKET = register("spruce_flower_basket", new FlowerBasketBlock(copyOf(Blocks.SPRUCE_PLANKS).noOcclusion()));
    public static final Block BIRCH_FLOWER_BASKET = register("birch_flower_basket", new FlowerBasketBlock(copyOf(Blocks.BIRCH_PLANKS).noOcclusion()));
    public static final Block JUNGLE_FLOWER_BASKET = register("jungle_flower_basket", new FlowerBasketBlock(copyOf(Blocks.JUNGLE_PLANKS).noOcclusion()));
    public static final Block ACACIA_FLOWER_BASKET = register("acacia_flower_basket", new FlowerBasketBlock(copyOf(Blocks.ACACIA_PLANKS).noOcclusion()));
    public static final Block CHERRY_FLOWER_BASKET = register("cherry_flower_basket", new FlowerBasketBlock(copyOf(Blocks.CRIMSON_PLANKS).noOcclusion()));
    public static final Block DARK_OAK_FLOWER_BASKET = register("dark_oak_flower_basket", new FlowerBasketBlock(copyOf(Blocks.DARK_OAK_PLANKS).noOcclusion()));
    public static final Block MANGROVE_FLOWER_BASKET = register("mangrove_flower_basket", new FlowerBasketBlock(copyOf(Blocks.WARPED_PLANKS).noOcclusion()));
    public static final Block BAMBOO_FLOWER_BASKET = register("bamboo_flower_basket", new FlowerBasketBlock(copyOf(Blocks.BAMBOO_PLANKS).noOcclusion()));
    public static final Block CRIMSON_FLOWER_BASKET = register("crimson_flower_basket", new FlowerBasketBlock(copyOf(Blocks.CRIMSON_PLANKS).noOcclusion()));
    public static final Block WARPED_FLOWER_BASKET = register("warped_flower_basket", new FlowerBasketBlock(copyOf(Blocks.WARPED_PLANKS).noOcclusion()));

    public static final Block OAK_LAMP = register("oak_lamp", new LampBlock(copyOf(Blocks.TORCH).noOcclusion().lightLevel(state -> state.getValue(LampBlock.LIT) ? 15 : 0)));
    public static final Block SPRUCE_LAMP = register("spruce_lamp", new LampBlock(copyOf(Blocks.TORCH).noOcclusion().lightLevel(state -> state.getValue(LampBlock.LIT) ? 15 : 0)));
    public static final Block BIRCH_LAMP = register("birch_lamp", new LampBlock(copyOf(Blocks.TORCH).noOcclusion().lightLevel(state -> state.getValue(LampBlock.LIT) ? 15 : 0)));
    public static final Block JUNGLE_LAMP = register("jungle_lamp", new LampBlock(copyOf(Blocks.TORCH).noOcclusion().lightLevel(state -> state.getValue(LampBlock.LIT) ? 15 : 0)));
    public static final Block ACACIA_LAMP = register("acacia_lamp", new LampBlock(copyOf(Blocks.TORCH).noOcclusion().lightLevel(state -> state.getValue(LampBlock.LIT) ? 15 : 0)));
    public static final Block CHERRY_LAMP = register("cherry_lamp", new LampBlock(copyOf(Blocks.TORCH).noOcclusion().lightLevel(state -> state.getValue(LampBlock.LIT) ? 15 : 0)));
    public static final Block DARK_OAK_LAMP = register("dark_oak_lamp", new LampBlock(copyOf(Blocks.TORCH).noOcclusion().lightLevel(state -> state.getValue(LampBlock.LIT) ? 15 : 0)));
    public static final Block MANGROVE_LAMP = register("mangrove_lamp", new LampBlock(copyOf(Blocks.TORCH).noOcclusion().lightLevel(state -> state.getValue(LampBlock.LIT) ? 15 : 0)));
    public static final Block BAMBOO_LAMP = register("bamboo_lamp", new LampBlock(copyOf(Blocks.TORCH).noOcclusion().lightLevel(state -> state.getValue(LampBlock.LIT) ? 15 : 0)));
    public static final Block CRIMSON_LAMP = register("crimson_lamp", new LampBlock(copyOf(Blocks.TORCH).noOcclusion().lightLevel(state -> state.getValue(LampBlock.LIT) ? 15 : 0)));
    public static final Block WARPED_LAMP = register("warped_lamp", new LampBlock(copyOf(Blocks.TORCH).noOcclusion().lightLevel(state -> state.getValue(LampBlock.LIT) ? 15 : 0)));

    public static final Block OAK_MAILBOX = register("oak_mailbox", new MailboxBlock(copyOf(Blocks.OAK_PLANKS).noOcclusion()));
    public static final Block SPRUCE_MAILBOX = register("spruce_mailbox", new MailboxBlock(copyOf(Blocks.OAK_PLANKS).noOcclusion()));
    public static final Block BIRCH_MAILBOX = register("birch_mailbox", new MailboxBlock(copyOf(Blocks.OAK_PLANKS).noOcclusion()));
    public static final Block JUNGLE_MAILBOX = register("jungle_mailbox", new MailboxBlock(copyOf(Blocks.OAK_PLANKS).noOcclusion()));
    public static final Block ACACIA_MAILBOX = register("acacia_mailbox", new MailboxBlock(copyOf(Blocks.OAK_PLANKS).noOcclusion()));
    public static final Block CHERRY_MAILBOX = register("cherry_mailbox", new MailboxBlock(copyOf(Blocks.OAK_PLANKS).noOcclusion()));
    public static final Block DARK_OAK_MAILBOX = register("dark_oak_mailbox", new MailboxBlock(copyOf(Blocks.OAK_PLANKS).noOcclusion()));
    public static final Block MANGROVE_MAILBOX = register("mangrove_mailbox", new MailboxBlock(copyOf(Blocks.OAK_PLANKS).noOcclusion()));
    public static final Block BAMBOO_MAILBOX = register("bamboo_mailbox", new MailboxBlock(copyOf(Blocks.OAK_PLANKS).noOcclusion()));
    public static final Block CRIMSON_MAILBOX = register("crimson_mailbox", new MailboxBlock(copyOf(Blocks.OAK_PLANKS).noOcclusion()));
    public static final Block WARPED_MAILBOX = register("warped_mailbox", new MailboxBlock(copyOf(Blocks.OAK_PLANKS).noOcclusion()));

    public static final Block OAK_SHELF = register("oak_shelf", new ShelfBlock(copyOf(Blocks.OAK_PLANKS).noOcclusion()));
    public static final Block SPRUCE_SHELF = register("spruce_shelf", new ShelfBlock(copyOf(Blocks.SPRUCE_PLANKS).noOcclusion()));
    public static final Block BIRCH_SHELF = register("birch_shelf", new ShelfBlock(copyOf(Blocks.BIRCH_PLANKS).noOcclusion()));
    public static final Block JUNGLE_SHELF = register("jungle_shelf", new ShelfBlock(copyOf(Blocks.JUNGLE_PLANKS).noOcclusion()));
    public static final Block ACACIA_SHELF = register("acacia_shelf", new ShelfBlock(copyOf(Blocks.ACACIA_PLANKS).noOcclusion()));
    public static final Block CHERRY_SHELF = register("cherry_shelf", new ShelfBlock(copyOf(Blocks.CRIMSON_PLANKS).noOcclusion()));
    public static final Block DARK_OAK_SHELF = register("dark_oak_shelf", new ShelfBlock(copyOf(Blocks.DARK_OAK_PLANKS).noOcclusion()));
    public static final Block MANGROVE_SHELF = register("mangrove_shelf", new ShelfBlock(copyOf(Blocks.WARPED_PLANKS).noOcclusion()));
    public static final Block BAMBOO_SHELF = register("bamboo_shelf", new ShelfBlock(copyOf(Blocks.BAMBOO_PLANKS).noOcclusion()));
    public static final Block CRIMSON_SHELF = register("crimson_shelf", new ShelfBlock(copyOf(Blocks.CRIMSON_PLANKS).noOcclusion()));
    public static final Block WARPED_SHELF = register("warped_shelf", new ShelfBlock(copyOf(Blocks.WARPED_PLANKS).noOcclusion()));

    public static final Block OAK_NIGHTSTAND = register("oak_nightstand", new NightstandBlock(copyOf(Blocks.OAK_PLANKS).noOcclusion()));
    public static final Block SPRUCE_NIGHTSTAND = register("spruce_nightstand", new NightstandBlock(copyOf(Blocks.SPRUCE_PLANKS).noOcclusion()));
    public static final Block BIRCH_NIGHTSTAND = register("birch_nightstand", new NightstandBlock(copyOf(Blocks.BIRCH_PLANKS).noOcclusion()));
    public static final Block JUNGLE_NIGHTSTAND = register("jungle_nightstand", new NightstandBlock(copyOf(Blocks.JUNGLE_PLANKS).noOcclusion()));
    public static final Block ACACIA_NIGHTSTAND = register("acacia_nightstand", new NightstandBlock(copyOf(Blocks.ACACIA_PLANKS).noOcclusion()));
    public static final Block CHERRY_NIGHTSTAND = register("cherry_nightstand", new NightstandBlock(copyOf(Blocks.CHERRY_PLANKS).noOcclusion()));
    public static final Block DARK_OAK_NIGHTSTAND = register("dark_oak_nightstand", new NightstandBlock(copyOf(Blocks.DARK_OAK_PLANKS).noOcclusion()));
    public static final Block MANGROVE_NIGHTSTAND = register("mangrove_nightstand", new NightstandBlock(copyOf(Blocks.MANGROVE_PLANKS).noOcclusion()));
    public static final Block BAMBOO_NIGHTSTAND = register("bamboo_nightstand", new NightstandBlock(copyOf(Blocks.BAMBOO_PLANKS).noOcclusion()));
    public static final Block CRIMSON_NIGHTSTAND = register("crimson_nightstand", new NightstandBlock(copyOf(Blocks.CRIMSON_PLANKS).noOcclusion()));
    public static final Block WARPED_NIGHTSTAND = register("warped_nightstand", new NightstandBlock(copyOf(Blocks.WARPED_PLANKS).noOcclusion()));

    public static final Block OAK_TABLE = register("oak_table", new TableBlock(copyOf(Blocks.OAK_PLANKS).noOcclusion()));
    public static final Block SPRUCE_TABLE = register("spruce_table", new TableBlock(copyOf(Blocks.SPRUCE_PLANKS).noOcclusion()));
    public static final Block BIRCH_TABLE = register("birch_table", new TableBlock(copyOf(Blocks.BIRCH_PLANKS).noOcclusion()));
    public static final Block JUNGLE_TABLE = register("jungle_table", new TableBlock(copyOf(Blocks.JUNGLE_PLANKS).noOcclusion()));
    public static final Block ACACIA_TABLE = register("acacia_table", new TableBlock(copyOf(Blocks.ACACIA_PLANKS).noOcclusion()));
    public static final Block CHERRY_TABLE = register("cherry_table", new TableBlock(copyOf(Blocks.CRIMSON_PLANKS).noOcclusion()));
    public static final Block DARK_OAK_TABLE = register("dark_oak_table", new TableBlock(copyOf(Blocks.DARK_OAK_PLANKS).noOcclusion()));
    public static final Block MANGROVE_TABLE = register("mangrove_table", new TableBlock(copyOf(Blocks.WARPED_PLANKS).noOcclusion()));
    public static final Block BAMBOO_TABLE = register("bamboo_table", new TableBlock(copyOf(Blocks.BAMBOO_PLANKS).noOcclusion()));
    public static final Block CRIMSON_TABLE = register("crimson_table", new TableBlock(copyOf(Blocks.CRIMSON_PLANKS).noOcclusion()));
    public static final Block WARPED_TABLE = register("warped_table", new TableBlock(copyOf(Blocks.WARPED_PLANKS).noOcclusion()));

    public static final Block OAK_CHAIR = register("oak_chair", new ChairBlock(copyOf(Blocks.OAK_PLANKS).noOcclusion()));
    public static final Block SPRUCE_CHAIR = register("spruce_chair", new ChairBlock(copyOf(Blocks.SPRUCE_PLANKS).noOcclusion()));
    public static final Block BIRCH_CHAIR = register("birch_chair", new ChairBlock(copyOf(Blocks.BIRCH_PLANKS).noOcclusion()));
    public static final Block JUNGLE_CHAIR = register("jungle_chair", new ChairBlock(copyOf(Blocks.JUNGLE_PLANKS).noOcclusion()));
    public static final Block ACACIA_CHAIR = register("acacia_chair", new ChairBlock(copyOf(Blocks.ACACIA_PLANKS).noOcclusion()));
    public static final Block CHERRY_CHAIR = register("cherry_chair", new ChairBlock(copyOf(Blocks.CRIMSON_PLANKS).noOcclusion()));
    public static final Block DARK_OAK_CHAIR = register("dark_oak_chair", new ChairBlock(copyOf(Blocks.DARK_OAK_PLANKS).noOcclusion()));
    public static final Block MANGROVE_CHAIR = register("mangrove_chair", new ChairBlock(copyOf(Blocks.WARPED_PLANKS).noOcclusion()));
    public static final Block BAMBOO_CHAIR = register("bamboo_chair", new ChairBlock(copyOf(Blocks.BAMBOO_PLANKS).noOcclusion()));
    public static final Block CRIMSON_CHAIR = register("crimson_chair", new ChairBlock(copyOf(Blocks.CRIMSON_PLANKS).noOcclusion()));
    public static final Block WARPED_CHAIR = register("warped_chair", new ChairBlock(copyOf(Blocks.WARPED_PLANKS).noOcclusion()));


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
        BLOCK_NAMES.add(id);
        BLOCKS.add(block);
        return Registry.register(BuiltInRegistries.BLOCK, new ResourceLocation(NookAndCranny.MOD_ID, id), block);
    }
}
