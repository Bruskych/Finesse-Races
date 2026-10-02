package org.bruskych.finesse_races.common.network;

import net.minecraft.client.Minecraft;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.network.NetworkEvent;
import org.bruskych.finesse_races.client.ClientPacketHandler;
import org.bruskych.finesse_races.client.gui.screens.RaceSelectionScreen;

import java.util.function.Supplier;

public class OpenRaceScreenS2CPacket {

    public OpenRaceScreenS2CPacket() {
        // Empty
    }

    public static void encode(OpenRaceScreenS2CPacket msg, FriendlyByteBuf buf) {
        // Empty
    }

    public static OpenRaceScreenS2CPacket decode(FriendlyByteBuf buf) {
        return new OpenRaceScreenS2CPacket();
    }

    public void handle(Supplier<NetworkEvent.Context> supplier) {
        NetworkEvent.Context context = supplier.get();
        context.enqueueWork(() -> {
            ClientPacketHandler.openRaceScreen();
        });
        context.setPacketHandled(true);
    }
}