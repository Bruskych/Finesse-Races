package org.bruskych.finesse_races.client.network;

import net.minecraft.client.Minecraft;
import net.minecraft.world.entity.player.Player;

import org.bruskych.finesse_races.client.gui.screens.RaceSelectionScreen;
import org.bruskych.finesse_races.gameplay.races.core.AbstractRace;
import org.bruskych.finesse_races.gameplay.races.core.RaceManager;

import java.util.UUID;

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

    /**
     * Opens the Race Selection GUI screen.
     */
    public static void openRaceScreen() {
        Minecraft.getInstance().setScreen(new RaceSelectionScreen());
    }

    /**
     * Updates a player's cached race on the client and triggers an immediate dimension refresh.
     *
     * @param playerUUID The unique identifier of the target player.
     * @param raceId     The race ID received from the server, or "none" to clear it.
     */
    public static void handleSyncPlayerRace(UUID playerUUID, String raceId) {
        Minecraft mc = Minecraft.getInstance();
        Player player = mc.level != null ? mc.level.getPlayerByUUID(playerUUID) : null;
        AbstractRace race = "none".equals(raceId) ? null : RaceManager.getRace(raceId);
        RaceManager.setClientPlayerRace(playerUUID, player, race);
    }
}