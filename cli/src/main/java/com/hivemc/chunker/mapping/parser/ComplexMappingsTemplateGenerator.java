package com.hivemc.chunker.mapping.parser;

import com.hivemc.chunker.mapping.parser.SimpleMappingsParser;
import com.hivemc.chunker.mapping.MappingsFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

/**
 * Utility class that writes example block mapping templates demonstrating
 * various features. The generated output includes a JSON mapping file and a
 * simple text mapping file with state aware entries.
 *
 * Usage:
 * <pre>
 *   java com.hivemc.chunker.mapping.parser.ComplexMappingsTemplateGenerator /path/to/dir
 * </pre>
 * The files {@value #JSON_FILE_NAME} and {@value #SIMPLE_FILE_NAME} will be
 * written to the provided directory or the current working directory if no
 * argument is given.
 */
public final class ComplexMappingsTemplateGenerator {
    /** Name of the generated JSON mapping file. */
    public static final String JSON_FILE_NAME = "block_mappings.complex.json";

    /** Name of the generated simple mapping file. */
    public static final String SIMPLE_FILE_NAME = "simple_block_mappings.complex.txt";

    private static final String SIMPLE_TEMPLATE = """
# Complex simple block mappings template
# Format: old_identifier[state=value] -> new_identifier[state=value]
# Block name to numeric ID
minecraft:basalt -> 3005
# Block name to block name
minecraft:smooth_basalt -> minecraft:blackstone
# Block with different states mapped to different IDs
minecraft:spruce_trapdoor[facing=EAST,half=BOTTOM,open=FALSE] -> 3006:0
minecraft:spruce_trapdoor[facing=WEST,half=BOTTOM,open=FALSE] -> 3006:1
# Block name to block name with state modification
minecraft:oak_stairs[facing=EAST,half=BOTTOM] -> minecraft:oak_stairs[facing=WEST,half=TOP]
""";

    private static final String JSON_TEMPLATE = """
{
  "identifiers": [
    {
      "old_identifier": "minecraft:basalt",
      "new_identifier": "3005"
    },
    {
      "old_identifier": "minecraft:smooth_basalt",
      "new_identifier": "minecraft:blackstone"
    },
    {
      "old_identifier": "minecraft:spruce_trapdoor",
      "old_state_values": {
        "facing": "EAST",
        "half": "BOTTOM",
        "open": false
      },
      "new_identifier": "3006",
      "new_state_values": {
        "data": 0
      }
    },
    {
      "old_identifier": "minecraft:spruce_trapdoor",
      "old_state_values": {
        "facing": "WEST",
        "half": "BOTTOM",
        "open": false
      },
      "new_identifier": "3006",
      "new_state_values": {
        "data": 1
      }
    },
    {
      "old_identifier": "minecraft:oak_stairs",
      "old_state_values": {
        "facing": "EAST",
        "half": "BOTTOM"
      },
      "new_identifier": "minecraft:oak_stairs",
      "new_state_values": {
        "facing": "WEST",
        "half": "TOP"
      }
    }
  ]
}
""";

    private ComplexMappingsTemplateGenerator() {
    }

    /**
     * Write both complex template files to the given directory.
     *
     * @param directory output directory
     * @throws IOException if writing fails
     */
    public static void writeTemplates(Path directory) throws IOException {
        Files.createDirectories(directory);
        Files.writeString(directory.resolve(JSON_FILE_NAME), JSON_TEMPLATE);
        Files.writeString(directory.resolve(SIMPLE_FILE_NAME), SIMPLE_TEMPLATE);
    }

    /**
     * Generates the templates to the directory provided as the first argument or
     * the current working directory if no argument is given.
     */
    public static void main(String[] args) throws Exception {
        Path dir = args.length > 0 ? Path.of(args[0]) : Path.of(".");
        writeTemplates(dir);
        // verify valid
        MappingsFile.load(JSON_TEMPLATE);
        SimpleMappingsParser.parse(dir.resolve(SIMPLE_FILE_NAME));
        System.out.println("Complex templates written to " + dir.toAbsolutePath());
    }
}
