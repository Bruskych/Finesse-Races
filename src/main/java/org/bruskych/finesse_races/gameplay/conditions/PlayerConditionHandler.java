package org.bruskych.finesse_races.gameplay.conditions;

import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.block.Blocks;

/**
 * Player condition handler.
 * Stores utility wrappers for Minecraft checks.
 */
public class PlayerConditionHandler {

    // 1. Basic water touch
    public static boolean isWater(Player player) {
        return player.isInWater();
    }

    // 2. Fully submerged underwater
    public static boolean isUnderWater(Player player) {
        return player.isUnderWater();
    }

    // 3. Strictly under rain (optimized order)
    public static boolean isRain(Player player) {
        return !player.isInWater() && player.isInWaterOrRain();
    }

    // 4. Bubble columns (soul sand or magma blocks)
    public static boolean isBubbleColumn(Player player) {
        if (!player.isInWater()) {
            return false;
        }
        return player.level().getBlockState(player.blockPosition()).is(Blocks.BUBBLE_COLUMN);
    }

    // The player is drowning underwater
    public static boolean isDrown(Player player) {
        return player.getAirSupply() <= 0;
    }
}