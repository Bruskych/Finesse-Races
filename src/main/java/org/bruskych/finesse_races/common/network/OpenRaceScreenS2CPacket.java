package org.bruskych.finesse_races.common.network;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.network.NetworkEvent;
import org.bruskych.finesse_races.client.network.ClientPacketHandler;
import org.bruskych.finesse_races.common.network.core.IFinessePacket;

import java.util.function.Supplier;

/**
 * Requests the client to open the race selection screen.
 */
public class OpenRaceScreenS2CPacket implements IFinessePacket {

    public OpenRaceScreenS2CPacket() {
        // Empty
    }

    @Override
    public void encode(FriendlyByteBuf buf) {
        // Empty (a packet with no data has nothing to write to the buffer)
    }

    public static OpenRaceScreenS2CPacket decode(FriendlyByteBuf buf) {
        return new OpenRaceScreenS2CPacket();
    }

    @Override
    public void handle(Supplier<NetworkEvent.Context> supplier) {
        NetworkEvent.Context context = supplier.get();
        context.enqueueWork(ClientPacketHandler::openRaceScreen);
        context.setPacketHandled(true);
    }
}