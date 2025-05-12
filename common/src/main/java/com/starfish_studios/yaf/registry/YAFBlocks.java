package com.starfish_studios.yaf.registry;

import com.starfish_studios.yaf.YetAnotherFurniture;
import com.starfish_studios.yaf.block.*;
import dev.architectury.registry.registries.DeferredRegister;
import dev.architectury.registry.registries.RegistrySupplier;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;

import java.util.HashMap;
import java.util.Map;


public class YAFBlocks {

    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(YetAnotherFurniture.MOD_ID, Registries.BLOCK);

    public static final Map<YAFWoodType, RegistrySupplier<DrawerBlock>> DRAWERS = new HashMap<>();
    public static final Map<YAFWoodType, RegistrySupplier<TallStoolBlock>> TALL_STOOLS = new HashMap<>();
    public static final Map<YAFWoodType, RegistrySupplier<BenchBlock>> BENCHES = new HashMap<>();
    public static final Map<YAFWoodType, RegistrySupplier<FlowerBasketBlock>> FLOWER_BASKETS = new HashMap<>();
    public static final Map<YAFWoodType, RegistrySupplier<LampBlock>> LAMPS = new HashMap<>();
    public static final Map<YAFWoodType, RegistrySupplier<MailboxBlock>> MAIL_BOXES = new HashMap<>();
    public static final Map<YAFWoodType, RegistrySupplier<ShelfBlock>> SHELVES = new HashMap<>();
    public static final Map<YAFWoodType, RegistrySupplier<TableBlock>> TABLES = new HashMap<>();
    public static final Map<YAFWoodType, RegistrySupplier<ChairBlock>> CHAIRS = new HashMap<>();
    public static final Map<YAFWoodType, RegistrySupplier<FanBlock>> FANS = new HashMap<>();
    public static final Map<YAFWoodType, RegistrySupplier<CabinetBlock>> CABINET = new HashMap<>();

    public static final Map<YAFDyeColor, RegistrySupplier<SofaBlock>> SOFAS = new HashMap<>();
    public static final Map<YAFDyeColor, RegistrySupplier<CurtainBlock>> CURTAINS = new HashMap<>();

    public static BlockBehaviour.Properties lamps =
            Block.Properties.of().noOcclusion().instabreak().lightLevel(state -> state.getValue(LampBlock.LIT) ? 15 : 0);

    public static final RegistrySupplier<Block> SPIGOT = BLOCKS.register("spigot", () -> new SpigotBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.CAULDRON).noOcclusion().randomTicks()));

    public static final RegistrySupplier<Block> AMETHYST_WIND_CHIMES = BLOCKS.register("amethyst_wind_chimes", () -> new WindChimeBlock(
                    "amethyst",
                    YAFSoundEvents.WINDCHIME_AMETHYST.get(),
                    BlockBehaviour.Properties.of().noOcclusion()
            )
    );

    public static final RegistrySupplier<Block> BAMBOO_WIND_CHIMES = BLOCKS.register("bamboo_wind_chimes",
            () -> new WindChimeBlock(
                    "bamboo",
                    YAFSoundEvents.WINDCHIME_BAMBOO.get(),
                    BlockBehaviour.Properties.of().noOcclusion()
            )
    );

    public static final RegistrySupplier<Block> BAMBOO_STRIPPED_WIND_CHIMES = BLOCKS.register("bamboo_stripped_wind_chimes",
            () -> new WindChimeBlock(
                    "bamboo_stripped",
                    YAFSoundEvents.WINDCHIME_BAMBOO.get(),
                    BlockBehaviour.Properties.of().noOcclusion()
            )
    );

    public static final RegistrySupplier<Block> BONE_WIND_CHIMES = BLOCKS.register("bone_wind_chimes",
            () -> new WindChimeBlock(
                    "bone",
                    YAFSoundEvents.WINDCHIME_BONE.get(),
                    BlockBehaviour.Properties.of().noOcclusion()
            )
    );

    public static final RegistrySupplier<Block> COPPER_WIND_CHIMES = BLOCKS.register("copper_wind_chimes",
            () -> new WindChimeBlock(
                    "copper",
                    YAFSoundEvents.WINDCHIME_COPPER.get(),
                    BlockBehaviour.Properties.of().noOcclusion()
            )
    );

    public static final RegistrySupplier<Block> ECHO_SHARD_WIND_CHIMES = BLOCKS.register("echo_shard_wind_chimes",
            () -> new WindChimeBlock(
                    "echo_shard",
                    YAFSoundEvents.WINDCHIME_ECHO_SHARD.get(),
                    BlockBehaviour.Properties.of().noOcclusion()
            )
    );

    static {

        for (YAFWoodType woodType : YAFWoodType.values()) {
            DRAWERS.put(woodType, BLOCKS.register(woodType.getName() + "_drawer",
                    () -> new DrawerBlock(woodType.getPlanks().asItem(), woodType.getBlockProperties())));

            TALL_STOOLS.put(woodType, BLOCKS.register(woodType.getName() + "_tall_stool",
                    () -> new TallStoolBlock(woodType.getBlockProperties())));

            BENCHES.put(woodType, BLOCKS.register(woodType.getName() + "_bench",
                    () -> new BenchBlock(woodType.getBlockProperties())));

            FLOWER_BASKETS.put(woodType, BLOCKS.register(woodType.getName() + "_flower_basket",
                    () -> new FlowerBasketBlock(woodType.getBlockProperties())));

            LAMPS.put(woodType, BLOCKS.register(woodType.getName() + "_lamp",
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
                    () -> new CabinetBlock(woodType.getPlanks().asItem(), woodType.getBlockProperties())));

        }

        for (YAFDyeColor dyeColor : YAFDyeColor.values()) {
            SOFAS.put(dyeColor, BLOCKS.register(dyeColor.getName() + "_sofa",
                    () -> new SofaBlock(dyeColor.getDyeColor(), dyeColor.getBlockProperties())));

            CURTAINS.put(dyeColor, BLOCKS.register(dyeColor.getName() + "_curtain",
                    () -> new CurtainBlock(dyeColor.getBlockProperties())));
        }
    }
}
