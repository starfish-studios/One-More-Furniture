//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.crispytwig.nookcranny.blocks.entities;

import com.crispytwig.nookcranny.blocks.AbstractDrawerBlock;
import com.crispytwig.nookcranny.blocks.CabinetBlock;
import com.crispytwig.nookcranny.blocks.DrawerBlock;
import com.crispytwig.nookcranny.inventory.DrawerMenu;
import com.crispytwig.nookcranny.registry.NCBlockEntities;
import com.mojang.datafixers.util.Pair;
import net.fabricmc.fabric.api.screenhandler.v1.ExtendedScreenHandlerFactory;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.NonNullList;
import net.minecraft.core.Vec3i;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.Container;
import net.minecraft.world.ContainerHelper;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.entity.ContainerOpenersCounter;
import net.minecraft.world.level.block.entity.RandomizableContainerBlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Objects;

public abstract class AbstractDrawerBlockEntity extends RandomizableContainerBlockEntity implements MenuProvider {
    private static final SoundEvent SOUND_OPEN = SoundEvents.BARREL_OPEN;
    private static final SoundEvent SOUND_CLOSE = SoundEvents.BARREL_CLOSE;

    private NonNullList<ItemStack> items = NonNullList.withSize(10, ItemStack.EMPTY);
    public final ContainerOpenersCounter openersCounter;

    public AbstractDrawerBlockEntity(BlockEntityType<?> be, BlockPos blockPos, BlockState blockState) {
        super(be, blockPos, blockState);

        this.openersCounter = new ContainerOpenersCounter() {
            protected void onOpen(@NotNull Level level, @NotNull BlockPos blockPos, @NotNull BlockState blockState) {
                AbstractDrawerBlockEntity.this.playSound(blockState, SOUND_OPEN);
            }

            protected void onClose(@NotNull Level level, @NotNull BlockPos blockPos, @NotNull BlockState blockState) {
                AbstractDrawerBlockEntity.this.playSound(blockState, SOUND_CLOSE);
            }

            protected void openerCountChanged(@NotNull Level level, @NotNull BlockPos blockPos, @NotNull BlockState blockState, int i, int j) {
            }

            protected boolean isOwnContainer(@NotNull Player player) {
                if (player.containerMenu instanceof DrawerMenu) {
                    Container container = ((DrawerMenu)player.containerMenu).getContainer();
                    return container == AbstractDrawerBlockEntity.this;
                } else {
                    return false;
                }
            }
        };
    }

    protected void saveAdditional(@NotNull CompoundTag compoundTag) {
        super.saveAdditional(compoundTag);

        ContainerHelper.saveAllItems(compoundTag, this.items);
    }

    public void load(@NotNull CompoundTag compoundTag) {
        super.load(compoundTag);
        this.items = NonNullList.withSize(this.getContainerSize(), ItemStack.EMPTY);

        ContainerHelper.loadAllItems(compoundTag, this.items);
    }

    public int getContainerSize() {
        return 10;
    }

    protected @NotNull NonNullList<ItemStack> getItems() {
        return this.items;
    }

    protected void setItems(@NotNull NonNullList<ItemStack> nonNullList) {
        this.items = nonNullList;
    }

    protected @NotNull Component getDefaultName() {
        return Component.translatable("container.drawer");
    }

