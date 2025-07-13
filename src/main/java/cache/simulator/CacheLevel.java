package cache.simulator;

import cache.config.CacheLevelConfig;

import java.util.*;

/**
 * Simulates a single cache level with basic hit/miss behavior.
 */
public class CacheLevel {
    private final String name;
    private final int blockSize;
    private final int associativity;
    private final int numSets;
    private final List<Deque<Long>> sets; // each set is a queue of tags

    private int hits = 0;
    private int misses = 0;

    public CacheLevel(CacheLevelConfig config) {
        this.name = config.name;
        this.blockSize = config.blockSize;
        this.associativity = config.associativity;
        this.numSets = config.size / (blockSize * associativity);

        this.sets = new ArrayList<>(numSets);
        for (int i = 0; i < numSets; i++) {
            sets.add(new ArrayDeque<>());
        }
    }

    /**
     * Simulates an access to the cache. Updates hit/miss counters.
     *
     * @param address memory address in bytes
     * @return true if hit, false if miss
     */
    public boolean access(long address) {
        long blockAddress = address / blockSize;
        int setIndex = (int) (blockAddress % numSets);
        long tag = blockAddress / numSets;

        Deque<Long> set = sets.get(setIndex);

        if (set.contains(tag)) {
            hits++;
            return true;
        } else {
            misses++;
            if (set.size() >= associativity) {
                set.pollFirst(); // evict oldest (FIFO for now)
            }
            set.addLast(tag);
            return false;
        }
    }

    public int getHits() {
        return hits;
    }

    public int getMisses() {
        return misses;
    }

    public String getName() {
        return name;
    }

    public void printStats() {
        int total = hits + misses;
        double hitRate = total == 0 ? 0.0 : (100.0 * hits / total);
        System.out.printf("[%s] Hits: %d, Misses: %d, Hit Rate: %.2f%%%n", name, hits, misses, hitRate);
    }
}
