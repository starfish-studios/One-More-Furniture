package com.crispytwig.omf.registry;

import com.crispytwig.omf.OneMoreFurniture;
import com.crispytwig.omf.block.entity.*;
import dev.architectury.registry.registries.DeferredRegister;
import dev.architectury.registry.registries.RegistrySupplier;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntityType;

public class OMFBlockEntities {

    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITY_TYPES = DeferredRegister.create(OneMoreFurniture.MOD_ID, Registries.BLOCK_ENTITY_TYPE);

    public static final RegistrySupplier<BlockEntityType<MailboxBlockEntity>> MAILBOX = BLOCK_ENTITY_TYPES.register("mailbox",
            () -> BlockEntityType.Builder.of(MailboxBlockEntity::new,
                    OMFBlocks.MAIL_BOXES.values().stream()
                            .map(RegistrySupplier::get)
                            .toArray(Block[]::new)
            ).build(null)
    );

    public static final RegistrySupplier<BlockEntityType<DrawerBlockEntity>> DRAWER = BLOCK_ENTITY_TYPES.register("drawer",
            () -> BlockEntityType.Builder.of(DrawerBlockEntity::new,
                    OMFBlocks.DRAWERS.values().stream()
                            .map(RegistrySupplier::get)
                            .toArray(Block[]::new)
            ).build(null)
    );

    public static final RegistrySupplier<BlockEntityType<CabinetBlockEntity>> CABINET = BLOCK_ENTITY_TYPES.register("cabinet",
            () -> BlockEntityType.Builder.of(CabinetBlockEntity::new,
                    OMFBlocks.CABINET.values().stream()
                            .map(RegistrySupplier::get)
                            .toArray(Block[]::new)
            ).build(null)
    );

    public static final RegistrySupplier<BlockEntityType<SpigotBlockEntity>> SPIGOT = BLOCK_ENTITY_TYPES.register("spigot",
            () -> BlockEntityType.Builder.of(SpigotBlockEntity::new, OMFBlocks.SPIGOT.get()).build(null));


    public static final RegistrySupplier<BlockEntityType<FlowerBasketBlockEntity>> FLOWER_BASKET = BLOCK_ENTITY_TYPES.register("flower_basket",
            () -> BlockEntityType.Builder.of(FlowerBasketBlockEntity::new,
                    OMFBlocks.FLOWER_BASKETS.values().stream()
                            .map(RegistrySupplier::get)
                            .toArray(Block[]::new)
            ).build(null)
    );

    public static final RegistrySupplier<BlockEntityType<ShelfBlockEntity>> SHELF = BLOCK_ENTITY_TYPES.register("shelf",
            () -> BlockEntityType.Builder.of(ShelfBlockEntity::new,
                    OMFBlocks.SHELVES.values().stream()
                            .map(RegistrySupplier::get)
                            .toArray(Block[]::new)
            ).build(null)
    );

    public static final RegistrySupplier<BlockEntityType<FanBlockEntity>> FAN = BLOCK_ENTITY_TYPES.register("fan",
            () -> BlockEntityType.Builder.of(FanBlockEntity::new,
                    OMFBlocks.FANS.values().stream()
                            .map(RegistrySupplier::get)
                            .toArray(Block[]::new)
            ).build(null)
    );


    public static final RegistrySupplier<BlockEntityType<WindChimeBlockEntity>> CHIME = BLOCK_ENTITY_TYPES.register("chime",  () -> BlockEntityType.Builder.of(WindChimeBlockEntity::new,
            OMFBlocks.AMETHYST_WIND_CHIMES.get(),
            OMFBlocks.BAMBOO_WIND_CHIMES.get(),
            OMFBlocks.BAMBOO_STRIPPED_WIND_CHIMES.get(),
            OMFBlocks.BONE_WIND_CHIMES.get(),
            OMFBlocks.COPPER_WIND_CHIMES.get(),
            OMFBlocks.ECHO_SHARD_WIND_CHIMES.get()

    ).build(null));
}
