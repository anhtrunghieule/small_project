/**
 * An immutable definition of one emoji used in Emoji Hunter.
 *
 * <p>A record with three fields. The canonical constructor, accessors,
 * {@code equals}, {@code hashCode}, and {@code toString} are all
 * auto-generated — you do not need to write them.
 *
 * <p><strong>Do not modify the record header or the field names.</strong>
 * You may add a compact constructor to validate the fields if you wish,
 * but it is not required.
 *
 * @param name   the human-readable name, e.g. {@code "Dragon"}
 * @param symbol the emoji character as a String, e.g. {@code "🐉"}
 * @param size   the number of cells this emoji spans on the board (1–4)
 */
public record Emoji(String name, String symbol, int size) {
    // The record gives you everything for free.
    // If you want to validate the fields (e.g. size > 0) you can add a
    // compact constructor here — but it is optional.

    public Emoji {
        // TODO: remove this throw — it is here only so tests fail until
        // you have confirmed that the record compiles correctly.
        throw new UnsupportedOperationException("TODO: implement");
    }
}
