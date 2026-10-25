package org.bruskych.finesse_races.common.network.core;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

/**
 * Base interface for all network packets (S2C and C2S) in the Finesse Races mod.
 */
public interface IFinessePacket {

    /* ====================================================================================
     * DECODER (Unpacker): Called on the receiver's side.
     *
     * IMPORTANT: Java interfaces cannot enforce static methods. Therefore, EVERY class
     * implementing this interface MUST manually include a static decode method.
     *
     * What it does: Creates a new packet object from the raw bytes received over the network.
     *
     * Rule: Data MUST be read (e.g., buf.readInt()) in the EXACT SAME ORDER it was
     * written in the encode method!
     *
     * Template to copy-paste into your packet classes:
     *
     * public static [YourPacketClass] decode(FriendlyByteBuf buf) {
     *     // 1. Read data from the buffer (e.g., int id = buf.readInt();)
     *     // 2. Return a new instance of the packet (e.g., return new [YourPacketClass](id);)
     * }
     * ==================================================================================== */

    /**
     * ENCODER: Called on the sender's side.
     * Converts the variables of this object into raw bytes for network transmission.
     * Rule: The exact order in which data is written here (e.g., buf.writeInt -> buf.writeUtf)
     * MUST be strictly followed when reading in the static decode method!
     *
     * @param buf The buffer into which data is written (the shipping box)
     */
    void encode(FriendlyByteBuf buf);

    /**
     * HANDLER: Called on the receiver's side once the packet has arrived
     * and been successfully unpacked by the decode method. Contains the main execution logic.
     * Rule: Any changes to the world, inventory, or GUI must be executed
     * inside context.enqueueWork(() -> { ... }) to ensure the code runs safely on the main game thread.
     *
     * @param supplier The network context supplier (contains information about the sender and threads)
     */
    void handle(Supplier<NetworkEvent.Context> supplier);
}