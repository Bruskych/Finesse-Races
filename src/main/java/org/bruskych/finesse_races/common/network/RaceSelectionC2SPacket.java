package org.bruskych.finesse_races.common.network;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.network.NetworkEvent;

import org.bruskych.finesse_races.common.network.core.IFinessePacket;
import org.bruskych.finesse_races.gameplay.conditions.PlayerConditionHandler;
import org.bruskych.finesse_races.gameplay.races.core.AbstractRace;
import org.bruskych.finesse_races.gameplay.races.core.RaceManager;
import org.bruskych.finesse_races.gameplay.starter.StarterKitHandler;

import java.util.function.Supplier;

/**
 * Handles the client-to-server packet sent when a player selects a race.
 * Transfers the selected race ID to the server, where the player's race
 * is set and the corresponding starter kit is granted.
 */
public class RaceSelectionC2SPacket implements IFinessePacket {
    private final String raceId;

    public RaceSelectionC2SPacket(String raceId) {
        this.raceId = raceId;
    }

    @Override
    public void encode(FriendlyByteBuf buf) {
        buf.writeUtf(this.raceId);
    }

    public static RaceSelectionC2SPacket decode(FriendlyByteBuf buf) {
        String extractedRaceId = buf.readUtf();
        return new RaceSelectionC2SPacket(extractedRaceId);
    }

    @Override
    public void handle(Supplier<NetworkEvent.Context> supplier) {
        NetworkEvent.Context context = supplier.get();
        context.enqueueWork(() -> {
            ServerPlayer player = context.getSender();
            if (player != null) {
                AbstractRace race = RaceManager.getRace(this.raceId);
                if (race != null) {
                    // RACE
                    RaceManager.setPlayerRace(player, race);
                    // STARTER KIT
                    StarterKitHandler.tryGiveRaceStarterKit(player, race);
                    // DISABLE immortality
                    PlayerConditionHandler.setSelectingRace(player, false);

                    // Delete Race Orb
                    if (!player.isCreative()) {
                        if (player.getMainHandItem().getItem() instanceof org.bruskych.finesse_races.common.items.RaceOrbItem) {
                            player.getMainHandItem().shrink(1);
                        } else if (player.getOffhandItem().getItem() instanceof org.bruskych.finesse_races.common.items.RaceOrbItem) {
                            player.getOffhandItem().shrink(1);
                        }
                    }
                }
            }
        });
        context.setPacketHandled(true);
    }
}
