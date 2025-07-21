package com.hivemc.chunker.mapping.parser;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.hivemc.chunker.mapping.MappingsFile;
import com.hivemc.chunker.mapping.identifier.Identifier;
import com.hivemc.chunker.mapping.identifier.states.StateValue;
import com.hivemc.chunker.mapping.identifier.states.StateValueBoolean;
import com.hivemc.chunker.mapping.identifier.states.StateValueInt;
import com.hivemc.chunker.mapping.identifier.states.StateValueString;
import com.hivemc.chunker.conversion.WorldConverter;
import com.hivemc.chunker.conversion.encoding.base.Version;
import com.hivemc.chunker.conversion.encoding.java.base.resolver.identifier.JavaBlockIdentifierResolver;
import com.hivemc.chunker.conversion.encoding.java.base.resolver.identifier.legacy.JavaLegacyBlockIdentifierResolver;
import com.hivemc.chunker.conversion.intermediate.column.chunk.identifier.ChunkerBlockIdentifier;
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

        // Build temporary resolvers to modernise legacy identifiers
        WorldConverter dummyConverter = new WorldConverter(java.util.UUID.randomUUID());
        JavaLegacyBlockIdentifierResolver legacyResolver = new JavaLegacyBlockIdentifierResolver(
                dummyConverter, new Version(1, 12, 2), true, false);
        JavaBlockIdentifierResolver modernResolver = new JavaBlockIdentifierResolver(
                dummyConverter, Version.LATEST, true, false);

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

            // Build Identifier objects for conversion
            Identifier oldIdentifierObj = toIdentifier(oldParsed);

            // If the mapping output is a legacy block, convert the input identifier using the default
            // modern -> legacy mapping so direction/meta states match
            if (isLegacyMapping(newParsed, idMap)) {
                java.util.Optional<ChunkerBlockIdentifier> intermediate = legacyResolver.to(oldIdentifierObj);
                if (intermediate.isPresent()) {
                    java.util.Optional<Identifier> modern = modernResolver.from(intermediate.get());
                    if (modern.isPresent()) {
                        oldIdentifierObj = modern.get();
                    }
                }
            }

            String oldIdentifier = oldIdentifierObj.getIdentifier();
            String newIdentifier = resolveIdentifier(idMap, newParsed);

            JsonObject obj = new JsonObject();
            obj.addProperty("old_identifier", oldIdentifier);
            obj.addProperty("new_identifier", newIdentifier);
            obj.addProperty("state_list", "*");
            if (!oldIdentifierObj.getStates().isEmpty()) {
                obj.add("old_state_values", toJson(oldIdentifierObj.getStates()));
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

    private static Identifier toIdentifier(ParsedIdentifier parsed) {
        if (parsed.states == null) {
            return new Identifier(parsed.identifier);
        }
        Map<String, Object> map = new java.util.HashMap<>(parsed.states.size());
        for (Map.Entry<String, com.google.gson.JsonElement> entry : parsed.states.entrySet()) {
            com.google.gson.JsonPrimitive prim = entry.getValue().getAsJsonPrimitive();
            if (prim.isBoolean()) {
                map.put(entry.getKey(), prim.getAsBoolean());
            } else if (prim.isNumber()) {
                map.put(entry.getKey(), prim.getAsInt());
            } else {
                map.put(entry.getKey(), prim.getAsString());
            }
        }
        return Identifier.fromBoxed(parsed.identifier, map);
    }

    private static JsonObject toJson(Map<String, StateValue<?>> states) {
        JsonObject obj = new JsonObject();
        for (Map.Entry<String, StateValue<?>> entry : states.entrySet()) {
            StateValue<?> val = entry.getValue();
            if (val instanceof StateValueBoolean b) {
                obj.addProperty(entry.getKey(), b.getValue());
            } else if (val instanceof StateValueInt i) {
                obj.addProperty(entry.getKey(), i.getValue());
            } else if (val instanceof StateValueString s) {
                obj.addProperty(entry.getKey(), s.getValue());
            }
        }
        return obj;
    }

    private static boolean isLegacyMapping(ParsedIdentifier newParsed, Map<String, Integer> idMap) {
        if (DATA_ID.matcher(newParsed.identifier).matches()) {
            return true;
        }
        return idMap.containsKey(newParsed.identifier);
    }

    private static String resolveIdentifier(Map<String, Integer> idMap, ParsedIdentifier parsed) throws IOException {
        if(parsed.identifier.startsWith("minecraft:"))
            return parsed.identifier;
        String identifier = parsed.identifier;
        Matcher matcher = DATA_ID.matcher(identifier);
        if (!matcher.matches()) {
            Integer id = idMap.get(identifier);
            if (id == null) {
                return identifier;
            }
            return String.valueOf(id);
        }

        if (parsed.states == null) parsed.states = new JsonObject();
        parsed.states.addProperty("data", Integer.parseInt(matcher.group(2)));
        return matcher.group(1);
    }

    private static Map<String, Integer> readLegacyIDs(File levelDat) throws IOException {
        Map<String, Integer> map = new HashMap<>();

        // 1) raw root, no "Data" unwrapping
        CompoundTag root = Tag.readRawJavaNBT(levelDat);
        if (root == null) return map;

        // 2) find FML/Forge
        CompoundTag meta = root.contains("FML") ? root.getCompound("FML")
                : root.contains("Forge") ? root.getCompound("Forge")
                : null;
        if (meta == null) return map;

        // 3) grab the raw ItemData tag (could be list or compound)
        Tag<?> itemTag = meta.get("ItemData");
        if (itemTag == null) itemTag = meta.get("Item Data");
        if (itemTag == null) {
            throw new IOException("Missing ItemData in level.dat");
        }

        // CASE 1: top‐level list of compounds
        if (itemTag instanceof ListTag<?,?> listRaw) {
            for (Tag<?> elem : listRaw) {
                if (elem instanceof CompoundTag entry) {
                    String key = entry.getString("K", null);
                    int    val = entry.getInt   ("V", -1);
                    if (key != null && val >= 0) {
                        String cleaned = key.trim().replace("\u0002", "").replace("\u0003", "").replace("\u0001", "");
                        map.put(cleaned, val);
                    }
                }
            }

            // CASE 2 & 3: a compound under "ItemData"
        } else if (itemTag instanceof CompoundTag itemData) {
            // first see if it has its own nested list
            Tag<?> nested = itemData.get("ItemData");
            if (nested instanceof ListTag<?,?> nestedRaw) {
                for (Tag<?> elem : nestedRaw) {
                    if (elem instanceof CompoundTag entry) {
                        String key = entry.getString("K", null);
                        int    val = entry.getInt   ("V", -1);
                        if (key != null && val >= 0) {
                            String cleaned = key.trim().replace("\u0002", "").replace("\u0003", "").replace("\u0001", "");
                            map.put(cleaned, val);
                        }
                    }
                }
            } else {
                // fallback: any IntTag directly under itemData is name→id
                for (Map.Entry<String, Tag<?>> e : itemData.getValue().entrySet()) {
                    if (e.getValue() instanceof IntTag it) {
                        String cleaned = e.getKey().replace("\u0002", "").replace("\u0003", "").replace("\u0001", "");
                        map.put(cleaned, it.getValue());
                    }
                }
            }

        } else {
            throw new IOException(
                    "Unexpected ItemData tag type: " + itemTag.getClass().getSimpleName()
            );
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
