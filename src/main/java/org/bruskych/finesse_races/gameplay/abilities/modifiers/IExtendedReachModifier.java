package org.bruskych.finesse_races.gameplay.abilities.modifiers;

public interface IExtendedReachModifier {
    /**
     * Gets the bonus distance for interacting with blocks (placing, breaking, using).
     *
     * @return Bonus in blocks (e.g., 1.5D means +1.5 blocks to the default reach).
     */
    double getBlockReachBonus();

    /**
     * Gets the bonus distance for attacking and interacting with entities.
     *
     * @return Bonus in blocks (e.g., 1.5D means +1.5 blocks to the default reach).
     */
    double getEntityReachBonus();
}