package org.bruskych.finesse_races.gameplay.starter;

import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.item.ItemStack;
import org.bruskych.finesse_races.gameplay.races.core.AbstractRace;

/**
 * Handles the physical distribution of starter kits to players.
 * Ensures items are placed in exact slots and prevents giving kits multiple times.
 */
public class StarterKitHandler {

    // NBT tag key used to remember if a player has already received their starter kit
    public static final String NBT_STARTER_KIT_GIVEN = "finesse_starter_kit_given";

    /**
     * Attempts to give a starter kit when a player selects a race.
     * Checks the persistent NBT flag to ensure the kit is not given more than once.
     *
     * @param player the player receiving the kit
     * @param race   the selected race containing the kit
     */
    public static void tryGiveRaceStarterKit(ServerPlayer player, AbstractRace race) {
        if (player.getPersistentData().getBoolean(NBT_STARTER_KIT_GIVEN)) {
            return;
        }
        player.getPersistentData().putBoolean(NBT_STARTER_KIT_GIVEN, true);
        giveKitToPlayer(player, race);
    }

    /**
     * Forcefully gives the race's starter kit to the player.
     * Used both by the first-join mechanic and admin commands.
     * If a target slot is already occupied, the existing item is pushed to the main inventory.
     *
     * @param player the player receiving the kit
     * @param race   the race containing the kit
     */
    public static void giveKitToPlayer(ServerPlayer player, AbstractRace race) {
        if (race == null || race.getStarterKit() == null || race.getStarterKit().isEmpty()) {
            return;
        }

        Inventory inv = player.getInventory();
        RaceStarterKit kit = race.getStarterKit();

        // 1. Distribute hotbar items
        for (int slot = 0; slot < RaceStarterKit.HOTBAR_SIZE; slot++) {
            ItemStack hotbarItem = kit.getHotbarItem(slot);
            if (!hotbarItem.isEmpty()) {
                ItemStack currentInSlot = inv.getItem(slot);
                if (!currentInSlot.isEmpty()) {
                    insertIntoMainInventoryOnly(player, currentInSlot);
                }
                inv.setItem(slot, hotbarItem);
            }
        }

        // 2. Distribute the offhand item
        ItemStack offhandItem = kit.getOffhandItem();
        if (!offhandItem.isEmpty()) {
            ItemStack currentOffhand = player.getOffhandItem();
            if (!currentOffhand.isEmpty()) {
                insertIntoMainInventoryOnly(player, currentOffhand);
            }
            player.setItemSlot(EquipmentSlot.OFFHAND, offhandItem);
        }

        // 3. Distribute armor
        equipArmorSlot(player, EquipmentSlot.HEAD, kit.getHeadItem());
        equipArmorSlot(player, EquipmentSlot.CHEST, kit.getChestItem());
        equipArmorSlot(player, EquipmentSlot.LEGS, kit.getLegsItem());
        equipArmorSlot(player, EquipmentSlot.FEET, kit.getFeetItem());

        // 4. Apply Potion Effects
        for (MobEffectInstance effect : kit.getEffects()) {
            player.addEffect(new MobEffectInstance(effect));
        }

        // Sync the inventory changes with the client so the player sees the new items
        player.inventoryMenu.broadcastChanges();
    }

    /**
     * Helper method to equip armor safely without deleting existing armor.
     */
    private static void equipArmorSlot(ServerPlayer player, EquipmentSlot slot, ItemStack newArmor) {
        if (!newArmor.isEmpty()) {
            ItemStack currentArmor = player.getItemBySlot(slot);
            if (!currentArmor.isEmpty()) {
                insertIntoMainInventoryOnly(player, currentArmor);
            }
            player.setItemSlot(slot, newArmor);
        }
    }

    /**
     * Pushes an item strictly into slots 9-35 (the main backpack).
     * If the backpack is completely full, the item is dropped on the floor.
     *
     * @param player the player receiving the item
     * @param stack  the item to move or drop
     */
    private static void insertIntoMainInventoryOnly(ServerPlayer player, ItemStack stack) {
        if (stack.isEmpty()) return;

        Inventory inventory = player.getInventory();

        // Find empty slot in the backpack
        for (int i = 9; i < 36; i++) {
            if (inventory.getItem(i).isEmpty()) {
                inventory.setItem(i, stack.copy());
                stack.setCount(0);
                return;
            }
        }

        // If we reach this point, the inventory is full. Drop the item on the ground.
        player.drop(stack.copy(), false);
        stack.setCount(0);
    }
}