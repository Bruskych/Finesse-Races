package org.bruskych.finesse_races.gameplay.races.core;

import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;

import org.bruskych.finesse_races.gameplay.abilities.modifiers.*;
import org.bruskych.finesse_races.gameplay.traits.modifiers.*;
import org.bruskych.finesse_races.gameplay.core.RaceFeature;
import org.bruskych.finesse_races.gameplay.abilities.core.RaceAbility;
import org.bruskych.finesse_races.gameplay.conditions.PlayerConditionHandler;
import org.bruskych.finesse_races.gameplay.starter.RaceStarterKit;
import org.bruskych.finesse_races.gameplay.traits.core.RaceTrait;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Race framework (Abstract base class).
 * Automates logic execution: a new race doesn't need custom tick handling,
 * it just provides a list of abilities, and this class iterates through them automatically.
 */
public abstract class AbstractRace {

    // --- DISPLAY & GUI SETTINGS ---
    private int displayOrder;
    private int difficulty;
    private final int plateU;
    private final int plateV;

    private RaceStarterKit starterKit = RaceStarterKit.EMPTY;

    /**
     * --- EVENT LISTENERS CACHE ---
     */

    // We store abilities in separate lists based on their implemented interfaces.
    // This allows the server to instantly find relevant abilities without checking every single one.
    private final List<IHurtModifier> hurtModifiers = new ArrayList<>();
    private final List<IItemUseModifier> itemUseModifiers = new ArrayList<>();
    private final List<IPotionSplashModifier> potionSplashModifiers = new ArrayList<>();
    private final List<IEnderAngerModifier> enderAngerModifiers = new ArrayList<>();
    private final List<ITickModifier> tickModifiers = new ArrayList<>();
    private final List<IAttackModifier> attackModifiers = new ArrayList<>();
    private final List<ILavaVisionModifier> lavaVisionModifiers = new ArrayList<>();
    private final List<IEnderPearlModifier> enderPearlModifiers = new ArrayList<>();
    private final List<IStepHeightModifier> stepHeightModifiers = new ArrayList<>();
    private final List<IBlockReachModifier> blockReachModifiers = new ArrayList<>();
    private final List<IEntityReachModifier> entityReachModifiers = new ArrayList<>();
    private final List<IMovementSpeedModifier> movementSpeedModifiers = new ArrayList<>();
    private final List<ISwimSpeedModifier> swimSpeedModifiers = new ArrayList<>();
    private final List<IAttackDamageModifier> attackDamageModifiers = new ArrayList<>();
    private final List<IMiningSpeedModifier> miningSpeedModifiers = new ArrayList<>();
    private final List<IAttackSpeedModifier> attackSpeedModifiers = new ArrayList<>();

    // Precomputed hitbox and camera profile (defaults to vanilla behavior)
    private PlayerDimensionsProfile dimensionsProfile = PlayerDimensionsProfile.EMPTY;

    /**
     * Initializes the event listener caches.
     * Iterates through all abilities belonging to this race and categorizes them by interface.
     * This guarantees O(1) performance during gameplay, protecting server TPS.
     */
    public void cacheAbilityListeners() {
        this.hurtModifiers.clear();
        this.itemUseModifiers.clear();
        this.potionSplashModifiers.clear();
        this.enderAngerModifiers.clear();
        this.tickModifiers.clear();
        this.attackModifiers.clear();
        this.lavaVisionModifiers.clear();
        this.enderPearlModifiers.clear();
        this.stepHeightModifiers.clear();
        this.blockReachModifiers.clear();
        this.entityReachModifiers.clear();
        this.movementSpeedModifiers.clear();
        this.swimSpeedModifiers.clear();
        this.attackDamageModifiers.clear();
        this.miningSpeedModifiers.clear();
        this.attackSpeedModifiers.clear();

        this.dimensionsProfile = PlayerDimensionsProfile.EMPTY;
        boolean hasSizeModifier = false;

        List<RaceFeature> allFeatures = new ArrayList<>();
        allFeatures.addAll(getAbilities());
        allFeatures.addAll(getTraits());

        for (RaceFeature feature : allFeatures) {
            if (feature instanceof IHurtModifier hm) this.hurtModifiers.add(hm);
            if (feature instanceof IItemUseModifier ium) this.itemUseModifiers.add(ium);
            if (feature instanceof IPotionSplashModifier psm) this.potionSplashModifiers.add(psm);
            if (feature instanceof IEnderAngerModifier eam) this.enderAngerModifiers.add(eam);
            if (feature instanceof ITickModifier tm) this.tickModifiers.add(tm);
            if (feature instanceof IAttackModifier am) this.attackModifiers.add(am);
            if (feature instanceof ILavaVisionModifier lvm) this.lavaVisionModifiers.add(lvm);
            if (feature instanceof IEnderPearlModifier epm) this.enderPearlModifiers.add(epm);
            if (feature instanceof IStepHeightModifier shm) this.stepHeightModifiers.add(shm);
            if (feature instanceof IBlockReachModifier brm) this.blockReachModifiers.add(brm);
            if (feature instanceof IEntityReachModifier erm) this.entityReachModifiers.add(erm);
            if (feature instanceof IMovementSpeedModifier msm) this.movementSpeedModifiers.add(msm);
            if (feature instanceof ISwimSpeedModifier ssm) this.swimSpeedModifiers.add(ssm);
            if (feature instanceof IAttackDamageModifier adm) this.attackDamageModifiers.add(adm);
            if (feature instanceof IMiningSpeedModifier msm) this.miningSpeedModifiers.add(msm);
            if (feature instanceof IAttackSpeedModifier asm) this.attackSpeedModifiers.add(asm);

            if (feature instanceof IEntitySizeModifier sizeModifier) {
                if (hasSizeModifier) {
                    throw new IllegalStateException("Race '" + getId() + "' defines multiple IEntitySizeModifier instances.");
                }
                PlayerDimensionsProfile profile = sizeModifier.getDimensionsProfile();
                this.dimensionsProfile = profile != null ? profile : PlayerDimensionsProfile.EMPTY;
                hasSizeModifier = true;
            }
        }
    }

