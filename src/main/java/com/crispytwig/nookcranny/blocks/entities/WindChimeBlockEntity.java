package com.crispytwig.nookcranny.blocks.entities;

import com.crispytwig.nookcranny.registry.NCBlockEntities;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.core.BlockPos;
import net.minecraft.util.Mth;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;

public class WindChimeBlockEntity extends BlockEntity {

    private float swingAngle = 0;
    private float prevSwingAngle = 0;
    private int tickCounter = 0;
    private float randomOffset;
    private float swaySpeed;

    public WindChimeBlockEntity(BlockPos pos, BlockState blockState) {
        super(NCBlockEntities.CHIME, pos, blockState);
    }

    public void commonTick(Level level, BlockState state) {
        if (level instanceof  ClientLevel clientLevel) {
            clientTick(clientLevel, state);
        }
    }

    public void clientTick(ClientLevel level, BlockState state) {
        tickCounter++;

        float targetAngle = (float) (Math.sin(tickCounter * swaySpeed + randomOffset) * (8 + Math.random() * 4));

        prevSwingAngle = swingAngle;
        swingAngle = Mth.lerp(0.1f, swingAngle, targetAngle);
    }

    public float getSwingAngle(float partialTick) {
        return Mth.lerp(partialTick, prevSwingAngle, swingAngle);
    }
}
