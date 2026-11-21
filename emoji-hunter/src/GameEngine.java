// =============================================================
// DO NOT MODIFY THIS FILE
// =============================================================

/**
 * Connects the JavaFX GUI to the student-implemented backend.
 *
 * <p><strong>This class is provided — do not modify it.</strong>
 *
 * <p>Creates all backend objects on construction, starts new games, and
 * delegates every GUI action to the appropriate student class. The GUI
 * ({@link EmojiHunterApp}) only ever calls methods on this class.
 */
public class GameEngine {

    private static final int  BOARD_SIZE  = 10;
    private static final int  MAX_GAMES   = 20;
    private static final String EMOJI_FILE = "emojis.csv";
    private static final String SCORE_FILE = "scores.csv";

    private final Board           board;
    private final EmojiCollection collection;
    private final HunterSession   session;
    private final ScoreBoard      scoreBoard;

    /**
     * Creates the engine and wires up all backend objects.
     *
     * <p>Loading the emoji file and any previously saved scores happens here.
     */
    public GameEngine() {
        board      = new Board(BOARD_SIZE);
        collection = new EmojiCollection();
        collection.loadFromFile(EMOJI_FILE);
        session    = new HunterSession(board, collection);
        scoreBoard = new ScoreBoard(MAX_GAMES);
        scoreBoard.loadFromFile(SCORE_FILE);
    }

    /**
     * Starts a new game.
     */
    public void startNewGame() {
        session.startGame();
    }

    /**
     * Processes a player guess at the given coordinate.
     *
     * <p>If the guess ends the game, the result is recorded in the
     * scoreboard and saved to disk immediately.
     *
     * @param row the row clicked (zero-indexed)
     * @param col the column clicked (zero-indexed)
     * @return the result string from {@link HunterSession#makeGuess}
     */
    public String guess(int row, int col) {
        String result = session.makeGuess(row, col);
        if (session.isGameOver()) {
            scoreBoard.recordGame(
                session.getScore(),
                session.getMoves(),
                session.getEmojisFound()
            );
            scoreBoard.saveToFile(SCORE_FILE);
        }
        return result;
    }

    /**
     * Returns the display grid for the GUI to render.
     *
     * @return the {@code String[][]} from {@link Board#getDisplayGrid()}
     */
    public String[][] getBoardDisplay() {
        return board.getDisplayGrid();
    }

    /** @return {@code true} if the current game is over */
    public boolean isGameOver() {
        return session.isGameOver();
    }

    /** @return the current score */
    public int getScore() {
        return session.getScore();
    }

    /** @return the current move count */
    public int getMoves() {
        return session.getMoves();
    }

    /** @return the number of emojis found so far */
    public int getEmojisFound() {
        return session.getEmojisFound();
    }

    /** @return the total number of emojis in the collection */
    public int getTotalEmojis() {
        return session.getTotalEmojis();
    }

    /**
     * Returns the score history grid for the GUI to render.
     *
     * @return the result of {@link ScoreBoard#getScoreGrid()}
     */
    public int[][] getScores() {
        return scoreBoard.getScoreGrid();
    }

    /** @return the number of completed games recorded */
    public int getTotalGames() {
        return scoreBoard.getTotalGames();
    }

    /**
     * Returns the status of each emoji in the collection for the legend panel.
     *
     * <p>Each row in the returned array has three elements:
     * <ol>
     *   <li>The emoji symbol (e.g. {@code "🐉"})
     *   <li>The emoji name  (e.g. {@code "Dragon"})
     *   <li>{@code "found"} or {@code "hunting"}
     * </ol>
     *
     * @return a {@code String[][]} with one row per emoji, or {@code null}
     *         if the collection is empty
     */
    public String[][] getEmojiStatus() {
        Emoji[] all = collection.getAll();
        if (all == null || all.length == 0) return null;
        String[][] status = new String[all.length][3];
        for (int i = 0; i < all.length; i++) {
            status[i][0] = all[i].symbol();
            status[i][1] = all[i].name();
            status[i][2] = board.isEmojiFound(all[i].symbol()) ? "found" : "hunting";
        }
        return status;
    }
}
