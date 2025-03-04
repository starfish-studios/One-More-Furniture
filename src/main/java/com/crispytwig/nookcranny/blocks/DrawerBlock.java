package com.crispytwig.nookcranny.blocks;

import com.crispytwig.nookcranny.blocks.entities.DrawerBlockEntity;
import com.crispytwig.nookcranny.blocks.properties.CountertopType;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.Container;
import net.minecraft.world.Containers;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
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
import org.jetbrains.annotations.Nullable;

import java.util.Arrays;

public class DrawerBlock extends BaseEntityBlock implements SimpleWaterloggedBlock {
    public static final DirectionProperty FACING = BlockStateProperties.HORIZONTAL_FACING;
    public static final BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;
    public static final BooleanProperty TOP_OPEN = BooleanProperty.create("top_open");
    public static final BooleanProperty BOTTOM_OPEN = BooleanProperty.create("bottom_open");
    public static final EnumProperty<CountertopType> COUNTERTOP = EnumProperty.create("countertop", CountertopType.class);

    public static VoxelShape BOTTOM_DRAWER_AABB = Block.box(-0.1, 0, -0.1, 16.1, 6, 16.1);
    public static VoxelShape TOP_DRAWER_AABB = Block.box(-0.1, 6, -0.1, 16.1, 12, 16.1);

    public DrawerBlock(Properties properties) {
        super(properties);
        this.registerDefaultState(this.getStateDefinition().any()
                .setValue(FACING, Direction.NORTH)
                .setValue(WATERLOGGED, false)
                .setValue(COUNTERTOP, CountertopType.OAK)
                .setValue(TOP_OPEN, false)
                .setValue(BOTTOM_OPEN, false));
    }

    public @NotNull VoxelShape getShape(@NotNull BlockState blockState, @NotNull BlockGetter blockGetter, @NotNull BlockPos blockPos, @NotNull CollisionContext collisionContext) {
        return Shapes.block();
    }

    public BlockState openTop(BlockState blockState, Level level, BlockPos blockPos) {
        blockState = blockState.cycle(TOP_OPEN);
        level.setBlock(blockPos, blockState, 3);
        return blockState;
    }

    public static BlockState openBottom(BlockState blockState, Level level, BlockPos blockPos) {
        blockState = blockState.cycle(BOTTOM_OPEN);
        level.setBlock(blockPos, blockState, 3);
        return blockState;
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

                    var defaultCounterTop = Arrays.stream(CountertopType.values())
                            .filter(type -> type.getDrawer() == state.getBlock())
                            .findFirst()
                            .orElse(null);

                    if (newCounterTop != currentCounterTop) {
                        if (currentCounterTop != defaultCounterTop) {
                            Containers.dropItemStack(level, pos.getX() + 0.5, pos.getY() + 0.85, pos.getZ() + 0.5, new ItemStack(currentCounterTop.getItem()));
                        }

                        if (newCounterTop != defaultCounterTop) {
                            player.getMainHandItem().shrink(1);
                        }

                        level.setBlockAndUpdate(pos, state.setValue(COUNTERTOP, newCounterTop));

                        return InteractionResult.CONSUME;
                    }
                }
            }

            BlockEntity blockEntity = level.getBlockEntity(pos);
            if (blockEntity instanceof DrawerBlockEntity drawerBlockEntity) {
                player.openMenu(drawerBlockEntity);
            }
            return InteractionResult.CONSUME;
        }
        return InteractionResult.SUCCESS;
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> stateDefinition) {
        stateDefinition.add(FACING, WATERLOGGED, TOP_OPEN, BOTTOM_OPEN, COUNTERTOP);
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

    @Nullable
    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        boolean waterlogged = context.getLevel().getFluidState(context.getClickedPos()).getType() == Fluids.WATER;
        return this.getStateDefinition().any()
                .setValue(FACING, context.getHorizontalDirection().getOpposite())
                .setValue(WATERLOGGED, waterlogged)
                .setValue(TOP_OPEN, false)
                .setValue(BOTTOM_OPEN, false)
                .setValue(COUNTERTOP, CountertopType.OAK);
    }

    @Override
    public boolean propagatesSkylightDown(BlockState blockState, BlockGetter blockGetter, BlockPos blockPos) {
        return true;
    }

    public void onRemove(BlockState blockState, @NotNull Level level, @NotNull BlockPos blockPos, BlockState blockState2, boolean bl) {
        if (!blockState.is(blockState2.getBlock())) {
            BlockEntity blockEntity = level.getBlockEntity(blockPos);
            if (blockEntity instanceof Container) {
                Containers.dropContents(level, blockPos, (Container)blockEntity);
                level.updateNeighbourForOutputSignal(blockPos, this);
            }

            var currentCounterTop = blockState.getValue(COUNTERTOP);

            if (currentCounterTop.getDrawer() != blockState.getBlock()) {
                Containers.dropItemStack(level, blockPos.getX() + 0.5, blockPos.getY() + 0.85, blockPos.getZ() + 0.5, new ItemStack(currentCounterTop.getItem()));
            }

            super.onRemove(blockState, level, blockPos, blockState2, bl);
        }
    }

    public void tick(@NotNull BlockState blockState, ServerLevel serverLevel, @NotNull BlockPos blockPos, @NotNull RandomSource randomSource) {
        BlockEntity blockEntity = serverLevel.getBlockEntity(blockPos);
        if (blockEntity instanceof DrawerBlockEntity) {
            ((DrawerBlockEntity)blockEntity).recheckOpen();
        }
    }


    public @NotNull RenderShape getRenderShape(@NotNull BlockState blockState) {
        return RenderShape.MODEL;
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(@NotNull BlockPos blockPos, @NotNull BlockState blockState) {
        return new DrawerBlockEntity(blockPos, blockState);
    }
}
