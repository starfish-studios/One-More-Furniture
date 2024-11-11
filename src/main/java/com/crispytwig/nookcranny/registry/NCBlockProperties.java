package com.crispytwig.nookcranny.registry;

import com.crispytwig.nookcranny.blocks.properties.ColorList;
import com.crispytwig.nookcranny.blocks.properties.HorizontalConnectionType;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.EnumProperty;

public class NCBlockProperties {
    public static final EnumProperty<ColorList> CUSHION = EnumProperty.create("cushion", ColorList.class);
    public static final EnumProperty<HorizontalConnectionType> HORIZONTAL_CONNECTION_TYPE = EnumProperty.create("horizontal", HorizontalConnectionType.class);
    public static final EnumProperty<ColorList> COLOR = EnumProperty.create("color", ColorList.class);

}
