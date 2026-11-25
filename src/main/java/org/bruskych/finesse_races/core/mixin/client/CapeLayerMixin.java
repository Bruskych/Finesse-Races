package org.bruskych.finesse_races.core.mixin.client;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.player.AbstractClientPlayer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.layers.CapeLayer;

import org.bruskych.finesse_races.client.render.ModelPartTransformHelper;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

/**
 * Aligns the player cape attachment pivot to customized race torso height and back depth.
 * Vanilla {@link CapeLayer} anchors at model Y = 0 and ignores {@code body.y}.
 */
@Mixin(CapeLayer.class)
public abstract class CapeLayerMixin {

    /**
     * Shifts the cape {@link PoseStack} after {@code pushPose()} to sit flush with customized shoulders and back.
     */
    @Inject(
            method = "render(Lcom/mojang/blaze3d/vertex/PoseStack;Lnet/minecraft/client/renderer/MultiBufferSource;ILnet/minecraft/client/player/AbstractClientPlayer;FFFFFF)V",
            at = @At(
                    value = "INVOKE",
                    target = "Lcom/mojang/blaze3d/vertex/PoseStack;pushPose()V",
                    shift = At.Shift.AFTER
            )
    )
    private void finesseRaces$alignCapeToShoulders(
            PoseStack poseStack,
            MultiBufferSource buffer,
            int packedLight,
            AbstractClientPlayer player,
            float limbSwing,
            float limbSwingAmount,
            float partialTicks,
            float ageInTicks,
            float netHeadYaw,
            float headPitch,
            CallbackInfo callbackInfo
    ) {
        ModelPartTransformHelper.alignTorsoAttachment(player, poseStack);
    }
}