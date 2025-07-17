package com.hivemc.chunker.mapping;

import com.hivemc.chunker.cli.CLI;
import com.hivemc.chunker.mapping.identifier.Identifier;
import com.hivemc.chunker.mapping.parser.SimpleMappingsParser;
import com.hivemc.chunker.mapping.MappingsFile;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.nio.file.Files;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertEquals;

/** Tests merging of mappings from JSON and simple text files. */
public class SimpleMappingsMergeTest {
    @Test
    public void testMerge() throws Exception {
        String json = "{\n" +
                "  \"identifiers\": [ { \"old_identifier\": \"a\", \"new_identifier\": \"b\", \"state_list\": \"*\" } ]\n" +
                "}";
        MappingsFile base = MappingsFile.load(json);

        File temp = File.createTempFile("simple", ".txt");
        temp.deleteOnExit();
        Files.writeString(temp.toPath(), "c -> d\n");
        MappingsFile extra = SimpleMappingsParser.parse(temp.toPath());

        MappingsFile merged = CLI.mergeMappings(base, extra);
        assertEquals(new Identifier("b", Collections.emptyMap()), merged.convertBlock(new Identifier("a", Collections.emptyMap())).orElse(null));
        assertEquals(new Identifier("d", Collections.emptyMap()), merged.convertBlock(new Identifier("c", Collections.emptyMap())).orElse(null));
    }
}
