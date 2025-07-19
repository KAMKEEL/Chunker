package com.hivemc.chunker.mapping.parser;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.hivemc.chunker.mapping.MappingsFile;
import com.hivemc.chunker.nbt.tags.Tag;
import com.hivemc.chunker.nbt.tags.collection.CompoundTag;
import com.hivemc.chunker.nbt.tags.collection.ListTag;
import com.hivemc.chunker.nbt.tags.primitive.IntTag;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Parser that extends {@link SimpleMappingsParser} to allow using namespaced
 * output identifiers that are resolved to legacy IDs using a level.dat file.
 */
public final class LevelConvertMappingsParser {
    /** Pattern matching a legacy numeric id with data, e.g. 112:3 */
    private static final Pattern DATA_ID = Pattern.compile("^(\\d+):(\\d+)$");

    private LevelConvertMappingsParser() {
    }

    /**
     * Parse a simple mapping file resolving output identifiers using the provided level.dat.
     *
     * @param path     the mapping file to parse.
     * @param levelDat the level.dat to read IDs from.
     * @return a {@link MappingsFile} representing the mappings.
     */
    public static MappingsFile parse(Path path, File levelDat) throws IOException {
        Map<String, Integer> idMap = readLegacyIDs(levelDat);
        List<String> lines = Files.readAllLines(path);
        JsonArray array = new JsonArray();
        int index = 0;
        for (String line : lines) {
            String trimmed = line.trim();
            if (trimmed.isEmpty() || trimmed.startsWith("#")) {
                continue;
            }
            String[] parts = trimmed.split("->");
            if (parts.length != 2) {
                throw new IOException("Invalid mapping line: " + line);
            }
            ParsedIdentifier oldParsed = parseIdentifier(parts[0].trim());
            ParsedIdentifier newParsed = parseIdentifier(parts[1].trim());
            if (oldParsed.identifier.isEmpty() || newParsed.identifier.isEmpty()) {
                throw new IOException("Invalid mapping line: " + line);
            }
            String newIdentifier = newParsed.identifier;
            Matcher matcher = DATA_ID.matcher(newIdentifier);
            if (!matcher.matches()) {
                Integer id = idMap.get(newIdentifier);
                if (id == null) {
                    throw new IOException("Unknown identifier in level.dat: " + newIdentifier);
                }
                newIdentifier = String.valueOf(id);
            } else {
                newIdentifier = matcher.group(1);
                if (newParsed.states == null) newParsed.states = new JsonObject();
                newParsed.states.addProperty("data", Integer.parseInt(matcher.group(2)));
            }
            JsonObject obj = new JsonObject();
            obj.addProperty("old_identifier", oldParsed.identifier);
            obj.addProperty("new_identifier", newIdentifier);
            obj.addProperty("state_list", "*");
            if (oldParsed.states != null) {
                obj.add("old_state_values", oldParsed.states);
            }
            if (newParsed.states != null) {
                obj.add("new_state_values", newParsed.states);
            }
            array.add(obj);
            index++;
        }
        JsonObject root = new JsonObject();
        root.add("identifiers", array);
        return MappingsFile.load(root.toString());
    }

    private static ParsedIdentifier parseIdentifier(String input) throws IOException {
        String trimmed = input.trim();
        String identifierPart = trimmed;
        String statePart = null;

        int idx = trimmed.indexOf('[');
        if (idx != -1) {
            if (!trimmed.endsWith("]")) {
                throw new IOException("Invalid state section for: " + input);
            }
            identifierPart = trimmed.substring(0, idx).trim();
            statePart = trimmed.substring(idx + 1, trimmed.length() - 1);
        }

        JsonObject statesObj = null;
        if (statePart != null) {
            statesObj = new JsonObject();
            if (!statePart.isEmpty()) {
                String[] pairs = statePart.split(",");
                for (String pair : pairs) {
                    String[] kv = pair.trim().split("=");
                    if (kv.length != 2) {
                        throw new IOException("Invalid state entry: " + pair);
                    }
                    String key = kv[0].trim().toLowerCase();
                    String value = kv[1].trim();
                    statesObj.add(key, parseValue(value));
                }
            }
        }

        Matcher matcher = DATA_ID.matcher(identifierPart);
        String identifier = identifierPart;
        if (matcher.matches()) {
            identifier = matcher.group(1);
            if (statesObj == null) statesObj = new JsonObject();
            statesObj.addProperty("data", Integer.parseInt(matcher.group(2)));
        }

        return new ParsedIdentifier(identifier, statesObj);
    }

    private static Map<String, Integer> readLegacyIDs(File levelDat) throws IOException {
        Map<String, Integer> map = new HashMap<>();
        CompoundTag root = Tag.readPossibleGZipJavaNBT(levelDat);
        if (root == null) return map;
        CompoundTag meta = root.getCompound("FML");
        if (meta == null) {
            meta = root.getCompound("Forge");
        }
        if (meta == null) return map;

        // Locate the ItemData compound used in legacy FML versions (1.7.10)
        CompoundTag itemData = meta.getCompound("Item Data", null);
        if (itemData == null) {
            itemData = meta.getCompound("ItemData", null);
        }
        if (itemData == null) {
            throw new IOException("Missing ItemData in level.dat");
        }

        // ItemData can either be stored as a list of compounds or as a
        // compound mapping names to ids depending on Forge version
        Tag<?> idsTag = itemData.get("ItemData", Tag.class);
        if (idsTag instanceof ListTag<?, ?> listTag) {
            @SuppressWarnings("unchecked")
            ListTag<CompoundTag, ?> ids = (ListTag<CompoundTag, ?>) listTag;
            for (CompoundTag entry : ids) {
                String key = entry.getString("K", entry.getString("k", null));
                int value = entry.getInt("V", entry.getInt("v", -1));
                if (key != null && value != -1) {
                    map.put(key, value);
                }
            }
        } else if (idsTag instanceof CompoundTag comp) {
            for (Map.Entry<String, Tag<?>> e : comp) {
                if (e.getValue() instanceof IntTag intTag) {
                    map.put(e.getKey(), intTag.getValue());
                }
            }
        } else {
            throw new IOException("Missing ItemData list in level.dat");
        }
        return map;
    }

    private static com.google.gson.JsonPrimitive parseValue(String value) {
        if ("true".equalsIgnoreCase(value) || "false".equalsIgnoreCase(value)) {
            return new com.google.gson.JsonPrimitive(Boolean.parseBoolean(value));
        }
        try {
            return new com.google.gson.JsonPrimitive(Integer.parseInt(value));
        } catch (NumberFormatException ignored) {
        }
        return new com.google.gson.JsonPrimitive(value.toUpperCase());
    }

    private static class ParsedIdentifier {
        final String identifier;
        JsonObject states;
        ParsedIdentifier(String identifier, JsonObject states) {
            this.identifier = identifier;
            this.states = states;
        }
    }
}
