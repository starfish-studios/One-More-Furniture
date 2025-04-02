//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.crispytwig.omf.block.properties;

import net.minecraft.util.StringRepresentable;
import org.jetbrains.annotations.NotNull;

public enum FlagStatus implements StringRepresentable {
    UP("up"),
    DOWN("down");

    private final String name;

    FlagStatus(String string2) {
        this.name = string2;
    }

    public String toString() {
        return this.getSerializedName();
    }

    public @NotNull String getSerializedName() {
        return this.name;
    }
}
