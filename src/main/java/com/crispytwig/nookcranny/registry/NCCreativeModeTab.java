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
        output.accept(WHITE_SOFA);
        output.accept(ORANGE_SOFA);
        output.accept(MAGENTA_SOFA);
        output.accept(LIGHT_BLUE_SOFA);
        output.accept(YELLOW_SOFA);
        output.accept(LIME_SOFA);
        output.accept(PINK_SOFA);
        output.accept(GRAY_SOFA);
        output.accept(LIGHT_GRAY_SOFA);
        output.accept(CYAN_SOFA);
        output.accept(PURPLE_SOFA);
        output.accept(BLUE_SOFA);
        output.accept(BROWN_SOFA);
        output.accept(GREEN_SOFA);
        output.accept(RED_SOFA);
        output.accept(BLACK_SOFA);

        }).build()
    );

    private static CreativeModeTab register(String id, CreativeModeTab tab) {
        return Registry.register(BuiltInRegistries.CREATIVE_MODE_TAB, new ResourceLocation(NookAndCranny.MOD_ID, id), tab);
    }
}
