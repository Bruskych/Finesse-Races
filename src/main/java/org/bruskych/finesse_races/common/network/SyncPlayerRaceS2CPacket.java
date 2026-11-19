package org.bruskych.finesse_races.common.network;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.network.NetworkEvent;

import org.bruskych.finesse_races.client.network.ClientPacketHandler;
import org.bruskych.finesse_races.common.network.core.IFinessePacket;

import java.util.UUID;
import java.util.function.Supplier;

/**
 * Synchronizes a player's active race from the server to clients.
 * Ensures client-side rendering, camera eye height, and hitboxes remain in sync.
 */
public class SyncPlayerRaceS2CPacket implements IFinessePacket {

    private final UUID playerUUID;
    private final String raceId;

    /**
     * @param playerUUID The unique identifier of the target player.
     * @param raceId     The race ID, or "none" if the player's race was cleared.
     */
    public SyncPlayerRaceS2CPacket(UUID playerUUID, String raceId) {
        this.playerUUID = playerUUID;
        this.raceId = raceId;
    }

    /**
     * Writes the player's UUID and race ID into the network buffer.
     */
    @Override
    public void encode(FriendlyByteBuf buf) {
        buf.writeUUID(this.playerUUID);
        buf.writeUtf(this.raceId);
    }

    /**
     * Reads the player's UUID and race ID from the network buffer.
     */
    public static SyncPlayerRaceS2CPacket decode(FriendlyByteBuf buf) {
        return new SyncPlayerRaceS2CPacket(buf.readUUID(), buf.readUtf());
    }

    /**
     * Processes the packet on the main client thread.
     * Delegates execution to ClientPacketHandler to avoid loading client-only classes on a dedicated server.
     */
    @Override
    public void handle(Supplier<NetworkEvent.Context> supplier) {
        NetworkEvent.Context context = supplier.get();
        context.enqueueWork(() ->
                DistExecutor.unsafeRunWhenOn(Dist.CLIENT, () ->
                        () -> ClientPacketHandler.handleSyncPlayerRace(this.playerUUID, this.raceId)
                )
        );
        context.setPacketHandled(true);
    }
}