package org.bruskych.finesse_races.gameplay.traits;

import net.minecraft.network.chat.Component;
import org.bruskych.finesse_races.gameplay.traits.modifiers.IMovementSpeedModifier;
import org.bruskych.finesse_races.gameplay.traits.core.RaceTrait;

/**
 * A trait that modifies the player's walking/running speed safely without exponential stacking.
 */
public class MovementSpeedTrait implements RaceTrait, IMovementSpeedModifier {

    private static final int ICON_U = 36;
    private static final int ICON_V = 244;

    private final double bonusPercent;
    private final String displayValue;

    /**
     * @param bonusPercent The percentage bonus as a fraction (e.g., 0.15 for +15%).
     * @param displayValue The visual text shown in the GUI (e.g., "+15%").
     */
    public MovementSpeedTrait(double bonusPercent, String displayValue) {
        this.bonusPercent = bonusPercent;
        this.displayValue = displayValue;
    }

    @Override
    public double getMovementSpeedBonus() {
        return this.bonusPercent;
    }

    @Override
    public int getIconU() {
        return ICON_U;
    }

    @Override
    public int getIconV() {
        return ICON_V;
    }

    @Override
    public String getDisplayValue() {
        return this.displayValue;
    }

    @Override
    public Component getTooltip() {
        return Component.translatable("trait.finesse_races.movement_speed.tooltip", this.displayValue);
    }
}