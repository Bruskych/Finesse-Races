package org.bruskych.finesse_races.gameplay.abilities.core;

import net.minecraft.tags.DamageTypeTags;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import org.bruskych.finesse_races.core.FinesseRaces;
import org.bruskych.finesse_races.gameplay.abilities.HigherHeatDamageAbility;
import org.bruskych.finesse_races.gameplay.races.core.AbstractRace;
import org.bruskych.finesse_races.gameplay.races.core.RaceManager;

/**
 * Automatically registers this class to the Forge Event Bus.
 * Central event handler for all race-related abilities and mechanics.
 */
@Mod.EventBusSubscriber(modid = FinesseRaces.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class RaceAbilitiesEvents {

    /**
     * Listens to incoming damage for all living entities and modifies it based on race abilities.
     */
    @SubscribeEvent
    public static void onPlayerHurt(LivingHurtEvent event) {
        if (event.getEntity() instanceof Player player) {
            AbstractRace race = RaceManager.getRaceOfPlayer(player);

            if (race != null) {
                for (RaceAbility ability : race.getAbilities()) {

                    if (ability instanceof HigherHeatDamageAbility heatAbility) {
                        if (event.getSource().is(DamageTypeTags.IS_FIRE)) {
                            float originalDamage = event.getAmount();
                            float multiplier = heatAbility.getDamageMultiplier();
                            event.setAmount(originalDamage * multiplier);
                        }
                        break;
                    }
                }
            }
        }
    }
}