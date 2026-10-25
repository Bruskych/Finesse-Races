package org.bruskych.finesse_races.common.items;

import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

import org.bruskych.finesse_races.common.network.FRNetwork;
import org.bruskych.finesse_races.common.network.OpenRaceScreenS2CPacket;
import org.bruskych.finesse_races.gameplay.conditions.PlayerConditionHandler;
import org.jetbrains.annotations.NotNull;

/**
 * Interactive item that triggers the race selection screen upon right-clicking.
 */
public class RaceOrbItem extends Item {

    public RaceOrbItem(Properties properties) {
        super(properties);
    }

    @Override
    public @NotNull InteractionResultHolder<ItemStack> use(Level level, Player player, @NotNull InteractionHand hand) {
        ItemStack itemStack = player.getItemInHand(hand);

        // Logic in SERVER
        if (!level.isClientSide && player instanceof ServerPlayer serverPlayer) {

            // Enable immortality and drop mob aggro
            PlayerConditionHandler.setSelectingRace(serverPlayer, true);

            // Open the selection GUI
            FRNetwork.sendToPlayer(new OpenRaceScreenS2CPacket(), serverPlayer);
        }

        // Animation
        return InteractionResultHolder.sidedSuccess(itemStack, level.isClientSide());
    }
}