package org.bruskych.finesse_races.client.render;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.player.Player;

import org.bruskych.finesse_races.gameplay.races.appearance.PlayerBodyPart;
import org.bruskych.finesse_races.gameplay.races.appearance.PlayerLimbProfile;
import org.bruskych.finesse_races.gameplay.races.core.AbstractRace;
import org.bruskych.finesse_races.gameplay.races.core.RaceManager;

/**
 * Shared client-side math utilities for aligning {@link ModelPart} transforms and
 * layer {@link PoseStack} attachments to customized race body proportions.
 * <p>
 * Used across armor alignment ({@code ArmorAppearanceController}), first-person hand
 * rendering ({@code FirstPersonHandController}), and back-mounted layers ({@code CapeLayerMixin},
 * {@code ElytraLayerMixin}) to keep transform math consistent and allocation-free.
 */
public final class ModelPartTransformHelper {

    private ModelPartTransformHelper() {
    }

    /**
     * Shifts a {@link ModelPart} along its own local Y axis without modifying its scale.
     * <p>
     * Rotates the local displacement vector {@code (0, deltaPixels, 0)} using Minecraft's
     * Z-Y-X Euler rotation order ({@code Rz * Ry * Rx}) and adds the result to the part's
     * pivot coordinates so attachments (helmets, boots, first-person arms) stay aligned
     * regardless of limb swing or head pitch/yaw.
     *
     * @param part        the model part whose position ({@code x, y, z}) will be shifted
     * @param deltaPixels offset in model pixels along local Y ({@code +Y} downward, {@code -Y} upward)
     */
    public static void shiftAlongLocalYAxis(ModelPart part, float deltaPixels) {
        if (deltaPixels == 0.0F) {
            return;
        }

        // Fast path: unrotated part aligns directly with model Y
        if (part.xRot == 0.0F && part.yRot == 0.0F && part.zRot == 0.0F) {
            part.y += deltaPixels;
            return;
        }

        float sinX = Mth.sin(part.xRot);
        float cosX = Mth.cos(part.xRot);

        // Common fast path for walking/running limbs where only pitch (xRot) is non-zero
        if (part.yRot == 0.0F && part.zRot == 0.0F) {
            part.y += deltaPixels * cosX;
            part.z += deltaPixels * sinX;
            return;
        }

        float sinY = Mth.sin(part.yRot);
        float cosY = Mth.cos(part.yRot);
        float sinZ = Mth.sin(part.zRot);
        float cosZ = Mth.cos(part.zRot);

        // 1. Rotate (0, deltaPixels, 0) around X axis
        float y1 = deltaPixels * cosX;
        float z1 = deltaPixels * sinX;

        // 2. Rotate around Y axis
        float x2 = z1 * sinY;
        float z2 = z1 * cosY;

        // 3. Rotate around Z axis and apply world-model translation
        part.x += x2 * cosZ - y1 * sinZ;
        part.y += x2 * sinZ + y1 * cosZ;
        part.z += z2;
    }

    /**
     * Translates a back-mounted render layer (such as a cape or Elytra) to match customized
     * torso height and back depth.
     * <p>
     * Vanilla cape and Elytra layers anchor at model {@code Y = 0} and ignore {@code body.y},
     * so this helper shifts the active {@link PoseStack} upward by {@code bodyLengthDelta} and
     * backward by half of {@code bodyDepthDelta} (converted from model pixels to blocks).
     *
     * @param player    the player whose race torso profile determines the offset
     * @param poseStack the layer's active {@link PoseStack} after {@code pushPose()}
     */
    public static void alignTorsoAttachment(Player player, PoseStack poseStack) {
        AbstractRace race = RaceManager.getRaceOfPlayer(player);
        if (race == null) {
            return;
        }

        PlayerLimbProfile bodyProfile = race.getAppearanceProfile().getPart(PlayerBodyPart.BODY);
        float bodyLengthDelta = bodyProfile.getLengthDelta();
        float bodyDepthDelta = bodyProfile.getDepthDelta();

        if (bodyLengthDelta != 0.0F || bodyDepthDelta != 0.0F) {
            // Convert model pixels to world blocks (/ 16.0F); +Z shifts away from the chest toward the back
            poseStack.translate(0.0F, -bodyLengthDelta / 16.0F, (bodyDepthDelta * 0.5F) / 16.0F);
        }
    }
}