package org.bruskych.finesse_races.gameplay.traits.modifiers;

import java.util.UUID;

/**
 * Micro-interface for features that modify the player's movement speed.
 */
public interface IMovementSpeedModifier {

    /**
     * A constant UUID to ensure the modifier is consistently applied and removed.
     */
    UUID MOVEMENT_SPEED_MODIFIER_ID = UUID.fromString("d4e5f6a7-b8c9-40d1-e2f3-a4b5c6d7e8f9");

    /**
     * Gets the bonus movement speed multiplier.
     *
     * @return Bonus as a fraction (e.g., 0.15D means +15% to base movement speed).
     */
    double getMovementSpeedBonus();
}