package org.bruskych.finesse_races.gameplay.traits;

import net.minecraft.network.chat.Component;

import org.bruskych.finesse_races.gameplay.traits.modifiers.ISwimSpeedModifier;
import org.bruskych.finesse_races.gameplay.traits.core.RaceTrait; // Если RaceTrait у вас интерфейс

/**
 * A basic trait that modifies the player's swim speed.
 */
public class SwimSpeedTrait implements RaceTrait, ISwimSpeedModifier {

    private static final int ICON_U = 48;
    private static final int ICON_V = 244;

    private final double speedMultiplier;
    private final String displayValue;

    /**
     * @param speedMultiplier The mechanical multiplier applied to the swim speed (e.g., 0.5D for +50%).
     * @param displayValue    The visual text shown in the GUI (e.g., "+50%").
     */
    public SwimSpeedTrait(double speedMultiplier, String displayValue) {
        this.speedMultiplier = speedMultiplier;
        this.displayValue = displayValue;
    }

    @Override
    public double getSwimSpeedMultiplier() {
        return this.speedMultiplier;
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
        return Component.translatable("trait.finesse_races.swim_speed.tooltip", this.displayValue);
    }
}