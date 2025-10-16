import javax.swing.*;
import java.awt.*;

public class GameOverPanel extends JPanel {
    private final JLabel scoreLabel  = new JLabel("Score: --");
    private final JLabel levelsLabel = new JLabel("Levels passed: --");

    public GameOverPanel(Runnable onBackToStart) {
        setLayout(new GridBagLayout());
        var c = new GridBagConstraints();
        c.insets = new Insets(8, 8, 8, 8);

        JLabel over = new JLabel("Game Over");
        over.setForeground(Color.RED);
        over.setFont(over.getFont().deriveFont(26f));
        add(over, c);

        c.gridy = 1; add(scoreLabel,  c);
        c.gridy = 2; add(levelsLabel, c);

        c.gridy = 3;
        JButton back = new JButton("Back to Start");
        back.addActionListener(e -> onBackToStart.run());
        add(back, c);
    }

    public void setFinalStats(int score, int levelsPassed) {
        scoreLabel.setText("Score: " + score);
        levelsLabel.setText("Levels passed: " + levelsPassed);
    }
}