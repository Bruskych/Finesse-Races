package org.bruskych.finesse_races.core;

import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.common.ForgeConfigSpec.ConfigValue;
import org.apache.commons.lang3.tuple.Pair;

import java.util.List;

public class FRConfig {

    // Common gameplay settings (shared between client and server)
    public static class Common {

        // List of race settings
        public final ConfigValue<List<? extends String>> raceSettings;

        public Common(ForgeConfigSpec.Builder builder) {
            builder.push("races");

            raceSettings = builder
                    .comment(
                            "Race settings: display order in the menu and their difficulty (1-3).",
                            "Format: 'race_id,order,difficulty'",
                            "Example: 'aquatic,1,2' (Aquatic race, 1st in the list, difficulty 2)"
                    )
                    .defineList("raceSettings",
                            // Значения по умолчанию (если конфиг создается впервые)
                            List.of("aquatic,1,1"),
                            // Валидатор: проверяет, что игрок не сломал формат в файле (строка разделена двумя запятыми)
                            obj -> obj instanceof String && ((String) obj).split(",").length == 3
                    );

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