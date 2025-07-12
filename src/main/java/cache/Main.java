package cache;

import cache.config.CacheConfig;
import cache.config.CacheLevelConfig;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.FileReader;

public class Main {
    public static void main(String[] args) {
        try {
            String configPath = "cache-config.json"; // later make this dynamic
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            CacheConfig config = gson.fromJson(new FileReader(configPath), CacheConfig.class);

            for (CacheLevelConfig level : config.levels) {
                level.validate();
            }

            System.out.println("Cache configuration loaded successfully!");
            System.out.println(config);
        } catch (Exception e) {
            System.err.println("Failed to load cache configuration:");
            e.printStackTrace();
        }
    }
}
