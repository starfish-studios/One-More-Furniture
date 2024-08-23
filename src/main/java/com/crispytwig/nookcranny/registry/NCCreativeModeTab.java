package com.crispytwig.nookcranny.registry;

import com.crispytwig.nookcranny.NookAndCranny;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTab;

import static com.crispytwig.nookcranny.registry.NCItems.*;

public class NCCreativeModeTab {

    @SuppressWarnings("unused")
    public static final CreativeModeTab ITEM_GROUP = register("item_group", FabricItemGroup.builder().icon(MOD_ICON::getDefaultInstance).title(Component.translatable("itemGroup.nookcranny.tab")).displayItems((featureFlagSet, output) -> {
        // White, Light Gray, Gray, Black, Brown, Red, Orange, Yellow, Lime, Green, Cyan, Light Blue, Blue, Purple, Magenta, Pink
        output.accept(SPIGOT);
//        output.accept(PLATE);

        output.accept(OAK_MAILBOX);

        output.accept(OAK_NIGHTSTAND);
        output.accept(OAK_SHELF);

        output.accept(OAK_TABLE);
        output.accept(SPRUCE_TABLE);
        output.accept(BIRCH_TABLE);
        output.accept(JUNGLE_TABLE);
        output.accept(ACACIA_TABLE);
        output.accept(CHERRY_TABLE);
        output.accept(DARK_OAK_TABLE);
        output.accept(MANGROVE_TABLE);
        output.accept(BAMBOO_TABLE);

        output.accept(OAK_CHAIR);
        output.accept(SPRUCE_CHAIR);
        output.accept(BIRCH_CHAIR);
        output.accept(JUNGLE_CHAIR);
        output.accept(ACACIA_CHAIR);
        output.accept(CHERRY_CHAIR);
        output.accept(DARK_OAK_CHAIR);
        output.accept(MANGROVE_CHAIR);
        output.accept(BAMBOO_CHAIR);

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
        return Registry.register(BuiltInRegistries.CREATIVE_MODE_TAB, new ResourceLocation(NookAndCranny.MOD_ID, id), tab);
    }
}
