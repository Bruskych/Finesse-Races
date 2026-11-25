package org.bruskych.finesse_races.core.mixin.client;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.model.PlayerModel;
import net.minecraft.world.entity.HumanoidArm;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;

import org.bruskych.finesse_races.client.render.player.ArmTransformCalculator;
import org.bruskych.finesse_races.client.render.player.FirstPersonHandController;
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
 * Aligns third-person held items to customized arm lengths and widths without stretching.
 * Targets {@link PlayerModel} directly because its {@code translateToHand} overrides
 * {@code HumanoidModel#translateToHand} without calling {@code super}.
 */
@Mixin(PlayerModel.class)
public abstract class PlayerModelMixin<T extends LivingEntity> {

    @Shadow @Final private boolean slim;

    @Unique
    private ArmTransformCalculator.HeldItemTransforms finesseRaces$itemTransforms =
            ArmTransformCalculator.HeldItemTransforms.IDENTITY;

    /**
     * Resolves and stores cached third-person held item transforms for the player being posed.
     */
    @Inject(method = "setupAnim", at = @At("TAIL"))
    private void finesseRaces$computeHeldItemTransforms(
            T entity,
            float limbSwing,
            float limbSwingAmount,
            float ageInTicks,
            float netHeadYaw,
            float headPitch,
            CallbackInfo callbackInfo
    ) {
        this.finesseRaces$itemTransforms = ArmTransformCalculator.HeldItemTransforms.IDENTITY;

        if (FirstPersonHandController.isActive() || !(entity instanceof Player player)) {
            return;
        }

        AbstractRace race = RaceManager.getRaceOfPlayer(player);
        if (race == null) {
            return;
        }

        this.finesseRaces$itemTransforms =
                ArmTransformCalculator.getThirdPersonItems(race.getAppearanceProfile(), this.slim);
    }

    /**
     * Cancels arm scaling on the active {@link PoseStack} and shifts the item to the customized wrist.
     */
    @Inject(method = "translateToHand", at = @At("TAIL"))
    private void finesseRaces$alignHeldItemToWrist(
            HumanoidArm side,
            PoseStack poseStack,
            CallbackInfo callbackInfo
    ) {
        if (FirstPersonHandController.isActive()
                || this.finesseRaces$itemTransforms == ArmTransformCalculator.HeldItemTransforms.IDENTITY) {
            return;
        }

        ArmTransformCalculator.HeldItemTransform transform = this.finesseRaces$itemTransforms.forSide(side);
        if (transform.isIdentity()) {
            return;
        }

        if (transform.invScaleX() != 1.0F || transform.invScaleY() != 1.0F || transform.invScaleZ() != 1.0F) {
            poseStack.scale(transform.invScaleX(), transform.invScaleY(), transform.invScaleZ());
        }

        if (transform.offsetX() != 0.0F || transform.offsetY() != 0.0F || transform.offsetZ() != 0.0F) {
            poseStack.translate(transform.offsetX(), transform.offsetY(), transform.offsetZ());
        }
    }
}