    /**
     * A powerful generic method to retrieve a specific list of modifiers at O(1) speed.
     * Used by managers (like RaceAttributeManager) to quickly apply math without knowing about specific classes.
     *
     * @param type The Class of the micro-interface (e.g., IStepHeightModifier.class).
     * @param <T>  The type of the modifier.
     * @return A list of modifiers that implement the requested interface.
     */
    @SuppressWarnings("unchecked")
    public <T> List<T> getModifiersOf(Class<T> type) {
        if (type == IStepHeightModifier.class) return (List<T>) this.stepHeightModifiers;
        if (type == IHurtModifier.class) return (List<T>) this.hurtModifiers;
        if (type == IItemUseModifier.class) return (List<T>) this.itemUseModifiers;
        if (type == IPotionSplashModifier.class) return (List<T>) this.potionSplashModifiers;
        if (type == IEnderAngerModifier.class) return (List<T>) this.enderAngerModifiers;
        if (type == ITickModifier.class) return (List<T>) this.tickModifiers;
        if (type == IAttackModifier.class) return (List<T>) this.attackModifiers;
        if (type == ILavaVisionModifier.class) return (List<T>) this.lavaVisionModifiers;
        if (type == IEnderPearlModifier.class) return (List<T>) this.enderPearlModifiers;
        if (type == IBlockReachModifier.class) return (List<T>) this.blockReachModifiers;
        if (type == IEntityReachModifier.class) return (List<T>) this.entityReachModifiers;
        if (type == IMovementSpeedModifier.class) return (List<T>) this.movementSpeedModifiers;
        if (type == ISwimSpeedModifier.class) return (List<T>) this.swimSpeedModifiers;
        if (type == IAttackDamageModifier.class) return (List<T>) this.attackDamageModifiers;
        if (type == IMiningSpeedModifier.class) return (List<T>) this.miningSpeedModifiers;
        if (type == IAttackSpeedModifier.class) return (List<T>) this.attackSpeedModifiers;

        return Collections.emptyList();
    }

    // --- FAST GETTERS ---
    // These methods provide instant access to the cached abilities for Forge event handlers.
    public List<IHurtModifier> getHurtModifiers() { return this.hurtModifiers; }
    public List<IItemUseModifier> getItemUseModifiers() { return this.itemUseModifiers; }
    public List<IPotionSplashModifier> getPotionSplashModifiers() { return this.potionSplashModifiers; }
    public List<IEnderAngerModifier> getEnderAngerModifiers() { return this.enderAngerModifiers; }
    public List<IAttackModifier> getAttackModifiers() { return this.attackModifiers; }
    public List<ILavaVisionModifier> getLavaVisionModifiers() { return this.lavaVisionModifiers; }
    public List<IEnderPearlModifier> getEnderPearlModifiers() { return this.enderPearlModifiers; }

    // Returns the precomputed hitbox and eye height profile for this race.
    public PlayerDimensionsProfile getDimensionsProfile() { return this.dimensionsProfile; }

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

    // Every race must provide a list of its simple traits (attributes)
    public abstract List<RaceTrait> getTraits();

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
        if (PlayerConditionHandler.isSelectingRace(player)) {
            return;
        }
        if (!this.tickModifiers.isEmpty()) {
            for (ITickModifier modifier : this.tickModifiers) {
                modifier.onTick(player);
            }
        }
    }
}
