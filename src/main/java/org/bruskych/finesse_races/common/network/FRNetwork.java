package org.bruskych.finesse_races.common.network;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.network.NetworkDirection;
import net.minecraftforge.network.NetworkRegistry;
import net.minecraftforge.network.PacketDistributor;
import net.minecraftforge.network.simple.SimpleChannel;

import org.bruskych.finesse_races.core.FinesseRaces;

/**
 * Communication channel registration (The class acts as a post office).
 */
public class FRNetwork {
    private static SimpleChannel INSTANCE;
    private static int packetId = 0;
    public static final String NETWORK_PROTOCOL = "FR1";

    /**
     * Returns the next unique packet ID.
     */
    private static int id() {
        return packetId++;
    }

    /**
     * Creates the network channel and registers all mod packets.
     */
    public static void register() {
        SimpleChannel net = NetworkRegistry.ChannelBuilder
                .named(ResourceLocation.fromNamespaceAndPath(FinesseRaces.MOD_ID, "messages"))
                .networkProtocolVersion(() -> NETWORK_PROTOCOL)
                .clientAcceptedVersions(NETWORK_PROTOCOL::equals)
                .serverAcceptedVersions(NETWORK_PROTOCOL::equals)
                .simpleChannel();
        INSTANCE = net;
        net.messageBuilder(RaceSelectionC2SPacket.class, id(), NetworkDirection.PLAY_TO_SERVER)
                .decoder(RaceSelectionC2SPacket::decode)
                .encoder(RaceSelectionC2SPacket::encode)
                .consumerMainThread(RaceSelectionC2SPacket::handle)
                .add();
        net.messageBuilder(OpenRaceScreenS2CPacket.class, id(), NetworkDirection.PLAY_TO_CLIENT)
                .decoder(OpenRaceScreenS2CPacket::decode)
                .encoder(OpenRaceScreenS2CPacket::encode)
                .consumerMainThread(OpenRaceScreenS2CPacket::handle)
                .add();
        net.messageBuilder(SyncStarterKitsS2CPacket.class, id(), NetworkDirection.PLAY_TO_CLIENT)
                .decoder(SyncStarterKitsS2CPacket::decode)
                .encoder(SyncStarterKitsS2CPacket::encode)
                .consumerMainThread(SyncStarterKitsS2CPacket::handle)
                .add();
    }

    /**
     * Sends a packet from the client to the server.
     */
    public static <MSG> void sendToServer(MSG message) {
        INSTANCE.sendToServer(message);
    }

    /**
     * Sends a packet from the server to a specific player.
     */
    public static <MSG> void sendToPlayer(MSG message, ServerPlayer player) {
        INSTANCE.send(PacketDistributor.PLAYER.with(() -> player), message);
    }
}
