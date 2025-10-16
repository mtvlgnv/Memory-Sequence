import javax.swing.SwingUtilities;

public class Main {
    public static void main(String[] args) {
        // TODO: seed and timer 
        SwingUtilities.invokeLater(() -> {
            GameFrame f = new GameFrame();
            f.setVisible(true);
        });
    }
}
