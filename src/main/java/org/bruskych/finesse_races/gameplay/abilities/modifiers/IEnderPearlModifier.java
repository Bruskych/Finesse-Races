package org.bruskych.finesse_races.gameplay.abilities.modifiers;

public interface IEnderPearlModifier {
    /**
     * Gets the multiplier for the throw velocity.
     *
     * @return The velocity multiplier (e.g., 1.5F means throwing 50% faster and further).
     */
    float getPearlThrowVelocityMultiplier();

    /**
     * Checks if the player should be immune to ender-pearl teleportation damage.
     *
     * @return {@code true} if the player takes no damage from teleporting, {@code false} otherwise.
     */
    boolean preventsPearlDamage();

    /**
     * Checks if the pearl should evaporate (be destroyed) upon touching any fluid (water, lava, modded liquids).
     *
     * @return {@code true} if the pearl evaporates in liquids, {@code false} otherwise.
     */
    boolean evaporatesInFluids();
}
