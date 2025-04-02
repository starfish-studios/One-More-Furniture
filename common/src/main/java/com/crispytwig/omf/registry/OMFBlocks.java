package com.crispytwig.omf.registry;

import com.crispytwig.omf.OneMoreFurniture;
import com.crispytwig.omf.block.*;
import dev.architectury.registry.registries.DeferredRegister;
import dev.architectury.registry.registries.RegistrySupplier;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;

import java.util.HashMap;
import java.util.Map;


public class OMFBlocks {

    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(OneMoreFurniture.MOD_ID, Registries.BLOCK);

    public static final Map<OMFWoodType, RegistrySupplier<DrawerBlock>> DRAWERS = new HashMap<>();
    public static final Map<OMFWoodType, RegistrySupplier<TallStoolBlock>> TALL_STOOLS = new HashMap<>();
    public static final Map<OMFWoodType, RegistrySupplier<BenchBlock>> BENCHES = new HashMap<>();
    public static final Map<OMFWoodType, RegistrySupplier<FlowerBasketBlock>> FLOWER_BASKETS = new HashMap<>();
    public static final Map<OMFWoodType, RegistrySupplier<LampBlock>> LAMPS = new HashMap<>();
    public static final Map<OMFWoodType, RegistrySupplier<MailboxBlock>> MAIL_BOXES = new HashMap<>();
    public static final Map<OMFWoodType, RegistrySupplier<ShelfBlock>> SHELVES = new HashMap<>();
    public static final Map<OMFWoodType, RegistrySupplier<TableBlock>> TABLES = new HashMap<>();
    public static final Map<OMFWoodType, RegistrySupplier<ChairBlock>> CHAIRS = new HashMap<>();
    public static final Map<OMFWoodType, RegistrySupplier<FanBlock>> FANS = new HashMap<>();
    public static final Map<OMFWoodType, RegistrySupplier<CabinetBlock>> CABINET = new HashMap<>();

    public static final Map<OMFDyeColor, RegistrySupplier<SofaBlock>> SOFAS = new HashMap<>();
    public static final Map<OMFDyeColor, RegistrySupplier<CurtainBlock>> CURTAINS = new HashMap<>();

    public static BlockBehaviour.Properties lamps =
            Block.Properties.of().noOcclusion().instabreak().lightLevel(state -> state.getValue(LampBlock.LIT) ? 15 : 0);

    public static final RegistrySupplier<Block> SPIGOT = BLOCKS.register("spigot", () -> new SpigotBlock(BlockBehaviour.Properties.copy(Blocks.CAULDRON).noOcclusion().randomTicks()));

    public static final RegistrySupplier<Block> AMETHYST_WIND_CHIMES = BLOCKS.register("amethyst_wind_chimes", () -> new WindChimeBlock(
                    "amethyst",
                    OMFSoundEvents.WINDCHIME_AMETHYST.get(),
                    BlockBehaviour.Properties.of().noOcclusion()
            )
    );

    public static final RegistrySupplier<Block> BAMBOO_WIND_CHIMES = BLOCKS.register("bamboo_wind_chimes",
            () -> new WindChimeBlock(
                    "bamboo",
                    OMFSoundEvents.WINDCHIME_BAMBOO.get(),
                    BlockBehaviour.Properties.of().noOcclusion()
            )
    );

    public static final RegistrySupplier<Block> BAMBOO_STRIPPED_WIND_CHIMES = BLOCKS.register("bamboo_stripped_wind_chimes",
            () -> new WindChimeBlock(
                    "bamboo_stripped",
                    OMFSoundEvents.WINDCHIME_BAMBOO.get(),
                    BlockBehaviour.Properties.of().noOcclusion()
            )
    );

    public static final RegistrySupplier<Block> BONE_WIND_CHIMES = BLOCKS.register("bone_wind_chimes",
            () -> new WindChimeBlock(
                    "bone",
                    OMFSoundEvents.WINDCHIME_BONE.get(),
                    BlockBehaviour.Properties.of().noOcclusion()
            )
    );

    public static final RegistrySupplier<Block> COPPER_WIND_CHIMES = BLOCKS.register("copper_wind_chimes",
            () -> new WindChimeBlock(
                    "copper",
                    OMFSoundEvents.WINDCHIME_COPPER.get(),
                    BlockBehaviour.Properties.of().noOcclusion()
            )
    );

    public static final RegistrySupplier<Block> ECHO_SHARD_WIND_CHIMES = BLOCKS.register("echo_shard_wind_chimes",
            () -> new WindChimeBlock(
                    "echo_shard",
                    OMFSoundEvents.WINDCHIME_ECHO_SHARD.get(),
                    BlockBehaviour.Properties.of().noOcclusion()
            )
    );

    static {

        for (OMFWoodType woodType : OMFWoodType.values()) {
            DRAWERS.put(woodType, BLOCKS.register(woodType.getName() + "_drawer",
                    () -> new DrawerBlock(woodType.getPlanksItem(), woodType.getBlockProperties())));

            TALL_STOOLS.put(woodType, BLOCKS.register(woodType.getName() + "_tall_stool",
                    () -> new TallStoolBlock(woodType.getBlockProperties())));

            BENCHES.put(woodType, BLOCKS.register(woodType.getName() + "_bench",
                    () -> new BenchBlock(woodType.getBlockProperties())));

            FLOWER_BASKETS.put(woodType, BLOCKS.register(woodType.getName() + "_flower_basket",
                    () -> new FlowerBasketBlock(woodType.getBlockProperties())));

            LAMPS.put(woodType, BLOCKS.register(woodType.getName() + "_lamps",
                    () -> new LampBlock(lamps)));

            MAIL_BOXES.put(woodType, BLOCKS.register(woodType.getName() + "_mailbox",
                    () -> new MailboxBlock(woodType.getBlockProperties())));

            SHELVES.put(woodType, BLOCKS.register(woodType.getName() + "_shelf",
                    () -> new ShelfBlock(woodType.getBlockProperties())));

            TABLES.put(woodType, BLOCKS.register(woodType.getName() + "_table",
                    () -> new TableBlock(woodType.getBlockProperties())));

            CHAIRS.put(woodType, BLOCKS.register(woodType.getName() + "_chair",
                    () -> new ChairBlock(woodType.getBlockProperties())));

            FANS.put(woodType, BLOCKS.register(woodType.getName() + "_fan",
                    () -> new FanBlock(woodType.getName(), woodType.getBlockProperties())));

            CABINET.put(woodType, BLOCKS.register(woodType.getName() + "_cabinet",
                    () -> new CabinetBlock(woodType.getPlanksItem(), woodType.getBlockProperties())));

        }

        for (OMFDyeColor dyeColor : OMFDyeColor.values()) {
            SOFAS.put(dyeColor, BLOCKS.register(dyeColor.getName() + "_sofa",
                    () -> new SofaBlock(dyeColor.getDyeColor(), dyeColor.getBlockProperties())));

            CURTAINS.put(dyeColor, BLOCKS.register(dyeColor.getName() + "_curtain",
                    () -> new CurtainBlock(dyeColor.getBlockProperties())));
        }
    }
}
