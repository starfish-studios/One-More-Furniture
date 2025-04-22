package com.starfish_studios.yaf.events;

import com.starfish_studios.yaf.block.ChairBlock;
import com.starfish_studios.yaf.block.entity.ChairBlockEntity;
import com.starfish_studios.yaf.block.properties.Cushionable;
import com.starfish_studios.yaf.registry.YAFItems;
import dev.architectury.event.EventResult;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;

import static com.starfish_studios.yaf.block.ChairBlock.BACK;

public class ChairInteractions {

    public static EventResult interact(Player player, InteractionHand hand, BlockPos pos, Direction direction) {
        var level = player.level();
        BlockState blockState = level.getBlockState(pos);
        Block block = blockState.getBlock();

        if (block instanceof ChairBlock) {
            if (player.isShiftKeyDown() && player.getItemInHand(hand).is(YAFItems.COPPER_SAW.get())) {
                if (!blockState.getValue(BACK)) {
                    return EventResult.interruptFalse();
                }

                if (level.getBlockEntity(pos) instanceof ChairBlockEntity chairBlockEntity) {
                    chairBlockEntity.setChairType(chairBlockEntity.getChairType().next());
                }

                level.setBlock(pos, blockState, 3);
                level.playSound(null, pos, blockState.getBlock().getSoundType(blockState).getPlaceSound(), SoundSource.BLOCKS, 1.0f, 1.0f);
                return EventResult.interruptTrue();
            }
        }

        return EventResult.pass();
    }
}
