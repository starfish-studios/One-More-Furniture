package com.crispytwig.omf.registry;

import net.minecraft.world.item.DyeColor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;

public enum OMFDyeColor {
    WHITE("white", DyeColor.WHITE, Blocks.WHITE_WOOL),
    ORANGE("orange", DyeColor.ORANGE, Blocks.ORANGE_WOOL),
    MAGENTA("magenta", DyeColor.MAGENTA, Blocks.MAGENTA_WOOL),
    LIGHT_BLUE("light_blue", DyeColor.LIGHT_BLUE, Blocks.LIGHT_BLUE_WOOL),
    YELLOW("yellow",DyeColor.YELLOW,  Blocks.YELLOW_WOOL),
    LIME("lime", DyeColor.LIME, Blocks.LIME_WOOL),
    PINK("pink", DyeColor.PINK, Blocks.PINK_WOOL),
    GRAY("gray",DyeColor.GRAY,  Blocks.GRAY_WOOL),
    LIGHT_GRAY("light_gray", DyeColor.LIGHT_GRAY, Blocks.LIGHT_GRAY_WOOL),
    CYAN("cyan", DyeColor.CYAN, Blocks.CYAN_WOOL),
    PURPLE("purple", DyeColor.PURPLE, Blocks.PURPLE_WOOL),
    BLUE("blue", DyeColor.BLUE, Blocks.BLUE_WOOL),
    BROWN("brown",DyeColor.BROWN,  Blocks.BROWN_WOOL),
    GREEN("green", DyeColor.GREEN, Blocks.GREEN_WOOL),
    RED("red", DyeColor.RED,  Blocks.RED_WOOL),
    BLACK("black", DyeColor.BLACK, Blocks.BLACK_WOOL);

    private final String name;
    private final Block baseBlock;
    private final DyeColor dyeColor;

    OMFDyeColor(String name, DyeColor dyeColor, Block baseBlock) {
        this.name = name;
        this.baseBlock = baseBlock;
        this.dyeColor = dyeColor;
    }

    public String getName() {
        return name;
    }

    public Block getBaseBlock() {
        return baseBlock;
    }

    public DyeColor getDyeColor(){
        return dyeColor;
    }

    public BlockBehaviour.Properties getBlockProperties() {
        return BlockBehaviour.Properties.copy(baseBlock).noOcclusion();
    }
}
