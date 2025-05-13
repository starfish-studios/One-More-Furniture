package com.starfish_studios.yaf.mixin;

import net.minecraft.network.chat.Component;
import net.minecraft.world.level.block.entity.BaseContainerBlockEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(BaseContainerBlockEntity.class)
public interface CustomNameAccessor {

    @Accessor("name")
    Component getName();

    @Accessor("name")
    void setName(Component name);
}
