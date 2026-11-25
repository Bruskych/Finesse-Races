package org.bruskych.finesse_races.core.mixin.client;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.model.PlayerModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.player.AbstractClientPlayer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.player.PlayerRenderer;
import net.minecraft.world.entity.Pose;

import org.bruskych.finesse_races.client.render.player.FirstPersonHandController;
import org.bruskych.finesse_races.client.render.player.RaceSkinResolver;
import org.bruskych.finesse_races.gameplay.races.appearance.PlayerAppearanceProfile;
import org.bruskych.finesse_races.gameplay.races.appearance.SeatedDimensionsCalculator;
import org.bruskych.finesse_races.gameplay.races.core.AbstractRace;
import org.bruskych.finesse_races.gameplay.races.core.RaceManager;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Arrays;

/**
 * Integrates third-person and first-person race appearance adjustments into {@link PlayerRenderer}
 * without canceling or redirecting vanilla methods, preserving compatibility with animation mods.
 */
@Mixin(PlayerRenderer.class)
public abstract class PlayerRendererMixin {

    // Bitmask flags tracking which vanilla ModelParts were hidden during a render call
    @Unique private static final int MASK_HEAD = 1;
    @Unique private static final int MASK_HAT = 1 << 1;
    @Unique private static final int MASK_BODY = 1 << 2;
    @Unique private static final int MASK_JACKET = 1 << 3;
    @Unique private static final int MASK_LEFT_ARM = 1 << 4;
    @Unique private static final int MASK_RIGHT_ARM = 1 << 5;
    @Unique private static final int MASK_LEFT_SLEEVE = 1 << 6;
    @Unique private static final int MASK_RIGHT_SLEEVE = 1 << 7;
    @Unique private static final int MASK_LEFT_LEG = 1 << 8;
    @Unique private static final int MASK_RIGHT_LEG = 1 << 9;
    @Unique private static final int MASK_LEFT_PANTS = 1 << 10;
    @Unique private static final int MASK_RIGHT_PANTS = 1 << 11;

    // Re-entrant stack of hidden-part bitmasks for nested player renders (e.g., UI previews).
    @Unique
    private int[] finesseRaces$hiddenMaskStack = new int[4];

    @Unique
    private int finesseRaces$hiddenMaskDepth = 0;

    /**
     * Pushes a clean visibility bitmask frame before a player render begins.
     */
    @Inject(method = "render", at = @At("HEAD"))
    private void finesseRaces$beginRender(
            AbstractClientPlayer player,
            float entityYaw,
            float partialTicks,
            PoseStack poseStack,
            MultiBufferSource buffer,
            int packedLight,
            CallbackInfo callbackInfo
    ) {
        if (this.finesseRaces$hiddenMaskDepth == this.finesseRaces$hiddenMaskStack.length) {
            this.finesseRaces$hiddenMaskStack = Arrays.copyOf(
                    this.finesseRaces$hiddenMaskStack,
                    this.finesseRaces$hiddenMaskStack.length * 2
            );
        }
        this.finesseRaces$hiddenMaskStack[this.finesseRaces$hiddenMaskDepth++] = 0;
    }

    /**
     * Pops the active bitmask and restores visibility for every vanilla part hidden by this mixin.
     */
    @Inject(method = "render", at = @At("RETURN"))
    private void finesseRaces$endRender(
            AbstractClientPlayer player,
            float entityYaw,
            float partialTicks,
            PoseStack poseStack,
            MultiBufferSource buffer,
            int packedLight,
            CallbackInfo callbackInfo
    ) {
        if (this.finesseRaces$hiddenMaskDepth == 0) {
            return;
        }
        int mask = this.finesseRaces$hiddenMaskStack[--this.finesseRaces$hiddenMaskDepth];
        if (mask == 0) {
            return;
        }

        PlayerRenderer self = (PlayerRenderer) (Object) this;
        PlayerModel<AbstractClientPlayer> model = self.getModel();

        if ((mask & MASK_HEAD) != 0) model.head.visible = true;
        if ((mask & MASK_HAT) != 0) model.hat.visible = true;
        if ((mask & MASK_BODY) != 0) model.body.visible = true;
        if ((mask & MASK_JACKET) != 0) model.jacket.visible = true;
        if ((mask & MASK_LEFT_ARM) != 0) model.leftArm.visible = true;
        if ((mask & MASK_RIGHT_ARM) != 0) model.rightArm.visible = true;
        if ((mask & MASK_LEFT_SLEEVE) != 0) model.leftSleeve.visible = true;
        if ((mask & MASK_RIGHT_SLEEVE) != 0) model.rightSleeve.visible = true;
        if ((mask & MASK_LEFT_LEG) != 0) model.leftLeg.visible = true;
        if ((mask & MASK_RIGHT_LEG) != 0) model.rightLeg.visible = true;
        if ((mask & MASK_LEFT_PANTS) != 0) model.leftPants.visible = true;
        if ((mask & MASK_RIGHT_PANTS) != 0) model.rightPants.visible = true;
    }

    /**
     * Marks entry into first-person hand rendering so third-person mixins skip camera hands.
     */
    @Inject(method = "renderHand", at = @At("HEAD"))
    private void finesseRaces$enterFirstPersonHand(
            PoseStack poseStack,
            MultiBufferSource buffer,
            int combinedLight,
            AbstractClientPlayer player,
            ModelPart rendererArm,
            ModelPart rendererArmwear,
            CallbackInfo callbackInfo
    ) {
        FirstPersonHandController.enter();
    }

