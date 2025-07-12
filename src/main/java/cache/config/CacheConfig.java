package cache.config;

import java.util.List;

/**
 * Container for the full cache hierarchy configuration.
 * Holds all levels defined in the JSON configuration file.
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
