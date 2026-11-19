package org.bruskych.finesse_races.gameplay.traits.modifiers;

import org.bruskych.finesse_races.gameplay.races.core.PlayerDimensionsProfile;

/**
 * Marker interface for race features that override player hitbox dimensions and eye height.
 */
public interface IEntitySizeModifier {

    /**
     * Returns the precomputed dimensions profile for the race.
     */
    PlayerDimensionsProfile getDimensionsProfile();
}