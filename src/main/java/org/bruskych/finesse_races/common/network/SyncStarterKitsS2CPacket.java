package org.bruskych.finesse_races.common.network;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.network.NetworkEvent;

import org.bruskych.finesse_races.common.network.core.IFinessePacket;
import org.bruskych.finesse_races.gameplay.races.core.AbstractRace;
import org.bruskych.finesse_races.gameplay.races.core.RaceManager;
import org.bruskych.finesse_races.gameplay.starter.RaceStarterKit;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

/**
 * A network packet for synchronizing starter kits from the server to the client.
 */
public class SyncStarterKitsS2CPacket implements IFinessePacket {

    // The key - race ID, the value - items (starter kit).
    private final Map<String, RaceStarterKit> kits;

    public SyncStarterKitsS2CPacket(Map<String, RaceStarterKit> kits) {
        this.kits = kits;
    }

    @Override
    public void encode(FriendlyByteBuf buf) {
        buf.writeInt(this.kits.size());
        for (Map.Entry<String, RaceStarterKit> entry : this.kits.entrySet()) {
            buf.writeUtf(entry.getKey());
            entry.getValue().encode(buf);
        }
    }

    public static SyncStarterKitsS2CPacket decode(FriendlyByteBuf buf) {
        int size = buf.readInt();
        Map<String, RaceStarterKit> decodedKits = new HashMap<>(size);
        for (int i = 0; i < size; i++) {
            String raceId = buf.readUtf();
            RaceStarterKit kit = RaceStarterKit.decode(buf);
            decodedKits.put(raceId, kit);
        }
        return new SyncStarterKitsS2CPacket(decodedKits);
    }

    @Override
    public void handle(Supplier<NetworkEvent.Context> supplier) {
        NetworkEvent.Context context = supplier.get();
        context.enqueueWork(() -> {
            for (Map.Entry<String, RaceStarterKit> entry : this.kits.entrySet()) {
                AbstractRace race = RaceManager.getRace(entry.getKey());
                if (race != null) {
                    race.setStarterKit(entry.getValue());
                }
            }
        });
        context.setPacketHandled(true);
    }
}