    /**
     * Prepares custom geometry or fallback scaling right before vanilla renders the first-person arm.
     */
    @Inject(
            method = "renderHand",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/client/model/geom/ModelPart;render(Lcom/mojang/blaze3d/vertex/PoseStack;Lcom/mojang/blaze3d/vertex/VertexConsumer;II)V",
                    ordinal = 0
            )
    )
    private void finesseRaces$prepareFirstPersonArm(
            PoseStack poseStack,
            MultiBufferSource buffer,
            int combinedLight,
            AbstractClientPlayer player,
            ModelPart rendererArm,
            ModelPart rendererArmwear,
            CallbackInfo callbackInfo
    ) {
        FirstPersonHandController.prepareArm((PlayerRenderer) (Object) this, player, rendererArm, rendererArmwear);
    }

    /**
     * Renders the custom first-person arm (if replaced), restores vanilla arm state, and exits the frame.
     */
    @Inject(method = "renderHand", at = @At("RETURN"))
    private void finesseRaces$finishFirstPersonHand(
            PoseStack poseStack,
            MultiBufferSource buffer,
            int combinedLight,
            AbstractClientPlayer player,
            ModelPart rendererArm,
            ModelPart rendererArmwear,
            CallbackInfo callbackInfo
    ) {
        FirstPersonHandController.finishArm(poseStack, buffer, combinedLight, rendererArm, rendererArmwear);
    }

    /**
     * Shifts the player model vertically so custom leg lengths stay flush with the ground,
     * or aligns seated players by thigh depth rather than leg length.
     */
    @Inject(method = "scale", at = @At("TAIL"))
    private void finesseRaces$applyVerticalGroundingOffset(
            AbstractClientPlayer player,
            PoseStack poseStack,
            float partialTickTime,
            CallbackInfo callbackInfo
    ) {
        Pose pose = player.getPose();
        if (pose != Pose.STANDING && pose != Pose.CROUCHING) {
            return;
        }

        AbstractRace race = RaceManager.getRaceOfPlayer(player);
        if (race == null) {
            return;
        }

        PlayerAppearanceProfile profile = race.getAppearanceProfile();

        // Seated players anchor at the hips (Y = 12), so only thigh thickness shifts them vertically
        if (SeatedDimensionsCalculator.isSeated(player)) {
            float thighOffsetPixels = SeatedDimensionsCalculator.getThighDepthOffsetPixels(profile);
            if (thighOffsetPixels != 0.0F) {
                poseStack.translate(0.0F, -thighOffsetPixels / 16.0F, 0.0F);
            }
            return;
        }

        float verticalOffsetPixels = profile.getVerticalOffsetPixels();
        if (verticalOffsetPixels != 0.0F) {
            poseStack.translate(0.0F, -verticalOffsetPixels / 16.0F, 0.0F);
        }
    }

    /**
     * Hides vanilla body parts that will be drawn by {@code RaceLimbsLayer} and records them
     * in the active bitmask for restoration after rendering.
     */
    @Inject(method = "setModelProperties", at = @At("TAIL"))
    private void finesseRaces$hideReplacedVanillaParts(
            AbstractClientPlayer player,
            CallbackInfo callbackInfo
    ) {
        if (FirstPersonHandController.isActive() || player.isSpectator()) {
            return;
        }

        AbstractRace race = RaceManager.getRaceOfPlayer(player);
        if (race == null) {
            return;
        }

        PlayerAppearanceProfile profile = race.getAppearanceProfile();
        if (!profile.hasAnyReplacedParts()) {
            return;
        }

        boolean slim = "slim".equals(player.getModelName());
        RaceSkinResolver.ResolvedLimbTextures textures = RaceSkinResolver.resolve(player, race, slim);

        PlayerRenderer self = (PlayerRenderer) (Object) this;
        PlayerModel<AbstractClientPlayer> model = self.getModel();

        int hiddenMask = 0;

        if (profile.replacesHead() && textures.headTexture() != null) {
            if (model.head.visible) { model.head.visible = false; hiddenMask |= MASK_HEAD; }
            if (model.hat.visible) { model.hat.visible = false; hiddenMask |= MASK_HAT; }
        }

        if (profile.replacesBody() && textures.bodyTexture() != null) {
            if (model.body.visible) { model.body.visible = false; hiddenMask |= MASK_BODY; }
            if (model.jacket.visible) { model.jacket.visible = false; hiddenMask |= MASK_JACKET; }
        }

        if (profile.replacesArms() && textures.armsTexture() != null) {
            if (model.leftArm.visible) { model.leftArm.visible = false; hiddenMask |= MASK_LEFT_ARM; }
            if (model.rightArm.visible) { model.rightArm.visible = false; hiddenMask |= MASK_RIGHT_ARM; }
            if (model.leftSleeve.visible) { model.leftSleeve.visible = false; hiddenMask |= MASK_LEFT_SLEEVE; }
            if (model.rightSleeve.visible) { model.rightSleeve.visible = false; hiddenMask |= MASK_RIGHT_SLEEVE; }
        }

        if (profile.replacesLegs() && textures.legsTexture() != null) {
            if (model.leftLeg.visible) { model.leftLeg.visible = false; hiddenMask |= MASK_LEFT_LEG; }
            if (model.rightLeg.visible) { model.rightLeg.visible = false; hiddenMask |= MASK_RIGHT_LEG; }
            if (model.leftPants.visible) { model.leftPants.visible = false; hiddenMask |= MASK_LEFT_PANTS; }
            if (model.rightPants.visible) { model.rightPants.visible = false; hiddenMask |= MASK_RIGHT_PANTS; }
        }

        if (hiddenMask != 0 && this.finesseRaces$hiddenMaskDepth > 0) {
            this.finesseRaces$hiddenMaskStack[this.finesseRaces$hiddenMaskDepth - 1] |= hiddenMask;
        }
    }
}