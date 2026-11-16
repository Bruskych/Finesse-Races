package org.bruskych.finesse_races.gameplay.traits.modifiers;

import java.util.UUID;

/**
 * Micro-interface for features (Abilities or Traits) that modify the player's swim speed.
 * Extracted for O(1) caching and fast access during race initialization.
 */
public interface ISwimSpeedModifier {

    /**
     * A constant UUID to ensure the modifier is consistently applied and removed.
     */
    UUID SWIM_SPEED_MODIFIER_ID = UUID.fromString("6a34c1b9-3d88-4f76-9e90-1c9f7a9d0123");

    /**
     * Defines the multiplier applied to the base swim speed.
     * For example, returning 0.5D increases the player's swim speed by 50%.
     *
     * @return The double multiplier to apply to the base swim speed.
     */
    double getSwimSpeedMultiplier();
}