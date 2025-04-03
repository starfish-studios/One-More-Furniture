package com.crispytwig.omf.events;

import com.crispytwig.omf.block.ChairBlock;
import com.crispytwig.omf.block.properties.Cushionable;
import com.crispytwig.omf.registry.OMFItems;
import dev.architectury.event.EventResult;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;

public class ChairInteractions implements Cushionable {

    public static EventResult interact(Player player, InteractionHand hand, BlockPos pos, Direction direction) {
        var level = player.level();
        BlockState blockState = level.getBlockState(pos);
        Block block = blockState.getBlock();

        if (block instanceof ChairBlock) {
            if (player.isShiftKeyDown() && player.getItemInHand(hand).is(OMFItems.COPPER_SAW.get())) {
                blockState = blockState.cycle(ChairBlock.BACK_TYPE);

                level.setBlock(pos, blockState, 3);
                level.playSound(null, pos, blockState.getBlock().getSoundType(blockState).getPlaceSound(), SoundSource.BLOCKS, 1.0f, 1.0f);
                return EventResult.interruptTrue();
            }
        }

        return EventResult.pass();
    }
}
