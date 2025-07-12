package cache.config;

/**
 * Represents the configuration of a single cache level (e.g. L1, L2).
 * This class is used to deserialize JSON input into usable Java objects.
 */

public class CacheLevelConfig {

    public String name;
    public int size;
    public int blockSize;
    public int associativity;
    public int latency;
    public String replacementPolicy;

    @Override
    public String toString() {
        return String.format(
                "[%s] %dB, %dB blocks, %d-way, %d cycles, policy: %s",
                name, size, blockSize, associativity, latency, replacementPolicy);
    }

    public void validate() {
        if (size <= 0 || blockSize <= 0 || associativity <= 0 || latency < 0) {
            throw new IllegalArgumentException("Invalid values in cache level: " + name);
        }
        if (replacementPolicy == null || replacementPolicy.isBlank()) {
            throw new IllegalArgumentException("Missing replacement policy in cache level: " + name);
        }
    }

}
