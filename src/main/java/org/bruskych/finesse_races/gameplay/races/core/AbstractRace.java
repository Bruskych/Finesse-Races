package org.bruskych.finesse_races.gameplay.races.core;

import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Player;
import org.bruskych.finesse_races.gameplay.abilities.core.RaceAbility;

import java.util.List;

/**
 * Race framework (Abstract base class).
 * Automates logic execution: a new race doesn't need custom tick handling,
 * it just provides a list of abilities, and this class iterates through them automatically.
 */
public abstract class AbstractRace {

    private int displayOrder;
    private int difficulty;
    private final int plateU;
    private final int plateV;

    /**
     * @param defaultDisplayOrder List position (default)
     * @param defaultDifficulty Race difficulty from 1 to 3 (default)
     * @param plateU X-coordinate on the texture for the race's name plate
     * @param plateV Y-coordinate on the texture for the race's name plate
     */
    public AbstractRace(int defaultDisplayOrder, int defaultDifficulty, int plateU, int plateV) {
        this.displayOrder = defaultDisplayOrder;
        this.difficulty = defaultDifficulty;
        this.plateU = plateU;
        this.plateV = plateV;
    }

    // The X-coordinate (U) on the texture for this race's nameplate.
    public int getPlateU() {
        return this.plateU;
    }

    // The Y-coordinate (V) on the texture for this race's nameplate.
    public int getPlateV() {
        return this.plateV;
    }

    // GET | SET
    public int getDisplayOrder() {
        return displayOrder;
    }
    public void setDisplayOrder(int displayOrder) {
        this.displayOrder = displayOrder;
    }
    public int getDifficulty() {
        return difficulty;
    }
    public void setDifficulty(int difficulty) {
        this.difficulty = difficulty;
    }

    // Technical identifier for the race
    public abstract String getId();

    // Display name for the RaceSelectionScreen
    public abstract Component getDisplayName();

    // Display story for the RaceSelectionScreen
    public abstract Component getDisplayStory();

    // Every race must provide a list of its abilities
    public abstract List<RaceAbility> getAbilities();

    // Main logic method called every tick
    public void onPlayerTick(Player player) {
        for (RaceAbility ability : getAbilities()) {
            ability.apply(player);
        }
    }
}
