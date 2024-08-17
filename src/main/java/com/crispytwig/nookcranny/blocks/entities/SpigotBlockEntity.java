package com.crispytwig.nookcranny.blocks.entities;

import com.crispytwig.nookcranny.blocks.SpigotBlock;
import com.crispytwig.nookcranny.registry.NCBlockEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.LayeredCauldronBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;

public class SpigotBlockEntity extends BlockEntity {
    public SpigotBlockEntity(BlockPos blockPos, BlockState blockState) {
        super(NCBlockEntities.SPIGOT, blockPos, blockState);
    }

    public static void fillingTick(Level level, BlockPos pos, BlockState state, SpigotBlockEntity spigot) {
        if (state.getValue(SpigotBlock.POWERED)) {
            if (level.getBlockState(pos.below()).is(Blocks.CAULDRON)) {
                // TODO: Perhaps make it progressively fill?
                level.setBlock(pos.below(), Blocks.WATER_CAULDRON.defaultBlockState().setValue(LayeredCauldronBlock.LEVEL, 3), 3);
            }
            if (level.getBlockState(pos.below()).is(Blocks.SPONGE)) {
                level.setBlock(pos.below(), Blocks.WET_SPONGE.defaultBlockState(), 3);
            }
        }
    }

}
