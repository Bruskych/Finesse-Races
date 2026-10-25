package org.bruskych.finesse_races.core;

import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.common.ForgeConfigSpec.ConfigValue;
import org.apache.commons.lang3.tuple.Pair;

import java.util.List;

public class FRConfig {

    // Common gameplay settings (shared between client and server)
    public static class Common {

        public final ForgeConfigSpec.BooleanValue enableStarterKits;
        public final ForgeConfigSpec.BooleanValue allowOtherModStarterItems;

        public Common(ForgeConfigSpec.Builder builder) {
            builder.push("starter_kit");

            enableStarterKits = builder
                    .comment("Enable race-specific starter kits from datapacks when choosing a race for the first time.")
                    .define("enableStarterKits", true);

            allowOtherModStarterItems = builder
                    .comment(
                            "If false: completely blocks other mods from giving starter items (books, guides, etc.) on first join.",
                            "If true: allows other mods' starter items, but forces them strictly into the main inventory (slots 9-35),",
                            "preventing them from occupying the hotbar (0-8) or hands."
                    )
                    .define("allowOtherModStarterItems", false);

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