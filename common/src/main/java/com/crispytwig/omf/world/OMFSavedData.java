package com.crispytwig.omf.world;

import com.crispytwig.omf.OneMoreFurniture;
import com.mojang.serialization.DataResult;
import net.minecraft.core.GlobalPos;
import net.minecraft.nbt.*;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.saveddata.SavedData;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class OMFSavedData extends SavedData {

    public final List<MailboxData> mailboxes = new ArrayList<>();

    public static OMFSavedData load(CompoundTag nbt) {
        var data = new OMFSavedData();
        ListTag mailboxesTag = nbt.getList("Mailboxes", Tag.TAG_COMPOUND);
        for (int i = 0; i < mailboxesTag.size(); i++) {
            var mailNbt = mailboxesTag.getCompound(i);

            var name = mailNbt.getString("Name");
            var playerName = mailNbt.getString("PlayerName");
            var pos = NbtUtils.readBlockPos(mailNbt.getCompound("GlobalPos"));
            var dim = Level.RESOURCE_KEY_CODEC.parse(NbtOps.INSTANCE, mailNbt.get("MailboxDimension")).result();
            if (dim.isPresent()) {
                var newPair = new MailboxData(playerName, name, GlobalPos.of(dim.get(), pos));
                data.mailboxes.add(newPair);
            }
        }

        return data;
    }

    @Override
    public @NotNull CompoundTag save(@NotNull CompoundTag nbt) {

        var nbtList = new ListTag();

        for (MailboxData mailbox : mailboxes) {
            var mailNbt = new CompoundTag();
            mailNbt.putString("Name", mailbox.name());
            mailNbt.putString("PlayerName", mailbox.playerName());
            mailNbt.put("GlobalPos", NbtUtils.writeBlockPos(mailbox.globalPos.pos()));

            DataResult<Tag> result = Level.RESOURCE_KEY_CODEC.encodeStart(NbtOps.INSTANCE, mailbox.globalPos.dimension());

            result.result().ifPresent(tag -> {
                mailNbt.put("MailboxDimension", tag);
            });

            nbtList.add(mailNbt);
        }
        nbt.put("Mailboxes", nbtList);

        return nbt;
    }

    public static OMFSavedData getMailboxes(ServerLevel level) {
        return level.getServer().overworld().getDataStorage().computeIfAbsent(OMFSavedData::load, OMFSavedData::new, OneMoreFurniture.MOD_ID);
    }

    public record MailboxData(String playerName, String name, GlobalPos globalPos) {}
}