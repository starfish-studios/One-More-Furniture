//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.crispytwig.nookcranny.blocks.entities;

import com.crispytwig.nookcranny.blocks.DrawerBlock;
import com.crispytwig.nookcranny.inventory.DrawerMenu;
import com.crispytwig.nookcranny.registry.NCBlockEntities;
import com.mojang.datafixers.util.Pair;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.NonNullList;
import net.minecraft.core.Vec3i;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
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
import net.minecraft.world.level.block.entity.ContainerOpenersCounter;
import net.minecraft.world.level.block.entity.RandomizableContainerBlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Objects;

public class DrawerBlockEntity extends RandomizableContainerBlockEntity implements MenuProvider {
    private static final SoundEvent SOUND_OPEN = SoundEvents.BARREL_OPEN;
    private static final SoundEvent SOUND_CLOSE = SoundEvents.BARREL_CLOSE;

    private NonNullList<ItemStack> items;
    private final ContainerOpenersCounter openersCounter;

    public DrawerBlockEntity(BlockPos blockPos, BlockState blockState) {
        super(NCBlockEntities.DRAWER, blockPos, blockState);
        this.items = NonNullList.withSize(27, ItemStack.EMPTY);
        this.openersCounter = new ContainerOpenersCounter() {
            protected void onOpen(@NotNull Level level, @NotNull BlockPos blockPos, @NotNull BlockState blockState) {
                DrawerBlockEntity.this.playSound(blockState, SOUND_OPEN);
            }

            protected void onClose(@NotNull Level level, @NotNull BlockPos blockPos, @NotNull BlockState blockState) {
                DrawerBlockEntity.this.playSound(blockState, SOUND_CLOSE);
            }

            protected void openerCountChanged(@NotNull Level level, @NotNull BlockPos blockPos, @NotNull BlockState blockState, int i, int j) {
            }

            protected boolean isOwnContainer(@NotNull Player player) {
                if (player.containerMenu instanceof DrawerMenu) {
                    Container container = ((DrawerMenu)player.containerMenu).getContainer();
                    return container == DrawerBlockEntity.this;
                } else {
                    return false;
                }
            }
        };
    }

    public Component getDrawerName() {
        return this.getDisplayName();
    }


    protected void saveAdditional(@NotNull CompoundTag compoundTag) {
        super.saveAdditional(compoundTag);
        if (!this.trySaveLootTable(compoundTag)) {
            ContainerHelper.saveAllItems(compoundTag, this.items);
        }
        // Saves items when the inventory is closed or updated
        System.out.println("Drawer items saved!");

    }

    public void load(@NotNull CompoundTag compoundTag) {
        super.load(compoundTag);
        this.items = NonNullList.withSize(this.getContainerSize(), ItemStack.EMPTY);
        if (!this.tryLoadLootTable(compoundTag)) {
            ContainerHelper.loadAllItems(compoundTag, this.items);
        }
        // Loads the items when the block is placed with saved NBT
        System.out.println("Drawer items loaded!");

    }

    public int getContainerSize() {
        return 27;
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
            int relativePos = -switch (blockHitResult.getDirection()) {
                case NORTH, SOUTH, EAST, WEST -> (int) Math.floor(1.0 - (localHitPos.y() + 0.5) * 16);
                case UP -> (int) Math.floor((localHitPos.z() + 0.5) * 16);
                case DOWN -> (int) Math.floor(1.0 - (localHitPos.z() + 0.5) * 16);
            };

            raycastResult = new Pair<>(relativePos, blockHitResult.getDirection());
        }

