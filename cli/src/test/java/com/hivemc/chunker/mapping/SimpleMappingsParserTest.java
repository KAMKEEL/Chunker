package com.hivemc.chunker.mapping;

import com.hivemc.chunker.mapping.identifier.Identifier;
import com.hivemc.chunker.mapping.identifier.states.StateValueInt;
import com.hivemc.chunker.mapping.identifier.states.StateValueString;
import com.hivemc.chunker.mapping.parser.SimpleMappingsParser;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Collections;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Tests for the {@link SimpleMappingsParser} utility.
 */
public class SimpleMappingsParserTest {
    @Test
    public void testParseSimpleFile() throws IOException {
        File temp = File.createTempFile("simple", ".txt");
        temp.deleteOnExit();
        Files.writeString(temp.toPath(), "minecraft:wool -> minecraft:stone\n");

        MappingsFile mappingsFile = SimpleMappingsParser.parse(temp.toPath());
        Identifier input = new Identifier("minecraft:wool", Collections.emptyMap());
        assertEquals(new Identifier("minecraft:stone", Collections.emptyMap()), mappingsFile.convertBlock(input).orElse(null));
    }

    @Test
    public void testParseStates() throws IOException {
        File temp = File.createTempFile("simple", ".txt");
        temp.deleteOnExit();
        Files.writeString(temp.toPath(), "minecraft:stairs[facing=east] -> custom:stairs[data=3]\n");

        MappingsFile mappingsFile = SimpleMappingsParser.parse(temp.toPath());
        Identifier input = new Identifier("minecraft:stairs", Map.of("facing", new StateValueString("EAST")));
        Identifier expected = new Identifier("custom:stairs", Map.of("facing", new StateValueString("EAST"), "data", new StateValueInt(3)));

        assertEquals(expected, mappingsFile.convertBlock(input).orElse(null));
    }

    @Test
    public void testParseNumericId() throws IOException {
        File temp = File.createTempFile("simple", ".txt");
        temp.deleteOnExit();
        Files.writeString(temp.toPath(), "minecraft:stairs[facing=east] -> 112:3\n");

        MappingsFile mappingsFile = SimpleMappingsParser.parse(temp.toPath());
        Identifier input = new Identifier("minecraft:stairs", Map.of("facing", new StateValueString("EAST")));
        Identifier expected = new Identifier("112", Map.of("facing", new StateValueString("EAST"), "data", new StateValueInt(3)));
        assertEquals(expected, mappingsFile.convertBlock(input).orElse(null));
    }
}
