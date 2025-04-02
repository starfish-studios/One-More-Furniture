package com.crispytwig.omf.registry;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;

public enum OMFWoodType {
    OAK("oak", Items.OAK_PLANKS, Blocks.OAK_PLANKS),
    SPRUCE("spruce", Items.SPRUCE_PLANKS, Blocks.SPRUCE_PLANKS),
    BIRCH("birch", Items.BIRCH_PLANKS, Blocks.BIRCH_PLANKS),
    JUNGLE("jungle", Items.JUNGLE_PLANKS, Blocks.JUNGLE_PLANKS),
    DARK_OAK("dark_oak", Items.DARK_OAK_PLANKS, Blocks.DARK_OAK_PLANKS),
    MANGROVE("mangrove", Items.MANGROVE_PLANKS, Blocks.MANGROVE_PLANKS),
    CHERRY("cherry", Items.CHERRY_PLANKS, Blocks.CHERRY_PLANKS),
    BAMBOO("bamboo", Items.BAMBOO_PLANKS, Blocks.BAMBOO_PLANKS),
    CRIMSON("crimson", Items.CRIMSON_PLANKS, Blocks.CRIMSON_PLANKS),
    WARPED("warped", Items.WARPED_PLANKS, Blocks.WARPED_PLANKS),
    ACACIA("acacia", Items.ACACIA_PLANKS, Blocks.ACACIA_PLANKS);

    private final String name;
    private final Item planksItem;
    private final Block baseBlock;

    OMFWoodType(String name, Item planksItem, Block baseBlock) {
        this.name = name;
        this.planksItem = planksItem;
        this.baseBlock = baseBlock;
    }

    public String getName() {
        return name;
    }

    public Item getPlanksItem() {
        return planksItem;
    }

    public BlockBehaviour.Properties getBlockProperties() {
        return BlockBehaviour.Properties.copy(baseBlock).noOcclusion();
    }
}
