// =============================================================
// DO NOT MODIFY THIS FILE
// =============================================================

import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;

/**
 * The Swing graphical interface for Emoji Hunter.
 *
 * <p><strong>This class is provided — do not modify it.</strong>
 *
 * <p>Renders the 10×10 grid as clickable buttons, shows score / moves /
 * found counters, lists the emoji legend, and displays score history.
 * All game logic is delegated to {@link GameEngine}.
 */
public class EmojiHunterApp {

    private static final int BOARD_SIZE = 10;
    private static final int CELL_PX    = 48;

    private final GameEngine engine;
    private JButton[][] cells;

    private JLabel statusLabel;
    private JLabel scoreLabel;
    private JLabel movesLabel;
    private JLabel foundLabel;
    private JPanel legendPanel;
    private JPanel historyPanel;

    // ── Entry point ───────────────────────────────────────────────────────

    public static void main(String[] args) {
        SwingUtilities.invokeLater(EmojiHunterApp::new);
    }

    // ── Constructor ───────────────────────────────────────────────────────

    public EmojiHunterApp() {
        engine = new GameEngine();
        buildAndShow();
    }

    // ── Build the window ──────────────────────────────────────────────────

    private void buildAndShow() {
        JFrame frame = new JFrame("Emoji Hunter");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        frame.add(buildTopBar(),    BorderLayout.NORTH);
        frame.add(buildBoardArea(), BorderLayout.CENTER);
        frame.add(buildPanel(),     BorderLayout.EAST);
        frame.add(buildStatusBar(), BorderLayout.SOUTH);

        frame.pack();
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        startNewGame();
    }

