// =============================================================
// DO NOT MODIFY THIS FILE
// =============================================================

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.RegisterExtension;
import org.junit.jupiter.api.extension.TestWatcher;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Test suite for Emoji Hunter.
 *
 * <p><strong>This file is provided — do not modify it.</strong>
 *
 * <p>Run all tests from the Testing panel in VS Code (beaker icon in the
 * left sidebar). A green tick means passing; a red cross means failing.
 * Read the failure message carefully — it shows what was expected and what
 * your code actually returned.
 */
@DisplayName("Emoji Hunter")
class EmojiHunterTest {

    private static final Map<String, Integer> TEST_POINTS = createTestPoints();
    private static final int DISPLAY_TOTAL_POINTS = 80;
    private static final int RAW_TOTAL_POINTS = TEST_POINTS.values().stream()
        .mapToInt(Integer::intValue)
        .sum();
    private static final AtomicInteger EARNED_POINTS = new AtomicInteger();

    @RegisterExtension
    static final ScoreTracker SCORE_TRACKER = new ScoreTracker();

    @AfterAll
    static void printScoreSummary() {
        double normalizedScore = EARNED_POINTS.get() * DISPLAY_TOTAL_POINTS / (double) RAW_TOTAL_POINTS;
        System.out.println();
        System.out.printf("Test score: %.1f/%d%n", normalizedScore, DISPLAY_TOTAL_POINTS);
    }

    private static Map<String, Integer> createTestPoints() {
        Map<String, Integer> points = new LinkedHashMap<>();

        points.put("Emoji · fields are stored correctly", 1);
        points.put("Emoji · two records with identical fields are equal", 1);
        points.put("Emoji · records with different fields are not equal", 1);

        points.put("Coordinate · fields are stored correctly", 1);
        points.put("Coordinate · isValid — all four corners of a 10×10 board", 2);
        points.put("Coordinate · isValid — false for negative row", 2);
        points.put("Coordinate · isValid — false for negative col", 2);
        points.put("Coordinate · isValid — false when col equals boardSize", 1);
        points.put("Coordinate · isValid — false when row equals boardSize", 1);
        points.put("Coordinate · structural equality", 1);

        points.put("Board · getSize returns the value passed to the constructor", 1);
        points.put("Board · no cell is revealed after construction", 1);
        points.put("Board · no cell is a hit after construction", 1);
        points.put("Board · isValidPlacement — true for a clear horizontal placement", 2);
        points.put("Board · isValidPlacement — true for a clear vertical placement", 2);
        points.put("Board · isValidPlacement — false when horizontal goes out of bounds", 2);
        points.put("Board · isValidPlacement — false when vertical goes out of bounds", 2);
        points.put("Board · placeEmoji — returns true and marks correct cells as hits", 3);
        points.put("Board · placeEmoji — returns false for out-of-bounds placement", 2);
        points.put("Board · isValidPlacement — false when a cell is already occupied", 3);
        points.put("Board · reveal — marks the cell revealed and returns its symbol", 2);
        points.put("Board · reveal — returns space for an empty cell", 2);
        points.put("Board · isEmojiFound — false when only some cells are revealed", 3);
        points.put("Board · isEmojiFound — true when all cells are revealed", 3);
        points.put("Board · countFoundEmojis — 0 when nothing is found", 2);
        points.put("Board · countFoundEmojis — 1 after one emoji is fully revealed", 3);
        points.put("Board · getDisplayGrid — '?' for unrevealed cells", 1);
        points.put("Board · getDisplayGrid — '·' for revealed empty cells", 2);
        points.put("Board · getDisplayGrid — emoji symbol for revealed hit cells", 2);
        points.put("Board · reset — clears placements and reveals", 1);

        points.put("EmojiCollection · size is 0 before loading", 1);
        points.put("EmojiCollection · loads correct number of emojis", 2);
        points.put("EmojiCollection · first emoji has correct fields", 2);
        points.put("EmojiCollection · getAll returns array of correct length", 1);

        points.put("HunterSession · getMoves is 0 after startGame", 1);
        points.put("HunterSession · isGameOver is false at start", 1);
        points.put("HunterSession · getTotalEmojis matches collection size", 1);
        points.put("HunterSession · getScore is non-negative at start", 1);
        points.put("HunterSession · makeGuess increments move count", 2);
        points.put("HunterSession · ALREADY_REVEALED on repeated guess", 2);
        points.put("HunterSession · ALREADY_REVEALED does not count as a move", 2);
        points.put("HunterSession · makeGuess returns HIT or MISS or FOUND (not empty)", 3);

        // POINTS.md is internally inconsistent. These entries follow the per-test rows,
        // and the final printed score is normalized to 80 in @AfterAll.
        points.put("ScoreBoard · getTotalGames is 0 after construction", 1);
        points.put("ScoreBoard · recordGame increments getTotalGames", 2);
        points.put("ScoreBoard · getBestScore returns highest recorded score", 2);
        points.put("ScoreBoard · getBestScore is 0 when no games recorded", 1);
        points.put("ScoreBoard · getScoreGrid contains recorded values in correct columns", 2);
        points.put("ScoreBoard · does not exceed capacity", 2);

        points.put("ComputerHider · placeOne successfully places emoji without overlap", 3);
        points.put("ComputerHider · placeAll places all emojis from the collection", 3);

        return points;
    }

