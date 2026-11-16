package org.bruskych.finesse_races.gameplay.traits;

import net.minecraft.network.chat.Component;
import org.bruskych.finesse_races.gameplay.traits.modifiers.IAttackSpeedModifier;
import org.bruskych.finesse_races.gameplay.traits.core.RaceTrait;

/**
 * A basic trait that increases or decreases the player's attack speed (weapon cooldown recovery).
 */
public class AttackSpeedTrait implements RaceTrait, IAttackSpeedModifier {

    private static final int ICON_U = 84;
    private static final int ICON_V = 244;

    private final double speedMultiplier;
    private final String displayValue;

    /**
     * @param speedMultiplier The mechanical multiplier applied to the attack speed (e.g., 0.1D for +10%).
     * @param displayValue    The visual text shown in the GUI (e.g., "+10%").
     */
    public AttackSpeedTrait(double speedMultiplier, String displayValue) {
        this.speedMultiplier = speedMultiplier;
        this.displayValue = displayValue;
    }

    @Override
    public double getAttackSpeedMultiplier() {
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
        return Component.translatable("trait.finesse_races.attack_speed.tooltip", this.displayValue);
    }
}