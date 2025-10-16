
import javax.swing.*;
import java.awt.*;

public class ManualDialog extends JDialog {

    public static void error(JFrame parent, String msg, String title) {
        JOptionPane.showMessageDialog(parent, msg, title, JOptionPane.ERROR_MESSAGE);
    }

    private ManualDialog(Frame owner) {
        super(owner, "How to Play", true);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setSize(520, 520);
        setLocationRelativeTo(owner);

        // main text area
        JTextArea text = new JTextArea();
        text.setEditable(false);
        text.setLineWrap(true);
        text.setWrapStyleWord(true);
        text.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 14));
        text.setBorder(BorderFactory.createEmptyBorder(12, 12, 12, 12));

        text.setText(
            "MEMORY GRID – MANUAL\n\n" +
            "Goal:\n" +
            "Memorize the black tiles shown briefly, then select the same tiles when they turn white.\n\n" +
            "Flow:\n" +
            "1) A grid appears. Some tiles turn black for a few seconds.\n" +
            "2) All tiles turn white. Now you select the tiles that were black.\n" +
            "3) If your selection matches, you advance to the next level; otherwise, you lose a life.\n\n" +
            "Lives, Levels, Score:\n" +
            "- You start with 3 lives.\n" +
            "- Each level gets harder (bigger grid and more targets).\n" +
            "- Your score grows with each level you pass.\n\n" +
            "Controls:\n" +
            "- Mouse: click tiles to select/deselect during the guessing phase.\n" +
            "- Tiles are disabled while the pattern is being shown.\n\n" +
            "Tips:\n" +
            "- Remember shapes (L, T, lines) or scan row-by-row.\n\n" +
            "Accessibility:\n" +
            "High-contrast colors are used. If you need keyboard support or thicker outlines, we can add them."
        );

        JScrollPane scroll = new JScrollPane(text);
        scroll.setBorder(BorderFactory.createEmptyBorder());

        // close button
        JButton close = new JButton("Close");
        close.addActionListener(e -> dispose());
        JPanel south = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        south.add(close);

        // layout setup
        setLayout(new BorderLayout(0, 10));
        add(scroll, BorderLayout.CENTER);
        add(south, BorderLayout.SOUTH);
    }

    public static void info(JFrame parent, String msg, String title) {
        JOptionPane.showMessageDialog(parent, msg, title, JOptionPane.INFORMATION_MESSAGE);
    }

    public static void show(JFrame owner) {
        new ManualDialog(owner).setVisible(true);
    }
}
