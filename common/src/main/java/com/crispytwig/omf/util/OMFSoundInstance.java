package com.crispytwig.omf.util;

import com.crispytwig.omf.block.entity.FanBlockEntity;
import com.crispytwig.omf.registry.OMFSoundEvents;
import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.sounds.AbstractTickableSoundInstance;
import net.minecraft.client.resources.sounds.SoundInstance;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.RandomSource;
import org.jetbrains.annotations.NotNull;

import java.util.Collections;
import java.util.Set;
import java.util.WeakHashMap;

public class OMFSoundInstance extends AbstractTickableSoundInstance {

    private static final Set<FanBlockEntity> activeSounds = Collections.newSetFromMap(new WeakHashMap<>());

    private final FanBlockEntity fan;

    private final int fadeDuration = 40;
    private int fadeInTicks = 1;
    private int fadeOutTicks = 0;
    private boolean fadingOut = false;

    public OMFSoundInstance(FanBlockEntity fan, SoundEvent soundEvent, RandomSource randomSource) {
        super(soundEvent, SoundSource.BLOCKS, randomSource);
        this.fan = fan;
        this.looping = true;
        this.delay = 0;

        this.x = fan.getBlockPos().getX() + 0.5;
        this.y = fan.getBlockPos().getY() + 0.5;
        this.z = fan.getBlockPos().getZ() + 0.5;
    }

    public static void tryPlay(FanBlockEntity fan) {
        if (!activeSounds.contains(fan)) {
            OMFSoundInstance instance = new OMFSoundInstance(fan, OMFSoundEvents.FAN_AMBIENCE.get(), SoundInstance.createUnseededRandom());
            Minecraft.getInstance().getSoundManager().queueTickingSound(instance);
            activeSounds.add(fan);
        }
    }

    public static void stop(FanBlockEntity fan) {
        activeSounds.remove(fan);
    }

    @Override
    public void tick() {
        if (fan.isRemoved() || !fan.fanOn) {
            if (!fadingOut) {
                fadingOut = true;
                fadeOutTicks = 0;
            }
            fadeOutTicks++;
            if (fadeOutTicks >= fadeDuration) {
                this.stop();
                stop(fan);
            }
        } else {
            if (fadingOut) {
                fadingOut = false;
                fadeOutTicks = 0;
            }
            if (fadeInTicks < fadeDuration) {
                fadeInTicks++;
            }
        }
    }

    @Override
    public float getVolume() {
        float factor;
        if (fadingOut) {
            factor = 1.0f - (fadeOutTicks / (float) fadeDuration);
        } else {
            factor = (fadeInTicks < fadeDuration) ? fadeInTicks / (float) fadeDuration : 1.0f;
        }
        float baseVolume = 0.5f;
        return baseVolume * factor * this.sound.getVolume().sample(this.random);
    }

    @Override
    public boolean canPlaySound() {
        return !fan.isRemoved() && (fan.fanOn || fadingOut);
    }

    @Override
    public SoundInstance.@NotNull Attenuation getAttenuation() {
        return SoundInstance.Attenuation.LINEAR;
    }
}
