/**
 * Orchestrates a single game session.
 *
 * <p>Owns the {@link Board} and {@link EmojiCollection}, processes player
 * guesses, tracks moves and score, and determines when the game is over.
 * {@link GameEngine} delegates all game logic to this class.
 *
 * <p><strong>Do not modify the method signatures.</strong>
 * You may add private helper methods and fields.
 */
public class HunterSession {

    private Board board;
    private EmojiCollection collection;
    private int moves;
    private int emojisFound;

    /**
     * Creates a session with the given board and emoji collection.
     *
     * <p>Does not start the game — call {@link #startGame()} to begin.
     *
     * @param board      the board this session will use
     * @param collection the emojis to find
     */
    public HunterSession(Board board, EmojiCollection collection) {
        // TODO: implement
    }

    /**
     * Resets the board and starts a new game.
     *
     * <p>Steps in order:
     * <ol>
     *   <li>Call {@link Board#reset()} to clear the board.
     *   <li>Create a {@link ComputerHider} and call its {@code placeAll} method.
     *   <li>Reset {@code moves} and {@code emojisFound} to zero.
     * </ol>
     */
    public void startGame() {
        // TODO: implement
    }

    /**
     * Processes a player guess at the given position.
     *
     * <p>Returns one of the following strings:
     * <ul>
     *   <li>{@code "ALREADY_REVEALED"} — the cell was already probed.
     *       Do not increment the move counter.
     *   <li>{@code "MISS"} — the cell was empty.
     *   <li>{@code "HIT"} — the cell contained part of an emoji, but that
     *       emoji is not yet fully revealed.
     *   <li>{@code "FOUND:🐉"} — the guess completed an emoji. Replace
     *       {@code 🐉} with the actual symbol of the emoji just fully found.
     * </ul>
     *
     * <p>Hint: call {@link Board#reveal(int, int)} to uncover the cell and
     * get its content. If the content is not {@code " "}, check
     * {@link Board#isEmojiFound(String)} to decide between HIT and FOUND.
     *
     * @param row the row the player clicked (zero-indexed)
     * @param col the column the player clicked (zero-indexed)
     * @return a result string as described above
     */
    public String makeGuess(int row, int col) {
        // TODO: implement
        return "";
    }

    /**
     * Returns {@code true} when all emojis in the collection have been found.
     *
     * <p>The game is over when {@code emojisFound == collection.size()}.
     *
     * @return {@code true} if the game is over
     */
    public boolean isGameOver() {
        // TODO: implement
        throw new UnsupportedOperationException("TODO: implement");
    }

    /**
     * Returns the total number of valid guesses made in this session.
     *
     * <p>{@code "ALREADY_REVEALED"} guesses do not count.
     *
     * @return the move count
     */
    public int getMoves() {
        // TODO: implement
        throw new UnsupportedOperationException("TODO: implement");
    }

    /**
     * Calculates and returns the current score.
     *
     * <p>You choose the formula — for example, {@code 1000 - (moves * 8)}.
     * The score must be at least {@code 0} and must decrease as moves increase.
     *
     * @return the current score (never negative)
     */
    public int getScore() {
        // TODO: implement
        throw new UnsupportedOperationException("TODO: implement");
    }

    /**
     * Returns the number of emojis fully revealed so far.
     *
     * @return the emojis-found count
     */
    public int getEmojisFound() {
        // TODO: implement
        return 0;
    }

    /**
     * Returns the total number of emojis in the collection.
     *
     * @return the total emoji count
     */
    public int getTotalEmojis() {
        // TODO: implement
        return 0;
    }
}
