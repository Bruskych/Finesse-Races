package org.bruskych.finesse_races.gameplay.abilities.core;

import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.ThrownPotion;
import net.minecraftforge.event.entity.ProjectileImpactEvent;
import net.minecraftforge.event.entity.living.EnderManAngerEvent;
import net.minecraftforge.event.entity.living.LivingAttackEvent;
import net.minecraftforge.event.entity.living.LivingEntityUseItemEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import org.bruskych.finesse_races.core.FinesseRaces;
import org.bruskych.finesse_races.gameplay.abilities.modifiers.*;
import org.bruskych.finesse_races.gameplay.races.core.AbstractRace;
import org.bruskych.finesse_races.gameplay.races.core.RaceManager;

import java.util.List;

/**
 * Automatically registers this class to the Forge Event Bus.
 * Central event dispatcher for all race-related abilities and mechanics on the server.
 */
@Mod.EventBusSubscriber(modid = FinesseRaces.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class AbilitiesServerEvents {

    /**
     * Intercepts incoming damage for players.
     * Delegates the event to abilities that implement the IHurtModifier interface.
     *
     * @param event The Forge event containing damage source and amount data.
     */
    @SubscribeEvent
    public static void onPlayerHurt(LivingHurtEvent event) {
        if (event.getEntity() instanceof Player player) {
            AbstractRace race = RaceManager.getRaceOfPlayer(player);
            if (race != null) {
                List<IHurtModifier> modifiers = race.getHurtModifiers();
                if (!modifiers.isEmpty()) {
                    for (IHurtModifier modifier : modifiers) {
                        modifier.onHurt(player, event);
                        if (event.isCanceled()) break;
                    }
                }
            }
        }
    }

    /**
     * Intercepts attacks before hurt animations or sounds are played.
     * Used for absolute immunities (e.g., magma blocks, fall damage).
     */
    @SubscribeEvent
    public static void onPlayerAttack(LivingAttackEvent event) {
        if (event.getEntity() instanceof Player player) {
            AbstractRace race = RaceManager.getRaceOfPlayer(player);
            if (race != null) {
                List<IAttackModifier> modifiers = race.getAttackModifiers();
                if (!modifiers.isEmpty()) {
                    for (IAttackModifier modifier : modifiers) {
                        modifier.onAttack(player, event);
                        if (event.isCanceled()) break;
                    }
                }
            }
        }
    }

    /**
     * Triggered when a player finishes consuming or using an item (e.g., eating, drinking).
     * Delegates the event to abilities that implement the IItemUseModifier interface.
     *
     * @param event The Forge event containing the consumed item and animation data.
     */
    @SubscribeEvent
    public static void onPlayerUseItemFinish(LivingEntityUseItemEvent.Finish event) {
        if (event.getEntity() instanceof Player player) {
            AbstractRace race = RaceManager.getRaceOfPlayer(player);
            if (race != null) {
                List<IItemUseModifier> modifiers = race.getItemUseModifiers();
                if (!modifiers.isEmpty()) {
                    for (IItemUseModifier modifier : modifiers) {
                        modifier.onItemUseFinish(player, event.getItem(), event.getItem().getUseAnimation());
                    }
                }
            }
        }
    }

    /**
     * Detects splash potion impacts and calculates the area of effect (AoE).
     * Delegates the event to nearby players whose abilities implement IPotionSplashModifier.
     *
     * @param event The Forge event containing projectile impact data.
     */
    @SubscribeEvent
    public static void onPotionSplash(ProjectileImpactEvent event) {
        if (event.getProjectile() instanceof ThrownPotion potion && !potion.level().isClientSide()) {

            // Define a bounding box for the splash potion's area of effect
            var searchBox = potion.getBoundingBox().inflate(4.0D, 2.0D, 4.0D);
            List<Player> nearbyPlayers = potion.level().getEntitiesOfClass(Player.class, searchBox);

            for (Player player : nearbyPlayers) {

                // Confirm the player is within the exact spherical radius of the splash
                if (player.distanceToSqr(potion) <= 16.0D) {
                    AbstractRace race = RaceManager.getRaceOfPlayer(player);
                    if (race != null) {
                        List<IPotionSplashModifier> modifiers = race.getPotionSplashModifiers();
                        if (!modifiers.isEmpty()) {
                            for (IPotionSplashModifier modifier : modifiers) {
                                modifier.onSplashPotionHit(player, potion);
                            }
                        }
                    }
                }
            }
        }
    }

    /**
     * Intercepts the trigger that causes Endermen to become hostile when looked at.
     * Delegates the event to abilities that implement the IEnderAngerModifier interface.
     *
     * @param event The Forge event fired when an Enderman targets a player.
     */
    @SubscribeEvent
    public static void onEnderManAnger(EnderManAngerEvent event) {
        Player player = event.getPlayer();
        if (player != null) {
            AbstractRace race = RaceManager.getRaceOfPlayer(player);
            if (race != null) {
                List<IEnderAngerModifier> modifiers = race.getEnderAngerModifiers();
                if (!modifiers.isEmpty()) {
                    for (IEnderAngerModifier modifier : modifiers) {
                        modifier.onEnderManAnger(player, event);
                        if (event.isCanceled()) break;
                    }
                }
            }
        }
    }
}