package org.bruskych.finesse_races.gameplay.abilities.modifiers;

import net.minecraft.world.entity.player.Player;
import net.minecraftforge.event.entity.living.LivingHurtEvent;

public interface IHurtModifier {
    void onHurt(Player player, LivingHurtEvent event);
}