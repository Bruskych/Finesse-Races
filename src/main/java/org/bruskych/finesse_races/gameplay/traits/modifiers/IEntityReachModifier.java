package org.bruskych.finesse_races.gameplay.traits.modifiers;

import java.util.UUID;

/**
 * Micro-interface for features that modify the player's entity interaction/attack distance.
 */
public interface IEntityReachModifier {

    /**
     * A constant UUID to ensure the modifier is consistently applied and removed.
     */
    UUID ENTITY_REACH_MODIFIER_ID = UUID.fromString("c3b4d5e6-f7a8-49b0-c1d2-e3f4a5b6c7d8");

    /**
     * Gets the bonus distance for attacking and interacting with entities.
     *
     * @return Bonus in blocks (e.g., 1.5D means +1.5 blocks to the default reach).
     */
    double getEntityReachBonus();
}