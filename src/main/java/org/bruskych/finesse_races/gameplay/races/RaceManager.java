package org.bruskych.finesse_races.gameplay.races;

import net.minecraft.world.entity.player.Player;

import java.util.*;

import org.bruskych.finesse_races.core.FRConfig;

/**
 * Race Manager (Central Registry).
 * Performs two main tasks: stores all registered races in memory at server startup,
 * and handles reading/writing player race IDs to individual player save data (NBT tags).
 */
public class RaceManager {

    // Stores all existing races in the mod
    private static final Map<String, AbstractRace> RACES = new HashMap<>();

    // Stores races only for players currently online on the server
    private static final Map<UUID, AbstractRace> ACTIVE_PLAYERS = new HashMap<>();

    // Triggered once at startup (registers all available races)
    static {
        registerRace(new AquanRace());
    }

    public static void applyConfigSettings() {
        List<? extends String> configList = FRConfig.COMMON.raceSettings.get();

        for (String entry : configList) {
            String[] parts = entry.split(",");

            if (parts.length == 3) {
                String raceId = parts[0].trim();

                AbstractRace race = getRace(raceId);
                if (race != null) {
                    try {
                        int displayOrder = Integer.parseInt(parts[1].trim());
                        int difficulty = Integer.parseInt(parts[2].trim());

                        race.setDisplayOrder(displayOrder);
                        race.setDifficulty(difficulty);
                    } catch (NumberFormatException e) {
                    }
                }
            }
        }
    }

    // Registers a new race into the dictionary
    public static void registerRace(AbstractRace race) {
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
        return ACTIVE_PLAYERS.get(player.getUUID());
    }

    // Called once when a player logs into the server
    public static void loadPlayerRace(Player player) {
        String raceId = player.getPersistentData().getString("finesse_race_id");
        if (!raceId.isEmpty()) {
            AbstractRace race = getRace(raceId);
            if (race != null) {
                ACTIVE_PLAYERS.put(player.getUUID(), race);
            }
        }
    }

    // Called when a player leaves the server (frees up memory)
    public static void unloadPlayerRace(Player player) {
        ACTIVE_PLAYERS.remove(player.getUUID());
    }

    // Sets or updates a player's race in-game
    public static void setPlayerRace(Player player, AbstractRace race) {
        if (race != null) {
            player.getPersistentData().putString("finesse_race_id", race.getId());
            ACTIVE_PLAYERS.put(player.getUUID(), race);
        } else {
            player.getPersistentData().remove("finesse_race_id");
            ACTIVE_PLAYERS.remove(player.getUUID());
        }
    }
}
