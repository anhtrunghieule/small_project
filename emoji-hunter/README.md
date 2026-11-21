# Capstone Project
Louis Botha,  `louis.botha@tuni.fi`


![](https://paper-attachments.dropboxusercontent.com/s_1587768F31975ABD1005865584762C0616E982B525379A5B7148F47A406BC6CD_1775364233009_image.png)



# Emoji Hunter 
----------
## Overview

In this project you will build Emoji Hunter — a single-player game where the computer hides a collection of emojis on a 10×10 grid and the player tries to find them all in as few guesses as possible.

The game has a graphical interface built with Swing. **That interface is provided for you.** 

Your job is to **implement the logic** that runs behind it.



----------
## Getting the starter kit

Download the starter project and **unzip it into an `emoji-hunter` directory at
the root of your GitLab repository** — that repository is where you build the
project and submit it from.

📦 **[Download emoji-hunter.zip](emoji-hunter.zip)**

```
# from the root of your cloned repository
unzip ~/Downloads/emoji-hunter.zip -d emoji-hunter
```

This creates an `emoji-hunter/` folder in the root of your repo containing
everything listed in [What you are given](#what-you-are-given) below — the `src/`
stubs, the provided GUI, `emojis.csv`, the JUnit jar in `lib/junit/`, the
`Makefile`, and these project documents. `emoji-hunter/` is your **project root**:
run every `make` command from inside it. Commit it as your first commit, then do
your work on the `project` branch.

> 💡 The JUnit jar in `lib/junit/` is the only external dependency, and it is
> already inside the zip — you do not need to download it separately.



----------
## How the game works

The computer places five emojis on a hidden 10×10 grid. Each emoji spans one or more cells depending on its size. The player clicks cells one at a time. A cell either contains part of an emoji — a hit — or is empty — a miss. When every cell of an emoji has been revealed it is fully found. The game ends when all five emojis are found, and a score is calculated based on how few guesses it took.



----------
## What you are given

Once you have unzipped the starter kit into the `emoji-hunter/` directory (see [Getting the starter kit](#getting-the-starter-kit) above), it contains the following files. Read through all of them before writing any code.

| **File**               | **Description**                                                |
| ---------------------- | -------------------------------------------------------------- |
| `EmojiHunterApp.java`  | The complete Swing application. **Do not modify this file.**   |
| `GameEngine.java`      | Connects the GUI to your code. **Do not modify this file.**    |
| `emojis.csv`           | The emoji data file your `EmojiCollection` will read.          |
| `EmojiHunterTest.java` | A JUnit 6 test suite. Run this at any time to check your work. |
| `Emoji.java`           | Stub: record you must implement.                               |
| `Coordinate.java`      | Stub: record you must implement.                               |
| `EmojiCollection.java` | Stub: class you must implement.                                |
| `Board.java`           | Stub: class you must implement — the core of the game.         |
| `ComputerHider.java`   | Stub: class you must implement.                                |
| `HunterSession.java`   | Stub: class you must implement.                                |
| `ScoreBoard.java`      | Stub: class you must implement.                                |


Here is the structure and files of the project.


    emoji-hunter/               ← project root, in the root of your repo
    ├── lib/
    │   └── junit/              ← JUnit console launcher jar
    ├── src/
    │   ├── Emoji.java          ← stub
    │   ├── Coordinate.java     ← stub
    │   ├── EmojiCollection.java← stub
    │   ├── Board.java          ← stub
    │   ├── ComputerHider.java  ← stub
    │   ├── HunterSession.java  ← stub
    │   ├── ScoreBoard.java     ← stub
    │   ├── GameEngine.java     ← provided, do not modify
    │   ├── EmojiHunterApp.java ← provided Swing GUI, do not modify
    │   └── EmojiHunterTest.java← provided, do not modify
    ├── emojis.csv
    ├── SUBMISSION.md
    ├── SETUP.md
    ├── POINTS.md
    └── README.md
    



----------
## What you must implement

You must implement seven classes and records. The method signatures, fields, and Javadoc are already in the stub files — do not change any of them. You may add private helper methods inside any class.

| **Class / Record**        | **Your task**                                                                                                                                                                                        |
| ------------------------- | ---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------- |
| `Emoji` (record)          | Immutable definition of one emoji: name, symbol, and size. A record with three fields and the auto-generated methods that come with it.                                                              |
| `Coordinate` (record)     | Immutable row/column pair. Add one custom instance method: `isValid(int boardSize)` that returns true when both coordinates are within the grid. Used in `ComputerHider` to validate random positions before placement. |
| `EmojiCollection` (class) | Load the `emojis.csv` file line by line, split each line on commas, and build an array of `Emoji` records. Handle `IOException` in a `try/catch` block.                                              |
| `Board` (class)           | The hardest class. Maintain two 10×10 arrays: `hidden` (where emojis are placed) and `revealed` (what the player has uncovered). Implement placement, validation, reveal, and found-detection logic. |
| `ComputerHider` (class)   | Pick random positions and orientations until each emoji fits without overlapping another. Use `Board.isValidPlacement()` to check before placing.                                                    |
| `HunterSession` (class)   | Tie everything together. Process a player guess by calling `reveal()` on the Board and checking whether an emoji is now fully found. Track moves and calculate a score.                              |
| `ScoreBoard` (class)      | Store each completed game as a row in a 2D `int` array: score, moves, emojis found. Save to file and load back using `try/catch` around file operations.                                             |




----------
## The data file

The `emojis.csv` file defines the five emojis used in the game. Each line has three comma-separated fields: name, symbol, size. 


> The size is the number of cells the emoji spans on the board.


    Dragon,🐉,4
    Unicorn,🦄,3
    Pizza,🍕,2
    Guitar,🎸,3
    Rocket,🚀,2

Your `EmojiCollection.loadFromFile()` method reads this file line by line, splits each line on commas and creates one `Emoji` record per line. Wrap the file operations in a `try/catch` block.



----------
## Running the tests

Unit tests are small programs that check whether your code behaves correctly. `EmojiHunterTest.java` is already written for you — you do not need to understand how the tests are written, only how to run them and read the results.

**What is a unit test?**
Each test calls one of your methods with a known input and checks that it returns the expected output. If it does, the test passes. If it does not, the test fails and tells you what it expected versus what your code returned. This is the same feedback loop as running your program manually, except it is faster and repeatable.

**Setup**
The project uses the JUnit 6 console launcher jar in `lib/junit/`, which comes with the starter kit. You do not need to install anything extra.

**Running the tests**

Run the whole suite from a terminal in the `emoji-hunter` project root:

```
make test
```

It compiles your code and runs every test, printing a pass/fail line for each. You can open a terminal inside VS Code or Zed with `` Ctrl+` ``, or use any system terminal.

**Reading the results**

- **Green tick** — the test passed. Your implementation is correct for that case.
- **Red cross** — the test failed. Read the message carefully: it will show you what value was expected and what your code actually returned.



> 💡 Do not try to fix all failures at once. Work through one class at a time, run the tests and fix what is failing before moving on.



----------
## Using the Makefile

If you prefer the terminal, the project includes a small `Makefile` with five commands.

Run these from the project root:

- `make compile` compiles all Java source files from `src/` into the `bin/` folder.
- `make run` compiles the project and starts the Emoji Hunter application.
- `make test` compiles the project and runs the JUnit test suite.
- `make doc` generates Javadoc for all student-implemented classes into the `doc/` folder. Open `doc/index.html` in a browser to browse the documentation.
- `make clean` removes compiled `.class` files from `bin/` and generated documentation from `doc/`.

Notes about `make test`:

- The output is filtered to show a compact test table: each test name, whether it passed or failed, and a short one-line reason for failures.
- Full stack traces are intentionally hidden to keep the output easier to read for beginners.
- The command still returns a failing exit code when tests fail, so it behaves correctly in the terminal.



----------
## Recommended implementation order

Work through the classes in this order. Run the test suite after completing each class before moving on. The tests will tell you what is working and what is not.


1. `Emoji` — a record with three fields. Delete the compact constructor throw to get started. The record's auto-generated methods handle the rest. There is nothing else to write — once you remove the throw, all three tests should pass.
    
2. `Coordinate` — another record. The constructor and accessors are already provided. Your only task is to implement `isValid()`: return `true` when both `row` and `col` satisfy `0 <= value < boardSize`, and `false` otherwise. Four comparisons, one return.
    
3. `EmojiCollection` — start with the constructor: initialise `emojis` as a fixed-size array (length 10 is fine) and set `count` to zero. Then implement `loadFromFile()`: open the file, read line by line, split each line on commas, create an `Emoji` record from the three fields, and store it in the array. Implement `size()`, `getEmoji()`, and `getAll()` last — they each return a single value or a copy of the array.
    
4. `Board` — the hardest class. Work through the methods in this order:
    - **Constructor** first: allocate both arrays and fill `hidden` with `" "` and `revealed` with `false`. Nothing else works until this is done.
    - **`isValidPlacement()`**: loop over the cells the emoji would occupy and return `false` if any fall outside the grid or are already occupied.
    - **`placeEmoji()`**: call `isValidPlacement()` first; if valid, write the symbol into each cell.
    - **`reveal()`**: set `revealed[row][col] = true` and return `hidden[row][col]`.
    - **`isRevealed()` and `isHit()`**: each is a single array lookup.
    - **`isEmojiFound()`**: scan the whole grid for cells that match the symbol; return `false` as soon as you find one that is not yet revealed.
    - **`countFoundEmojis()`**: collect the distinct symbols on the board, then count how many pass `isEmojiFound()`.
    - **`getDisplayGrid()`** and **`reset()`** last — straightforward once the above work.
    
5. `ComputerHider` — implement `placeOne()` first: create a `Random`, loop up to 200 times, pick a random row, column, and orientation, wrap them in a `Coordinate` and call `isValid()`, then call `Board.isValidPlacement()` before committing with `placeEmoji()`. Once `placeOne()` works, `placeAll()` is just a loop over the collection that calls it for each emoji.
    
6. `HunterSession` — start with the constructor (store the board and collection, set counters to zero) and `startGame()` (reset the board, create a `ComputerHider`, call `placeAll()`). Then implement `makeGuess()`: check `isRevealed()` first and return `"ALREADY_REVEALED"` without incrementing moves; otherwise reveal the cell, increment moves, and return `"MISS"`, `"HIT"`, or `"FOUND:symbol"` based on what `reveal()` returns and whether `isEmojiFound()` is now true. The remaining methods (`getMoves()`, `getScore()`, `isGameOver()`, etc.) each return a single field or a simple calculation.
    
7. `ScoreBoard` — start with the constructor: allocate `scores` as a 2D array with `maxGames` rows and 3 columns, set `gameCount` to zero. Implement `recordGame()` next (store the three values, increment `gameCount`, ignore if at capacity), then `getTotalGames()` and `getBestScore()`. Implement `saveToFile()` and `loadFromFile()` last — each iterates over the rows and reads or writes three comma-separated integers per line.



----------
## Rules
- Do not modify any method signature in the stub files.
- Do not modify `GameEngine.java` or `EmojiHunterApp.java`.
- Do not modify `EmojiHunterTest.java`.
- You may add as many private helper methods as you like inside any class.
- You may not add public methods or fields that are not in the stubs.
- The project must compile and run without errors at submission.



----------
## Submission

You have **4 weeks** to complete this capstone project. Your **code** goes to
GitLab; your **hand-in** goes to **Moodle**. See **[SUBMISSION.md](SUBMISSION.md)**
for the full details.

**Commit strategy:**

1. Work on the `project` branch.
2. Commit regularly — at minimum one commit per class you complete. Use clear messages like "Implement Emoji record" or "Fix Board placement logic".
3. The commit history is part of your submission and shows your development process.
4. Push to GitLab before the deadline.

**Hand in on Moodle:**

1. **A working link to your GitLab repository.**
2. **A screenshot of a completed `make test` run.**
3. **Your AI usage PDF** — one PDF with your AI-usage report *and* the prompts you
   used (see [SUBMISSION.md](SUBMISSION.md)).

> ⚠️ **Deadline policy:** a **late submission** scores a **maximum of half the
> points**, no matter how complete it is. A **missing AI usage PDF** caps your
> score at half the points.

**Before you submit:**

- Run `make test` and verify the test suite compiles and runs without errors.
- Test the game manually: start it with `make run`, play through at least one full round, and verify the score is recorded.
- Verify that starting a new game resets the board and move counter correctly.
- Confirm your Moodle hand-in has all three items: repo link, test screenshot, AI usage PDF.


----------
## Assessment

See [POINTS.md](POINTS.md) for the complete grading rubric.

**Quick summary:**

- **80 points** from the test suite (50 tests × 1–3 points each by difficulty)
- **20 points** from playability (three tiers: basic 10 pts, multi-round 5 pts, history 5 pts)
- **100 points total**

Partial credit applies throughout. A working `EmojiCollection` counts even if `Board` is incomplete.



----------
## A note on using AI tools
![](https://paper-attachments.dropboxusercontent.com/s_1587768F31975ABD1005865584762C0616E982B525379A5B7148F47A406BC6CD_1775365757753_image.png)


You are not prohibited from using AI tools. You might be asked to walk through your own code line by line and explain your decisions. If you did not write it yourself, you will not be able to answer those questions.

The safest approach is to write the code yourself, use AI to check your understanding, and make sure you can explain every line before you submit.

**You hand in one AI usage PDF on Moodle** containing both your AI-usage report
(did you use AI, how, and did it help?) and the prompts you used. If you used no
AI tools, say so in the report. See **[SUBMISSION.md](SUBMISSION.md)** for the
full instructions.

> ⚠️ A missing AI usage PDF caps your score at **half the points**.


----------

---

## Quick Start Checklist

- [ ] Read this entire README and POINTS.md
- [ ] Run `make test` to see all 50 tests (they will fail — that's expected)
- [ ] Start with the recommended implementation order: Emoji → Coordinate → EmojiCollection → Board → ComputerHider → HunterSession → ScoreBoard
- [ ] Run tests after completing each class
- [ ] When all tests pass and the game is playable, commit and push **before the deadline**
- [ ] Hand in on Moodle: repo link, a `make test` screenshot, and your AI usage PDF


*Good luck. Start with Emoji. Run the tests. Keep going.*
