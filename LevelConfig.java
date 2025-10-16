import java.util.*;


public final class LevelConfig {
    private static final Random RNG = new Random(); // TODO: seed

    private LevelConfig() {}

    public static int gridSizeForLevel(int level) {
        return 3 + (level - 1) / 2;
    }

    public static int showMillisForLevel(int level) {
        // TODO: make config from SettingsCustomization later
        return 3000;
    }

    public static int patternCount(int size, int level) {
        return 3 + level;
    }

    public static Set<Integer> generatePattern(int size, int level) {
        int cells = size * size;
        int k     = patternCount(size, level);

        List<Integer> pool = new ArrayList<>(cells);
        for (int i = 0; i < cells; i++) pool.add(i);
        Collections.shuffle(pool, RNG);

        Set<Integer> s = new HashSet<>();
        for (int i = 0; i < k && i < pool.size(); i++) s.add(pool.get(i));
        return s;
    }
}