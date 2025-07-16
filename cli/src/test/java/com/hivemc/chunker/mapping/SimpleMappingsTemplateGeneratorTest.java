package com.hivemc.chunker.mapping;

import com.hivemc.chunker.mapping.parser.SimpleMappingsParser;
import com.hivemc.chunker.mapping.parser.SimpleMappingsTemplateGenerator;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.nio.file.Files;

import static org.junit.jupiter.api.Assertions.assertTrue;

/** Tests for generating the simple mappings template. */
public class SimpleMappingsTemplateGeneratorTest {
    @Test
    public void testTemplateWritten() throws Exception {
        File temp = File.createTempFile("template", ".txt");
        temp.deleteOnExit();
        SimpleMappingsTemplateGenerator.writeTemplate(temp.toPath());
        String content = Files.readString(temp.toPath());
        assertTrue(content.contains("minecraft:basalt"));
        // Ensure parser can read the template
        SimpleMappingsParser.parse(temp.toPath());
    }
}
