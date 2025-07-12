package cache.utils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

/**
 * Utility class to load memory access traces from a file.
 * Supports both hexadecimal (prefixed with 0x) and decimal addresses.
 */
public class TraceReader {

    /**
     * Loads a list of memory addresses from a text file.
     * Each line should contain a valid address in decimal or hex format.
     *
     * @param filePath path to the trace file
     * @return list of memory addresses as Long values
     * @throws Exception if the file cannot be read or contains invalid lines
     */
    public static List<Long> loadAddresses(String filePath) throws Exception {
        List<Long> addresses = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            int lineNumber = 1;
            while ((line = reader.readLine()) != null) {
                line = line.trim();
                if (line.isEmpty())
                    continue;

                try {
                    long address = line.startsWith("0x") || line.startsWith("0X")
                            ? Long.parseLong(line.substring(2), 16)
                            : Long.parseLong(line);
                    addresses.add(address);
                } catch (NumberFormatException e) {
                    throw new IllegalArgumentException("Invalid address at line " + lineNumber + ": " + line);
                }
                lineNumber++;
            }
        }
        return addresses;
    }
}
