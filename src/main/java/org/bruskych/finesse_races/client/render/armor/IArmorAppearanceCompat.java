package org.bruskych.finesse_races.client.render.armor;

import net.minecraft.client.model.Model;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;

import org.bruskych.finesse_races.gameplay.races.appearance.PlayerAppearanceProfile;

/**
 * Extension hook for adapting non-standard modded armor models (e.g., GeckoLib)
 * to custom race limb proportions.
 */
public interface IArmorAppearanceCompat {

    /**
     * Returns true if this adapter handles the given armor model instance.
     */
    boolean supports(Model model);

    /**
     * Applies race proportions to the custom armor model before rendering.
     */
    void apply(Player player, Model model, PlayerAppearanceProfile profile, EquipmentSlot slot);

    /**
     * Restores the custom armor model back to its default state after rendering.
     */
    void reset(Model model, EquipmentSlot slot);
}