/**
 * The game grid.
 *
 * <p>Maintains two parallel 2D arrays:
 * <ul>
 *   <li>{@code hidden} — the true state of the board, populated by
 *       {@link ComputerHider} before play begins. Each cell contains either
 *       an emoji symbol string (e.g. {@code "🐉"}) or a space ({@code " "})
 *       for empty cells.
 *   <li>{@code revealed} — tracks which cells the player has already probed.
 *       All {@code false} at construction; set to {@code true} by {@link #reveal}.
 * </ul>
 *
 * <p>The GUI never reads {@code hidden} directly — it calls
 * {@link #getDisplayGrid()} to get a safe view of the board state.
 *
 * <p><strong>Note on emoji storage:</strong> emoji symbols are stored as
 * {@code String} rather than {@code char} because most emoji characters
 * require more than one Java {@code char} to represent.
 *
 * <p><strong>Do not modify the method signatures.</strong>
 * You may add private helper methods.
 */
public class Board {

    private String[][] hidden;
    private boolean[][] revealed;
    private int size;

    /**
     * Creates a blank board of the given dimension.
     *
     * <p>Every cell in {@code hidden} is set to {@code " "} (a single space).
     * Every cell in {@code revealed} is set to {@code false}.
     *
     * @param size the number of rows and columns (e.g. 10 for a 10×10 board)
     */
    public Board(int size) {
        // TODO: implement
    }

    /**
     * Places an emoji on the hidden grid.
     *
     * <p>If {@code horizontal} is {@code true}, the emoji occupies cells
     * {@code (row, col)}, {@code (row, col+1)}, ... up to size−1 cells.
     * If {@code false}, it occupies {@code (row, col)}, {@code (row+1, col)}, ...
     *
     * <p>The emoji's {@link Emoji#symbol()} string is written into each cell.
     *
     * @param emoji      the emoji to place
     * @param row        the starting row (zero-indexed)
     * @param col        the starting column (zero-indexed)
     * @param horizontal {@code true} for horizontal, {@code false} for vertical
     * @return {@code true} if placed successfully;
     *         {@code false} if the placement is out of bounds or overlaps another emoji
     */
    public boolean placeEmoji(Emoji emoji, int row, int col, boolean horizontal) {
        // TODO: implement
        throw new UnsupportedOperationException("TODO: implement");
    }

    /**
     * Returns {@code true} if the emoji can be placed at the given position.
     *
     * <p>A placement is valid when every cell it would occupy is:
     * <ul>
     *   <li>within the board boundaries, and
     *   <li>currently empty (contains {@code " "}).
     * </ul>
     *
     * @param emoji      the emoji to check
     * @param row        the starting row
     * @param col        the starting column
     * @param horizontal {@code true} for horizontal, {@code false} for vertical
     * @return {@code true} if the placement is valid
     */
    public boolean isValidPlacement(Emoji emoji, int row, int col, boolean horizontal) {
        // TODO: implement
        throw new UnsupportedOperationException("TODO: implement");
    }

    /**
     * Marks a cell as revealed and returns its hidden content.
     *
     * <p>Sets {@code revealed[row][col]} to {@code true} and returns
     * the string stored in {@code hidden[row][col]}.
     *
     * @param row the row to reveal (zero-indexed)
     * @param col the column to reveal (zero-indexed)
     * @return the string at that cell — {@code " "} for an empty cell,
     *         or the emoji symbol string for a hit
     */
    public String reveal(int row, int col) {
        // TODO: implement
        return " ";
    }

    /**
     * Returns {@code true} if the given cell has already been probed.
     *
     * @param row the row to check
     * @param col the column to check
     * @return {@code true} if the cell is revealed
     */
    public boolean isRevealed(int row, int col) {
        // TODO: implement
        throw new UnsupportedOperationException("TODO: implement");
    }

    /**
     * Returns {@code true} if the given cell contains part of an emoji.
     *
     * <p>A cell is a hit when its {@code hidden} value is not {@code " "}.
     *
     * @param row the row to check
     * @param col the column to check
     * @return {@code true} if the cell contains an emoji symbol
     */
    public boolean isHit(int row, int col) {
        // TODO: implement
        throw new UnsupportedOperationException("TODO: implement");
    }

    /**
     * Returns {@code true} if every cell containing this emoji symbol is revealed.
     *
     * <p>Scans the entire {@code hidden} grid for cells that equal {@code symbol}
     * and checks that all of them are also {@code true} in {@code revealed}.
     *
     * @param symbol the emoji symbol to search for (e.g. {@code "🐉"})
     * @return {@code true} if all cells with this symbol are revealed
     */
    public boolean isEmojiFound(String symbol) {
        // TODO: implement
        throw new UnsupportedOperationException("TODO: implement");
    }

    /**
     * Returns how many distinct emoji symbols are fully revealed.
     *
     * <p>Collects the unique emoji symbols present in {@code hidden}, then
     * counts how many pass {@link #isEmojiFound(String)}.
     *
     * <p>Hint: scan every cell of {@code hidden}. For each cell that is not
     * {@code " "}, check whether you have already seen that symbol (by
     * comparing it against a small {@code String[]} you build up as you go).
     * If it is new, add it to your list and call {@link #isEmojiFound(String)}
     * on it. The board holds at most five distinct symbols, so a fixed-size
     * array of length 5 is enough.
     *
     * @return the number of fully found emojis
     */
    public int countFoundEmojis() {
        // TODO: implement
        throw new UnsupportedOperationException("TODO: implement");
    }

    /**
     * Returns a display-safe copy of the grid for the GUI to render.
     *
     * <p>Each cell follows these rules:
     * <ul>
     *   <li>Not yet revealed → {@code "?"}
     *   <li>Revealed and contains an emoji → the emoji symbol string
     *   <li>Revealed and empty → {@code "·"}
     * </ul>
     *
     * <p>This method returns a <em>new</em> array — the GUI cannot accidentally
     * modify the board's internal state.
     *
     * @return a new {@code String[][]} representing the visible board state
     */
    public String[][] getDisplayGrid() {
        // TODO: implement
        return null;
    }

    /**
     * Resets the board to a blank state, ready for a new game.
     *
     * <p>Fills {@code hidden} with {@code " "} and {@code revealed} with
     * {@code false}.
     */
    public void reset() {
        // TODO: implement
    }

    /**
     * Returns the dimension of this board.
     *
     * @return the board size passed to the constructor
     */
    public int getSize() {
        // TODO: implement
        return 0;
    }
}
