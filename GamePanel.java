import javax.swing.*;
import java.awt.*;
import java.util.HashSet;
import java.util.Set;

public class GamePanel extends JPanel {

    // HUD
    private final JLabel levelLbl = new JLabel("Level: 1");
    private final JLabel livesLbl = new JLabel("Lives: 3");
    private final JLabel scoreLbl = new JLabel("Score: 0");

    // Controls
    private final JButton nextBtn    = new JButton("Start / Next");
    private final JButton confirmBtn = new JButton("Confirm");
    private final JButton giveUpBtn  = new JButton("Give Up");

    // Grid
    private final JPanel gridPanel = new JPanel();
    private JButton[] tiles = new JButton[0]; //  array (idx = row*size + col)

    private final GameController controller;

    public GamePanel(GameController controller) {
        this.controller = controller;

        setLayout(new BorderLayout(10, 10));

        // top HUD
        JPanel hud = new JPanel(new FlowLayout(FlowLayout.CENTER, 16, 8));
        hud.add(levelLbl);
        hud.add(livesLbl);
        hud.add(scoreLbl);
        add(hud, BorderLayout.NORTH);

        // center grid
        gridPanel.setBorder(BorderFactory.createEmptyBorder(8, 8, 8, 8));
        add(gridPanel, BorderLayout.CENTER);

        // bottom controls
        JPanel bottom = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 8));
        bottom.add(nextBtn);
        bottom.add(confirmBtn);
        bottom.add(giveUpBtn);
        add(bottom, BorderLayout.SOUTH);

        // actions
        nextBtn.addActionListener(e -> controller.onUserPressedNext());
        confirmBtn.addActionListener(e -> controller.onUserPressedConfirm(getUserSelection()));
        giveUpBtn.addActionListener(e -> controller.onUserPressedGiveUp());

        // TODO: hide Confirm until pattern
    }

    //  Called by controller 

    public void updateHud(int level, int lives, int score) {
        levelLbl.setText("Level: " + level);
        livesLbl.setText("Lives: " + lives);
        scoreLbl.setText("Score: " + score);
    }

    public void buildGrid(int size) {
        gridPanel.removeAll();
        gridPanel.setLayout(new GridLayout(size, size, 4, 4)); //4 pixel gaps
        tiles = new JButton[size * size];

        for (int i = 0; i < tiles.length; i++) {
            final int idx = i;
            JButton b = new JButton("");
            b.setFocusPainted(false);
            b.setPreferredSize(new Dimension(60, 60)); // TODO: make responsive
            b.setBackground(Color.WHITE);
            b.setForeground(Color.BLACK);

            b.addActionListener(e -> {
                if (!controller.isAcceptingInput()) return;
                if ("●".equals(b.getText())) {
                    b.setText("");
                    b.setBackground(Color.WHITE);
                } else {
                    b.setText("●");
                    b.setBackground(new Color(200, 230, 255)); // TODO: move to SettingsCustomization
                }
            });

            tiles[i] = b;
            gridPanel.add(b);
        }

        gridPanel.revalidate();
        gridPanel.repaint();
    }

    public void showPattern(Set<Integer> pattern) {
        // paint black tiles while showing
        for (int i = 0; i < tiles.length; i++) {
            JButton b = tiles[i];
            if (pattern.contains(i)) {
                b.setBackground(Color.BLACK);
                b.setForeground(Color.WHITE);
                b.setText("");
            } else {
                b.setBackground(Color.WHITE);
                b.setForeground(Color.BLACK);
                b.setText("");
            }
        }
    }

    public void hidePattern() {
        for (JButton b : tiles) {
            b.setBackground(Color.WHITE);
            b.setForeground(Color.BLACK);
            b.setText("");
        }
    }

    //Read selection
    public Set<Integer> getUserSelection() {
        Set<Integer> s = new HashSet<>();
        for (int i = 0; i < tiles.length; i++) {
            if ("●".equals(tiles[i].getText())) s.add(i);
        }
        return s;
    }
}