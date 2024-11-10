//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.crispytwig.nookcranny.blocks.properties;

import net.minecraft.util.StringRepresentable;
import org.jetbrains.annotations.NotNull;

public enum CountertopType implements StringRepresentable {
    OAK("oak"),
    SPRUCE("spruce"),
    BIRCH("birch"),
    JUNGLE("jungle"),
    ACACIA("acacia"),
    DARK_OAK("dark_oak"),
    CRIMSON("crimson"),
    WARPED("warped"),
    MANGROVE("mangrove"),
    BAMBOO("bamboo"),
    CHERRY("cherry");

    private final String name;

    CountertopType(String string2) {
        this.name = string2;
    }

    public String toString() {
        return this.getSerializedName();
    }

    public @NotNull String getSerializedName() {
        return this.name;
    }
}
