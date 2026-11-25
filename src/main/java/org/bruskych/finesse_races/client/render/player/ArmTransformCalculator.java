package org.bruskych.finesse_races.client.render.player;

import net.minecraft.client.player.AbstractClientPlayer;
import net.minecraft.world.entity.HumanoidArm;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;

import org.bruskych.finesse_races.gameplay.races.appearance.PlayerAppearanceProfile;
import org.bruskych.finesse_races.gameplay.races.appearance.PlayerBodyPart;
import org.bruskych.finesse_races.gameplay.races.appearance.PlayerLimbProfile;

import java.util.IdentityHashMap;
import java.util.Map;

/**
 * Centralized math utility that computes arm-related transforms for both third-person
 * held item alignment and first-person hand placement.
 * <p>
 * Adapts automatically to any custom arm length, width, or depth without stretching
 * held items in third person or letting unusually long/short arms obstruct the first-person view.
 */
public final class ArmTransformCalculator {

    private static final float BASE_ARM_HEIGHT = 12.0F;
    private static final float BASE_ARM_DEPTH = 4.0F;
    private static final float VANILLA_WRIST_Y = 10.0F;

    /**
     * First-person empty-hand balance: ~30% of length delta translates into subtle wrist reach,
     * while the remaining ~70% shifts elbow depth behind the camera.
     */
    private static final float FP_WRIST_SENSITIVITY = 0.30F;
    private static final float FP_MAX_WRIST_EXTEND_PX = 2.5F;
    private static final float FP_MAX_WRIST_RETRACT_PX = 2.0F;

    private static final Map<PlayerAppearanceProfile, HeldItemTransforms> WIDE_ITEM_CACHE = new IdentityHashMap<>();
    private static final Map<PlayerAppearanceProfile, HeldItemTransforms> SLIM_ITEM_CACHE = new IdentityHashMap<>();

    private ArmTransformCalculator() {
    }

    /**
     * Local arm-space transform applied at the end of {@code PlayerModel#translateToHand}.
     *
     * @param offsetX   horizontal wrist alignment offset in world blocks (pixels / 16.0)
     * @param offsetY   vertical wrist alignment offset in world blocks (pixels / 16.0)
     * @param offsetZ   depth wrist alignment offset in world blocks (pixels / 16.0)
     * @param invScaleX inverse X scale factor applied to cancel arm width scaling on held items
     * @param invScaleY inverse Y scale factor applied to cancel arm length scaling on held items
     * @param invScaleZ inverse Z scale factor applied to cancel arm depth scaling on held items
     */
    public record HeldItemTransform(
            float offsetX,
            float offsetY,
            float offsetZ,
            float invScaleX,
            float invScaleY,
            float invScaleZ
    ) {
        /** No-op transform representing an unmodified vanilla arm. */
        public static final HeldItemTransform IDENTITY = new HeldItemTransform(
                0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F
        );

        /**
         * Returns {@code true} if this transform applies neither translation nor scaling.
         */
        public boolean isIdentity() {
            return this.offsetX == 0.0F
                    && this.offsetY == 0.0F
                    && this.offsetZ == 0.0F
                    && this.invScaleX == 1.0F
                    && this.invScaleY == 1.0F
                    && this.invScaleZ == 1.0F;
        }
    }

    /**
     * Cached pair of third-person held item transforms for both arms of a race appearance profile.
     *
     * @param right held item transform for the right arm
     * @param left  held item transform for the left arm
     */
    public record HeldItemTransforms(HeldItemTransform right, HeldItemTransform left) {

        /** Shared identity pair for profiles with no arm modifications. */
        public static final HeldItemTransforms IDENTITY = new HeldItemTransforms(
                HeldItemTransform.IDENTITY,
                HeldItemTransform.IDENTITY
        );

        /**
         * Returns the held item transform corresponding to the specified arm side.
         *
         * @param side the arm side being translated to
         * @return the matching {@link HeldItemTransform}
         */
        public HeldItemTransform forSide(HumanoidArm side) {
            return (side == HumanoidArm.RIGHT) ? this.right : this.left;
        }
    }

