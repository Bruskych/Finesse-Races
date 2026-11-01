package org.bruskych.finesse_races.gameplay.abilities.modifiers;

import net.minecraft.world.entity.player.Player;

public interface ITickModifier {
    void onTick(Player player);
}