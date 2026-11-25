package org.bruskych.finesse_races.client.render.player;

import net.minecraft.client.renderer.entity.LivingEntityRenderer;
import net.minecraft.client.renderer.entity.player.PlayerRenderer;
import net.minecraft.server.packs.resources.ResourceManagerReloadListener;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.client.event.RegisterClientReloadListenersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import org.bruskych.finesse_races.core.FinesseRaces;

/**
 * Registers client-side race rendering layers and resource reload listeners on the MOD event bus.
 */
@Mod.EventBusSubscriber(modid = FinesseRaces.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public final class RaceClientRenderEvents {

    private RaceClientRenderEvents() {
    }

    /**
     * Attaches {@link RaceLimbsLayer} to both Classic ("default") and Slim ("slim") player renderers.
     */
    @SubscribeEvent
    public static void onAddLayers(EntityRenderersEvent.AddLayers event) {
        for (String skinType : event.getSkins()) {
            LivingEntityRenderer<?, ?> renderer = event.getSkin(skinType);
            if (renderer instanceof PlayerRenderer playerRenderer) {
                boolean slim = "slim".equals(skinType);
                playerRenderer.addLayer(new RaceLimbsLayer(playerRenderer, slim));
            }
        }
    }

    /**
     * Clears cached limb meshes and resolved skin textures when resource packs are reloaded (F3 + T).
     */
    @SubscribeEvent
    public static void onRegisterReloadListeners(RegisterClientReloadListenersEvent event) {
        event.registerReloadListener((ResourceManagerReloadListener) resourceManager -> {
            RaceSkinResolver.clearCache();
            RaceModelCache.clearCache();
        });
    }
}