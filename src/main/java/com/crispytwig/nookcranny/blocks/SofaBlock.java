package com.crispytwig.nookcranny.blocks;

import com.crispytwig.nookcranny.registry.NCBlocks;
import com.google.common.collect.Maps;
import net.minecraft.Util;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.item.DyeItem;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.*;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.phys.BlockHitResult;
import org.jetbrains.annotations.Nullable;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class SofaBlock extends SeatBlock implements SimpleWaterloggedBlock {
    public static final DirectionProperty FACING = BlockStateProperties.HORIZONTAL_FACING;
    public static final BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;

    public SofaBlock(BlockBehaviour.Properties properties) {
        super(properties);
    }

    public InteractionResult use(BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hit) {
        if (player.getItemInHand(hand).getItem() instanceof DyeItem) {
            if (state.getBlock() instanceof SofaBlock) {
                if (((DyeItem) player.getItemInHand(hand).getItem()).getDyeColor() == DyeColor.WHITE && !state.getBlock().equals(NCBlocks.WHITE_SOFA)) {
                    level.setBlockAndUpdate(pos, NCBlocks.WHITE_SOFA.defaultBlockState().setValue(FACING, state.getValue(FACING)).setValue(WATERLOGGED, state.getValue(WATERLOGGED)));
                } else if (((DyeItem) player.getItemInHand(hand).getItem()).getDyeColor() == DyeColor.ORANGE && !state.getBlock().equals(NCBlocks.ORANGE_SOFA)) {
                    level.setBlockAndUpdate(pos, NCBlocks.ORANGE_SOFA.defaultBlockState().setValue(FACING, state.getValue(FACING)).setValue(WATERLOGGED, state.getValue(WATERLOGGED)));
                } else if (((DyeItem) player.getItemInHand(hand).getItem()).getDyeColor() == DyeColor.MAGENTA && !state.getBlock().equals(NCBlocks.MAGENTA_SOFA)) {
                    level.setBlockAndUpdate(pos, NCBlocks.MAGENTA_SOFA.defaultBlockState().setValue(FACING, state.getValue(FACING)).setValue(WATERLOGGED, state.getValue(WATERLOGGED)));
                } else if (((DyeItem) player.getItemInHand(hand).getItem()).getDyeColor() == DyeColor.LIGHT_BLUE && !state.getBlock().equals(NCBlocks.LIGHT_BLUE_SOFA)) {
                    level.setBlockAndUpdate(pos, NCBlocks.LIGHT_BLUE_SOFA.defaultBlockState().setValue(FACING, state.getValue(FACING)).setValue(WATERLOGGED, state.getValue(WATERLOGGED)));
                } else if (((DyeItem) player.getItemInHand(hand).getItem()).getDyeColor() == DyeColor.YELLOW && !state.getBlock().equals(NCBlocks.YELLOW_SOFA)) {
                    level.setBlockAndUpdate(pos, NCBlocks.YELLOW_SOFA.defaultBlockState().setValue(FACING, state.getValue(FACING)).setValue(WATERLOGGED, state.getValue(WATERLOGGED)));
                } else if (((DyeItem) player.getItemInHand(hand).getItem()).getDyeColor() == DyeColor.LIME && !state.getBlock().equals(NCBlocks.LIME_SOFA)) {
                    level.setBlockAndUpdate(pos, NCBlocks.LIME_SOFA.defaultBlockState().setValue(FACING, state.getValue(FACING)).setValue(WATERLOGGED, state.getValue(WATERLOGGED)));
                } else if (((DyeItem) player.getItemInHand(hand).getItem()).getDyeColor() == DyeColor.PINK && !state.getBlock().equals(NCBlocks.PINK_SOFA)) {
                    level.setBlockAndUpdate(pos, NCBlocks.PINK_SOFA.defaultBlockState().setValue(FACING, state.getValue(FACING)).setValue(WATERLOGGED, state.getValue(WATERLOGGED)));
                } else if (((DyeItem) player.getItemInHand(hand).getItem()).getDyeColor() == DyeColor.GRAY && !state.getBlock().equals(NCBlocks.GRAY_SOFA)) {
                    level.setBlockAndUpdate(pos, NCBlocks.GRAY_SOFA.defaultBlockState().setValue(FACING, state.getValue(FACING)).setValue(WATERLOGGED, state.getValue(WATERLOGGED)));
                } else if (((DyeItem) player.getItemInHand(hand).getItem()).getDyeColor() == DyeColor.LIGHT_GRAY && !state.getBlock().equals(NCBlocks.LIGHT_GRAY_SOFA)) {
                    level.setBlockAndUpdate(pos, NCBlocks.LIGHT_GRAY_SOFA.defaultBlockState().setValue(FACING, state.getValue(FACING)).setValue(WATERLOGGED, state.getValue(WATERLOGGED)));
                } else if (((DyeItem) player.getItemInHand(hand).getItem()).getDyeColor() == DyeColor.CYAN && !state.getBlock().equals(NCBlocks.CYAN_SOFA)) {
                    level.setBlockAndUpdate(pos, NCBlocks.CYAN_SOFA.defaultBlockState().setValue(FACING, state.getValue(FACING)).setValue(WATERLOGGED, state.getValue(WATERLOGGED)));
                } else if (((DyeItem) player.getItemInHand(hand).getItem()).getDyeColor() == DyeColor.PURPLE && !state.getBlock().equals(NCBlocks.PURPLE_SOFA)) {
                    level.setBlockAndUpdate(pos, NCBlocks.PURPLE_SOFA.defaultBlockState().setValue(FACING, state.getValue(FACING)).setValue(WATERLOGGED, state.getValue(WATERLOGGED)));
                } else if (((DyeItem) player.getItemInHand(hand).getItem()).getDyeColor() == DyeColor.BLUE && !state.getBlock().equals(NCBlocks.BLUE_SOFA)) {
                    level.setBlockAndUpdate(pos, NCBlocks.BLUE_SOFA.defaultBlockState().setValue(FACING, state.getValue(FACING)).setValue(WATERLOGGED, state.getValue(WATERLOGGED)));
                } else if (((DyeItem) player.getItemInHand(hand).getItem()).getDyeColor() == DyeColor.BROWN && !state.getBlock().equals(NCBlocks.BROWN_SOFA)) {
                    level.setBlockAndUpdate(pos, NCBlocks.BROWN_SOFA.defaultBlockState().setValue(FACING, state.getValue(FACING)).setValue(WATERLOGGED, state.getValue(WATERLOGGED)));
                } else if (((DyeItem) player.getItemInHand(hand).getItem()).getDyeColor() == DyeColor.GREEN && !state.getBlock().equals(NCBlocks.GREEN_SOFA)) {
                    level.setBlockAndUpdate(pos, NCBlocks.GREEN_SOFA.defaultBlockState().setValue(FACING, state.getValue(FACING)).setValue(WATERLOGGED, state.getValue(WATERLOGGED)));
                } else if (((DyeItem) player.getItemInHand(hand).getItem()).getDyeColor() == DyeColor.RED && !state.getBlock().equals(NCBlocks.RED_SOFA)) {
                    level.setBlockAndUpdate(pos, NCBlocks.RED_SOFA.defaultBlockState().setValue(FACING, state.getValue(FACING)).setValue(WATERLOGGED, state.getValue(WATERLOGGED)));
                } else if (((DyeItem) player.getItemInHand(hand).getItem()).getDyeColor() == DyeColor.BLACK && !state.getBlock().equals(NCBlocks.BLACK_SOFA)) {
                    level.setBlockAndUpdate(pos, NCBlocks.BLACK_SOFA.defaultBlockState().setValue(FACING, state.getValue(FACING)).setValue(WATERLOGGED, state.getValue(WATERLOGGED)));
                }
                level.playSound(null, (double)pos.getX() + 0.5, (double)pos.getY() + 0.5, (double)pos.getZ() + 0.5, SoundEvents.WOOL_BREAK, SoundSource.BLOCKS, 1.0F, 1.0F);

                for(int i = 0; i < 4; ++i) {
                    double d = level.random.nextGaussian() * 0.025;
                    double e = level.random.nextGaussian() * 0.025;
                    double f = level.random.nextGaussian() * 0.025;
                    level.addParticle(ParticleTypes.POOF, (double)pos.getX() + 0.5, (double)pos.getY() + 0.5, (double)pos.getZ() + 0.5, d, e, f);
                }

//                level.addParticle(ParticleTypes.POOF, (double)pos.getX() + 0.5, (double)pos.getY() + 1.0, (double)pos.getZ() + 0.5, 0.0D, 0.0D, 0.0D);
                return InteractionResult.SUCCESS;
            }
        } else {
            return super.use(state, level, pos, player, hand, hit);
        }
        return InteractionResult.PASS;
    }

    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> stateDefinition) {
        stateDefinition.add(FACING, WATERLOGGED);
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

    @Override
    public float seatHeight(BlockState state) {
        return 0.4F;
    }

    @Override
    public BlockPos primaryDismountLocation(Level level, BlockState state, BlockPos pos) {
        return pos.relative(state.getValue(FACING));
    }

    @Override
    public float setRiderRotation(BlockState state, Entity entity) {
        return state.getValue(FACING).toYRot();
    }

    @Nullable
    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        boolean waterlogged = context.getLevel().getFluidState(context.getClickedPos()).getType() == Fluids.WATER;
        return this.defaultBlockState()
                .setValue(FACING, context.getHorizontalDirection().getOpposite())
                .setValue(WATERLOGGED, waterlogged);
    }

    @Override
    public boolean propagatesSkylightDown(BlockState blockState, BlockGetter blockGetter, BlockPos blockPos) {
        return true;
    }

    @Override
    public BlockState updateShape(BlockState state, Direction direction, BlockState neighborState, LevelAccessor level, BlockPos currentPos, BlockPos neighborPos) {
        if (state.getValue(WATERLOGGED)) level.scheduleTick(currentPos, Fluids.WATER, Fluids.WATER.getTickDelay(level));
        return super.updateShape(state, direction, neighborState, level, currentPos, neighborPos);
    }
}
