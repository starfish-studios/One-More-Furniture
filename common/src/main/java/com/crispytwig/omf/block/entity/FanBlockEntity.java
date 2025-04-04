package com.crispytwig.omf.block.entity;

import com.crispytwig.omf.registry.OMFBlockEntities;
import com.crispytwig.omf.registry.OMFSoundEvents;
import com.crispytwig.omf.util.OMFSoundInstance;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.Vec3i;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;

import java.util.List;

public class FanBlockEntity extends BlockEntity {

    private static final int MAX_DISTANCE = 8;
    private static final double MAX_FORCE = 0.25;
    private static final double MIN_FORCE = 0.05;

    private static final float MAX_SPEED = 20.0f;
    private static final float ACCELERATION = 0.5f;
    private static final float DECELERATION = 0.3f;

    private float rotationSpeed = 0.0f;
    private float currentRotation = 0.0f;

    public boolean fanOn = false;

    private OMFSoundInstance soundInstance;

    public FanBlockEntity(BlockPos pos, BlockState blockState) {
        super(OMFBlockEntities.FAN.get(), pos, blockState);
    }

    public void commonTick(Level level, BlockState state) {
        boolean powered = state.getValue(BlockStateProperties.POWERED);

        if (soundInstance == null) {
            soundInstance = new OMFSoundInstance(this, OMFSoundEvents.FAN_ON.get(), this.getLevel().random);
        }

        if (level.isClientSide()) {
            if (powered || fanOn) {
                rotationSpeed = Math.min(rotationSpeed + ACCELERATION, MAX_SPEED);
                soundInstance.playSound();
            } else {
                rotationSpeed = Math.max(rotationSpeed - DECELERATION, 0);
                soundInstance.stopSound();
                level.playSound(null, getBlockPos(), OMFSoundEvents.FAN_OFF.get(), SoundSource.BLOCKS, 1f, 1f);
            }

            currentRotation = (currentRotation + rotationSpeed) % 360;
        }

        if (!powered && !fanOn) {
            return;
        }

        BlockPos pos = getBlockPos();
        Direction direction = state.getValue(BlockStateProperties.FACING);
        if (direction == Direction.DOWN) {
            return;
        }

        Vec3 fanPos = Vec3.atCenterOf(pos);
        Vec3i pushDirection = direction.getNormal();

        AABB affectedArea = new AABB(pos.relative(direction, 1)).inflate(1.05)
                .expandTowards(
                        pushDirection.getX() * MAX_DISTANCE,
                        pushDirection.getY() * MAX_DISTANCE,
                        pushDirection.getZ() * MAX_DISTANCE
                );

        List<Entity> entities = level.getEntities(null, affectedArea).stream()
                .filter(it -> !(it instanceof Player player && player.isCreative() && player.getAbilities().flying)).toList();

        for (Entity entity : entities) {

            double distance = entity.position().distanceTo(fanPos);
            double force = Mth.lerp(distance / MAX_DISTANCE, MAX_FORCE, MIN_FORCE);
            Vec3 currentMotion = entity.getDeltaMovement();

            Vec3 pushVelocity = new Vec3(pushDirection.getX() * force, pushDirection.getY() * force, pushDirection.getZ() * force);

            Vec3 newMotion = currentMotion.add(pushVelocity.subtract(currentMotion)).scale(0.1);

            entity.setDeltaMovement(currentMotion.add(newMotion));
        }
    }

    public float getRotationAngle(float partialTick) {
        return (currentRotation + rotationSpeed * partialTick) % 360;
    }

    @Override
    protected void saveAdditional(CompoundTag tag) {
        super.saveAdditional(tag);
        tag.putBoolean("On", fanOn);
    }

    @Override
    public void load(CompoundTag tag) {
        super.load(tag);
        fanOn = tag.getBoolean("On");
    }
}
