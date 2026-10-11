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

    /**
     * A constructor that requires each race to specify its base parameters.
     * @param defaultDisplayOrder List position (default)
     * @param defaultDifficulty Race difficulty from 1 to 3 (default)
     */
    public AbstractRace(int defaultDisplayOrder, int defaultDifficulty) {
        this.displayOrder = defaultDisplayOrder;
        this.difficulty = defaultDifficulty;
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
