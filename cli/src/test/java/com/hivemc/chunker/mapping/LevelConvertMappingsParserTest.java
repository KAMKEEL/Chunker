package com.hivemc.chunker.mapping;

import com.hivemc.chunker.mapping.identifier.Identifier;
import com.hivemc.chunker.mapping.identifier.states.StateValueInt;
import com.hivemc.chunker.mapping.identifier.states.StateValueString;
import com.hivemc.chunker.mapping.parser.LevelConvertMappingsParser;
import com.hivemc.chunker.nbt.tags.Tag;
import com.hivemc.chunker.nbt.tags.collection.CompoundTag;
import com.hivemc.chunker.nbt.tags.collection.ListTag;
import com.hivemc.chunker.nbt.tags.primitive.IntTag;
import com.hivemc.chunker.nbt.tags.primitive.StringTag;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.nio.file.Files;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

/** Tests for {@link LevelConvertMappingsParser}. */
public class LevelConvertMappingsParserTest {
    @Test
    public void testParseWithLevelDat() throws Exception {
        // Build minimal level.dat with block ids
        CompoundTag root = new CompoundTag();
        CompoundTag fml = new CompoundTag();
        root.put("FML", fml);
        CompoundTag itemData = new CompoundTag();
        fml.put("ItemData", itemData);
        ListTag<CompoundTag, ?> ids = new ListTag<>();
        CompoundTag entry = new CompoundTag();
        entry.put("K", new StringTag("etfuturum:stripped_acacia_log"));
        entry.put("V", new IntTag(1300));
        ids.add(entry);
        itemData.put("ItemData", ids);

        File levelDat = File.createTempFile("level", ".dat");
        levelDat.deleteOnExit();
        Tag.writeGZipJavaNBT(levelDat, root);

        // Simple mapping file
        File mapping = File.createTempFile("simple", ".txt");
        mapping.deleteOnExit();
        Files.writeString(mapping.toPath(),
                "minecraft:stripped_acacia_log[facing=NORTH] -> etfuturum:stripped_acacia_log[data=2]\n");

        MappingsFile mappingsFile = LevelConvertMappingsParser.parse(mapping.toPath(), levelDat);
        Identifier input = new Identifier("minecraft:stripped_acacia_log", Map.of(
                "facing", new StateValueString("NORTH")
        ));
        Identifier expected = new Identifier("1300", Map.of(
                "facing", new StateValueString("NORTH"),
                "data", new StateValueInt(2)
        ));
        assertEquals(expected, mappingsFile.convertBlock(input).orElse(null));
    }

    @Test
    public void testParseWithForgeFormat() throws Exception {
        CompoundTag root = new CompoundTag();
        CompoundTag forge = new CompoundTag();
        root.put("Forge", forge);
        CompoundTag itemData = new CompoundTag();
        forge.put("ItemData", itemData);
        CompoundTag ids = new CompoundTag();
        ids.put("etfuturum:stripped_acacia_log", new IntTag(1300));
        itemData.put("ItemData", ids);

        File levelDat = File.createTempFile("level2", ".dat");
        levelDat.deleteOnExit();
        Tag.writeGZipJavaNBT(levelDat, root);

        File mapping = File.createTempFile("simple2", ".txt");
        mapping.deleteOnExit();
        Files.writeString(mapping.toPath(),
                "minecraft:stripped_acacia_log[facing=NORTH] -> etfuturum:stripped_acacia_log[data=2]\n");

        MappingsFile mappingsFile = LevelConvertMappingsParser.parse(mapping.toPath(), levelDat);
        Identifier input = new Identifier("minecraft:stripped_acacia_log", Map.of(
                "facing", new StateValueString("NORTH")
        ));
        Identifier expected = new Identifier("1300", Map.of(
                "facing", new StateValueString("NORTH"),
                "data", new StateValueInt(2)
        ));
        assertEquals(expected, mappingsFile.convertBlock(input).orElse(null));
    }
}
