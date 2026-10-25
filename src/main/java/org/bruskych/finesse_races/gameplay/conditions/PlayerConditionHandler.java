package org.bruskych.finesse_races.gameplay.conditions;

import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.memory.MemoryModuleType;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.block.Blocks;
import org.bruskych.finesse_races.gameplay.races.core.RaceManager;

import java.util.Set;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Player condition handler.
 * Stores utility wrappers for Minecraft checks.
 */
public class PlayerConditionHandler {

    /**
     * Basic water touch
     */
    public static boolean isWater(Player player) {
        return player.isInWater();
    }

    /**
     * Fully submerged underwater
     */
    public static boolean isUnderWater(Player player) {
        return player.isUnderWater();
    }

    /**
     * Strictly under rain (optimized order)
     */
    public static boolean isRain(Player player) {
        return !player.isInWater() && player.isInWaterOrRain();
    }

    /**
     * Bubble columns (soul sand or magma blocks)
     */
    public static boolean isBubbleColumn(Player player) {
        if (!player.isInWater()) {
            return false;
        }
        return player.level().getBlockState(player.blockPosition()).is(Blocks.BUBBLE_COLUMN);
    }

    /**
     * The player is drowning underwater
     */
    public static boolean isDrown(Player player) {
        return player.getAirSupply() <= 0;
    }

    /**
     * A thread-safe set to track players who are currently selecting a race.
     */
    private static final Set<UUID> SELECTING_RACE_PLAYERS = ConcurrentHashMap.newKeySet();


    /**
     * Sets the "selecting race" state for the player.
     */
    public static void setSelectingRace(Player player, boolean selecting) {
        if (selecting) {
            SELECTING_RACE_PLAYERS.add(player.getUUID());

            // Extinguish the player if they were on fire
            player.clearFire();

            // If it's the logical server, remove aggro from nearby mobs
            if (!player.level().isClientSide()) {
                var box = player.getBoundingBox().inflate(64.0D);
                for (Mob mob : player.level().getEntitiesOfClass(Mob.class, box)) {
                    if (mob.getTarget() == player) {
                        mob.setTarget(null);
                        mob.getBrain().eraseMemory(MemoryModuleType.ATTACK_TARGET);
                    }
                }
            }
        } else {
            SELECTING_RACE_PLAYERS.remove(player.getUUID());
        }
    }

    /**
     * Checks if the player is currently in the process of selecting a race.
     */
    public static boolean isSelectingRace(Player player) {
        return SELECTING_RACE_PLAYERS.contains(player.getUUID()) || RaceManager.getRaceOfPlayer(player) == null;
    }
}