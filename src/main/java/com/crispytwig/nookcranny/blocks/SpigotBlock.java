package com.crispytwig.nookcranny.blocks;

import com.crispytwig.nookcranny.blocks.entities.SpigotBlockEntity;
import com.crispytwig.nookcranny.registry.NCBlockEntities;
import com.crispytwig.nookcranny.registry.NCSoundEvents;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.*;
import net.minecraft.world.item.alchemy.PotionUtils;
import net.minecraft.world.item.alchemy.Potions;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.*;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.BooleanOp;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.Nullable;

public class SpigotBlock extends BaseEntityBlock implements SimpleWaterloggedBlock {
    public static final DirectionProperty FACING = BlockStateProperties.HORIZONTAL_FACING;
    public static final EnumProperty<AttachFace> FACE = BlockStateProperties.ATTACH_FACE;
    public static final BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;
    public static final BooleanProperty POWERED = BlockStateProperties.POWERED;

    public static final VoxelShape NORTH_SHAPE = Block.box(6.0, 6.0, 7.0, 10.0, 12.0, 16.0);
    public static final VoxelShape EAST_SHAPE = Block.box(0.0, 6.0, 6.0, 9.0, 12.0, 10.0);
    public static final VoxelShape SOUTH_SHAPE = Block.box(6.0, 6.0, 0.0, 10.0, 12.0, 9.0);
    public static final VoxelShape WEST_SHAPE = Block.box(7.0, 6.0, 6.0, 16.0, 12.0, 10.0);

    public static final VoxelShape SHAFT_FLOOR_SHAPE = Block.box(6.0, 0.0, 6.0, 10.0, 8.0, 10.0);

    public static final VoxelShape SHAFT_CEILING_SHAPE = Block.box(6.0, 4.0, 6.0, 10.0, 16.0, 10.0);

    public static final VoxelShape NORTH_FLOOR_SHAPE = Block.box(6.0, 6.0, 0.0, 10.0, 12.0, 10.0);
    public static final VoxelShape EAST_FLOOR_SHAPE = Block.box(6.0, 6.0, 6.0, 16.0, 12.0, 10.0);
    public static final VoxelShape SOUTH_FLOOR_SHAPE = Block.box(6.0, 6.0, 6.0, 10.0, 12.0, 16.0);
    public static final VoxelShape WEST_FLOOR_SHAPE = Block.box(0.0, 6.0, 6.0, 10.0, 12.0, 10.0);

    public SpigotBlock(Properties properties) {
        super(properties);
        this.registerDefaultState(this.getStateDefinition().any()
                .setValue(FACING, Direction.NORTH)
                .setValue(FACE, AttachFace.WALL)
                .setValue(WATERLOGGED, false)
                .setValue(POWERED, false)
        );
    }

    @Override
    public RenderShape getRenderShape(BlockState blockState) {
        return RenderShape.MODEL;
    }

    @Override
    public VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {

        var dir = state.getValue(BlockStateProperties.HORIZONTAL_FACING);

        if (state.getValue(FACE) == AttachFace.CEILING) {
            return SHAFT_CEILING_SHAPE;
        }

        if (state.getValue(FACE) == AttachFace.FLOOR) {
            return switch (dir) {
                case NORTH -> Shapes.join(SHAFT_FLOOR_SHAPE, NORTH_FLOOR_SHAPE, BooleanOp.OR);
                case EAST -> Shapes.join(SHAFT_FLOOR_SHAPE, EAST_FLOOR_SHAPE, BooleanOp.OR);
                case SOUTH -> Shapes.join(SHAFT_FLOOR_SHAPE, SOUTH_FLOOR_SHAPE, BooleanOp.OR);
                default -> Shapes.join(SHAFT_FLOOR_SHAPE, WEST_FLOOR_SHAPE, BooleanOp.OR);
            };
        }

        return switch (dir) {
            case SOUTH -> SOUTH_SHAPE;
            case WEST -> WEST_SHAPE;
            case EAST -> EAST_SHAPE;
            default -> NORTH_SHAPE;
        };
    }

