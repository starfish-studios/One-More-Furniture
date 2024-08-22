package com.crispytwig.nookcranny.registry;

import com.crispytwig.nookcranny.NookAndCranny;
import com.mojang.serialization.Codec;
import net.minecraft.core.Registry;
import net.minecraft.core.particles.DustParticleOptions;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleType;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;

import java.util.function.Function;

public class NCParticles {

    public static SimpleParticleType register(String id, SimpleParticleType type) {
        return Registry.register(BuiltInRegistries.PARTICLE_TYPE, new ResourceLocation(NookAndCranny.MOD_ID, id), type);
    }
}
