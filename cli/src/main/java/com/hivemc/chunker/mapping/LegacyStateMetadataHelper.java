package com.hivemc.chunker.mapping;

import com.hivemc.chunker.mapping.identifier.states.StateValue;
import com.hivemc.chunker.mapping.identifier.states.StateValueBoolean;
import com.hivemc.chunker.mapping.identifier.states.StateValueInt;
import com.hivemc.chunker.mapping.identifier.states.StateValueString;

import java.util.Map;

/**
 * Helper used to resolve legacy metadata values for common state groups such as
 * fence gates and trapdoors. The calculations replicate the behaviour of the
 * {@code JavaLegacyStateGroups} without requiring full state mappings.
 */
public final class LegacyStateMetadataHelper {
    private LegacyStateMetadataHelper() {
    }

    /**
     * Compute the legacy data value for a fence gate using the provided state
     * map.
     *
     * @param states normalized state map (lower-case keys)
     * @return the metadata value or null if facing is missing
     */
    public static Integer resolveFenceGate(Map<String, StateValue<?>> states) {
        StateValue<?> facingVal = states.get("facing");
        if (!(facingVal instanceof StateValueString facing)) return null;
        int orientation = switch (facing.getValue().toUpperCase()) {
            case "SOUTH" -> 0;
            case "WEST" -> 1;
            case "NORTH" -> 2;
            case "EAST" -> 3;
            default -> 0;
        };

        boolean powered = getBoolean(states.get("powered"));
        boolean open = getBoolean(states.get("open"));
        int data = orientation;
        if (powered) data += 4;
        if (open) data += 8;
        return data;
    }

    /**
     * Compute the legacy data value for a trapdoor using the provided state map.
     *
     * @param states normalized state map (lower-case keys)
     * @return the metadata value or null if facing or half is missing
     */
    public static Integer resolveTrapdoor(Map<String, StateValue<?>> states) {
        StateValue<?> facingVal = states.get("facing");
        StateValue<?> halfVal = states.get("half");
        if (!(facingVal instanceof StateValueString facing) ||
                !(halfVal instanceof StateValueString half)) {
            return null;
        }
        int orientation = switch (facing.getValue().toUpperCase()) {
            case "NORTH" -> 0;
            case "SOUTH" -> 1;
            case "WEST" -> 2;
            case "EAST" -> 3;
            default -> 0;
        };
        boolean open = getBoolean(states.get("open"));
        boolean top = half.getValue().equalsIgnoreCase("top");
        int data = orientation;
        if (open) data += 4;
        if (top) data += 8;
        return data;
    }

    private static boolean getBoolean(StateValue<?> value) {
        if (value instanceof StateValueBoolean b) {
            return b.getValue();
        }
        if (value instanceof StateValueString s) {
            return Boolean.parseBoolean(s.getValue());
        }
        if (value instanceof StateValueInt i) {
            return i.getValue() != 0;
        }
        return false;
    }
}
