package org.bruskych.finesse_races.gameplay.races.appearance;

import java.util.Arrays;
import java.util.Objects;

/**
 * Immutable appearance profile for a race.
 * Defines per-limb pixel deltas, custom geometry replacement flags, vertical grounding offset,
 * and armor fit settings. Validated at build time with precomputed fast-path flags.
 */
public final class PlayerAppearanceProfile {

    private static final int PART_COUNT = PlayerBodyPart.values().length;

    /** Shared unmodified vanilla appearance profile. */
    public static final PlayerAppearanceProfile EMPTY = builder().build();

    private final PlayerLimbProfile[] parts;
    private final boolean replaceHead;
    private final boolean replaceBody;
    private final boolean replaceArms;
    private final boolean replaceLegs;
    private final float verticalOffsetPixels;
    private final ArmorFitProfile armorFitProfile;
    private final boolean hasVisualModifications;
    private final boolean hasArmorModifications;

    private PlayerAppearanceProfile(Builder builder) {
        this.parts = builder.parts.clone();
        this.replaceHead = builder.replaceHead;
        this.replaceBody = builder.replaceBody;
        this.replaceArms = builder.replaceArms;
        this.replaceLegs = builder.replaceLegs;
        this.armorFitProfile = builder.armorFitProfile;

        validatePartDimensions(PlayerBodyPart.HEAD, 8.0F, 8.0F, 8.0F);
        validatePartDimensions(PlayerBodyPart.BODY, 8.0F, 12.0F, 4.0F);
        // Validate against slim arm baseline (3px) so slim skins never collapse below 1px
        validatePartDimensions(PlayerBodyPart.LEFT_ARM, 3.0F, 12.0F, 4.0F);
        validatePartDimensions(PlayerBodyPart.RIGHT_ARM, 3.0F, 12.0F, 4.0F);
        validatePartDimensions(PlayerBodyPart.LEFT_LEG, 4.0F, 12.0F, 4.0F);
        validatePartDimensions(PlayerBodyPart.RIGHT_LEG, 4.0F, 12.0F, 4.0F);

        if (builder.customVerticalOffset != null) {
            this.verticalOffsetPixels = builder.customVerticalOffset;
        } else {
            float leftLegDelta = this.parts[PlayerBodyPart.LEFT_LEG.ordinal()].getLengthDelta();
            float rightLegDelta = this.parts[PlayerBodyPart.RIGHT_LEG.ordinal()].getLengthDelta();
            this.verticalOffsetPixels = Math.max(leftLegDelta, rightLegDelta);
        }

        boolean visualModified = this.replaceHead || this.replaceBody || this.replaceArms
                || this.replaceLegs || this.verticalOffsetPixels != 0.0F;
        if (!visualModified) {
            for (PlayerLimbProfile part : this.parts) {
                if (part.isModified()) {
                    visualModified = true;
                    break;
                }
            }
        }
        this.hasVisualModifications = visualModified;
        this.hasArmorModifications = this.armorFitProfile.hasModifications();
    }

    /**
     * Ensures no axis of the given body part shrinks below {@link PlayerLimbProfile#MIN_DIMENSION_PIXELS}.
     */
    private void validatePartDimensions(PlayerBodyPart bodyPart, float minBaseW, float baseH, float baseD) {
        PlayerLimbProfile part = this.parts[bodyPart.ordinal()];
        if (minBaseW + part.getWidthDelta() < PlayerLimbProfile.MIN_DIMENSION_PIXELS) {
            throw new IllegalArgumentException(bodyPart + " widthDelta (" + part.getWidthDelta()
                    + ") shrinks width below " + PlayerLimbProfile.MIN_DIMENSION_PIXELS + "px");
        }
        if (baseH + part.getLengthDelta() < PlayerLimbProfile.MIN_DIMENSION_PIXELS) {
            throw new IllegalArgumentException(bodyPart + " lengthDelta (" + part.getLengthDelta()
                    + ") shrinks height below " + PlayerLimbProfile.MIN_DIMENSION_PIXELS + "px");
        }
        if (baseD + part.getDepthDelta() < PlayerLimbProfile.MIN_DIMENSION_PIXELS) {
            throw new IllegalArgumentException(bodyPart + " depthDelta (" + part.getDepthDelta()
                    + ") shrinks depth below " + PlayerLimbProfile.MIN_DIMENSION_PIXELS + "px");
        }
    }

    public PlayerLimbProfile getPart(PlayerBodyPart part) {
        return this.parts[part.ordinal()];
    }

    public boolean replacesHead() {
        return this.replaceHead;
    }

    public boolean replacesBody() {
        return this.replaceBody;
    }

    public boolean replacesArms() {
        return this.replaceArms;
    }

    public boolean replacesLegs() {
        return this.replaceLegs;
    }

