package com.crispytwig.omf.fabric.data;

import com.crispytwig.omf.registry.OMFBlocks;
import com.crispytwig.omf.registry.OMFTags;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.block.Blocks;

import java.util.concurrent.CompletableFuture;

public class OMFBlockTagProvider extends FabricTagProvider.BlockTagProvider {
    public OMFBlockTagProvider(FabricDataOutput output, CompletableFuture<HolderLookup.Provider> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    protected void addTags(HolderLookup.Provider provider) {

        OMFBlocks.CHAIRS.forEach((omfWoodType, supplier) ->  getOrCreateTagBuilder(BlockTags.MINEABLE_WITH_AXE).add(supplier.get()));
        OMFBlocks.BENCHES.forEach((omfWoodType, supplier) ->  getOrCreateTagBuilder(BlockTags.MINEABLE_WITH_AXE).add(supplier.get()));
        OMFBlocks.SOFAS.forEach((omfWoodType, supplier) ->  getOrCreateTagBuilder(BlockTags.MINEABLE_WITH_AXE).add(supplier.get()));
        OMFBlocks.SHELVES.forEach((omfWoodType, supplier) ->  getOrCreateTagBuilder(BlockTags.MINEABLE_WITH_AXE).add(supplier.get()));
        OMFBlocks.DRAWERS.forEach((omfWoodType, supplier) ->  getOrCreateTagBuilder(BlockTags.MINEABLE_WITH_AXE).add(supplier.get()));
        OMFBlocks.CABINET.forEach((omfWoodType, supplier) ->  getOrCreateTagBuilder(BlockTags.MINEABLE_WITH_AXE).add(supplier.get()));
        OMFBlocks.FANS.forEach((omfWoodType, supplier) ->  getOrCreateTagBuilder(BlockTags.MINEABLE_WITH_AXE).add(supplier.get()));
        OMFBlocks.MAIL_BOXES.forEach((omfWoodType, supplier) ->  getOrCreateTagBuilder(BlockTags.MINEABLE_WITH_AXE).add(supplier.get()));
        OMFBlocks.TALL_STOOLS.forEach((omfWoodType, supplier) ->  getOrCreateTagBuilder(BlockTags.MINEABLE_WITH_AXE).add(supplier.get()));
        OMFBlocks.TABLES.forEach((omfWoodType, supplier) ->  {
            getOrCreateTagBuilder(BlockTags.MINEABLE_WITH_AXE).add(supplier.get());
            getOrCreateTagBuilder(OMFTags.BlockTags.TABLES_CONNECTABLE).add(supplier.get());
            getOrCreateTagBuilder(OMFTags.BlockTags.TABLES).add(supplier.get());
        });
        OMFBlocks.FLOWER_BASKETS.forEach((omfWoodType, supplier) ->  getOrCreateTagBuilder(BlockTags.MINEABLE_WITH_AXE).add(supplier.get()));
        OMFBlocks.LAMPS.forEach((omfWoodType, supplier) ->  getOrCreateTagBuilder(BlockTags.MINEABLE_WITH_AXE).add(supplier.get()));

        getOrCreateTagBuilder(BlockTags.MINEABLE_WITH_PICKAXE).add(OMFBlocks.SPIGOT.get());
        getOrCreateTagBuilder(OMFTags.BlockTags.TABLES_CONNECTABLE).add(Blocks.SCAFFOLDING);
    }
}
