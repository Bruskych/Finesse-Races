package org.bruskych.finesse_races.gameplay.abilities.modifiers;

import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.UseAnim;

public interface IItemUseModifier {
    void onItemUseFinish(Player player, ItemStack item, UseAnim anim);
}