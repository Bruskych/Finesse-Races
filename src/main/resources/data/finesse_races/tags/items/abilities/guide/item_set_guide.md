# Guide: Configuring Items for Ability Displays

In the **Finesse Races** mod, each racial ability features a card in the race selection menu. These cards display item icons that provide visual context for the ability (such as an ender pearl, a water bucket, or a potion).

These items are not hardcoded into the mod. You can easily modify them using a standard **Datapack** through the Minecraft Tags system.

## File Locations

Each ability reads its own specific tag file. In your datapack, the directory path must look like this:
`data/finesse_races/tags/items/abilities/<ability_name>.json`

For example: `data/finesse_races/tags/items/abilities/water_damage.json`

## Proper JSON Formatting

Below is an ideal template of how your JSON file should be structured. Pay close attention to the parameters used:

```json
{
  "replace": true,
  "values": [
    { "id": "minecraft:water_bucket", "required": false },
    { "id": "minecraft:potion", "required": false },
    { "id": "minecraft:suspicious_stew", "required": false },
    { "id": "#minecraft:fishes", "required": false }
  ]
}
```

### 1. The "replace" Parameter

* **`"replace": true`** — **Recommended!** This will completely remove the default items provided by the mod and replace them exclusively with your custom list.
* **`"replace": false`** — Your custom items will be appended to our default list.

### 2. The Safe Wrapper ("required": false)

Always use the object format `{"id": "...", "required": false}` instead of writing the item name in plain quotation marks. 

**Why is this important?** If you specify an item from a third-party mod (for example, `create:diving_helmet`), and a player later removes that mod from their modpack, a tag without `"required": false` will completely break and cause UI parsing errors. The safe wrapper tells the game engine: *"If this item is missing from the registry, simply ignore it and proceed with the rest of the list."*

### 3. Support for Other Tags

You can display not only specific items but also entire item groups by adding a hash `#` before the ID. For example, `{"id": "#minecraft:fishes", "required": false}` will tell the menu to display items from the vanilla fishes tag.

## IMPORTANT WARNING: Visual Limits

The UI system supports any number of items and will automatically cycle through them using a flip animation.

However, it is **strongly advised against** adding too many items to a single ability card.

* **Optimal amount:** 1 to 6 items.

Always select only the most relevant and recognizable items for each ability.