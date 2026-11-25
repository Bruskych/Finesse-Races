package org.bruskych.finesse_races.client.render.player;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.PlayerModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.player.AbstractClientPlayer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.LivingEntityRenderer;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.world.entity.player.PlayerModelPart;

import org.bruskych.finesse_races.gameplay.races.appearance.PlayerAppearanceProfile;
import org.bruskych.finesse_races.gameplay.races.core.AbstractRace;
import org.bruskych.finesse_races.gameplay.races.core.RaceManager;

/**
 * Third-person {@link RenderLayer} responsible for drawing custom baked race body parts
 * (head, torso, legs, arms, and their outer overlays) in place of vanilla parts hidden by
 * {@code PlayerRendererMixin}.
 * <p>
 * Each custom {@link ModelPart} copies its live pose from the parent {@link PlayerModel},
 * automatically inheriting vanilla and modded animations while preserving invisibility
 * translucency, hurt/overlay tinting, and the player's skin layer visibility toggles.
 */
public class RaceLimbsLayer extends RenderLayer<AbstractClientPlayer, PlayerModel<AbstractClientPlayer>> {

    private final boolean slim;

    /**
     * Creates a new race limbs render layer bound to a {@link PlayerRenderer}.
     *
     * @param renderer the parent player renderer
     * @param slim     {@code true} if attached to the slim (Alex) player renderer, {@code false} for classic (Steve)
     */
    public RaceLimbsLayer(
            RenderLayerParent<AbstractClientPlayer, PlayerModel<AbstractClientPlayer>> renderer,
            boolean slim
    ) {
        super(renderer);
        this.slim = slim;
    }

