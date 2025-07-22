package com.hivemc.chunker.conversion.java.resolver.legacy;

import com.hivemc.chunker.conversion.bedrock.resolver.MockConverter;
import com.hivemc.chunker.conversion.encoding.base.Version;
import com.hivemc.chunker.conversion.encoding.java.base.resolver.identifier.legacy.JavaLegacyBlockIdentifierResolver;
import com.hivemc.chunker.conversion.intermediate.column.chunk.identifier.ChunkerBlockIdentifier;
import com.hivemc.chunker.conversion.intermediate.column.chunk.identifier.type.block.ChunkerVanillaBlockType;
import com.hivemc.chunker.conversion.intermediate.column.chunk.identifier.type.block.states.vanilla.VanillaBlockStates;
import com.hivemc.chunker.conversion.intermediate.column.chunk.identifier.type.block.states.vanilla.types.Bool;
import com.hivemc.chunker.conversion.intermediate.column.chunk.identifier.type.block.states.vanilla.types.FacingDirectionHorizontal;
import com.hivemc.chunker.conversion.intermediate.column.chunk.identifier.type.block.states.vanilla.types.FacingDirection;
import com.hivemc.chunker.mapping.identifier.states.StateValueInt;
import com.hivemc.chunker.mapping.MappingsFile;
import com.hivemc.chunker.mapping.identifier.Identifier;
import com.hivemc.chunker.mapping.parser.SimpleMappingsParser;
import com.hivemc.chunker.mapping.resolver.MappingsFileResolvers;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.nio.file.Files;
import java.util.Map;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

/** Tests for the legacy simple mappings feature. */
public class JavaLegacySimpleMappingsTest {

    @Test
    public void testLegacySimpleMappingApplied() throws Exception {
        File simple = File.createTempFile("simple", ".txt");
        simple.deleteOnExit();
        Files.writeString(simple.toPath(), "minecraft:red_sandstone_stairs -> custom:rs_stairs\n");

        MappingsFile mappings = SimpleMappingsParser.parse(simple.toPath());

        MockConverter converter = new MockConverter(null);
        converter.setBlockMappings(new MappingsFileResolvers(mappings));
        converter.setLegacySimpleMappings(true);

        JavaLegacyBlockIdentifierResolver resolver = new JavaLegacyBlockIdentifierResolver(
                converter, new Version(1, 12, 2), false, false);

        Optional<Identifier> result = resolver.from(new ChunkerBlockIdentifier(ChunkerVanillaBlockType.RED_SANDSTONE_STAIRS));
        assertTrue(result.isPresent());
        assertEquals("custom:rs_stairs", result.get().getIdentifier());
    }

    @Test
    public void testLegacyMappingPreservesStates() throws Exception {
        File simple = File.createTempFile("simple", ".txt");
        simple.deleteOnExit();
        Files.writeString(simple.toPath(), "minecraft:spruce_fence_gate -> custom:sfg\n");

        MappingsFile mappings = SimpleMappingsParser.parse(simple.toPath());

        MockConverter converter = new MockConverter(null);
        converter.setBlockMappings(new MappingsFileResolvers(mappings));
        converter.setLegacySimpleMappings(true);

        JavaLegacyBlockIdentifierResolver resolver = new JavaLegacyBlockIdentifierResolver(
                converter, new Version(1, 12, 2), false, false);

        ChunkerBlockIdentifier input = new ChunkerBlockIdentifier(
                ChunkerVanillaBlockType.SPRUCE_FENCE_GATE,
                Map.of(
                        VanillaBlockStates.FACING_HORIZONTAL, FacingDirectionHorizontal.EAST,
                        VanillaBlockStates.OPEN, Bool.TRUE,
                        VanillaBlockStates.POWERED, Bool.FALSE
                )
        );

        Optional<Identifier> result = resolver.from(input);
        assertTrue(result.isPresent());
        assertEquals("custom:sfg", result.get().getIdentifier());
        assertNotNull(result.get().getStates().get("data"));
    }

    @Test
    public void testLegacyMappingIgnoredByDefault() throws Exception {
        File simple = File.createTempFile("simple", ".txt");
        simple.deleteOnExit();
        Files.writeString(simple.toPath(), "minecraft:red_sandstone_stairs -> custom:rs_stairs\n");

        MappingsFile mappings = SimpleMappingsParser.parse(simple.toPath());

        MockConverter converter = new MockConverter(null);
        converter.setBlockMappings(new MappingsFileResolvers(mappings));

        JavaLegacyBlockIdentifierResolver resolver = new JavaLegacyBlockIdentifierResolver(
                converter, new Version(1, 12, 2), false, false);

        Optional<Identifier> result = resolver.from(new ChunkerBlockIdentifier(ChunkerVanillaBlockType.RED_SANDSTONE_STAIRS));
        assertTrue(result.isPresent());
        assertEquals("minecraft:red_sandstone_stairs", result.get().getIdentifier());
    }

    @Test
    public void testLegacyMappingWithOlderTargetVersion() throws Exception {
        File simple = File.createTempFile("simple", ".txt");
        simple.deleteOnExit();
        Files.writeString(simple.toPath(), "minecraft:spruce_fence_gate -> custom:sfg\n");

        MappingsFile mappings = SimpleMappingsParser.parse(simple.toPath());

        MockConverter converter = new MockConverter(null);
        converter.setBlockMappings(new MappingsFileResolvers(mappings));
        converter.setLegacySimpleMappings(true);

        JavaLegacyBlockIdentifierResolver resolver = new JavaLegacyBlockIdentifierResolver(
                converter, new Version(1, 7, 10), false, false);

        ChunkerBlockIdentifier input = new ChunkerBlockIdentifier(
                ChunkerVanillaBlockType.SPRUCE_FENCE_GATE,
                Map.of(
                        VanillaBlockStates.FACING_HORIZONTAL, FacingDirectionHorizontal.EAST,
                        VanillaBlockStates.OPEN, Bool.TRUE,
                        VanillaBlockStates.POWERED, Bool.FALSE
                )
        );

        Optional<Identifier> result = resolver.from(input);
        assertTrue(result.isPresent());
        assertEquals("custom:sfg", result.get().getIdentifier());
    }

    @Test
    public void testEndRodPreservesOrientationOnOlderTarget() throws Exception {
        File simple = File.createTempFile("simple", ".txt");
        simple.deleteOnExit();
        Files.writeString(simple.toPath(), "minecraft:end_rod -> custom:er\n");

        MappingsFile mappings = SimpleMappingsParser.parse(simple.toPath());

        MockConverter converter = new MockConverter(null);
        converter.setBlockMappings(new MappingsFileResolvers(mappings));
        converter.setLegacySimpleMappings(true);

        JavaLegacyBlockIdentifierResolver resolver = new JavaLegacyBlockIdentifierResolver(
                converter, new Version(1, 7, 10), false, false);

        ChunkerBlockIdentifier input = new ChunkerBlockIdentifier(
                ChunkerVanillaBlockType.END_ROD,
                Map.of(VanillaBlockStates.FACING_ALL, FacingDirection.EAST)
        );

        Optional<Identifier> result = resolver.from(input);
        assertTrue(result.isPresent());
        assertEquals("custom:er", result.get().getIdentifier());
        assertEquals(5, ((StateValueInt) result.get().getStates().get("data")).getValue());
    }
}

