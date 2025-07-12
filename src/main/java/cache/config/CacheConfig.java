package cache.config;

import java.util.List;

/**
 * Holds the entire cache hierarchy configuration.
 * Deserialized from JSON (list of levels).
 */
public class CacheConfig {

    public List<CacheLevelConfig> levels;

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Cache Hierarchy:\n");
        for (CacheLevelConfig level : levels) {
            sb.append("  ").append(level).append("\n");
        }
        return sb.toString();
    }

}
