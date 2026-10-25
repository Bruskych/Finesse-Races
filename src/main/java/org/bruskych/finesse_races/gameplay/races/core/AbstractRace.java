package org.bruskych.finesse_races.gameplay.races.core;

import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import org.bruskych.finesse_races.gameplay.abilities.core.RaceAbility;
import org.bruskych.finesse_races.gameplay.starter.RaceStarterKit;

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

    private RaceStarterKit starterKit = RaceStarterKit.EMPTY;

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

    /**
     * --- DISPLAY & DIFFICULTY SETTINGS ---
     * GET | SET
     */

    // Gets the display order of the race in menus.
    public int getDisplayOrder() {
        return displayOrder;
    }

    // Sets the display order of the race in menus.
    public void setDisplayOrder(int displayOrder) {
        this.displayOrder = displayOrder;
    }

    // Gets the difficulty rating of the race (1-3).
    public int getDifficulty() {
        return difficulty;
    }

    // Sets the difficulty rating of the race (1-3).
    public void setDifficulty(int difficulty) {
        this.difficulty = difficulty;
    }

    /**
     * --- STARTER KIT ACCESSORS ---
     */

    // Gets the full starter kit object for this race.
    public RaceStarterKit getStarterKit() {
        return this.starterKit;
    }

    // Sets the full starter kit object for this race.
    public void setStarterKit(RaceStarterKit starterKit) {
        this.starterKit = starterKit != null ? starterKit : RaceStarterKit.EMPTY;
    }

    // Gets all 9 hotbar items from the starter kit.
    public List<ItemStack> getStarterHotbar() {
        return this.starterKit.getHotbarItems();
    }

    // Gets a specific hotbar item by slot index (0-8).
    public ItemStack getStarterHotbarItem(int slot) {
        return this.starterKit.getHotbarItem(slot);
    }

    // Gets the offhand item from the starter kit.
    public ItemStack getStarterOffhandItem() {
        return this.starterKit.getOffhandItem();
    }

    // Sets a specific item in the hotbar at the given slot (0-8). Creates a kit if empty.
    public void setStarterHotbarItem(int slot, ItemStack stack) {
        if (this.starterKit == RaceStarterKit.EMPTY) {
            this.starterKit = new RaceStarterKit();
        }
        this.starterKit.setHotbarItem(slot, stack != null ? stack : ItemStack.EMPTY);
    }

    // Sets the offhand item for the starter kit. Creates a kit if empty.
    public void setStarterOffhandItem(ItemStack stack) {
        if (this.starterKit == RaceStarterKit.EMPTY) {
            this.starterKit = new RaceStarterKit();
        }
        this.starterKit.setOffhandItem(stack != null ? stack : ItemStack.EMPTY);
    }

    /**
     * --- ABSTRACT RACE DEFINITIONS ---
     */

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
