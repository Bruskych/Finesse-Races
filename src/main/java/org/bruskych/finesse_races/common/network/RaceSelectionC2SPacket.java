package org.bruskych.finesse_races.common.network;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.network.NetworkEvent;
import org.bruskych.finesse_races.gameplay.races.core.AbstractRace;
import org.bruskych.finesse_races.gameplay.races.core.RaceManager;

import java.util.function.Supplier;

public class RaceSelectionC2SPacket {
    private final String raceId;

    // Creating a package on the client
    public RaceSelectionC2SPacket(String raceId) {
        this.raceId = raceId;
    }

    // Packaging is the client's responsibility
    public void encode(FriendlyByteBuf buf) {
        buf.writeUtf(this.raceId);
    }

    // Unpacking on the server
    public static RaceSelectionC2SPacket decode(FriendlyByteBuf buf) {
        String extractedRaceId = buf.readUtf();
        return new RaceSelectionC2SPacket(extractedRaceId);
    }

    // Server-side action
    public void handle(Supplier<NetworkEvent.Context> supplier) {
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
        context.setPacketHandled(true);
    }
}
