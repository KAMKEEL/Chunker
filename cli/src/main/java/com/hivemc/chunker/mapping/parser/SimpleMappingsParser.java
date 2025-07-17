package com.hivemc.chunker.mapping.parser;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import com.hivemc.chunker.mapping.MappingsFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Utility to parse a simple text mapping file into a {@link MappingsFile}.
 * <p>
 * The format is one mapping per line in the form:
 * <pre>
 * old_identifier[state=value] -> new_identifier[state=value]
 * </pre>
 * States are optional and are specified inside square brackets separated by
 * commas. Lines starting with '#' and empty lines are ignored.
 */
public final class SimpleMappingsParser {

    /** Pattern matching a legacy numeric id with data, e.g. 112:3 */
    private static final Pattern DATA_ID = Pattern.compile("^(\\d+):(\\d+)$");

    private SimpleMappingsParser() {
    }

    /**
     * Parse a simple mapping file.
     *
     * @param path the file to parse.
     * @return a {@link MappingsFile} representing the mappings.
     * @throws IOException if reading the file fails or the format is invalid.
     */
    public static MappingsFile parse(Path path) throws IOException {
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
            JsonObject obj = new JsonObject();
            obj.addProperty("old_identifier", oldParsed.identifier);
            obj.addProperty("new_identifier", newParsed.identifier);
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
                    String key = kv[0].trim();
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
            statesObj.add("data", new JsonPrimitive(Integer.parseInt(matcher.group(2))));
        }

        return new ParsedIdentifier(identifier, statesObj);
    }

    private static JsonPrimitive parseValue(String value) {
        if ("true".equalsIgnoreCase(value) || "false".equalsIgnoreCase(value)) {
            return new JsonPrimitive(Boolean.parseBoolean(value));
        }
        try {
            return new JsonPrimitive(Integer.parseInt(value));
        } catch (NumberFormatException ignored) {
        }
        return new JsonPrimitive(value.toUpperCase());
    }

    private record ParsedIdentifier(String identifier, JsonObject states) {
    }
}
