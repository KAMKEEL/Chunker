package com.hivemc.chunker.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Utility to load simple identifier -> legacy ID mappings from a text file.
 * Lines should be in the format "identifier -> id" and may contain comments
 * starting with '#'.
 */
public final class LegacyBlockIDTranslationLoader {
    private LegacyBlockIDTranslationLoader() {}

    /**
     * Load a mapping file.
     *
     * @param file the translation file.
     * @return the parsed mappings.
     */
    public static Map<String, Integer> load(File file) throws IOException {
        Map<String, Integer> result = new HashMap<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                line = line.trim();
                if (line.isEmpty() || line.startsWith("#")) continue;
                String[] parts = line.split("->");
                if (parts.length != 2) continue;
                String key = parts[0].trim();
                String value = parts[1].trim();
                // Only parse number before any ':' if present
                int id;
                try {
                    if (value.contains(":")) {
                        id = Integer.parseInt(value.split(":" ,2)[0]);
                    } else {
                        id = Integer.parseInt(value);
                    }
                } catch (NumberFormatException e) {
                    continue; // skip invalid
                }
                result.put(key, id);
            }
        }
        return result;
    }
}
