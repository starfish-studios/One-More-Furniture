package com.crispytwig.nookcranny.blocks;

import com.crispytwig.nookcranny.blocks.entities.FanBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.RedstoneLampBlock;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.Nullable;

public class FanBlock extends BaseEntityBlock {

    public String wood;

    private static final VoxelShape SHAPE_NORTH = Block.box(4.0D, 4.0D, 4.0D, 12.0D, 12.0D, 16.0D);
    private static final VoxelShape SHAPE_SOUTH = Block.box(4.0D, 4.0D, 0.0D, 12.0D, 12.0D, 12.0D);
    private static final VoxelShape SHAPE_WEST = Block.box(4.0D, 4.0D, 4.0D, 16.0D, 12.0D, 12.0D);
    private static final VoxelShape SHAPE_EAST = Block.box(0.0D, 4.0D, 4.0D, 12.0D, 12.0D, 12.0D);
    private static final VoxelShape SHAPE_UP = Block.box(4.0D, 0.0D, 4.0D, 12.0D, 12.0D, 12.0D);
    private static final VoxelShape SHAPE_DOWN = Block.box(4.0D, 4.0D, 4.0D, 12.0D, 16.0D, 12.0D);

    public FanBlock(String wood, Properties properties) {
        super(properties
                .lightLevel(state -> state.getValue(BlockStateProperties.POWERED) ? 12 : 0)
                .emissiveRendering((blockState, blockGetter, blockPos) -> blockState.getValue(BlockStateProperties.POWERED)));
        this.registerDefaultState(this.stateDefinition.any()
                .setValue(BlockStateProperties.FACING, Direction.NORTH)
                .setValue(BlockStateProperties.POWERED, false)
        );
        this.wood = wood;
    }

    public RenderShape getRenderShape(BlockState state) {
        return RenderShape.MODEL;
    }

    @Override
    public VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        return switch (state.getValue(BlockStateProperties.FACING)) {
            case DOWN -> SHAPE_DOWN;
            case UP -> SHAPE_UP;
            case NORTH -> SHAPE_NORTH;
            case SOUTH -> SHAPE_SOUTH;
            case WEST -> SHAPE_WEST;
            case EAST -> SHAPE_EAST;
        };
    }

    @Nullable
    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level level, BlockState state, BlockEntityType<T> blockEntityType) {
        return (level1, pos, state1, blockEntity) -> {
            if (blockEntity instanceof FanBlockEntity fanBlockEntity) {
                fanBlockEntity.commonTick(level, state);
            }
        };
    }

    @Override
    public void tick(BlockState state, ServerLevel level, BlockPos pos, RandomSource random) {
        if (state.getValue(BlockStateProperties.POWERED) && !level.hasNeighborSignal(pos)) {
            level.setBlock(pos, state.cycle(BlockStateProperties.POWERED), 2);
        }
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(BlockStateProperties.FACING, BlockStateProperties.POWERED);
    }

    public void neighborChanged(BlockState state, Level level, BlockPos pos, Block neighborBlock, BlockPos neighborPos, boolean movedByPiston) {
        if (!level.isClientSide) {
            boolean bl = state.getValue(BlockStateProperties.POWERED);
            if (bl != level.hasNeighborSignal(pos)) {
                if (bl) {
                    level.scheduleTick(pos, this, 4);
                } else {
                    level.setBlock(pos, state.cycle(BlockStateProperties.POWERED), 2);
                }
            }

        }
    }

    @Nullable
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        return this.defaultBlockState().setValue(BlockStateProperties.FACING, context.getNearestLookingDirection().getOpposite());
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return new FanBlockEntity(pos, state);
    }
}
