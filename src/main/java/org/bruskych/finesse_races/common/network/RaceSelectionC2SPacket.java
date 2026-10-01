package org.bruskych.finesse_races.common.network;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.network.NetworkEvent;
import org.bruskych.finesse_races.gameplay.races.AbstractRace;
import org.bruskych.finesse_races.gameplay.races.RaceManager;

import java.util.function.Supplier;

public class RaceSelectionC2SPacket {
    private final String raceId;

    // Creating a package on the client
    public RaceSelectionC2SPacket(String raceId) {
        this.raceId = raceId;
    }

    // Unpacking on the server
    public RaceSelectionC2SPacket(FriendlyByteBuf buf) {
        this.raceId = buf.readUtf();
    }

    // Packaging is the client's responsibility.
    public void toBytes(FriendlyByteBuf buf) {
        buf.writeUtf(this.raceId);
    }

    // Server-side action
    public boolean handle(Supplier<NetworkEvent.Context> supplier) {
        NetworkEvent.Context context = supplier.get();
        context.enqueueWork(() -> {
            ServerPlayer player = context.getSender();
            if (player != null) {
                AbstractRace race = RaceManager.getRace(this.raceId);
                if (race != null) {
                    RaceManager.setPlayerRace(player, race);
                }
            }
        });
        return true;
    }
}
