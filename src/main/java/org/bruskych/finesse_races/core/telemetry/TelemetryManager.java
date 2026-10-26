package org.bruskych.finesse_races.core.telemetry;

import com.google.gson.JsonObject;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.fml.ModList;

import org.bruskych.finesse_races.core.FRConfig;
import org.bruskych.finesse_races.core.FinesseRaces;
import org.bruskych.finesse_races.gameplay.races.core.AbstractRace;
import org.bruskych.finesse_races.gameplay.races.core.RaceManager;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.time.Duration;

/**
 * Manages asynchronous telemetry data collection.
 * Sends anonymous gameplay events to the backend REST API for balancing analysis.
 */
public class TelemetryManager {

    // TODO: Replace with production server URL before release
    private static final String API_URL = "http://localhost:8080/api/v1/telemetry";

    // Reusable HTTP client with a fast timeout to prevent thread hanging
    private static final HttpClient HTTP_CLIENT = HttpClient.newBuilder()
            .connectTimeout(Duration.ofSeconds(5))
            .build();

    /**
     * Collects and sends a specific telemetry event to the backend.
     * Executes asynchronously to avoid blocking the main server thread.
     *
     * @param player    The player involved in the event.
     * @param eventType The category of the event (e.g., "DEATH", "RACE_SELECTED").
     * @param extraData Additional context (e.g., damage source or advancement ID).
     */
    public static void sendEvent(ServerPlayer player, String eventType, String extraData) {

        // Respect player's privacy settings
        if (!FRConfig.COMMON.enableTelemetry.get()) {
            return;
        }

        // Exclude admins/ops to keep data clean from testing
        if (player.hasPermissions(2)) {
            return;
        }

        try {
            AbstractRace currentRace = RaceManager.getRaceOfPlayer(player);
            String raceId = currentRace != null ? currentRace.getId() : "none";

            // Dynamic mod version from Forge environment
            String version = ModList
                    .get()
                    .getModContainerById(FinesseRaces.MOD_ID)
                    .map(container -> container.getModInfo().getVersion().toString())
                    .orElse("unknown");

            // Build the JSON payload
            JsonObject json = new JsonObject();
            json.addProperty("playerHash", hashUUID(player.getStringUUID()));
            json.addProperty("playerName", player.getName().getString());
            json.addProperty("eventType", eventType);
            json.addProperty("raceId", raceId);
            json.addProperty("extraData", extraData);
            json.addProperty("modVersion", version);
            json.addProperty("isDedicated", player.server.isDedicatedServer());

            // Prepare POST request with auth header
            HttpRequest request = HttpRequest
                    .newBuilder()
                    .uri(URI.create(API_URL))
                    .header("Content-Type", "application/json")
                    .header("X-Finesse-Auth", "telemetry-secret-key")
                    .POST(HttpRequest.BodyPublishers.ofString(json.toString()))
                    .build();

            // Send silently in the background
            HTTP_CLIENT.sendAsync(request, HttpResponse.BodyHandlers.discarding())
                    .exceptionally(ex -> {
                        FinesseRaces.LOGGER.debug("Telemetry failed to send: {}", ex.getMessage());
                        return null;
                    });

        } catch (Exception e) {
            FinesseRaces.LOGGER.error("Error building telemetry event", e);
        }
    }

    /**
     * Hashes the player's UUID using SHA-256.
     * Ensures GDPR compliance by anonymizing user data before transmission.
     *
     * @param uuid The raw player UUID string.
     * @return A 64-character hex string hash, or "unknown_hash" on failure.
     */
    private static String hashUUID(String uuid) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hashBytes = digest.digest(uuid.getBytes(StandardCharsets.UTF_8));
            StringBuilder hexString = new StringBuilder(2 * hashBytes.length);

            // Convert bytes to hex format
            for (byte b : hashBytes) {
                String hex = Integer.toHexString(0xff & b);
                if (hex.length() == 1) {
                    hexString.append('0');
                }
                hexString.append(hex);
            }
            return hexString.toString();
        } catch (Exception e) {
            return "unknown_hash";
        }
    }
}