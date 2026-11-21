# Grading Rubric

## Overview

- **Total possible**: 100 points
- **From tests**: 80 points (50 tests × 1–3 points each, depending on difficulty)
- **From playability**: 20 points (game must compile and can be played)

---

## Test-Based Points (80 points)

The test suite has 50 tests distributed across seven components. Each test is weighted by implementation difficulty.

**Difficulty levels:**

- **1 point** — trivial or foundational (record accessors, simple state checks)
- **2 points** — moderate algorithmic or I/O complexity
- **3 points** — high complexity (state management, algorithm, interdependencies)

---

### Component Breakdown

| Component          | Tests | Total   | Notes |
|--------------------|-------|---------|-------|
| **Emoji**          | 3     | **3**   | Record with auto-generated methods; trivial once throw is removed. |
| **Coordinate**     | 7     | **10**  | Record structure trivial, but `isValid()` requires boundary logic and edge cases. |
| **Board**          | 20    | **32**  | Largest component. Ranges from construction/state (1 pt) to complex reveal/found-detection (3 pts). |
| **EmojiCollection**| 4     | **6**   | File I/O with CSV parsing. Straightforward but requires exception handling. |
| **HunterSession**  | 8     | **14**  | Orchestrates game logic. Basic accessors (1 pt); complex guess processing (3 pts). |
| **ScoreBoard**     | 6     | **10**  | 2D array + file I/O. Moderate complexity; comparable to EmojiCollection. |
| **ComputerHider**  | 2     | **6**   | Random placement algorithm with validation; essential for game startup. Part of `startGame()` orchestration. |
| **TOTAL TESTS** | **50** | **80** | — |

---

### Detailed Point Allocation by Component

#### Emoji (3 tests, 3 points)
Tests that the record stores and compares its three fields correctly. Students simply remove the compact constructor throw.

- `Emoji · fields are stored correctly` — 1 pt
- `Emoji · two records with identical fields are equal` — 1 pt
- `Emoji · records with different fields are not equal` — 1 pt

---

#### Coordinate (7 tests, 10 points)
Record accessors (trivial) plus boundary checking in `isValid()`.

- `Coordinate · fields are stored correctly` — 1 pt
- `Coordinate · structural equality` — 1 pt
- `Coordinate · isValid — all four corners of a 10×10 board` — 2 pts
- `Coordinate · isValid — false for negative row` — 2 pts
- `Coordinate · isValid — false for negative col` — 2 pts
- `Coordinate · isValid — false when col equals boardSize` — 1 pt
- `Coordinate · isValid — false when row equals boardSize` — 1 pt

---

#### Board (20 tests, 32 points)
The hardest component. Maintains two 2D arrays and implements placement validation, reveal logic, and found-detection.

| Test | Difficulty | Points | Rationale |
|------|------------|--------|-----------|
| `Board · getSize returns the value passed to the constructor` | 1 | 1 | Trivial. |
| `Board · no cell is revealed after construction` | 1 | 1 | Loop test; state must be initialized correctly. |
| `Board · no cell is a hit after construction` | 1 | 1 | Same as above. |
| `Board · isValidPlacement — true for a clear horizontal placement` | 2 | 2 | Requires understanding board boundaries. |
| `Board · isValidPlacement — true for a clear vertical placement` | 2 | 2 | Variant check. |
| `Board · isValidPlacement — false when horizontal goes out of bounds` | 2 | 2 | Boundary logic. |
| `Board · isValidPlacement — false when vertical goes out of bounds` | 2 | 2 | Boundary logic. |
| `Board · placeEmoji — returns true and marks correct cells as hits` | 3 | 3 | Modifies state correctly based on size and orientation. |
| `Board · placeEmoji — returns false for out-of-bounds placement` | 2 | 2 | Validation before mutation. |
| `Board · isValidPlacement — false when a cell is already occupied` | 3 | 3 | Checks internal state (`hidden` array). |
| `Board · reveal — marks the cell revealed and returns its symbol` | 2 | 2 | Dual state array update.  |
| `Board · reveal — returns space for an empty cell` | 2 | 2 | Space char logic. |
| `Board · isEmojiFound — false when only some cells are revealed` | 3 | 3 | Scan + logical condition. |
| `Board · isEmojiFound — true when all cells are revealed` | 3 | 3 | Harder; requires checking all matching cells. |
| `Board · countFoundEmojis — 0 when nothing is found` | 2 | 2 | Iterate and count. |
| `Board · countFoundEmojis — 1 after one emoji is fully revealed` | 3 | 3 | Correct state aggregation. |
| `Board · getDisplayGrid — '?' for unrevealed cells` | 1 | 1 | Mapping logic. |
| `Board · getDisplayGrid — '·' for revealed empty cells` | 2 | 2 | State-dependent rendering. |
| `Board · getDisplayGrid — emoji symbol for revealed hit cells` | 2 | 2 | Full mapping. |
| `Board · reset — clears placements and reveals` | 1 | 1 | Reinitialize both arrays. |

