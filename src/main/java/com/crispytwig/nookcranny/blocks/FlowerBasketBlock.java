//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.crispytwig.nookcranny.blocks;

import com.crispytwig.nookcranny.blocks.entities.FlowerBasketBlockEntity;
import com.crispytwig.nookcranny.util.block.BlockPart;
import com.google.common.collect.Maps;
import java.util.Map;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.stats.Stats;
import net.minecraft.world.Containers;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.level.pathfinder.PathComputationType;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class FlowerBasketBlock extends BaseEntityBlock {
    private static final Map<Block, Block> POTTED_BY_CONTENT = Maps.newHashMap();
    public static final DirectionProperty FACING = BlockStateProperties.HORIZONTAL_FACING;
    public static final float AABB_SIZE = 3.0F;
    protected static final VoxelShape SHAPE = Block.box(5.0, 0.0, 5.0, 11.0, 6.0, 11.0);

    public FlowerBasketBlock(Properties properties) {
        super(properties);
        this.registerDefaultState(this.stateDefinition.any()
                .setValue(FACING, Direction.NORTH));
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(@NotNull BlockPos pos, @NotNull BlockState state) {
        return new FlowerBasketBlockEntity(pos, state);
    }

    public @NotNull VoxelShape getShape(@NotNull BlockState blockState, @NotNull BlockGetter blockGetter, @NotNull BlockPos blockPos, @NotNull CollisionContext collisionContext) {
        return SHAPE;
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(FACING);
    }

    public @NotNull RenderShape getRenderShape(@NotNull BlockState blockState) {
        return RenderShape.MODEL;
    }

    @Override
    public @NotNull InteractionResult use(@NotNull BlockState state, Level level, @NotNull BlockPos pos, @NotNull Player player, @NotNull InteractionHand hand, @NotNull BlockHitResult hit) {
        BlockEntity blockEntity = level.getBlockEntity(pos);
        if (!(blockEntity instanceof FlowerBasketBlockEntity flowerBoxBE)) return InteractionResult.PASS;

        ItemStack stack = player.getItemInHand(hand);
//        if (!stack.is(AFItemTags.FLOWER_BOX_PLACEABLES) || stack.is(AFItemTags.FLOWER_BOX_BANNED)) return InteractionResult.PASS;

        Direction facing = state.getValue(FACING);
        int slot = BlockPart.get1D(pos, hit.getLocation(), facing.getClockWise(), 2);
        if (!level.isClientSide && flowerBoxBE.placeFlower(player.getAbilities().instabuild ? stack.copy() : stack, slot))
            return InteractionResult.SUCCESS;

        return InteractionResult.CONSUME;
    }

    @Override
    public void onRemove(BlockState state, Level level, BlockPos pos, BlockState newState, boolean isMoving) {
        if (state.is(newState.getBlock())) return;

        BlockEntity blockEntity = level.getBlockEntity(pos);
        if (blockEntity instanceof FlowerBasketBlockEntity flowerBoxBE) Containers.dropContents(level, pos, flowerBoxBE.getItems());
        super.onRemove(state, level, pos, newState, isMoving);
    }

    @Nullable
    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
//        boolean waterlogged = context.getLevel().getFluidState(context.getClickedPos()).getType() == Fluids.WATER;
        return this.getStateDefinition().any()
                .setValue(FACING, context.getHorizontalDirection().getOpposite());
    }

    @Override
    public @NotNull BlockState rotate(BlockState state, Rotation rotation) {
        return state.setValue(FACING, rotation.rotate(state.getValue(FACING)));
    }

    public @NotNull BlockState updateShape(@NotNull BlockState blockState, @NotNull Direction direction, @NotNull BlockState blockState2, @NotNull LevelAccessor levelAccessor, @NotNull BlockPos blockPos, @NotNull BlockPos blockPos2) {
        return direction == Direction.DOWN && !blockState.canSurvive(levelAccessor, blockPos) ? Blocks.AIR.defaultBlockState() : super.updateShape(blockState, direction, blockState2, levelAccessor, blockPos, blockPos2);
    }

    public boolean isPathfindable(BlockState blockState, BlockGetter blockGetter, BlockPos blockPos, PathComputationType pathComputationType) {
        return false;
    }
}
