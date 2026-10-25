# Finesse Races — Starter Kits Datapack Guide

This guide explains how to create and customize race-specific starter kits using Datapacks. Starter kits allow modpack creators to give unique starting items, armor, and potion effects to players when they choose a race for the first time.

---

## 1. Directory Structure

To add or modify a starter kit, create a JSON file inside your datapack or mod resources. **The name of the file MUST match the exact race ID.**

```text
data/
└── <namespace>/
    └── starter_kits/
        └── <race_id>.json
```

* `<namespace>`: Your mod ID (`finesse_races`) or your custom datapack namespace.
* `<race_id>`: The ID of the race (e.g., `dwarf`, `alien`, `vampire`, etc.).

> **Example Path:**
> `../dwarf.json`

---

## 2. JSON Structure Overview

A starter kit JSON is completely flexible. You can include all sections, just one, or leave the file completely empty. It consists of four main optional sections:

* `offhand`: The item equipped in the player's offhand slot (Shield, Totem, etc.).
* `hotbar`: A list of item entries assigned to specific hotbar slots (`0` to `8`).
* `armor`: Items automatically equipped into the player's armor slots.
* `effects`: Potion effects granted to the player upon spawning.

```json
{
  "armor": {
    "head": { "item": "minecraft:iron_helmet" }
  },
  "effects": [
    { "effect": "minecraft:speed", "duration": 1200 }
  ],
  "offhand": {
    "item": "minecraft:shield"
  },
  "hotbar": [
    { "slot": 0, "item": "minecraft:iron_pickaxe" }
  ]
}
```

---

## 3. Detailed Parameter Reference

### Root Fields

| Field | Type | Required | Description |
|---|---|---|---|
| `offhand` | Object | Optional | The item object placed into the offhand slot. |
| `hotbar` | Array | Optional | List of item objects for hotbar slots `0` through `8`. |
| `armor` | Object | Optional | Defines items for the `head`, `chest`, `legs`, and `feet` slots. |
| `effects` | Array | Optional | List of potion effects applied to the player. |

### Item Entry Fields (For `hotbar`, `offhand`, and `armor`)

Each item entry supports standard Forge/Vanilla item deserialization:

| Field | Type | Required | Default | Description |
|---|---|---|---|---|
| `slot` | Integer | Required (Hotbar only) | - | Target hotbar slot index from `0` (far left) to `8` (far right). |
| `item` | String | Required | - | Resource Location of the item (e.g., `minecraft:iron_sword`). |
| `count` | Integer | Optional | `1` | Number of items in the stack. Automatically clamped to the item's maximum stack size (e.g., `64` for blocks, `16` for ender pearls, `1` for tools). |
| `nbt` | String/Object | Optional | `{}` | SNBT string or JSON object for enchantments, names, colors, etc. |

### Armor Fields (Inside `armor`)

| Field | Type | Description |
|---|---|---|
| `head` | Object (Item Entry) | Item placed in the Helmet slot. |
| `chest` | Object (Item Entry) | Item placed in the Chestplate slot. |
| `legs` | Object (Item Entry) | Item placed in the Leggings slot. |
| `feet` | Object (Item Entry) | Item placed in the Boots slot. |

### Effect Entry Fields (Inside `effects`)

| Field | Type | Required | Default | Description |
|---|---|---|---|---|
| `effect` | String | Required | - | Resource Location of the potion effect (e.g., `minecraft:speed`). |
| `duration` | Integer | Optional | `600` | Duration in ticks (`20` ticks = 1 second). Set to `-1` for an **infinite** effect. Clamped between `1` and `20,000,000` ticks. |
| `amplifier` | Integer | Optional | `0` | Effect level (`0` = Level I, `1` = Level II, etc.). Automatically clamped to the vanilla range of `0` to `255`. |

---

## 4. Hotbar Slot Mapping

The hotbar index corresponds to the following in-game slots:

```text
[ 0 ] [ 1 ] [ 2 ] [ 3 ] [ 4 ] [ 5 ] [ 6 ] [ 7 ] [ 8 ]
  ^                                               ^
Slot 1                                          Slot 9
(Keyboard 1)                                    (Keyboard 9)
```

---

## 5. Comprehensive Examples

### Example 1: The "Full Feature" Kit (`../vampire.json`)
Demonstrates armor with colored NBT, enchanted weapons, and multiple potion effects:

```json
{
  "effects": [
    {
      "effect": "minecraft:speed",
      "duration": 1200,
      "amplifier": 1
    },
    {
      "effect": "minecraft:night_vision",
      "duration": 6000,
      "amplifier": 0
    }
  ],
  "armor": {
    "head": {
      "item": "minecraft:iron_helmet"
    },
    "chest": {
      "item": "minecraft:leather_chestplate",
      "nbt": "{display:{color:16711680}}"
    },
    "feet": {
      "item": "minecraft:iron_boots",
      "nbt": "{Enchantments:[{id:\"minecraft:feather_falling\",lvl:4s}]}"
    }
  },
  "offhand": {
    "item": "minecraft:totem_of_undying"
  },
  "hotbar": [
    {
      "slot": 0,
      "item": "minecraft:iron_sword",
      "nbt": "{display:{Name:'{\"text\":\"Night Blade\",\"color\":\"dark_red\"}'},Enchantments:[{id:\"minecraft:sharpness\",lvl:3s}]}"
    },
    {
      "slot": 1,
      "item": "minecraft:cooked_beef",
      "count": 32
    }
  ]
}
```

### Example 2: Minimalist Kit (`../dwarf.json`)
You can omit any section you don't need. If you only want to give the player a shield, your file will look like this:

```json
{
  "offhand": {
    "item": "minecraft:shield"
  }
}
```

---

## 6. How Starter Kits Work (Behavior Rules)

1. **Single Issuance Rule:** Starter kits are given only once per player upon their initial race selection.
2. **Race Reset / Orbs:** Changing races later using a Race Change Sphere will NOT re-issue starter kits.
3. **Absolute Priority System:** Finesse Races items have the highest priority for your designated slots. If another mod (like FTB Quests) issues starter items directly into your requested hotbar/offhand slots when the player joins, Finesse Races will safely push those third-party items into the main inventory (slots `9–35`). 
4. **Drop on Floor:** If the player's main inventory (backpack) is completely full and a third-party item is being pushed out, it will be safely dropped on the ground at the player's feet so nothing is ever deleted.
5. **Fault-Tolerant & Flexible Parsing:** The system will never crash if a file is completely empty (`{}`) or missing sections. Furthermore, each slot and effect is parsed independently — if a single item has a typo in its ID or malformed NBT data, only that specific slot is skipped (with a warning in the server log), while the rest of the kit is still granted normally.
6. **Automatic Safety Clamping:** Out-of-bounds values (such as stack sizes over `64` or `1` for weapons, or potion amplifiers above `255`) are automatically clamped to safe vanilla limits to prevent network byte overflow or client crashes.