    private static final class ScoreTracker implements TestWatcher {
        @Override
        public void testSuccessful(ExtensionContext context) {
            EARNED_POINTS.addAndGet(TEST_POINTS.getOrDefault(context.getDisplayName(), 0));
        }
    }

    // ── Emoji record ──────────────────────────────────────────────────────

    @Test
    @DisplayName("Emoji · fields are stored correctly")
    void emojiFieldsStoredCorrectly() {
        Emoji e = new Emoji("Dragon", "🐉", 4);
        assertEquals("Dragon", e.name());
        assertEquals("🐉",     e.symbol());
        assertEquals(4,        e.size());
    }

    @Test
    @DisplayName("Emoji · two records with identical fields are equal")
    void emojiEquality() {
        Emoji a = new Emoji("Dragon", "🐉", 4);
        Emoji b = new Emoji("Dragon", "🐉", 4);
        assertEquals(a, b);
    }

    @Test
    @DisplayName("Emoji · records with different fields are not equal")
    void emojiInequality() {
        Emoji a = new Emoji("Dragon",  "🐉", 4);
        Emoji b = new Emoji("Unicorn", "🦄", 3);
        assertNotEquals(a, b);
    }

    // ── Coordinate record ─────────────────────────────────────────────────

    @Test
    @DisplayName("Coordinate · fields are stored correctly")
    void coordinateFields() {
        Coordinate c = new Coordinate(3, 7);
        assertEquals(3, c.row());
        assertEquals(7, c.col());
    }

    @Test
    @DisplayName("Coordinate · isValid — all four corners of a 10×10 board")
    void coordinateValidCorners() {
        assertTrue(new Coordinate(0, 0).isValid(10));
        assertTrue(new Coordinate(9, 9).isValid(10));
        assertTrue(new Coordinate(0, 9).isValid(10));
        assertTrue(new Coordinate(9, 0).isValid(10));
    }

    @Test
    @DisplayName("Coordinate · isValid — false for negative row")
    void coordinateInvalidNegativeRow() {
        assertFalse(new Coordinate(-1, 5).isValid(10));
    }

    @Test
    @DisplayName("Coordinate · isValid — false for negative col")
    void coordinateInvalidNegativeCol() {
        assertFalse(new Coordinate(5, -1).isValid(10));
    }

    @Test
    @DisplayName("Coordinate · isValid — false when col equals boardSize")
    void coordinateInvalidColAtBoundary() {
        assertFalse(new Coordinate(0, 10).isValid(10));
    }

    @Test
    @DisplayName("Coordinate · isValid — false when row equals boardSize")
    void coordinateInvalidRowAtBoundary() {
        assertFalse(new Coordinate(10, 0).isValid(10));
    }

    @Test
    @DisplayName("Coordinate · structural equality")
    void coordinateEquality() {
        assertEquals(new Coordinate(2, 5), new Coordinate(2, 5));
        assertNotEquals(new Coordinate(2, 5), new Coordinate(5, 2));
    }

    // ── Board ─────────────────────────────────────────────────────────────

    @Test
    @DisplayName("Board · getSize returns the value passed to the constructor")
    void boardGetSize() {
        assertEquals(10, new Board(10).getSize());
        assertEquals(5,  new Board(5).getSize());
    }

    @Test
    @DisplayName("Board · no cell is revealed after construction")
    void boardInitiallyUnrevealed() {
        Board b = new Board(10);
        for (int r = 0; r < 10; r++)
            for (int c = 0; c < 10; c++)
                assertFalse(b.isRevealed(r, c),
                    "Cell (" + r + "," + c + ") should not be revealed at start");
    }

    @Test
    @DisplayName("Board · no cell is a hit after construction")
    void boardInitiallyNoHits() {
        Board b = new Board(10);
        for (int r = 0; r < 10; r++)
            for (int c = 0; c < 10; c++)
                assertFalse(b.isHit(r, c),
                    "Cell (" + r + "," + c + ") should not be a hit at start");
    }

