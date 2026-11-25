# Finesse Races — Custom Limb Textures Guide

Textures live in your resource pack at:
`assets/finesse_races/textures/entity/race/<race_id>/`

## 1. Folders & Priority (3 Folders)

Lookup checks in this order (first match wins; missing files fall back to vanilla):

1. **`custom/<uuid>/<part>.png`** — Per-player override using lowercase dashed UUID (e.g. `069a79f4-44e9-4726-a5be-fca90e38aaf5`). *(Note: in `RaceSkinResolver`, files are resolved inside `custom/<uuid>/`.)*
2. **`vanilla/<skin_name>/<part>.png`** — Matches built-in skins (`steve`, `alex`, `ari`, `efe`, `kai`, `makena`, `noor`, `sunny`, `zuri`), e.g. `vanilla/steve_legs.png`.
3. **`default/<part>.png`** — Default race texture for everyone else.

### File Names (`<part>.png`)
- `head.png` — Head + Hat (when head is replaced)
- `body.png` — Torso + Jacket (when body is replaced)
- `arms_wide.png` / `arms_slim.png` — Arms + Sleeves for Classic (`4px`) / Slim (`3px`) skins
- `legs.png` — Legs + Pants

---

## 2. 64×64 UV Layout & 2nd Layer (Overlay)

All sheets are **64×64** PNGs with alpha transparency.

### Paired Limbs (`arms_wide.png`, `arms_slim.png`, `legs.png`) — 2×2 Grid
| `(0, 0)` Left Base | `(32, 0)` Left Overlay (2nd layer) |
|---|---|
| `(0, 32)` Right Base | `(32, 32)` Right Overlay (2nd layer) |

### Single Parts (`head.png`, `body.png`) — Stacked Vertically
- `(0, 0)` — **Base** (Head / Body)
- `(0, 32)` — **Overlay / 2nd Layer** (Hat / Jacket)

---

## 3. Box-UV Coordinates Inside a Block `(U, V)`

Given cube size **W** (width), **H** (height), **D** (depth) from `(U, V)`:

```text
           [Top: W×D]  [Bottom: W×D]
[Right: D×H][Front: W×H][Left: D×H][Back: W×H]
```

| Face | Top-Left `(x, y)` | Size `(width × height)` |
|---|---|---|
| Top | `(U + D, V)` | `W × D` |
| Bottom | `(U + D + W, V)` | `W × D` |
| Right | `(U, V + D)` | `D × H` |
| Front | `(U + D, V + D)` | `W × H` |
| Left | `(U + D + W, V + D)` | `D × H` |
| Back | `(U + 2D + W, V + D)` | `W × H` |

### Base Sizes + Race Deltas
- **Head:** `8 × 8 × 8`
- **Body:** `8 × 12 × 4`
- **Arm:** `4 × 12 × 4` (wide) or `3 × 12 × 4` (slim)
- **Leg:** `4 × 12 × 4`

Add `widthDelta`, `lengthDelta`, `depthDelta` to get `W`, `H`, `D` (min `1px` per axis). Keep `2W + 2D <= 32` and `H + D <= 32` so faces stay inside their `32×32` area.

## Quick Notes
- **2nd layer** respects vanilla *Skin Customization* toggles (`Hat`, `Jacket`, `Sleeves`, `Pants`). Keep unused overlay pixels transparent.
- Press **`F3 + T`** in-game to reload textures without restarting.