package org.bruskych.finesse_races.gameplay.races.core;

import net.minecraft.world.entity.player.Player;

import org.bruskych.finesse_races.common.network.FRNetwork;
import org.bruskych.finesse_races.common.network.SyncPlayerRaceS2CPacket;
import org.bruskych.finesse_races.gameplay.races.*;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Race Manager (Central Registry).
 * Performs two main tasks: stores all registered races in memory at server startup,
 * and handles reading/writing player race IDs to individual player save data (NBT tags).
 */
public class RaceManager {

    // Stores all existing races in the mod
    private static final Map<String, AbstractRace> RACES = new HashMap<>();

    // Stores races only for players currently online on the server
    private static final Map<UUID, AbstractRace> ACTIVE_PLAYERS = new ConcurrentHashMap<>();

    // Triggered once at startup (registers all available races)
    static {
        registerRace(new DwarfRace());
        registerRace(new AquanRace());
        registerRace(new VampireRace());
        registerRace(new EnderianRace());
        registerRace(new MecharRace());
        registerRace(new AvianRace());
        registerRace(new WerewolfRace());
        registerRace(new VerdianRace());
        registerRace(new DemonRace());
        registerRace(new UndeadRace());
        registerRace(new AlienRace());
        // TODO: More race!
    }

    // Registers a new race into the central registry.
    public static void registerRace(AbstractRace race) {
        // Cache event listeners immediately to avoid runtime overhead
        race.cacheAbilityListeners();
        RACES.put(race.getId(), race);
    }

    // Retrieves a race by its string ID (e.g., "aquatic")
    public static AbstractRace getRace(String id) {
        return RACES.get(id);
    }

    // Returns a collection of all registered races (useful for the RaceSelectionScreen)
    public static Collection<AbstractRace> getAllRaces() {
        return RACES.values();
    }

    // Gets the active race of a specific player
    public static AbstractRace getRaceOfPlayer(Player player) {
        if (player == null) return null;
        UUID uuid = player.getUUID();
        return uuid != null ? ACTIVE_PLAYERS.get(uuid) : null;
    }

    // Loads a player's saved race from persistent NBT data into the active cache.
    public static void loadPlayerRace(Player player) {
        String raceId = player.getPersistentData().getString("finesse_race_id");
        if (!raceId.isEmpty()) {
            AbstractRace race = getRace(raceId);
            if (race != null) {
                ACTIVE_PLAYERS.put(player.getUUID(), race);
                player.refreshDimensions();
            }
        }
    }

    // Called when a player leaves the server (frees up memory)
    public static void unloadPlayerRace(Player player) {
        ACTIVE_PLAYERS.remove(player.getUUID());
    }

    // Sets or updates a player's race in-game (Server-side)
    public static void setPlayerRace(Player player, AbstractRace race) {
        if (race != null) {
            player.getPersistentData().putString("finesse_race_id", race.getId());
            ACTIVE_PLAYERS.put(player.getUUID(), race);
            RaceAttributeManager.applyRaceAttributes(player, race);
            player.refreshDimensions();

            if (!player.level().isClientSide()) {
                FRNetwork.sendToAll(new SyncPlayerRaceS2CPacket(player.getUUID(), race.getId()));
            }
        } else {
            player.getPersistentData().remove("finesse_race_id");
            ACTIVE_PLAYERS.remove(player.getUUID());
            RaceAttributeManager.clearRaceAttributes(player);
            player.refreshDimensions();

            if (!player.level().isClientSide()) {
                FRNetwork.sendToAll(new SyncPlayerRaceS2CPacket(player.getUUID(), "none"));
            }
        }
    }

    // Updates the cached race on the client side without touching server NBT or attributes
    public static void setClientPlayerRace(UUID playerUUID, Player player, AbstractRace race) {
        if (playerUUID == null) return;
        if (race != null) {
            ACTIVE_PLAYERS.put(playerUUID, race);
        } else {
            ACTIVE_PLAYERS.remove(playerUUID);
        }
        if (player != null) {
            player.refreshDimensions();
        }
    }

    // Clears stale race cache on the client when disconnecting from a server or world
    public static void clearCache() {
        ACTIVE_PLAYERS.clear();
    }
}
