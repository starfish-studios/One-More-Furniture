//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.crispytwig.nookcranny.blocks.properties;

import com.crispytwig.nookcranny.registry.NCBlocks;
import net.minecraft.util.StringRepresentable;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Block;
import org.jetbrains.annotations.NotNull;

import java.util.Arrays;
import java.util.function.Supplier;

public enum CountertopType implements StringRepresentable {
    OAK("oak", () -> Items.OAK_PLANKS, () -> NCBlocks.OAK_DRAWER),
    SPRUCE("spruce", () -> Items.SPRUCE_PLANKS, () -> NCBlocks.SPRUCE_DRAWER),
    BIRCH("birch", () -> Items.BIRCH_PLANKS, () -> NCBlocks.BIRCH_DRAWER),
    JUNGLE("jungle", () -> Items.JUNGLE_PLANKS, () -> NCBlocks.JUNGLE_DRAWER),
    ACACIA("acacia", () -> Items.ACACIA_PLANKS, () -> NCBlocks.ACACIA_DRAWER),
    DARK_OAK("dark_oak", () -> Items.DARK_OAK_PLANKS, () -> NCBlocks.DARK_OAK_DRAWER),
    CRIMSON("crimson", () -> Items.CRIMSON_PLANKS, () -> NCBlocks.CRIMSON_DRAWER),
    WARPED("warped", () -> Items.WARPED_PLANKS, () -> NCBlocks.WARPED_DRAWER),
    MANGROVE("mangrove", () -> Items.MANGROVE_PLANKS, () -> NCBlocks.MANGROVE_DRAWER),
    BAMBOO("bamboo", () -> Items.BAMBOO_PLANKS, () -> NCBlocks.BAMBOO_DRAWER),
    CHERRY("cherry", () -> Items.CHERRY_PLANKS, () -> NCBlocks.CHERRY_DRAWER),
    QUARTZ("quartz", () -> Items.QUARTZ_BLOCK, () -> null),
    RED_SANDSTONE("red_sandstone", () -> Items.RED_SANDSTONE, () -> null),
    SANDSTONE("sandstone", () -> Items.SANDSTONE, () -> null),
    PRISMARINE("prismarine", () -> Items.PRISMARINE, () -> null),
    STONE_BRICKS("stone_bricks", () -> Items.STONE_BRICKS, () -> null),
    DIORITE("diorite", () -> Items.DIORITE, () -> null),
    GRANITE("granite", () -> Items.GRANITE, () -> null),
    ANDESITE("andesite", () -> Items.ANDESITE, () -> null),
    DEEPSLATE("deepslate", () -> Items.DEEPSLATE, () -> null),
    NETHER_BRICKS("nether_bricks", () -> Items.NETHER_BRICKS, () -> null),
    PURPUR("purpur", () -> Items.PURPUR_BLOCK, () -> null);

    private final String name;
    private final Supplier<Item> item;
    private final Supplier<Block> drawer;

    CountertopType(String name, Supplier<Item> item, Supplier<Block> drawer) {
        this.name = name;
        this.item = item;
        this.drawer = drawer;
    }

    public static CountertopType getFromBlock(Block drawer) {
        return Arrays.stream(CountertopType.values()).filter(block -> {
            if (block.getDrawer() != null) {
                return block.getDrawer() == drawer;
            }
            return false;
        }).findFirst().orElse(null);
    }

    @Override
    public @NotNull String getSerializedName() {
        return this.name;
    }

    public @NotNull Item getItem() {
        return this.item.get();
    }

    public Block getDrawer() {
        return this.drawer.get();
    }
}
