import java.util.Random;

/**
 * Places all emojis on the board at random valid positions before a game starts.
 *
 * <p>This class is stateless — it holds no fields. All behaviour is driven
 * by the {@link Board} and {@link EmojiCollection} passed in to each method.
 *
 * <p><strong>Do not modify the method signatures.</strong>
 * You may add private helper methods.
 */
public class ComputerHider {

    /**
     * Creates a new {@code ComputerHider}. No fields to initialise.
     */
    public ComputerHider() {
        // Nothing to do here.
    }

    /**
     * Places every emoji from the collection onto the board.
     *
     * <p>Iterates through all emojis in the collection and calls
     * {@link #placeOne} for each one. If {@code placeOne} returns
     * {@code false} for any emoji (meaning no valid position was found),
     * print a warning but continue with the remaining emojis.
     *
     * @param board      the board to place emojis on
     * @param collection the emojis to place
     */
    public void placeAll(Board board, EmojiCollection collection) {
        // TODO: implement
    }

    /**
     * Attempts to place a single emoji at a random valid position.
     *
     * <p>Picks a random row, column, and orientation (horizontal or vertical).
     * Use a {@link Coordinate} to hold the random row and column, then call
     * {@link Coordinate#isValid(int)} to check boundaries before calling
     * {@link Board#isValidPlacement}. Retries up to 200 times before giving up.
     *
     * <p>Hint: {@code new Random()} gives you a random number generator.
     * {@code random.nextInt(board.getSize())} gives a random row or column.
     * {@code random.nextBoolean()} gives a random orientation.
     *
     * @param board the board to place the emoji on
     * @param emoji the emoji to place
     * @return {@code true} if the emoji was placed successfully,
     *         {@code false} if no valid position was found after 200 attempts
     */
    public boolean placeOne(Board board, Emoji emoji) {
        // TODO: implement
        return false;
    }
}
