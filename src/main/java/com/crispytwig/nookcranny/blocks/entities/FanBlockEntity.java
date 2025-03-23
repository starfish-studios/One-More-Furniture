package com.crispytwig.nookcranny.blocks.entities;

import com.crispytwig.nookcranny.registry.NCBlockEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Entity;
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
    private static final double SCALE = 0.2;

    public FanBlockEntity(BlockPos pos, BlockState blockState) {
        super(NCBlockEntities.FAN, pos, blockState);
    }

    public void commonTick(Level level, BlockState state) {
        if (level.isClientSide()) {
            return;
        }

        BlockPos pos = getBlockPos();
        Direction direction = state.getValue(BlockStateProperties.FACING);
        if (direction == Direction.DOWN) {
            return;
        }

        Vec3 fanPos = Vec3.atCenterOf(pos);
        Vec3 pushDirection = Vec3.atLowerCornerOf(direction.getNormal()).normalize();

        AABB affectedArea = new AABB(pos.relative(direction, 1)).expandTowards(pushDirection.scale(MAX_DISTANCE));
        List<Entity> entities = level.getEntities(null, affectedArea);

        for (Entity entity : entities) {
            Vec3 entityPos = entity.position();
            double distance = fanPos.distanceTo(entityPos);

            double force = Mth.lerp(distance / MAX_DISTANCE, MAX_FORCE, MIN_FORCE);

            Vec3 currentMotion = entity.getDeltaMovement();
            Vec3 pushVelocity = pushDirection.scale(force);
            Vec3 newMotion = currentMotion.add(pushVelocity).scale(1 - SCALE).add(pushVelocity.scale(SCALE));

            entity.setDeltaMovement(newMotion);
            entity.hurtMarked = true;
        }
    }
}
