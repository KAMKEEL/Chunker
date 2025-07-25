package com.hivemc.chunker.conversion.encoding.java.base.resolver.entity.legacy.handlers;

import com.hivemc.chunker.conversion.encoding.base.resolver.entity.EntityHandler;
import com.hivemc.chunker.conversion.encoding.java.base.resolver.JavaResolvers;
import com.hivemc.chunker.conversion.intermediate.column.chunk.identifier.type.block.states.vanilla.types.FacingDirectionHorizontal;
import com.hivemc.chunker.conversion.intermediate.column.entity.PaintingEntity;
import com.hivemc.chunker.conversion.intermediate.column.entity.type.ChunkerVanillaEntityType;
import com.hivemc.chunker.nbt.tags.collection.CompoundTag;
import org.jetbrains.annotations.NotNull;

/**
 * Handler for writing/reading Paintings.
 */
public class JavaLegacyPaintingEntityHandler extends EntityHandler<JavaResolvers, CompoundTag, PaintingEntity> {
    public JavaLegacyPaintingEntityHandler() {
        super(ChunkerVanillaEntityType.PAINTING, PaintingEntity.class, PaintingEntity::new);
    }

    @Override
    public void read(@NotNull JavaResolvers resolvers, @NotNull CompoundTag input, @NotNull PaintingEntity value) {
        String motive = input.getString("variant", null);
        if (motive == null) {
            motive = input.getString("Motive", "Kebab");
        }
        value.setMotive(resolvers.readPaintingMotive(motive));

        // Facing
        String name;
        if (input.contains("facing")) {
            name = "facing";
        } else if (input.contains("Facing")) {
            name = "Facing";
        } else {
            name = "Direction";
        }
        value.setDirection(FacingDirectionHorizontal.from2DByte(input.getByte(name, (byte) 0)));
    }

    @Override
    public void write(@NotNull JavaResolvers resolvers, @NotNull CompoundTag output, @NotNull PaintingEntity value) {
        output.put("Motive", resolvers.writePaintingMotive(value.getMotive()));
        if (resolvers.dataVersion().getVersion().isLessThan(1, 8, 0)) {
            output.put("Direction", value.getDirection() == null ? 0 : value.getDirection().to2DByte());
        } else {
            output.put("Facing", value.getDirection() == null ? 0 : value.getDirection().to2DByte());
        }
    }
}
