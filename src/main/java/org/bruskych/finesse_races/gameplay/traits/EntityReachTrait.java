package org.bruskych.finesse_races.gameplay.traits;

import net.minecraft.network.chat.Component;

import org.bruskych.finesse_races.client.gui.FRColors;
import org.bruskych.finesse_races.gameplay.traits.core.RaceTrait;
import org.bruskych.finesse_races.gameplay.traits.modifiers.IEntityReachModifier;

/**
 * A basic trait that modifies the player's attack and interaction range with entities.
 */
public class EntityReachTrait implements RaceTrait, IEntityReachModifier {

    private static final int ICON_U = 24;
    private static final int ICON_V = 244;

    private final double bonus;
    private final String displayValue;

    /**
     * @param bonus        The mechanical value added to the entity reach.
     * @param displayValue The visual text shown in the GUI (e.g., "+1.0").
     */
    public EntityReachTrait(double bonus, String displayValue) {
        this.bonus = bonus;
        this.displayValue = displayValue;
    }

    @Override
    public double getEntityReachBonus() {
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
        int color = FRColors.getTraitColor(this.getTraitValue().doubleValue());
        Component coloredValue = Component
                .literal(this.displayValue)
                .withStyle(style -> style.withColor(color));

        return Component.translatable("trait.finesse_races.entity_reach.tooltip", coloredValue);
    }

    @Override
    public Number getTraitValue() {
        return this.bonus;
    }
}