    private JPanel buildTopBar() {
        JPanel bar = new JPanel(new BorderLayout());
        bar.setBackground(new Color(248, 248, 248));
        bar.setBorder(new MatteBorder(0, 0, 1, 0, new Color(220, 220, 220)));

        JLabel title = new JLabel("  Emoji Hunter");
        title.setFont(title.getFont().deriveFont(Font.BOLD, 15f));
        title.setBorder(BorderFactory.createEmptyBorder(8, 0, 8, 0));

        JButton newGameBtn = new JButton("New Game");
        newGameBtn.setFocusPainted(false);
        newGameBtn.addActionListener(e -> startNewGame());

        JPanel right = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 6));
        right.setOpaque(false);
        right.add(newGameBtn);

        bar.add(title, BorderLayout.WEST);
        bar.add(right, BorderLayout.EAST);
        return bar;
    }

    private JPanel buildBoardArea() {
        JPanel outer = new JPanel(new BorderLayout(2, 2));
        outer.setBorder(BorderFactory.createEmptyBorder(12, 14, 12, 8));

        // Column headers A–J
        JPanel colHeaders = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
        colHeaders.add(Box.createHorizontalStrut(24));
        for (String col : "ABCDEFGHIJ".split("")) {
            JLabel lbl = new JLabel(col, SwingConstants.CENTER);
            lbl.setPreferredSize(new Dimension(CELL_PX + 2, 16));
            lbl.setFont(lbl.getFont().deriveFont(10f));
            lbl.setForeground(Color.GRAY);
            colHeaders.add(lbl);
        }

        // Row labels 1–10
        JPanel rowLabels = new JPanel();
        rowLabels.setLayout(new BoxLayout(rowLabels, BoxLayout.Y_AXIS));
        for (int r = 0; r < BOARD_SIZE; r++) {
            JLabel lbl = new JLabel(String.valueOf(r + 1), SwingConstants.RIGHT);
            lbl.setPreferredSize(new Dimension(20, CELL_PX + 2));
            lbl.setMaximumSize(new Dimension(20, CELL_PX + 2));
            lbl.setFont(lbl.getFont().deriveFont(10f));
            lbl.setForeground(Color.GRAY);
            rowLabels.add(lbl);
        }

        // 10×10 button grid
        JPanel grid = new JPanel(new GridLayout(BOARD_SIZE, BOARD_SIZE, 2, 2));
        cells = new JButton[BOARD_SIZE][BOARD_SIZE];
        for (int r = 0; r < BOARD_SIZE; r++) {
            for (int c = 0; c < BOARD_SIZE; c++) {
                final int row = r, col = c;
                JButton btn = new JButton("?");
                btn.setPreferredSize(new Dimension(CELL_PX, CELL_PX));
                btn.setFont(btn.getFont().deriveFont(12f));
                btn.setBackground(new Color(232, 232, 232));
                btn.setBorder(BorderFactory.createLineBorder(new Color(200, 200, 200)));
                btn.setFocusPainted(false);
                btn.addActionListener(e -> onCellClick(row, col));
                cells[r][c] = btn;
                grid.add(btn);
            }
        }

        outer.add(colHeaders, BorderLayout.NORTH);
        outer.add(rowLabels,  BorderLayout.WEST);
        outer.add(grid,       BorderLayout.CENTER);
        return outer;
    }

    private JPanel buildPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setPreferredSize(new Dimension(185, 0));
        panel.setBorder(new CompoundBorder(
            new MatteBorder(0, 1, 0, 0, new Color(220, 220, 220)),
            BorderFactory.createEmptyBorder(12, 14, 12, 14)
        ));

        scoreLabel = monoLabel("1000");
        movesLabel = monoLabel("0");
        foundLabel = monoLabel("0 / 0");

        panel.add(sectionHeader("SCORE"));
        panel.add(statRow("Score", scoreLabel));
        panel.add(statRow("Moves", movesLabel));
        panel.add(statRow("Found", foundLabel));
        panel.add(Box.createVerticalStrut(16));

        legendPanel = new JPanel();
        legendPanel.setLayout(new BoxLayout(legendPanel, BoxLayout.Y_AXIS));
        legendPanel.setOpaque(false);
        panel.add(sectionHeader("EMOJIS TO FIND"));
        panel.add(legendPanel);
        panel.add(Box.createVerticalStrut(16));

        historyPanel = new JPanel();
        historyPanel.setLayout(new BoxLayout(historyPanel, BoxLayout.Y_AXIS));
        historyPanel.setOpaque(false);
        panel.add(sectionHeader("SCORE HISTORY"));
        panel.add(historyPanel);

        return panel;
    }

    private JLabel buildStatusBar() {
        statusLabel = new JLabel("  Click any cell to start hunting.");
        statusLabel.setPreferredSize(new Dimension(0, 30));
        statusLabel.setFont(statusLabel.getFont().deriveFont(12f));
        statusLabel.setBorder(new MatteBorder(1, 0, 0, 0, new Color(220, 220, 220)));
        statusLabel.setBackground(new Color(248, 248, 248));
        statusLabel.setOpaque(true);
        return statusLabel;
    }

    // ── Game actions ──────────────────────────────────────────────────────

    private void startNewGame() {
        try { engine.startNewGame(); } catch (Exception ignored) {}
        updateBoard();
        updatePanel();
        setStatus("New game — find all the emojis!");
    }

    private void onCellClick(int row, int col) {
        try { if (engine.isGameOver()) return; } catch (Exception ignored) {}
        String result;
        try { result = engine.guess(row, col); } catch (Exception e) { return; }
        updateBoard();
        updatePanel();
        switch (result) {
            case "ALREADY_REVEALED" -> setStatus("Already revealed — try another cell.");
            case "MISS"             -> setStatus("Miss. Nothing there.");
            case "HIT"              -> setStatus("Hit! Keep probing nearby.");
            default -> {
                if (result.startsWith("FOUND:"))
                    setStatus("Found " + result.substring(6) + "! Keep going!");
            }
        }
        boolean over = false;
        try { over = engine.isGameOver(); } catch (Exception ignored) {}
        if (over) {
            int score = 0; try { score = engine.getScore(); } catch (Exception ignored) {}
            int moves = 0; try { moves = engine.getMoves(); } catch (Exception ignored) {}
            setStatus("You found them all!  Final score: " + score + "  |  " + moves + " moves.");
        }
    }

    // ── Render ────────────────────────────────────────────────────────────

    void updateBoard() {
        String[][] display = engine.getBoardDisplay();
        if (display == null) return;
        for (int r = 0; r < BOARD_SIZE; r++) {
            for (int c = 0; c < BOARD_SIZE; c++) {
                JButton btn  = cells[r][c];
                String  cell = display[r][c];
                switch (cell) {
                    case "?" -> {
                        btn.setText("?");
                        btn.setFont(btn.getFont().deriveFont(Font.PLAIN, 12f));
                        btn.setBackground(new Color(232, 232, 232));
                        btn.setForeground(Color.DARK_GRAY);
                        btn.setBorder(BorderFactory.createLineBorder(new Color(200, 200, 200)));
                        btn.setEnabled(true);
                    }
                    case "·" -> {
                        btn.setText("·");
                        btn.setFont(btn.getFont().deriveFont(Font.PLAIN, 16f));
                        btn.setBackground(UIManager.getColor("Panel.background"));
                        btn.setForeground(new Color(180, 180, 180));
                        btn.setBorder(BorderFactory.createEmptyBorder());
                        btn.setEnabled(false);
                    }
                    default -> {
                        btn.setText(cell);
                        btn.setFont(btn.getFont().deriveFont(Font.PLAIN, 22f));
                        btn.setBackground(new Color(225, 245, 238));
                        btn.setForeground(Color.BLACK);
                        btn.setBorder(BorderFactory.createLineBorder(new Color(159, 225, 203)));
                        btn.setEnabled(false);
                    }
                }
            }
        }
    }

    void updatePanel() {
        try { scoreLabel.setText(String.valueOf(engine.getScore())); } catch (Exception ignored) {}
        try { movesLabel.setText(String.valueOf(engine.getMoves())); } catch (Exception ignored) {}
        try { foundLabel.setText(engine.getEmojisFound() + " / " + engine.getTotalEmojis()); } catch (Exception ignored) {}
        updateLegend();
        updateHistory();
    }

    private void updateLegend() {
        try {
            legendPanel.removeAll();
            String[][] status = engine.getEmojiStatus();
            if (status == null) return;
            for (String[] entry : status) {
                JPanel row = new JPanel(new FlowLayout(FlowLayout.LEFT, 4, 1));
                row.setOpaque(false);
                row.setMaximumSize(new Dimension(Integer.MAX_VALUE, 26));

                JLabel sym  = new JLabel(entry[0]);
                JLabel name = new JLabel(entry[1]);
                JLabel tag  = new JLabel("found".equals(entry[2]) ? "found" : "···");

                sym.setFont(sym.getFont().deriveFont(16f));
                name.setFont(name.getFont().deriveFont(11f));
                name.setPreferredSize(new Dimension(68, 18));
                tag.setFont(tag.getFont().deriveFont(10f));
                tag.setForeground("found".equals(entry[2])
                    ? new Color(15, 110, 86) : Color.GRAY);

                row.add(sym); row.add(name); row.add(tag);
                legendPanel.add(row);
            }
            legendPanel.revalidate();
            legendPanel.repaint();
        } catch (Exception ignored) {}
    }

    private void updateHistory() {
        try {
            historyPanel.removeAll();
            int total = engine.getTotalGames();
            if (total == 0) {
                JLabel none = new JLabel("No completed games yet");
                none.setFont(none.getFont().deriveFont(Font.ITALIC, 11f));
                none.setForeground(Color.GRAY);
                historyPanel.add(none);
            } else {
                historyPanel.add(histRow("SCORE", "MOVES", true));
                int[][] grid = engine.getScores();
                for (int i = 0; i < total; i++) {
                    historyPanel.add(histRow(
                        String.valueOf(grid[i][0]),
                        String.valueOf(grid[i][1]), false));
                }
            }
            historyPanel.revalidate();
            historyPanel.repaint();
        } catch (Exception ignored) {}
    }

    void setStatus(String msg) {
        statusLabel.setText("  " + msg);
    }

    // ── Small UI helpers ──────────────────────────────────────────────────

    private JLabel sectionHeader(String text) {
        JLabel lbl = new JLabel(text);
        lbl.setFont(lbl.getFont().deriveFont(Font.BOLD, 9f));
        lbl.setForeground(Color.GRAY);
        lbl.setAlignmentX(Component.LEFT_ALIGNMENT);
        return lbl;
    }

    private JPanel statRow(String labelText, JLabel valueLabel) {
        JPanel row = new JPanel(new BorderLayout());
        row.setOpaque(false);
        row.setMaximumSize(new Dimension(Integer.MAX_VALUE, 22));
        JLabel lbl = new JLabel(labelText);
        lbl.setFont(lbl.getFont().deriveFont(12f));
        lbl.setForeground(Color.DARK_GRAY);
        row.add(lbl,        BorderLayout.WEST);
        row.add(valueLabel, BorderLayout.EAST);
        return row;
    }

    private JLabel monoLabel(String text) {
        JLabel lbl = new JLabel(text);
        lbl.setFont(new Font(Font.MONOSPACED, Font.BOLD, 13));
        return lbl;
    }

    private JPanel histRow(String score, String moves, boolean header) {
        JPanel row = new JPanel(new GridLayout(1, 2));
        row.setOpaque(false);
        row.setMaximumSize(new Dimension(Integer.MAX_VALUE, 20));
        int style = header ? Font.BOLD : Font.PLAIN;
        int size  = header ? 9 : 11;
        JLabel s = new JLabel(score);
        JLabel m = new JLabel(moves);
        Font f = new Font(Font.MONOSPACED, style, size);
        s.setFont(f); m.setFont(f);
        if (header) { s.setForeground(Color.GRAY); m.setForeground(Color.GRAY); }
        row.add(s); row.add(m);
        return row;
    }
}
