package com.hivemc.chunker.conversion.encoding.java.base.resolver.entity.legacy.handlers;

import com.hivemc.chunker.conversion.encoding.base.resolver.entity.EntityHandler;
import com.hivemc.chunker.conversion.encoding.java.base.resolver.JavaResolvers;
import com.hivemc.chunker.conversion.intermediate.column.chunk.identifier.type.block.states.vanilla.types.FacingDirection;
import com.hivemc.chunker.conversion.intermediate.column.entity.ItemFrameEntity;
import com.hivemc.chunker.conversion.intermediate.column.entity.type.ChunkerVanillaEntityType;
import com.hivemc.chunker.nbt.tags.collection.CompoundTag;
import org.jetbrains.annotations.NotNull;

/**
 * Handler for writing/reading Item Frames.
 */
public class JavaLegacyItemFrameEntityHandler extends EntityHandler<JavaResolvers, CompoundTag, ItemFrameEntity> {
    public JavaLegacyItemFrameEntityHandler() {
        super(ChunkerVanillaEntityType.ITEM_FRAME, ItemFrameEntity.class, ItemFrameEntity::new);
    }

    @Override
    public void read(@NotNull JavaResolvers resolvers, @NotNull CompoundTag input, @NotNull ItemFrameEntity value) {
        // Read item
        CompoundTag item = input.getCompound("Item");
        if (item != null) {
            value.setItem(resolvers.readItem(item));
        }

        // Read rotation
        value.setItemRotation(input.getByte("ItemRotation", (byte) 0));

        // Facing
        String name;
        if (input.contains("facing")) {
            name = "facing";
        } else if (input.contains("Facing")) {
            name = "Facing";
        } else {
            name = "Direction";
        }
        value.setDirection(FacingDirection.from2DByte(input.getByte(name, (byte) 0)));
    }

    @Override
    public void write(@NotNull JavaResolvers resolvers, @NotNull CompoundTag output, @NotNull ItemFrameEntity value) {
        // Write item
        if (value.getItem() != null && !value.getItem().getIdentifier().isAir()) {
            resolvers.writeItem(value.getItem()).ifPresent(item -> output.put("Item", item));
        }

        // Write rotation
        output.put("ItemRotation", value.getItemRotation());

        // Write facing
        if (resolvers.dataVersion().getVersion().isLessThan(1, 8, 0)) {
            output.put("Direction", value.getDirection().to2DByte());
        } else {
            output.put("Facing", value.getDirection().to2DByte());
        }
    }
}
