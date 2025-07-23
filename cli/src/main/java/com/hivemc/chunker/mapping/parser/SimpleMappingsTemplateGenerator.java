package com.hivemc.chunker.mapping.parser;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

/**
 * Utility that writes a template file demonstrating the simple
 * mappings format accepted by {@link SimpleMappingsParser}.
 */
public final class SimpleMappingsTemplateGenerator {
    private static final String TEMPLATE = """
# Simple block mappings template
# Format: old_identifier[state=value] -> new_identifier[state=value] -> state_list
# Example mapping modern basalt to legacy ID 3005
minecraft:basalt -> 3005
# Example mapping with state and data value
minecraft:oak_stairs[facing=EAST] -> 112:3
# Example mapping applying a legacy state type
minecraft:acacia_fence_gate -> etfuturum:acacia_fence_gate -> FENCE_GATE
""";

    private SimpleMappingsTemplateGenerator() {
    }

    /**
     * Writes the template to the specified path. If the file already exists it
     * will be overwritten.
     *
     * @param path target file
     * @throws IOException if writing fails
     */
    public static void writeTemplate(Path path) throws IOException {
        Files.writeString(path, TEMPLATE);
    }
}
