package org.bruskych.finesse_races.gameplay.traits.core;

import net.minecraft.network.chat.Component;

import org.bruskych.finesse_races.gameplay.core.RaceFeature;

/**
 * Represents a basic numerical characteristic or attribute modifier for a race.
 * Displayed in the GUI as an icon (from a shared sprite sheet) and a value.
 */
public interface RaceTrait extends RaceFeature {

    /**
     * The X-coordinate (U) of the trait's icon on the shared traits sprite sheet.
     *
     * @return The U coordinate in pixels.
     */
    int getIconU();

    /**
     * The Y-coordinate (V) of the trait's icon on the shared traits sprite sheet.
     *
     * @return The V coordinate in pixels.
     */
    int getIconV();

    /**
     * The formatted value to be displayed next to the icon (e.g., "+1.5", "-10%", "x2").
     *
     * @return The string representation of the modifier value.
     */
    String getDisplayValue();

    /**
     * The tooltip text shown when the player hovers over the trait in the GUI.
     *
     * @return A translatable component (e.g., "Step Height: +0.5").
     */
    Component getTooltip();

    /**
     * Retrieves the mechanical numerical value of this trait.
     * Uses the generic Number class to seamlessly support both double (for standard Attributes)
     * and float (for dynamic Forge Events) without type casting conflicts.
     * Often used by the GUI to dynamically colorize tooltips (e.g., green for buffs, red for debuffs).
     *
     * @return The numerical value of the trait's modifier.
     */
    Number getTraitValue();
}