    public boolean hasAnyReplacedParts() {
        return this.replaceHead || this.replaceBody || this.replaceArms || this.replaceLegs;
    }

    /**
     * Vertical grounding offset in model pixels (defaults to the longer leg's length delta).
     */
    public float getVerticalOffsetPixels() {
        return this.verticalOffsetPixels;
    }

    public ArmorFitProfile getArmorFitProfile() {
        return this.armorFitProfile;
    }

    public boolean hasVisualModifications() {
        return this.hasVisualModifications;
    }

    public boolean hasArmorModifications() {
        return this.hasArmorModifications;
    }

    public boolean isEmpty() {
        return !this.hasVisualModifications && !this.hasArmorModifications;
    }

    public static Builder builder() {
        return new Builder();
    }

    /**
     * Fluent builder for constructing validated {@link PlayerAppearanceProfile} instances.
     */
    public static final class Builder {

        private final PlayerLimbProfile[] parts = new PlayerLimbProfile[PART_COUNT];
        private boolean replaceHead = false;
        private boolean replaceBody = false;
        private boolean replaceArms = false;
        private boolean replaceLegs = false;
        private Float customVerticalOffset = null;
        private ArmorFitProfile armorFitProfile = ArmorFitProfile.DEFAULT;

        private Builder() {
            Arrays.fill(this.parts, PlayerLimbProfile.DEFAULT);
        }

        public Builder part(PlayerBodyPart bodyPart, float lengthDelta, float widthDelta, float depthDelta) {
            Objects.requireNonNull(bodyPart, "Body part cannot be null");
            this.parts[bodyPart.ordinal()] = new PlayerLimbProfile(lengthDelta, widthDelta, depthDelta);
            return this;
        }

        public Builder head(float lengthDelta, float widthDelta, float depthDelta) {
            return part(PlayerBodyPart.HEAD, lengthDelta, widthDelta, depthDelta);
        }

        public Builder body(float lengthDelta, float widthDelta, float depthDelta) {
            return part(PlayerBodyPart.BODY, lengthDelta, widthDelta, depthDelta);
        }

        public Builder leftArm(float lengthDelta, float widthDelta, float depthDelta) {
            return part(PlayerBodyPart.LEFT_ARM, lengthDelta, widthDelta, depthDelta);
        }

        public Builder rightArm(float lengthDelta, float widthDelta, float depthDelta) {
            return part(PlayerBodyPart.RIGHT_ARM, lengthDelta, widthDelta, depthDelta);
        }

        public Builder arms(float lengthDelta, float widthDelta, float depthDelta) {
            leftArm(lengthDelta, widthDelta, depthDelta);
            return rightArm(lengthDelta, widthDelta, depthDelta);
        }

        public Builder leftLeg(float lengthDelta, float widthDelta, float depthDelta) {
            return part(PlayerBodyPart.LEFT_LEG, lengthDelta, widthDelta, depthDelta);
        }

        public Builder rightLeg(float lengthDelta, float widthDelta, float depthDelta) {
            return part(PlayerBodyPart.RIGHT_LEG, lengthDelta, widthDelta, depthDelta);
        }

        public Builder legs(float lengthDelta, float widthDelta, float depthDelta) {
            leftLeg(lengthDelta, widthDelta, depthDelta);
            return rightLeg(lengthDelta, widthDelta, depthDelta);
        }

        public Builder replaceHead(boolean replace) {
            this.replaceHead = replace;
            return this;
        }

        public Builder replaceBody(boolean replace) {
            this.replaceBody = replace;
            return this;
        }

        public Builder replaceArms(boolean replace) {
            this.replaceArms = replace;
            return this;
        }

        public Builder replaceLegs(boolean replace) {
            this.replaceLegs = replace;
            return this;
        }

        public Builder replaceAll(boolean replace) {
            this.replaceHead = replace;
            this.replaceBody = replace;
            this.replaceArms = replace;
            this.replaceLegs = replace;
            return this;
        }

        /**
         * Overrides the automatic leg-based vertical grounding offset (in model pixels).
         */
        public Builder verticalOffsetPixels(float pixels) {
            if (!Float.isFinite(pixels) || Math.abs(pixels) > PlayerLimbProfile.MAX_ABS_DELTA_PIXELS) {
                throw new IllegalArgumentException("Invalid vertical offset: " + pixels);
            }
            this.customVerticalOffset = pixels;
            return this;
        }

        public Builder armorFit(ArmorFitProfile armorFitProfile) {
            this.armorFitProfile = Objects.requireNonNull(armorFitProfile, "ArmorFitProfile cannot be null");
            return this;
        }

        public PlayerAppearanceProfile build() {
            return new PlayerAppearanceProfile(this);
        }
    }
}