    @Override
    public InteractionResult use(BlockState blockState, Level level, BlockPos blockPos, Player player, InteractionHand interactionHand, BlockHitResult blockHitResult) {
        BlockState blockState2;

        boolean waterOn = blockState.getValue(POWERED);

        if (waterOn && player.getItemInHand(interactionHand).getItem() == Items.SPONGE) {
            player.getItemInHand(interactionHand).shrink(1);
            player.addItem(new ItemStack(Items.WET_SPONGE));
            level.playSound(null, blockPos, SoundEvents.WET_GRASS_PLACE, SoundSource.BLOCKS, 1.0F, 1.0F);
        } else if (waterOn && player.getItemInHand(interactionHand).getItem() == Items.BUCKET) {
            player.getItemInHand(interactionHand).shrink(1);
            player.addItem(new ItemStack(Items.WATER_BUCKET));
            level.playSound(null, blockPos, SoundEvents.BUCKET_FILL, SoundSource.BLOCKS, 1.0F, 1.0F);
        } else if (waterOn && player.getItemInHand(interactionHand).getItem() == Items.GLASS_BOTTLE) {
            player.getItemInHand(interactionHand).shrink(1);
            player.addItem(PotionUtils.setPotion(new ItemStack(Items.POTION), Potions.WATER));
            level.playSound(null, blockPos, SoundEvents.BUCKET_FILL, SoundSource.BLOCKS, 1.0F, 1.0F);
        } else {
            blockState2 = this.pull(blockState, level, blockPos);
            float f = blockState2.getValue(POWERED) ? 0.6F : 0.5F;
            level.playSound(null, blockPos,
                    waterOn ? NCSoundEvents.SPIGOT_ON : NCSoundEvents.SPIGOT_OFF,
                    SoundSource.BLOCKS, f,
                    1.0f);
        }
        return InteractionResult.SUCCESS;
    }

    public BlockState pull(BlockState blockState, Level level, BlockPos blockPos) {
        blockState = blockState.cycle(POWERED);
        level.setBlock(blockPos, blockState, 3);
        return blockState;
    }