    @Test
    @DisplayName("Board · isValidPlacement — true for a clear horizontal placement")
    void boardValidHorizontalPlacement() {
        Board b = new Board(10);
        assertTrue(b.isValidPlacement(new Emoji("Dragon", "🐉", 4), 0, 0, true));
    }

    @Test
    @DisplayName("Board · isValidPlacement — true for a clear vertical placement")
    void boardValidVerticalPlacement() {
        Board b = new Board(10);
        assertTrue(b.isValidPlacement(new Emoji("Dragon", "🐉", 4), 0, 0, false));
    }

    @Test
    @DisplayName("Board · isValidPlacement — false when horizontal goes out of bounds")
    void boardInvalidHorizontalOutOfBounds() {
        Board b = new Board(10);
        // size 4 starting at col 8 → cols 8,9,10,11 — last two out of bounds
        assertFalse(b.isValidPlacement(new Emoji("Dragon", "🐉", 4), 0, 8, true));
    }

    @Test
    @DisplayName("Board · isValidPlacement — false when vertical goes out of bounds")
    void boardInvalidVerticalOutOfBounds() {
        Board b = new Board(10);
        // size 4 starting at row 8 → rows 8,9,10,11 — last two out of bounds
        assertFalse(b.isValidPlacement(new Emoji("Dragon", "🐉", 4), 8, 0, false));
    }

    @Test
    @DisplayName("Board · placeEmoji — returns true and marks correct cells as hits")
    void boardPlaceEmojiSetsHits() {
        Board b = new Board(10);
        Emoji e = new Emoji("Dragon", "🐉", 4);
        assertTrue(b.placeEmoji(e, 2, 3, true));
        assertTrue(b.isHit(2, 3));
        assertTrue(b.isHit(2, 4));
        assertTrue(b.isHit(2, 5));
        assertTrue(b.isHit(2, 6));
        assertFalse(b.isHit(2, 2), "Cell before placement should not be a hit");
        assertFalse(b.isHit(2, 7), "Cell after placement should not be a hit");
    }

    @Test
    @DisplayName("Board · placeEmoji — returns false for out-of-bounds placement")
    void boardPlaceEmojiReturnsFalse() {
        Board b = new Board(10);
        assertFalse(b.placeEmoji(new Emoji("Dragon", "🐉", 4), 0, 8, true));
    }

    @Test
    @DisplayName("Board · isValidPlacement — false when a cell is already occupied")
    void boardNoOverlap() {
        Board b = new Board(10);
        b.placeEmoji(new Emoji("Dragon", "🐉", 4), 0, 0, true); // occupies (0,0)–(0,3)
        assertFalse(b.isValidPlacement(new Emoji("Unicorn", "🦄", 3), 0, 2, true));
    }

    @Test
    @DisplayName("Board · reveal — marks the cell revealed and returns its symbol")
    void boardRevealHitCell() {
        Board b = new Board(10);
        b.placeEmoji(new Emoji("Dragon", "🐉", 4), 0, 0, true);
        String ch = b.reveal(0, 0);
        assertEquals("🐉", ch);
        assertTrue(b.isRevealed(0, 0));
    }

    @Test
    @DisplayName("Board · reveal — returns space for an empty cell")
    void boardRevealEmptyCell() {
        Board b = new Board(10);
        assertEquals(" ", b.reveal(5, 5));
        assertTrue(b.isRevealed(5, 5));
    }

    @Test
    @DisplayName("Board · isEmojiFound — false when only some cells are revealed")
    void boardEmojiNotYetFound() {
        Board b = new Board(10);
        b.placeEmoji(new Emoji("Dragon", "🐉", 4), 0, 0, true);
        b.reveal(0, 0);
        b.reveal(0, 1);
        assertFalse(b.isEmojiFound("🐉"));
    }

    @Test
    @DisplayName("Board · isEmojiFound — true when all cells are revealed")
    void boardEmojiFullyFound() {
        Board b = new Board(10);
        b.placeEmoji(new Emoji("Dragon", "🐉", 4), 0, 0, true);
        b.reveal(0, 0); b.reveal(0, 1); b.reveal(0, 2); b.reveal(0, 3);
        assertTrue(b.isEmojiFound("🐉"));
    }

    @Test
    @DisplayName("Board · countFoundEmojis — 0 when nothing is found")
    void boardCountFoundZero() {
        Board b = new Board(10);
        b.placeEmoji(new Emoji("Dragon", "🐉", 4), 0, 0, true);
        assertEquals(0, b.countFoundEmojis());
    }

