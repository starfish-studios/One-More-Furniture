package com.crispytwig.omf.util;

import com.crispytwig.omf.block.entity.FanBlockEntity;
import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.sounds.AbstractTickableSoundInstance;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.RandomSource;

public class OMFSoundInstance extends AbstractTickableSoundInstance {

    public FanBlockEntity fan;

    public OMFSoundInstance(FanBlockEntity fan, SoundEvent soundEvent, RandomSource randomSource) {
        super(soundEvent, SoundSource.BLOCKS, randomSource);
        this.fan = fan;
    }

    @Override
    public void tick() {
        if (fan.isRemoved()) {
            stopSound();
        }
    }

    public void playSound() {
        Minecraft.getInstance().getSoundManager().queueTickingSound(this);
    }

    public void stopSound(){
        stop();
    }
}
