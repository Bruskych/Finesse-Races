package org.bruskych.finesse_races.gameplay.traits.modifiers;

import java.util.UUID;

/**
 * Micro-interface for features that passively modify the player's base melee attack damage.
 * Extracted for O(1) caching and fast access during race initialization.
 */
public interface IAttackDamageModifier {

    /**
     * A constant UUID to ensure the modifier is consistently applied and removed.
     */
    UUID ATTACK_DAMAGE_MODIFIER_ID = UUID.fromString("d8f1e2c4-9a3b-4c5d-7e8f-0a1b2c3d4e5f");

    /**
     * Defines the flat bonus or penalty to the base attack damage.
     * For example, returning 2.0D adds 1 full heart of damage to every melee hit.
     *
     * @return The double value to add to the base attack damage.
     */
    double getAttackDamageAddition();
}