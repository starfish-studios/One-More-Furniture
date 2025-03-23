package com.crispytwig.nookcranny.blocks.entities;

import com.crispytwig.nookcranny.registry.NCBlockEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;

public class FanBlockEntity extends BlockEntity {
    public FanBlockEntity(BlockPos pos, BlockState blockState) {
        super(NCBlockEntities.FAN, pos, blockState);
    }
}
