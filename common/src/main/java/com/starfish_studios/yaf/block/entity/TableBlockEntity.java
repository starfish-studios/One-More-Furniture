package com.starfish_studios.yaf.block.entity;

import com.starfish_studios.yaf.block.properties.ColorList;
import com.starfish_studios.yaf.registry.YAFBlockEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.NbtOps;
import net.minecraft.nbt.Tag;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.Nullable;


public class TableBlockEntity extends BlockEntity {

    private byte legs = 0b1111; // All legs present by default (bits 0-3 set)
    private ColorList color = ColorList.WHITE;

    public TableBlockEntity(BlockPos pos, BlockState blockState) {
        super(YAFBlockEntities.TABLE.get(), pos, blockState);
    }

    public boolean hasLeg(int leg) {
        if (leg < 1 || leg > 4) return false;
        return (legs & (1 << (leg - 1))) != 0;
    }

    public void setLeg(int leg, boolean present) {
        if (leg < 1 || leg > 4) return;
        if (present) {
            legs |= (byte) (1 << (leg - 1));
        } else {
            legs &= (byte) ~(1 << (leg - 1));
        }
        setChanged();
    }

    public ColorList getColor() {
        return color;
    }

    public void setColor(ColorList color) {
        this.color = color;
        setChanged();
    }

    @Nullable
    @Override
    public Packet<ClientGamePacketListener> getUpdatePacket() {
        return ClientboundBlockEntityDataPacket.create(this);
    }

    @Override
    public CompoundTag getUpdateTag() {
        CompoundTag compound = super.getUpdateTag();
        saveAdditional(compound);
        return compound;
    }

    @Override
    protected void saveAdditional(CompoundTag tag) {
        super.saveAdditional(tag);
        tag.putByte("Legs", legs);
        ColorList.CODEC.encodeStart(NbtOps.INSTANCE, color)
                .result()
                .ifPresent(encoded -> tag.put("Color", encoded));
    }

    @Override
    public void load(CompoundTag tag) {
        super.load(tag);
        this.legs = tag.getByte("Legs");
        if (tag.contains("Color", Tag.TAG_STRING)) {
            this.color = ColorList.CODEC.parse(NbtOps.INSTANCE, tag.get("Color"))
                    .result()
                    .orElse(ColorList.WHITE);
        }
    }
}