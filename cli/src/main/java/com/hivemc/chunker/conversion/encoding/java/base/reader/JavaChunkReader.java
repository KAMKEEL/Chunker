package com.hivemc.chunker.conversion.encoding.java.base.reader;

import com.hivemc.chunker.conversion.encoding.base.Converter;
import com.hivemc.chunker.conversion.encoding.java.base.resolver.JavaResolvers;
import com.hivemc.chunker.conversion.intermediate.column.ChunkerColumn;
import com.hivemc.chunker.conversion.intermediate.column.chunk.ChunkerChunk;
import com.hivemc.chunker.conversion.intermediate.column.chunk.identifier.ChunkerBlockIdentifier;
import com.hivemc.chunker.conversion.intermediate.column.chunk.palette.ShortBasedPalette;
import com.hivemc.chunker.nbt.tags.collection.CompoundTag;
import com.hivemc.chunker.scheduling.task.Task;
import com.hivemc.chunker.scheduling.task.TaskWeight;
import com.hivemc.chunker.util.ByteUtil;
import com.hivemc.chunker.util.LegacyIdentifier;

/**
 * A reader for Java chunks.
 */
public class JavaChunkReader {
    protected final Converter converter;
    protected final JavaResolvers resolvers;
    protected final ChunkerColumn column;
    protected final ChunkerChunk chunk;

    /**
     * Create a new java chunk reader.
     *
     * @param converter the converter instance.
     * @param resolvers the resolvers to use.
     * @param column    the column the chunk belongs to.
     * @param chunk     the chunk instance.
     */
    public JavaChunkReader(Converter converter, JavaResolvers resolvers, ChunkerColumn column, ChunkerChunk chunk) {
        this.converter = converter;
        this.resolvers = resolvers;
        this.column = column;
        this.chunk = chunk;
    }

    /**
     * Read a chunk from Java NBT.
     *
     * @param nbt the chunk NBT.
     */
    public void readChunk(CompoundTag nbt) {
        Task.asyncConsume("Reading Palette", TaskWeight.HIGHER, this::readPalette, nbt);
        if (converter.shouldProcessLighting()) {
            Task.asyncConsume("Reading Lighting", TaskWeight.HIGHER, this::readLighting, nbt);
        }
    }

    /**
     * Read the palette from the chunk.
     *
     * @param nbt the chunk NBT.
     */
    protected void readPalette(CompoundTag nbt) {
        byte[] blocks = nbt.getByteArray("Blocks", null);
        if (blocks == null) {
            // If there's no blocks use an air palette
            chunk.setPalette(ChunkerBlockIdentifier.AIR.asFilledChunkPalette());
            return;
        }

        // Gather the data
        byte[] data = nbt.getByteArray("Data", null);
        byte[] extraData = nbt.getByteArray("Add", null);
        byte[] blocks16Bytes = nbt.getByteArray("Blocks16", null);
        short[] blocks16 = blocks16Bytes != null ? ByteUtil.bytesToShortArray(blocks16Bytes) : null;

        ShortBasedPalette<LegacyIdentifier> intPalette = new ShortBasedPalette<>(16, 16);
        for (int i = 0; i < 4096; i++) {
            // Co-ords
            int x = i & 0xF;
            int z = (i >> 4) & 0xF;
            int y = (i >> 8) & 0xF;

            // Nibble (half-byte arrays)
            int nibbleIndex = i >> 1;
            boolean lowestBits = (i & 1) == 0;

            int id = (blocks[i] & 0xFF);
            if (blocks16 != null) {
                // When the Blocks16 tag is present it contains the full 16-bit
                // ID for each block in big endian order, just like mIDas Platinum.
                id = blocks16[i] & 0xFFFF;
            } else if (extraData != null) {
                // Fallback for vanilla 12-bit ids using the Add array.
                id |= (ByteUtil.getNibbleFromByte(extraData[nibbleIndex], lowestBits) & 0xF) << 8;
            }

            // Create a legacy identifier, pairing the id and data
            LegacyIdentifier identifier = new LegacyIdentifier(
                    id,
                    data == null ? 0 : ByteUtil.getNibbleFromByte(data[nibbleIndex], lowestBits)
            );

            // Set the entry
            intPalette.set(x, y, z, identifier);
        }

        // Remap the palette and set it
        chunk.setPalette(intPalette.map(resolvers::readBlock));
    }

    /**
     * Read the lighting data from the chunk.
     *
     * @param nbt the chunk NBT.
     */
    protected void readLighting(CompoundTag nbt) {
        byte[] blockLight = nbt.getByteArray("BlockLight", null);
        byte[] skyLight = nbt.getByteArray("SkyLight", null);

        // Set up the values if they were found
        if (blockLight != null) {
            chunk.setBlockLight(new byte[16][16][16]);
        }
        if (skyLight != null) {
            chunk.setSkyLight(new byte[16][16][16]);
        }

        // Read the values out of both arrays
        if (blockLight != null || skyLight != null) {
            for (int i = 0; i < 4096; i++) {
                // Co-ords
                int x = i & 0xF;
                int z = (i >> 4) & 0xF;
                int y = (i >> 8) & 0xF;

                // Nibble (half-byte arrays)
                int nibbleIndex = i >> 1;
                boolean lowestBits = (i & 1) == 0;

                // If the blockLight is present convert it from the nibble array to a normal array
                if (blockLight != null) {
                    chunk.getBlockLight()[x][y][z] = ByteUtil.getNibbleFromByte(blockLight[nibbleIndex], lowestBits);
                }

                // If the skyLight is present convert it from the nibble array to a normal array
                if (skyLight != null) {
                    chunk.getSkyLight()[x][y][z] = ByteUtil.getNibbleFromByte(skyLight[nibbleIndex], lowestBits);
                }
            }
        }
    }
}
