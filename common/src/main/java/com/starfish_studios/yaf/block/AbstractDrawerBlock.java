package com.starfish_studios.yaf.block;


import com.starfish_studios.yaf.block.entity.AbstractDrawerBlockEntity;
import com.starfish_studios.yaf.block.properties.CountertopType;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.Container;
import net.minecraft.world.Containers;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.NotNull;

import java.util.Arrays;

public abstract class AbstractDrawerBlock extends BaseEntityBlock implements SimpleWaterloggedBlock {
    public static final DirectionProperty FACING = BlockStateProperties.HORIZONTAL_FACING;
    public static final BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;

    public static final EnumProperty<CountertopType> COUNTERTOP = EnumProperty.create("countertop", CountertopType.class);

    public final Item plankBlock;

    public AbstractDrawerBlock(Item plank, Properties properties) {
        super(properties);
        this.plankBlock = plank;
    }

    public Item getPlanks(){
        return plankBlock;
    }

    @Override
    public @NotNull VoxelShape getShape(@NotNull BlockState blockState, @NotNull BlockGetter blockGetter, @NotNull BlockPos blockPos, @NotNull CollisionContext collisionContext) {
        return Shapes.block();
    }

    @Override
    public @NotNull InteractionResult use(@NotNull BlockState state, Level level, @NotNull BlockPos pos, @NotNull Player player, @NotNull InteractionHand hand, @NotNull BlockHitResult hit) {
        if (!level.isClientSide) {
            var handItem = player.getMainHandItem().getItem();
            var counterTops = Arrays.stream(CountertopType.values());
            var filtered = counterTops.filter(type -> type.getItem() == handItem).toList();

            if (state.hasProperty(COUNTERTOP)) {
                var currentCounterTop = state.getValue(COUNTERTOP);

                if (!filtered.isEmpty()) {
                    var newCounterTop = filtered.get(0);
                    var defaultCounterTop = CountertopType.getFromBlock(((AbstractDrawerBlock)state.getBlock()).getPlanks());

                    if (newCounterTop != currentCounterTop) {
                        if (currentCounterTop != defaultCounterTop) {
                            Containers.dropItemStack(level, pos.getX() + 0.5, pos.getY() + 0.9, pos.getZ() + 0.5, new ItemStack(currentCounterTop.getItem()));
                        }

                        if (newCounterTop != defaultCounterTop && !player.isCreative()) {
                            player.getMainHandItem().shrink(1);
                        }

                        level.setBlockAndUpdate(pos, state.setValue(COUNTERTOP, newCounterTop));

                        return InteractionResult.SUCCESS;
                    }
                }
            }
        }

        BlockEntity blockEntity = level.getBlockEntity(pos);

        Direction facing = state.getValue(FACING);
        Direction hitLoc = hit.getDirection();

        if (blockEntity instanceof AbstractDrawerBlockEntity drawerBlockEntity && facing == hitLoc) {
            player.openMenu(drawerBlockEntity);
            return InteractionResult.SUCCESS;
        }

        return InteractionResult.PASS;
    }

    @Override
    public @NotNull FluidState getFluidState(BlockState state) {
        return state.getValue(WATERLOGGED) ? Fluids.WATER.getSource(false) : super.getFluidState(state);
    }

    @Override
    public @NotNull BlockState rotate(BlockState state, Rotation rotation) {
        return state.setValue(FACING, rotation.rotate(state.getValue(FACING)));
    }

    @Override
    public @NotNull BlockState mirror(BlockState state, Mirror mirror) {
        return state.rotate(mirror.getRotation(state.getValue(FACING)));
    }

    @Override
    public boolean propagatesSkylightDown(BlockState blockState, BlockGetter blockGetter, BlockPos blockPos) {
        return true;
    }

    @Override
    public void onRemove(BlockState blockState, @NotNull Level level, @NotNull BlockPos blockPos, BlockState blockState2, boolean bl) {
        if (!blockState.is(blockState2.getBlock())) {
            BlockEntity blockEntity = level.getBlockEntity(blockPos);
            if (blockEntity instanceof Container) {
                Containers.dropContents(level, blockPos, (Container) blockEntity);
                level.updateNeighbourForOutputSignal(blockPos, this);
            }

            var currentCounterTop = blockState.getValue(COUNTERTOP);

            if (currentCounterTop.getItem() != plankBlock) {
                Containers.dropItemStack(level, blockPos.getX() + 0.5, blockPos.getY() + 0.85, blockPos.getZ() + 0.5, new ItemStack(currentCounterTop.getItem()));
            }

            super.onRemove(blockState, level, blockPos, blockState2, bl);
        }
    }

    @Override
    public void tick(@NotNull BlockState blockState, ServerLevel serverLevel, @NotNull BlockPos blockPos, @NotNull RandomSource randomSource) {
        BlockEntity blockEntity = serverLevel.getBlockEntity(blockPos);
        if (blockEntity instanceof AbstractDrawerBlockEntity) {
            ((AbstractDrawerBlockEntity) blockEntity).recheckOpen();
        }
    }

    @Override
    public @NotNull RenderShape getRenderShape(@NotNull BlockState blockState) {
        return RenderShape.MODEL;
    }
}