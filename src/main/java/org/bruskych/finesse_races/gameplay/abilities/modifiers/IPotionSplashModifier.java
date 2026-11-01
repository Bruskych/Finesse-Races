package org.bruskych.finesse_races.gameplay.abilities.modifiers;

import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.ThrownPotion;

public interface IPotionSplashModifier {
    void onSplashPotionHit(Player player, ThrownPotion potion);
}