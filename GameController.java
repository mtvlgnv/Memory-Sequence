import javax.swing.*;
import java.util.HashSet;
import java.util.Set;

public class GameController {

    private final GameFrame frame;
    private final GameState state = new GameState(); 

    private boolean acceptingInput = false; // UI via isAcceptingInput()

    public GameController(GameFrame frame) {
        this.frame = frame;
    }

    //  Buttons 

    public void startNewRun() {
        state.reset();
        frame.showGame();
        frame.getGamePanel().updateHud(state.level(), state.lives(), state.score());
    }

    public void onUserPressedNext() {
        // advance to next level    
        state.nextLevel();

        int size = LevelConfig.gridSizeForLevel(state.level());
        state.setCurrentGridSize(size);

        // (re)build grid
        frame.getGamePanel().buildGrid(size);

        // generate pattern for this level
        Set<Integer> pattern = LevelConfig.generatePattern(size, state.level());
        state.setCurrentPattern(pattern);

        // show pattern, then hide after time
        frame.getGamePanel().showPattern(pattern);
        acceptingInput = false;

        int ms = LevelConfig.showMillisForLevel(state.level()); 
        new javax.swing.Timer(ms, e -> {
            ((Timer) e.getSource()).stop();
            frame.getGamePanel().hidePattern();
            acceptingInput = true;
        }).start();

        frame.getGamePanel().updateHud(state.level(), state.lives(), state.score());
    }

    public void onUserPressedConfirm(Set<Integer> selectionFromView) {
        if (!acceptingInput) return;

        Set<Integer> user = new HashSet<>(selectionFromView);
        Set<Integer> target = state.currentPattern();

        boolean correct = user.equals(target);
        if (correct) {
            int gained = Scoring.pointsFor(state.level());
            state.addScore(gained);
            acceptingInput = false;
            ManualDialog.info(frame, "Correct!", "OK");
            frame.getGamePanel().updateHud(state.level(), state.lives(), state.score());
        } else {
            state.loseLife();
            frame.getGamePanel().updateHud(state.level(), state.lives(), state.score());

            if (state.lives() <= 0) {
                acceptingInput = false;
                frame.showGameOver(state.score(), Math.max(0, state.level() - 1));
            } else {
                acceptingInput = false;
                ManualDialog.error(frame, "Wrong! Try again.", "Oops");
                state.decrementLevel();
                onUserPressedNext(); 
            }
        }
    }

    public void onUserPressedGiveUp() {
        acceptingInput = false;
        frame.showGameOver(state.score(), Math.max(0, state.level() - 1));
    }

    public boolean isAcceptingInput() {
        return acceptingInput;
    }
}