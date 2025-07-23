package com.hivemc.chunker.mapping;

import com.hivemc.chunker.conversion.encoding.base.resolver.identifier.state.StateMappingGroup;
import com.hivemc.chunker.conversion.intermediate.column.chunk.identifier.type.block.states.BlockState;
import com.hivemc.chunker.conversion.intermediate.column.chunk.identifier.type.block.states.BlockStateValue;
import com.hivemc.chunker.conversion.intermediate.column.chunk.identifier.type.block.states.vanilla.VanillaBlockStates;
import com.hivemc.chunker.conversion.encoding.java.base.resolver.identifier.legacy.JavaLegacyStateGroups;
import com.hivemc.chunker.mapping.identifier.states.StateValue;
import com.hivemc.chunker.mapping.identifier.states.StateValueBoolean;
import com.hivemc.chunker.mapping.identifier.states.StateValueInt;
import com.hivemc.chunker.mapping.identifier.states.StateValueString;
import it.unimi.dsi.fastutil.objects.Object2ObjectOpenHashMap;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.HashMap;
import java.util.Map;

/**
 * Helper used to resolve legacy metadata values by delegating to the existing
 * {@link JavaLegacyStateGroups} and {@link VanillaBlockStates} definitions.
 */
public final class LegacyStateMetadataHelper {
    private static final Map<String, StateMappingGroup> GROUP_LOOKUP = new HashMap<>();

    static {
        try {
            // Map legacy group names
            for (Field field : JavaLegacyStateGroups.class.getFields()) {
                if (Modifier.isStatic(field.getModifiers()) && field.getType() == StateMappingGroup.class) {
                    GROUP_LOOKUP.put(field.getName(), (StateMappingGroup) field.get(null));
                }
            }

        } catch (IllegalAccessException ex) {
            throw new ExceptionInInitializerError(ex);
        }
    }

    private LegacyStateMetadataHelper() {
    }

    /**
     * Resolve the legacy metadata value for a specific Java legacy state group
     * using the supplied states.
     *
     * @param groupName the name of the legacy state group, e.g. "FENCE_GATE".
     * @param states    map of state names to values (case-insensitive keys).
     * @return the computed metadata value or {@code null} if it could not be
     * resolved.
     */
    public static Integer resolve(String groupName, Map<String, StateValue<?>> states) {
        StateMappingGroup group = GROUP_LOOKUP.get(groupName);
        if (group == null) return null;

        Map<String, Object> outputs = new Object2ObjectOpenHashMap<>();
        group.applyOutput((state, useDefault) -> {
            StateValue<?> raw = states.get(state.getName().toLowerCase());
            BlockStateValue val = parse(state, raw);
            if (val == null && useDefault) return state.getDefault();
            return val;
        }, outputs);

        Object data = outputs.get("data");
        return data instanceof Integer ? (Integer) data : null;
    }

    private static BlockStateValue parse(BlockState<?> blockState, StateValue<?> value) {
        Class<?> type = blockState.getValues()[0].getClass();
        if (Enum.class.isAssignableFrom(type)) {
            String strValue;
            if (value instanceof StateValueBoolean b) {
                strValue = b.getValue() ? "TRUE" : "FALSE";
            } else if (value instanceof StateValueInt i) {
                strValue = String.valueOf(i.getValue());
            } else if (value instanceof StateValueString s) {
                strValue = s.getValue();
            } else {
                return null;
            }
            try {
                @SuppressWarnings("unchecked")
                Enum<?> e = Enum.valueOf((Class<Enum>) type, strValue.toUpperCase());
                return (BlockStateValue) e;
            } catch (IllegalArgumentException ex) {
                return null;
            }
        }
        return null;
    }
}