    @Override
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return NCBlockEntities.SPIGOT.create(pos, state);
    }

    @Nullable
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level level, BlockState state, BlockEntityType<T> blockEntity) {
        return BaseEntityBlock.createTickerHelper(blockEntity, NCBlockEntities.SPIGOT, SpigotBlockEntity::fillingTick);
    }


    @Override
    public void animateTick(BlockState blockState, Level level, BlockPos pos, RandomSource randomSource) {
        if (blockState.getValue(POWERED)) {
            if (randomSource.nextDouble() < 0.1) {
                double d = (double)pos.getX() + 0.5;
                double e = pos.getY();
                double f = (double)pos.getZ() + 0.5;
                level.playLocalSound(d, e, f, SoundEvents.WATER_AMBIENT, SoundSource.BLOCKS, 0.2F, 1.3F, false);
            }

            if (level.getBlockState(pos.below()).isAir() || level.getBlockState(pos.below()).getBlock() instanceof CauldronBlock) {
                for (int i = 0; i < 2; ++i) {
                    double x = (double) pos.getX() + 0.6 + (level.random.nextDouble() * 0.25D - 0.25D);
                    double y = (double) pos.getY() - 0.4 + (level.random.nextDouble() * 0.2D - 0.1D);
                    double z = (double) pos.getZ() + 0.6 + (level.random.nextDouble() * 0.25D - 0.25D);
                    level.addParticle(ParticleTypes.FALLING_WATER, x, y, z, 0.0D, 0.0D, 0.0D);
                }
                for (int i = 0; i < 1; ++i) {
                    double x = (double) pos.getX() + 0.6 + (level.random.nextDouble() * 0.25D - 0.25D);
                    double y = (double) pos.getY() - 0.4 + (level.random.nextDouble() * 0.2D - 0.1D);
                    double z = (double) pos.getZ() + 0.6 + (level.random.nextDouble() * 0.25D - 0.25D);
                    level.addParticle(ParticleTypes.CLOUD, x, y, z, 0.0D, 0.0D, 0.0D);
                }
            } else if (level.getBlockState(pos.below()).getBlock() instanceof SpongeBlock || level.getBlockState(pos.below()).getBlock() instanceof WetSpongeBlock) {
                for (int i = 0; i < 2; ++i) {
                    double x = (double) pos.getX() + 0.6 + (level.random.nextDouble() * 0.25D - 0.25D);
                    double y = (double) pos.getY() + 0.2 + (level.random.nextDouble() * 0.2D - 0.1D);
                    double z = (double) pos.getZ() + 0.6 + (level.random.nextDouble() * 0.25D - 0.25D);
                    level.addParticle(ParticleTypes.FALLING_WATER, x, y, z, 0.0D, 0.0D, 0.0D);
                }
                for (int i = 0; i < 1; ++i) {
                    double x = (double) pos.getX() + 0.6 + (level.random.nextDouble() * 0.25D - 0.25D);
                    double y = (double) pos.getY() + 0.2 + (level.random.nextDouble() * 0.2D - 0.1D);
                    double z = (double) pos.getZ() + 0.6 + (level.random.nextDouble() * 0.25D - 0.25D);
                    level.addParticle(ParticleTypes.CLOUD, x, y, z, 0.0D, 0.0D, 0.0D);
                }
            }

            if (level.getBlockState(pos.below()).getBlock() instanceof SimpleWaterloggedBlock && level.getBlockState(pos.below()).getValue(WATERLOGGED)
            || (level.getBlockState(pos.below()).getBlock() instanceof LayeredCauldronBlock && level.getBlockState(pos.below()).is(Blocks.WATER_CAULDRON))) {

                double yOffset = 0.0D;
                int levelBelow = level.getBlockState(pos.below()).getValue(LayeredCauldronBlock.LEVEL);

                if (level.getBlockState(pos.below()).getBlock() instanceof LayeredCauldronBlock) {
                    if (levelBelow == 1) {
                        yOffset = -0.3D;
                    } else if (levelBelow == 2) {
                        yOffset = -0.1D;
                    } else if (levelBelow == 3) {
                        yOffset = 0.1D;
                    }
                }
                if (randomSource.nextDouble() < 0.3) {
                    double d = (double)pos.getX() + 0.5;
                    double e = pos.getY();
                    double f = (double)pos.getZ() + 0.5;
                    level.playLocalSound(d, e, f, SoundEvents.FISHING_BOBBER_SPLASH, SoundSource.BLOCKS, 0.02F, 1.0F, false);
                }
                for (int i = 0; i < 5; ++i) {
                    double x = (double) pos.getX() + 0.5 + (level.random.nextDouble() * 0.5D - 0.25D);
                    double y = (double) pos.getY() - 0.1D + yOffset + (level.random.nextDouble() * 0.2D - 0.1D);
                    double z = (double) pos.getZ() + 0.4 + (level.random.nextDouble() * 0.5D - 0.25D);
                    level.addParticle(ParticleTypes.BUBBLE_POP, x, y, z, 0.0D, 0.0D, 0.0D);
                }
                for (int i = 0; i < 1; ++i) {
                    double x = (double) pos.getX() + 0.5 + (level.random.nextDouble() * 0.5D - 0.25D);
                    double y = (double) pos.getY() - 0.2D + yOffset + (level.random.nextDouble() * 0.2D - 0.1D);
                    double z = (double) pos.getZ() + 0.4 + (level.random.nextDouble() * 0.5D - 0.25D);
                    level.addParticle(ParticleTypes.CLOUD, x, y, z, 0.0D, 0.0D, 0.0D);
                }
            }
        }
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> stateDefinition) {
        stateDefinition.add(FACING, WATERLOGGED, POWERED, FACE);
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

        var state = this.getStateDefinition().any();

        Direction direction = context.getClickedFace();
        if (direction == Direction.DOWN) {
            state = state.setValue(FACE, AttachFace.CEILING);
        } else if (direction == Direction.UP) {
            state = state
                    .setValue(FACE, AttachFace.FLOOR);
        } else {
            state = state.setValue(FACE, AttachFace.WALL);
        }

        return state
                .setValue(FACING, context.getHorizontalDirection().getOpposite())
                .setValue(WATERLOGGED, waterlogged)
                .setValue(POWERED, false);
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
