package com.starfish_studios.yaf.block.properties;

import com.starfish_studios.yaf.events.CushionableEvents;
import net.minecraft.core.BlockPos;
import net.minecraft.world.Containers;
import net.minecraft.world.level.Level;

public interface Cushionable {
    ColorList getColor();

    void setColor(ColorList color);

    default void dropCarpet(Level level, BlockPos pos){
        if (getColor() != ColorList.EMPTY) {
            var carpet =  CushionableEvents.REVERSE_CARPET_MAP.get(getColor());
            if (carpet != null) {
                Containers.dropItemStack(level, pos.getX() + 0.5, pos.getY() + 0.5, pos.getZ() + 0.5, carpet.getDefaultInstance());
            }
        }
    }
}
