package org.bruskych.finesse_races.client.render.player;

import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeDeformation;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.PartDefinition;

import org.bruskych.finesse_races.gameplay.races.appearance.PlayerAppearanceProfile;
import org.bruskych.finesse_races.gameplay.races.appearance.PlayerBodyPart;
import org.bruskych.finesse_races.gameplay.races.appearance.PlayerLimbProfile;

/**
 * Procedurally builds baked {@link ModelPart} meshes with exact pixel dimensions for any
 * player body parts replaced by a race's {@link PlayerAppearanceProfile}.
 * <p>
 * Baking dedicated geometry preserves crisp 1:1 texel density on resized limbs instead of
 * stretching vanilla textures. Invoked lazily by {@link RaceModelCache} so no mesh baking
 * occurs during frame rendering.
 *
 * <p>Standardized 64x64 Box-UV texture layouts:
 * <ul>
 *     <li><b>legs.png / arms_wide.png / arms_slim.png</b> (2x2 grid):
 *         Left Base (0, 0), Left Overlay (32, 0), Right Base (0, 32), Right Overlay (32, 32)</li>
 *     <li><b>head.png</b> (stacked vertically): Head Base (0, 0), Hat Overlay (0, 32)</li>
 *     <li><b>body.png</b> (stacked vertically): Body Base (0, 0), Jacket Overlay (0, 32)</li>
 * </ul>
 */
public final class RaceLimbGeometryBuilder {

    // Standard width and height (in pixels) of all custom race limb texture sheets.
    public static final int TEXTURE_SIZE = 64;
    // Vanilla inflation (in model pixels) for jacket, sleeve, and pants overlay layers.
    private static final float LIMB_OVERLAY_DEFORMATION = 0.25F;
    // Vanilla inflation (in model pixels) for the outer hat overlay layer.
    private static final float HAT_OVERLAY_DEFORMATION = 0.5F;

    // 2x2 UV quadrant origins for paired limbs (arms and legs)
    private static final int RIGHT_BASE_U = 0;
    private static final int RIGHT_BASE_V = 32;
    private static final int RIGHT_OVERLAY_U = 32;
    private static final int RIGHT_OVERLAY_V = 32;

    private static final int LEFT_BASE_U = 0;
    private static final int LEFT_BASE_V = 0;
    private static final int LEFT_OVERLAY_U = 32;
    private static final int LEFT_OVERLAY_V = 0;

    // Vertical UV origins for single-part sheets (head and body)
    private static final int SINGLE_BASE_U = 0;
    private static final int SINGLE_BASE_V = 0;
    private static final int SINGLE_OVERLAY_U = 0;
    private static final int SINGLE_OVERLAY_V = 32;

    private RaceLimbGeometryBuilder() {
    }

    /**
     * Immutable set of baked custom {@link ModelPart} meshes for a single race and arm model variant.
     * Any body part group not flagged for replacement in the profile remains {@code null}.
     *
     * @param head        baked custom head base layer, or {@code null}
     * @param hat         baked custom hat overlay layer, or {@code null}
     * @param body        baked custom torso base layer, or {@code null}
     * @param jacket      baked custom jacket overlay layer, or {@code null}
     * @param rightArm    baked custom right arm base layer, or {@code null}
     * @param rightSleeve baked custom right sleeve overlay layer, or {@code null}
     * @param leftArm     baked custom left arm base layer, or {@code null}
     * @param leftSleeve  baked custom left sleeve overlay layer, or {@code null}
     * @param rightLeg    baked custom right leg base layer, or {@code null}
     * @param rightPants  baked custom right pants overlay layer, or {@code null}
     * @param leftLeg     baked custom left leg base layer, or {@code null}
     * @param leftPants   baked custom left pants overlay layer, or {@code null}
     */
    public record BakedRaceLimbs(
            ModelPart head,
            ModelPart hat,
            ModelPart body,
            ModelPart jacket,
            ModelPart rightArm,
            ModelPart rightSleeve,
            ModelPart leftArm,
            ModelPart leftSleeve,
            ModelPart rightLeg,
            ModelPart rightPants,
            ModelPart leftLeg,
            ModelPart leftPants
    ) {
    }

