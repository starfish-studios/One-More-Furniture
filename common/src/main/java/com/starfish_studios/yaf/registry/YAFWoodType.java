package com.starfish_studios.yaf.registry;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;

public enum YAFWoodType {
    OAK("oak", Blocks.OAK_PLANKS, Blocks.OAK_SLAB),
    SPRUCE("spruce", Blocks.SPRUCE_PLANKS, Blocks.SPRUCE_SLAB),
    BIRCH("birch", Blocks.BIRCH_PLANKS, Blocks.BIRCH_SLAB),
    JUNGLE("jungle", Blocks.JUNGLE_PLANKS, Blocks.JUNGLE_SLAB),
    DARK_OAK("dark_oak", Blocks.DARK_OAK_PLANKS, Blocks.DARK_OAK_SLAB),
    MANGROVE("mangrove", Blocks.MANGROVE_PLANKS, Blocks.MANGROVE_SLAB),
    CHERRY("cherry", Blocks.CHERRY_PLANKS, Blocks.CHERRY_SLAB),
    BAMBOO("bamboo", Blocks.BAMBOO_PLANKS, Blocks.BAMBOO_SLAB),
    CRIMSON("crimson", Blocks.CRIMSON_PLANKS, Blocks.CRIMSON_SLAB),
    WARPED("warped", Blocks.WARPED_PLANKS, Blocks.WARPED_SLAB),
    ACACIA("acacia", Blocks.ACACIA_PLANKS, Blocks.ACACIA_SLAB);

    private final String name;
    private final Block planks;
    private final Block slab;

    YAFWoodType(String name, Block planks, Block slab) {
        this.name = name;
        this.planks = planks;
        this.slab = slab;
    }

    public String getName() {
        return name;
    }

    public Block getPlanks() {
        return planks;
    }

    public Block getSlab() {
        return slab;
    }

    public BlockBehaviour.Properties getBlockProperties() {
        return BlockBehaviour.Properties.ofFullCopy(planks).noOcclusion();
    }
}
