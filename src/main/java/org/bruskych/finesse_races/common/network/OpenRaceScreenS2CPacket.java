package org.bruskych.finesse_races.common.network;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.network.NetworkEvent;

import org.bruskych.finesse_races.client.network.ClientPacketHandler;
import org.bruskych.finesse_races.common.network.core.IFinessePacket;

import java.util.function.Supplier;

/**
 * Requests the client to open the race selection screen.
 */
public class OpenRaceScreenS2CPacket implements IFinessePacket {

    /**
     * Creates a new empty packet instance.
     */
    public OpenRaceScreenS2CPacket() {
        // Empty
    }

    /**
     * Writes packet data to the buffer (no data needed for this packet).
     */
    @Override
    public void encode(FriendlyByteBuf buf) {
        // Empty
    }

    /**
     * Reads the buffer and creates a new packet instance.
     */
    public static OpenRaceScreenS2CPacket decode(FriendlyByteBuf buf) {
        return new OpenRaceScreenS2CPacket();
    }

    /**
     * Opens the race selection screen on the client main thread.
     */
    @Override
    public void handle(Supplier<NetworkEvent.Context> supplier) {
        NetworkEvent.Context context = supplier.get();
        context.enqueueWork(() ->
                DistExecutor.unsafeRunWhenOn(Dist.CLIENT, () -> ClientPacketHandler::openRaceScreen)
        );
        context.setPacketHandled(true);
    }
}