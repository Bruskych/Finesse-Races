package org.bruskych.finesse_races.gameplay.abilities.core;

import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Player;

/**
 * Ability interface.
 * A unified contract for all mod mechanics.
 * Ensures that any created ability has an apply method.
 */
public interface RaceAbility {
    void apply(Player player);

    // Returns the ability's title for the menu
    Component getTitle();

    // Returns the ability's description for the menu
    Component getDescription();

    // Returns the ability's category
    AbilityCategory getCategory();
}
