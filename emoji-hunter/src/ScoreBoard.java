import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Records completed game results in a 2D array and saves them to a file.
 *
 * <p>Each row in the 2D array represents one completed game.
 * The three columns store:
 * <ol>
 *   <li>Score (index 0)
 *   <li>Moves (index 1)
 *   <li>Emojis found (index 2)
 * </ol>
 *
 * <p>Example: {@code scores[0] = {840, 20, 5}} means the first recorded game
 * had a score of 840, took 20 moves, and found all 5 emojis.
 *
 * <p><strong>Do not modify the method signatures.</strong>
 * You may add private helper methods.
 */
public class ScoreBoard {

    private int[][] scores;
    private int gameCount;

    /**
     * Creates an empty scoreboard with capacity for the given number of games.
     *
     * <p>Initialises {@code scores} as a 2D array with {@code maxGames} rows
     * and 3 columns. Sets {@code gameCount} to zero.
     *
     * @param maxGames the maximum number of game results to store
     */
    public ScoreBoard(int maxGames) {
        // TODO: implement
    }

    /**
     * Records the result of one completed game.
     *
     * <p>Stores the three values as a new row in the {@code scores} array
     * at index {@code gameCount}, then increments {@code gameCount}.
     * Does nothing silently if the board is already at capacity.
     *
     * @param score       the final score
     * @param moves       the total number of valid moves made
     * @param emojisFound the number of emojis fully found
     */
    public void recordGame(int score, int moves, int emojisFound) {
        // TODO: implement
    }

    /**
     * Returns the highest score recorded, or {@code 0} if no games have
     * been played yet.
     *
     * <p>Scans column 0 (score) of the first {@code gameCount} rows.
     *
     * @return the best score
     */
    public int getBestScore() {
        // TODO: implement
        throw new UnsupportedOperationException("TODO: implement");
    }

    /**
     * Returns the number of completed games recorded so far.
     *
     * @return the game count
     */
    public int getTotalGames() {
        // TODO: implement
        throw new UnsupportedOperationException("TODO: implement");
    }

    /**
     * Returns the raw 2D scores array.
     *
     * <p>Only the first {@link #getTotalGames()} rows contain valid data.
     * The GUI uses this to render the score history table.
     *
     * @return the scores array
     */
    public int[][] getScoreGrid() {
        // TODO: implement
        return null;
    }

    /**
     * Writes each recorded game to a text file, one game per line.
     *
     * <p>Each line contains three comma-separated integers: score, moves,
     * emojisFound. Example line: {@code 840,20,5}
     *
     * <p>Wraps all file operations in a {@code try/catch} block and prints
     * a meaningful error message if the file cannot be written.
     *
     * @param path the file path to write to (e.g. {@code "scores.csv"})
     */
    public void saveToFile(String path) {
        // TODO: implement
    }

    /**
     * Reads game results back from a file into the 2D array.
     *
     * <p>Expects the same format produced by {@link #saveToFile}: one game
     * per line, three comma-separated integers. Resets {@code gameCount}
     * to zero before loading, then increments it for each line read.
     *
     * <p>Wraps all file operations in a {@code try/catch} block. If the
     * file does not exist yet, do nothing — the scoreboard simply stays empty.
     *
     * @param path the file path to read from (e.g. {@code "scores.csv"})
     */
    public void loadFromFile(String path) {
        // TODO: implement
    }
}
