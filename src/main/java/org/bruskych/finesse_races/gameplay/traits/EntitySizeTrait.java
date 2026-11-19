package org.bruskych.finesse_races.gameplay.traits;

import net.minecraft.network.chat.Component;

import org.bruskych.finesse_races.client.gui.FRColors;
import org.bruskych.finesse_races.gameplay.races.core.PlayerDimensionsProfile;
import org.bruskych.finesse_races.gameplay.traits.core.RaceTrait;
import org.bruskych.finesse_races.gameplay.traits.modifiers.IEntitySizeModifier;

import java.util.Objects;

/**
 * Race trait that defines custom per-pose hitbox dimensions and camera eye height.
 */
public class EntitySizeTrait implements RaceTrait, IEntitySizeModifier {

    private static final int ICON_U = 96;
    private static final int ICON_V = 244;

    private final PlayerDimensionsProfile dimensionsProfile;
    private final float traitValue;
    private final String displayValue;

    /**
     * @param dimensionsProfile Precomputed hitbox and eye height profile for the race.
     * @param traitValue        Numeric summary value used for trait comparisons or sorting.
     * @param displayValue      Formatted value displayed in the race selection UI (e.g., "+0.5").
     */
    public EntitySizeTrait(PlayerDimensionsProfile dimensionsProfile, float traitValue, String displayValue) {
        this.dimensionsProfile = Objects.requireNonNull(dimensionsProfile, "Dimensions profile cannot be null");
        this.traitValue = traitValue;
        this.displayValue = displayValue;
    }

    @Override
    public PlayerDimensionsProfile getDimensionsProfile() {
        return this.dimensionsProfile;
    }

    @Override
    public Number getTraitValue() {
        return this.traitValue;
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
        Component whiteValue = Component.literal(this.displayValue)
                .withStyle(style -> style.withColor(FRColors.WHITE));

        return Component.translatable("trait.finesse_races.entity_size.tooltip", whiteValue);
    }
}