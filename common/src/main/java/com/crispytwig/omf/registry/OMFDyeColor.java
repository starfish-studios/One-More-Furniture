package com.crispytwig.omf.registry;

import net.minecraft.world.item.DyeColor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;

public enum OMFDyeColor {
    WHITE("white", DyeColor.WHITE, Blocks.WHITE_WOOL, Blocks.WHITE_CARPET),
    ORANGE("orange", DyeColor.ORANGE, Blocks.ORANGE_WOOL, Blocks.ORANGE_CARPET),
    MAGENTA("magenta", DyeColor.MAGENTA, Blocks.MAGENTA_WOOL, Blocks.MAGENTA_CARPET),
    LIGHT_BLUE("light_blue", DyeColor.LIGHT_BLUE, Blocks.LIGHT_BLUE_WOOL, Blocks.LIGHT_BLUE_CARPET),
    YELLOW("yellow", DyeColor.YELLOW, Blocks.YELLOW_WOOL, Blocks.YELLOW_CARPET),
    LIME("lime", DyeColor.LIME, Blocks.LIME_WOOL, Blocks.LIME_CARPET),
    PINK("pink", DyeColor.PINK, Blocks.PINK_WOOL, Blocks.PINK_CARPET),
    GRAY("gray", DyeColor.GRAY, Blocks.GRAY_WOOL, Blocks.GRAY_CARPET),
    LIGHT_GRAY("light_gray", DyeColor.LIGHT_GRAY, Blocks.LIGHT_GRAY_WOOL, Blocks.LIGHT_GRAY_CARPET),
    CYAN("cyan", DyeColor.CYAN, Blocks.CYAN_WOOL, Blocks.CYAN_CARPET),
    PURPLE("purple", DyeColor.PURPLE, Blocks.PURPLE_WOOL, Blocks.PURPLE_CARPET),
    BLUE("blue", DyeColor.BLUE, Blocks.BLUE_WOOL, Blocks.BLUE_CARPET),
    BROWN("brown", DyeColor.BROWN, Blocks.BROWN_WOOL, Blocks.BROWN_CARPET),
    GREEN("green", DyeColor.GREEN, Blocks.GREEN_WOOL, Blocks.GREEN_CARPET),
    RED("red", DyeColor.RED, Blocks.RED_WOOL, Blocks.RED_CARPET),
    BLACK("black", DyeColor.BLACK, Blocks.BLACK_WOOL, Blocks.BLACK_CARPET);

    private final String name;
    private final Block wool;
    private final Block carpet;
    private final DyeColor dyeColor;

    OMFDyeColor(String name, DyeColor dyeColor, Block wool, Block carpet) {
        this.name = name;
        this.wool = wool;
        this.carpet = carpet;
        this.dyeColor = dyeColor;
    }

    public String getName() {
        return name;
    }

    public Block getWool() {
        return wool;
    }

    public Block getCarpet() {
        return carpet;
    }

    public DyeColor getDyeColor() {
        return dyeColor;
    }

    public BlockBehaviour.Properties getBlockProperties() {
        return BlockBehaviour.Properties.copy(wool).noOcclusion();
    }
}
