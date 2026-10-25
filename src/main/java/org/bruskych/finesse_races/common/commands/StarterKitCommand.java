package org.bruskych.finesse_races.common.commands;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.StringArgumentType;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.commands.arguments.EntityArgument;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import org.bruskych.finesse_races.gameplay.races.core.AbstractRace;
import org.bruskych.finesse_races.gameplay.races.core.RaceManager;
import org.bruskych.finesse_races.gameplay.starter.StarterKitHandler;

/**
 * Registers the administrative command for giving a race's starter kit to a player.
 */
public class StarterKitCommand {

    /**
     * Adds {@code /finesse_races starterkit <race> [target]} to the command dispatcher.
     * Players with permission level 2 or higher can give a kit to themselves or another player.
     *
     * @param dispatcher the dispatcher used to register server commands
     */
    public static void register(CommandDispatcher<CommandSourceStack> dispatcher) {
        dispatcher.register(Commands.literal("finesse_races")
                .requires(source -> source.hasPermission(2))
                .then(Commands.literal("starterkit")
                        .then(Commands.argument("race", StringArgumentType.word())

                                // Provide auto-completion (Tab suggestions) for existing races
                                .suggests((context, builder) -> {
                                    for (AbstractRace race : RaceManager.getAllRaces()) {
                                        builder.suggest(race.getId());
                                    }
                                    return builder.buildFuture();
                                })

                                // It gives the kit to the person who typed the command /finesse_races starterkit dwarf
                                .executes(context -> giveKit(
                                        context.getSource(),
                                        StringArgumentType.getString(context, "race"),
                                        context.getSource().getPlayerOrException()
                                ))

                                // It gives the kit to the specified player /finesse_races starterkit dwarf Notch
                                .then(Commands.argument("target", EntityArgument.player())
                                        .executes(context -> giveKit(
                                                context.getSource(),
                                                StringArgumentType.getString(context, "race"),
                                                EntityArgument.getPlayer(context, "target")
                                        ))
                                )
                        )
                )
        );
    }

    /**
     * Looks up the requested race and gives its starter kit to the target player.
     *
     * @param source the command source receiving the result message
     * @param raceId the ID of the race whose kit should be given
     * @param target the player who receives the kit
     * @return {@code 1} when the kit is given, or {@code 0} when the race ID is unknown
     */
    private static int giveKit(CommandSourceStack source, String raceId, ServerPlayer target) {

        // Validate the Race ID
        AbstractRace race = RaceManager.getRace(raceId);
        if (race == null) {
            source.sendFailure(Component.translatable("commands.finesse_races.starterkit.unknown_race", raceId));
            return 0;
        }

        // Deliver the Items
        StarterKitHandler.giveKitToPlayer(target, race);

        // Send Confirmation
        source.sendSuccess(() -> Component.translatable(
                "commands.finesse_races.starterkit.success",
                raceId,
                target.getDisplayName()
        ), true);
        return 1;
    }
}