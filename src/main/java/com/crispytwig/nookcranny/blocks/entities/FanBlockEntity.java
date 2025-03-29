package com.crispytwig.nookcranny.blocks.entities;

import com.crispytwig.nookcranny.registry.NCBlockEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.Vec3i;
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

    public FanBlockEntity(BlockPos pos, BlockState blockState) {
        super(NCBlockEntities.FAN, pos, blockState);
    }

    public void commonTick(Level level, BlockState state) {
        boolean powered = state.getValue(BlockStateProperties.POWERED);

        if (level.isClientSide()) {
            if (powered) {
                rotationSpeed = Math.min(rotationSpeed + ACCELERATION, MAX_SPEED);
            } else {
                rotationSpeed = Math.max(rotationSpeed - DECELERATION, 0);
            }

            currentRotation = (currentRotation + rotationSpeed) % 360;
        }

        if (!powered) {
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
}
