package com.starfish_studios.yaf.registry;

import com.starfish_studios.yaf.block.properties.ColorList;
import com.starfish_studios.yaf.block.properties.HorizontalConnectionType;
import net.minecraft.world.level.block.state.properties.EnumProperty;

public class YAFBlockProperties {
    public static final EnumProperty<HorizontalConnectionType> HORIZONTAL_CONNECTION_TYPE = EnumProperty.create("horizontal", HorizontalConnectionType.class);
    public static final EnumProperty<ColorList> COLOR = EnumProperty.create("color", ColorList.class);

}