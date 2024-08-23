package com.crispytwig.nookcranny.blocks;

import com.crispytwig.nookcranny.blocks.properties.ColorList;
import com.crispytwig.nookcranny.blocks.properties.FlagStatus;
import com.crispytwig.nookcranny.registry.NCBlockProperties;
import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Mirror;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.level.block.SimpleWaterloggedBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.phys.BlockHitResult;
import org.jetbrains.annotations.Nullable;

public class MailboxBlock extends Block implements SimpleWaterloggedBlock {
    public static final DirectionProperty FACING = BlockStateProperties.HORIZONTAL_FACING;
    public static final BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;
    public static final EnumProperty<FlagStatus> FLAG_STATUS = EnumProperty.create("flag", FlagStatus.class);
    public static final BooleanProperty OPEN = BlockStateProperties.OPEN;
    public static final BooleanProperty FENCE_BELOW = BooleanProperty.create("fence_below");
//    public static final EnumProperty<ColorList> COLOR = NCBlockProperties.COLOR;

    public MailboxBlock(Properties properties) {
        super(properties);
        this.registerDefaultState(this.getStateDefinition().any()
                .setValue(FACING, Direction.NORTH)
                .setValue(WATERLOGGED, false)
                .setValue(FLAG_STATUS, FlagStatus.DOWN)
                .setValue(OPEN, false)
                .setValue(FENCE_BELOW, false));
    }

    public BlockState open(BlockState blockState, Level level, BlockPos blockPos) {
        blockState = blockState.cycle(OPEN);
        level.setBlock(blockPos, blockState, 3);
        return blockState;
    }

    @Override
    public InteractionResult use(BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hit) {
        if (!level.isClientSide) {
            if(player.isShiftKeyDown()) {
                if (state.getValue(FLAG_STATUS) == FlagStatus.DOWN) {
                    player.displayClientMessage(Component.translatable("nookcranny.mailbox.sending").append(" XYZ: " + pos.getX() + " / " + pos.getY() + " / "  + pos.getZ())
                            .withStyle(ChatFormatting.GREEN).withStyle(ChatFormatting.ITALIC), true);
                } else if (state.getValue(FLAG_STATUS) == FlagStatus.UP) {
                    player.displayClientMessage(Component.translatable("nookcranny.mailbox.receiving").append(" XYZ: " + pos.getX() + " / " + pos.getY() + " / "  + pos.getZ())
                            .withStyle(ChatFormatting.YELLOW).withStyle(ChatFormatting.ITALIC), true);
                }


                BlockState blockState2;
                blockState2 = this.open(state, level, pos);
                float f = blockState2.getValue(FLAG_STATUS) == FlagStatus.UP ? 0.5F : 0.6F;
                level.playSound(null, pos, SoundEvents.LEVER_CLICK, SoundSource.BLOCKS, 0.3F, f);
                level.setBlock(pos, state.cycle(FLAG_STATUS), 3);
            } else {
                level.setBlock(pos, state.cycle(OPEN), 3);
                level.playSound(null, pos, state.getValue(OPEN) ? SoundEvents.IRON_TRAPDOOR_CLOSE : SoundEvents.IRON_TRAPDOOR_OPEN, SoundSource.BLOCKS, 1.0F, 1.0F);
            }
        }
        return InteractionResult.SUCCESS;
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> stateDefinition) {
        stateDefinition.add(FACING, WATERLOGGED, FLAG_STATUS, OPEN, FENCE_BELOW);
    }

    @Override
    public FluidState getFluidState(BlockState state) {
        return state.getValue(WATERLOGGED) ? Fluids.WATER.getSource(false) : super.getFluidState(state);
    }

    @Override
    public BlockState rotate(BlockState state, Rotation rotation) {
        return state.setValue(FACING, rotation.rotate(state.getValue(FACING)));
    }

    @Override
    public BlockState mirror(BlockState state, Mirror mirror) {
        return state.rotate(mirror.getRotation(state.getValue(FACING)));
    }

    @Nullable
    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        boolean waterlogged = context.getLevel().getFluidState(context.getClickedPos()).getType() == Fluids.WATER;
        return this.getStateDefinition().any()
                .setValue(FACING, context.getHorizontalDirection().getOpposite())
                .setValue(WATERLOGGED, waterlogged)
                .setValue(FLAG_STATUS, FlagStatus.DOWN)
                .setValue(OPEN, false)
                .setValue(FENCE_BELOW, context.getLevel().getBlockState(context.getClickedPos().below()).is(BlockTags.FENCES));
    }

    @Override
    public boolean propagatesSkylightDown(BlockState blockState, BlockGetter blockGetter, BlockPos blockPos) {
        return true;
    }
}
