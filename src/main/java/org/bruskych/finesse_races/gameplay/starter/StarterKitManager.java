package org.bruskych.finesse_races.gameplay.starter;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.resources.ResourceManager;
import net.minecraft.server.packs.resources.SimpleJsonResourceReloadListener;
import net.minecraft.util.GsonHelper;
import net.minecraft.util.Mth;
import net.minecraft.util.profiling.ProfilerFiller;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.common.crafting.CraftingHelper;
import net.minecraftforge.registries.ForgeRegistries;

import org.bruskych.finesse_races.core.FinesseRaces;
import org.bruskych.finesse_races.gameplay.races.core.AbstractRace;
import org.bruskych.finesse_races.gameplay.races.core.RaceManager;

import org.jetbrains.annotations.NotNull;
import java.util.HashMap;
import java.util.Map;

/**
 * Manages the loading of starter kits from datapack JSON files.
 * Automatically scans the "data/[namespace]/starter_kits/" directory,
 * validates item stack sizes, potion effect limits, and handles malformed NBT safely.
 */
public class StarterKitManager extends SimpleJsonResourceReloadListener {

    // Configures GSON to parse JSON files nicely without altering special characters
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().disableHtmlEscaping().create();

    // Singleton instance used to register this listener to the server's resource manager
    public static final StarterKitManager INSTANCE = new StarterKitManager();

    // Vanilla limits for potion effects
    private static final int MIN_AMPLIFIER = 0;
    private static final int MAX_AMPLIFIER = 255;
    private static final int MAX_DURATION_TICKS = 20_000_000;

    /**
     * Initializes the manager and tells Minecraft to look for JSON files
     * inside the "starter_kits" folder within datapacks.
     */
    public StarterKitManager() {
        super(GSON, "starter_kits");
    }

