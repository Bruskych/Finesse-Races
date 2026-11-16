package org.bruskych.finesse_races.gameplay.traits;

import net.minecraft.network.chat.Component;

import org.bruskych.finesse_races.gameplay.traits.core.RaceTrait;
import org.bruskych.finesse_races.gameplay.traits.modifiers.IBlockReachModifier;

/**
 * A basic trait that modifies the player's block interaction range.
 */
public class BlockReachTrait implements RaceTrait, IBlockReachModifier {

    private static final int ICON_U = 12;
    private static final int ICON_V = 244;

    private final double bonus;
    private final String displayValue;

    /**
     * @param bonus        The mechanical value added to the block reach.
     * @param displayValue The visual text shown in the GUI (e.g., "+1.5").
     */
    public BlockReachTrait(double bonus, String displayValue) {
        this.bonus = bonus;
        this.displayValue = displayValue;
    }

    @Override
    public double getBlockReachBonus() {
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
        return Component.translatable("trait.finesse_races.block_reach.tooltip", this.displayValue);
    }
}