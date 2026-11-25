package org.bruskych.finesse_races.core.mixin.client;

import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.PlayerModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.player.AbstractClientPlayer;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;

import org.bruskych.finesse_races.client.render.player.FirstPersonHandController;
import org.bruskych.finesse_races.gameplay.races.appearance.PlayerAppearanceProfile;
import org.bruskych.finesse_races.gameplay.races.appearance.PlayerBodyPart;
import org.bruskych.finesse_races.gameplay.races.appearance.PlayerLimbProfile;
import org.bruskych.finesse_races.gameplay.races.core.AbstractRace;
import org.bruskych.finesse_races.gameplay.races.core.RaceManager;

import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

/**
 * Resets shared {@link PlayerModel} transforms every frame, aligns torso joints (neck and shoulders)
 * to custom race proportions, and applies fallback scaling to modified parts without custom geometry.
 */
@Mixin(HumanoidModel.class)
public abstract class HumanoidModelMixin<T extends LivingEntity> {

    @Shadow @Final public ModelPart head;
    @Shadow @Final public ModelPart hat;
    @Shadow @Final public ModelPart body;
    @Shadow @Final public ModelPart rightArm;
    @Shadow @Final public ModelPart leftArm;
    @Shadow @Final public ModelPart rightLeg;
    @Shadow @Final public ModelPart leftLeg;

    /**
     * Resets all base parts before vanilla animation runs so scale and offset changes
     * never leak between players sharing the same {@link PlayerModel} instance.
     */
    @Inject(method = "setupAnim", at = @At("HEAD"))
    private void finesseRaces$resetPlayerModelPose(
            T entity,
            float limbSwing,
            float limbSwingAmount,
            float ageInTicks,
            float netHeadYaw,
            float headPitch,
            CallbackInfo callbackInfo
    ) {
        if (!((Object) this instanceof PlayerModel<?>)) {
            return;
        }

        this.head.resetPose();
        this.hat.resetPose();
        this.body.resetPose();
        this.rightArm.resetPose();
        this.leftArm.resetPose();
        this.rightLeg.resetPose();
        this.leftLeg.resetPose();
    }

    /**
     * Applies race joint offsets and non-replaced limb scaling after vanilla pose setup.
     * Skipped during first-person hand rendering, which is handled by {@link FirstPersonHandController}.
     */
    @Inject(method = "setupAnim", at = @At("TAIL"))
    private void finesseRaces$applyAppearanceTransforms(
            T entity,
            float limbSwing,
            float limbSwingAmount,
            float ageInTicks,
            float netHeadYaw,
            float headPitch,
            CallbackInfo callbackInfo
    ) {
        if (!((Object) this instanceof PlayerModel<?>)) {
            return;
        }
        if (FirstPersonHandController.isActive()) {
            return;
        }
        if (!(entity instanceof Player player)) {
            return;
        }
        AbstractRace race = RaceManager.getRaceOfPlayer(player);
        if (race == null) {
            return;
        }
        PlayerAppearanceProfile profile = race.getAppearanceProfile();
        if (!profile.hasVisualModifications()) {
            return;
        }

        boolean slim = player instanceof AbstractClientPlayer clientPlayer
                && "slim".equals(clientPlayer.getModelName());
        float baseArmWidth = slim ? 3.0F : 4.0F;

        // 1. Align torso, neck, and shoulders relative to the hips (Y = 12)
        PlayerLimbProfile bodyProfile = profile.getPart(PlayerBodyPart.BODY);
        if (bodyProfile.isModified()) {
            float bodyLengthDelta = bodyProfile.getLengthDelta();
            float bodyWidthDelta = bodyProfile.getWidthDelta();

            if (bodyLengthDelta != 0.0F) {
                this.body.y -= bodyLengthDelta;
                this.head.y -= bodyLengthDelta;
                this.hat.y -= bodyLengthDelta;
                this.rightArm.y -= bodyLengthDelta;
                this.leftArm.y -= bodyLengthDelta;
            }
            if (bodyWidthDelta != 0.0F) {
                float halfWidthShift = bodyWidthDelta * 0.5F;
                this.rightArm.x -= halfWidthShift;
                this.leftArm.x += halfWidthShift;
            }
            if (!profile.replacesBody()) {
                applyScale(this.body, bodyProfile, 8.0F, 12.0F, 4.0F);
            }
        }

        // 2. Head fallback scaling (skipped when using baked custom head geometry)
        if (!profile.replacesHead()) {
            PlayerLimbProfile headProfile = profile.getPart(PlayerBodyPart.HEAD);
            if (headProfile.isModified()) {
                applyScale(this.head, headProfile, 8.0F, 8.0F, 8.0F);
                applyScale(this.hat, headProfile, 8.0F, 8.0F, 8.0F);
            }
        }

        // 3. Arm fallback scaling (skipped when using baked custom arm geometry)
        if (!profile.replacesArms()) {
            PlayerLimbProfile rightArmProfile = profile.getPart(PlayerBodyPart.RIGHT_ARM);
            if (rightArmProfile.isModified()) {
                applyScale(this.rightArm, rightArmProfile, baseArmWidth, 12.0F, 4.0F);
            }
            PlayerLimbProfile leftArmProfile = profile.getPart(PlayerBodyPart.LEFT_ARM);
            if (leftArmProfile.isModified()) {
                applyScale(this.leftArm, leftArmProfile, baseArmWidth, 12.0F, 4.0F);
            }
        }

        // 4. Leg fallback scaling (skipped when using baked custom leg geometry)
        if (!profile.replacesLegs()) {
            PlayerLimbProfile rightLegProfile = profile.getPart(PlayerBodyPart.RIGHT_LEG);
            if (rightLegProfile.isModified()) {
                applyScale(this.rightLeg, rightLegProfile, 4.0F, 12.0F, 4.0F);
            }
            PlayerLimbProfile leftLegProfile = profile.getPart(PlayerBodyPart.LEFT_LEG);
            if (leftLegProfile.isModified()) {
                applyScale(this.leftLeg, leftLegProfile, 4.0F, 12.0F, 4.0F);
            }
        }
    }

    /**
     * Scales a vanilla {@link ModelPart} to match the target pixel deltas of a {@link PlayerLimbProfile}.
     */
    @Unique
    private static void applyScale(
            ModelPart part,
            PlayerLimbProfile limbProfile,
            float baseWidth,
            float baseHeight,
            float baseDepth
    ) {
        part.xScale = PlayerLimbProfile.computeScale(baseWidth, limbProfile.getWidthDelta());
        part.yScale = PlayerLimbProfile.computeScale(baseHeight, limbProfile.getLengthDelta());
        part.zScale = PlayerLimbProfile.computeScale(baseDepth, limbProfile.getDepthDelta());
    }
}