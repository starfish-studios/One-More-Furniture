package com.crispytwig.omf.registry;

import com.crispytwig.omf.OneMoreFurniture;
import dev.architectury.registry.registries.DeferredRegister;
import dev.architectury.registry.registries.RegistrySupplier;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;

import java.util.HashMap;
import java.util.Map;

public class OMFItems {

    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(OneMoreFurniture.MOD_ID, Registries.ITEM);

    public static final Map<OMFWoodType, RegistrySupplier<BlockItem>> DRAWERS = new HashMap<>();
    public static final Map<OMFWoodType, RegistrySupplier<BlockItem>> TALL_STOOLS = new HashMap<>();
    public static final Map<OMFWoodType, RegistrySupplier<BlockItem>> BENCHES = new HashMap<>();
    public static final Map<OMFWoodType, RegistrySupplier<BlockItem>> FLOWER_BASKETS = new HashMap<>();
    public static final Map<OMFWoodType, RegistrySupplier<BlockItem>> LAMPS = new HashMap<>();
    public static final Map<OMFWoodType, RegistrySupplier<BlockItem>> MAIL_BOXES = new HashMap<>();
    public static final Map<OMFWoodType, RegistrySupplier<BlockItem>> SHELVES = new HashMap<>();
    public static final Map<OMFWoodType, RegistrySupplier<BlockItem>> TABLES = new HashMap<>();
    public static final Map<OMFWoodType, RegistrySupplier<BlockItem>> CHAIRS = new HashMap<>();
    public static final Map<OMFWoodType, RegistrySupplier<BlockItem>> FANS = new HashMap<>();
    public static final Map<OMFWoodType, RegistrySupplier<BlockItem>> CABINET = new HashMap<>();

    public static final Map<OMFDyeColor, RegistrySupplier<BlockItem>> SOFAS = new HashMap<>();
    public static final Map<OMFDyeColor, RegistrySupplier<BlockItem>> CURTAINS = new HashMap<>();
    
    public static final RegistrySupplier<Item> COPPER_SAW = ITEMS.register("copper_saw", () -> new Item(new Item.Properties().stacksTo(1).durability(128)));

    public static final RegistrySupplier<Item> SPIGOT = ITEMS.register("spigot", () -> new BlockItem(OMFBlocks.SPIGOT.get(), new Item.Properties()));

    public static final RegistrySupplier<Item> AMETHYST_WIND_CHIMES = ITEMS.register("amethyst_wind_chimes", () -> new BlockItem(OMFBlocks.AMETHYST_WIND_CHIMES.get(),  new Item.Properties()));
    public static final RegistrySupplier<Item> BAMBOO_WIND_CHIMES = ITEMS.register("bamboo_wind_chimes", () ->  new BlockItem(OMFBlocks.BAMBOO_WIND_CHIMES.get(),  new Item.Properties()));
    public static final RegistrySupplier<Item> BAMBOO_STRIPPED_WIND_CHIMES = ITEMS.register("bamboo_stripped_wind_chimes",  () -> new BlockItem(OMFBlocks.BAMBOO_STRIPPED_WIND_CHIMES.get(),  new Item.Properties()));
    public static final RegistrySupplier<Item> BONE_WIND_CHIMES = ITEMS.register("bone_wind_chimes", () ->  new BlockItem(OMFBlocks.BONE_WIND_CHIMES.get(),  new Item.Properties()));
    public static final RegistrySupplier<Item> COPPER_WIND_CHIMES = ITEMS.register("copper_wind_chimes",  () -> new BlockItem(OMFBlocks.COPPER_WIND_CHIMES.get(),  new Item.Properties()));
    public static final RegistrySupplier<Item> ECHO_SHARD_WIND_CHIMES = ITEMS.register("echo_shard_wind_chimes", () ->  new BlockItem(OMFBlocks.ECHO_SHARD_WIND_CHIMES.get(),  new Item.Properties()));

    static {
        for (OMFWoodType woodType : OMFWoodType.values()) {
            DRAWERS.put(woodType, ITEMS.register(woodType.getName() + "_drawer",
                    () -> new BlockItem(OMFBlocks.DRAWERS.get(woodType).get(), new Item.Properties())));

            TALL_STOOLS.put(woodType, ITEMS.register(woodType.getName() + "_tall_stool",
                    () -> new BlockItem(OMFBlocks.TALL_STOOLS.get(woodType).get(), new Item.Properties())));

            FLOWER_BASKETS.put(woodType, ITEMS.register(woodType.getName() + "_flower_basket",
                    () -> new BlockItem(OMFBlocks.FLOWER_BASKETS.get(woodType).get(), new Item.Properties())));

            LAMPS.put(woodType, ITEMS.register(woodType.getName() + "_lamp",
                    () -> new BlockItem(OMFBlocks.LAMPS.get(woodType).get(), new Item.Properties())));
            
            MAIL_BOXES.put(woodType, ITEMS.register(woodType.getName() + "_mailbox",
                    () -> new BlockItem(OMFBlocks.MAIL_BOXES.get(woodType).get(), new Item.Properties())));
            
            BENCHES.put(woodType, ITEMS.register(woodType.getName() + "_bench",
                    () -> new BlockItem(OMFBlocks.BENCHES.get(woodType).get(), new Item.Properties())));
            
            SHELVES.put(woodType, ITEMS.register(woodType.getName() + "_shelf",
                    () -> new BlockItem(OMFBlocks.SHELVES.get(woodType).get(), new Item.Properties())));

            TABLES.put(woodType, ITEMS.register(woodType.getName() + "_table",
                    () -> new BlockItem(OMFBlocks.TABLES.get(woodType).get(), new Item.Properties())));

            CHAIRS.put(woodType, ITEMS.register(woodType.getName() + "_chair",
                    () -> new BlockItem(OMFBlocks.CHAIRS.get(woodType).get(), new Item.Properties())));

            FANS.put(woodType, ITEMS.register(woodType.getName() + "_fan",
                    () -> new BlockItem(OMFBlocks.FANS.get(woodType).get(), new Item.Properties())));

            CABINET.put(woodType, ITEMS.register(woodType.getName() + "_cabinet",
                    () -> new BlockItem(OMFBlocks.CABINET.get(woodType).get(), new Item.Properties())));
        }

        for (OMFDyeColor dyeColor : OMFDyeColor.values()) {
            SOFAS.put(dyeColor, ITEMS.register(dyeColor.getName() + "_sofa",
                    () -> new BlockItem(OMFBlocks.SOFAS.get(dyeColor).get(), new Item.Properties())));

            CURTAINS.put(dyeColor, ITEMS.register(dyeColor.getName() + "_curtain",
                    () -> new BlockItem(OMFBlocks.CURTAINS.get(dyeColor).get(), new Item.Properties())));
        }
    }
}
