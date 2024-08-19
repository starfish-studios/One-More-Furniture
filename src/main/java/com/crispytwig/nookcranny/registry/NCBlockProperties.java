package com.crispytwig.nookcranny.registry;

import net.minecraft.world.item.DyeColor;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.EnumProperty;

public class NCBlockProperties {
    public static final EnumProperty<DyeColor> CUSHION = EnumProperty.create("cushion", DyeColor.class);
}
