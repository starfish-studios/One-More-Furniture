package com.crispytwig.nookcranny.data;

import com.crispytwig.nookcranny.NookAndCranny;
import com.crispytwig.nookcranny.blocks.LampBlock;
import com.crispytwig.nookcranny.blocks.properties.ColorList;
import com.crispytwig.nookcranny.events.DyeSofa;
import com.crispytwig.nookcranny.registry.NCBlocks;
import com.crispytwig.nookcranny.registry.NCItems;
import com.crispytwig.nookcranny.registry.NCTags;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.Util;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.ShapedRecipeBuilder;
import net.minecraft.data.recipes.ShapelessRecipeBuilder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.WoolCarpetBlock;
import net.minecraft.world.level.block.state.BlockState;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;

public class NCRecipeProvider extends FabricRecipeProvider {
    public NCRecipeProvider(FabricDataOutput output) {
        super(output);
    }
    
    @Override
    public void buildRecipes(Consumer<FinishedRecipe> consumer) {
        createDrawer(consumer, NCItems.OAK_DRAWER, Items.OAK_PLANKS, Items.OAK_SLAB);
        createDrawer(consumer, NCItems.SPRUCE_DRAWER, Items.SPRUCE_PLANKS, Items.SPRUCE_SLAB);
        createDrawer(consumer, NCItems.BIRCH_DRAWER, Items.BIRCH_PLANKS, Items.BIRCH_SLAB);
        createDrawer(consumer, NCItems.JUNGLE_DRAWER, Items.JUNGLE_PLANKS, Items.JUNGLE_SLAB);
        createDrawer(consumer, NCItems.ACACIA_DRAWER, Items.ACACIA_PLANKS, Items.ACACIA_SLAB);
        createDrawer(consumer, NCItems.MANGROVE_DRAWER, Items.MANGROVE_PLANKS, Items.MANGROVE_SLAB);
        createDrawer(consumer, NCItems.BAMBOO_DRAWER, Items.BAMBOO_PLANKS, Items.BAMBOO_SLAB);
        createDrawer(consumer, NCItems.CHERRY_DRAWER, Items.CHERRY_PLANKS, Items.CHERRY_SLAB);
        createDrawer(consumer, NCItems.DARK_OAK_DRAWER, Items.DARK_OAK_PLANKS, Items.DARK_OAK_SLAB);
        createDrawer(consumer, NCItems.CRIMSON_DRAWER, Items.CRIMSON_PLANKS, Items.CRIMSON_SLAB);
        createDrawer(consumer, NCItems.WARPED_DRAWER, Items.WARPED_PLANKS, Items.WARPED_SLAB);

        createCabinet(consumer, NCItems.OAK_CABINET, Items.OAK_PLANKS, Items.OAK_SLAB);
        createCabinet(consumer, NCItems.SPRUCE_CABINET, Items.SPRUCE_PLANKS, Items.SPRUCE_SLAB);
        createCabinet(consumer, NCItems.BIRCH_CABINET, Items.BIRCH_PLANKS, Items.BIRCH_SLAB);
        createCabinet(consumer, NCItems.JUNGLE_CABINET, Items.JUNGLE_PLANKS, Items.JUNGLE_SLAB);
        createCabinet(consumer, NCItems.ACACIA_CABINET, Items.ACACIA_PLANKS, Items.ACACIA_SLAB);
        createCabinet(consumer, NCItems.MANGROVE_CABINET, Items.MANGROVE_PLANKS, Items.MANGROVE_SLAB);
        createCabinet(consumer, NCItems.BAMBOO_CABINET, Items.BAMBOO_PLANKS, Items.BAMBOO_SLAB);
        createCabinet(consumer, NCItems.CHERRY_CABINET, Items.CHERRY_PLANKS, Items.CHERRY_SLAB);
        createCabinet(consumer, NCItems.DARK_OAK_CABINET, Items.DARK_OAK_PLANKS, Items.DARK_OAK_SLAB);
        createCabinet(consumer, NCItems.CRIMSON_CABINET, Items.CRIMSON_PLANKS, Items.CRIMSON_SLAB);
        createCabinet(consumer, NCItems.WARPED_CABINET, Items.WARPED_PLANKS, Items.WARPED_SLAB);

        createLamp(consumer, Items.OAK_PLANKS, NCItems.OAK_LAMP);
        createLamp(consumer, Items.SPRUCE_PLANKS, NCItems.SPRUCE_LAMP);
        createLamp(consumer, Items.BIRCH_PLANKS, NCItems.BIRCH_LAMP);
        createLamp(consumer, Items.ACACIA_PLANKS, NCItems.ACACIA_LAMP);
        createLamp(consumer, Items.MANGROVE_PLANKS, NCItems.MANGROVE_LAMP);
        createLamp(consumer, Items.DARK_OAK_PLANKS, NCItems.DARK_OAK_LAMP);
        createLamp(consumer, Items.BAMBOO_PLANKS, NCItems.BAMBOO_LAMP);
        createLamp(consumer, Items.JUNGLE_PLANKS, NCItems.JUNGLE_LAMP);
        createLamp(consumer, Items.CHERRY_PLANKS, NCItems.CHERRY_LAMP);
        createLamp(consumer, Items.CRIMSON_PLANKS, NCItems.CRIMSON_LAMP);
        createLamp(consumer, Items.WARPED_PLANKS, NCItems.WARPED_LAMP);

        createTallStool(consumer, NCItems.OAK_TALL_STOOL, Items.OAK_SLAB);
        createTallStool(consumer, NCItems.SPRUCE_TALL_STOOL, Items.SPRUCE_SLAB);
        createTallStool(consumer, NCItems.BIRCH_TALL_STOOL, Items.BIRCH_SLAB);
        createTallStool(consumer, NCItems.ACACIA_TALL_STOOL, Items.ACACIA_SLAB);
        createTallStool(consumer, NCItems.MANGROVE_TALL_STOOL, Items.MANGROVE_SLAB);
        createTallStool(consumer, NCItems.DARK_OAK_TALL_STOOL, Items.DARK_OAK_SLAB);
        createTallStool(consumer, NCItems.BAMBOO_TALL_STOOL, Items.BAMBOO_SLAB);
        createTallStool(consumer, NCItems.JUNGLE_TALL_STOOL, Items.JUNGLE_SLAB);
        createTallStool(consumer, NCItems.CHERRY_TALL_STOOL, Items.CHERRY_SLAB);
        createTallStool(consumer, NCItems.WARPED_TALL_STOOL, Items.CRIMSON_SLAB);
        createTallStool(consumer, NCItems.CRIMSON_TALL_STOOL, Items.WARPED_SLAB);

        createChair(consumer, NCItems.OAK_CHAIR, Items.OAK_SLAB);
        createChair(consumer, NCItems.SPRUCE_CHAIR, Items.SPRUCE_SLAB);
        createChair(consumer, NCItems.BIRCH_CHAIR, Items.BIRCH_SLAB);
        createChair(consumer, NCItems.ACACIA_CHAIR, Items.ACACIA_SLAB);
        createChair(consumer, NCItems.MANGROVE_CHAIR, Items.MANGROVE_SLAB);
        createChair(consumer, NCItems.DARK_OAK_CHAIR, Items.DARK_OAK_SLAB);
        createChair(consumer, NCItems.BAMBOO_CHAIR, Items.BAMBOO_SLAB);
        createChair(consumer, NCItems.JUNGLE_CHAIR, Items.JUNGLE_SLAB);
        createChair(consumer, NCItems.CHERRY_CHAIR, Items.CHERRY_SLAB);
        createChair(consumer, NCItems.WARPED_CHAIR, Items.CRIMSON_SLAB);
        createChair(consumer, NCItems.CRIMSON_CHAIR, Items.WARPED_SLAB);

        createTable(consumer, NCItems.OAK_TABLE, Items.OAK_SLAB);
        createTable(consumer, NCItems.SPRUCE_TABLE, Items.SPRUCE_SLAB);
        createTable(consumer, NCItems.BIRCH_TABLE, Items.BIRCH_SLAB);
        createTable(consumer, NCItems.ACACIA_TABLE, Items.ACACIA_SLAB);
        createTable(consumer, NCItems.MANGROVE_TABLE, Items.MANGROVE_SLAB);
        createTable(consumer, NCItems.DARK_OAK_TABLE, Items.DARK_OAK_SLAB);
        createTable(consumer, NCItems.BAMBOO_TABLE, Items.BAMBOO_SLAB);
        createTable(consumer, NCItems.JUNGLE_TABLE, Items.JUNGLE_SLAB);
        createTable(consumer, NCItems.CHERRY_TABLE, Items.CHERRY_SLAB);
        createTable(consumer, NCItems.WARPED_TABLE, Items.CRIMSON_SLAB);
        createTable(consumer, NCItems.CRIMSON_TABLE, Items.WARPED_SLAB);

        createFan(consumer, NCItems.OAK_FAN, Items.OAK_PLANKS);
        createFan(consumer, NCItems.SPRUCE_FAN, Items.SPRUCE_PLANKS);
        createFan(consumer, NCItems.BIRCH_FAN, Items.BIRCH_PLANKS);
        createFan(consumer, NCItems.ACACIA_FAN, Items.ACACIA_PLANKS);
        createFan(consumer, NCItems.MANGROVE_FAN, Items.MANGROVE_PLANKS);
        createFan(consumer, NCItems.DARK_OAK_FAN, Items.DARK_OAK_PLANKS);
        createFan(consumer, NCItems.BAMBOO_FAN, Items.BAMBOO_PLANKS);
        createFan(consumer, NCItems.JUNGLE_FAN, Items.JUNGLE_PLANKS);
        createFan(consumer, NCItems.CHERRY_FAN, Items.CHERRY_PLANKS);
        createFan(consumer, NCItems.WARPED_FAN, Items.CRIMSON_PLANKS);
        createFan(consumer, NCItems.CRIMSON_FAN, Items.WARPED_PLANKS);

        createShelf(consumer, NCItems.OAK_SHELF, Items.OAK_SLAB);
        createShelf(consumer, NCItems.SPRUCE_SHELF, Items.SPRUCE_SLAB);
        createShelf(consumer, NCItems.BIRCH_SHELF, Items.BIRCH_SLAB);
        createShelf(consumer, NCItems.ACACIA_SHELF, Items.ACACIA_SLAB);
        createShelf(consumer, NCItems.MANGROVE_SHELF, Items.MANGROVE_SLAB);
        createShelf(consumer, NCItems.DARK_OAK_SHELF, Items.DARK_OAK_SLAB);
        createShelf(consumer, NCItems.BAMBOO_SHELF, Items.BAMBOO_SLAB);
        createShelf(consumer, NCItems.JUNGLE_SHELF, Items.JUNGLE_SLAB);
        createShelf(consumer, NCItems.CHERRY_SHELF, Items.CHERRY_SLAB);
        createShelf(consumer, NCItems.WARPED_SHELF, Items.CRIMSON_SLAB);
        createShelf(consumer, NCItems.CRIMSON_SHELF, Items.WARPED_SLAB);

        createBench(consumer, NCItems.OAK_BENCH, Items.OAK_PLANKS);
        createBench(consumer, NCItems.SPRUCE_BENCH, Items.SPRUCE_PLANKS);
        createBench(consumer, NCItems.BIRCH_BENCH, Items.BIRCH_PLANKS);
        createBench(consumer, NCItems.ACACIA_BENCH, Items.ACACIA_PLANKS);
        createBench(consumer, NCItems.MANGROVE_BENCH, Items.MANGROVE_PLANKS);
        createBench(consumer, NCItems.DARK_OAK_BENCH, Items.DARK_OAK_PLANKS);
        createBench(consumer, NCItems.BAMBOO_BENCH, Items.BAMBOO_PLANKS);
        createBench(consumer, NCItems.JUNGLE_BENCH, Items.JUNGLE_PLANKS);
        createBench(consumer, NCItems.CHERRY_BENCH, Items.CHERRY_PLANKS);
        createBench(consumer, NCItems.WARPED_BENCH, Items.CRIMSON_PLANKS);
        createBench(consumer, NCItems.CRIMSON_BENCH, Items.WARPED_PLANKS);

        createMailbox(consumer, NCItems.OAK_MAILBOX, Items.OAK_PLANKS);
        createMailbox(consumer, NCItems.SPRUCE_MAILBOX, Items.SPRUCE_PLANKS);
        createMailbox(consumer, NCItems.BIRCH_MAILBOX, Items.BIRCH_PLANKS);
        createMailbox(consumer, NCItems.ACACIA_MAILBOX, Items.ACACIA_PLANKS);
        createMailbox(consumer, NCItems.MANGROVE_MAILBOX, Items.MANGROVE_PLANKS);
        createMailbox(consumer, NCItems.DARK_OAK_MAILBOX, Items.DARK_OAK_PLANKS);
        createMailbox(consumer, NCItems.BAMBOO_MAILBOX, Items.BAMBOO_PLANKS);
        createMailbox(consumer, NCItems.JUNGLE_MAILBOX, Items.JUNGLE_PLANKS);
        createMailbox(consumer, NCItems.CHERRY_MAILBOX, Items.CHERRY_PLANKS);
        createMailbox(consumer, NCItems.WARPED_MAILBOX, Items.CRIMSON_PLANKS);
        createMailbox(consumer, NCItems.CRIMSON_MAILBOX, Items.WARPED_PLANKS);

        for (Map.Entry<Item, Item> map : WOOL_MAP.entrySet()) {
            createSofa(consumer, map.getValue(), map.getKey());
        }

        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, NCItems.SPIGOT)
                .define('N', Items.IRON_NUGGET)
                .define('I', Items.IRON_INGOT)
                .define('B', Items.WATER_BUCKET)
                .pattern(" N ")
                .pattern("BB ")
                .pattern(" I ")
                .unlockedBy("has_bucket", has( Items.WATER_BUCKET))
                .save(consumer);

        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, NCItems.COPPER_SAW)
                .define('N', Items.COPPER_INGOT)
                .define('I', Items.STICK)
                .pattern("NI")
                .pattern("NI")
                .pattern(" I")
                .unlockedBy("has_copper", has( Items.COPPER_INGOT))
                .save(consumer);


        createWindchime(consumer, NCItems.AMETHYST_WIND_CHIMES, Items.AMETHYST_SHARD);
        createWindchime(consumer, NCItems.BAMBOO_WIND_CHIMES, Items.BAMBOO_BLOCK);
        createWindchime(consumer, NCItems.BAMBOO_STRIPPED_WIND_CHIMES, Items.STRIPPED_BAMBOO_BLOCK);
        createWindchime(consumer, NCItems.BONE_WIND_CHIMES, Items.BONE);
        createWindchime(consumer, NCItems.COPPER_WIND_CHIMES, Items.COPPER_INGOT);
        createWindchime(consumer, NCItems.ECHO_SHARD_WIND_CHIMES, Items.ECHO_SHARD);
    }

    private void createWindchime(Consumer<FinishedRecipe> consumer, Item chime, Item material){
        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, chime)
                .define('W', material)
                .define('P', Items.STRING)
                .pattern("P")
                .pattern("W")
                .unlockedBy("has_string", has(Items.STRING))
                .save(consumer);
    }

    private void createMailbox(Consumer<FinishedRecipe> consumer, Item box, Item planks){
        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, box)
                .define('W', planks)
                .define('P', Items.PAPER)
                .define('I', Items.IRON_INGOT)
                .pattern("III")
                .pattern("IPI")
                .pattern(" W ")
                .unlockedBy("has_paper", has(Items.PAPER))
                .save(consumer);
    }


    private void createBench(Consumer<FinishedRecipe> consumer, Item bench, Item planks){
        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, bench, 2)
                .define('W', planks)
                .define('N', Items.IRON_NUGGET)
                .pattern("NW ")
                .pattern("NWW")
                .unlockedBy("has_planks", has(planks))
                .save(consumer);
    }


    private void createShelf(Consumer<FinishedRecipe> consumer, Item shelf, Item slab){
        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, shelf, 4)
                .define('W', slab)
                .define('S', Items.STICK)
                .pattern("WW")
                .pattern("S ")
                .unlockedBy("has_slab", has(slab))
                .save(consumer);
    }

    private void createFan(Consumer<FinishedRecipe> consumer, Item fan, Item plank){
        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, fan)
                .define('W', plank)
                .define('I', Items.IRON_INGOT)
                .pattern(" W ")
                .pattern("WIW")
                .pattern(" W ")
                .unlockedBy("has_plank", has(plank))
                .save(consumer);
    }


    private void createTable(Consumer<FinishedRecipe> consumer, Item table, Item slab){
        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, table)
                .define('W', slab)
                .define('S', Items.STICK)
                .pattern("WW")
                .pattern("SS")
                .unlockedBy("has_slab", has(slab))
                .save(consumer);
    }

    private void createChair(Consumer<FinishedRecipe> consumer, Item chair, Item slab){
        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, chair, 2)
                .define('W', slab)
                .define('S', Items.STICK)
                .pattern("W ")
                .pattern("WW")
                .pattern("SS")
                .unlockedBy("has_slab", has(slab))
                .save(consumer);
    }

    private void createSofa(Consumer<FinishedRecipe> consumer, Item sofa, Item wool){
        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, sofa, 2)
                .define('W', wool)
                .pattern("W ")
                .pattern("WW")
                .unlockedBy("has_slab", has(wool))
                .save(consumer);
    }

    private void createCabinet(Consumer<FinishedRecipe> consumer, Item drawer, Item plank, Item slab) {
        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, drawer, 1)
                .define('P', plank)
                .define('S', slab)
                .define('I', Items.IRON_NUGGET)
                .pattern("PPP")
                .pattern("SIS")
                .pattern("PPP")
                .unlockedBy("has_nugget", has(Items.IRON_NUGGET))
                .save(consumer);
    }

    private void createTallStool(Consumer<FinishedRecipe> consumer, Item tallStool, Item slab){
        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, tallStool, 2)
                .define('P', slab)
                .define('S', Items.STICK)
                .pattern("P")
                .pattern("S")
                .unlockedBy("has_slab", has(slab))
                .save(consumer);
    }

    private void createDrawer(Consumer<FinishedRecipe> consumer, Item drawer, Item plank, Item slab) {
        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, drawer, 1)
                .define('P', plank)
                .define('S', slab)
                .define('I', Items.IRON_NUGGET)
                .pattern("PSP")
                .pattern("PIP")
                .pattern("PSP")
                .unlockedBy("has_nugget", has(Items.IRON_NUGGET))
                .save(consumer);
    }

    private void createLamp(Consumer<FinishedRecipe> consumer, Item wood, Item lamp) {
        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, lamp, 2)
                .define('W', ItemTags.WOOL)
                .define('T', Items.TORCH)
                .define('S', wood)
                .pattern("W")
                .pattern("T")
                .pattern("S")
                .unlockedBy("has_torch", has(Items.TORCH))
                .save(consumer);
    }

    private static final Map<Item, Item> WOOL_MAP = Util.make(new HashMap<>(), (map) -> {
        map.put(Items.WHITE_WOOL, NCItems.WHITE_SOFA);
        map.put(Items.LIGHT_GRAY_WOOL, NCItems.LIGHT_GRAY_SOFA);
        map.put(Items.GRAY_WOOL, NCItems.GRAY_SOFA);
        map.put(Items.BLACK_WOOL, NCItems.BLACK_SOFA);
        map.put(Items.BROWN_WOOL, NCItems.BROWN_SOFA);
        map.put(Items.RED_WOOL, NCItems.RED_SOFA);
        map.put(Items.ORANGE_WOOL, NCItems.ORANGE_SOFA);
        map.put(Items.YELLOW_WOOL, NCItems.YELLOW_SOFA);
        map.put(Items.LIME_WOOL, NCItems.LIME_SOFA);
        map.put(Items.GREEN_WOOL, NCItems.GREEN_SOFA);
        map.put(Items.CYAN_WOOL, NCItems.CYAN_SOFA);
        map.put(Items.LIGHT_BLUE_WOOL, NCItems.LIGHT_BLUE_SOFA);
        map.put(Items.BLUE_WOOL, NCItems.BLUE_SOFA);
        map.put(Items.PURPLE_WOOL, NCItems.PURPLE_SOFA);
        map.put(Items.MAGENTA_WOOL, NCItems.MAGENTA_SOFA);
        map.put(Items.PINK_WOOL, NCItems.PINK_SOFA);
    });
}
