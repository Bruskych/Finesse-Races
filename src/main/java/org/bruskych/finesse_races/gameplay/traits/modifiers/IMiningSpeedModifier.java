package org.bruskych.finesse_races.gameplay.traits.modifiers;

/**
 * Micro-interface for features that modify the player's block breaking speed.
 * Extracted for O(1) caching and fast access during race initialization.
 */
public interface IMiningSpeedModifier {

    /**
     * Defines the multiplier applied to the final mining speed.
     * Example: 0.3F means +30% faster mining. -0.2F means -20% slower mining.
     *
     * @return The float multiplier to apply to the mining speed.
     */
    float getMiningSpeedMultiplier();
}