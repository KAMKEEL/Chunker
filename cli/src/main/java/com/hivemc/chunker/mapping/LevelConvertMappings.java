package com.hivemc.chunker.mapping;

import com.hivemc.chunker.mapping.identifier.Identifier;
import com.hivemc.chunker.nbt.tags.Tag;
import com.hivemc.chunker.nbt.tags.collection.CompoundTag;
import com.hivemc.chunker.nbt.tags.collection.ListTag;
import com.hivemc.chunker.nbt.tags.primitive.IntTag;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Utility class used for converting namespaced identifiers from legacy worlds
 * using a provided {@code level.dat} ItemData map.
 */
public final class LevelConvertMappings {
    private LevelConvertMappings() {
    }

    /**
     * Read the namespace to id map from a legacy {@code level.dat} file.
     *
     * @param levelDat the legacy level.dat file.
     * @return a map of identifier to numeric id.
     */
    public static Map<String, Integer> readLegacyIDs(File levelDat) throws IOException {
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

    /**
     * Convert an identifier using the provided legacy mapping.
     *
     * @param identifier the identifier to convert.
     * @param idMap      the namespace to id map.
     * @return the converted identifier or the original if no mapping exists.
     */
    public static Identifier toLegacy(Identifier identifier, Map<String, Integer> idMap) {
        if (idMap == null || identifier.isVanilla()) {
            return identifier;
        }
        Integer id = idMap.get(identifier.getIdentifier());
        if (id == null) {
            return identifier;
        }
        return new Identifier(String.valueOf(id), identifier.getStates());
    }
}
