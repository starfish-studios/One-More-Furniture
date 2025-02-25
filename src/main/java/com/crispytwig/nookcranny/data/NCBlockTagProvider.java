package com.crispytwig.nookcranny.data;

import com.crispytwig.nookcranny.blocks.*;
import com.crispytwig.nookcranny.registry.NCBlocks;
import com.crispytwig.nookcranny.registry.NCTags;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;

import java.util.concurrent.CompletableFuture;

public class NCBlockTagProvider extends FabricTagProvider.BlockTagProvider {
    public NCBlockTagProvider(FabricDataOutput output, CompletableFuture<HolderLookup.Provider> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    protected void addTags(HolderLookup.Provider provider) {

        for (Block block : NCBlocks.BLOCKS) {
            if (block instanceof ChairBlock) {
                getOrCreateTagBuilder(BlockTags.MINEABLE_WITH_AXE).add(block);
            }
            if (block instanceof BenchBlock) {
                getOrCreateTagBuilder(BlockTags.MINEABLE_WITH_AXE).add(block);
            }
            if (block instanceof ShelfBlock) {
                getOrCreateTagBuilder(BlockTags.MINEABLE_WITH_AXE).add(block);
            }
            if (block instanceof DrawerBlock) {
                getOrCreateTagBuilder(BlockTags.MINEABLE_WITH_AXE).add(block);
            }
            if (block instanceof TallStoolBlock) {
                getOrCreateTagBuilder(BlockTags.MINEABLE_WITH_AXE).add(block);
            }
            if (block instanceof TableBlock) {
                getOrCreateTagBuilder(BlockTags.MINEABLE_WITH_AXE).add(block);
            }
            if (block instanceof FlowerBasketBlock) {
                getOrCreateTagBuilder(BlockTags.MINEABLE_WITH_AXE).add(block);
            }

            if (block instanceof LampBlock) {
                getOrCreateTagBuilder(BlockTags.MINEABLE_WITH_AXE).add(block);
            }
            if (block instanceof SpigotBlock) {
                getOrCreateTagBuilder(BlockTags.MINEABLE_WITH_PICKAXE).add(block);
            }

            if (block instanceof TableBlock) {
                getOrCreateTagBuilder(NCTags.BlockTags.TABLES_CONNECTABLE).add(block);
                getOrCreateTagBuilder(NCTags.BlockTags.TABLES).add(block);
            }
            if (block instanceof NightstandBlock) {
                getOrCreateTagBuilder(NCTags.BlockTags.TABLES_CONNECTABLE).add(block);
                getOrCreateTagBuilder(BlockTags.MINEABLE_WITH_AXE).add(block);
            }
        }

        getOrCreateTagBuilder(NCTags.BlockTags.TABLES_CONNECTABLE)
                .add(Blocks.SCAFFOLDING);
    }
}
