package org.bruskych.finesse_races.client.render.player;

import com.mojang.logging.LogUtils;
import net.minecraft.client.Minecraft;
import net.minecraft.client.player.AbstractClientPlayer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.resources.ResourceManager;
import org.slf4j.Logger;

import org.bruskych.finesse_races.core.FinesseRaces;
import org.bruskych.finesse_races.gameplay.races.appearance.PlayerAppearanceProfile;
import org.bruskych.finesse_races.gameplay.races.core.AbstractRace;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

/**
 * Resolves and caches race body part textures per player.
 * Lookup order: {@code custom/<uuid>/<part>.png} -> {@code vanilla/<skin>/<part>.png} -> {@code default/<part>.png}.
 * Returns {@code null} when missing so renderers can fall back to vanilla parts.
 */
public final class RaceSkinResolver {

    private static final Logger LOGGER = LogUtils.getLogger();
    private static final String VANILLA_SKIN_PREFIX = "textures/entity/player/";
    private static final String TEXTURE_BASE_PATH = "textures/entity/race/";

    // Per-player texture resolution cache, invalidated on race/skin/slim change or resource reload.
    private static final Map<UUID, CachedSkinEntry> PLAYER_CACHE = new HashMap<>();
    // Tracks missing fallback locations already logged to prevent console spam.
    private static final Set<ResourceLocation> WARNED_MISSING = new HashSet<>();

    private RaceSkinResolver() {
    }

    /**
     * Resolved textures for replaceable body part groups ({@code null} if not replaced or missing).
     */
    public record ResolvedLimbTextures(
            ResourceLocation headTexture,
            ResourceLocation bodyTexture,
            ResourceLocation legsTexture,
            ResourceLocation armsTexture
    ) {
    }

    /**
     * Cache entry tying resolved textures to the exact race, player skin location, and arm model variant.
     */
    private record CachedSkinEntry(
            String raceId,
            ResourceLocation skinLocation,
            boolean slim,
            ResolvedLimbTextures textures
    ) {
        /**
         * Returns {@code true} if this cached entry is still valid for the given player state.
         */
        boolean matches(String raceId, ResourceLocation skinLocation, boolean slim) {
            return this.slim == slim
                    && this.raceId.equals(raceId)
                    && this.skinLocation.equals(skinLocation);
        }
    }

    /**
     * Resolves or returns cached custom textures for the player's current race and arm model.
     */
    public static ResolvedLimbTextures resolve(AbstractClientPlayer player, AbstractRace race, boolean slim) {
        UUID playerUuid = player.getUUID();
        String raceId = race.getId();
        ResourceLocation skinLocation = player.getSkinTextureLocation();

        CachedSkinEntry cached = PLAYER_CACHE.get(playerUuid);
        if (cached != null && cached.matches(raceId, skinLocation, slim)) {
            return cached.textures();
        }

        PlayerAppearanceProfile profile = race.getAppearanceProfile();
        ResourceManager resourceManager = Minecraft.getInstance().getResourceManager();
        String vanillaSkinName = extractVanillaSkinName(skinLocation);
        String armsPartName = slim ? "arms_slim" : "arms_wide";

        ResourceLocation headTexture = profile.replacesHead()
                ? resolvePartTexture(resourceManager, raceId, playerUuid, vanillaSkinName, "head")
                : null;
        ResourceLocation bodyTexture = profile.replacesBody()
                ? resolvePartTexture(resourceManager, raceId, playerUuid, vanillaSkinName, "body")
                : null;
        ResourceLocation legsTexture = profile.replacesLegs()
                ? resolvePartTexture(resourceManager, raceId, playerUuid, vanillaSkinName, "legs")
                : null;
        ResourceLocation armsTexture = profile.replacesArms()
                ? resolvePartTexture(resourceManager, raceId, playerUuid, vanillaSkinName, armsPartName)
                : null;

        ResolvedLimbTextures resolved = new ResolvedLimbTextures(headTexture, bodyTexture, legsTexture, armsTexture);
        PLAYER_CACHE.put(playerUuid, new CachedSkinEntry(raceId, skinLocation, slim, resolved));
        return resolved;
    }

    /**
     * Finds the first existing texture along the priority chain, or {@code null} if none exist.
     */
    private static ResourceLocation resolvePartTexture(
            ResourceManager resourceManager,
            String raceId,
            UUID playerUuid,
            String vanillaSkinName,
            String partName
    ) {
        // TODO: Delete UUID folder system, only - custom/part.png
        // TODO: Create 3 layer for Default texture (ghost effect)

        // 1. Player-specific override by UUID
        String uuidString = playerUuid.toString().toLowerCase(Locale.ROOT);
        ResourceLocation playerSpecific = ResourceLocation.fromNamespaceAndPath(
                FinesseRaces.MOD_ID,
                TEXTURE_BASE_PATH + raceId + "/custom/" + uuidString + "/" + partName + ".png"
        );
        if (resourceManager.getResource(playerSpecific).isPresent()) {
            return playerSpecific;
        }

        // 2. Built-in vanilla skin variant match (e.g., vanilla/steve/legs.png, vanilla/alex/arms_slim.png)
        if (vanillaSkinName != null) {
            ResourceLocation vanillaMatched = ResourceLocation.fromNamespaceAndPath(
                    FinesseRaces.MOD_ID,
                    TEXTURE_BASE_PATH + raceId + "/vanilla/" + vanillaSkinName + "/" + partName + ".png"
            );
            if (resourceManager.getResource(vanillaMatched).isPresent()) {
                return vanillaMatched;
            }
        }

        // 3. Race default fallback texture
        ResourceLocation defaultFallback = ResourceLocation.fromNamespaceAndPath(
                FinesseRaces.MOD_ID,
                TEXTURE_BASE_PATH + raceId + "/default/" + partName + ".png"
        );
        if (resourceManager.getResource(defaultFallback).isPresent()) {
            return defaultFallback;
        }

        if (WARNED_MISSING.add(defaultFallback)) {
            LOGGER.warn("[Finesse Races] Missing fallback race texture: {}. Falling back to vanilla body part.", defaultFallback);
        }
        return null;
    }

    /**
     * Extracts the lowercase vanilla skin name from {@code minecraft:textures/entity/player/.../*.png}.
     */
    private static String extractVanillaSkinName(ResourceLocation skinLocation) {
        if (!"minecraft".equals(skinLocation.getNamespace())) {
            return null;
        }

        String path = skinLocation.getPath();
        if (!path.startsWith(VANILLA_SKIN_PREFIX) || !path.endsWith(".png")) {
            return null;
        }

        int lastSlash = path.lastIndexOf('/');
        if (lastSlash < 0 || lastSlash + 1 >= path.length() - 4) {
            return null;
        }

        return path.substring(lastSlash + 1, path.length() - 4).toLowerCase(Locale.ROOT);
    }

    /**
     * Removes a single player's cached textures.
     */
    public static void removePlayer(UUID playerUuid) {
        PLAYER_CACHE.remove(playerUuid);
    }

    /**
     * Clears cached textures and warnings on resource reload.
     */
    public static void clearCache() {
        PLAYER_CACHE.clear();
        WARNED_MISSING.clear();
    }
}