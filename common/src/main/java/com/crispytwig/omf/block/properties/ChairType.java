package com.crispytwig.omf.block.properties;

import net.minecraft.util.StringRepresentable;
import org.jetbrains.annotations.NotNull;

public enum ChairType implements StringRepresentable {
    TYPE_1("1"),
    TYPE_2("2"),
    TYPE_3("3"),
    TYPE_4("4"),
    TYPE_5("5"),
    TYPE_6("6"),
    TYPE_7("7");

    private final String id;

    ChairType(String id) {
        this.id = id;
    }

    @Override
    public @NotNull String getSerializedName() {
        return this.id;
    }
}
