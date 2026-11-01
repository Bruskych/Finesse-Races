package org.bruskych.finesse_races.gameplay.abilities.modifiers;

import net.minecraft.world.entity.player.Player;
import net.minecraftforge.event.entity.living.LivingAttackEvent;

public interface IAttackModifier {
    void onAttack(Player player, LivingAttackEvent event);
}