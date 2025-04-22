package com.starfish_studios.yaf.block.entity;

import com.starfish_studios.yaf.block.properties.ChairType;
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


public class ChairBlockEntity extends BlockEntity {

    private ColorList color = ColorList.EMPTY;
    private ChairType chairType = ChairType.TYPE_1;

    public boolean hasBack() {
        return hasBack;
    }

    public void setHasBack(boolean hasBack) {
        this.hasBack = hasBack;
        setChanged();
    }

    private boolean hasBack = false;

    public ChairBlockEntity(BlockPos pos, BlockState blockState) {
        super(YAFBlockEntities.CHAIR.get(), pos, blockState);
    }

    public ColorList getColor() {
        return color;
    }

    public void setColor(ColorList color) {
        this.color = color;
        setChanged();
    }

    public ChairType getChairType() {
        return chairType;
    }

    public void setChairType(ChairType type) {
        this.chairType = type;
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
        tag.putBoolean("hasBack", hasBack);
        ColorList.CODEC.encodeStart(NbtOps.INSTANCE, color)
                .result()
                .ifPresent(encoded -> tag.put("Color", encoded));
        ChairType.CODEC.encodeStart(NbtOps.INSTANCE, chairType)
                .result()
                .ifPresent(encoded -> tag.put("ChairType", encoded));
    }

    @Override
    public void load(CompoundTag tag) {
        super.load(tag);
        hasBack = tag.getBoolean("hasBack");
        if (tag.contains("Color", Tag.TAG_STRING)) {
            this.color = ColorList.CODEC.parse(NbtOps.INSTANCE, tag.get("Color"))
                    .result()
                    .orElse(ColorList.WHITE);
        }
        if (tag.contains("ChairType", Tag.TAG_STRING)) {
            this.chairType = ChairType.CODEC.parse(NbtOps.INSTANCE, tag.get("ChairType"))
                    .result()
                    .orElse(ChairType.TYPE_1);
        }
    }


}