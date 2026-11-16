package org.bruskych.finesse_races.gameplay.traits;

import net.minecraft.network.chat.Component;
import org.bruskych.finesse_races.client.gui.FRColors;
import org.bruskych.finesse_races.gameplay.traits.modifiers.IMiningSpeedModifier;
import org.bruskych.finesse_races.gameplay.traits.core.RaceTrait;

/**
 * A trait that increases or decreases the speed at which the player breaks blocks.
 */
public class MiningSpeedTrait implements RaceTrait, IMiningSpeedModifier {

    private static final int ICON_U = 72;
    private static final int ICON_V = 244;

    private final float speedMultiplier;
    private final String displayValue;

    /**
     * @param speedMultiplier The multiplier for mining speed (e.g., 0.3F for +30%).
     * @param displayValue    The visual text shown in the GUI (e.g., "+30%").
     */
    public MiningSpeedTrait(float speedMultiplier, String displayValue) {
        this.speedMultiplier = speedMultiplier;
        this.displayValue = displayValue;
    }

    @Override
    public float getMiningSpeedMultiplier() {
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
        int color = FRColors.getTraitColor(this.getTraitValue().doubleValue());
        Component coloredValue = Component
                .literal(this.displayValue)
                .withStyle(style -> style.withColor(color));

        return Component.translatable("trait.finesse_races.mining_speed.tooltip", coloredValue);
    }

    @Override
    public Number getTraitValue() {
        return this.speedMultiplier;
    }
}