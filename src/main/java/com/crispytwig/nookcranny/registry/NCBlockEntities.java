package com.crispytwig.nookcranny.registry;

import com.crispytwig.nookcranny.NookAndCranny;
import com.crispytwig.nookcranny.blocks.ShelfBlock;
import com.crispytwig.nookcranny.blocks.entities.ShelfBlockEntity;
import com.crispytwig.nookcranny.blocks.entities.SpigotBlockEntity;
import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.entity.BlockEntityType;

import static com.crispytwig.nookcranny.NookAndCranny.MOD_ID;

public class NCBlockEntities {
    public static final BlockEntityType<SpigotBlockEntity> SPIGOT = register("spigot", FabricBlockEntityTypeBuilder.create(SpigotBlockEntity::new, NCBlocks.SPIGOT).build(null));

    public static final BlockEntityType<ShelfBlockEntity> SHELF = register("shelf", FabricBlockEntityTypeBuilder.create(ShelfBlockEntity::new,
            NCBlocks.OAK_SHELF,
            NCBlocks.SPRUCE_SHELF,
            NCBlocks.BIRCH_SHELF,
            NCBlocks.JUNGLE_SHELF,
            NCBlocks.ACACIA_SHELF,
            NCBlocks.DARK_OAK_SHELF,
            NCBlocks.MANGROVE_SHELF,
//            NCBlocks.CRIMSON_SHELF,
//            NCBlocks.WARPED_SHELF,
            NCBlocks.BAMBOO_SHELF,
            NCBlocks.CHERRY_SHELF
    ).build(null));

    public static <T extends BlockEntityType<?>> T register(String name, T blockEntityType) {
        return Registry.register(BuiltInRegistries.BLOCK_ENTITY_TYPE, new ResourceLocation(MOD_ID, name), blockEntityType);
    }
}