    @Nullable
    @Override
    public AbstractContainerMenu createMenu(int i, Inventory inventory, Player player) {
        Pair<Integer, Direction> raycastResult = new Pair<>(0, Direction.UP);

        assert level != null;
        BlockPos blockPos = this.getBlockPos();
        BlockEntity blockEntity = level.getBlockEntity(blockPos);

        //do a raycast from player's POV
        HitResult hitResult = player.pick(10, 1, false);
        if (hitResult.getType() == HitResult.Type.BLOCK) {
            BlockHitResult blockHitResult = (BlockHitResult) hitResult;
            //get the local hit pos result by subtracting the center point from the raycast result
            Vec3 localHitPos = blockHitResult.getLocation().subtract(Vec3.atCenterOf(blockHitResult.getBlockPos()));

            //convert depending on which face was raycasted to
            var bl = this.getBlockState().getBlock() instanceof CabinetBlock;
            int relativePos = getRelativeDrawerPos(blockHitResult.getDirection(), localHitPos, bl);

            raycastResult = new Pair<>(relativePos, blockHitResult.getDirection());
        }

        if (raycastResult.getSecond() == this.getBlockState().getValue(DrawerBlock.FACING)) {

            if (blockEntity instanceof AbstractDrawerBlockEntity abstractDrawerBlockEntity) {
                if (abstractDrawerBlockEntity instanceof CabinetBlockEntity) {
                    if (raycastResult.getFirst() > -7) {
                        abstractDrawerBlockEntity.setCustomName(Component.translatable("container.cabinet_right"));
                    } else {
                        abstractDrawerBlockEntity.setCustomName(Component.translatable("container.cabinet_left"));
                    }
                } else {
                    if (raycastResult.getFirst() > 7) {
                        abstractDrawerBlockEntity.setCustomName(Component.translatable("container.drawer_top"));
                    } else {
                        abstractDrawerBlockEntity.setCustomName(Component.translatable("container.drawer_bottom"));
                    }
                }

                openScreen(abstractDrawerBlockEntity, player, raycastResult);
            }
        }
        return null;
    }

    private void openScreen(AbstractDrawerBlockEntity blockEntity, Player player, Pair<Integer, Direction> raycastResult){
        var screen = new ExtendedScreenHandlerFactory() {

            @Nullable
            @Override
            public AbstractContainerMenu createMenu(int i, Inventory inventory, Player player) {
                return new DrawerMenu(i, inventory, blockEntity, raycastResult.getFirst() < 7 ? 0 : 5);
            }

            @Override
            public Component getDisplayName() {
                assert blockEntity instanceof AbstractDrawerBlockEntity;
                return blockEntity.getDisplayName();
            }

            @Override
            public void writeScreenOpeningData(ServerPlayer player, FriendlyByteBuf buf) {

            }
        };

        player.openMenu(screen);
    }

    //UNUSED, SHOULDN'T BE CALLED (?)
    @Override
    protected @NotNull AbstractContainerMenu createMenu(int i, Inventory inventory) {
        return new DrawerMenu(i, inventory, this, 0);
    }



    public int getRelativeDrawerPos(Direction direction, Vec3 localHitPos, boolean isCabinet) {
        return -switch (direction) {
            case NORTH, SOUTH, EAST, WEST -> isCabinet
                    ? (int) Math.floor((localHitPos.x() + 0.5) * 16)
                    : (int) Math.floor(1.0 - (localHitPos.y() + 0.5) * 16);
            case UP -> (int) Math.floor((localHitPos.z() + 0.5) * 16);
            case DOWN -> (int) Math.floor(1.0 - (localHitPos.z() + 0.5) * 16);
        };
    }

    public void recheckOpen() {
        if (!this.remove) {
            this.openersCounter.recheckOpeners(Objects.requireNonNull(this.getLevel()), this.getBlockPos(), this.getBlockState());
        }
    }

    void playSound(BlockState blockState, SoundEvent soundEvent) {
        Vec3i vec3i = blockState.getValue(DrawerBlock.FACING).getNormal();
        double d = (double)this.worldPosition.getX() + 0.5 + (double)vec3i.getX() / 2.0;
        double e = (double)this.worldPosition.getY() + 0.5 + (double)vec3i.getY() / 2.0;
        double f = (double)this.worldPosition.getZ() + 0.5 + (double)vec3i.getZ() / 2.0;
        assert this.level != null;
        this.level.playSound(null, d, e, f, soundEvent, SoundSource.BLOCKS, 0.5F, this.level.random.nextFloat() * 0.1F + 0.9F);
    }
}
