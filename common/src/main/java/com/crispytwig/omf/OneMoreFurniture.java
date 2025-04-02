package com.crispytwig.omf;

import com.crispytwig.omf.registry.*;
import com.google.common.base.Suppliers;
import dev.architectury.registry.CreativeTabRegistry;
import dev.architectury.registry.registries.DeferredRegister;
import dev.architectury.registry.registries.RegistrarManager;
import dev.architectury.registry.registries.RegistrySupplier;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;

import java.util.function.Supplier;

public class OneMoreFurniture {
    public static final String MOD_ID = "omf";
    public static final Supplier<RegistrarManager> REGISTRIES = Suppliers.memoize(() -> RegistrarManager.get(MOD_ID));

    public static final DeferredRegister<CreativeModeTab> TABS = DeferredRegister.create(MOD_ID, Registries.CREATIVE_MODE_TAB);

    public static final RegistrySupplier<CreativeModeTab> MAIN = TABS.register("main",
            () -> CreativeTabRegistry.create(builder -> {
                builder.title(Component.translatable("itemGroup.nookcranny.tab"));
                builder.icon(() -> new ItemStack(OMFItems.BENCHES.get(OMFWoodType.OAK).get()));
                builder.displayItems((params, output) -> {
                    OMFItems.ITEMS.forEach(itemRegistrySupplier ->
                            output.accept(itemRegistrySupplier.get().getDefaultInstance()));
                });
            })
    );


    public static void init() {
        TABS.register();
        OMFItems.ITEMS.register();
        OMFBlocks.BLOCKS.register();
        OMFBlockEntities.BLOCK_ENTITY_TYPES.register();
        OMFMenus.MENUS.register();
        OMFSoundEvents.SOUNDS.register();
        OMFEntities.ENTITY_TYPES.register();

    }
}
