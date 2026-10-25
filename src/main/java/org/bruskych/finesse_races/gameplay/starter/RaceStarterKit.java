package org.bruskych.finesse_races.gameplay.starter;

import net.minecraft.core.NonNullList;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Represents the starter kit data for a specific race.
 * Stores items for the 9 hotbar slots, offhand slot, armor slots, and potion effects.
 * Designed as a safe data container that prevents accidental modification of base items.
 */
public class RaceStarterKit {

    public static final int HOTBAR_SIZE = 9;

    // A globally accessible empty kit to avoid null checks
    public static final RaceStarterKit EMPTY = new RaceStarterKit();

    // NonNullList guarantees that slots are filled with ItemStack.EMPTY instead of null
    private final NonNullList<ItemStack> hotbar = NonNullList.withSize(HOTBAR_SIZE, ItemStack.EMPTY);
    private ItemStack offhand = ItemStack.EMPTY;

    // Armor slots
    private ItemStack head = ItemStack.EMPTY;
    private ItemStack chest = ItemStack.EMPTY;
    private ItemStack legs = ItemStack.EMPTY;
    private ItemStack feet = ItemStack.EMPTY;

    // Potion effects
    private final List<MobEffectInstance> effects = new ArrayList<>();

    /**
     * Default constructor creates an empty kit.
     */
    public RaceStarterKit() {
    }

    /**
     * Sets an item in a specific hotbar slot.
     * Uses .copy() to ensure the kit stores its own unique instance of the item.
     *
     * @param slot  the hotbar index (0-8)
     * @param stack the item to place in the slot
     */
    public void setHotbarItem(int slot, ItemStack stack) {
        if (slot >= 0 && slot < HOTBAR_SIZE) {
            this.hotbar.set(slot, stack.copy());
        }
    }

    /**
     * Retrieves a copy of the item in the specified hotbar slot.
     *
     * @param slot the hotbar index (0-8)
     * @return a safe copy of the item, or ItemStack.EMPTY if the slot is invalid/empty
     */
    public ItemStack getHotbarItem(int slot) {
        if (slot >= 0 && slot < HOTBAR_SIZE) {
            return this.hotbar.get(slot).copy();
        }
        return ItemStack.EMPTY;
    }

    /**
     * Returns an unmodifiable list of all 9 hotbar slots.
     * Empty slots are represented by ItemStack.EMPTY.
     *
     * @return an unmodifiable list of hotbar items
     */
    public List<ItemStack> getHotbarItems() {
        return Collections.unmodifiableList(this.hotbar);
    }

    /**
     * Returns only the populated hotbar slots as a Map of (SlotIndex -> Item).
     * This is highly optimized for rendering in GUIs to avoid looping over empty slots.
     *
     * @return a map containing only slots with items
     */
    public Map<Integer, ItemStack> getPopulatedHotbarSlots() {
        Map<Integer, ItemStack> map = new HashMap<>();
        for (int i = 0; i < HOTBAR_SIZE; i++) {
            ItemStack stack = this.hotbar.get(i);
            if (!stack.isEmpty()) {
                map.put(i, stack.copy());
            }
        }
        return map;
    }

    public void setOffhandItem(ItemStack stack) { this.offhand = stack.copy(); }
    public ItemStack getOffhandItem() { return this.offhand.copy(); }

    public void setHeadItem(ItemStack stack) { this.head = stack.copy(); }
    public ItemStack getHeadItem() { return this.head.copy(); }

    public void setChestItem(ItemStack stack) { this.chest = stack.copy(); }
    public ItemStack getChestItem() { return this.chest.copy(); }

    public void setLegsItem(ItemStack stack) { this.legs = stack.copy(); }
    public ItemStack getLegsItem() { return this.legs.copy(); }

    public void setFeetItem(ItemStack stack) { this.feet = stack.copy(); }
    public ItemStack getFeetItem() { return this.feet.copy(); }

    /**
     * Adds a potion effect to the starter kit.
     *
     * @param effect the potion effect instance
     */
    public void addEffect(MobEffectInstance effect) {
        this.effects.add(new MobEffectInstance(effect));
    }

    /**
     * Returns all potion effects configured for this kit.
     *
     * @return a list of potion effect instances
     */
    public List<MobEffectInstance> getEffects() {
        return Collections.unmodifiableList(this.effects);
    }

    /**
     * Checks if the entire starter kit is completely empty.
     * Now also checks armor and effects.
     *
     * @return true if no items and no effects exist
     */
    public boolean isEmpty() {
        if (!this.offhand.isEmpty()) return false;
        if (!this.head.isEmpty() || !this.chest.isEmpty() || !this.legs.isEmpty() || !this.feet.isEmpty()) return false;
        if (!this.effects.isEmpty()) return false;
        for (ItemStack stack : this.hotbar) {
            if (!stack.isEmpty()) return false;
        }
        return true;
    }

    /**
     * Writes all items and effects in this kit into a network buffer.
     *
     * @param buf the buffer to write to
     */
    public void encode(FriendlyByteBuf buf) {
        for (int i = 0; i < HOTBAR_SIZE; i++) {
            buf.writeItem(this.hotbar.get(i));
        }
        buf.writeItem(this.offhand);

        buf.writeItem(this.head);
        buf.writeItem(this.chest);
        buf.writeItem(this.legs);
        buf.writeItem(this.feet);

        // Serialize effects
        buf.writeInt(this.effects.size());
        for (MobEffectInstance effectInstance : this.effects) {
            ResourceLocation effectId = ForgeRegistries.MOB_EFFECTS.getKey(effectInstance.getEffect());
            buf.writeUtf(effectId != null ? effectId.toString() : "");
            buf.writeInt(effectInstance.getDuration());
            buf.writeInt(effectInstance.getAmplifier());
        }
    }

    /**
     * Reads items and effects from a network buffer and reconstructs a RaceStarterKit.
     *
     * @param buf the buffer to read from
     * @return a newly constructed kit filled with the received data
     */
    public static RaceStarterKit decode(FriendlyByteBuf buf) {
        RaceStarterKit kit = new RaceStarterKit();
        for (int i = 0; i < HOTBAR_SIZE; i++) {
            kit.setHotbarItem(i, buf.readItem());
        }
        kit.setOffhandItem(buf.readItem());

        kit.setHeadItem(buf.readItem());
        kit.setChestItem(buf.readItem());
        kit.setLegsItem(buf.readItem());
        kit.setFeetItem(buf.readItem());

        // Deserialize effects
        int effectCount = buf.readInt();
        for (int i = 0; i < effectCount; i++) {
            String effectIdStr = buf.readUtf();
            int duration = buf.readInt();
            int amplifier = buf.readInt();

            if (!effectIdStr.isEmpty()) {
                ResourceLocation effectLoc = ResourceLocation.tryParse(effectIdStr);
                if (effectLoc != null) {
                    MobEffect effect = ForgeRegistries.MOB_EFFECTS.getValue(effectLoc);
                    if (effect != null) {
                        kit.addEffect(new MobEffectInstance(effect, duration, amplifier));
                    }
                }
            }
        }
        return kit;
    }
}