    @Test
    @DisplayName("Board · countFoundEmojis — 1 after one emoji is fully revealed")
    void boardCountFoundOne() {
        Board b = new Board(10);
        b.placeEmoji(new Emoji("Dragon", "🐉", 2), 0, 0, true);
        b.reveal(0, 0); b.reveal(0, 1);
        assertEquals(1, b.countFoundEmojis());
    }

    @Test
    @DisplayName("Board · getDisplayGrid — '?' for unrevealed cells")
    void boardDisplayGridUnrevealed() {
        Board b     = new Board(10);
        String[][] d = b.getDisplayGrid();
        assertNotNull(d);
        assertEquals("?", d[0][0]);
        assertEquals("?", d[9][9]);
    }

    @Test
    @DisplayName("Board · getDisplayGrid — '·' for revealed empty cells")
    void boardDisplayGridMiss() {
        Board b = new Board(10);
        b.reveal(5, 5);
        assertEquals("·", b.getDisplayGrid()[5][5]);
    }

    @Test
    @DisplayName("Board · getDisplayGrid — emoji symbol for revealed hit cells")
    void boardDisplayGridHit() {
        Board b = new Board(10);
        b.placeEmoji(new Emoji("Dragon", "🐉", 4), 3, 3, true);
        b.reveal(3, 3);
        assertEquals("🐉", b.getDisplayGrid()[3][3]);
    }

    @Test
    @DisplayName("Board · reset — clears placements and reveals")
    void boardReset() {
        Board b = new Board(10);
        b.placeEmoji(new Emoji("Dragon", "🐉", 4), 0, 0, true);
        b.reveal(0, 0);
        b.reset();
        assertFalse(b.isHit(0, 0));
        assertFalse(b.isRevealed(0, 0));
    }

    // ── EmojiCollection ───────────────────────────────────────────────────

    @Test
    @DisplayName("EmojiCollection · size is 0 before loading")
    void collectionEmptyBeforeLoad() {
        assertEquals(0, new EmojiCollection().size());
    }

    @Test
    @DisplayName("EmojiCollection · loads correct number of emojis")
    void collectionLoadsCount() {
        EmojiCollection c = new EmojiCollection();
        c.loadFromFile("emojis.csv");
        assertEquals(5, c.size());
    }

    @Test
    @DisplayName("EmojiCollection · first emoji has correct fields")
    void collectionFirstEmoji() {
        EmojiCollection c = new EmojiCollection();
        c.loadFromFile("emojis.csv");
        Emoji first = c.getEmoji(0);
        assertEquals("Dragon", first.name());
        assertEquals("🐉",     first.symbol());
        assertEquals(4,        first.size());
    }

    @Test
    @DisplayName("EmojiCollection · getAll returns array of correct length")
    void collectionGetAll() {
        EmojiCollection c = new EmojiCollection();
        c.loadFromFile("emojis.csv");
        assertEquals(5, c.getAll().length);
    }

    // ── HunterSession ─────────────────────────────────────────────────────

    private HunterSession makeSession() {
        Board b = new Board(10);
        EmojiCollection col = new EmojiCollection();
        col.loadFromFile("emojis.csv");
        HunterSession s = new HunterSession(b, col);
        s.startGame();
        return s;
    }

    @Test
    @DisplayName("HunterSession · getMoves is 0 after startGame")
    void sessionMovesZeroAtStart() {
        assertEquals(0, makeSession().getMoves());
    }

    @Test
    @DisplayName("HunterSession · isGameOver is false at start")
    void sessionNotOverAtStart() {
        assertFalse(makeSession().isGameOver());
    }

    @Test
    @DisplayName("HunterSession · getTotalEmojis matches collection size")
    void sessionTotalEmojis() {
        assertEquals(5, makeSession().getTotalEmojis());
    }

    @Test
    @DisplayName("HunterSession · getScore is non-negative at start")
    void sessionScoreNonNegative() {
        assertTrue(makeSession().getScore() >= 0);
    }

    @Test
    @DisplayName("HunterSession · makeGuess increments move count")
    void sessionMakeGuessIncrementsMoves() {
        HunterSession s = makeSession();
        s.makeGuess(0, 0);
        assertEquals(1, s.getMoves());
    }

    @Test
    @DisplayName("HunterSession · ALREADY_REVEALED on repeated guess")
    void sessionAlreadyRevealed() {
        HunterSession s = makeSession();
        s.makeGuess(0, 0);
        assertEquals("ALREADY_REVEALED", s.makeGuess(0, 0));
    }