**Subtotal: 32 points**

---

#### EmojiCollection (4 tests, 6 points)
File I/O with CSV parsing. Requires exception handling and string manipulation.

- `EmojiCollection · size is 0 before loading` — 1 pt
- `EmojiCollection · loads correct number of emojis` — 2 pts (file must be read and parsed)
- `EmojiCollection · first emoji has correct fields` — 2 pts (parsing correctness)
- `EmojiCollection · getAll returns array of correct length` — 1 pt

---

#### HunterSession (8 tests, 14 points)
Orchestrates game flow. Ranges from simple accessors to complex guess logic.

- `HunterSession · getMoves is 0 after startGame` — 1 pt
- `HunterSession · isGameOver is false at start` — 1 pt
- `HunterSession · getTotalEmojis matches collection size` — 1 pt
- `HunterSession · getScore is non-negative at start` — 1 pt
- `HunterSession · makeGuess increments move count` — 2 pts (state update)
- `HunterSession · ALREADY_REVEALED on repeated guess` — 2 pts (special case logic)
- `HunterSession · ALREADY_REVEALED does not count as a move` — 2 pts (independent condition)
- `HunterSession · makeGuess returns HIT or MISS or FOUND (not empty)` — 3 pts (requires calling Board methods and checking state)

**Subtotal: 14 points**

---

#### ScoreBoard (6 tests, 10 points)
2D array management and file I/O. Similar complexity to EmojiCollection.

- `ScoreBoard · getTotalGames is 0 after construction` — 1 pt
- `ScoreBoard · recordGame increments getTotalGames` — 2 pts (array mutation and counter)
- `ScoreBoard · getBestScore returns highest recorded score` — 2 pts (scan logic)
- `ScoreBoard · getBestScore is 0 when no games recorded` — 1 pt
- `ScoreBoard · getScoreGrid contains recorded values in correct columns` — 2 pts (2D array access)
- `ScoreBoard · does not exceed capacity` — 2 pts (boundary condition)

**Subtotal: 10 points**

---

#### ComputerHider (2 tests, 6 points)
Random placement with validation. Essential for game initialization. These tests verify the random placement algorithm works correctly.

- `ComputerHider · placeOne successfully places emoji at random valid position` — 3 pts (random algorithm + validation loop)
- `ComputerHider · placeAll places all emojis without overlap` — 3 pts (iteration + fallback for failures)

**Subtotal: 6 points**

---

## Playability Bonus (20 points)

The playability points reward a working, playable game that completes rounds correctly. These are broken into achievable milestones rather than an all-or-nothing assessment.

### Tier 1: Basic Playability (10 points)

Award these 10 points if:

