package com.crispytwig.omf.block.entity;

import com.crispytwig.omf.block.SpigotBlock;
import com.crispytwig.omf.registry.OMFBlockEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.LayeredCauldronBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Fluids;

public class SpigotBlockEntity extends BlockEntity {
    public SpigotBlockEntity(BlockPos blockPos, BlockState blockState) {
        super(OMFBlockEntities.SPIGOT.get(), blockPos, blockState);
    }

    private static long lastFillingTick = -1;

    public static void fillingTick(Level level, BlockPos pos, BlockState state, SpigotBlockEntity spigot) {
        if (state.getValue(SpigotBlock.POWERED)) {
            long currentTime = level.getGameTime();

            if (lastFillingTick == -1 || currentTime - lastFillingTick >= 40) {
                lastFillingTick = currentTime;

                BlockState belowState = level.getBlockState(pos.below());

                if (belowState.is(Blocks.CAULDRON)) {
                    level.setBlock(pos.below(), Blocks.WATER_CAULDRON.defaultBlockState().setValue(LayeredCauldronBlock.LEVEL, 1), 3);
                    level.playSound(null, pos, SoundEvents.BUCKET_EMPTY, SoundSource.BLOCKS, 1.0F, 1.0F);

                }
                else if (belowState.is(Blocks.WATER_CAULDRON)) {
                    int currentLevel = belowState.getValue(LayeredCauldronBlock.LEVEL);

                    if (currentLevel < 3) {
                        level.setBlock(pos.below(), Blocks.WATER_CAULDRON.defaultBlockState().setValue(LayeredCauldronBlock.LEVEL, currentLevel + 1), 3);
                        level.playSound(null, pos, SoundEvents.BUCKET_EMPTY, SoundSource.BLOCKS, 1.0F, 1.0F);
                    } else if (currentLevel == 3 && !state.getValue(SpigotBlock.WATERLOGGED)) {
                        level.setBlock(
                                pos.below(),
                                Blocks.WATER_CAULDRON.defaultBlockState().setValue(LayeredCauldronBlock.LEVEL, 3),
                                3
                        );
                        level.playSound(null, pos, SoundEvents.BUCKET_EMPTY, SoundSource.BLOCKS, 1.0F, 1.0F);
                        BlockState newState = state.setValue(SpigotBlock.WATERLOGGED, true);
                        level.setBlock(pos, newState, 3);
                        level.scheduleTick(pos, Fluids.WATER, Fluids.WATER.getTickDelay(level));
                    }
                }

                if (belowState.is(Blocks.SPONGE)) {
                    level.setBlock(pos.below(), Blocks.WET_SPONGE.defaultBlockState(), 3);
                    level.playSound(null, pos, SoundEvents.BUCKET_EMPTY_TADPOLE, SoundSource.BLOCKS, 1.0F, 1.0F);
                }
            }
        }
    }



}
