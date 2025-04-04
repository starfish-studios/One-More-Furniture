package com.crispytwig.omf.util;

import com.crispytwig.omf.block.entity.FanBlockEntity;
import com.crispytwig.omf.registry.OMFSoundEvents;
import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.sounds.AbstractTickableSoundInstance;
import net.minecraft.client.resources.sounds.SoundInstance;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.RandomSource;

import java.util.Collections;
import java.util.Set;
import java.util.WeakHashMap;

public class OMFSoundInstance extends AbstractTickableSoundInstance {

    private static final Set<FanBlockEntity> activeSounds = Collections.newSetFromMap(new WeakHashMap<>());

    private final FanBlockEntity fan;

    public OMFSoundInstance(FanBlockEntity fan, SoundEvent soundEvent, RandomSource randomSource) {
        super(soundEvent, SoundSource.BLOCKS, randomSource);
        this.fan = fan;
        this.looping = true;
        this.delay = 0;
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
            stop();
            stop(fan);
        }
    }

    @Override
    public boolean canPlaySound() {
        return !fan.isRemoved() && fan.fanOn;
    }
}