1. The game compiles without errors.
2. The game launches and displays the grid (Swing window opens, board is visible).
3. Cells are clickable and the game responds (clicking updates the display, no crashes).
4. One complete round finishes successfully (all emojis found, game stops accepting guesses, displays "Game Over" or similar).

**Condition**: All four must be true. 

**Score**: 0 or 10 points (binary).

---

### Tier 2: Multiple Rounds (5 points)

Award these 5 additional points if:

1. After a round completes, the player can start a new game (e.g., a "New Game" button is functional).
2. A second complete round plays without crashes or state corruption (board resets correctly, moves/score reset).

**Condition**: Both must be true. 

**Score**: 0 or 5 points (binary).

---

### Tier 3: Score History (5 points)

Award these 5 additional points if:

1. Score history persists across rounds (history table shows results from all completed rounds, not just the current one).
2. History displays correct values (score, moves, emojis found columns match what was earned and recorded).
3. History file (if applicable) is saved and can be loaded on restart (optional but recommended; if implemented, must work correctly).

**Condition**: At least 1 and 2 must be true; 3 is a bonus. 

**Score**: 0 or 5 points (binary).

---

## Final Grade

```
Final Score = (Test Points) + (Playability Points)
            = (0–80) + (0–20)
            = 0–100 points

Breakdown:
  Test Points:    0–80 (50 tests × 1–3 points each)
  Basic Play:     0–10 (one round, correct completion)
  Multi-Round:    0–5  (multiple rounds, state resets correctly)
  History:        0–5  (history persists and displays correctly)
```

---

## Penalties

These caps apply **after** the test and playability points above are totalled.
They are caps on the final score, not point deductions, and the lowest applicable
cap wins.

| Condition | Effect |
|-----------|--------|
| **Late submission** (handed in after the deadline) | Final score capped at **50% of points earned** (maximum half the points) |
| **Missing AI usage PDF** | Final score capped at **50%** |

- The AI usage PDF is a single PDF containing the student's AI-usage report
  **and** the prompts they used, handed in on Moodle — see
  [SUBMISSION.md](SUBMISSION.md).
- A student who used **no** AI tools states that in the report section of the PDF;
  there are then no prompts to include, but the PDF is still required.

---

## Summary Table

| Component | Tests | Test Points | Playability Component | Points |
|-----------|-------|------------|-----|--------|
| Emoji | 3 | 3 | — | — |
| Coordinate | 7 | 10 | — | — |
| Board | 20 | 32 | — | — |
| EmojiCollection | 4 | 6 | — | — |
| HunterSession | 8 | 14 | — | — |
| ScoreBoard | 6 | 10 | — | — |
| ComputerHider | 2 | 6 | — | — |
| — | — | **80** | Basic Playability | **10** |
| — | — | — | Multiple Rounds | **5** |
| — | — | — | Score History | **5** |
| **TOTAL** | **50** | **80** | **Playability** | **20** |
| | | | | **100** |

---

## Notes

- **Partial credit on tests**: A student who completes 40 of the 50 tests passes ~32 points on average (assuming even distribution of difficulty). With playability tiers, they can earn anywhere from 0–20 additional points based on how far they get.
- **Playability scoring philosophy**: The three tiers reward incremental progress. A student with perfect test coverage but a non-functional game receives 80 points. A student with 70% tests + all three playability tiers receives 56 + 20 = 76 points. A student with 40% tests + basic playability receives 32 + 10 = 42 points. This creates flexible incentives.
- **ComputerHider tests**: These are new and test the random placement algorithm directly. They complement the indirect testing that occurs during `HunterSession` tests (which call `startGame()` and trigger emoji placement).
- **Test filtering**: The JUnit output is filtered to hide stack traces for clarity. Run `make test` to see detailed one-line failure reasons.
- **History tracking**: "Correct" history means: (1) results accumulate (not replaced), (2) columns align with score/moves/found, (3) data survives if file I/O is implemented. File persistence is optional but recommended.
