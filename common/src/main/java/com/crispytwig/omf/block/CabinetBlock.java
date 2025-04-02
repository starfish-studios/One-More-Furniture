package com.crispytwig.omf.block;

import com.crispytwig.omf.block.entity.CabinetBlockEntity;
import com.crispytwig.omf.block.properties.CountertopType;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.*;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import org.jetbrains.annotations.Nullable;

public class CabinetBlock extends AbstractDrawerBlock {

    public static final BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;
    public static final BooleanProperty BOTTOM = BlockStateProperties.BOTTOM;

    public static final BooleanProperty LEFT_OPEN = BooleanProperty.create("left_open");
    public static final BooleanProperty RIGHT_OPEN = BooleanProperty.create("right_open");

    public CabinetBlock(Item plank, Properties properties) {
        super(plank, properties);
        this.registerDefaultState(this.stateDefinition.any()
                .setValue(FACING, Direction.NORTH)
                .setValue(BOTTOM, true)
                .setValue(COUNTERTOP, CountertopType.OAK)
                .setValue(WATERLOGGED, false)
        );
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return new CabinetBlockEntity(pos, state);
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(FACING, BOTTOM, WATERLOGGED, COUNTERTOP);
    }

    @Nullable
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        BlockPos blockPos = context.getClickedPos();
        var countertop = CountertopType.getFromBlock(plankBlock);

        FluidState fluidState = context.getLevel().getFluidState(blockPos);
        BlockState blockState2 = this.defaultBlockState()
                .setValue(BOTTOM, true)
                .setValue(COUNTERTOP, countertop)
                .setValue(WATERLOGGED, fluidState.getType() == Fluids.WATER)
                .setValue(FACING, context.getHorizontalDirection().getOpposite());

        Direction upOrDown = context.getNearestLookingVerticalDirection();
        if (upOrDown == Direction.UP) {
            blockState2 = blockState2.setValue(BOTTOM, false);
        }
        return blockState2;
    }

}