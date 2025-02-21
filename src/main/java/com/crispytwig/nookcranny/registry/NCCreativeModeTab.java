package com.crispytwig.nookcranny.registry;

import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTab;

import static com.crispytwig.nookcranny.NookAndCranny.MOD_ID;
import static com.crispytwig.nookcranny.registry.NCItems.*;

public class NCCreativeModeTab {

    @SuppressWarnings("unused")
    public static final CreativeModeTab ITEM_GROUP = register(MOD_ID, FabricItemGroup.builder()
                    .icon(MOD_ICON::getDefaultInstance)
                    .title(Component.translatable("itemGroup.nookcranny.tab"))
                    .displayItems((featureFlagSet, output) -> {
                        // White, Light Gray, Gray, Black, Brown, Red, Orange, Yellow, Lime, Green, Cyan, Light Blue, Blue, Purple, Magenta, Pink
                        output.accept(COPPER_SAW);
                        output.accept(OAK_DRAWER);
                        output.accept(OAK_TALL_STOOL);

                        output.accept(SPIGOT);
//        output.accept(PLATE);

                        output.accept(OAK_FLOWER_BASKET);
                        output.accept(OAK_LAMP);
                        output.accept(OAK_MAILBOX);

                        output.accept(OAK_NIGHTSTAND);
                        output.accept(SPRUCE_NIGHTSTAND);
                        output.accept(BIRCH_NIGHTSTAND);
                        output.accept(JUNGLE_NIGHTSTAND);
                        output.accept(ACACIA_NIGHTSTAND);
                        output.accept(CHERRY_NIGHTSTAND);
                        output.accept(DARK_OAK_NIGHTSTAND);
                        output.accept(MANGROVE_NIGHTSTAND);
                        output.accept(BAMBOO_NIGHTSTAND);
                        output.accept(CRIMSON_NIGHTSTAND);
                        output.accept(WARPED_NIGHTSTAND);

                        output.accept(OAK_BENCH);
                        output.accept(SPRUCE_BENCH);
                        output.accept(BIRCH_BENCH);
                        output.accept(JUNGLE_BENCH);
                        output.accept(ACACIA_BENCH);
                        output.accept(CHERRY_BENCH);
                        output.accept(DARK_OAK_BENCH);
                        output.accept(MANGROVE_BENCH);
                        output.accept(BAMBOO_BENCH);
                        output.accept(CRIMSON_BENCH);
                        output.accept(WARPED_BENCH);

                        output.accept(OAK_SHELF);
                        output.accept(SPRUCE_SHELF);
                        output.accept(BIRCH_SHELF);
                        output.accept(JUNGLE_SHELF);
                        output.accept(ACACIA_SHELF);
                        output.accept(CHERRY_SHELF);
                        output.accept(DARK_OAK_SHELF);
                        output.accept(MANGROVE_SHELF);
                        output.accept(BAMBOO_SHELF);
                        output.accept(CRIMSON_SHELF);
                        output.accept(WARPED_SHELF);

                        output.accept(OAK_TABLE);
                        output.accept(SPRUCE_TABLE);
                        output.accept(BIRCH_TABLE);
                        output.accept(JUNGLE_TABLE);
                        output.accept(ACACIA_TABLE);
                        output.accept(CHERRY_TABLE);
                        output.accept(DARK_OAK_TABLE);
                        output.accept(MANGROVE_TABLE);
                        output.accept(BAMBOO_TABLE);
                        output.accept(CRIMSON_TABLE);
                        output.accept(WARPED_TABLE);

                        output.accept(OAK_CHAIR);
                        output.accept(SPRUCE_CHAIR);
                        output.accept(BIRCH_CHAIR);
                        output.accept(JUNGLE_CHAIR);
                        output.accept(ACACIA_CHAIR);
                        output.accept(CHERRY_CHAIR);
                        output.accept(DARK_OAK_CHAIR);
                        output.accept(MANGROVE_CHAIR);
                        output.accept(BAMBOO_CHAIR);
                        output.accept(CRIMSON_CHAIR);
                        output.accept(WARPED_CHAIR);

                        output.accept(WHITE_SOFA);
                        output.accept(LIGHT_GRAY_SOFA);
                        output.accept(GRAY_SOFA);
                        output.accept(BLACK_SOFA);
                        output.accept(BROWN_SOFA);
                        output.accept(RED_SOFA);
                        output.accept(ORANGE_SOFA);
                        output.accept(YELLOW_SOFA);
                        output.accept(LIME_SOFA);
                        output.accept(GREEN_SOFA);
                        output.accept(CYAN_SOFA);
                        output.accept(LIGHT_BLUE_SOFA);
                        output.accept(BLUE_SOFA);
                        output.accept(PURPLE_SOFA);
                        output.accept(MAGENTA_SOFA);
                        output.accept(PINK_SOFA);

                    }).build()
    );

    private static CreativeModeTab register(String id, CreativeModeTab tab) {
        return Registry.register(BuiltInRegistries.CREATIVE_MODE_TAB, new ResourceLocation(MOD_ID, id), tab);
    }
}
