//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.starfish_studios.yaf.block.properties;

import net.minecraft.util.StringRepresentable;
import org.jetbrains.annotations.NotNull;

public enum FoodList implements StringRepresentable {
    EMPTY("empty"),
    APPLE("apple"),
    BAKED_POTATO("baked_potato"),
    BEETROOT("beetroot"),
    BREAD("bread"),
    CARROT("carrot"),
    COOKIE("cookie"),
    POTATO("potato");

    private final String name;

    FoodList(String string2) {
        this.name = string2;
    }

    public String toString() {
        return this.getSerializedName();
    }

    public @NotNull String getSerializedName() {
        return this.name;
    }
}