    /**
     * The core method called by Minecraft during world load or when the /reload command is used.
     * Processes all found JSON files and assigns the parsed starter kits to their respective races.
     *
     * @param objectMap       a map containing the file path (ResourceLocation) and the parsed JSON content
     * @param resourceManager the server's resource manager
     * @param profiler        the profiler used for tracking performance (not heavily used here)
     */
    @Override
    protected void apply(
            @NotNull Map<ResourceLocation, JsonElement> objectMap,
            @NotNull ResourceManager resourceManager,
            @NotNull ProfilerFiller profiler
    ) {
        // Step 1: Reset all races to have an empty starter kit before loading new ones
        for (AbstractRace race : RaceManager.getAllRaces()) {
            race.setStarterKit(RaceStarterKit.EMPTY);
        }

        int loadedCount = 0;

        // Step 2: Iterate through every JSON file found in the "starter_kits" folder
        for (Map.Entry<ResourceLocation, JsonElement> entry : objectMap.entrySet()) {
            ResourceLocation fileId = entry.getKey();

            // Protection against fully empty or non-object JSON files
            if (!entry.getValue().isJsonObject()) {
                continue;
            }

            try {
                JsonObject root = entry.getValue().getAsJsonObject();

                // EXTRACT RACE ID FROM THE FILENAME (e.g., "alien.json" -> "alien")
                String raceId = fileId.getPath();
                AbstractRace race = RaceManager.getRace(raceId);

                // If the filename doesn't match any registered race in the mod, skip it
                if (race == null) {
                    FinesseRaces.LOGGER.warn("Starter kit file '{}.json' does not match any registered race!", raceId);
                    continue;
                }

                RaceStarterKit kit = new RaceStarterKit();

                // Step 3: Parse the "offhand" item safely
                if (root.has("offhand") && root.get("offhand").isJsonObject()) {
                    ItemStack offhandStack = parseSafeItemStack(root.getAsJsonObject("offhand"), "offhand", fileId);
                    if (!offhandStack.isEmpty()) {
                        kit.setOffhandItem(offhandStack);
                    }
                }

                // Step 4: Parse the "hotbar" items safely
                if (root.has("hotbar") && root.get("hotbar").isJsonArray()) {
                    JsonArray hotbarArray = root.getAsJsonArray("hotbar");
                    for (JsonElement element : hotbarArray) {
                        if (!element.isJsonObject()) continue;
                        JsonObject slotObj = element.getAsJsonObject();

                        int slot = GsonHelper.getAsInt(slotObj, "slot", -1);
                        if (slot >= 0 && slot < RaceStarterKit.HOTBAR_SIZE) {

                            if (!kit.getHotbarItem(slot).isEmpty()) {
                                FinesseRaces.LOGGER.warn(
                                        "Starter kit '{}' defines multiple items for hotbar slot {}. Keeping the first item and skipping duplicates.",
                                        fileId, slot
                                );
                                continue;
                            }
                            ItemStack hotbarStack = parseSafeItemStack(slotObj, "hotbar slot " + slot, fileId);
                            if (!hotbarStack.isEmpty()) {
                                kit.setHotbarItem(slot, hotbarStack);
                            }
                        } else {
                            FinesseRaces.LOGGER.warn("Invalid hotbar slot '{}' in starter kit '{}'. Must be between 0 and 8.", slot, fileId);
                        }
                    }
                }

                // Step 5: Parse the "armor" items safely
                if (root.has("armor") && root.get("armor").isJsonObject()) {
                    JsonObject armorObj = root.getAsJsonObject("armor");
                    if (armorObj.has("head") && armorObj.get("head").isJsonObject()) {
                        kit.setHeadItem(parseSafeItemStack(armorObj.getAsJsonObject("head"), "armor.head", fileId));
                    }
                    if (armorObj.has("chest") && armorObj.get("chest").isJsonObject()) {
                        kit.setChestItem(parseSafeItemStack(armorObj.getAsJsonObject("chest"), "armor.chest", fileId));
                    }
                    if (armorObj.has("legs") && armorObj.get("legs").isJsonObject()) {
                        kit.setLegsItem(parseSafeItemStack(armorObj.getAsJsonObject("legs"), "armor.legs", fileId));
                    }
                    if (armorObj.has("feet") && armorObj.get("feet").isJsonObject()) {
                        kit.setFeetItem(parseSafeItemStack(armorObj.getAsJsonObject("feet"), "armor.feet", fileId));
                    }
                }

                // Step 6: Parse and clamp "effects" safely
                if (root.has("effects") && root.get("effects").isJsonArray()) {
                    for (JsonElement element : root.getAsJsonArray("effects")) {
                        if (!element.isJsonObject()) continue;
                        parseSafeEffect(element.getAsJsonObject(), kit, fileId);
                    }
                }

                // Step 7: Assign the constructed kit to the race ONLY if it contains valid items or effects
                if (!kit.isEmpty()) {
                    race.setStarterKit(kit);
                    loadedCount++;
                }

            } catch (Exception e) {
                // Catch any fatal JSON structure errors and log them without crashing the game
                FinesseRaces.LOGGER.error("Failed to load starter kit '{}': {}", fileId, e.getMessage());
            }
        }

        // Output the final tally to the server console
        FinesseRaces.LOGGER.info("Loaded {} race starter kits.", loadedCount);
    }

    /**
     * Safely parses an ItemStack from a JSON object, catching malformed NBT or invalid item IDs,
     * and clamping the stack count to the item's maximum allowed stack size (e.g., 64, 16, or 1).
     *
     * @param itemJson    the JSON object representing the item
     * @param slotContext a label indicating where the item is located (for clear warning logs)
     * @param fileId      the datapack file resource location
     * @return a valid ItemStack, or ItemStack.EMPTY if parsing failed
     */
    private ItemStack parseSafeItemStack(JsonObject itemJson, String slotContext, ResourceLocation fileId) {
        try {
            ItemStack stack = CraftingHelper.getItemStack(itemJson, true, true);
            if (stack.isEmpty()) {
                return ItemStack.EMPTY;
            }

            // Clamp stack size to prevent byte overflow (>127) and unstackable item stacking (e.g. 64 swords)
            int maxStack = stack.getMaxStackSize();
            if (stack.getCount() > maxStack) {
                FinesseRaces.LOGGER.warn(
                        "Item '{}' in {} of '{}' has count {}, which exceeds its max stack size of {}. Clamping to {}.",
                        ForgeRegistries.ITEMS.getKey(stack.getItem()), slotContext, fileId, stack.getCount(), maxStack, maxStack
                );
                stack.setCount(maxStack);
            }
            return stack;
        } catch (Exception e) {
            FinesseRaces.LOGGER.error("Invalid item or NBT in {} of starter kit '{}': {}", slotContext, fileId, e.getMessage());
            return ItemStack.EMPTY;
        }
    }