    @Test
    @DisplayName("HunterSession · ALREADY_REVEALED does not count as a move")
    void sessionAlreadyRevealedNoMove() {
        HunterSession s = makeSession();
        s.makeGuess(0, 0);
        s.makeGuess(0, 0); // duplicate
        assertEquals(1, s.getMoves());
    }

    @Test
    @DisplayName("HunterSession · makeGuess returns HIT or MISS or FOUND (not empty)")
    void sessionMakeGuessReturnsNonEmpty() {
        HunterSession s = makeSession();
        String result = s.makeGuess(0, 0);
        assertFalse(result.isBlank(), "makeGuess should return a non-empty string");
        assertTrue(
            result.equals("HIT") || result.equals("MISS") || result.startsWith("FOUND:"),
            "Expected HIT, MISS or FOUND:... but got: " + result
        );
    }

    // ── ScoreBoard ────────────────────────────────────────────────────────

    @Test
    @DisplayName("ScoreBoard · getTotalGames is 0 after construction")
    void scoreBoardInitiallyEmpty() {
        assertEquals(0, new ScoreBoard(10).getTotalGames());
    }

    @Test
    @DisplayName("ScoreBoard · recordGame increments getTotalGames")
    void scoreBoardRecordGame() {
        ScoreBoard sb = new ScoreBoard(10);
        sb.recordGame(800, 25, 5);
        assertEquals(1, sb.getTotalGames());
    }

    @Test
    @DisplayName("ScoreBoard · getBestScore returns highest recorded score")
    void scoreBoardBestScore() {
        ScoreBoard sb = new ScoreBoard(10);
        sb.recordGame(600, 30, 5);
        sb.recordGame(900, 15, 5);
        sb.recordGame(750, 22, 5);
        assertEquals(900, sb.getBestScore());
    }

    @Test
    @DisplayName("ScoreBoard · getBestScore is 0 when no games recorded")
    void scoreBoardBestScoreEmpty() {
        assertEquals(0, new ScoreBoard(10).getBestScore());
    }

    @Test
    @DisplayName("ScoreBoard · getScoreGrid contains recorded values in correct columns")
    void scoreBoardGetGrid() {
        ScoreBoard sb = new ScoreBoard(10);
        sb.recordGame(800, 25, 5);
        int[][] grid = sb.getScoreGrid();
        assertNotNull(grid);
        assertEquals(800, grid[0][0], "column 0 should be score");
        assertEquals(25,  grid[0][1], "column 1 should be moves");
        assertEquals(5,   grid[0][2], "column 2 should be emojis found");
    }

    @Test
    @DisplayName("ScoreBoard · does not exceed capacity")
    void scoreBoardCapacity() {
        ScoreBoard sb = new ScoreBoard(3);
        sb.recordGame(100, 10, 5);
        sb.recordGame(200, 20, 5);
        sb.recordGame(300, 30, 5);
        sb.recordGame(400, 40, 5); // should be silently ignored
        assertEquals(3, sb.getTotalGames());
    }

    // ── ComputerHider ────────────────────────────────────────────────────

    @Test
    @DisplayName("ComputerHider · placeOne successfully places emoji without overlap")
    void computerHiderPlaceOne() {
        Board b = new Board(10);
        Emoji e = new Emoji("Dragon", "🐉", 4);
        ComputerHider hider = new ComputerHider();
        boolean placed = hider.placeOne(b, e);
        assertTrue(placed, "placeOne should place the emoji successfully");
        // Verify at least one cell of the emoji is marked as hit
        boolean foundHit = false;
        for (int r = 0; r < 10 && !foundHit; r++)
            for (int c = 0; c < 10 && !foundHit; c++)
                if (b.isHit(r, c))
                    foundHit = true;
        assertTrue(foundHit, "After placeOne, at least one cell should be marked as a hit");
    }

    @Test
    @DisplayName("ComputerHider · placeAll places all emojis from the collection")
    void computerHiderPlaceAll() {
        Board b = new Board(10);
        EmojiCollection col = new EmojiCollection();
        col.loadFromFile("emojis.csv");
        ComputerHider hider = new ComputerHider();
        hider.placeAll(b, col);
        // Verify that at least some cells are marked as hits
        // (we cannot check exact placement since it is random)
        int hitCount = 0;
        for (int r = 0; r < 10; r++)
            for (int c = 0; c < 10; c++)
                if (b.isHit(r, c))
                    hitCount++;
        assertTrue(hitCount > 0, "After placeAll, at least one cell should contain an emoji");
    }
}
