package cache;

import cache.config.CacheConfig;
import cache.config.CacheLevelConfig;
import cache.utils.TraceReader;
import cache.simulator.CacheLevel;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.FileReader;
import java.util.List;

/**
 * Entry point for the Multilevel Cache Simulator.
 * Loads cache configuration and memory trace, and prepares for simulation.
 */
public class Main {

    public static void main(String[] args) {
        try {
            if (args.length < 2) {
                System.err.println("Usage: java -jar cache-simulator.jar <config.json> <trace.txt>");
                return;
            }

            String configPath = args[0];
            String tracePath = args[1];

            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            CacheConfig config = gson.fromJson(new FileReader(configPath), CacheConfig.class);

            for (CacheLevelConfig level : config.levels) {
                level.validate();
            }

            System.out.println("Cache configuration loaded successfully!");
            System.out.println(config);

            List<Long> addresses = TraceReader.loadAddresses(tracePath);
            System.out.println("Trace loaded: " + addresses.size() + " addresses.");
            System.out.println("First 5 addresses:");
            addresses.stream().limit(5).forEach(addr -> System.out.printf("  0x%X\n", addr));

            CacheLevel l1 = new CacheLevel(config.levels.get(0));

            int total = 0;
            for (long addr : addresses) {
                boolean hit = l1.access(addr);
                System.out.printf("Access 0x%X => %s\n", addr, hit ? "HIT" : "MISS");
                total++;
            }

            
            System.out.println("\n=== Simulation Complete ===");
            System.out.println("Total accesses: " + total);
            System.out.println("Hits: " + l1.getHits());
            System.out.println("Misses: " + l1.getMisses());
            
            double hitRate = total == 0 ? 0.0 : (100.0 * l1.getHits() / total);
            System.out.printf("Hit rate: %.2f%%\n", hitRate);
            
            l1.printStats();

        } catch (Exception e) {
            System.err.println("Error:");
            e.printStackTrace();
        }
    }
}