    /**
     * Safely parses a MobEffectInstance from a JSON object and clamps its duration and amplifier
     * to vanilla-safe boundaries (amplifier 0-255, duration -1 or 1..20,000,000).
     *
     * @param effectObj the JSON object representing the effect entry
     * @param kit       the starter kit to add the effect to
     * @param fileId    the datapack file resource location
     */
    private void parseSafeEffect(JsonObject effectObj, RaceStarterKit kit, ResourceLocation fileId) {
        try {
            String effectIdStr = GsonHelper.getAsString(effectObj, "effect");
            ResourceLocation effectLoc = ResourceLocation.tryParse(effectIdStr);

            if (effectLoc == null || !ForgeRegistries.MOB_EFFECTS.containsKey(effectLoc)) {
                FinesseRaces.LOGGER.warn("Unknown potion effect '{}' in starter kit '{}'", effectIdStr, fileId);
                return;
            }

            MobEffect effect = ForgeRegistries.MOB_EFFECTS.getValue(effectLoc);
            if (effect == null) return;

            int rawDuration = GsonHelper.getAsInt(effectObj, "duration", 600);
            int rawAmplifier = GsonHelper.getAsInt(effectObj, "amplifier", 0);

            // Validate duration: allow -1 (infinite duration in 1.20.1), otherwise clamp to [1, MAX_DURATION_TICKS]
            int safeDuration = rawDuration;
            if (rawDuration != MobEffectInstance.INFINITE_DURATION) {
                if (rawDuration <= 0 || rawDuration > MAX_DURATION_TICKS) {
                    safeDuration = Mth.clamp(rawDuration, 1, MAX_DURATION_TICKS);
                    FinesseRaces.LOGGER.warn(
                            "Effect '{}' in '{}' has out-of-bounds duration ({}). Clamped to {}. (Use -1 for infinite duration).",
                            effectIdStr, fileId, rawDuration, safeDuration
                    );
                }
            }

            // Validate amplifier: clamp strictly between 0 (Level I) and 255 (Level 256)
            int safeAmplifier = Mth.clamp(rawAmplifier, MIN_AMPLIFIER, MAX_AMPLIFIER);
            if (rawAmplifier != safeAmplifier) {
                FinesseRaces.LOGGER.warn(
                        "Effect '{}' in '{}' has out-of-bounds amplifier ({}). Clamped to {} (vanilla range is 0-255).",
                        effectIdStr, fileId, rawAmplifier, safeAmplifier
                );
            }

            kit.addEffect(new MobEffectInstance(effect, safeDuration, safeAmplifier));
        } catch (Exception e) {
            FinesseRaces.LOGGER.error("Invalid potion effect entry in starter kit '{}': {}", fileId, e.getMessage());
        }
    }

    /**
     * Collects all current starter kits into a Map.
     * Specifically designed to be used by the SyncStarterKitsS2CPacket for network transmission.
     *
     * @return a map where the key is the Race ID and the value is the RaceStarterKit object
     */
    public static Map<String, RaceStarterKit> getAllKitsForSync() {
        Map<String, RaceStarterKit> map = new HashMap<>();
        for (AbstractRace race : RaceManager.getAllRaces()) {
            map.put(race.getId(), race.getStarterKit());
        }
        return map;
    }
}