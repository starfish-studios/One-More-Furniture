package com.crispytwig.nookcranny.registry;

import com.crispytwig.nookcranny.NookAndCranny;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.level.block.SoundType;

public interface NCSoundEvents {

    SoundEvent WINDCHIME_AMETHYST = register("block.windchimes.amethyst");
    SoundEvent WINDCHIME_BAMBOO = register("block.windchimes.bamboo");
    SoundEvent WINDCHIME_BONE = register("block.windchimes.bone");
    SoundEvent WINDCHIME_COPPER = register("block.windchimes.copper");
    SoundEvent WINDCHIME_ECHO_SHARD = register("block.windchimes.echo_shard");
    SoundEvent SPIGOT_ON = register("block.spigot.on");
    SoundEvent SPIGOT_OFF = register("block.spigot.off");

    private static SoundType register(String name, float volume, float pitch) {
        return new SoundType(volume, pitch, register("block." + name + ".break"), register("block." + name + ".step"), register("block." + name + ".place"), register("block." + name + ".hit"), register("block." + name + ".fall"));
    }

    static SoundEvent register(String name) {
        ResourceLocation id = new ResourceLocation(NookAndCranny.MOD_ID, name);
        return Registry.register(BuiltInRegistries.SOUND_EVENT, id, SoundEvent.createVariableRangeEvent(id));
    }
}
