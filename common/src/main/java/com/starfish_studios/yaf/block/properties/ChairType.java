package com.starfish_studios.yaf.block.properties;

import com.mojang.serialization.Codec;
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

    public final String id;

    public static final Codec<ChairType> CODEC = StringRepresentable.fromEnum(ChairType::values);

    ChairType(String id) {
        this.id = id;
    }

    public ChairType next() {
        ChairType[] values = ChairType.values();
        int nextOrdinal = (this.ordinal() + 1) % values.length;
        return values[nextOrdinal];
    }

    @Override
    public @NotNull String getSerializedName() {
        return this.id;
    }
}
