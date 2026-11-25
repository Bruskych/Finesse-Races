package org.bruskych.finesse_races.client.events;

import net.minecraft.client.Minecraft;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.ClientPlayerNetworkEvent;
import net.minecraftforge.client.event.RenderGuiOverlayEvent;
import net.minecraftforge.event.entity.EntityLeaveLevelEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import org.bruskych.finesse_races.client.gui.screens.RaceSelectionScreen;
import org.bruskych.finesse_races.client.render.player.RaceSkinResolver;
import org.bruskych.finesse_races.core.FinesseRaces;

/**
 * Handles strictly client-side Forge events.
 * This class is only loaded on the physical client (Dist.CLIENT),
 * preventing crashes on dedicated servers.
 */
@Mod.EventBusSubscriber(modid = FinesseRaces.MOD_ID, value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class FRClientEvents {

    /**
     * Intercepts the rendering of all HUD overlays (hotbar, health, crosshair, etc.).
     * If the RaceSelectionScreen is open, it cancels the rendering, simulating F1 mode.
     */
    @SubscribeEvent
    public static void onRenderGuiOverlay(RenderGuiOverlayEvent.Pre event) {
        if (Minecraft.getInstance().screen instanceof RaceSelectionScreen) {
            event.setCanceled(true);
        }
    }

    /**
     * Evicts a player's cached race limb textures when they leave the client level
     * (e.g., disconnect or move out of tracking range), preventing unbounded cache growth.
     */
    @SubscribeEvent
    public static void onEntityLeaveLevel(EntityLeaveLevelEvent event) {
        if (event.getLevel().isClientSide() && event.getEntity() instanceof Player player) {
            RaceSkinResolver.removePlayer(player.getUUID());
        }
    }

    /**
     * Clears the entire resolved race texture cache when the local client disconnects
     * from a server or exits a singleplayer world.
     */
    @SubscribeEvent
    public static void onClientLogout(ClientPlayerNetworkEvent.LoggingOut event) {
        RaceSkinResolver.clearCache();
    }
}