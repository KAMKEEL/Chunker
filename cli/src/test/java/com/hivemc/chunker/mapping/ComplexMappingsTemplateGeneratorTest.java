package com.hivemc.chunker.mapping;

import com.hivemc.chunker.mapping.parser.ComplexMappingsTemplateGenerator;
import com.hivemc.chunker.mapping.parser.SimpleMappingsParser;
import org.junit.jupiter.api.Test;

import java.nio.file.Files;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.assertTrue;

/** Tests for generating the complex mapping templates. */
public class ComplexMappingsTemplateGeneratorTest {
    @Test
    public void testTemplatesWritten() throws Exception {
        Path dir = Files.createTempDirectory("complex");
        ComplexMappingsTemplateGenerator.writeTemplates(dir);
        String simple = Files.readString(dir.resolve(ComplexMappingsTemplateGenerator.SIMPLE_FILE_NAME));
        String json = Files.readString(dir.resolve(ComplexMappingsTemplateGenerator.JSON_FILE_NAME));
        assertTrue(simple.contains("minecraft:basalt"));
        assertTrue(json.contains("\"identifiers\""));
        // Validate parsers can read the templates
        SimpleMappingsParser.parse(dir.resolve(ComplexMappingsTemplateGenerator.SIMPLE_FILE_NAME));
        MappingsFile.load(json);
    }
}
