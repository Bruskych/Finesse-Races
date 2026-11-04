package org.bruskych.finesse_races.gameplay.abilities.core;

import net.minecraft.world.entity.ai.attributes.AttributeModifier;
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

import static net.minecraftforge.common.ForgeMod.BLOCK_REACH;
import static net.minecraftforge.common.ForgeMod.ENTITY_REACH;

import java.util.List;
import java.util.UUID;

/**
 * Automatically registers this class to the Forge Event Bus.
 * Central event dispatcher for all race-related abilities and mechanics on the server.
 */
@Mod.EventBusSubscriber(modid = FinesseRaces.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class AbilitiesServerEvents {

    // Unique UUIDs for attributes
    private static final UUID BLOCK_REACH_UUID = UUID.fromString("1e5d7a8b-3c4f-4a6b-8b9c-2d1e3f4a5b6c");
    private static final UUID ENTITY_REACH_UUID = UUID.fromString("7a8b9c0d-1e2f-3a4b-5c6d-7e8f9a0b1c2d");

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

    /**
     * Calculates the total reach bonus from all abilities and applies it to the player.
     * Called when a player joins the world, respawns, or changes race.
     *
     * @param player The player whose reach attributes need to be updated.
     */
    public static void syncReachAttributes(Player player) {
        AbstractRace race = RaceManager.getRaceOfPlayer(player);

        var blockReachAttr = player.getAttribute(BLOCK_REACH.get());
        var entityReachAttr = player.getAttribute(ENTITY_REACH.get());

        if (blockReachAttr == null || entityReachAttr == null) return;

        // Cleanup old modifiers
        blockReachAttr.removeModifier(BLOCK_REACH_UUID);
        entityReachAttr.removeModifier(ENTITY_REACH_UUID);

        if (race == null) return;

        double totalBlockBonus = 0.0D;
        double totalEntityBonus = 0.0D;

        for (IExtendedReachModifier modifier : race.getExtendedReachModifiers()) {
            totalBlockBonus += modifier.getBlockReachBonus();
            totalEntityBonus += modifier.getEntityReachBonus();
        }
        if (totalBlockBonus > 0) {
            blockReachAttr.addPermanentModifier(new AttributeModifier(
                    BLOCK_REACH_UUID,
                    "Finesse Races Block Reach Bonus",
                    totalBlockBonus,
                    AttributeModifier.Operation.ADDITION)
            );
        }
        if (totalEntityBonus > 0) {
            entityReachAttr.addPermanentModifier(new AttributeModifier(
                    ENTITY_REACH_UUID,
                    "Finesse Races Entity Reach Bonus",
                    totalEntityBonus,
                    AttributeModifier.Operation.ADDITION)
            );
        }
    }
}