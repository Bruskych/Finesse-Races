package org.bruskych.finesse_races.core;

import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.common.ForgeConfigSpec.BooleanValue;
import org.apache.commons.lang3.tuple.Pair;

public class FRConfig {

    // Общие игровые настройки (клиент + сервер)
    public static class Common {

        public Common(ForgeConfigSpec.Builder builder) {
            builder.push("general");
            // Здесь будут настройки механик, урона, спавна и т.д.
            builder.pop();
        }
    }

    // Клиентские настройки (визуал, GUI, интерфейсы)
    public static class Client {

        public final BooleanValue enableRaceSelectionOnFirstJoin;
        public final BooleanValue enableCustomRaceHud;

        public Client(ForgeConfigSpec.Builder builder) {
            builder.push("gui");

            enableRaceSelectionOnFirstJoin = builder
                    .comment("Открывать ли экран выбора расы при первом входе игрока в мир")
                    .define("enableRaceSelectionOnFirstJoin", true);

            enableCustomRaceHud = builder
                    .comment("Отображать ли кастомный HUD способностей рас на экране")
                    .define("enableCustomRaceHud", true);

            builder.pop();
        }
    }

    // Спецификации и экземпляры конфигураций
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