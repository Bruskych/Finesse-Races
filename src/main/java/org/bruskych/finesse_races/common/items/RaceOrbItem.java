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

/**
 * Interactive item that triggers the race selection screen upon right-clicking.
 */
public class RaceOrbItem extends Item {

    public RaceOrbItem(Properties properties) {
        super(properties);
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        ItemStack itemStack = player.getItemInHand(hand);

        // Logic in SERVER
        if (!level.isClientSide && player instanceof ServerPlayer serverPlayer) {
            FRNetwork.sendToPlayer(new OpenRaceScreenS2CPacket(), serverPlayer);
        }

        // Animation
        return InteractionResultHolder.sidedSuccess(itemStack, level.isClientSide());
    }
}