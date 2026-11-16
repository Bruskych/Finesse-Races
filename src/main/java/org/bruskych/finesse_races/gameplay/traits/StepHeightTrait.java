package org.bruskych.finesse_races.gameplay.traits;

import net.minecraft.network.chat.Component;

import org.bruskych.finesse_races.gameplay.traits.modifiers.IStepHeightModifier;
import org.bruskych.finesse_races.gameplay.traits.core.RaceTrait;

/**
 * A basic trait that modifies the player's step height.
 */
public class StepHeightTrait implements RaceTrait, IStepHeightModifier {

    private static final int ICON_U = 0;
    private static final int ICON_V = 244;

    private final double bonus;
    private final String displayValue;

    /**
     * @param bonus        The mechanical value added to the step height.
     * @param displayValue The visual text shown in the GUI (e.g., "+0.5").
     */
    public StepHeightTrait(double bonus, String displayValue) {
        this.bonus = bonus;
        this.displayValue = displayValue;
    }

    @Override
    public double getStepHeightAddition() {
        return this.bonus;
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
        return Component.translatable("trait.finesse_races.step_height.tooltip", this.displayValue);
    }
}