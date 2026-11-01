package org.bruskych.finesse_races.gameplay.abilities.modifiers;

import net.minecraft.world.entity.player.Player;
import net.minecraftforge.event.entity.living.EnderManAngerEvent;

public interface IEnderAngerModifier {
    void onEnderManAnger(Player player, EnderManAngerEvent event);
}