package com.crispytwig.nookcranny.registry;

import com.crispytwig.nookcranny.NookAndCranny;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.level.block.SoundType;

public interface NCSoundEvents {

    private static SoundType register(String name, float volume, float pitch) {
        return new SoundType(volume, pitch, register("block." + name + ".break"), register("block." + name + ".step"), register("block." + name + ".place"), register("block." + name + ".hit"), register("block." + name + ".fall"));
    }

    static SoundEvent register(String name) {
        ResourceLocation id = new ResourceLocation(NookAndCranny.MOD_ID, name);
        return Registry.register(BuiltInRegistries.SOUND_EVENT, id, SoundEvent.createVariableRangeEvent(id));
    }
}