    /**
     * Transform applied to the player's arm during first-person hand rendering.
     *
     * @param offsetYPixels shift in model pixels along the arm's local Y axis
     *                      ({@code -Y} moves toward the elbow, {@code +Y} moves toward the wrist)
     * @param scaleX        X scale factor for non-replaced vanilla arms ({@code 1.0} when replaced)
     * @param scaleY        Y scale factor for non-replaced vanilla arms ({@code 1.0} when replaced)
     * @param scaleZ        Z scale factor for non-replaced vanilla arms ({@code 1.0} when replaced)
     */
    public record FirstPersonArmTransform(
            float offsetYPixels,
            float scaleX,
            float scaleY,
            float scaleZ
    ) {
        /** No-op transform representing an unmodified vanilla first-person arm. */
        public static final FirstPersonArmTransform IDENTITY = new FirstPersonArmTransform(
                0.0F, 1.0F, 1.0F, 1.0F
        );

        /**
         * Returns {@code true} if this transform applies neither a Y-axis shift nor scaling.
         */
        public boolean isIdentity() {
            return this.offsetYPixels == 0.0F
                    && this.scaleX == 1.0F
                    && this.scaleY == 1.0F
                    && this.scaleZ == 1.0F;
        }
    }

    /**
     * Returns the cached third-person held item transforms for both arms, computing them lazily
     * once per {@link PlayerAppearanceProfile} instance and arm model variant.
     *
     * @param profile the race appearance profile to resolve transforms for
     * @param slim    {@code true} for the slim (3px) arm model, {@code false} for classic (4px)
     */
    public static HeldItemTransforms getThirdPersonItems(PlayerAppearanceProfile profile, boolean slim) {
        if (!profile.hasVisualModifications()) {
            return HeldItemTransforms.IDENTITY;
        }

        Map<PlayerAppearanceProfile, HeldItemTransforms> cache = slim ? SLIM_ITEM_CACHE : WIDE_ITEM_CACHE;
        HeldItemTransforms cached = cache.get(profile);
        if (cached != null) {
            return cached;
        }

        HeldItemTransform right = computeThirdPersonItem(profile, HumanoidArm.RIGHT, slim);
        HeldItemTransform left = computeThirdPersonItem(profile, HumanoidArm.LEFT, slim);
        HeldItemTransforms computed = (right.isIdentity() && left.isIdentity())
                ? HeldItemTransforms.IDENTITY
                : new HeldItemTransforms(right, left);

        cache.put(profile, computed);
        return computed;
    }

    /**
     * Clears all cached third-person held item transforms (e.g., on resource reload).
     */
    public static void clearCache() {
        WIDE_ITEM_CACHE.clear();
        SLIM_ITEM_CACHE.clear();
    }

    /**
     * Computes the third-person held item alignment transform for a single arm side.
     * For custom-geometry arms, shifts the item to the exact resized wrist; for scaled vanilla
     * arms, additionally applies inverse scale factors so held items stay at 1:1 proportions.
     */
    private static HeldItemTransform computeThirdPersonItem(
            PlayerAppearanceProfile profile,
            HumanoidArm side,
            boolean slim
    ) {
        PlayerBodyPart bodyPart = (side == HumanoidArm.RIGHT)
                ? PlayerBodyPart.RIGHT_ARM
                : PlayerBodyPart.LEFT_ARM;
        PlayerLimbProfile armProfile = profile.getPart(bodyPart);

        if (!armProfile.isModified()) {
            return HeldItemTransform.IDENTITY;
        }

        float baseWidth = slim ? 3.0F : 4.0F;
        float sideSign = (side == HumanoidArm.RIGHT) ? -1.0F : 1.0F;

        if (profile.replacesArms()) {
            float actualWidth = PlayerLimbProfile.computeDimension(baseWidth, armProfile.getWidthDelta());
            float actualHeight = PlayerLimbProfile.computeDimension(BASE_ARM_HEIGHT, armProfile.getLengthDelta());

            float deltaWidth = actualWidth - baseWidth;
            float deltaHeight = actualHeight - BASE_ARM_HEIGHT;

            float offsetX = (sideSign * deltaWidth * 0.5F) / 16.0F;
            float offsetY = deltaHeight / 16.0F;
            return new HeldItemTransform(offsetX, offsetY, 0.0F, 1.0F, 1.0F, 1.0F);
        }

        // Unreplaced vanilla arm: undo ModelPart scale so held items never stretch, then align to scaled wrist
        float sx = PlayerLimbProfile.computeScale(baseWidth, armProfile.getWidthDelta());
        float sy = PlayerLimbProfile.computeScale(BASE_ARM_HEIGHT, armProfile.getLengthDelta());
        float sz = PlayerLimbProfile.computeScale(BASE_ARM_DEPTH, armProfile.getDepthDelta());

        float vanillaArmCenterX = slim ? 0.5F : 1.0F;
        float offsetX = (sideSign * vanillaArmCenterX * (sx - 1.0F)) / 16.0F;
        float offsetY = (VANILLA_WRIST_Y * (sy - 1.0F)) / 16.0F;

        return new HeldItemTransform(offsetX, offsetY, 0.0F, 1.0F / sx, 1.0F / sy, 1.0F / sz);
    }

