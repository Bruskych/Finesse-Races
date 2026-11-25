package org.bruskych.finesse_races.gameplay.races.appearance;

/**
 * Immutable configuration controlling non-stretching boot alignment for a race.
 * Armor always keeps vanilla 1:1 proportions; this profile only governs vertical boot positioning.
 */
public final class ArmorFitProfile {

    /** Shared default profile with automatic boot-to-foot alignment and no extra offset. */
    public static final ArmorFitProfile DEFAULT = builder().build();

    private final boolean alignBootsToFeet;
    private final float bootExtraOffsetPixels;

    private ArmorFitProfile(Builder builder) {
        this.alignBootsToFeet = builder.alignBootsToFeet;
        this.bootExtraOffsetPixels = builder.bootExtraOffsetPixels;
    }

    /**
     * Returns whether boots should automatically shift along each leg's local Y axis to match custom leg length.
     */
    public boolean shouldAlignBootsToFeet() {
        return this.alignBootsToFeet;
    }

    /**
     * Returns the additional vertical boot offset (in model pixels) applied on top of leg length delta.
     */
    public float getBootExtraOffsetPixels() {
        return this.bootExtraOffsetPixels;
    }

    /**
     * Returns {@code true} if a non-zero custom boot offset is configured.
     */
    public boolean hasModifications() {
        return this.bootExtraOffsetPixels != 0.0F;
    }

    /**
     * Creates a new {@link Builder} initialized to vanilla-aligned defaults.
     */
    public static Builder builder() {
        return new Builder();
    }

    /**
     * Fluent builder for constructing validated {@link ArmorFitProfile} instances.
     */
    public static final class Builder {

        private boolean alignBootsToFeet = true;
        private float bootExtraOffsetPixels = 0.0F;

        private Builder() {
        }

        /**
         * Enables or disables automatic boot alignment to custom leg lengths (enabled by default).
         */
        public Builder alignBootsToFeet(boolean align) {
            this.alignBootsToFeet = align;
            return this;
        }

        /**
         * Sets an extra vertical offset (in model pixels) for fine-tuning boot placement (e.g., hooves or claws).
         *
         * @throws IllegalArgumentException if {@code pixels} is non-finite or exceeds {@link PlayerLimbProfile#MAX_ABS_DELTA_PIXELS}
         */
        public Builder bootExtraOffsetPixels(float pixels) {
            if (!Float.isFinite(pixels) || Math.abs(pixels) > PlayerLimbProfile.MAX_ABS_DELTA_PIXELS) {
                throw new IllegalArgumentException("Invalid boot extra offset: " + pixels);
            }
            this.bootExtraOffsetPixels = pixels;
            return this;
        }

        /**
         * Builds an immutable {@link ArmorFitProfile} from the configured options.
         */
        public ArmorFitProfile build() {
            return new ArmorFitProfile(this);
        }
    }
}