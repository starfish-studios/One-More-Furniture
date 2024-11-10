package com.crispytwig.nookcranny.registry;

import com.crispytwig.nookcranny.blocks.entities.*;
import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.entity.BlockEntityType;

import java.util.function.Supplier;

import static com.crispytwig.nookcranny.NookAndCranny.MOD_ID;

public class NCBlockEntities {

    public static final BlockEntityType<MailboxBlockEntity> MAILBOX = register("mailbox", FabricBlockEntityTypeBuilder.create(MailboxBlockEntity::new, NCBlocks.OAK_MAILBOX).build(null));

    public static final BlockEntityType<DrawerBlockEntity> DRAWER = register("drawer", FabricBlockEntityTypeBuilder.create(DrawerBlockEntity::new, NCBlocks.OAK_DRAWER).build(null));

    public static final BlockEntityType<SpigotBlockEntity> SPIGOT = register("spigot", FabricBlockEntityTypeBuilder.create(SpigotBlockEntity::new, NCBlocks.SPIGOT).build(null));

//    public static final Supplier<BlockEntityType<FlowerBoxBlockEntity>> FLOWER_BOX = AFRegistry.registerBlockEntityType("planter_box",
//            () -> AFRegistry.createBlockEntityType(FlowerBoxBlockEntity::new,
//                    AFBlocks.OAK_FLOWER_BOX.get(),
//                    AFBlocks.SPRUCE_FLOWER_BOX.get(),
//                    AFBlocks.BIRCH_FLOWER_BOX.get(),
//                    AFBlocks.JUNGLE_FLOWER_BOX.get(),
//                    AFBlocks.ACACIA_FLOWER_BOX.get(),
//                    AFBlocks.DARK_OAK_FLOWER_BOX.get(),
//                    AFBlocks.MANGROVE_FLOWER_BOX.get(),
//                    AFBlocks.CRIMSON_FLOWER_BOX.get(),
//                    AFBlocks.WARPED_FLOWER_BOX.get(),
//                    AFBlocks.BAMBOO_FLOWER_BOX.get(),
//                    AFBlocks.CHERRY_FLOWER_BOX.get()
//            ));

    public static final BlockEntityType<FlowerBasketBlockEntity> FLOWER_BASKET = register("flower_basket", FabricBlockEntityTypeBuilder.create(FlowerBasketBlockEntity::new,
            NCBlocks.OAK_FLOWER_BASKET,
            NCBlocks.SPRUCE_FLOWER_BASKET,
            NCBlocks.BIRCH_FLOWER_BASKET,
            NCBlocks.JUNGLE_FLOWER_BASKET,
            NCBlocks.ACACIA_FLOWER_BASKET,
            NCBlocks.DARK_OAK_FLOWER_BASKET,
            NCBlocks.MANGROVE_FLOWER_BASKET,
            NCBlocks.BAMBOO_FLOWER_BASKET,
            NCBlocks.CHERRY_FLOWER_BASKET,
            NCBlocks.CRIMSON_FLOWER_BASKET,
            NCBlocks.WARPED_FLOWER_BASKET
    ).build(null));


    public static final BlockEntityType<ShelfBlockEntity> SHELF = register("shelf", FabricBlockEntityTypeBuilder.create(ShelfBlockEntity::new,
            NCBlocks.OAK_SHELF,
            NCBlocks.SPRUCE_SHELF,
            NCBlocks.BIRCH_SHELF,
            NCBlocks.JUNGLE_SHELF,
            NCBlocks.ACACIA_SHELF,
            NCBlocks.DARK_OAK_SHELF,
            NCBlocks.MANGROVE_SHELF,
            NCBlocks.BAMBOO_SHELF,
            NCBlocks.CHERRY_SHELF
    ).build(null));

    public static <T extends BlockEntityType<?>> T register(String name, T blockEntityType) {
        return Registry.register(BuiltInRegistries.BLOCK_ENTITY_TYPE, new ResourceLocation(MOD_ID, name), blockEntityType);
    }
}