        if (raycastResult.getSecond() == this.getBlockState().getValue(DrawerBlock.FACING)) {

            if (blockEntity instanceof DrawerBlockEntity) {
                if (raycastResult.getFirst() > 7) {
                    ((DrawerBlockEntity) blockEntity).setCustomName(Component.translatable("container.drawer_top"));
                } else {
                    ((DrawerBlockEntity) blockEntity).setCustomName(Component.translatable("container.drawer_bottom"));
                }
            }

            return new DrawerMenu(i, inventory, this, raycastResult.getFirst() < 7 ? 0 : 5);
        }
        return null;
    }

    //UNUSED, SHOULDN'T BE CALLED (?)
    @Override
    protected @NotNull AbstractContainerMenu createMenu(int i, Inventory inventory) {
        return new DrawerMenu(i, inventory, this, 0);
    }

    public void startOpen(@NotNull Player player) {
        if (!this.remove && !player.isSpectator()) {
            this.openersCounter.incrementOpeners(player, Objects.requireNonNull(this.getLevel()), this.getBlockPos(), this.getBlockState());
        }
        Pair<Integer, Direction> raycastResult = new Pair<>(0, Direction.UP);

        HitResult hitResult = player.pick(10, 1, false);
        if (hitResult.getType() == HitResult.Type.BLOCK) {
            BlockHitResult blockHitResult = (BlockHitResult) hitResult;
            Vec3 localHitPos = blockHitResult.getLocation().subtract(Vec3.atCenterOf(blockHitResult.getBlockPos()));

            int relativePos = -switch (blockHitResult.getDirection()) {
                case NORTH, SOUTH, EAST, WEST -> (int) Math.floor(1.0 - (localHitPos.y() + 0.5) * 16);
                case UP -> (int) Math.floor((localHitPos.z() + 0.5) * 16);
                case DOWN -> (int) Math.floor(1.0 - (localHitPos.z() + 0.5) * 16);
            };

            raycastResult = new Pair<>(relativePos, blockHitResult.getDirection());
            if (raycastResult.getFirst() < 7) {
                this.updateBottomState(this.getBlockState(), true);
            } else {
                this.updateTopState(this.getBlockState(), true);
            }
        }
    }

    public void stopOpen(@NotNull Player player) {
        if (!this.remove && !player.isSpectator()) {
            this.openersCounter.decrementOpeners(player, Objects.requireNonNull(this.getLevel()), this.getBlockPos(), this.getBlockState());
        }

        Pair<Integer, Direction> raycastResult = new Pair<>(0, Direction.UP);

        HitResult hitResult = player.pick(10, 1, false);
        if (hitResult.getType() == HitResult.Type.BLOCK) {
            BlockHitResult blockHitResult = (BlockHitResult) hitResult;
            Vec3 localHitPos = blockHitResult.getLocation().subtract(Vec3.atCenterOf(blockHitResult.getBlockPos()));

            int relativePos = -switch (blockHitResult.getDirection()) {
                case NORTH, SOUTH, EAST, WEST -> (int) Math.floor(1.0 - (localHitPos.y() + 0.5) * 16);
                case UP -> (int) Math.floor((localHitPos.z() + 0.5) * 16);
                case DOWN -> (int) Math.floor(1.0 - (localHitPos.z() + 0.5) * 16);
            };

            raycastResult = new Pair<>(relativePos, blockHitResult.getDirection());
            if (raycastResult.getFirst() < 7) {
                this.updateBottomState(this.getBlockState(), false);
            } else {
                this.updateTopState(this.getBlockState(), false);
            }
        }

    }

    public void recheckOpen() {
        if (!this.remove) {
            this.openersCounter.recheckOpeners(Objects.requireNonNull(this.getLevel()), this.getBlockPos(), this.getBlockState());
        }

    }

    void updateTopState(BlockState blockState, boolean bl) {
        assert this.level != null;
        this.level.setBlock(this.getBlockPos(), blockState.setValue(DrawerBlock.TOP_OPEN, bl), 3);
    }

    void updateBottomState(BlockState blockState, boolean bl) {
        assert this.level != null;
        this.level.setBlock(this.getBlockPos(), blockState.setValue(DrawerBlock.BOTTOM_OPEN, bl), 3);
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
