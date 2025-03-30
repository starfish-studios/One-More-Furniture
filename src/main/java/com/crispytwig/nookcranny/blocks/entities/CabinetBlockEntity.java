package com.crispytwig.nookcranny.blocks.entities;

import com.crispytwig.nookcranny.blocks.CabinetBlock;
import com.crispytwig.nookcranny.registry.NCBlockEntities;
import com.mojang.datafixers.util.Pair;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

public class CabinetBlockEntity extends AbstractDrawerBlockEntity {
    public CabinetBlockEntity(BlockPos blockPos, BlockState blockState) {
        super(NCBlockEntities.CABINET, blockPos, blockState);
    }

    void updateLeftDoor(BlockState state, boolean open) {
        assert this.level != null;
        if (state.hasProperty(CabinetBlock.LEFT_OPEN)) {
            this.level.setBlock(this.getBlockPos(), state.setValue(CabinetBlock.LEFT_OPEN, open), 3);
        }
    }

    void updateRightDoor(BlockState state, boolean open) {
        assert this.level != null;
        if (state.hasProperty(CabinetBlock.RIGHT_OPEN)) {
            this.level.setBlock(this.getBlockPos(), state.setValue(CabinetBlock.RIGHT_OPEN, open), 3);
        }
    }

    @Override
    public void startOpen(@NotNull Player player) {
        HitResult hitResult = player.pick(10, 1, false);
        if (hitResult.getType() == HitResult.Type.BLOCK) {
            BlockHitResult blockHitResult = (BlockHitResult) hitResult;
            Vec3 localHitPos = blockHitResult.getLocation().subtract(Vec3.atCenterOf(blockHitResult.getBlockPos()));
            int relativePos = getRelativeDrawerPos(blockHitResult.getDirection(), localHitPos, true);
            if (relativePos > 0) {
                this.updateRightDoor(this.getBlockState(), true);
            } else {
                this.updateLeftDoor(this.getBlockState(), true);
            }
        }
        if (!this.remove && !player.isSpectator()) {
            this.openersCounter.incrementOpeners(player, Objects.requireNonNull(this.getLevel()), this.getBlockPos(), this.getBlockState());
        }
    }

    @Override
    public void stopOpen(@NotNull Player player) {
        HitResult hitResult = player.pick(10, 1, false);
        if (hitResult.getType() == HitResult.Type.BLOCK) {
            BlockHitResult blockHitResult = (BlockHitResult) hitResult;
            Vec3 localHitPos = blockHitResult.getLocation().subtract(Vec3.atCenterOf(blockHitResult.getBlockPos()));
            int relativePos = getRelativeDrawerPos(blockHitResult.getDirection(), localHitPos, true);
            if (relativePos > 0) {
                this.updateRightDoor(this.getBlockState(), false);
            } else {
                this.updateLeftDoor(this.getBlockState(), false);
            }
        }
        if (!this.remove && !player.isSpectator()) {
            this.openersCounter.decrementOpeners(player, Objects.requireNonNull(this.getLevel()), this.getBlockPos(), this.getBlockState());
        }
    }
}
