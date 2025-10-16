import javax.swing.*;
import java.awt.*;

public class GameFrame extends JFrame {

    public static final String CARD_START = "start";
    public static final String CARD_GAME  = "game";
    public static final String CARD_OVER  = "over";

    private final CardLayout cards = new CardLayout();
    private final JPanel root = new JPanel(cards);

    private final StartPanel startPanel;
    private final GamePanel gamePanel;
    private final GameOverPanel overPanel;

    private final GameController controller;

    public GameFrame() {
        super("Memory Tiles");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(520, 700);
        setLocationRelativeTo(null);

        controller = new GameController(this);

        startPanel = new StartPanel(() -> controller.startNewRun());
        gamePanel  = new GamePanel(controller);
        overPanel  = new GameOverPanel(() -> showStart());

        root.add(startPanel, CARD_START);
        root.add(gamePanel,  CARD_GAME);
        root.add(overPanel,  CARD_OVER);

        setContentPane(root);
        showStart();
    }


    public void showStart() {
        cards.show(root, CARD_START);
        // TODO: maybe show "last score" under the Start button
    }

    public void showGame() {
        cards.show(root, CARD_GAME);
    }

    public void showGameOver(int score, int levelsPassed) {
        overPanel.setFinalStats(score, levelsPassed);
        cards.show(root, CARD_OVER);
    }

    GamePanel getGamePanel() {
        return gamePanel;
    }
}