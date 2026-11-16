package org.bruskych.finesse_races.gameplay.traits.modifiers;

import java.util.UUID;

/**
 * Micro-interface for features that passively modify the player's attack speed (cooldown).
 * Extracted for O(1) caching and fast access during race initialization.
 */
public interface IAttackSpeedModifier {

    /**
     * A constant UUID to ensure the modifier is consistently applied and removed.
     */
    UUID ATTACK_SPEED_MODIFIER_ID = UUID.fromString("b3c4d5e6-f7a8-1b2c-3d4e-5f6a7b8c9d0e");

    /**
     * Defines the multiplier applied to the base attack speed.
     * For example, returning 0.1D increases the attack speed by 10% (faster weapon cooldown).
     *
     * @return The double multiplier to apply to the base attack speed.
     */
    double getAttackSpeedMultiplier();
}