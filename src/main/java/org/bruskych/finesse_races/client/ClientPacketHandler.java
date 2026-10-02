package org.bruskych.finesse_races.client;

import net.minecraft.client.Minecraft;
import org.bruskych.finesse_races.client.gui.screens.RaceSelectionScreen;

/**
 * Client-side packet handler (Client-side network packet handler).
 * Isolates the execution of client-side-only code from general network packets.
 * Methods will be added here for:
 * - Opening other interfaces
 * - Synchronizing and updating the custom HUD (ability cooldowns)
 * - Spawning unique particles
 * - Playing client-side visual and sound effects
 */
public class ClientPacketHandler {
    public static void openRaceScreen() {
        Minecraft.getInstance().setScreen(new RaceSelectionScreen());
    }
}