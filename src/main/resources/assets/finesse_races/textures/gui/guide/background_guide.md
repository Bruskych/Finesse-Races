# Finesse Races — GUI & Texture Coordinates Guide

## `background.png` — Nine-Slice Scaling

`background.png` scales dynamically using **Nine-Slice (9-patch)** slicing:

```text
+--------+--------------------+--------+
| Corner |   Top Edge (X)     | Corner |
+--------+--------------------+--------+
| Left   |                    | Right  |
| Edge   |   Center (1×1 px)  | Edge   |
| (Y)    |   Stretched 2D     | (Y)    |
+--------+--------------------+--------+
| Corner |  Bottom Edge (X)   | Corner |
+--------+--------------------+--------+
```

* **Corners:** Rendered unstretched at 1:1 scale. Place borders, bevels, and corner details here.
* **Edges (Top/Bottom & Left/Right):** Stretched along a single axis to span between the corners.
* **Center:** A **1×1 pixel** area stretched across the entire interior fill. Keep it clean/seamless to avoid stretching artifacts.

---

## Race Icon & Plate Coordinates `(X, Y)`

Top-left origin coordinates in the GUI texture sheet:

| Race | Origin `(X, Y)` |
|---|---|
| Aquan | `(35, 34)` |
| Vampire | `(44, 34)` |
| Enderian | `(53, 34)` |
| Demon | `(62, 34)` |
| Avian | `(71, 34)` |
| Alien | `(80, 34)` |
| Dwarf | `(89, 34)` |
| Mechar | `(98, 34)` |
| Undead | `(107, 34)` |
| Verdian | `(116, 34)` |
| Werewolf | `(125, 34)` |

* **Traits plate background:** `(35, 43)`