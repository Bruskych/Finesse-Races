package org.bruskych.finesse_races.client.render.player;

import org.bruskych.finesse_races.gameplay.races.appearance.PlayerAppearanceProfile;
import org.bruskych.finesse_races.gameplay.races.core.AbstractRace;

import java.util.HashMap;
import java.util.Map;

/**
 * Client-side cache storing pre-baked custom limb meshes ({@link RaceLimbGeometryBuilder.BakedRaceLimbs})
 * per race for both Classic (wide) and Slim arm model variants.
 * <p>
 * Shared by third-person ({@link RaceLimbsLayer}) and first-person ({@link FirstPersonHandController})
 * renderers to provide O(1) lookups with zero mesh baking or allocations during frame rendering.
 */
public final class RaceModelCache {

    // Baked limb cache for Classic (4px wide arm) player models, keyed by race ID.
    private static final Map<String, RaceLimbGeometryBuilder.BakedRaceLimbs> WIDE_CACHE = new HashMap<>();
    // Baked limb cache for Slim (3px arm) player models, keyed by race ID.
    private static final Map<String, RaceLimbGeometryBuilder.BakedRaceLimbs> SLIM_CACHE = new HashMap<>();

    private RaceModelCache() {
    }

    /**
     * Returns the cached custom limb meshes for the given race and arm model variant,
     * lazily building and storing them on first access.
     *
     * @param race the race whose appearance profile defines the limb geometry
     * @param slim {@code true} for the slim (Alex) arm variant, {@code false} for classic (Steve)
     * @return the baked {@link RaceLimbGeometryBuilder.BakedRaceLimbs} bundle for this race and variant
     */
    public static RaceLimbGeometryBuilder.BakedRaceLimbs getOrBuild(AbstractRace race, boolean slim) {
        Map<String, RaceLimbGeometryBuilder.BakedRaceLimbs> targetCache = slim ? SLIM_CACHE : WIDE_CACHE;
        RaceLimbGeometryBuilder.BakedRaceLimbs cached = targetCache.get(race.getId());
        if (cached != null) {
            return cached;
        }

        PlayerAppearanceProfile profile = race.getAppearanceProfile();
        RaceLimbGeometryBuilder.BakedRaceLimbs built = RaceLimbGeometryBuilder.build(profile, slim);
        targetCache.put(race.getId(), built);
        return built;
    }

    /**
     * Clears all cached meshes so they are rebuilt cleanly on next render (invoked during resource reloads).
     */
    public static void clearCache() {
        WIDE_CACHE.clear();
        SLIM_CACHE.clear();
    }
}