    /**
     * Renders all replaced race body parts for the player if their race profile defines custom
     * geometry and matching textures are available.
     *
     * @param poseStack       the current render {@link PoseStack}
     * @param bufferSource    the buffer source used to obtain translucent entity consumers
     * @param packedLight     packed block/sky lightmap coordinates
     * @param player          the client player being rendered
     * @param limbSwing       accumulated limb swing animation progress
     * @param limbSwingAmount limb swing amplitude
     * @param partialTick     frame interpolation factor between ticks
     * @param ageInTicks      entity age in ticks (including partial tick)
     * @param netHeadYaw      interpolated head yaw relative to body yaw
     * @param headPitch       interpolated head pitch
     */
    @Override
    public void render(
            PoseStack poseStack,
            MultiBufferSource bufferSource,
            int packedLight,
            AbstractClientPlayer player,
            float limbSwing,
            float limbSwingAmount,
            float partialTick,
            float ageInTicks,
            float netHeadYaw,
            float headPitch
    ) {
        if (player.isSpectator()) {
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

        // Mirror vanilla visibility rules: fully hidden when invisible, or 15% alpha for teammates/spectators
        boolean visible = !player.isInvisible();
        boolean semiVisibleToViewer = !visible && !player.isInvisibleTo(Minecraft.getInstance().player);
        if (!visible && !semiVisibleToViewer) {
            return;
        }

        float alpha = semiVisibleToViewer ? 0.15F : 1.0F;
        int overlayCoords = LivingEntityRenderer.getOverlayCoords(player, 0.0F);

        PlayerModel<AbstractClientPlayer> parentModel = this.getParentModel();
        RaceLimbGeometryBuilder.BakedRaceLimbs baked = RaceModelCache.getOrBuild(race, this.slim);
        RaceSkinResolver.ResolvedLimbTextures textures = RaceSkinResolver.resolve(player, race, this.slim);

        // Head base and hat overlay
        if (profile.replacesHead() && textures.headTexture() != null) {
            VertexConsumer headConsumer = bufferSource.getBuffer(RenderType.entityTranslucent(textures.headTexture()));
            renderSyncedPart(baked.head(), parentModel.head, poseStack, headConsumer, packedLight, overlayCoords, alpha);
            if (player.isModelPartShown(PlayerModelPart.HAT)) {
                renderSyncedPart(baked.hat(), parentModel.hat, poseStack, headConsumer, packedLight, overlayCoords, alpha);
            }
        }

        // Torso base and jacket overlay
        if (profile.replacesBody() && textures.bodyTexture() != null) {
            VertexConsumer bodyConsumer = bufferSource.getBuffer(RenderType.entityTranslucent(textures.bodyTexture()));
            renderSyncedPart(baked.body(), parentModel.body, poseStack, bodyConsumer, packedLight, overlayCoords, alpha);
            if (player.isModelPartShown(PlayerModelPart.JACKET)) {
                renderSyncedPart(baked.jacket(), parentModel.jacket, poseStack, bodyConsumer, packedLight, overlayCoords, alpha);
            }
        }

        // Legs base and pants overlays
        if (profile.replacesLegs() && textures.legsTexture() != null) {
            VertexConsumer legsConsumer = bufferSource.getBuffer(RenderType.entityTranslucent(textures.legsTexture()));
            renderSyncedPart(baked.rightLeg(), parentModel.rightLeg, poseStack, legsConsumer, packedLight, overlayCoords, alpha);
            renderSyncedPart(baked.leftLeg(), parentModel.leftLeg, poseStack, legsConsumer, packedLight, overlayCoords, alpha);

            if (player.isModelPartShown(PlayerModelPart.RIGHT_PANTS_LEG)) {
                renderSyncedPart(baked.rightPants(), parentModel.rightPants, poseStack, legsConsumer, packedLight, overlayCoords, alpha);
            }
            if (player.isModelPartShown(PlayerModelPart.LEFT_PANTS_LEG)) {
                renderSyncedPart(baked.leftPants(), parentModel.leftPants, poseStack, legsConsumer, packedLight, overlayCoords, alpha);
            }
        }

        // Arms base and sleeve overlays
        if (profile.replacesArms() && textures.armsTexture() != null) {
            VertexConsumer armsConsumer = bufferSource.getBuffer(RenderType.entityTranslucent(textures.armsTexture()));
            renderSyncedPart(baked.rightArm(), parentModel.rightArm, poseStack, armsConsumer, packedLight, overlayCoords, alpha);
            renderSyncedPart(baked.leftArm(), parentModel.leftArm, poseStack, armsConsumer, packedLight, overlayCoords, alpha);

            if (player.isModelPartShown(PlayerModelPart.RIGHT_SLEEVE)) {
                renderSyncedPart(baked.rightSleeve(), parentModel.rightSleeve, poseStack, armsConsumer, packedLight, overlayCoords, alpha);
            }
            if (player.isModelPartShown(PlayerModelPart.LEFT_SLEEVE)) {
                renderSyncedPart(baked.leftSleeve(), parentModel.leftSleeve, poseStack, armsConsumer, packedLight, overlayCoords, alpha);
            }
        }
    }

    /**
     * Copies position, rotation, and scale from the animated vanilla part onto the custom baked
     * part and renders it with the provided lighting, overlay, and opacity.
     *
     * @param customPart     the baked custom {@link ModelPart} to render; skipped if {@code null}
     * @param vanillaSource  the animated vanilla {@link ModelPart} providing the current pose
     * @param poseStack      the active {@link PoseStack}
     * @param vertexConsumer the target vertex consumer bound to the part's texture
     * @param packedLight    packed lightmap coordinates
     * @param overlayCoords  packed overlay coordinates (e.g., hurt flash)
     * @param alpha          opacity multiplier ({@code 1.0F} normal, {@code 0.15F} semi-invisible)
     */
    private static void renderSyncedPart(
            ModelPart customPart,
            ModelPart vanillaSource,
            PoseStack poseStack,
            VertexConsumer vertexConsumer,
            int packedLight,
            int overlayCoords,
            float alpha
    ) {
        if (customPart == null) {
            return;
        }
        customPart.copyFrom(vanillaSource);
        customPart.render(poseStack, vertexConsumer, packedLight, overlayCoords, 1.0F, 1.0F, 1.0F, alpha);
    }
}