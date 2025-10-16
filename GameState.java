import java.util.HashSet;
import java.util.Set;


public class GameState {

    private int level = 0;           
    private int lives = 3;           
    private int score = 0;

    private int currentGridSize = 3; 
    private Set<Integer> currentPattern = new HashSet<>();

    public void reset() {
        level = 0;
        lives = 3;
        score = 0;
        currentGridSize = 3;
        currentPattern.clear();
    }

    public void nextLevel() { 
        level++; 
    }
    
    public void decrementLevel() { 
        level = Math.max(1, level - 1); 
    }

    public int level() {
        return Math.max(1, level);
        }

    public int lives() { 
        return lives; 
    }

    public int score() {
        return score;
    }
    

    public void addScore(int delta) { 
        score += Math.max(0, delta); 
    }
    public void loseLife() {
        lives = Math.max(0, lives - 1);
    }

    public void setCurrentGridSize(int size) { 
        this.currentGridSize = size; 
    }

    public int currentGridSize() { 
        return currentGridSize; 
    }

    public void setCurrentPattern(Set<Integer> p) { 
        this.currentPattern = new HashSet<>(p); 
    }

    public Set<Integer> currentPattern() { 
        return new HashSet<>(currentPattern); 
    }
}