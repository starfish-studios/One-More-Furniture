package com.crispytwig.nookcranny.blocks;

import com.crispytwig.nookcranny.blocks.entities.MailboxBlockEntity;
import com.crispytwig.nookcranny.blocks.properties.FlagStatus;
import com.crispytwig.nookcranny.registry.NCBlockEntities;
import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.RandomSource;
import net.minecraft.world.Container;
import net.minecraft.world.Containers;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.entity.HopperBlockEntity;
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
import org.jetbrains.annotations.Nullable;

public class MailboxBlock extends BaseEntityBlock implements SimpleWaterloggedBlock {
    public static final DirectionProperty FACING = BlockStateProperties.HORIZONTAL_FACING;
    public static final BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;
    public static final EnumProperty<FlagStatus> FLAG_STATUS = EnumProperty.create("flag", FlagStatus.class);
    public static final BooleanProperty OPEN = BlockStateProperties.OPEN;
    public static final BooleanProperty FENCE_BELOW = BooleanProperty.create("fence_below");
//    public static final EnumProperty<ColorList> COLOR = NCBlockProperties.COLOR;

    public static final VoxelShape NORTH_AABB = Block.box(4.0D, 0.0D, 0.0D, 12.0D, 12.0D, 16.0D);
    public static final VoxelShape NORTH_FENCE_AABB = Block.box(4.0D, 0.0D, -7.0D, 12.0D, 12.0D, 10.0D);
    public static final VoxelShape EAST_AABB = Block.box(0.0D, 0.0D, 4.0D, 16.0D, 12.0D, 12.0D);
    public static final VoxelShape EAST_FENCE_AABB = Block.box(6.0D, 0.0D, 4.0D, 23.0D, 12.0D, 12.0D);
    public static final VoxelShape SOUTH_AABB = Block.box(4.0D, 0.0D, 0.0D, 12.0D, 12.0D, 16.0D);
    public static final VoxelShape SOUTH_FENCE_AABB = Block.box(4.0D, 0.0D, 6.0D, 12.0D, 12.0D, 23.0D);
    public static final VoxelShape WEST_AABB = Block.box(0.0D, 0.0D, 4.0D, 16.0D, 12.0D, 12.0D);
    public static final VoxelShape WEST_FENCE_AABB = Block.box(-7.0D, 0.0D, 4.0D, 10.0D, 12.0D, 12.0D);

    public MailboxBlock(Properties properties) {
        super(properties);
        this.registerDefaultState(this.getStateDefinition().any()
                .setValue(FACING, Direction.NORTH)
                .setValue(WATERLOGGED, false)
                .setValue(FLAG_STATUS, FlagStatus.DOWN)
                .setValue(OPEN, false)
                .setValue(FENCE_BELOW, false));
    }

    public VoxelShape getShape(BlockState blockState, BlockGetter blockGetter, BlockPos blockPos, CollisionContext collisionContext) {
        return switch (blockState.getValue(FACING)) {
            default -> blockState.getValue(FENCE_BELOW) ? NORTH_FENCE_AABB : NORTH_AABB;
            case EAST -> blockState.getValue(FENCE_BELOW) ? EAST_FENCE_AABB : EAST_AABB;
            case SOUTH -> blockState.getValue(FENCE_BELOW) ? SOUTH_FENCE_AABB : SOUTH_AABB;
            case WEST -> blockState.getValue(FENCE_BELOW) ? WEST_FENCE_AABB : WEST_AABB;
        };
    }

    public BlockState open(BlockState blockState, Level level, BlockPos blockPos) {
        blockState = blockState.cycle(OPEN);
        level.setBlock(blockPos, blockState, 3);
        return blockState;
    }

