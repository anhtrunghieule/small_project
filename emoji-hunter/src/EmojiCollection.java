import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * Loads and stores the set of emojis used in the game.
 *
 * <p>Reads from a plain-text CSV file where each line defines one emoji:
 * <pre>
 *   Dragon,🐉,4
 *   Unicorn,🦄,3
 * </pre>
 *
 * <p>Emojis are stored in a fixed-size array of {@link Emoji} records.
 * The array is sized when the file is first read.
 *
 * <p><strong>Do not modify the method signatures.</strong>
 * You may add private helper methods.
 */
public class EmojiCollection {

    private Emoji[] emojis;
    private int count;

    /**
     * Creates an empty collection.
     *
     * <p>Call {@link #loadFromFile(String)} to populate it.
     */
    public EmojiCollection() {
        // TODO: initialise fields
    }

    /**
     * Reads emoji definitions from a CSV file and stores them as records.
     *
     * <p>Each line must contain exactly three comma-separated fields:
     * name, symbol, size. For example:
     * <pre>Dragon,🐉,4</pre>
     *
     * <p>Wraps all file operations in a {@code try/catch} block and prints
     * a meaningful error message if the file cannot be read.
     *
     * <p>Hint: allocate {@code emojis} as a fixed-size array (e.g. length 10)
     * before the loop, then set each slot as you read each line.
     * Use {@link BufferedReader} with {@link FileReader} to read the
     * file line by line, and {@link String#split(String)} to split on commas.
     * Use {@link Integer#parseInt(String)} to convert the size field.
     *
     * @param path the path to the CSV file (e.g. {@code "emojis.csv"})
     */
    public void loadFromFile(String path) {
        // TODO: implement
    }

    /**
     * Returns the emoji at the given index.
     *
     * @param index a value in the range {@code [0, size())}
     * @return the {@link Emoji} at that index
     */
    public Emoji getEmoji(int index) {
        // TODO: implement
        return null;
    }

    /**
     * Returns the number of emojis currently loaded.
     *
     * @return the emoji count
     */
    public int size() {
        // TODO: implement
        throw new UnsupportedOperationException("TODO: implement");
    }

    /**
     * Returns a copy of the internal array containing all loaded emojis.
     *
     * <p>Returns a copy so callers cannot modify the collection's internal state.
     *
     * @return an array of all {@link Emoji} records in load order
     */
    public Emoji[] getAll() {
        // TODO: implement
        return null;
    }
}
