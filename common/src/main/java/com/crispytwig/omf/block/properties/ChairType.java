package com.crispytwig.omf.block.properties;

import net.minecraft.util.StringRepresentable;
import org.jetbrains.annotations.NotNull;

public enum ChairType implements StringRepresentable {
    TYPE_1("1"),
    TYPE_2("2");

    private final String id;

    ChairType(String id) {
        this.id = id;
    }

    @Override
    public @NotNull String getSerializedName() {
        return this.id;
    }
}