    @Override
    public InteractionResult use(BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hit) {
        if (!level.isClientSide) {
            BlockEntity blockEntity = level.getBlockEntity(pos);
            MailboxBlockEntity mailboxBlockEntity = (MailboxBlockEntity) blockEntity;
            if(player.isShiftKeyDown()) {

                if (state.getValue(FLAG_STATUS) == FlagStatus.DOWN) {
                    assert blockEntity != null;
                    player.displayClientMessage(
                            Component.translatable("nookcranny.mailbox.sending",
                                            mailboxBlockEntity.getMailboxName(),
                                            "XYZ: " + pos.getX() + " / " + pos.getY() + " / "  + pos.getZ()
                            ).withStyle(ChatFormatting.GREEN).withStyle(ChatFormatting.ITALIC), true
                    );


                } else if (state.getValue(FLAG_STATUS) == FlagStatus.UP) {
                    assert blockEntity != null;
                    player.displayClientMessage(
                            Component.translatable("nookcranny.mailbox.receiving",
                                    mailboxBlockEntity.getMailboxName(),
                                    "XYZ: " + pos.getX() + " / " + pos.getY() + " / "  + pos.getZ()
                            ).withStyle(ChatFormatting.YELLOW).withStyle(ChatFormatting.ITALIC), true
                    );
                }


                BlockState blockState2;
                blockState2 = this.open(state, level, pos);
                float f = blockState2.getValue(FLAG_STATUS) == FlagStatus.UP ? 0.5F : 0.6F;
                level.playSound(null, pos, SoundEvents.LEVER_CLICK, SoundSource.BLOCKS, 0.3F, f);
                level.setBlock(pos, state.cycle(FLAG_STATUS), 3);
            } else if (blockEntity instanceof MailboxBlockEntity) {
                player.openMenu((MailboxBlockEntity)blockEntity);
//                player.awardStat(Stats.OPEN_BARREL);
//                PiglinAi.angerNearbyPiglins(player, true);
                return InteractionResult.CONSUME;
            }
        }
        return InteractionResult.SUCCESS;
    }

    @Nullable
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level level, BlockState blockState, BlockEntityType<T> blockEntityType) {
        return level.isClientSide ? null : createTickerHelper(blockEntityType, NCBlockEntities.MAILBOX, MailboxBlockEntity::sendItemsTick);
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

    public void onRemove(BlockState blockState, Level level, BlockPos blockPos, BlockState blockState2, boolean bl) {
        if (!blockState.is(blockState2.getBlock())) {
            BlockEntity blockEntity = level.getBlockEntity(blockPos);
            if (blockEntity instanceof Container) {
                Containers.dropContents(level, blockPos, (Container)blockEntity);
                level.updateNeighbourForOutputSignal(blockPos, this);
            }
            super.onRemove(blockState, level, blockPos, blockState2, bl);
        }
    }

    public void setPlacedBy(Level level, BlockPos blockPos, BlockState blockState, @Nullable LivingEntity livingEntity, ItemStack itemStack) {
        Player player = livingEntity instanceof Player ? (Player)livingEntity : null;
        if (player != null && !itemStack.hasCustomHoverName()) {
            BlockEntity blockEntity = level.getBlockEntity(blockPos);
            if (blockEntity instanceof MailboxBlockEntity) {
                ((MailboxBlockEntity)blockEntity).setCustomName(Component.translatable("container.mailbox.player_name", player.getDisplayName()));
            }
        } else if (itemStack.hasCustomHoverName()) {
            BlockEntity blockEntity = level.getBlockEntity(blockPos);
            if (blockEntity instanceof MailboxBlockEntity) {
                ((MailboxBlockEntity)blockEntity).setCustomName(itemStack.getHoverName());
            }
        }
    }

    public void tick(BlockState blockState, ServerLevel serverLevel, BlockPos blockPos, RandomSource randomSource) {
        BlockEntity blockEntity = serverLevel.getBlockEntity(blockPos);
        if (blockEntity instanceof MailboxBlockEntity) {
            ((MailboxBlockEntity)blockEntity).recheckOpen();
        }
    }


    public RenderShape getRenderShape(BlockState blockState) {
        return RenderShape.MODEL;
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos blockPos, BlockState blockState) {
        return new MailboxBlockEntity(blockPos, blockState);
    }
}
