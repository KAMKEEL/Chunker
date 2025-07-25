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
import com.hivemc.chunker.mapping.LevelConvertMappings;
import com.hivemc.chunker.mapping.parser.SimpleMappingsParser;
import com.hivemc.chunker.mapping.resolver.MappingsFileResolvers;
import com.hivemc.chunker.util.LegacyIdentifier;
import com.hivemc.chunker.conversion.encoding.java.base.JavaReaderWriter;
import com.hivemc.chunker.conversion.encoding.java.base.resolver.JavaResolvers;
import com.hivemc.chunker.nbt.tags.Tag;
import com.hivemc.chunker.nbt.tags.collection.CompoundTag;
import com.hivemc.chunker.nbt.tags.primitive.IntTag;
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
    public void testFenceGateOrientationOnOlderTarget() throws Exception {
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
                        VanillaBlockStates.FACING_HORIZONTAL, FacingDirectionHorizontal.WEST,
                        VanillaBlockStates.OPEN, Bool.TRUE,
                        VanillaBlockStates.POWERED, Bool.FALSE
                )
        );

        Optional<Identifier> result = resolver.from(input);
        assertTrue(result.isPresent());
        assertEquals("custom:sfg", result.get().getIdentifier());
        assertEquals(5, ((StateValueInt) result.get().getStates().get("data")).getValue());
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

    @Test
    public void testMappingForcesBaseData() throws Exception {
        File simple = File.createTempFile("simple", ".txt");
        simple.deleteOnExit();
        Files.writeString(simple.toPath(), "minecraft:end_rod -> custom:er[data=0]\n");

        MappingsFile mappings = SimpleMappingsParser.parse(simple.toPath());

        MockConverter converter = new MockConverter(null);
        converter.setBlockMappings(new MappingsFileResolvers(mappings));
        converter.setLegacySimpleMappings(true);

        JavaLegacyBlockIdentifierResolver resolver = new JavaLegacyBlockIdentifierResolver(
                converter, new Version(1, 7, 10), false, false);

        ChunkerBlockIdentifier input = new ChunkerBlockIdentifier(
                ChunkerVanillaBlockType.END_ROD,
                Map.of(VanillaBlockStates.FACING_ALL, FacingDirection.WEST)
        );

        Optional<Identifier> result = resolver.from(input);
        assertTrue(result.isPresent());
        assertEquals("custom:er", result.get().getIdentifier());
        // The data from the input (facing west -> 4) should override the mapping
        assertEquals(4, ((StateValueInt) result.get().getStates().get("data")).getValue());
    }

    @Test
    public void testCustomBlockWithLevelConvert() throws Exception {
        // Build minimal level.dat mapping custommod:block2 -> 1300
        CompoundTag root = new CompoundTag();
        CompoundTag fml = new CompoundTag();
        root.put("FML", fml);
        CompoundTag itemData = new CompoundTag();
        fml.put("ItemData", itemData);
        itemData.put("custommod:block2", new IntTag(1300));
        itemData.put("custommod:block", new IntTag(1299));

        File levelDat = File.createTempFile("level", ".dat");
        levelDat.deleteOnExit();
        Tag.writeGZipJavaNBT(levelDat, root);

        File mapping = File.createTempFile("mapping", ".txt");
        mapping.deleteOnExit();
        Files.writeString(mapping.toPath(), "custommod:block -> custommod:block2\n");

        LevelConvertMappings.load(levelDat);
        MappingsFile mappings = SimpleMappingsParser.parse(mapping.toPath());

        MockConverter converter = new MockConverter(null);
        converter.setBlockMappings(new MappingsFileResolvers(mappings));
        converter.setLegacySimpleMappings(true);

        JavaLegacyBlockIdentifierResolver resolver = new JavaLegacyBlockIdentifierResolver(
                converter, new Version(1, 7, 10), false, true);

        ChunkerBlockIdentifier input = ChunkerBlockIdentifier.custom(
                "custommod:block",
                Map.of("facing", "EAST")
        );

        Optional<Identifier> result = resolver.from(input);
        assertTrue(result.isPresent());
        assertEquals("1300", result.get().getIdentifier());
        assertEquals("EAST", result.get().getStates().get("facing").toString());
    }

    @Test
    public void testVanillaBlockWithLevelConvert() throws Exception {
        // Build minimal level.dat mapping etfuturum:end_rod -> 198
        CompoundTag root = new CompoundTag();
        CompoundTag fml = new CompoundTag();
        root.put("FML", fml);
        CompoundTag itemData = new CompoundTag();
        fml.put("ItemData", itemData);
        itemData.put("etfuturum:end_rod", new IntTag(198));

        File levelDat = File.createTempFile("level", ".dat");
        levelDat.deleteOnExit();
        Tag.writeGZipJavaNBT(levelDat, root);

        File mapping = File.createTempFile("mapping", ".txt");
        mapping.deleteOnExit();
        Files.writeString(mapping.toPath(), "minecraft:end_rod -> etfuturum:end_rod\n");

        LevelConvertMappings.load(levelDat);
        MappingsFile mappings = SimpleMappingsParser.parse(mapping.toPath());

        MockConverter converter = new MockConverter(null);
        converter.setBlockMappings(new MappingsFileResolvers(mappings));
        converter.setLegacySimpleMappings(true);

        JavaLegacyBlockIdentifierResolver resolver = new JavaLegacyBlockIdentifierResolver(
                converter, new Version(1, 7, 10), false, false);

        ChunkerBlockIdentifier input = new ChunkerBlockIdentifier(
                ChunkerVanillaBlockType.END_ROD,
                Map.of(VanillaBlockStates.FACING_ALL, FacingDirection.SOUTH)
        );

        Optional<Identifier> result = resolver.from(input);
        assertTrue(result.isPresent());
        assertEquals("198", result.get().getIdentifier());
        assertEquals(3, ((StateValueInt) result.get().getStates().get("data")).getValue());
    }

    @Test
    public void testGlazedTerracottaOrientationWithLevelConvert() throws Exception {
        // Build minimal level.dat mapping uptodate:glazed_terracotta_lime -> 1100
        CompoundTag root = new CompoundTag();
        CompoundTag fml = new CompoundTag();
        root.put("FML", fml);
        CompoundTag itemData = new CompoundTag();
        fml.put("ItemData", itemData);
        itemData.put("uptodate:glazed_terracotta_lime", new IntTag(1100));

        File levelDat = File.createTempFile("level", ".dat");
        levelDat.deleteOnExit();
        Tag.writeGZipJavaNBT(levelDat, root);

        File mapping = File.createTempFile("mapping", ".txt");
        mapping.deleteOnExit();
        Files.writeString(mapping.toPath(), "minecraft:lime_glazed_terracotta -> uptodate:glazed_terracotta_lime\n");

        LevelConvertMappings.load(levelDat);
        MappingsFile mappings = SimpleMappingsParser.parse(mapping.toPath());

        MockConverter converter = new MockConverter(null);
        converter.setBlockMappings(new MappingsFileResolvers(mappings));
        converter.setLegacySimpleMappings(true);

        JavaLegacyBlockIdentifierResolver resolver = new JavaLegacyBlockIdentifierResolver(
                converter, new Version(1, 7, 10), false, false);

        ChunkerBlockIdentifier input = new ChunkerBlockIdentifier(
                ChunkerVanillaBlockType.LIME_GLAZED_TERRACOTTA,
                Map.of(VanillaBlockStates.FACING_HORIZONTAL, FacingDirectionHorizontal.WEST)
        );

        Optional<Identifier> result = resolver.from(input);
        assertTrue(result.isPresent());
        assertEquals("1100", result.get().getIdentifier());
        assertEquals(1, ((StateValueInt) result.get().getStates().get("data")).getValue());
    }

    @Test
    public void testFenceGateOrientationWithLevelConvert() throws Exception {
        // Build minimal level.dat mapping uptodate:fence_gate_spruce -> 1200
        CompoundTag root = new CompoundTag();
        CompoundTag fml = new CompoundTag();
        root.put("FML", fml);
        CompoundTag itemData = new CompoundTag();
        fml.put("ItemData", itemData);
        itemData.put("uptodate:fence_gate_spruce", new IntTag(1200));

        File levelDat = File.createTempFile("level", ".dat");
        levelDat.deleteOnExit();
        Tag.writeGZipJavaNBT(levelDat, root);

        File mapping = File.createTempFile("mapping", ".txt");
        mapping.deleteOnExit();
        Files.writeString(mapping.toPath(), "minecraft:spruce_fence_gate -> uptodate:fence_gate_spruce\n");

        LevelConvertMappings.load(levelDat);
        MappingsFile mappings = SimpleMappingsParser.parse(mapping.toPath());

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
        assertEquals("1200", result.get().getIdentifier());
        assertEquals(7, ((StateValueInt) result.get().getStates().get("data")).getValue());
    }

    @Test
    public void testLevelConvertOverridesDefaultData() throws Exception {
        // Build minimal level.dat mapping etfuturum:end_rod -> 198
        CompoundTag root = new CompoundTag();
        CompoundTag fml = new CompoundTag();
        root.put("FML", fml);
        CompoundTag itemData = new CompoundTag();
        fml.put("ItemData", itemData);
        itemData.put("etfuturum:end_rod", new IntTag(198));

        File levelDat = File.createTempFile("level", ".dat");
        levelDat.deleteOnExit();
        Tag.writeGZipJavaNBT(levelDat, root);

        File mapping = File.createTempFile("mapping", ".txt");
        mapping.deleteOnExit();
        Files.writeString(mapping.toPath(), "minecraft:end_rod -> etfuturum:end_rod[data=0]\n");

        LevelConvertMappings.load(levelDat);
        MappingsFile mappings = SimpleMappingsParser.parse(mapping.toPath());

        MockConverter converter = new MockConverter(null);
        converter.setBlockMappings(new MappingsFileResolvers(mappings));
        converter.setLegacySimpleMappings(true);

        JavaLegacyBlockIdentifierResolver resolver = new JavaLegacyBlockIdentifierResolver(
                converter, new Version(1, 7, 10), false, false);

        ChunkerBlockIdentifier input = new ChunkerBlockIdentifier(
                ChunkerVanillaBlockType.END_ROD,
                Map.of(VanillaBlockStates.FACING_ALL, FacingDirection.WEST)
        );

        Optional<Identifier> result = resolver.from(input);
        assertTrue(result.isPresent());
        assertEquals("198", result.get().getIdentifier());
        assertEquals(4, ((StateValueInt) result.get().getStates().get("data")).getValue());
    }

    @Test
    public void testWriteLegacyIdentifierWithLevelConvert() throws Exception {
        // Build minimal level.dat mapping uptodate:glazed_terracotta_lime -> 1100
        CompoundTag root = new CompoundTag();
        CompoundTag fml = new CompoundTag();
        root.put("FML", fml);
        CompoundTag itemData = new CompoundTag();
        fml.put("ItemData", itemData);
        itemData.put("uptodate:glazed_terracotta_lime", new IntTag(1100));

        File levelDat = File.createTempFile("level", ".dat");
        levelDat.deleteOnExit();
        Tag.writeGZipJavaNBT(levelDat, root);

        File mapping = File.createTempFile("mapping", ".txt");
        mapping.deleteOnExit();
        Files.writeString(mapping.toPath(), "minecraft:lime_glazed_terracotta -> uptodate:glazed_terracotta_lime\n");

        LevelConvertMappings.load(levelDat);
        MappingsFile mappings = SimpleMappingsParser.parse(mapping.toPath());

        MockConverter converter = new MockConverter(null);
        converter.setBlockMappings(new MappingsFileResolvers(mappings));
        converter.setLegacySimpleMappings(true);

        JavaReaderWriter writer = new JavaReaderWriter() {
            @Override public boolean isReader() { return false; }
            @Override public Version getVersion() { return new Version(1, 7, 10); }
        };
        JavaResolvers resolvers = writer.buildResolvers(converter).build();

        ChunkerBlockIdentifier input = new ChunkerBlockIdentifier(
                ChunkerVanillaBlockType.LIME_GLAZED_TERRACOTTA,
                Map.of(VanillaBlockStates.FACING_HORIZONTAL, FacingDirectionHorizontal.WEST)
        );

        LegacyIdentifier legacy = resolvers.writeLegacyBlockIdentifier(input);
        assertEquals(1100, legacy.id());
        assertEquals(1, legacy.data());
    }

    @Test
    public void testEqualsPrefixDisablesLevelConvert() throws Exception {
        CompoundTag root = new CompoundTag();
        CompoundTag fml = new CompoundTag();
        root.put("FML", fml);
        CompoundTag itemData = new CompoundTag();
        fml.put("ItemData", itemData);
        itemData.put("custommod:block2", new IntTag(1300));

        File levelDat = File.createTempFile("level", ".dat");
        levelDat.deleteOnExit();
        Tag.writeGZipJavaNBT(levelDat, root);

        File mapping = File.createTempFile("mapping", ".txt");
        mapping.deleteOnExit();
        Files.writeString(mapping.toPath(), "=custommod:block -> custommod:block2\n");

        LevelConvertMappings.load(levelDat);
        MappingsFile mappings = SimpleMappingsParser.parse(mapping.toPath());

        MockConverter converter = new MockConverter(null);
        converter.setBlockMappings(new MappingsFileResolvers(mappings));
        converter.setLegacySimpleMappings(true);

        JavaLegacyBlockIdentifierResolver resolver = new JavaLegacyBlockIdentifierResolver(
                converter, new Version(1, 7, 10), false, true);

        ChunkerBlockIdentifier input = ChunkerBlockIdentifier.custom(
                "custommod:block",
                Map.of("facing", "EAST")
        );

        Optional<Identifier> result = resolver.from(input);
        assertTrue(result.isPresent());
        assertEquals("custommod:block2", result.get().getIdentifier());
    }

    @Test
    public void testNoPrefixUsesLegacyMapping() throws Exception {
        CompoundTag root = new CompoundTag();
        CompoundTag fml = new CompoundTag();
        root.put("FML", fml);
        CompoundTag itemData = new CompoundTag();
        fml.put("ItemData", itemData);
        itemData.put("custommod:block2", new IntTag(1300));
        itemData.put("custommod:block", new IntTag(1299));

        File levelDat = File.createTempFile("level", ".dat");
        levelDat.deleteOnExit();
        Tag.writeGZipJavaNBT(levelDat, root);

        File mapping = File.createTempFile("mapping", ".txt");
        mapping.deleteOnExit();
        Files.writeString(mapping.toPath(), "custommod:block -> custommod:block2\n");

        LevelConvertMappings.load(levelDat);
        MappingsFile mappings = SimpleMappingsParser.parse(mapping.toPath());

        MockConverter converter = new MockConverter(null);
        converter.setBlockMappings(new MappingsFileResolvers(mappings));
        converter.setLegacySimpleMappings(true);

        JavaLegacyBlockIdentifierResolver resolver = new JavaLegacyBlockIdentifierResolver(
                converter, new Version(1, 7, 10), false, true);

        ChunkerBlockIdentifier input = ChunkerBlockIdentifier.custom(
                "custommod:block",
                Map.of("facing", "EAST")
        );

        Optional<Identifier> result = resolver.from(input);
        assertTrue(result.isPresent());
        assertEquals("1300", result.get().getIdentifier());
    }

    @Test
    public void testEqualsPrefixSkippedWhenLegacyIdExists() throws Exception {
        CompoundTag root = new CompoundTag();
        CompoundTag fml = new CompoundTag();
        root.put("FML", fml);
        CompoundTag itemData = new CompoundTag();
        fml.put("ItemData", itemData);
        itemData.put("custommod:block2", new IntTag(1300));
        itemData.put("custommod:block", new IntTag(1299));

        File levelDat = File.createTempFile("level", ".dat");
        levelDat.deleteOnExit();
        Tag.writeGZipJavaNBT(levelDat, root);

        File mapping = File.createTempFile("mapping", ".txt");
        mapping.deleteOnExit();
        Files.writeString(mapping.toPath(), "=custommod:block -> custommod:block2\n");

        LevelConvertMappings.load(levelDat);
        MappingsFile mappings = SimpleMappingsParser.parse(mapping.toPath());

        MockConverter converter = new MockConverter(null);
        converter.setBlockMappings(new MappingsFileResolvers(mappings));
        converter.setLegacySimpleMappings(true);

        JavaLegacyBlockIdentifierResolver resolver = new JavaLegacyBlockIdentifierResolver(
                converter, new Version(1, 7, 10), false, true);

        ChunkerBlockIdentifier input = ChunkerBlockIdentifier.custom(
                "custommod:block",
                Map.of("facing", "EAST")
        );

        Optional<Identifier> result = resolver.from(input);
        assertTrue(result.isPresent());
        assertEquals("1299", result.get().getIdentifier());
    }
}