    /**
     * Builds and bakes custom {@link ModelPart} geometry for every body part group marked as replaced
     * in the given appearance profile.
     *
     * @param profile the race appearance profile defining part dimensions and replacement flags
     * @param slim    {@code true} to bake slim (3px) arms, {@code false} for classic (4px) arms
     */
    public static BakedRaceLimbs build(PlayerAppearanceProfile profile, boolean slim) {
        ModelPart head = null;
        ModelPart hat = null;

        if (profile.replacesHead()) {
            PlayerLimbProfile headProfile = profile.getPart(PlayerBodyPart.HEAD);
            float headWidth = PlayerLimbProfile.computeDimension(8.0F, headProfile.getWidthDelta());
            float headHeight = PlayerLimbProfile.computeDimension(8.0F, headProfile.getLengthDelta());
            float headDepth = PlayerLimbProfile.computeDimension(8.0F, headProfile.getDepthDelta());

            // Center horizontally around neck pivot and grow upward (-Y) from Y = 0
            float headOriginX = -headWidth * 0.5F;
            float headOriginY = -headHeight;
            float headOriginZ = -headDepth * 0.5F;

            MeshDefinition headMesh = new MeshDefinition();
            PartDefinition headRoot = headMesh.getRoot();

            headRoot.addOrReplaceChild("head",
                    CubeListBuilder.create()
                            .texOffs(SINGLE_BASE_U, SINGLE_BASE_V)
                            .addBox(headOriginX, headOriginY, headOriginZ, headWidth, headHeight, headDepth, CubeDeformation.NONE),
                    PartPose.ZERO);

            headRoot.addOrReplaceChild("hat",
                    CubeListBuilder.create()
                            .texOffs(SINGLE_OVERLAY_U, SINGLE_OVERLAY_V)
                            .addBox(headOriginX, headOriginY, headOriginZ, headWidth, headHeight, headDepth, new CubeDeformation(HAT_OVERLAY_DEFORMATION)),
                    PartPose.ZERO);

            ModelPart bakedHead = LayerDefinition.create(headMesh, TEXTURE_SIZE, TEXTURE_SIZE).bakeRoot();
            head = bakedHead.getChild("head");
            hat = bakedHead.getChild("hat");
        }

        ModelPart body = null;
        ModelPart jacket = null;

        if (profile.replacesBody()) {
            PlayerLimbProfile bodyProfile = profile.getPart(PlayerBodyPart.BODY);
            float bodyWidth = PlayerLimbProfile.computeDimension(8.0F, bodyProfile.getWidthDelta());
            float bodyHeight = PlayerLimbProfile.computeDimension(12.0F, bodyProfile.getLengthDelta());
            float bodyDepth = PlayerLimbProfile.computeDimension(4.0F, bodyProfile.getDepthDelta());

            // Center horizontally around spine and extend downward (+Y) from shoulder line Y = 0
            float bodyOriginX = -bodyWidth * 0.5F;
            float bodyOriginZ = -bodyDepth * 0.5F;

            MeshDefinition bodyMesh = new MeshDefinition();
            PartDefinition bodyRoot = bodyMesh.getRoot();

            bodyRoot.addOrReplaceChild("body",
                    CubeListBuilder.create()
                            .texOffs(SINGLE_BASE_U, SINGLE_BASE_V)
                            .addBox(bodyOriginX, 0.0F, bodyOriginZ, bodyWidth, bodyHeight, bodyDepth, CubeDeformation.NONE),
                    PartPose.ZERO);

            bodyRoot.addOrReplaceChild("jacket",
                    CubeListBuilder.create()
                            .texOffs(SINGLE_OVERLAY_U, SINGLE_OVERLAY_V)
                            .addBox(bodyOriginX, 0.0F, bodyOriginZ, bodyWidth, bodyHeight, bodyDepth, new CubeDeformation(LIMB_OVERLAY_DEFORMATION)),
                    PartPose.ZERO);

            ModelPart bakedBody = LayerDefinition.create(bodyMesh, TEXTURE_SIZE, TEXTURE_SIZE).bakeRoot();
            body = bakedBody.getChild("body");
            jacket = bakedBody.getChild("jacket");
        }

        ModelPart rightArm = null;
        ModelPart rightSleeve = null;
        ModelPart leftArm = null;
        ModelPart leftSleeve = null;

        if (profile.replacesArms()) {
            float baseArmWidth = slim ? 3.0F : 4.0F;
            float armPivotY = slim ? 2.5F : 2.0F;

            // Keep the inner arm edge anchored against the torso so wider arms expand outward
            PlayerLimbProfile rightArmProfile = profile.getPart(PlayerBodyPart.RIGHT_ARM);
            float rightWidth = PlayerLimbProfile.computeDimension(baseArmWidth, rightArmProfile.getWidthDelta());
            float rightHeight = PlayerLimbProfile.computeDimension(12.0F, rightArmProfile.getLengthDelta());
            float rightDepth = PlayerLimbProfile.computeDimension(4.0F, rightArmProfile.getDepthDelta());
            float rightOriginX = 1.0F - rightWidth;
            float rightOriginZ = -rightDepth * 0.5F;

            PlayerLimbProfile leftArmProfile = profile.getPart(PlayerBodyPart.LEFT_ARM);
            float leftWidth = PlayerLimbProfile.computeDimension(baseArmWidth, leftArmProfile.getWidthDelta());
            float leftHeight = PlayerLimbProfile.computeDimension(12.0F, leftArmProfile.getLengthDelta());
            float leftDepth = PlayerLimbProfile.computeDimension(4.0F, leftArmProfile.getDepthDelta());
            float leftOriginX = -1.0F;
            float leftOriginZ = -leftDepth * 0.5F;

            MeshDefinition armsMesh = new MeshDefinition();
            PartDefinition armsRoot = armsMesh.getRoot();

            armsRoot.addOrReplaceChild("right_arm",
                    CubeListBuilder.create()
                            .texOffs(RIGHT_BASE_U, RIGHT_BASE_V)
                            .addBox(rightOriginX, -2.0F, rightOriginZ, rightWidth, rightHeight, rightDepth, CubeDeformation.NONE),
                    PartPose.offset(-5.0F, armPivotY, 0.0F));

            armsRoot.addOrReplaceChild("right_sleeve",
                    CubeListBuilder.create()
                            .texOffs(RIGHT_OVERLAY_U, RIGHT_OVERLAY_V)
                            .addBox(rightOriginX, -2.0F, rightOriginZ, rightWidth, rightHeight, rightDepth, new CubeDeformation(LIMB_OVERLAY_DEFORMATION)),
                    PartPose.offset(-5.0F, armPivotY, 0.0F));

            armsRoot.addOrReplaceChild("left_arm",
                    CubeListBuilder.create()
                            .texOffs(LEFT_BASE_U, LEFT_BASE_V)
                            .addBox(leftOriginX, -2.0F, leftOriginZ, leftWidth, leftHeight, leftDepth, CubeDeformation.NONE),
                    PartPose.offset(5.0F, armPivotY, 0.0F));

            armsRoot.addOrReplaceChild("left_sleeve",
                    CubeListBuilder.create()
                            .texOffs(LEFT_OVERLAY_U, LEFT_OVERLAY_V)
                            .addBox(leftOriginX, -2.0F, leftOriginZ, leftWidth, leftHeight, leftDepth, new CubeDeformation(LIMB_OVERLAY_DEFORMATION)),
                    PartPose.offset(5.0F, armPivotY, 0.0F));

            ModelPart bakedArms = LayerDefinition.create(armsMesh, TEXTURE_SIZE, TEXTURE_SIZE).bakeRoot();
            rightArm = bakedArms.getChild("right_arm");
            rightSleeve = bakedArms.getChild("right_sleeve");
            leftArm = bakedArms.getChild("left_arm");
            leftSleeve = bakedArms.getChild("left_sleeve");
        }

        ModelPart rightLeg = null;
        ModelPart rightPants = null;
        ModelPart leftLeg = null;
        ModelPart leftPants = null;

        if (profile.replacesLegs()) {
            // Keep inner leg edges anchored near the centerline (X = +/-1.9) so wider legs expand outward
            PlayerLimbProfile rightLegProfile = profile.getPart(PlayerBodyPart.RIGHT_LEG);
            float rightWidth = PlayerLimbProfile.computeDimension(4.0F, rightLegProfile.getWidthDelta());
            float rightHeight = PlayerLimbProfile.computeDimension(12.0F, rightLegProfile.getLengthDelta());
            float rightDepth = PlayerLimbProfile.computeDimension(4.0F, rightLegProfile.getDepthDelta());
            float rightLegOriginX = 1.9F - rightWidth;

            PlayerLimbProfile leftLegProfile = profile.getPart(PlayerBodyPart.LEFT_LEG);
            float leftWidth = PlayerLimbProfile.computeDimension(4.0F, leftLegProfile.getWidthDelta());
            float leftHeight = PlayerLimbProfile.computeDimension(12.0F, leftLegProfile.getLengthDelta());
            float leftDepth = PlayerLimbProfile.computeDimension(4.0F, leftLegProfile.getDepthDelta());
            float leftLegOriginX = -1.9F;

            MeshDefinition legsMesh = new MeshDefinition();
            PartDefinition legsRoot = legsMesh.getRoot();

            legsRoot.addOrReplaceChild("right_leg",
                    CubeListBuilder.create()
                            .texOffs(RIGHT_BASE_U, RIGHT_BASE_V)
                            .addBox(rightLegOriginX, 0.0F, -rightDepth * 0.5F, rightWidth, rightHeight, rightDepth, CubeDeformation.NONE),
                    PartPose.offset(-1.9F, 12.0F, 0.0F));

            legsRoot.addOrReplaceChild("right_pants",
                    CubeListBuilder.create()
                            .texOffs(RIGHT_OVERLAY_U, RIGHT_OVERLAY_V)
                            .addBox(rightLegOriginX, 0.0F, -rightDepth * 0.5F, rightWidth, rightHeight, rightDepth, new CubeDeformation(LIMB_OVERLAY_DEFORMATION)),
                    PartPose.offset(-1.9F, 12.0F, 0.0F));

            legsRoot.addOrReplaceChild("left_leg",
                    CubeListBuilder.create()
                            .texOffs(LEFT_BASE_U, LEFT_BASE_V)
                            .addBox(leftLegOriginX, 0.0F, -leftDepth * 0.5F, leftWidth, leftHeight, leftDepth, CubeDeformation.NONE),
                    PartPose.offset(1.9F, 12.0F, 0.0F));

            legsRoot.addOrReplaceChild("left_pants",
                    CubeListBuilder.create()
                            .texOffs(LEFT_OVERLAY_U, LEFT_OVERLAY_V)
                            .addBox(leftLegOriginX, 0.0F, -leftDepth * 0.5F, leftWidth, leftHeight, leftDepth, new CubeDeformation(LIMB_OVERLAY_DEFORMATION)),
                    PartPose.offset(1.9F, 12.0F, 0.0F));

            ModelPart bakedLegs = LayerDefinition.create(legsMesh, TEXTURE_SIZE, TEXTURE_SIZE).bakeRoot();
            rightLeg = bakedLegs.getChild("right_leg");
            rightPants = bakedLegs.getChild("right_pants");
            leftLeg = bakedLegs.getChild("left_leg");
            leftPants = bakedLegs.getChild("left_pants");
        }

        return new BakedRaceLimbs(
                head, hat,
                body, jacket,
                rightArm, rightSleeve, leftArm, leftSleeve,
                rightLeg, rightPants, leftLeg, leftPants
        );
    }
}