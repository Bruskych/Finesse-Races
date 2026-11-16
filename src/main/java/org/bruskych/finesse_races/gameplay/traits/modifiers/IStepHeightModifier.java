package org.bruskych.finesse_races.gameplay.traits.modifiers;

import java.util.UUID;

/**
 * Micro-interface for features (Abilities or Traits) that modify the player's step height.
 * Extracted for O(1) caching and fast access during race initialization.
 */
public interface IStepHeightModifier {

    /**
     * A constant UUID to ensure the modifier is consistently applied and removed.
     */
    UUID STEP_HEIGHT_MODIFIER_ID = UUID.fromString("a1b2c3d4-e5f6-4a7b-8c9d-0e1f2a3b4c5d");

    /**
     * Defines the bonus or penalty to the default step height (0.6F).
     * For example, returning 0.5D allows the player to step up full blocks (0.6 + 0.5 = 1.1).
     *
     * @return The double value to add to the base step height.
     */
    double getStepHeightAddition();
}