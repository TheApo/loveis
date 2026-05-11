# Love is …

**A puzzle game where *you* write the rules.**
Push the words around the board, build the laws of physics on the fly, and reunite the hero with their love. Inspired by *Baba Is You*, then taken further with new mechanics, a built-in level editor, and a growing library of community-made levels.

🎮 Play it on the web: **<https://theapo.github.io/loveis/>**

---

## ✨ What makes it special

The rules of every level are sitting *on the board* as little text blocks. Move them, swap them, break them apart — and the world changes the moment you do.

- **`HERO IS YOU`** → you control the knight
- Push `WOMAN IS GOAL` next to a heart and suddenly the heart wins you the level
- Stand in front of `WALL IS SOLID` and you're stuck. Push `SOLID` away — walk right through.
- `ROCK IS PUSH` → rocks shove
- `ICE IS SLIP` → step on ice, glide until you hit something
- `FIRE IS HOT` + `SNOWBALL IS MELT` → walking snowmen vanish in the flames
- `KEY IS KEY`, `PADLOCK IS LOCK` → classic, but you can rewrite those rules too

Re-arrange a single word and an entire level changes its mind.

---

## 📦 What's in the box

| | |
|---|---|
| **3 tutorial levels** | Gently introduce the rule-pushing concept with on-screen hints and red arrows |
| **50+ hand-crafted levels** | A full campaign that gradually layers new objects and verbs |
| **80+ community user-levels** | Pulled live from the apo-games.de server — sortable, rateable, always growing |
| **Full level editor** | Paint a board, drop in text blocks, test, share with the world |
| **34 rule-building words** | 13 nouns · 15 verbs/properties · 4 predicates (`IS`, `ISNOT`, `AND`, `ISON`) · 2 conditions (`IF`, `THEN`) |
| **4 terrains** | Grass · Water · Ice · Lava — each interacts with the rules differently |
| **Bilingual** | English & German, switchable in-game |
| **On-screen D-Pad** | Touch controls on Android *and* mobile web browsers |

---

## 🧱 The vocabulary

Build any sentence you want. The game does the rest.

**Nouns (the things in the world)**
`HERO` · `WOMAN` · `ROCK` · `LOVE` · `TREE` · `FIRE` · `SNOWBALL` · `PADLOCK` · `STAR` · `WALL` · `BLUEWALL` · `REDWALL` · `GRAYWALL`

**Verbs / properties (what they become)**
`YOU` · `GOAL` · `ALLGOAL` · `WINABLE` · `SOLID` · `PUSH` · `KILL` · `MELT` · `HOT` · `SLIP` · `SINK` · `KEY` · `LOCK` · `STICKY` · `GHOST`

**Predicates** `IS` · `ISNOT` · `AND` · `ISON`
**Conditions** `IF` · `THEN`

Combine freely. `IF HERO ISON STAR THEN WALL IS YOU` is a perfectly valid thing to write.

---

## 🚀 Where to play

| Platform | How |
|---|---|
| **Web** | Open <https://theapo.github.io/loveis/> — runs in any modern browser, including mobile (compiled with TeaVM, no plugins) |
| **Android** | Native build from the `android/` module |
| **Desktop** | Run the `desktop/` module on Windows, macOS or Linux (LWJGL3) |

Controls:
- **Arrow keys** to move · **R** to restart the level · **U** to undo · **N** for next · **P** for previous
- On Android and mobile web a **D-Pad** appears in the bottom-left corner

---

## 🛠 Build it yourself

The project is a standard **libGDX** multi-module Gradle build.

```bash
# Desktop
./gradlew desktop:run

# Android (with an SDK installed)
./gradlew android:installDebug

# Web (TeaVM) — generates teavm/build/dist/webapp
./gradlew teavm:build
```

Open the result of the web build in a browser, or serve `teavm/build/dist/webapp/` over any static HTTP server.

---

## 🎨 Make your own levels

Hit the **Editor** from the main menu and start painting. Drop in objects, terrains, and text blocks. Press **Test** when your level becomes solvable. Solve it once yourself, and you can upload it — it'll show up in the user-levels list for everyone, rateable by the community.

The editor is the same engine that runs the campaign. Anything the campaign can do, you can do.

---

## 🧰 Tech

- **Engine:** [libGDX](https://libgdx.com/)
- **Web target:** [TeaVM](https://teavm.org/) (Java → JavaScript)
- **Languages in-game:** EN / DE (`assets/i18n/love*.properties`)
- **Online level service:** PHP backend at `apo-games.de`

---

## 💌 Credits

Made by **Dirk Aporius** ([Apo-Games](https://www.apo-games.de/)).
Inspired by Hempuli's *Baba Is You*, then extended with predicates, conditionals, terrain interactions, and an editor + user-level pipeline.

Pull a word. Change the world. Reach your love.
