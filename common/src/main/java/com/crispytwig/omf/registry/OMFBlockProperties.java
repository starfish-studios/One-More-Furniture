package com.crispytwig.omf.registry;

import com.crispytwig.omf.block.properties.ColorList;
import com.crispytwig.omf.block.properties.HorizontalConnectionType;
import net.minecraft.world.level.block.state.properties.EnumProperty;

public class OMFBlockProperties {
    public static final EnumProperty<ColorList> CUSHION = EnumProperty.create("cushion", ColorList.class);
    public static final EnumProperty<HorizontalConnectionType> HORIZONTAL_CONNECTION_TYPE = EnumProperty.create("horizontal", HorizontalConnectionType.class);
    public static final EnumProperty<ColorList> COLOR = EnumProperty.create("color", ColorList.class);

}