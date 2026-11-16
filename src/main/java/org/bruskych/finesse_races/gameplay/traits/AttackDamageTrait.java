package org.bruskych.finesse_races.gameplay.traits;

import net.minecraft.network.chat.Component;
import org.bruskych.finesse_races.gameplay.traits.modifiers.IAttackDamageModifier;
import org.bruskych.finesse_races.gameplay.traits.core.RaceTrait;

/**
 * A basic trait that flatly increases or decreases the player's melee attack damage.
 */
public class AttackDamageTrait implements RaceTrait, IAttackDamageModifier {

    private static final int ICON_U = 60;
    private static final int ICON_V = 244;

    private final double bonus;
    private final String displayValue;

    /**
     * @param bonus        The flat mechanical value added to the attack damage (e.g., 2.0D for +2 damage).
     * @param displayValue The visual text shown in the GUI (e.g., "+2 Damage").
     */
    public AttackDamageTrait(double bonus, String displayValue) {
        this.bonus = bonus;
        this.displayValue = displayValue;
    }

    @Override
    public double getAttackDamageAddition() {
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
        return Component.translatable("trait.finesse_races.attack_damage.tooltip", this.displayValue);
    }
}