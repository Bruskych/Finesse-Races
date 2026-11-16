package org.bruskych.finesse_races.gameplay.traits.modifiers;

import java.util.UUID;

/**
 * Micro-interface for features that modify the player's block interaction distance (placing/breaking).
 */
public interface IBlockReachModifier {

    /**
     * A constant UUID to ensure the modifier is consistently applied and removed.
     */
    UUID BLOCK_REACH_MODIFIER_ID = UUID.fromString("b2a3c4d5-e6f7-48a9-b0c1-d2e3f4a5b6c7");

    /**
     * Gets the bonus distance for interacting with blocks.
     *
     * @return Bonus in blocks (e.g., 1.5D means +1.5 blocks to the default reach).
     */
    double getBlockReachBonus();
}