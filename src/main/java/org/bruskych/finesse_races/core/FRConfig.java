package org.bruskych.finesse_races.core;

import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.common.ForgeConfigSpec.ConfigValue;
import org.apache.commons.lang3.tuple.Pair;

import java.util.List;

public class FRConfig {

    // Common gameplay settings (shared between client and server)
    public static class Common {

        public final ForgeConfigSpec.BooleanValue enableTelemetry;

        public Common(ForgeConfigSpec.Builder builder) {
            builder.push("telemetry");

            enableTelemetry = builder
                    .comment(
                            "If true, the mod will send anonymous gameplay data (race selection, deaths, progression)",
                            "to the developer's server to help balance the races.",
                            "No personal data or raw UUIDs are collected."
                    )
                    .define("enableTelemetry", true);

            builder.pop();
        }
    }

    // Client-side settings (visuals, GUI, interfaces)
    public static class Client {

        public Client(ForgeConfigSpec.Builder builder) {
            builder.push("gui");
            // TODO: Mechanics, damage, spawning settings will go here
            builder.pop();
        }
    }

    // Specifications and configuration instances
    public static final ForgeConfigSpec COMMON_SPEC;
    public static final Common COMMON;

    public static final ForgeConfigSpec CLIENT_SPEC;
    public static final Client CLIENT;

    static {
        Pair<Common, ForgeConfigSpec> commonSpecPair = new ForgeConfigSpec.Builder().configure(Common::new);
        COMMON_SPEC = commonSpecPair.getRight();
        COMMON = commonSpecPair.getLeft();

        Pair<Client, ForgeConfigSpec> clientSpecPair = new ForgeConfigSpec.Builder().configure(Client::new);
        CLIENT_SPEC = clientSpecPair.getRight();
        CLIENT = clientSpecPair.getLeft();
    }
}