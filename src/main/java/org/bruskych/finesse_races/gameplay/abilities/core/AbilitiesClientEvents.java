package org.bruskych.finesse_races.gameplay.abilities.core;

import com.mojang.blaze3d.shaders.FogShape;
import net.minecraft.client.Camera;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.material.FogType;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.ViewportEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import org.bruskych.finesse_races.core.FinesseRaces;
import org.bruskych.finesse_races.gameplay.abilities.HeatResistanceAbility;
import org.bruskych.finesse_races.gameplay.races.core.AbstractRace;
import org.bruskych.finesse_races.gameplay.races.core.RaceManager;

/**
 * Handles client-side only events, such as rendering, fog, and visual effects.
 */
@Mod.EventBusSubscriber(modid = FinesseRaces.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE, value = Dist.CLIENT)
public class AbilitiesClientEvents {

    /**
     * Modifies the fog rendering distance and color.
     * Used here to improve visibility when submerged in lava for specific races.
     */
    @SubscribeEvent
    public static void onRenderFog(ViewportEvent.RenderFog event) {
        Camera camera = event.getCamera();

        // Check if the camera (player's head) is inside lava
        if (camera.getFluidInCamera() == FogType.LAVA) {

            if (camera.getEntity() instanceof Player player) {
                AbstractRace race = RaceManager.getRaceOfPlayer(player);

                if (race != null) {
                    for (RaceAbility ability : race.getAbilities()) {

                        if (ability instanceof HeatResistanceAbility resistanceAbility) {
                            if (resistanceAbility.hasBetterLavaVisibility()) {

                                // Fog start distance
                                event.setNearPlaneDistance(0.5f);

                                // Fog end distance
                                event.setFarPlaneDistance(6.0f);

                                // Spherical fog shape
                                event.setFogShape(FogShape.SPHERE);

                                // Apply custom distances
                                event.setCanceled(true);
                                return;
                            }
                        }
                    }
                }
            }
        }
    }
}