    /**
     * Calculates the first-person arm Y-axis offset and (for non-replaced arms) scale factors.
     * Locks the wrist to the exact vanilla grip point whenever holding an item or two-handed map
     * (preserving compatibility with maps and first-person item mods), and applies a smooth
     * elbow/wrist length split when empty-handed.
     *
     * @param player     the local client player whose arm is being rendered
     * @param profile    the player's active race appearance profile
     * @param isRightArm {@code true} if rendering the right arm, {@code false} for the left arm
     * @param slim       {@code true} for the slim (3px) arm model, {@code false} for classic (4px)
     */
    public static FirstPersonArmTransform computeFirstPersonArm(
            AbstractClientPlayer player,
            PlayerAppearanceProfile profile,
            boolean isRightArm,
            boolean slim
    ) {
        PlayerBodyPart bodyPart = isRightArm ? PlayerBodyPart.RIGHT_ARM : PlayerBodyPart.LEFT_ARM;
        PlayerLimbProfile armProfile = profile.getPart(bodyPart);

        if (!armProfile.isModified()) {
            return FirstPersonArmTransform.IDENTITY;
        }

        float baseWidth = slim ? 3.0F : 4.0F;
        float actualHeight = PlayerLimbProfile.computeDimension(BASE_ARM_HEIGHT, armProfile.getLengthDelta());
        float effectiveLengthDelta = actualHeight - BASE_ARM_HEIGHT;

        boolean lockToWrist = shouldLockToVanillaWrist(player, isRightArm);
        float desiredWristShift = lockToWrist ? 0.0F : computeSmoothWristShift(effectiveLengthDelta);

        if (profile.replacesArms()) {
            // Custom arm bottom sits at (VANILLA_WRIST_Y + effectiveLengthDelta)
            float offsetYPixels = desiredWristShift - effectiveLengthDelta;
            return new FirstPersonArmTransform(offsetYPixels, 1.0F, 1.0F, 1.0F);
        }

        float sx = PlayerLimbProfile.computeScale(baseWidth, armProfile.getWidthDelta());
        float sy = actualHeight / BASE_ARM_HEIGHT;
        float sz = PlayerLimbProfile.computeScale(BASE_ARM_DEPTH, armProfile.getDepthDelta());

        // Scaled vanilla arm bottom sits at (VANILLA_WRIST_Y * sy)
        float offsetYPixels = desiredWristShift + VANILLA_WRIST_Y * (1.0F - sy);
        return new FirstPersonArmTransform(offsetYPixels, sx, sy, sz);
    }

    /**
     * Maps raw arm length delta to a soft-clamped first-person wrist shift using {@link Math#tanh}
     * so extreme arm lengths feel distinct without pushing the hand off-screen or into the camera.
     */
    private static float computeSmoothWristShift(float lengthDelta) {
        if (lengthDelta == 0.0F) {
            return 0.0F;
        }
        float maxShift = (lengthDelta > 0.0F) ? FP_MAX_WRIST_EXTEND_PX : FP_MAX_WRIST_RETRACT_PX;
        double normalized = (lengthDelta * FP_WRIST_SENSITIVITY) / maxShift;
        return (float) (maxShift * Math.tanh(normalized));
    }

    /**
     * Determines whether the first-person wrist must stay locked to the vanilla grip position
     * because the arm is holding an item or supporting a two-handed filled map.
     */
    private static boolean shouldLockToVanillaWrist(AbstractClientPlayer player, boolean isRightArm) {
        boolean isMainHand = (player.getMainArm() == HumanoidArm.RIGHT) == isRightArm;
        ItemStack thisHand = isMainHand ? player.getMainHandItem() : player.getOffhandItem();
        if (!thisHand.isEmpty()) {
            return true;
        }
        // Two-handed vanilla map holds the map with both arms even while the offhand stack is empty
        ItemStack otherHand = isMainHand ? player.getOffhandItem() : player.getMainHandItem();
        return otherHand.is(Items.FILLED_MAP);
    }
}