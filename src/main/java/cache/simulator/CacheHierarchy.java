package cache.simulator;

import cache.config.CacheConfig;
import cache.config.CacheLevelConfig;

import java.util.ArrayList;
import java.util.List;

/**
 * Simulates a multi-level cache hierarchy (e.g. L1 → L2 → ...).
 */
public class CacheHierarchy {
    private final List<CacheLevel> levels = new ArrayList<>();

    public CacheHierarchy(CacheConfig config) {
        for (CacheLevelConfig levelConfig : config.levels) {
            levels.add(new CacheLevel(levelConfig));
        }
    }

    /**
     * Attempts to access the address in the cache hierarchy.
     * Returns true if any level hits.
     */
    public boolean access(long address) {
        for (int i = 0; i < levels.size(); i++) {
            boolean hit = levels.get(i).access(address);
            if (hit) {
                // Optionally: promote to upper level in future versions
                return true;
            }
        }
        return false;
    }

    public void printStats() {
        for (CacheLevel level : levels) {
            level.printStats();
        }
    }

    public int getTotalAccesses() {
        if (levels.isEmpty())
            return 0;
        return levels.get(0).getHits() + levels.get(0).getMisses();
    }

    public double getOverallHitRate() {
        if (levels.isEmpty())
            return 0.0;
        int total = levels.get(0).getHits() + levels.get(0).getMisses();
        return total == 0 ? 0.0 : (100.0 * levels.get(0).getHits() / total);
    }

}
