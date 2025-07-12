package cache.config;

/**
 * Represents a single cache level (e.g., L1, L2) with its configuration
 * parameters.
 * This class is mapped directly from the JSON config file.
 */
public class CacheLevelConfig {
    public String name;
    public int size;
    public int blockSize;
    public int associativity;
    public int latency;
    public String replacementPolicy;

    /**
     * Performs basic validation of the configuration values.
     * Throws IllegalArgumentException if any value is invalid.
     */
    public void validate() {
        if (size <= 0 || blockSize <= 0 || associativity <= 0 || latency < 0) {
            throw new IllegalArgumentException("Invalid numeric values in cache level: " + name);
        }
        if (replacementPolicy == null || replacementPolicy.isBlank()) {
            throw new IllegalArgumentException("Missing replacement policy in cache level: " + name);
        }
    }

    @Override
    public String toString() {
        return String.format(
                "[%s] %dB, %dB blocks, %d-way, %d cycles, policy: %s",
                name, size, blockSize, associativity, latency, replacementPolicy);
    }
}
