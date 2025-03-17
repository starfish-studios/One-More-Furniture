package com.crispytwig.nookcranny.blocks.properties;

import net.minecraft.util.StringRepresentable;
import org.jetbrains.annotations.NotNull;

public enum ChairType implements StringRepresentable {
    TYPE_1("1"),
    TYPE_2("2");

    private final String name;

    ChairType(String name) {
        this.name = name;
    }

    @Override
    public @NotNull String getSerializedName() {
        return this.name;
    }
}
