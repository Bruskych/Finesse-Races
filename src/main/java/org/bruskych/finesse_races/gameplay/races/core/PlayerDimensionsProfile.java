package org.bruskych.finesse_races.gameplay.races.core;

import net.minecraft.world.entity.EntityDimensions;
import net.minecraft.world.entity.Pose;

import java.util.Objects;

/**
 * Immutable profile storing precomputed player hitboxes and camera eye heights per pose.
 * Indexed directly by {@link Pose#ordinal()} for O(1) lookups without runtime allocations.
 */
public final class PlayerDimensionsProfile {

    private static final int POSE_COUNT = Pose.values().length;

    private static final byte EYE_UNCHANGED = 0;
    private static final byte EYE_OFFSET = 1;
    private static final byte EYE_ABSOLUTE = 2;

    /**
     * Default profile that leaves all vanilla dimensions and eye heights unchanged.
     */
    public static final PlayerDimensionsProfile EMPTY = builder().build();

    private final EntityDimensions[] dimensions;
    private final float[] eyeHeightValues;
    private final byte[] eyeHeightModes;

    private PlayerDimensionsProfile(Builder builder) {
        this.dimensions = builder.dimensions.clone();
        this.eyeHeightValues = builder.eyeHeightValues.clone();
        this.eyeHeightModes = builder.eyeHeightModes.clone();
    }

    /**
     * Returns custom dimensions for the given pose, or {@code null} if vanilla dimensions should be used.
     */
    public EntityDimensions getDimensions(Pose pose) {
        if (pose == null) {
            return null;
        }
        int poseIndex = pose.ordinal();
        if (poseIndex >= this.dimensions.length) {
            return null;
        }
        return this.dimensions[poseIndex];
    }

    /**
     * Applies a configured eye height override or offset to the vanilla eye height.
     *
     * @param pose             The player's current pose.
     * @param vanillaEyeHeight The default eye height calculated by Minecraft.
     * @return The resulting eye height, clamped to a non-negative value.
     */
    public float applyEyeHeight(Pose pose, float vanillaEyeHeight) {
        if (pose == null) {
            return vanillaEyeHeight;
        }

        int poseIndex = pose.ordinal();
        if (poseIndex >= this.dimensions.length) {
            return vanillaEyeHeight;
        }

        return switch (this.eyeHeightModes[poseIndex]) {
            case EYE_OFFSET -> Math.max(0.0F, vanillaEyeHeight + this.eyeHeightValues[poseIndex]);
            case EYE_ABSOLUTE -> this.eyeHeightValues[poseIndex];
            default -> vanillaEyeHeight;
        };
    }

    /**
     * Creates a new builder for configuring a race dimensions profile.
     */
    public static Builder builder() {
        return new Builder();
    }

    /**
     * Fluent builder for constructing immutable {@link PlayerDimensionsProfile} instances.
     */
    public static final class Builder {

        private final EntityDimensions[] dimensions = new EntityDimensions[POSE_COUNT];
        private final float[] eyeHeightValues = new float[POSE_COUNT];
        private final byte[] eyeHeightModes = new byte[POSE_COUNT];

        /**
         * Sets custom scalable dimensions for a specific pose.
         */
        public Builder dimensions(Pose pose, float width, float height) {
            Objects.requireNonNull(pose, "Pose cannot be null");
            validateDimensions(width, height);

            this.dimensions[pose.ordinal()] = EntityDimensions.scalable(width, height);
            return this;
        }

        /**
         * Sets custom fixed dimensions that ignore entity scaling for a specific pose.
         */
        public Builder fixedDimensions(Pose pose, float width, float height) {
            Objects.requireNonNull(pose, "Pose cannot be null");
            validateDimensions(width, height);

            this.dimensions[pose.ordinal()] = EntityDimensions.fixed(width, height);
            return this;
        }

        /**
         * Adds width and height offsets relative to vanilla Minecraft 1.20.1 player dimensions.
         * Preserves whether the vanilla pose uses fixed or scalable geometry.
         */
        public Builder addToVanilla(Pose pose, float widthOffset, float heightOffset) {
            Objects.requireNonNull(pose, "Pose cannot be null");
            EntityDimensions vanilla = getVanillaDimensions(pose);

            float width = vanilla.width + widthOffset;
            float height = vanilla.height + heightOffset;
            validateDimensions(width, height);

            this.dimensions[pose.ordinal()] = vanilla.fixed
                    ? EntityDimensions.fixed(width, height)
                    : EntityDimensions.scalable(width, height);

            return this;
        }

        /**
         * Adds a relative offset (in blocks) to the vanilla camera eye height for a pose.
         */
        public Builder eyeHeightOffset(Pose pose, float offset) {
            Objects.requireNonNull(pose, "Pose cannot be null");
            if (!Float.isFinite(offset)) {
                throw new IllegalArgumentException("Invalid eye height offset: " + offset);
            }

            int poseIndex = pose.ordinal();
            this.eyeHeightValues[poseIndex] = offset;
            this.eyeHeightModes[poseIndex] = EYE_OFFSET;
            return this;
        }

        /**
         * Sets an exact camera eye height (in blocks) for a pose.
         */
        public Builder eyeHeight(Pose pose, float height) {
            Objects.requireNonNull(pose, "Pose cannot be null");
            if (!Float.isFinite(height) || height < 0.0F) {
                throw new IllegalArgumentException("Invalid eye height: " + height);
            }

            int poseIndex = pose.ordinal();
            this.eyeHeightValues[poseIndex] = height;
            this.eyeHeightModes[poseIndex] = EYE_ABSOLUTE;
            return this;
        }

        /**
         * Finalizes and returns an immutable {@link PlayerDimensionsProfile}.
         */
        public PlayerDimensionsProfile build() {
            return new PlayerDimensionsProfile(this);
        }

        /**
         * Returns standard vanilla player dimensions for Minecraft 1.20.1.
         */
        private static EntityDimensions getVanillaDimensions(Pose pose) {
            return switch (pose) {
                case CROUCHING -> EntityDimensions.scalable(0.6F, 1.5F);
                case SWIMMING, FALL_FLYING, SPIN_ATTACK -> EntityDimensions.scalable(0.6F, 0.6F);
                case SLEEPING, DYING -> EntityDimensions.fixed(0.2F, 0.2F);
                default -> EntityDimensions.scalable(0.6F, 1.8F);
            };
        }

        private static void validateDimensions(float width, float height) {
            if (!Float.isFinite(width) || !Float.isFinite(height) || width <= 0.0F || height <= 0.0F) {
                throw new IllegalArgumentException("Invalid dimensions: " + width + " x " + height);
            }
        }
    }
}