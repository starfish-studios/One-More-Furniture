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
    OAK("oak", () -> Items.OAK_PLANKS),
    SPRUCE("spruce", () -> Items.SPRUCE_PLANKS),
    BIRCH("birch", () -> Items.BIRCH_PLANKS),
    JUNGLE("jungle", () -> Items.JUNGLE_PLANKS),
    ACACIA("acacia", () -> Items.ACACIA_PLANKS),
    DARK_OAK("dark_oak", () -> Items.DARK_OAK_PLANKS),
    CRIMSON("crimson", () -> Items.CRIMSON_PLANKS),
    WARPED("warped", () -> Items.WARPED_PLANKS),
    MANGROVE("mangrove", () -> Items.MANGROVE_PLANKS),
    BAMBOO("bamboo", () -> Items.BAMBOO_PLANKS),
    CHERRY("cherry", () -> Items.CHERRY_PLANKS),
    QUARTZ("quartz", () -> Items.QUARTZ_BLOCK),
    RED_SANDSTONE("red_sandstone", () -> Items.RED_SANDSTONE),
    SANDSTONE("sandstone", () -> Items.SANDSTONE),
    PRISMARINE("prismarine", () -> Items.PRISMARINE),
    STONE_BRICKS("stone_bricks", () -> Items.STONE_BRICKS),
    DIORITE("diorite", () -> Items.DIORITE),
    GRANITE("granite", () -> Items.GRANITE),
    ANDESITE("andesite", () -> Items.ANDESITE),
    DEEPSLATE("deepslate", () -> Items.DEEPSLATE),
    NETHER_BRICKS("nether_bricks", () -> Items.NETHER_BRICKS),
    PURPUR("purpur", () -> Items.PURPUR_BLOCK);

    private final String name;
    private final Supplier<Item> item;

    CountertopType(String name, Supplier<Item> item) {
        this.name = name;
        this.item = item;
    }

    public static CountertopType getFromBlock(Item drawer) {
        return Arrays.stream(CountertopType.values()).filter(plank -> {
            plank.getItem();
            return plank.getItem() == drawer;
        }).findFirst().orElse(null);
    }

    @Override
    public @NotNull String getSerializedName() {
        return this.name;
    }

    public @NotNull Item getItem() {
        return this.item.get();
    }
}
