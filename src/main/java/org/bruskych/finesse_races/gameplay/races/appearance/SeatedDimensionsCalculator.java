package org.bruskych.finesse_races.gameplay.races.appearance;

import net.minecraft.util.Mth;
import net.minecraft.world.entity.EntityDimensions;
import net.minecraft.world.entity.player.Player;

/**
 * Computes hitbox height and eye height for seated players (boats, minecarts, mounts, modded seats).
 * Anchors the player at the hips rather than the feet, ignoring leg length while adapting to torso,
 * head, and thigh thickness deltas consistently on both client and server.
 */
public final class SeatedDimensionsCalculator {

    private static final float VANILLA_STANDING_HEIGHT = 1.8F;
    private static final float VANILLA_STANDING_EYE_HEIGHT = 1.62F;

    // Player model scale (0.9375) divided by 16 pixels per block.
    private static final float MODEL_PIXEL_TO_BLOCKS = 0.9375F / 16.0F;

    private static final float MIN_SEATED_HEIGHT = 0.4F;
    private static final float MAX_SEATED_HEIGHT = 4.0F;
    private static final float MIN_SEATED_EYE_HEIGHT = 0.2F;

    private SeatedDimensionsCalculator() {
    }

    /**
     * Returns {@code true} if the player is riding a vehicle or seat in a sitting posture.
     */
    public static boolean isSeated(Player player) {
        return player.isPassenger()
                && player.getVehicle() != null
                && player.getVehicle().shouldRiderSit();
    }

    /**
     * Computes seated hitbox dimensions, keeping {@code width} and adjusting height
     * solely by torso, head, and thigh thickness deltas.
     */
    public static EntityDimensions computeSeatedDimensions(PlayerAppearanceProfile profile, float width) {
        float bodyDeltaPx = profile.getPart(PlayerBodyPart.BODY).getLengthDelta();
        float headDeltaPx = profile.getPart(PlayerBodyPart.HEAD).getLengthDelta();
        float thighOffsetPx = getThighDepthOffsetPixels(profile);

        float totalDeltaPx = bodyDeltaPx + headDeltaPx + thighOffsetPx;
        if (totalDeltaPx == 0.0F) {
            return EntityDimensions.scalable(width, VANILLA_STANDING_HEIGHT);
        }

        float seatedHeight = Mth.clamp(
                VANILLA_STANDING_HEIGHT + totalDeltaPx * MODEL_PIXEL_TO_BLOCKS,
                MIN_SEATED_HEIGHT,
                MAX_SEATED_HEIGHT
        );
        return EntityDimensions.scalable(width, seatedHeight);
    }

    /**
     * Computes seated eye height, shifting 1:1 with torso/thigh deltas and 0.5x with head height
     * (eyes sit near the vertical center of the head).
     */
    public static float computeSeatedEyeHeight(
            PlayerAppearanceProfile profile,
            EntityDimensions seatedDimensions
    ) {
        float bodyDeltaPx = profile.getPart(PlayerBodyPart.BODY).getLengthDelta();
        float headDeltaPx = profile.getPart(PlayerBodyPart.HEAD).getLengthDelta();
        float thighOffsetPx = getThighDepthOffsetPixels(profile);

        float eyeDeltaPx = bodyDeltaPx + (headDeltaPx * 0.5F) + thighOffsetPx;
        if (eyeDeltaPx == 0.0F) {
            return VANILLA_STANDING_EYE_HEIGHT;
        }

        float maxEyeHeight = Math.max(MIN_SEATED_EYE_HEIGHT, seatedDimensions.height - 0.1F);
        return Mth.clamp(
                VANILLA_STANDING_EYE_HEIGHT + eyeDeltaPx * MODEL_PIXEL_TO_BLOCKS,
                MIN_SEATED_EYE_HEIGHT,
                maxEyeHeight
        );
    }

    /**
     * Returns the vertical offset (in model pixels) compensating for bent thigh thickness while seated.
     */
    public static float getThighDepthOffsetPixels(PlayerAppearanceProfile profile) {
        float maxLegDepthDelta = Math.max(
                profile.getPart(PlayerBodyPart.LEFT_LEG).getDepthDelta(),
                profile.getPart(PlayerBodyPart.RIGHT_LEG).getDepthDelta()
        );
        return maxLegDepthDelta * 0.5F;
    }
}