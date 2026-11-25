package org.bruskych.finesse_races.gameplay.races.appearance;

/**
 * Immutable per-part dimension deltas (in model pixels) relative to vanilla proportions.
 * Provides shared dimension and scale math for both custom mesh baking and vanilla scaling.
 */
public final class PlayerLimbProfile {

    // Minimum allowed size (in pixels) along any axis to prevent degenerate geometry.
    public static final float MIN_DIMENSION_PIXELS = 1.0F;
    // Maximum absolute delta (in pixels) accepted for any limb dimension or offset.
    public static final float MAX_ABS_DELTA_PIXELS = 48.0F;

    // Shared unmodified vanilla limb profile.
    public static final PlayerLimbProfile DEFAULT = new PlayerLimbProfile(0.0F, 0.0F, 0.0F);

    private final float lengthDelta;
    private final float widthDelta;
    private final float depthDelta;

    public PlayerLimbProfile(float lengthDelta, float widthDelta, float depthDelta) {
        validateDelta("lengthDelta", lengthDelta);
        validateDelta("widthDelta", widthDelta);
        validateDelta("depthDelta", depthDelta);

        this.lengthDelta = lengthDelta;
        this.widthDelta = widthDelta;
        this.depthDelta = depthDelta;
    }

    public float getLengthDelta() {
        return this.lengthDelta;
    }

    public float getWidthDelta() {
        return this.widthDelta;
    }

    public float getDepthDelta() {
        return this.depthDelta;
    }

    public boolean isModified() {
        return this.lengthDelta != 0.0F || this.widthDelta != 0.0F || this.depthDelta != 0.0F;
    }

    /**
     * Computes the final pixel dimension clamped to {@link #MIN_DIMENSION_PIXELS}.
     */
    public static float computeDimension(float basePixels, float deltaPixels) {
        return Math.max(MIN_DIMENSION_PIXELS, basePixels + deltaPixels);
    }

    /**
     * Computes the multiplicative scale factor relative to {@code basePixels}.
     */
    public static float computeScale(float basePixels, float deltaPixels) {
        return computeDimension(basePixels, deltaPixels) / basePixels;
    }

    private static void validateDelta(String name, float value) {
        if (!Float.isFinite(value) || Math.abs(value) > MAX_ABS_DELTA_PIXELS) {
            throw new IllegalArgumentException(
                    "Invalid " + name + ": " + value + " (must be finite and within [-"
                            + MAX_ABS_DELTA_PIXELS + ", " + MAX_ABS_DELTA_PIXELS + "])"
            );
        }
    }
}