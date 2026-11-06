package org.bruskych.finesse_races.gameplay.abilities.core;

import net.minecraft.core.particles.ItemParticleOption;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.ThrownPotion;
import net.minecraft.world.entity.projectile.ThrownEnderpearl;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.EntityJoinLevelEvent;
import net.minecraftforge.event.entity.EntityTeleportEvent;
import net.minecraftforge.event.entity.ProjectileImpactEvent;
import net.minecraftforge.event.entity.living.*;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import org.bruskych.finesse_races.core.FinesseRaces;
import org.bruskych.finesse_races.gameplay.abilities.modifiers.*;
import org.bruskych.finesse_races.gameplay.races.core.AbstractRace;
import org.bruskych.finesse_races.gameplay.races.core.RaceManager;

import static net.minecraftforge.common.ForgeMod.BLOCK_REACH;
import static net.minecraftforge.common.ForgeMod.ENTITY_REACH;

import java.util.*;

/**
 * Automatically registers this class to the Forge Event Bus.
 * Central event dispatcher for all race-related abilities and mechanics on the server.
 */
@Mod.EventBusSubscriber(modid = FinesseRaces.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class AbilitiesServerEvents {

    // Unique UUIDs for attributes
    private static final UUID BLOCK_REACH_UUID = UUID.fromString("1e5d7a8b-3c4f-4a6b-8b9c-2d1e3f4a5b6c");
    private static final UUID ENTITY_REACH_UUID = UUID.fromString("7a8b9c0d-1e2f-3a4b-5c6d-7e8f9a0b1c2d");

    // If a pearl is destroyed by the world, it gets garbage collected automatically
    private static final Set<ThrownEnderpearl> EVAPORATING_PEARLS = Collections.newSetFromMap(new WeakHashMap<>());

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

    /**
     * Intercepts entities joining the world.
     * Used here to increase the velocity of Ender Pearls thrown by specific races.
     *
     * @param event The Forge event fired when an entity is added to the level.
     */
    @SubscribeEvent
    public static void onEntityJoinLevel(EntityJoinLevelEvent event) {
        // Verify that the spawned entity is an Ender Pearl
        if (event.getEntity() instanceof ThrownEnderpearl pearl) {

            // Check if the owner (thrower) of the pearl is a player
            if (pearl.getOwner() instanceof Player player) {
                AbstractRace race = RaceManager.getRaceOfPlayer(player);

                if (race != null) {
                    List<IEnderPearlModifier> modifiers = race.getEnderPearlModifiers();
                    if (!modifiers.isEmpty()) {
                        double maxMultiplier = 1.0D;
                        boolean shouldEvaporate = false;

                        // Find the highest velocity bonus if multiple abilities apply
                        for (IEnderPearlModifier modifier : modifiers) {
                            if (modifier.getPearlThrowVelocityMultiplier() > maxMultiplier) {
                                maxMultiplier = modifier.getPearlThrowVelocityMultiplier();
                            }
                            if (modifier.evaporatesInFluids()) {
                                shouldEvaporate = true;
                            }
                        }
                        // Apply the bonus velocity to the pearl's movement vector
                        if (maxMultiplier > 1.0D) {
                            var currentDelta = pearl.getDeltaMovement();
                            pearl.setDeltaMovement(
                                    currentDelta.multiply(maxMultiplier, maxMultiplier, maxMultiplier)
                            );
                        }
                        // If a pearl is supposed to evaporate and we are on the server, we add it to the tracker
                        if (shouldEvaporate && !pearl.level().isClientSide()) {
                            EVAPORATING_PEARLS.add(pearl);
                        }
                    }
                }
            }
        }
    }

    /**
     * Intercepts Ender Pearl teleportation before the damage is applied.
     * Used here to cancel the teleportation damage for specific races.
     *
     * @param event The Forge event fired right before an Ender Pearl teleports an entity.
     */
    @SubscribeEvent
    public static void onEnderPearlTeleport(EntityTeleportEvent.EnderPearl event) {
        // Ensure the teleporting entity is a player
        if (event.getEntity() instanceof Player player) {
            AbstractRace race = RaceManager.getRaceOfPlayer(player);

            if (race != null) {
                List<IEnderPearlModifier> modifiers = race.getEnderPearlModifiers();
                if (!modifiers.isEmpty()) {
                    for (IEnderPearlModifier modifier : modifiers) {
                        if (modifier.preventsPearlDamage()) {

                            // Set the teleportation damage to 0 (vanilla = 5.0F)
                            event.setAttackDamage(0.0F);
                            return;
                        }
                    }
                }
            }
        }
    }

    /**
     * Ticks tracked Ender Pearls to check if they hit a fluid (water, lava, modded liquids).
     * If they do, they are destroyed with an Eye of Ender breaking effect.
     */
    @SubscribeEvent
    public static void onServerTick(TickEvent.ServerTickEvent event) {
        if (event.phase != TickEvent.Phase.END) return; // Check once per tick
        if (EVAPORATING_PEARLS.isEmpty()) return; // Fast fail

        var iterator = EVAPORATING_PEARLS.iterator();
        while (iterator.hasNext()) {
            var pearl = iterator.next();

            // If the pearl has already been removed by the game, forget about it
            if (pearl.isRemoved()) {
                iterator.remove();
                continue;
            }

            // Check collision with ANY fluid
            if (
                    pearl.isInWater() ||
                    pearl.isInLava() ||
                    !pearl.level().getFluidState(pearl.blockPosition()).isEmpty()
            ) {

                if (pearl.level() instanceof net.minecraft.server.level.ServerLevel serverLevel) {

                    // SOUND
                    serverLevel.playSound(
                            null,
                            pearl.blockPosition(),
                            SoundEvents.ENDER_EYE_DEATH,
                            SoundSource.NEUTRAL,
                            1.0F,
                            1.0F
                    );

                    // PARTICLES
                    serverLevel.sendParticles(
                            new ItemParticleOption(ParticleTypes.ITEM, new ItemStack(Items.ENDER_PEARL)),
                            pearl.getX(), pearl.getY() + 0.5D, pearl.getZ(),
                            15,
                            0.25D, 0.25D, 0.25D,
                            0.05D
                    );
                }

                // Delete pearl
                pearl.discard();

                // Remove from our tracker
                iterator.remove();
            }
        }
    }
}