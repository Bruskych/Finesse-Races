package org.bruskych.finesse_races.gameplay.abilities.core;

import net.minecraft.tags.DamageTypeTags;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.ThrownPotion;
import net.minecraftforge.event.entity.ProjectileImpactEvent;
import net.minecraftforge.event.entity.living.LivingEntityUseItemEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import org.bruskych.finesse_races.core.FinesseRaces;
import org.bruskych.finesse_races.gameplay.abilities.HigherHeatDamageAbility;
import org.bruskych.finesse_races.gameplay.races.core.AbstractRace;
import org.bruskych.finesse_races.gameplay.races.core.RaceManager;

import java.util.List;

/**
 * Automatically registers this class to the Forge Event Bus.
 * Central event handler for all race-related abilities and mechanics.
 */
@Mod.EventBusSubscriber(modid = FinesseRaces.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class RaceAbilitiesEvents {

    /**
     * Listens to incoming damage for all living entities and modifies it based on race abilities.
     * Currently handles elemental weaknesses (e.g., increased fire damage).
     *
     * @param event The event triggered right before an entity takes damage.
     */
    @SubscribeEvent
    public static void onPlayerHurt(LivingHurtEvent event) {
        // Ensure the entity receiving damage is a Player
        if (event.getEntity() instanceof Player player) {
            AbstractRace race = RaceManager.getRaceOfPlayer(player);

            if (race != null) {
                for (RaceAbility ability : race.getAbilities()) {

                    // Check for specific fire damage multiplier ability
                    if (ability instanceof HigherHeatDamageAbility heatAbility) {
                        if (event.getSource().is(DamageTypeTags.IS_FIRE)) {
                            float originalDamage = event.getAmount();
                            float multiplier = heatAbility.getDamageMultiplier();
                            event.setAmount(originalDamage * multiplier);
                        }
                        // Break out of the loop assuming only one heat modifier per race
                        break;
                    }
                }
            }
        }
    }

    /**
     * Triggered when an entity finishes using an item (e.g., eating food, drinking a potion).
     * Delegates the event to the player's active race abilities for custom handling.
     *
     * @param event The event containing the entity, the item, and usage data.
     */
    @SubscribeEvent
    public static void onPlayerUseItemFinish(LivingEntityUseItemEvent.Finish event) {
        // Ensure the entity using the item is a Player
        if (event.getEntity() instanceof Player player) {
            AbstractRace race = RaceManager.getRaceOfPlayer(player);

            if (race != null) {
                // Delegate the event to all abilities (e.g., checking for water consumption)
                for (RaceAbility ability : race.getAbilities()) {
                    ability.onItemUseFinish(player, event.getItem(), event.getItem().getUseAnimation());
                }
            }
        }
    }

    /**
     * Triggered when a projectile impacts a block or an entity.
     * Captures Splash Potion explosions to apply race-specific area-of-effect abilities.
     *
     * @param event The event containing the projectile and its impact point.
     */
    @SubscribeEvent
    public static void onPotionSplash(ProjectileImpactEvent event) {
        // Verify that the impacting projectile is a thrown potion
        if (event.getProjectile() instanceof ThrownPotion potion) {

            // Damage and ability logic should only be executed on the logical server side
            if (!potion.level().isClientSide()) {

                // Vanilla splash potion radius is 4 blocks horizontally and 2 blocks vertically.
                var searchBox = potion.getBoundingBox().inflate(4.0D, 2.0D, 4.0D);
                List<Player> nearbyPlayers = potion.level().getEntitiesOfClass(Player.class, searchBox);

                for (Player player : nearbyPlayers) {
                    // Perform an exact spherical distance check (4 blocks = 16.0 squared distance)
                    if (player.distanceToSqr(potion) <= 16.0D) {

                        AbstractRace race = RaceManager.getRaceOfPlayer(player);
                        if (race != null) {
                            // Delegate the splash event to the player's abilities
                            for (RaceAbility ability : race.getAbilities()) {
                                ability.onSplashPotionHit(player, potion);
                            }
                        }
                    }
                }
            }
        }
    }
}