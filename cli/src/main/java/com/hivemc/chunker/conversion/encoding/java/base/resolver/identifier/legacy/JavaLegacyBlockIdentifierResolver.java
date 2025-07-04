package com.hivemc.chunker.conversion.encoding.java.base.resolver.identifier.legacy;

import com.google.common.collect.ImmutableMultimap;
import com.hivemc.chunker.conversion.encoding.base.Converter;
import com.hivemc.chunker.conversion.encoding.base.Version;
import com.hivemc.chunker.conversion.encoding.base.resolver.identifier.BlockMapping;
import com.hivemc.chunker.conversion.encoding.base.resolver.identifier.ChunkerBlockIdentifierResolver;
import com.hivemc.chunker.conversion.encoding.base.resolver.identifier.state.StateMappingGroup;
import com.hivemc.chunker.conversion.intermediate.column.chunk.identifier.type.block.ChunkerBlockType;
import com.hivemc.chunker.conversion.intermediate.column.chunk.identifier.type.block.ChunkerVanillaBlockType;
import com.hivemc.chunker.conversion.intermediate.column.chunk.identifier.type.block.states.BlockStateValue;
import com.hivemc.chunker.conversion.intermediate.column.chunk.identifier.type.block.states.vanilla.VanillaBlockStates;
import com.hivemc.chunker.conversion.intermediate.column.chunk.identifier.type.block.states.vanilla.types.*;
import it.unimi.dsi.fastutil.Pair;

import java.util.List;
import java.util.Map;

/**
 * Resolver to convert between Java legacy block identifiers and ChunkerBlockIdentifier.
 */
public class JavaLegacyBlockIdentifierResolver extends ChunkerBlockIdentifierResolver {
    /**
     * Default states which use 0 for data and false for waterlogged.
     */
    public static final StateMappingGroup DEFAULT_DATA_WATERLOGGED_FALSE = new StateMappingGroup.Builder()
            .defaultOutput(VanillaBlockStates.WATERLOGGED, Bool.FALSE)
            .defaultInput("data", 0)
            .build();

    /**
     * Create a new legacy java block identifier resolver.
     *
     * @param converter                the converter instance.
     * @param version                  the version being resolved.
     * @param reader                   whether this is used for the reader.
     * @param customIdentifiersAllowed whether unknown identifiers should be converted to custom identifiers.
     */
    public JavaLegacyBlockIdentifierResolver(Converter converter, Version version, boolean reader, boolean customIdentifiersAllowed) {
        super(converter, version, reader, customIdentifiersAllowed);
    }

    @Override
    public void registerMappings(Version version) {
        extraStateMappingGroup(DEFAULT_DATA_WATERLOGGED_FALSE);

        // Air
        register(BlockMapping.of("minecraft:air", ChunkerVanillaBlockType.AIR));
        registerDuplicateInput(BlockMapping.of("minecraft:air", ChunkerVanillaBlockType.VOID_AIR));
        registerDuplicateInput(BlockMapping.of("minecraft:air", ChunkerVanillaBlockType.CAVE_AIR));

        // Blocks
        register(BlockMapping.of("minecraft:beacon", ChunkerVanillaBlockType.BEACON));
        register(BlockMapping.of("minecraft:bedrock", ChunkerVanillaBlockType.BEDROCK, JavaLegacyStateGroups.BEDROCK));
        register(BlockMapping.of("minecraft:bookshelf", ChunkerVanillaBlockType.BOOKSHELF));
        register(BlockMapping.of("minecraft:brewing_stand", ChunkerVanillaBlockType.BREWING_STAND, JavaLegacyStateGroups.BREWING_STAND));
        register(BlockMapping.of("minecraft:brick_block", ChunkerVanillaBlockType.BRICKS));
        register(BlockMapping.of("minecraft:brown_mushroom", ChunkerVanillaBlockType.BROWN_MUSHROOM));
        register(BlockMapping.of("minecraft:cake", ChunkerVanillaBlockType.CAKE, JavaLegacyStateGroups.CAKE));
        register(BlockMapping.of("minecraft:clay", ChunkerVanillaBlockType.CLAY));
        register(BlockMapping.of("minecraft:coal_block", ChunkerVanillaBlockType.COAL_BLOCK));
        register(BlockMapping.of("minecraft:coal_ore", ChunkerVanillaBlockType.COAL_ORE));
        register(BlockMapping.of("minecraft:cobblestone", ChunkerVanillaBlockType.COBBLESTONE));
        register(BlockMapping.of("minecraft:web", ChunkerVanillaBlockType.COBWEB));
        register(BlockMapping.of("minecraft:cocoa", ChunkerVanillaBlockType.COCOA, JavaLegacyStateGroups.COCOA));
        register(BlockMapping.of("minecraft:crafting_table", ChunkerVanillaBlockType.CRAFTING_TABLE));
        register(BlockMapping.of("minecraft:yellow_flower", ChunkerVanillaBlockType.DANDELION));
        register(BlockMapping.of("minecraft:deadbush", ChunkerVanillaBlockType.DEAD_BUSH));
        register(BlockMapping.of("minecraft:diamond_block", ChunkerVanillaBlockType.DIAMOND_BLOCK));
        register(BlockMapping.of("minecraft:diamond_ore", ChunkerVanillaBlockType.DIAMOND_ORE));
        register(BlockMapping.of("minecraft:dragon_egg", ChunkerVanillaBlockType.DRAGON_EGG));
        register(BlockMapping.of("minecraft:emerald_block", ChunkerVanillaBlockType.EMERALD_BLOCK));
        register(BlockMapping.of("minecraft:emerald_ore", ChunkerVanillaBlockType.EMERALD_ORE));
        register(BlockMapping.of("minecraft:enchanting_table", ChunkerVanillaBlockType.ENCHANTING_TABLE));
        register(BlockMapping.of("minecraft:end_portal", ChunkerVanillaBlockType.END_PORTAL));
        register(BlockMapping.of("minecraft:end_portal_frame", ChunkerVanillaBlockType.END_PORTAL_FRAME, JavaLegacyStateGroups.END_PORTAL_FRAME));
        register(BlockMapping.of("minecraft:end_stone", ChunkerVanillaBlockType.END_STONE));
        register(BlockMapping.of("minecraft:farmland", ChunkerVanillaBlockType.FARMLAND, JavaLegacyStateGroups.FARMLAND));
        register(BlockMapping.of("minecraft:fire", ChunkerVanillaBlockType.FIRE, JavaLegacyStateGroups.FIRE));
        register(BlockMapping.of("minecraft:glass", ChunkerVanillaBlockType.GLASS));
        register(BlockMapping.of("minecraft:glowstone", ChunkerVanillaBlockType.GLOWSTONE));
        register(BlockMapping.of("minecraft:gold_block", ChunkerVanillaBlockType.GOLD_BLOCK));
        register(BlockMapping.of("minecraft:gold_ore", ChunkerVanillaBlockType.GOLD_ORE));
        register(BlockMapping.of("minecraft:gravel", ChunkerVanillaBlockType.GRAVEL));
        register(BlockMapping.of("minecraft:hopper", ChunkerVanillaBlockType.HOPPER, JavaLegacyStateGroups.HOPPER));
        register(BlockMapping.of("minecraft:ice", ChunkerVanillaBlockType.ICE));
        register(BlockMapping.of("minecraft:iron_block", ChunkerVanillaBlockType.IRON_BLOCK));
        register(BlockMapping.of("minecraft:iron_ore", ChunkerVanillaBlockType.IRON_ORE));
        register(BlockMapping.of("minecraft:jukebox", ChunkerVanillaBlockType.JUKEBOX, JavaLegacyStateGroups.JUKEBOX));
        register(BlockMapping.of("minecraft:lapis_block", ChunkerVanillaBlockType.LAPIS_BLOCK));
        register(BlockMapping.of("minecraft:lapis_ore", ChunkerVanillaBlockType.LAPIS_ORE));
        register(BlockMapping.of("minecraft:waterlily", ChunkerVanillaBlockType.LILY_PAD));
        register(BlockMapping.of("minecraft:melon_block", ChunkerVanillaBlockType.MELON));
        register(BlockMapping.of("minecraft:mossy_cobblestone", ChunkerVanillaBlockType.MOSSY_COBBLESTONE));
        register(BlockMapping.of("minecraft:nether_brick", ChunkerVanillaBlockType.NETHER_BRICKS));
        register(BlockMapping.of("minecraft:portal", ChunkerVanillaBlockType.NETHER_PORTAL, JavaLegacyStateGroups.NETHER_PORTAL));
        register(BlockMapping.of("minecraft:quartz_ore", ChunkerVanillaBlockType.NETHER_QUARTZ_ORE));
        register(BlockMapping.of("minecraft:netherrack", ChunkerVanillaBlockType.NETHERRACK));
        register(BlockMapping.of("minecraft:noteblock", ChunkerVanillaBlockType.NOTE_BLOCK, JavaLegacyStateGroups.NOTE_BLOCK));
        register(BlockMapping.of("minecraft:obsidian", ChunkerVanillaBlockType.OBSIDIAN));
        register(BlockMapping.of("minecraft:packed_ice", ChunkerVanillaBlockType.PACKED_ICE));
        register(BlockMapping.of("minecraft:piston_head", ChunkerVanillaBlockType.PISTON_HEAD, JavaLegacyStateGroups.PISTON_HEAD));
        register(BlockMapping.of("minecraft:pumpkin", ChunkerVanillaBlockType.CARVED_PUMPKIN, JavaLegacyStateGroups.FACING_HORIZONTAL_SWNE));
        register(BlockMapping.of("minecraft:lit_pumpkin", ChunkerVanillaBlockType.JACK_O_LANTERN, JavaLegacyStateGroups.FACING_HORIZONTAL_SWNE));
        register(BlockMapping.of("minecraft:rail", ChunkerVanillaBlockType.RAIL, JavaLegacyStateGroups.RAIL));
        register(BlockMapping.of("minecraft:red_mushroom", ChunkerVanillaBlockType.RED_MUSHROOM));
        register(BlockMapping.of("minecraft:redstone_block", ChunkerVanillaBlockType.REDSTONE_BLOCK));
        register(BlockMapping.of("minecraft:redstone_wire", ChunkerVanillaBlockType.REDSTONE_WIRE, JavaLegacyStateGroups.REDSTONE_WIRE));
        register(BlockMapping.of("minecraft:sea_lantern", ChunkerVanillaBlockType.SEA_LANTERN));
        register(BlockMapping.of("minecraft:slime", ChunkerVanillaBlockType.SLIME_BLOCK));
        register(BlockMapping.of("minecraft:snow_layer", ChunkerVanillaBlockType.SNOW, JavaLegacyStateGroups.SNOW));
        register(BlockMapping.of("minecraft:snow", ChunkerVanillaBlockType.SNOW_BLOCK));
        register(BlockMapping.of("minecraft:soul_sand", ChunkerVanillaBlockType.SOUL_SAND));
        register(BlockMapping.of("minecraft:mob_spawner", ChunkerVanillaBlockType.SPAWNER));
        register(BlockMapping.of("minecraft:hardened_clay", ChunkerVanillaBlockType.TERRACOTTA));
        register(BlockMapping.of("minecraft:tnt", ChunkerVanillaBlockType.TNT, JavaLegacyStateGroups.TNT));
        register(BlockMapping.of("minecraft:tripwire", ChunkerVanillaBlockType.TRIPWIRE, JavaLegacyStateGroups.TRIPWIRE));
        register(BlockMapping.of("minecraft:tripwire_hook", ChunkerVanillaBlockType.TRIPWIRE_HOOK, JavaLegacyStateGroups.TRIPWIRE_HOOK));
        register(BlockMapping.of("minecraft:vine", ChunkerVanillaBlockType.VINE, JavaLegacyStateGroups.VINE));

        // Sponge
        register(BlockMapping.flatten("minecraft:sponge", "data",
                ImmutableMultimap.<Integer, ChunkerVanillaBlockType>builder()
                        .put(0, ChunkerVanillaBlockType.SPONGE)
                        .put(1, ChunkerVanillaBlockType.WET_SPONGE)
                        .build()
        ));
        registerDuplicateOutput(BlockMapping.of("minecraft:sponge", ChunkerVanillaBlockType.SPONGE));

        // Prismarine
        register(BlockMapping.of("minecraft:prismarine", ChunkerVanillaBlockType.PRISMARINE));
        register(BlockMapping.of("minecraft:prismarine", "data", 1, ChunkerVanillaBlockType.PRISMARINE_BRICKS));
        register(BlockMapping.of("minecraft:prismarine", "data", 2, ChunkerVanillaBlockType.DARK_PRISMARINE));

        // Moving Block (not equivalent to moving piston)
        registerDuplicateInput(BlockMapping.of("minecraft:air", ChunkerVanillaBlockType.MOVING_BLOCK_BEDROCK));
        register(BlockMapping.of("minecraft:piston_extension", ChunkerVanillaBlockType.MOVING_PISTON_JAVA, JavaLegacyStateGroups.PISTON_EXTENSION));

        // Barrier (also use barrier for invisible_bedrock)
        register(BlockMapping.of("minecraft:barrier", ChunkerVanillaBlockType.BARRIER));
        registerDuplicateInput(BlockMapping.of("minecraft:barrier", ChunkerVanillaBlockType.INVISIBLE_BEDROCK));

        // Signs
        register(BlockMapping.of("minecraft:standing_sign", ChunkerVanillaBlockType.OAK_SIGN, JavaLegacyStateGroups.ROTATION));
        register(BlockMapping.of("minecraft:wall_sign", ChunkerVanillaBlockType.OAK_WALL_SIGN, JavaLegacyStateGroups.FACING_HORIZONTAL_UNUSUAL));

        // Cauldron
        register(BlockMapping.of("minecraft:cauldron", "data", 0, ChunkerVanillaBlockType.CAULDRON));

        // Cauldron levels should be mapped to water cauldron since in newer versions the block is split
        register(BlockMapping.of("minecraft:cauldron", ChunkerVanillaBlockType.WATER_CAULDRON, JavaLegacyStateGroups.CAULDRON));

        // Pistons
        register(BlockMapping.group(ImmutableMultimap.<String, ChunkerVanillaBlockType>builder()
                        .put("minecraft:piston", ChunkerVanillaBlockType.PISTON)
                        .put("minecraft:sticky_piston", ChunkerVanillaBlockType.STICKY_PISTON)
                        .build(),
                JavaLegacyStateGroups.PISTON));

        register(BlockMapping.group(ImmutableMultimap.<String, ChunkerVanillaBlockType>builder()
                        .put("minecraft:dispenser", ChunkerVanillaBlockType.DISPENSER)
                        .put("minecraft:dropper", ChunkerVanillaBlockType.DROPPER)
                        .build(),
                JavaLegacyStateGroups.FACING_TRIGGERED));

        register(BlockMapping.of("minecraft:lever", ChunkerVanillaBlockType.LEVER, JavaLegacyStateGroups.LEVER));

        // Buttons
        register(BlockMapping.group(ImmutableMultimap.<String, ChunkerVanillaBlockType>builder()
                        .put("minecraft:wooden_button", ChunkerVanillaBlockType.OAK_BUTTON)
                        .put("minecraft:stone_button", ChunkerVanillaBlockType.STONE_BUTTON)
                        .build(),
                JavaLegacyStateGroups.BUTTON));
        register(BlockMapping.group(ImmutableMultimap.<String, ChunkerVanillaBlockType>builder()
                        .put("minecraft:heavy_weighted_pressure_plate", ChunkerVanillaBlockType.HEAVY_WEIGHTED_PRESSURE_PLATE)
                        .put("minecraft:light_weighted_pressure_plate", ChunkerVanillaBlockType.LIGHT_WEIGHTED_PRESSURE_PLATE)
                        .build(),
                JavaLegacyStateGroups.POWER));

        // Sapling
        register(BlockMapping.flatten("minecraft:sapling", "data", VanillaBlockStates.STAGE,
                ImmutableMultimap.<Integer, Pair<Stage, ? extends ChunkerBlockType>>builder()
                        .put(0, Pair.of(Stage._0, ChunkerVanillaBlockType.OAK_SAPLING))
                        .put(1, Pair.of(Stage._0, ChunkerVanillaBlockType.SPRUCE_SAPLING))
                        .put(2, Pair.of(Stage._0, ChunkerVanillaBlockType.BIRCH_SAPLING))
                        .put(3, Pair.of(Stage._0, ChunkerVanillaBlockType.JUNGLE_SAPLING))
                        .put(4, Pair.of(Stage._0, ChunkerVanillaBlockType.ACACIA_SAPLING))
                        .put(5, Pair.of(Stage._0, ChunkerVanillaBlockType.DARK_OAK_SAPLING))
                        .put(8, Pair.of(Stage._1, ChunkerVanillaBlockType.OAK_SAPLING))
                        .put(9, Pair.of(Stage._1, ChunkerVanillaBlockType.SPRUCE_SAPLING))
                        .put(10, Pair.of(Stage._1, ChunkerVanillaBlockType.BIRCH_SAPLING))
                        .put(11, Pair.of(Stage._1, ChunkerVanillaBlockType.JUNGLE_SAPLING))
                        .put(12, Pair.of(Stage._1, ChunkerVanillaBlockType.ACACIA_SAPLING))
                        .put(13, Pair.of(Stage._1, ChunkerVanillaBlockType.DARK_OAK_SAPLING))
                        .build()
        ));

        // Trapdoors
        register(BlockMapping.group(ImmutableMultimap.<String, ChunkerVanillaBlockType>builder()
                        .put("minecraft:iron_trapdoor", ChunkerVanillaBlockType.IRON_TRAPDOOR)
                        .put("minecraft:trapdoor", ChunkerVanillaBlockType.OAK_TRAPDOOR)
                        .build(),
                JavaLegacyStateGroups.TRAPDOOR));

        register(BlockMapping.group(ImmutableMultimap.<String, ChunkerVanillaBlockType>builder()
                        .put("minecraft:cactus", ChunkerVanillaBlockType.CACTUS)
                        .put("minecraft:reeds", ChunkerVanillaBlockType.SUGAR_CANE)
                        .build(),
                JavaLegacyStateGroups.AGE_15));

        register(BlockMapping.group(ImmutableMultimap.<String, ChunkerVanillaBlockType>builder()
                        .put("minecraft:ender_chest", ChunkerVanillaBlockType.ENDER_CHEST)
                        .put("minecraft:ladder", ChunkerVanillaBlockType.LADDER)
                        .build(),
                JavaLegacyStateGroups.FACING_HORIZONTAL_UNUSUAL));

        // Pressure plates
        register(BlockMapping.group(ImmutableMultimap.<String, ChunkerVanillaBlockType>builder()
                        .put("minecraft:wooden_pressure_plate", ChunkerVanillaBlockType.OAK_PRESSURE_PLATE)
                        .put("minecraft:stone_pressure_plate", ChunkerVanillaBlockType.STONE_PRESSURE_PLATE)
                        .build(),
                JavaLegacyStateGroups.POWERED));
        register(BlockMapping.of("minecraft:nether_wart", ChunkerVanillaBlockType.NETHER_WART, JavaLegacyStateGroups.AGE_3));

        // Fence gates
        register(BlockMapping.group(ImmutableMultimap.<String, ChunkerVanillaBlockType>builder()
                        .put("minecraft:acacia_fence_gate", ChunkerVanillaBlockType.ACACIA_FENCE_GATE)
                        .put("minecraft:birch_fence_gate", ChunkerVanillaBlockType.BIRCH_FENCE_GATE)
                        .put("minecraft:dark_oak_fence_gate", ChunkerVanillaBlockType.DARK_OAK_FENCE_GATE)
                        .put("minecraft:jungle_fence_gate", ChunkerVanillaBlockType.JUNGLE_FENCE_GATE)
                        .put("minecraft:fence_gate", ChunkerVanillaBlockType.OAK_FENCE_GATE)
                        .put("minecraft:spruce_fence_gate", ChunkerVanillaBlockType.SPRUCE_FENCE_GATE)
                        .build(),
                JavaLegacyStateGroups.FENCE_GATE));

        // Chests
        register(BlockMapping.group(ImmutableMultimap.<String, ChunkerVanillaBlockType>builder()
                        .put("minecraft:chest", ChunkerVanillaBlockType.CHEST)
                        .put("minecraft:trapped_chest", ChunkerVanillaBlockType.TRAPPED_CHEST)
                        .build(),
                JavaLegacyStateGroups.CHEST));

        // Crops
        register(BlockMapping.group(ImmutableMultimap.<String, ChunkerVanillaBlockType>builder()
                        .put("minecraft:carrots", ChunkerVanillaBlockType.CARROTS)
                        .put("minecraft:melon_stem", ChunkerVanillaBlockType.MELON_STEM)
                        .put("minecraft:potatoes", ChunkerVanillaBlockType.POTATOES)
                        .put("minecraft:pumpkin_stem", ChunkerVanillaBlockType.PUMPKIN_STEM)
                        .put("minecraft:wheat", ChunkerVanillaBlockType.WHEAT)
                        .build(),
                JavaLegacyStateGroups.AGE_7));

        // Stems
        register(BlockMapping.group("growth", 7, ImmutableMultimap.<String, ChunkerVanillaBlockType>builder()
                        .put("minecraft:pumpkin_stem", ChunkerVanillaBlockType.ATTACHED_PUMPKIN_STEM)
                        .put("minecraft:melon_stem", ChunkerVanillaBlockType.ATTACHED_MELON_STEM)
                        .build(),
                JavaLegacyStateGroups.STEM_FACING));

        // Redstone based rails
        register(BlockMapping.group(ImmutableMultimap.<String, ChunkerVanillaBlockType>builder()
                        .put("minecraft:activator_rail", ChunkerVanillaBlockType.ACTIVATOR_RAIL)
                        .put("minecraft:detector_rail", ChunkerVanillaBlockType.DETECTOR_RAIL)
                        .put("minecraft:golden_rail", ChunkerVanillaBlockType.POWERED_RAIL)
                        .build(),
                JavaLegacyStateGroups.POWERED_RAIL));
        register(BlockMapping.of("minecraft:hay_block", ChunkerVanillaBlockType.HAY_BLOCK, JavaLegacyStateGroups.AXIS));

        // Command blocks
        register(BlockMapping.of("minecraft:command_block", ChunkerVanillaBlockType.COMMAND_BLOCK, JavaLegacyStateGroups.COMMAND_BLOCK));

        // Grass variants
        register(BlockMapping.group(ImmutableMultimap.<String, ChunkerVanillaBlockType>builder()
                        .put("minecraft:grass", ChunkerVanillaBlockType.GRASS_BLOCK)
                        .put("minecraft:mycelium", ChunkerVanillaBlockType.MYCELIUM)
                        .build(),
                JavaLegacyStateGroups.SNOWY));

        // Stairs
        register(BlockMapping.group(ImmutableMultimap.<String, ChunkerVanillaBlockType>builder()
                        .put("minecraft:acacia_stairs", ChunkerVanillaBlockType.ACACIA_STAIRS)
                        .put("minecraft:birch_stairs", ChunkerVanillaBlockType.BIRCH_STAIRS)
                        .put("minecraft:brick_stairs", ChunkerVanillaBlockType.BRICK_STAIRS)
                        .put("minecraft:dark_oak_stairs", ChunkerVanillaBlockType.DARK_OAK_STAIRS)
                        .put("minecraft:jungle_stairs", ChunkerVanillaBlockType.JUNGLE_STAIRS)
                        .put("minecraft:nether_brick_stairs", ChunkerVanillaBlockType.NETHER_BRICK_STAIRS)
                        .put("minecraft:oak_stairs", ChunkerVanillaBlockType.OAK_STAIRS)
                        .put("minecraft:quartz_stairs", ChunkerVanillaBlockType.QUARTZ_STAIRS)
                        .put("minecraft:red_sandstone_stairs", ChunkerVanillaBlockType.RED_SANDSTONE_STAIRS)
                        .put("minecraft:sandstone_stairs", ChunkerVanillaBlockType.SANDSTONE_STAIRS)
                        .put("minecraft:spruce_stairs", ChunkerVanillaBlockType.SPRUCE_STAIRS)
                        .put("minecraft:stone_brick_stairs", ChunkerVanillaBlockType.STONE_BRICK_STAIRS)
                        .put("minecraft:stone_stairs", ChunkerVanillaBlockType.COBBLESTONE_STAIRS)
                        .build(),
                JavaLegacyStateGroups.STAIRS));

        // Doors
        register(BlockMapping.group(ImmutableMultimap.<String, ChunkerVanillaBlockType>builder()
                        .put("minecraft:iron_door", ChunkerVanillaBlockType.IRON_DOOR)
                        .put("minecraft:wooden_door", ChunkerVanillaBlockType.OAK_DOOR)
                        .build(),
                JavaLegacyStateGroups.DOOR));

        // New doors, don't have as many valid states
        register(BlockMapping.group(ImmutableMultimap.<String, ChunkerVanillaBlockType>builder()
                        .put("minecraft:acacia_door", ChunkerVanillaBlockType.ACACIA_DOOR)
                        .put("minecraft:birch_door", ChunkerVanillaBlockType.BIRCH_DOOR)
                        .put("minecraft:dark_oak_door", ChunkerVanillaBlockType.DARK_OAK_DOOR)
                        .put("minecraft:jungle_door", ChunkerVanillaBlockType.JUNGLE_DOOR)
                        .put("minecraft:spruce_door", ChunkerVanillaBlockType.SPRUCE_DOOR)
                        .build(),
                JavaLegacyStateGroups.DOOR_2));

        // Beds
        register(BlockMapping.of("minecraft:bed", ChunkerVanillaBlockType.RED_BED, JavaLegacyStateGroups.BED));
        registerDuplicateInput(BlockMapping.group(ImmutableMultimap.<String, ChunkerVanillaBlockType>builder()
                        .put("minecraft:bed", ChunkerVanillaBlockType.BROWN_BED)
                        .put("minecraft:bed", ChunkerVanillaBlockType.BLACK_BED)
                        .put("minecraft:bed", ChunkerVanillaBlockType.CYAN_BED)
                        .put("minecraft:bed", ChunkerVanillaBlockType.LIGHT_BLUE_BED)
                        .put("minecraft:bed", ChunkerVanillaBlockType.WHITE_BED)
                        .put("minecraft:bed", ChunkerVanillaBlockType.ORANGE_BED)
                        .put("minecraft:bed", ChunkerVanillaBlockType.MAGENTA_BED)
                        .put("minecraft:bed", ChunkerVanillaBlockType.BLUE_BED)
                        .put("minecraft:bed", ChunkerVanillaBlockType.GREEN_BED)
                        .put("minecraft:bed", ChunkerVanillaBlockType.GRAY_BED)
                        .put("minecraft:bed", ChunkerVanillaBlockType.PINK_BED)
                        .put("minecraft:bed", ChunkerVanillaBlockType.YELLOW_BED)
                        .put("minecraft:bed", ChunkerVanillaBlockType.LIGHT_GRAY_BED)
                        .put("minecraft:bed", ChunkerVanillaBlockType.PURPLE_BED)
                        .put("minecraft:bed", ChunkerVanillaBlockType.LIME_BED)
                        .build(),
                JavaLegacyStateGroups.BED));

        register(BlockMapping.flatten("minecraft:wool", "data",
                ImmutableMultimap.<Integer, ChunkerVanillaBlockType>builder()
                        .put(0, ChunkerVanillaBlockType.WHITE_WOOL)
                        .put(1, ChunkerVanillaBlockType.ORANGE_WOOL)
                        .put(2, ChunkerVanillaBlockType.MAGENTA_WOOL)
                        .put(3, ChunkerVanillaBlockType.LIGHT_BLUE_WOOL)
                        .put(4, ChunkerVanillaBlockType.YELLOW_WOOL)
                        .put(5, ChunkerVanillaBlockType.LIME_WOOL)
                        .put(6, ChunkerVanillaBlockType.PINK_WOOL)
                        .put(7, ChunkerVanillaBlockType.GRAY_WOOL)
                        .put(8, ChunkerVanillaBlockType.LIGHT_GRAY_WOOL)
                        .put(9, ChunkerVanillaBlockType.CYAN_WOOL)
                        .put(10, ChunkerVanillaBlockType.PURPLE_WOOL)
                        .put(11, ChunkerVanillaBlockType.BLUE_WOOL)
                        .put(12, ChunkerVanillaBlockType.BROWN_WOOL)
                        .put(13, ChunkerVanillaBlockType.GREEN_WOOL)
                        .put(14, ChunkerVanillaBlockType.RED_WOOL)
                        .put(15, ChunkerVanillaBlockType.BLACK_WOOL)
                        .build()
        ));

        // Log
        register(BlockMapping.flatten("minecraft:log", "data", VanillaBlockStates.AXIS,
                ImmutableMultimap.<Integer, Pair<Axis, ? extends ChunkerBlockType>>builder()
                        .put(0, Pair.of(Axis.Y, ChunkerVanillaBlockType.OAK_LOG))
                        .put(1, Pair.of(Axis.Y, ChunkerVanillaBlockType.SPRUCE_LOG))
                        .put(2, Pair.of(Axis.Y, ChunkerVanillaBlockType.BIRCH_LOG))
                        .put(3, Pair.of(Axis.Y, ChunkerVanillaBlockType.JUNGLE_LOG))
                        .put(4, Pair.of(Axis.X, ChunkerVanillaBlockType.OAK_LOG))
                        .put(5, Pair.of(Axis.X, ChunkerVanillaBlockType.SPRUCE_LOG))
                        .put(6, Pair.of(Axis.X, ChunkerVanillaBlockType.BIRCH_LOG))
                        .put(7, Pair.of(Axis.X, ChunkerVanillaBlockType.JUNGLE_LOG))
                        .put(8, Pair.of(Axis.Z, ChunkerVanillaBlockType.OAK_LOG))
                        .put(9, Pair.of(Axis.Z, ChunkerVanillaBlockType.SPRUCE_LOG))
                        .put(10, Pair.of(Axis.Z, ChunkerVanillaBlockType.BIRCH_LOG))
                        .put(11, Pair.of(Axis.Z, ChunkerVanillaBlockType.JUNGLE_LOG))
                        .build()
        ));
        register(BlockMapping.flatten("minecraft:log", "data",
                ImmutableMultimap.<Integer, ChunkerVanillaBlockType>builder()
                        .put(12, ChunkerVanillaBlockType.OAK_WOOD)
                        .put(13, ChunkerVanillaBlockType.SPRUCE_WOOD)
                        .put(14, ChunkerVanillaBlockType.BIRCH_WOOD)
                        .put(15, ChunkerVanillaBlockType.JUNGLE_WOOD)
                        .build(), JavaLegacyStateGroups.WOOD
        ));

        register(BlockMapping.flatten("minecraft:log2", "data", VanillaBlockStates.AXIS,
                ImmutableMultimap.<Integer, Pair<Axis, ? extends ChunkerBlockType>>builder()
                        .put(0, Pair.of(Axis.Y, ChunkerVanillaBlockType.ACACIA_LOG))
                        .put(1, Pair.of(Axis.Y, ChunkerVanillaBlockType.DARK_OAK_LOG))
                        .put(4, Pair.of(Axis.X, ChunkerVanillaBlockType.ACACIA_LOG))
                        .put(5, Pair.of(Axis.X, ChunkerVanillaBlockType.DARK_OAK_LOG))
                        .put(8, Pair.of(Axis.Z, ChunkerVanillaBlockType.ACACIA_LOG))
                        .put(9, Pair.of(Axis.Z, ChunkerVanillaBlockType.DARK_OAK_LOG))
                        .build()
        ));
        register(BlockMapping.flatten("minecraft:log2", "data",
                ImmutableMultimap.<Integer, ChunkerVanillaBlockType>builder()
                        .put(12, ChunkerVanillaBlockType.ACACIA_WOOD)
                        .put(13, ChunkerVanillaBlockType.DARK_OAK_WOOD)
                        .build(), JavaLegacyStateGroups.WOOD
        ));

        // Panes / Fences
        register(BlockMapping.group(ImmutableMultimap.<String, ChunkerVanillaBlockType>builder()
                        .put("minecraft:acacia_fence", ChunkerVanillaBlockType.ACACIA_FENCE)
                        .put("minecraft:birch_fence", ChunkerVanillaBlockType.BIRCH_FENCE)
                        .put("minecraft:dark_oak_fence", ChunkerVanillaBlockType.DARK_OAK_FENCE)
                        .put("minecraft:glass_pane", ChunkerVanillaBlockType.GLASS_PANE)
                        .put("minecraft:iron_bars", ChunkerVanillaBlockType.IRON_BARS)
                        .put("minecraft:jungle_fence", ChunkerVanillaBlockType.JUNGLE_FENCE)
                        .put("minecraft:nether_brick_fence", ChunkerVanillaBlockType.NETHER_BRICK_FENCE)
                        .put("minecraft:fence", ChunkerVanillaBlockType.OAK_FENCE)
                        .put("minecraft:spruce_fence", ChunkerVanillaBlockType.SPRUCE_FENCE)
                        .build(),
                JavaLegacyStateGroups.CONNECTABLE_HORIZONTAL));
        register(BlockMapping.flatten("minecraft:carpet", "data",
                ImmutableMultimap.<Integer, ChunkerVanillaBlockType>builder()
                        .put(0, ChunkerVanillaBlockType.WHITE_CARPET)
                        .put(1, ChunkerVanillaBlockType.ORANGE_CARPET)
                        .put(2, ChunkerVanillaBlockType.MAGENTA_CARPET)
                        .put(3, ChunkerVanillaBlockType.LIGHT_BLUE_CARPET)
                        .put(4, ChunkerVanillaBlockType.YELLOW_CARPET)
                        .put(5, ChunkerVanillaBlockType.LIME_CARPET)
                        .put(6, ChunkerVanillaBlockType.PINK_CARPET)
                        .put(7, ChunkerVanillaBlockType.GRAY_CARPET)
                        .put(8, ChunkerVanillaBlockType.LIGHT_GRAY_CARPET)
                        .put(9, ChunkerVanillaBlockType.CYAN_CARPET)
                        .put(10, ChunkerVanillaBlockType.PURPLE_CARPET)
                        .put(11, ChunkerVanillaBlockType.BLUE_CARPET)
                        .put(12, ChunkerVanillaBlockType.BROWN_CARPET)
                        .put(13, ChunkerVanillaBlockType.GREEN_CARPET)
                        .put(14, ChunkerVanillaBlockType.RED_CARPET)
                        .put(15, ChunkerVanillaBlockType.BLACK_CARPET)
                        .build()
        ));

        register(BlockMapping.flatten("minecraft:stained_glass", "data",
                ImmutableMultimap.<Integer, ChunkerVanillaBlockType>builder()
                        .put(0, ChunkerVanillaBlockType.WHITE_STAINED_GLASS)
                        .put(1, ChunkerVanillaBlockType.ORANGE_STAINED_GLASS)
                        .put(2, ChunkerVanillaBlockType.MAGENTA_STAINED_GLASS)
                        .put(3, ChunkerVanillaBlockType.LIGHT_BLUE_STAINED_GLASS)
                        .put(4, ChunkerVanillaBlockType.YELLOW_STAINED_GLASS)
                        .put(5, ChunkerVanillaBlockType.LIME_STAINED_GLASS)
                        .put(6, ChunkerVanillaBlockType.PINK_STAINED_GLASS)
                        .put(7, ChunkerVanillaBlockType.GRAY_STAINED_GLASS)
                        .put(8, ChunkerVanillaBlockType.LIGHT_GRAY_STAINED_GLASS)
                        .put(9, ChunkerVanillaBlockType.CYAN_STAINED_GLASS)
                        .put(10, ChunkerVanillaBlockType.PURPLE_STAINED_GLASS)
                        .put(11, ChunkerVanillaBlockType.BLUE_STAINED_GLASS)
                        .put(12, ChunkerVanillaBlockType.BROWN_STAINED_GLASS)
                        .put(13, ChunkerVanillaBlockType.GREEN_STAINED_GLASS)
                        .put(14, ChunkerVanillaBlockType.RED_STAINED_GLASS)
                        .put(15, ChunkerVanillaBlockType.BLACK_STAINED_GLASS)
                        .build()
        ));

        register(BlockMapping.flatten("minecraft:stained_glass_pane", "data",
                ImmutableMultimap.<Integer, ChunkerVanillaBlockType>builder()
                        .put(0, ChunkerVanillaBlockType.WHITE_STAINED_GLASS_PANE)
                        .put(1, ChunkerVanillaBlockType.ORANGE_STAINED_GLASS_PANE)
                        .put(2, ChunkerVanillaBlockType.MAGENTA_STAINED_GLASS_PANE)
                        .put(3, ChunkerVanillaBlockType.LIGHT_BLUE_STAINED_GLASS_PANE)
                        .put(4, ChunkerVanillaBlockType.YELLOW_STAINED_GLASS_PANE)
                        .put(5, ChunkerVanillaBlockType.LIME_STAINED_GLASS_PANE)
                        .put(6, ChunkerVanillaBlockType.PINK_STAINED_GLASS_PANE)
                        .put(7, ChunkerVanillaBlockType.GRAY_STAINED_GLASS_PANE)
                        .put(8, ChunkerVanillaBlockType.LIGHT_GRAY_STAINED_GLASS_PANE)
                        .put(9, ChunkerVanillaBlockType.CYAN_STAINED_GLASS_PANE)
                        .put(10, ChunkerVanillaBlockType.PURPLE_STAINED_GLASS_PANE)
                        .put(11, ChunkerVanillaBlockType.BLUE_STAINED_GLASS_PANE)
                        .put(12, ChunkerVanillaBlockType.BROWN_STAINED_GLASS_PANE)
                        .put(13, ChunkerVanillaBlockType.GREEN_STAINED_GLASS_PANE)
                        .put(14, ChunkerVanillaBlockType.RED_STAINED_GLASS_PANE)
                        .put(15, ChunkerVanillaBlockType.BLACK_STAINED_GLASS_PANE)
                        .build(),
                JavaLegacyStateGroups.CONNECTABLE_HORIZONTAL));

        // Standing banners
        register(BlockMapping.of("minecraft:standing_banner", ChunkerVanillaBlockType.WHITE_BANNER, JavaLegacyStateGroups.ROTATION));
        registerDuplicateInput(BlockMapping.group(ImmutableMultimap.<String, ChunkerVanillaBlockType>builder()
                        .put("minecraft:standing_banner", ChunkerVanillaBlockType.ORANGE_BANNER)
                        .put("minecraft:standing_banner", ChunkerVanillaBlockType.BROWN_BANNER)
                        .put("minecraft:standing_banner", ChunkerVanillaBlockType.BLUE_BANNER)
                        .put("minecraft:standing_banner", ChunkerVanillaBlockType.CYAN_BANNER)
                        .put("minecraft:standing_banner", ChunkerVanillaBlockType.GREEN_BANNER)
                        .put("minecraft:standing_banner", ChunkerVanillaBlockType.RED_BANNER)
                        .put("minecraft:standing_banner", ChunkerVanillaBlockType.YELLOW_BANNER)
                        .put("minecraft:standing_banner", ChunkerVanillaBlockType.MAGENTA_BANNER)
                        .put("minecraft:standing_banner", ChunkerVanillaBlockType.GRAY_BANNER)
                        .put("minecraft:standing_banner", ChunkerVanillaBlockType.PINK_BANNER)
                        .put("minecraft:standing_banner", ChunkerVanillaBlockType.BLACK_BANNER)
                        .put("minecraft:standing_banner", ChunkerVanillaBlockType.LIGHT_BLUE_BANNER)
                        .put("minecraft:standing_banner", ChunkerVanillaBlockType.LIGHT_GRAY_BANNER)
                        .put("minecraft:standing_banner", ChunkerVanillaBlockType.LIME_BANNER)
                        .put("minecraft:standing_banner", ChunkerVanillaBlockType.PURPLE_BANNER)
                        .build(),
                JavaLegacyStateGroups.ROTATION));

        // Wall banners
        register(BlockMapping.of("minecraft:wall_banner", ChunkerVanillaBlockType.WHITE_WALL_BANNER, JavaLegacyStateGroups.FACING_HORIZONTAL_UNUSUAL));
        registerDuplicateInput(BlockMapping.group(ImmutableMultimap.<String, ChunkerVanillaBlockType>builder()
                        .put("minecraft:wall_banner", ChunkerVanillaBlockType.MAGENTA_WALL_BANNER)
                        .put("minecraft:wall_banner", ChunkerVanillaBlockType.BROWN_WALL_BANNER)
                        .put("minecraft:wall_banner", ChunkerVanillaBlockType.BLUE_WALL_BANNER)
                        .put("minecraft:wall_banner", ChunkerVanillaBlockType.GRAY_WALL_BANNER)
                        .put("minecraft:wall_banner", ChunkerVanillaBlockType.PINK_WALL_BANNER)
                        .put("minecraft:wall_banner", ChunkerVanillaBlockType.ORANGE_WALL_BANNER)
                        .put("minecraft:wall_banner", ChunkerVanillaBlockType.LIGHT_GRAY_WALL_BANNER)
                        .put("minecraft:wall_banner", ChunkerVanillaBlockType.YELLOW_WALL_BANNER)
                        .put("minecraft:wall_banner", ChunkerVanillaBlockType.CYAN_WALL_BANNER)
                        .put("minecraft:wall_banner", ChunkerVanillaBlockType.LIGHT_BLUE_WALL_BANNER)
                        .put("minecraft:wall_banner", ChunkerVanillaBlockType.RED_WALL_BANNER)
                        .put("minecraft:wall_banner", ChunkerVanillaBlockType.PURPLE_WALL_BANNER)
                        .put("minecraft:wall_banner", ChunkerVanillaBlockType.BLACK_WALL_BANNER)
                        .put("minecraft:wall_banner", ChunkerVanillaBlockType.GREEN_WALL_BANNER)
                        .put("minecraft:wall_banner", ChunkerVanillaBlockType.LIME_WALL_BANNER)
                        .build(),
                JavaLegacyStateGroups.FACING_HORIZONTAL_UNUSUAL));


        // Wall Skulls - Converted to block entity data
        register(BlockMapping.of("minecraft:skull", ChunkerVanillaBlockType.SKELETON_WALL_SKULL, JavaLegacyStateGroups.WALL_SKULL));
        registerDuplicateInput(BlockMapping.group(ImmutableMultimap.<String, ChunkerVanillaBlockType>builder()
                        .put("minecraft:skull", ChunkerVanillaBlockType.ZOMBIE_WALL_HEAD)
                        .put("minecraft:skull", ChunkerVanillaBlockType.PLAYER_WALL_HEAD)
                        .put("minecraft:skull", ChunkerVanillaBlockType.CREEPER_WALL_HEAD)
                        .put("minecraft:skull", ChunkerVanillaBlockType.WITHER_SKELETON_WALL_SKULL)
                        .put("minecraft:skull", ChunkerVanillaBlockType.DRAGON_WALL_HEAD)
                        .put("minecraft:skull", ChunkerVanillaBlockType.PIGLIN_WALL_HEAD)
                        .build(),
                JavaLegacyStateGroups.WALL_SKULL));

        // Skulls - Converted to block entity data
        register(BlockMapping.of("minecraft:skull", "data", 1, ChunkerVanillaBlockType.SKELETON_SKULL, JavaLegacyStateGroups.SKULL, VanillaBlockStates.NO_DROP, Bool.FALSE));
        registerDuplicateOutput(BlockMapping.of("minecraft:skull", "data", 0, ChunkerVanillaBlockType.SKELETON_SKULL, JavaLegacyStateGroups.SKULL, VanillaBlockStates.NO_DROP, Bool.FALSE));
        register(BlockMapping.of("minecraft:skull", "data", 9, ChunkerVanillaBlockType.SKELETON_SKULL, JavaLegacyStateGroups.SKULL, VanillaBlockStates.NO_DROP, Bool.TRUE));
        registerDuplicateOutput(BlockMapping.of("minecraft:skull", "data", 8, ChunkerVanillaBlockType.SKELETON_SKULL, JavaLegacyStateGroups.SKULL, VanillaBlockStates.NO_DROP, Bool.TRUE));
        registerDuplicateInput(BlockMapping.flatten("minecraft:skull", "data",
                ImmutableMultimap.<Integer, ChunkerVanillaBlockType>builder()
                        .put(1, ChunkerVanillaBlockType.PLAYER_HEAD)
                        .put(1, ChunkerVanillaBlockType.DRAGON_HEAD)
                        .put(1, ChunkerVanillaBlockType.PIGLIN_HEAD)
                        .put(1, ChunkerVanillaBlockType.WITHER_SKELETON_SKULL)
                        .put(1, ChunkerVanillaBlockType.ZOMBIE_HEAD)
                        .put(1, ChunkerVanillaBlockType.CREEPER_HEAD)
                        .build(),
                JavaLegacyStateGroups.SKULL, VanillaBlockStates.NO_DROP, Bool.FALSE));
        registerDuplicateInput(BlockMapping.flatten("minecraft:skull", "data",
                ImmutableMultimap.<Integer, ChunkerVanillaBlockType>builder()
                        .put(9, ChunkerVanillaBlockType.PLAYER_HEAD)
                        .put(9, ChunkerVanillaBlockType.DRAGON_HEAD)
                        .put(9, ChunkerVanillaBlockType.PIGLIN_HEAD)
                        .put(9, ChunkerVanillaBlockType.WITHER_SKELETON_SKULL)
                        .put(9, ChunkerVanillaBlockType.ZOMBIE_HEAD)
                        .put(9, ChunkerVanillaBlockType.CREEPER_HEAD)
                        .build(),
                JavaLegacyStateGroups.SKULL, VanillaBlockStates.NO_DROP, Bool.TRUE));

        // Stone brick
        register(BlockMapping.flatten("minecraft:stonebrick", "data",
                ImmutableMultimap.<Integer, ChunkerVanillaBlockType>builder()
                        .put(0, ChunkerVanillaBlockType.STONE_BRICKS)
                        .put(1, ChunkerVanillaBlockType.MOSSY_STONE_BRICKS)
                        .put(2, ChunkerVanillaBlockType.CRACKED_STONE_BRICKS)
                        .put(3, ChunkerVanillaBlockType.CHISELED_STONE_BRICKS)
                        .build()
        ));
        // Anvil
        register(BlockMapping.flatten("minecraft:anvil", "data", VanillaBlockStates.FACING_HORIZONTAL,
                ImmutableMultimap.<Integer, Pair<FacingDirectionHorizontal, ? extends ChunkerBlockType>>builder()
                        .put(0, Pair.of(FacingDirectionHorizontal.SOUTH, ChunkerVanillaBlockType.ANVIL))
                        .put(1, Pair.of(FacingDirectionHorizontal.WEST, ChunkerVanillaBlockType.ANVIL))
                        .put(2, Pair.of(FacingDirectionHorizontal.NORTH, ChunkerVanillaBlockType.ANVIL))
                        .put(3, Pair.of(FacingDirectionHorizontal.EAST, ChunkerVanillaBlockType.ANVIL))
                        .put(4, Pair.of(FacingDirectionHorizontal.SOUTH, ChunkerVanillaBlockType.CHIPPED_ANVIL))
                        .put(5, Pair.of(FacingDirectionHorizontal.WEST, ChunkerVanillaBlockType.CHIPPED_ANVIL))
                        .put(6, Pair.of(FacingDirectionHorizontal.NORTH, ChunkerVanillaBlockType.CHIPPED_ANVIL))
                        .put(7, Pair.of(FacingDirectionHorizontal.EAST, ChunkerVanillaBlockType.CHIPPED_ANVIL))
                        .put(8, Pair.of(FacingDirectionHorizontal.SOUTH, ChunkerVanillaBlockType.DAMAGED_ANVIL))
                        .put(9, Pair.of(FacingDirectionHorizontal.WEST, ChunkerVanillaBlockType.DAMAGED_ANVIL))
                        .put(10, Pair.of(FacingDirectionHorizontal.NORTH, ChunkerVanillaBlockType.DAMAGED_ANVIL))
                        .put(11, Pair.of(FacingDirectionHorizontal.EAST, ChunkerVanillaBlockType.DAMAGED_ANVIL))
                        .build()
        ));

        // Flowers
        register(BlockMapping.flatten("minecraft:red_flower", "data",
                ImmutableMultimap.<Integer, ChunkerVanillaBlockType>builder()
                        .put(0, ChunkerVanillaBlockType.POPPY)
                        .put(1, ChunkerVanillaBlockType.BLUE_ORCHID)
                        .put(2, ChunkerVanillaBlockType.ALLIUM)
                        .put(3, ChunkerVanillaBlockType.AZURE_BLUET)
                        .put(4, ChunkerVanillaBlockType.RED_TULIP)
                        .put(5, ChunkerVanillaBlockType.ORANGE_TULIP)
                        .put(6, ChunkerVanillaBlockType.WHITE_TULIP)
                        .put(7, ChunkerVanillaBlockType.PINK_TULIP)
                        .put(8, ChunkerVanillaBlockType.OXEYE_DAISY)
                        .build()
        ));

        // Flower pots
        register(BlockMapping.of("minecraft:flower_pot", ChunkerVanillaBlockType.FLOWER_POT));
        registerDuplicateInput(BlockMapping.group(ImmutableMultimap.<String, ChunkerVanillaBlockType>builder()
                .put("minecraft:flower_pot", ChunkerVanillaBlockType.POTTED_RED_TULIP)
                .put("minecraft:flower_pot", ChunkerVanillaBlockType.POTTED_AZURE_BLUET)
                .put("minecraft:flower_pot", ChunkerVanillaBlockType.POTTED_WHITE_TULIP)
                .put("minecraft:flower_pot", ChunkerVanillaBlockType.POTTED_BLUE_ORCHID)
                .put("minecraft:flower_pot", ChunkerVanillaBlockType.POTTED_RED_MUSHROOM)
                .put("minecraft:flower_pot", ChunkerVanillaBlockType.POTTED_SPRUCE_SAPLING)
                .put("minecraft:flower_pot", ChunkerVanillaBlockType.POTTED_AZALEA_BUSH)
                .put("minecraft:flower_pot", ChunkerVanillaBlockType.POTTED_WITHER_ROSE)
                .put("minecraft:flower_pot", ChunkerVanillaBlockType.POTTED_TORCHFLOWER)
                .put("minecraft:flower_pot", ChunkerVanillaBlockType.POTTED_ORANGE_TULIP)
                .put("minecraft:flower_pot", ChunkerVanillaBlockType.POTTED_CHERRY_SAPLING)
                .put("minecraft:flower_pot", ChunkerVanillaBlockType.POTTED_JUNGLE_SAPLING)
                .put("minecraft:flower_pot", ChunkerVanillaBlockType.POTTED_BROWN_MUSHROOM)
                .put("minecraft:flower_pot", ChunkerVanillaBlockType.POTTED_CORNFLOWER)
                .put("minecraft:flower_pot", ChunkerVanillaBlockType.POTTED_FLOWERING_AZALEA_BUSH)
                .put("minecraft:flower_pot", ChunkerVanillaBlockType.POTTED_ALLIUM)
                .put("minecraft:flower_pot", ChunkerVanillaBlockType.POTTED_OAK_SAPLING)
                .put("minecraft:flower_pot", ChunkerVanillaBlockType.POTTED_WARPED_FUNGUS)
                .put("minecraft:flower_pot", ChunkerVanillaBlockType.POTTED_DEAD_BUSH)
                .put("minecraft:flower_pot", ChunkerVanillaBlockType.POTTED_CRIMSON_ROOTS)
                .put("minecraft:flower_pot", ChunkerVanillaBlockType.POTTED_CRIMSON_FUNGUS)
                .put("minecraft:flower_pot", ChunkerVanillaBlockType.POTTED_POPPY)
                .put("minecraft:flower_pot", ChunkerVanillaBlockType.POTTED_MANGROVE_PROPAGULE)
                .put("minecraft:flower_pot", ChunkerVanillaBlockType.POTTED_FERN)
                .put("minecraft:flower_pot", ChunkerVanillaBlockType.POTTED_CACTUS)
                .put("minecraft:flower_pot", ChunkerVanillaBlockType.POTTED_BIRCH_SAPLING)
                .put("minecraft:flower_pot", ChunkerVanillaBlockType.POTTED_OXEYE_DAISY)
                .put("minecraft:flower_pot", ChunkerVanillaBlockType.POTTED_ACACIA_SAPLING)
                .put("minecraft:flower_pot", ChunkerVanillaBlockType.POTTED_LILY_OF_THE_VALLEY)
                .put("minecraft:flower_pot", ChunkerVanillaBlockType.POTTED_DARK_OAK_SAPLING)
                .put("minecraft:flower_pot", ChunkerVanillaBlockType.POTTED_DANDELION)
                .put("minecraft:flower_pot", ChunkerVanillaBlockType.POTTED_PINK_TULIP)
                .put("minecraft:flower_pot", ChunkerVanillaBlockType.POTTED_WARPED_ROOTS)
                .put("minecraft:flower_pot", ChunkerVanillaBlockType.POTTED_BAMBOO)
                .build()
        ));

        // Double Plant
        register(BlockMapping.flatten("minecraft:double_plant", "data", VanillaBlockStates.HALF,
                ImmutableMultimap.<Integer, Pair<Half, ? extends ChunkerBlockType>>builder()
                        .put(0, Pair.of(Half.BOTTOM, ChunkerVanillaBlockType.SUNFLOWER))
                        .put(1, Pair.of(Half.BOTTOM, ChunkerVanillaBlockType.LILAC))
                        .put(2, Pair.of(Half.BOTTOM, ChunkerVanillaBlockType.TALL_GRASS))
                        .put(3, Pair.of(Half.BOTTOM, ChunkerVanillaBlockType.LARGE_FERN))
                        .put(4, Pair.of(Half.BOTTOM, ChunkerVanillaBlockType.ROSE_BUSH))
                        .put(5, Pair.of(Half.BOTTOM, ChunkerVanillaBlockType.PEONY))
                        .put(8, Pair.of(Half.TOP, ChunkerVanillaBlockType.PEONY))
                        .build()
        ));
        registerDuplicateOutput(BlockMapping.flatten("minecraft:double_plant", "data", VanillaBlockStates.HALF,
                ImmutableMultimap.<Integer, Pair<Half, ? extends ChunkerBlockType>>builder()
                        .put(9, Pair.of(Half.TOP, ChunkerVanillaBlockType.PEONY))
                        .put(10, Pair.of(Half.TOP, ChunkerVanillaBlockType.PEONY))
                        .put(11, Pair.of(Half.TOP, ChunkerVanillaBlockType.PEONY))
                        .build()
        ));

        // These need to use the same top half
        registerDuplicateInput(BlockMapping.of("minecraft:double_plant", "data", 8, ChunkerVanillaBlockType.SUNFLOWER, VanillaBlockStates.HALF, Half.TOP));
        registerDuplicateInput(BlockMapping.of("minecraft:double_plant", "data", 8, ChunkerVanillaBlockType.TALL_GRASS, VanillaBlockStates.HALF, Half.TOP));
        registerDuplicateInput(BlockMapping.of("minecraft:double_plant", "data", 8, ChunkerVanillaBlockType.LARGE_FERN, VanillaBlockStates.HALF, Half.TOP));
        registerDuplicateInput(BlockMapping.of("minecraft:double_plant", "data", 8, ChunkerVanillaBlockType.ROSE_BUSH, VanillaBlockStates.HALF, Half.TOP));
        registerDuplicateInput(BlockMapping.of("minecraft:double_plant", "data", 8, ChunkerVanillaBlockType.LILAC, VanillaBlockStates.HALF, Half.TOP));

        // Leaves
        register(BlockMapping.flatten("minecraft:leaves", "data", List.of(VanillaBlockStates.UPDATE, VanillaBlockStates.PERSISTENT),
                ImmutableMultimap.<Integer, Pair<List<BlockStateValue>, ? extends ChunkerBlockType>>builder()
                        .put(0, Pair.of(List.of(Bool.FALSE, Bool.FALSE), ChunkerVanillaBlockType.OAK_LEAVES))
                        .put(1, Pair.of(List.of(Bool.FALSE, Bool.FALSE), ChunkerVanillaBlockType.SPRUCE_LEAVES))
                        .put(2, Pair.of(List.of(Bool.FALSE, Bool.FALSE), ChunkerVanillaBlockType.BIRCH_LEAVES))
                        .put(3, Pair.of(List.of(Bool.FALSE, Bool.FALSE), ChunkerVanillaBlockType.JUNGLE_LEAVES))
                        .put(4, Pair.of(List.of(Bool.FALSE, Bool.TRUE), ChunkerVanillaBlockType.OAK_LEAVES))
                        .put(5, Pair.of(List.of(Bool.FALSE, Bool.TRUE), ChunkerVanillaBlockType.SPRUCE_LEAVES))
                        .put(6, Pair.of(List.of(Bool.FALSE, Bool.TRUE), ChunkerVanillaBlockType.BIRCH_LEAVES))
                        .put(7, Pair.of(List.of(Bool.FALSE, Bool.TRUE), ChunkerVanillaBlockType.JUNGLE_LEAVES))
                        .put(8, Pair.of(List.of(Bool.TRUE, Bool.FALSE), ChunkerVanillaBlockType.OAK_LEAVES))
                        .put(9, Pair.of(List.of(Bool.TRUE, Bool.FALSE), ChunkerVanillaBlockType.SPRUCE_LEAVES))
                        .put(10, Pair.of(List.of(Bool.TRUE, Bool.FALSE), ChunkerVanillaBlockType.BIRCH_LEAVES))
                        .put(11, Pair.of(List.of(Bool.TRUE, Bool.FALSE), ChunkerVanillaBlockType.JUNGLE_LEAVES))
                        .put(12, Pair.of(List.of(Bool.TRUE, Bool.TRUE), ChunkerVanillaBlockType.OAK_LEAVES))
                        .put(13, Pair.of(List.of(Bool.TRUE, Bool.TRUE), ChunkerVanillaBlockType.SPRUCE_LEAVES))
                        .put(14, Pair.of(List.of(Bool.TRUE, Bool.TRUE), ChunkerVanillaBlockType.BIRCH_LEAVES))
                        .put(15, Pair.of(List.of(Bool.TRUE, Bool.TRUE), ChunkerVanillaBlockType.JUNGLE_LEAVES))
                        .build(),
                JavaLegacyStateGroups.LEAVES
        ));
        register(BlockMapping.flatten("minecraft:leaves2", "data", List.of(VanillaBlockStates.UPDATE, VanillaBlockStates.PERSISTENT),
                ImmutableMultimap.<Integer, Pair<List<BlockStateValue>, ? extends ChunkerBlockType>>builder()
                        .put(0, Pair.of(List.of(Bool.FALSE, Bool.FALSE), ChunkerVanillaBlockType.ACACIA_LEAVES))
                        .put(1, Pair.of(List.of(Bool.FALSE, Bool.FALSE), ChunkerVanillaBlockType.DARK_OAK_LEAVES))
                        .put(4, Pair.of(List.of(Bool.FALSE, Bool.TRUE), ChunkerVanillaBlockType.ACACIA_LEAVES))
                        .put(5, Pair.of(List.of(Bool.FALSE, Bool.TRUE), ChunkerVanillaBlockType.DARK_OAK_LEAVES))
                        .put(8, Pair.of(List.of(Bool.TRUE, Bool.FALSE), ChunkerVanillaBlockType.ACACIA_LEAVES))
                        .put(9, Pair.of(List.of(Bool.TRUE, Bool.FALSE), ChunkerVanillaBlockType.DARK_OAK_LEAVES))
                        .put(12, Pair.of(List.of(Bool.TRUE, Bool.TRUE), ChunkerVanillaBlockType.ACACIA_LEAVES))
                        .put(13, Pair.of(List.of(Bool.TRUE, Bool.TRUE), ChunkerVanillaBlockType.DARK_OAK_LEAVES))
                        .build(),
                JavaLegacyStateGroups.LEAVES
        ));

        register(BlockMapping.flatten("minecraft:stained_hardened_clay", "data",
                ImmutableMultimap.<Integer, ChunkerVanillaBlockType>builder()
                        .put(0, ChunkerVanillaBlockType.WHITE_TERRACOTTA)
                        .put(1, ChunkerVanillaBlockType.ORANGE_TERRACOTTA)
                        .put(2, ChunkerVanillaBlockType.MAGENTA_TERRACOTTA)
                        .put(3, ChunkerVanillaBlockType.LIGHT_BLUE_TERRACOTTA)
                        .put(4, ChunkerVanillaBlockType.YELLOW_TERRACOTTA)
                        .put(5, ChunkerVanillaBlockType.LIME_TERRACOTTA)
                        .put(6, ChunkerVanillaBlockType.PINK_TERRACOTTA)
                        .put(7, ChunkerVanillaBlockType.GRAY_TERRACOTTA)
                        .put(8, ChunkerVanillaBlockType.LIGHT_GRAY_TERRACOTTA)
                        .put(9, ChunkerVanillaBlockType.CYAN_TERRACOTTA)
                        .put(10, ChunkerVanillaBlockType.PURPLE_TERRACOTTA)
                        .put(11, ChunkerVanillaBlockType.BLUE_TERRACOTTA)
                        .put(12, ChunkerVanillaBlockType.BROWN_TERRACOTTA)
                        .put(13, ChunkerVanillaBlockType.GREEN_TERRACOTTA)
                        .put(14, ChunkerVanillaBlockType.RED_TERRACOTTA)
                        .put(15, ChunkerVanillaBlockType.BLACK_TERRACOTTA)
                        .build()
        ));
        // Wood slabs
        register(BlockMapping.flatten("minecraft:wooden_slab", "data", VanillaBlockStates.SLAB_TYPE,
                ImmutableMultimap.<Integer, Pair<SlabType, ? extends ChunkerBlockType>>builder()
                        .put(0, Pair.of(SlabType.BOTTOM, ChunkerVanillaBlockType.OAK_SLAB))
                        .put(1, Pair.of(SlabType.BOTTOM, ChunkerVanillaBlockType.SPRUCE_SLAB))
                        .put(2, Pair.of(SlabType.BOTTOM, ChunkerVanillaBlockType.BIRCH_SLAB))
                        .put(3, Pair.of(SlabType.BOTTOM, ChunkerVanillaBlockType.JUNGLE_SLAB))
                        .put(4, Pair.of(SlabType.BOTTOM, ChunkerVanillaBlockType.ACACIA_SLAB))
                        .put(5, Pair.of(SlabType.BOTTOM, ChunkerVanillaBlockType.DARK_OAK_SLAB))
                        .put(8, Pair.of(SlabType.TOP, ChunkerVanillaBlockType.OAK_SLAB))
                        .put(9, Pair.of(SlabType.TOP, ChunkerVanillaBlockType.SPRUCE_SLAB))
                        .put(10, Pair.of(SlabType.TOP, ChunkerVanillaBlockType.BIRCH_SLAB))
                        .put(11, Pair.of(SlabType.TOP, ChunkerVanillaBlockType.JUNGLE_SLAB))
                        .put(12, Pair.of(SlabType.TOP, ChunkerVanillaBlockType.ACACIA_SLAB))
                        .put(13, Pair.of(SlabType.TOP, ChunkerVanillaBlockType.DARK_OAK_SLAB))
                        .build()
        ));
        registerDuplicateOutput(BlockMapping.of("minecraft:wooden_slab", ChunkerVanillaBlockType.OAK_SLAB, VanillaBlockStates.HALF, Half.BOTTOM));

        register(BlockMapping.flatten("minecraft:double_wooden_slab", "data",
                ImmutableMultimap.<Integer, ChunkerVanillaBlockType>builder()
                        .put(0, ChunkerVanillaBlockType.OAK_SLAB)
                        .put(1, ChunkerVanillaBlockType.SPRUCE_SLAB)
                        .put(2, ChunkerVanillaBlockType.BIRCH_SLAB)
                        .put(3, ChunkerVanillaBlockType.JUNGLE_SLAB)
                        .put(4, ChunkerVanillaBlockType.ACACIA_SLAB)
                        .put(5, ChunkerVanillaBlockType.DARK_OAK_SLAB)
                        .build(),
                VanillaBlockStates.SLAB_TYPE, SlabType.DOUBLE));
        registerDuplicateOutput(BlockMapping.of("minecraft:double_wooden_slab", ChunkerVanillaBlockType.OAK_SLAB, VanillaBlockStates.SLAB_TYPE, SlabType.DOUBLE));

        // Stone slabs
        register(BlockMapping.flatten("minecraft:stone_slab", "data", VanillaBlockStates.SLAB_TYPE,
                ImmutableMultimap.<Integer, Pair<SlabType, ? extends ChunkerBlockType>>builder()
                        .put(0, Pair.of(SlabType.BOTTOM, ChunkerVanillaBlockType.SMOOTH_STONE_SLAB))
                        .put(1, Pair.of(SlabType.BOTTOM, ChunkerVanillaBlockType.SANDSTONE_SLAB))
                        .put(3, Pair.of(SlabType.BOTTOM, ChunkerVanillaBlockType.COBBLESTONE_SLAB))
                        .put(4, Pair.of(SlabType.BOTTOM, ChunkerVanillaBlockType.BRICK_SLAB))
                        .put(5, Pair.of(SlabType.BOTTOM, ChunkerVanillaBlockType.STONE_BRICK_SLAB))
                        .put(6, Pair.of(SlabType.BOTTOM, ChunkerVanillaBlockType.NETHER_BRICK_SLAB))
                        .put(7, Pair.of(SlabType.BOTTOM, ChunkerVanillaBlockType.QUARTZ_SLAB))
                        .put(8, Pair.of(SlabType.TOP, ChunkerVanillaBlockType.SMOOTH_STONE_SLAB))
                        .put(9, Pair.of(SlabType.TOP, ChunkerVanillaBlockType.SANDSTONE_SLAB))
                        .put(11, Pair.of(SlabType.TOP, ChunkerVanillaBlockType.COBBLESTONE_SLAB))
                        .put(12, Pair.of(SlabType.TOP, ChunkerVanillaBlockType.BRICK_SLAB))
                        .put(13, Pair.of(SlabType.TOP, ChunkerVanillaBlockType.STONE_BRICK_SLAB))
                        .put(14, Pair.of(SlabType.TOP, ChunkerVanillaBlockType.NETHER_BRICK_SLAB))
                        .put(15, Pair.of(SlabType.TOP, ChunkerVanillaBlockType.QUARTZ_SLAB))
                        .build()
        ));
        registerDuplicateOutput(BlockMapping.of("minecraft:stone_slab", ChunkerVanillaBlockType.SMOOTH_STONE_SLAB, VanillaBlockStates.HALF, Half.BOTTOM));

        register(BlockMapping.flatten("minecraft:double_stone_slab", "data",
                ImmutableMultimap.<Integer, ChunkerVanillaBlockType>builder()
                        .put(0, ChunkerVanillaBlockType.SMOOTH_STONE_SLAB)
                        .put(1, ChunkerVanillaBlockType.SANDSTONE_SLAB)
                        .put(3, ChunkerVanillaBlockType.COBBLESTONE_SLAB)
                        .put(4, ChunkerVanillaBlockType.BRICK_SLAB)
                        .put(5, ChunkerVanillaBlockType.STONE_BRICK_SLAB)
                        .put(6, ChunkerVanillaBlockType.NETHER_BRICK_SLAB)
                        .put(7, ChunkerVanillaBlockType.QUARTZ_SLAB)
                        .build(),
                VanillaBlockStates.SLAB_TYPE, SlabType.DOUBLE));
        registerDuplicateOutput(BlockMapping.of("minecraft:double_stone_slab", ChunkerVanillaBlockType.SMOOTH_STONE_SLAB, VanillaBlockStates.SLAB_TYPE, SlabType.DOUBLE));

        // Mapping for petrified_oak_slab
        register(BlockMapping.of("minecraft:stone_slab", "data", 2, ChunkerVanillaBlockType.PETRIFIED_OAK_SLAB, VanillaBlockStates.SLAB_TYPE, SlabType.BOTTOM));
        register(BlockMapping.of("minecraft:stone_slab", "data", 10, ChunkerVanillaBlockType.PETRIFIED_OAK_SLAB, VanillaBlockStates.SLAB_TYPE, SlabType.TOP));
        register(BlockMapping.of("minecraft:double_stone_slab", "data", 2, ChunkerVanillaBlockType.PETRIFIED_OAK_SLAB, VanillaBlockStates.SLAB_TYPE, SlabType.DOUBLE));

        // Duplicate double slabs
        registerDuplicateOutput(BlockMapping.of("minecraft:double_stone_slab", "data", 10, ChunkerVanillaBlockType.PETRIFIED_OAK_SLAB, VanillaBlockStates.SLAB_TYPE, SlabType.DOUBLE));
        registerDuplicateOutput(BlockMapping.of("minecraft:double_stone_slab", "data", 11, ChunkerVanillaBlockType.COBBLESTONE_SLAB, VanillaBlockStates.SLAB_TYPE, SlabType.DOUBLE));
        registerDuplicateOutput(BlockMapping.of("minecraft:double_stone_slab", "data", 12, ChunkerVanillaBlockType.BRICK_SLAB, VanillaBlockStates.SLAB_TYPE, SlabType.DOUBLE));
        registerDuplicateOutput(BlockMapping.of("minecraft:double_stone_slab", "data", 13, ChunkerVanillaBlockType.STONE_BRICK_SLAB, VanillaBlockStates.SLAB_TYPE, SlabType.DOUBLE));
        registerDuplicateOutput(BlockMapping.of("minecraft:double_stone_slab", "data", 14, ChunkerVanillaBlockType.NETHER_BRICK_SLAB, VanillaBlockStates.SLAB_TYPE, SlabType.DOUBLE));

        // Extra double slab types
        register(BlockMapping.of("minecraft:double_stone_slab", "data", 9, ChunkerVanillaBlockType.SMOOTH_SANDSTONE));
        register(BlockMapping.flatten("minecraft:double_stone_slab", "data",
                ImmutableMultimap.<Integer, ChunkerVanillaBlockType>builder()
                        .put(8, ChunkerVanillaBlockType.SMOOTH_STONE)
                        .put(15, ChunkerVanillaBlockType.SMOOTH_QUARTZ)
                        .build()
        ));

        register(BlockMapping.of("minecraft:stone_slab2", ChunkerVanillaBlockType.RED_SANDSTONE_SLAB, JavaLegacyStateGroups.SLAB_HALF));
        register(BlockMapping.of("minecraft:double_stone_slab2", "data", 0, ChunkerVanillaBlockType.RED_SANDSTONE_SLAB, VanillaBlockStates.SLAB_TYPE, SlabType.DOUBLE));
        register(BlockMapping.of("minecraft:double_stone_slab2", "data", 8, ChunkerVanillaBlockType.SMOOTH_RED_SANDSTONE));
        registerDuplicateInput(BlockMapping.of("minecraft:double_stone_slab2", "data", 8, ChunkerVanillaBlockType.SMOOTH_RED_SANDSTONE_SLAB, VanillaBlockStates.SLAB_TYPE, SlabType.DOUBLE));

        // Stone
        register(BlockMapping.flatten("minecraft:stone", "data",
                ImmutableMultimap.<Integer, ChunkerVanillaBlockType>builder()
                        .put(0, ChunkerVanillaBlockType.STONE)
                        .put(1, ChunkerVanillaBlockType.GRANITE)
                        .put(2, ChunkerVanillaBlockType.POLISHED_GRANITE)
                        .put(3, ChunkerVanillaBlockType.DIORITE)
                        .put(4, ChunkerVanillaBlockType.POLISHED_DIORITE)
                        .put(5, ChunkerVanillaBlockType.ANDESITE)
                        .put(6, ChunkerVanillaBlockType.POLISHED_ANDESITE)
                        .build()
        ));
        registerDuplicateOutput(BlockMapping.of("minecraft:stone", ChunkerVanillaBlockType.STONE));

        // Monster egg
        register(BlockMapping.flatten("minecraft:monster_egg", "data",
                ImmutableMultimap.<Integer, ChunkerVanillaBlockType>builder()
                        .put(0, ChunkerVanillaBlockType.INFESTED_STONE)
                        .put(2, ChunkerVanillaBlockType.INFESTED_STONE_BRICKS)
                        .put(1, ChunkerVanillaBlockType.INFESTED_COBBLESTONE)
                        .put(3, ChunkerVanillaBlockType.INFESTED_MOSSY_STONE_BRICKS)
                        .put(4, ChunkerVanillaBlockType.INFESTED_CRACKED_STONE_BRICKS)
                        .put(5, ChunkerVanillaBlockType.INFESTED_CHISELED_STONE_BRICKS)
                        .build()
        ));

        // Redstone lamp
        register(BlockMapping.of("minecraft:lit_redstone_lamp", ChunkerVanillaBlockType.REDSTONE_LAMP, VanillaBlockStates.LIT, Bool.TRUE));
        register(BlockMapping.of("minecraft:redstone_lamp", ChunkerVanillaBlockType.REDSTONE_LAMP, VanillaBlockStates.LIT, Bool.FALSE));

        // Redstone ore
        register(BlockMapping.of("minecraft:lit_redstone_ore", ChunkerVanillaBlockType.REDSTONE_ORE, VanillaBlockStates.LIT, Bool.TRUE));
        register(BlockMapping.of("minecraft:redstone_ore", ChunkerVanillaBlockType.REDSTONE_ORE, VanillaBlockStates.LIT, Bool.FALSE));

        // Redstone torch (normal/wall)
        register(BlockMapping.of("minecraft:unlit_redstone_torch", "data", 5, ChunkerVanillaBlockType.REDSTONE_TORCH, VanillaBlockStates.LIT, Bool.FALSE));
        register(BlockMapping.of("minecraft:unlit_redstone_torch", ChunkerVanillaBlockType.REDSTONE_WALL_TORCH, JavaLegacyStateGroups.TORCH_DIRECTION, VanillaBlockStates.LIT, Bool.FALSE));

        // Lit redstone torch
        register(BlockMapping.of("minecraft:redstone_torch", "data", 5, ChunkerVanillaBlockType.REDSTONE_TORCH, VanillaBlockStates.LIT, Bool.TRUE));
        register(BlockMapping.of("minecraft:redstone_torch", ChunkerVanillaBlockType.REDSTONE_WALL_TORCH, JavaLegacyStateGroups.TORCH_DIRECTION, VanillaBlockStates.LIT, Bool.TRUE));

        register(BlockMapping.of("minecraft:lit_furnace", ChunkerVanillaBlockType.FURNACE, JavaLegacyStateGroups.FACING_HORIZONTAL_UNUSUAL, VanillaBlockStates.LIT, Bool.TRUE));
        register(BlockMapping.of("minecraft:furnace", ChunkerVanillaBlockType.FURNACE, JavaLegacyStateGroups.FACING_HORIZONTAL_UNUSUAL, VanillaBlockStates.LIT, Bool.FALSE));

        // Comparators
        register(BlockMapping.of("minecraft:unpowered_comparator", ChunkerVanillaBlockType.COMPARATOR, JavaLegacyStateGroups.UNPOWERED_COMPARATOR));
        registerDuplicateOutput(BlockMapping.of("minecraft:powered_comparator", ChunkerVanillaBlockType.COMPARATOR, JavaLegacyStateGroups.POWERED_COMPARATOR));

        // Repeater
        register(BlockMapping.of("minecraft:powered_repeater", ChunkerVanillaBlockType.REPEATER, JavaLegacyStateGroups.REPEATER, VanillaBlockStates.POWERED, Bool.TRUE));
        register(BlockMapping.of("minecraft:unpowered_repeater", ChunkerVanillaBlockType.REPEATER, JavaLegacyStateGroups.REPEATER, VanillaBlockStates.POWERED, Bool.FALSE));

        // Daylight detector
        register(BlockMapping.of("minecraft:daylight_detector_inverted", ChunkerVanillaBlockType.DAYLIGHT_DETECTOR, JavaLegacyStateGroups.POWER, VanillaBlockStates.INVERTED, Bool.TRUE));
        register(BlockMapping.of("minecraft:daylight_detector", ChunkerVanillaBlockType.DAYLIGHT_DETECTOR, JavaLegacyStateGroups.POWER, VanillaBlockStates.INVERTED, Bool.FALSE));

        // Liquids
        register(BlockMapping.group(ImmutableMultimap.<String, ChunkerVanillaBlockType>builder()
                        .put("minecraft:water", ChunkerVanillaBlockType.WATER)
                        .put("minecraft:lava", ChunkerVanillaBlockType.LAVA)
                        .build(),
                JavaLegacyStateGroups.LIQUID, VanillaBlockStates.FLOWING, Bool.FALSE));

        // Flowing liquids should use the flowing state
        register(BlockMapping.group(ImmutableMultimap.<String, ChunkerVanillaBlockType>builder()
                        .put("minecraft:flowing_water", ChunkerVanillaBlockType.WATER)
                        .put("minecraft:flowing_lava", ChunkerVanillaBlockType.LAVA)
                        .build(),
                JavaLegacyStateGroups.LIQUID, VanillaBlockStates.FLOWING, Bool.TRUE));

        register(BlockMapping.flatten("minecraft:planks", "data",
                ImmutableMultimap.<Integer, ChunkerVanillaBlockType>builder()
                        .put(0, ChunkerVanillaBlockType.OAK_PLANKS)
                        .put(1, ChunkerVanillaBlockType.SPRUCE_PLANKS)
                        .put(2, ChunkerVanillaBlockType.BIRCH_PLANKS)
                        .put(3, ChunkerVanillaBlockType.JUNGLE_PLANKS)
                        .put(4, ChunkerVanillaBlockType.ACACIA_PLANKS)
                        .put(5, ChunkerVanillaBlockType.DARK_OAK_PLANKS)
                        .build()
        ));
        // Sandstone
        register(BlockMapping.flatten("minecraft:sandstone", "data",
                ImmutableMultimap.<Integer, ChunkerVanillaBlockType>builder()
                        .put(0, ChunkerVanillaBlockType.SANDSTONE)
                        .put(1, ChunkerVanillaBlockType.CHISELED_SANDSTONE)
                        .put(2, ChunkerVanillaBlockType.CUT_SANDSTONE)
                        .build()
        ));
        registerDuplicateOutput(BlockMapping.of("minecraft:sandstone", ChunkerVanillaBlockType.SANDSTONE));

        // Red Sandstone
        register(BlockMapping.flatten("minecraft:red_sandstone", "data",
                ImmutableMultimap.<Integer, ChunkerVanillaBlockType>builder()
                        .put(0, ChunkerVanillaBlockType.RED_SANDSTONE)
                        .put(1, ChunkerVanillaBlockType.CHISELED_RED_SANDSTONE)
                        .put(2, ChunkerVanillaBlockType.CUT_RED_SANDSTONE)
                        .build()
        ));
        registerDuplicateOutput(BlockMapping.of("minecraft:red_sandstone", ChunkerVanillaBlockType.RED_SANDSTONE));

        // Sand
        register(BlockMapping.flatten("minecraft:sand", "data",
                ImmutableMultimap.<Integer, ChunkerVanillaBlockType>builder()
                        .put(0, ChunkerVanillaBlockType.SAND)
                        .put(1, ChunkerVanillaBlockType.RED_SAND)
                        .build()
        ));
        registerDuplicateOutput(BlockMapping.of("minecraft:sand", ChunkerVanillaBlockType.SAND));

        // Quartz Blocks
        register(BlockMapping.of("minecraft:quartz_block", "data", 1, ChunkerVanillaBlockType.CHISELED_QUARTZ_BLOCK));
        register(BlockMapping.of("minecraft:quartz_block", "data", 2, ChunkerVanillaBlockType.QUARTZ_PILLAR, VanillaBlockStates.AXIS, Axis.Y));
        register(BlockMapping.of("minecraft:quartz_block", "data", 3, ChunkerVanillaBlockType.QUARTZ_PILLAR, VanillaBlockStates.AXIS, Axis.X));
        register(BlockMapping.of("minecraft:quartz_block", "data", 4, ChunkerVanillaBlockType.QUARTZ_PILLAR, VanillaBlockStates.AXIS, Axis.Z));
        register(BlockMapping.of("minecraft:quartz_block", ChunkerVanillaBlockType.QUARTZ_BLOCK));

        // Tall grass (two types which look identical to other blocks)
        registerDuplicateOutput(BlockMapping.of("minecraft:tallgrass", "data", 0, ChunkerVanillaBlockType.DEAD_BUSH));
        register(BlockMapping.of("minecraft:tallgrass", "data", 1, ChunkerVanillaBlockType.SHORT_GRASS));
        register(BlockMapping.of("minecraft:tallgrass", "data", 2, ChunkerVanillaBlockType.FERN));

        // Dirt
        register(BlockMapping.flatten("minecraft:dirt", "data",
                ImmutableMultimap.<Integer, ChunkerVanillaBlockType>builder()
                        .put(0, ChunkerVanillaBlockType.DIRT)
                        .put(1, ChunkerVanillaBlockType.COARSE_DIRT)
                        .build()
        ));
        register(BlockMapping.of("minecraft:dirt", "data", 2, ChunkerVanillaBlockType.PODZOL, JavaLegacyStateGroups.SNOWY));

        // Torch (normal/wall)
        register(BlockMapping.of("minecraft:torch", "data", 5, ChunkerVanillaBlockType.TORCH));
        register(BlockMapping.of("minecraft:torch", ChunkerVanillaBlockType.WALL_TORCH, JavaLegacyStateGroups.TORCH_DIRECTION));

        // Walls
        register(BlockMapping.flatten("minecraft:cobblestone_wall", "data",
                ImmutableMultimap.<Integer, ChunkerVanillaBlockType>builder()
                        .put(0, ChunkerVanillaBlockType.COBBLESTONE_WALL)
                        .put(1, ChunkerVanillaBlockType.MOSSY_COBBLESTONE_WALL)
                        .build(),
                JavaLegacyStateGroups.WALL));

        // Mushroom / Mushroom Stem
        register(BlockMapping.group(ImmutableMultimap.<String, ChunkerVanillaBlockType>builder()
                        .put("minecraft:brown_mushroom_block", ChunkerVanillaBlockType.BROWN_MUSHROOM_BLOCK)
                        .put("minecraft:red_mushroom_block", ChunkerVanillaBlockType.RED_MUSHROOM_BLOCK)
                        .build(),
                JavaLegacyStateGroups.MUSHROOM_BLOCK));
        register(BlockMapping.of("minecraft:brown_mushroom_block", "data", 15, ChunkerVanillaBlockType.MUSHROOM_STEM, Map.of(
                VanillaBlockStates.NORTH, Bool.TRUE,
                VanillaBlockStates.EAST, Bool.TRUE,
                VanillaBlockStates.SOUTH, Bool.TRUE,
                VanillaBlockStates.WEST, Bool.TRUE,
                VanillaBlockStates.UP, Bool.TRUE,
                VanillaBlockStates.DOWN, Bool.TRUE
        )));
        register(BlockMapping.of("minecraft:brown_mushroom_block", "data", 10, ChunkerVanillaBlockType.MUSHROOM_STEM, Map.of(
                VanillaBlockStates.NORTH, Bool.TRUE,
                VanillaBlockStates.EAST, Bool.TRUE,
                VanillaBlockStates.SOUTH, Bool.TRUE,
                VanillaBlockStates.WEST, Bool.TRUE,
                VanillaBlockStates.UP, Bool.FALSE,
                VanillaBlockStates.DOWN, Bool.FALSE
        )));
        registerDuplicateOutput(BlockMapping.of("minecraft:red_mushroom_block", "data", 15, ChunkerVanillaBlockType.MUSHROOM_STEM, Map.of(
                VanillaBlockStates.NORTH, Bool.TRUE,
                VanillaBlockStates.EAST, Bool.TRUE,
                VanillaBlockStates.SOUTH, Bool.TRUE,
                VanillaBlockStates.WEST, Bool.TRUE,
                VanillaBlockStates.UP, Bool.TRUE,
                VanillaBlockStates.DOWN, Bool.TRUE
        )));
        registerDuplicateOutput(BlockMapping.of("minecraft:red_mushroom_block", "data", 10, ChunkerVanillaBlockType.MUSHROOM_STEM, Map.of(
                VanillaBlockStates.NORTH, Bool.TRUE,
                VanillaBlockStates.EAST, Bool.TRUE,
                VanillaBlockStates.SOUTH, Bool.TRUE,
                VanillaBlockStates.WEST, Bool.TRUE,
                VanillaBlockStates.UP, Bool.FALSE,
                VanillaBlockStates.DOWN, Bool.FALSE
        )));

        // Fallback to a normal mushroom block since stem doesn't exist in this version
        registerDuplicateInput(BlockMapping.of("minecraft:brown_mushroom_block", "data", 15, ChunkerVanillaBlockType.MUSHROOM_STEM));

        // 1.9
        if (version.isGreaterThanOrEqual(1, 9, 0)) {
            register(BlockMapping.of("minecraft:chorus_flower", ChunkerVanillaBlockType.CHORUS_FLOWER, JavaLegacyStateGroups.AGE_5));
            register(BlockMapping.of("minecraft:chorus_plant", ChunkerVanillaBlockType.CHORUS_PLANT, JavaLegacyStateGroups.CONNECTABLE));
            register(BlockMapping.of("minecraft:beetroots", ChunkerVanillaBlockType.BEETROOTS, JavaLegacyStateGroups.AGE_3_TO_7));
            register(BlockMapping.of("minecraft:end_gateway", ChunkerVanillaBlockType.END_GATEWAY));
            register(BlockMapping.of("minecraft:end_bricks", ChunkerVanillaBlockType.END_STONE_BRICKS));
            register(BlockMapping.of("minecraft:end_rod", ChunkerVanillaBlockType.END_ROD, JavaLegacyStateGroups.FACING_ALL));
            register(BlockMapping.of("minecraft:frosted_ice", ChunkerVanillaBlockType.FROSTED_ICE, JavaLegacyStateGroups.AGE_3));
            register(BlockMapping.of("minecraft:grass_path", ChunkerVanillaBlockType.DIRT_PATH));
            register(BlockMapping.of("minecraft:structure_block", ChunkerVanillaBlockType.STRUCTURE_BLOCK, JavaLegacyStateGroups.STRUCTURE_BLOCK));

            register(BlockMapping.of("minecraft:purpur_pillar", ChunkerVanillaBlockType.PURPUR_PILLAR, JavaLegacyStateGroups.AXIS));
            register(BlockMapping.of("minecraft:purpur_stairs", ChunkerVanillaBlockType.PURPUR_STAIRS, JavaLegacyStateGroups.STAIRS));
            register(BlockMapping.of("minecraft:purpur_block", ChunkerVanillaBlockType.PURPUR_BLOCK));
            register(BlockMapping.of("minecraft:purpur_slab", ChunkerVanillaBlockType.PURPUR_SLAB, JavaLegacyStateGroups.SLAB_HALF));
            register(BlockMapping.of("minecraft:purpur_double_slab", ChunkerVanillaBlockType.PURPUR_SLAB, VanillaBlockStates.SLAB_TYPE, SlabType.DOUBLE));
            register(BlockMapping.of("minecraft:chain_command_block", ChunkerVanillaBlockType.CHAIN_COMMAND_BLOCK, JavaLegacyStateGroups.COMMAND_BLOCK));
            register(BlockMapping.of("minecraft:repeating_command_block", ChunkerVanillaBlockType.REPEATING_COMMAND_BLOCK, JavaLegacyStateGroups.COMMAND_BLOCK));
        }

        // 1.10
        if (version.isGreaterThanOrEqual(1, 10, 0)) {
            register(BlockMapping.of("minecraft:structure_void", ChunkerVanillaBlockType.STRUCTURE_VOID, JavaLegacyStateGroups.STRUCTURE_VOID));
            register(BlockMapping.of("minecraft:magma", ChunkerVanillaBlockType.MAGMA_BLOCK));
            register(BlockMapping.of("minecraft:nether_wart_block", ChunkerVanillaBlockType.NETHER_WART_BLOCK));
            register(BlockMapping.of("minecraft:red_nether_brick", ChunkerVanillaBlockType.RED_NETHER_BRICKS));
            register(BlockMapping.of("minecraft:bone_block", ChunkerVanillaBlockType.BONE_BLOCK, JavaLegacyStateGroups.AXIS));
        }

        // 1.11
        if (version.isGreaterThanOrEqual(1, 11, 0)) {
            register(BlockMapping.of("minecraft:observer", ChunkerVanillaBlockType.OBSERVER, JavaLegacyStateGroups.FACING_POWERED));
            register(BlockMapping.group(ImmutableMultimap.<String, ChunkerVanillaBlockType>builder()
                            .put("minecraft:black_shulker_box", ChunkerVanillaBlockType.BLACK_SHULKER_BOX)
                            .put("minecraft:blue_shulker_box", ChunkerVanillaBlockType.BLUE_SHULKER_BOX)
                            .put("minecraft:brown_shulker_box", ChunkerVanillaBlockType.BROWN_SHULKER_BOX)
                            .put("minecraft:cyan_shulker_box", ChunkerVanillaBlockType.CYAN_SHULKER_BOX)
                            .put("minecraft:gray_shulker_box", ChunkerVanillaBlockType.GRAY_SHULKER_BOX)
                            .put("minecraft:green_shulker_box", ChunkerVanillaBlockType.GREEN_SHULKER_BOX)
                            .put("minecraft:light_blue_shulker_box", ChunkerVanillaBlockType.LIGHT_BLUE_SHULKER_BOX)
                            .put("minecraft:silver_shulker_box", ChunkerVanillaBlockType.LIGHT_GRAY_SHULKER_BOX)
                            .put("minecraft:lime_shulker_box", ChunkerVanillaBlockType.LIME_SHULKER_BOX)
                            .put("minecraft:magenta_shulker_box", ChunkerVanillaBlockType.MAGENTA_SHULKER_BOX)
                            .put("minecraft:orange_shulker_box", ChunkerVanillaBlockType.ORANGE_SHULKER_BOX)
                            .put("minecraft:pink_shulker_box", ChunkerVanillaBlockType.PINK_SHULKER_BOX)
                            .put("minecraft:purple_shulker_box", ChunkerVanillaBlockType.PURPLE_SHULKER_BOX)
                            .put("minecraft:red_shulker_box", ChunkerVanillaBlockType.RED_SHULKER_BOX)
                            .put("minecraft:white_shulker_box", ChunkerVanillaBlockType.WHITE_SHULKER_BOX)
                            .put("minecraft:yellow_shulker_box", ChunkerVanillaBlockType.YELLOW_SHULKER_BOX)
                            .build(),
                    JavaLegacyStateGroups.FACING_ALL));
        }

        // 1.12
        if (version.isGreaterThanOrEqual(1, 12, 0)) {
            // Concrete Powder
            register(BlockMapping.flatten("minecraft:concrete_powder", "data",
                    ImmutableMultimap.<Integer, ChunkerVanillaBlockType>builder()
                            .put(0, ChunkerVanillaBlockType.WHITE_CONCRETE_POWDER)
                            .put(1, ChunkerVanillaBlockType.ORANGE_CONCRETE_POWDER)
                            .put(2, ChunkerVanillaBlockType.MAGENTA_CONCRETE_POWDER)
                            .put(3, ChunkerVanillaBlockType.LIGHT_BLUE_CONCRETE_POWDER)
                            .put(4, ChunkerVanillaBlockType.YELLOW_CONCRETE_POWDER)
                            .put(5, ChunkerVanillaBlockType.LIME_CONCRETE_POWDER)
                            .put(6, ChunkerVanillaBlockType.PINK_CONCRETE_POWDER)
                            .put(7, ChunkerVanillaBlockType.GRAY_CONCRETE_POWDER)
                            .put(8, ChunkerVanillaBlockType.LIGHT_GRAY_CONCRETE_POWDER)
                            .put(9, ChunkerVanillaBlockType.CYAN_CONCRETE_POWDER)
                            .put(10, ChunkerVanillaBlockType.PURPLE_CONCRETE_POWDER)
                            .put(11, ChunkerVanillaBlockType.BLUE_CONCRETE_POWDER)
                            .put(12, ChunkerVanillaBlockType.BROWN_CONCRETE_POWDER)
                            .put(13, ChunkerVanillaBlockType.GREEN_CONCRETE_POWDER)
                            .put(14, ChunkerVanillaBlockType.RED_CONCRETE_POWDER)
                            .put(15, ChunkerVanillaBlockType.BLACK_CONCRETE_POWDER)
                            .build()
            ));

            // Concrete
            register(BlockMapping.flatten("minecraft:concrete", "data",
                    ImmutableMultimap.<Integer, ChunkerVanillaBlockType>builder()
                            .put(0, ChunkerVanillaBlockType.WHITE_CONCRETE)
                            .put(1, ChunkerVanillaBlockType.ORANGE_CONCRETE)
                            .put(2, ChunkerVanillaBlockType.MAGENTA_CONCRETE)
                            .put(3, ChunkerVanillaBlockType.LIGHT_BLUE_CONCRETE)
                            .put(4, ChunkerVanillaBlockType.YELLOW_CONCRETE)
                            .put(5, ChunkerVanillaBlockType.LIME_CONCRETE)
                            .put(6, ChunkerVanillaBlockType.PINK_CONCRETE)
                            .put(7, ChunkerVanillaBlockType.GRAY_CONCRETE)
                            .put(8, ChunkerVanillaBlockType.LIGHT_GRAY_CONCRETE)
                            .put(9, ChunkerVanillaBlockType.CYAN_CONCRETE)
                            .put(10, ChunkerVanillaBlockType.PURPLE_CONCRETE)
                            .put(11, ChunkerVanillaBlockType.BLUE_CONCRETE)
                            .put(12, ChunkerVanillaBlockType.BROWN_CONCRETE)
                            .put(13, ChunkerVanillaBlockType.GREEN_CONCRETE)
                            .put(14, ChunkerVanillaBlockType.RED_CONCRETE)
                            .put(15, ChunkerVanillaBlockType.BLACK_CONCRETE)
                            .build()
            ));

            register(BlockMapping.group(ImmutableMultimap.<String, ChunkerVanillaBlockType>builder()
                            .put("minecraft:black_glazed_terracotta", ChunkerVanillaBlockType.BLACK_GLAZED_TERRACOTTA)
                            .put("minecraft:blue_glazed_terracotta", ChunkerVanillaBlockType.BLUE_GLAZED_TERRACOTTA)
                            .put("minecraft:brown_glazed_terracotta", ChunkerVanillaBlockType.BROWN_GLAZED_TERRACOTTA)
                            .put("minecraft:cyan_glazed_terracotta", ChunkerVanillaBlockType.CYAN_GLAZED_TERRACOTTA)
                            .put("minecraft:gray_glazed_terracotta", ChunkerVanillaBlockType.GRAY_GLAZED_TERRACOTTA)
                            .put("minecraft:green_glazed_terracotta", ChunkerVanillaBlockType.GREEN_GLAZED_TERRACOTTA)
                            .put("minecraft:light_blue_glazed_terracotta", ChunkerVanillaBlockType.LIGHT_BLUE_GLAZED_TERRACOTTA)
                            .put("minecraft:silver_glazed_terracotta", ChunkerVanillaBlockType.LIGHT_GRAY_GLAZED_TERRACOTTA)
                            .put("minecraft:lime_glazed_terracotta", ChunkerVanillaBlockType.LIME_GLAZED_TERRACOTTA)
                            .put("minecraft:magenta_glazed_terracotta", ChunkerVanillaBlockType.MAGENTA_GLAZED_TERRACOTTA)
                            .put("minecraft:orange_glazed_terracotta", ChunkerVanillaBlockType.ORANGE_GLAZED_TERRACOTTA)
                            .put("minecraft:pink_glazed_terracotta", ChunkerVanillaBlockType.PINK_GLAZED_TERRACOTTA)
                            .put("minecraft:purple_glazed_terracotta", ChunkerVanillaBlockType.PURPLE_GLAZED_TERRACOTTA)
                            .put("minecraft:red_glazed_terracotta", ChunkerVanillaBlockType.RED_GLAZED_TERRACOTTA)
                            .put("minecraft:white_glazed_terracotta", ChunkerVanillaBlockType.WHITE_GLAZED_TERRACOTTA)
                            .put("minecraft:yellow_glazed_terracotta", ChunkerVanillaBlockType.YELLOW_GLAZED_TERRACOTTA)
                            .build(),
                    JavaLegacyStateGroups.FACING_HORIZONTAL_SWNE));
        }

        // 1.13+
        if (version.isGreaterThanOrEqual(1, 13, 0)) {
            register(BlockMapping.of("minecraft:acacia_button", ChunkerVanillaBlockType.ACACIA_BUTTON));
            register(BlockMapping.of("minecraft:acacia_leaves", ChunkerVanillaBlockType.ACACIA_LEAVES));
            register(BlockMapping.of("minecraft:acacia_log", ChunkerVanillaBlockType.ACACIA_LOG));
            register(BlockMapping.of("minecraft:acacia_planks", ChunkerVanillaBlockType.ACACIA_PLANKS));
            register(BlockMapping.of("minecraft:acacia_pressure_plate", ChunkerVanillaBlockType.ACACIA_PRESSURE_PLATE));
            register(BlockMapping.of("minecraft:acacia_sapling", ChunkerVanillaBlockType.ACACIA_SAPLING));
            register(BlockMapping.of("minecraft:acacia_sign", ChunkerVanillaBlockType.ACACIA_SIGN));
            register(BlockMapping.of("minecraft:acacia_slab", ChunkerVanillaBlockType.ACACIA_SLAB));
            register(BlockMapping.of("minecraft:acacia_trapdoor", ChunkerVanillaBlockType.ACACIA_TRAPDOOR));
            register(BlockMapping.of("minecraft:acacia_wall_sign", ChunkerVanillaBlockType.ACACIA_WALL_SIGN));
            register(BlockMapping.of("minecraft:acacia_wood", ChunkerVanillaBlockType.ACACIA_WOOD));
            register(BlockMapping.of("minecraft:allium", ChunkerVanillaBlockType.ALLIUM));
            register(BlockMapping.of("minecraft:andesite", ChunkerVanillaBlockType.ANDESITE));
            register(BlockMapping.of("minecraft:andesite_slab", ChunkerVanillaBlockType.ANDESITE_SLAB));
            register(BlockMapping.of("minecraft:andesite_stairs", ChunkerVanillaBlockType.ANDESITE_STAIRS));
            register(BlockMapping.of("minecraft:andesite_wall", ChunkerVanillaBlockType.ANDESITE_WALL));
            register(BlockMapping.of("minecraft:attached_melon_stem", ChunkerVanillaBlockType.ATTACHED_MELON_STEM));
            register(BlockMapping.of("minecraft:attached_pumpkin_stem", ChunkerVanillaBlockType.ATTACHED_PUMPKIN_STEM));
            register(BlockMapping.of("minecraft:azure_bluet", ChunkerVanillaBlockType.AZURE_BLUET));
            register(BlockMapping.of("minecraft:bamboo", ChunkerVanillaBlockType.BAMBOO));
            register(BlockMapping.of("minecraft:bamboo_sapling", ChunkerVanillaBlockType.BAMBOO_SAPLING));
            register(BlockMapping.of("minecraft:barrel", ChunkerVanillaBlockType.BARREL));
            register(BlockMapping.of("minecraft:bee_nest", ChunkerVanillaBlockType.BEE_NEST));
            register(BlockMapping.of("minecraft:beehive", ChunkerVanillaBlockType.BEEHIVE));
            register(BlockMapping.of("minecraft:bell", ChunkerVanillaBlockType.BELL));
            register(BlockMapping.of("minecraft:birch_button", ChunkerVanillaBlockType.BIRCH_BUTTON));
            register(BlockMapping.of("minecraft:birch_leaves", ChunkerVanillaBlockType.BIRCH_LEAVES));
            register(BlockMapping.of("minecraft:birch_log", ChunkerVanillaBlockType.BIRCH_LOG));
            register(BlockMapping.of("minecraft:birch_planks", ChunkerVanillaBlockType.BIRCH_PLANKS));
            register(BlockMapping.of("minecraft:birch_pressure_plate", ChunkerVanillaBlockType.BIRCH_PRESSURE_PLATE));
            register(BlockMapping.of("minecraft:birch_sapling", ChunkerVanillaBlockType.BIRCH_SAPLING));
            register(BlockMapping.of("minecraft:birch_sign", ChunkerVanillaBlockType.BIRCH_SIGN));
            register(BlockMapping.of("minecraft:birch_slab", ChunkerVanillaBlockType.BIRCH_SLAB));
            register(BlockMapping.of("minecraft:birch_trapdoor", ChunkerVanillaBlockType.BIRCH_TRAPDOOR));
            register(BlockMapping.of("minecraft:birch_wall_sign", ChunkerVanillaBlockType.BIRCH_WALL_SIGN));
            register(BlockMapping.of("minecraft:birch_wood", ChunkerVanillaBlockType.BIRCH_WOOD));
            register(BlockMapping.of("minecraft:black_banner", ChunkerVanillaBlockType.BLACK_BANNER));
            register(BlockMapping.of("minecraft:black_bed", ChunkerVanillaBlockType.BLACK_BED));
            register(BlockMapping.of("minecraft:black_carpet", ChunkerVanillaBlockType.BLACK_CARPET));
            register(BlockMapping.of("minecraft:black_concrete", ChunkerVanillaBlockType.BLACK_CONCRETE));
            register(BlockMapping.of("minecraft:black_concrete_powder", ChunkerVanillaBlockType.BLACK_CONCRETE_POWDER));
            register(BlockMapping.of("minecraft:black_stained_glass", ChunkerVanillaBlockType.BLACK_STAINED_GLASS));
            register(BlockMapping.of("minecraft:black_stained_glass_pane", ChunkerVanillaBlockType.BLACK_STAINED_GLASS_PANE));
            register(BlockMapping.of("minecraft:black_terracotta", ChunkerVanillaBlockType.BLACK_TERRACOTTA));
            register(BlockMapping.of("minecraft:black_wall_banner", ChunkerVanillaBlockType.BLACK_WALL_BANNER));
            register(BlockMapping.of("minecraft:blast_furnace", ChunkerVanillaBlockType.BLAST_FURNACE));
            register(BlockMapping.of("minecraft:blue_banner", ChunkerVanillaBlockType.BLUE_BANNER));
            register(BlockMapping.of("minecraft:blue_bed", ChunkerVanillaBlockType.BLUE_BED));
            register(BlockMapping.of("minecraft:blue_carpet", ChunkerVanillaBlockType.BLUE_CARPET));
            register(BlockMapping.of("minecraft:blue_concrete", ChunkerVanillaBlockType.BLUE_CONCRETE));
            register(BlockMapping.of("minecraft:blue_concrete_powder", ChunkerVanillaBlockType.BLUE_CONCRETE_POWDER));
            register(BlockMapping.of("minecraft:blue_ice", ChunkerVanillaBlockType.BLUE_ICE));
            register(BlockMapping.of("minecraft:blue_orchid", ChunkerVanillaBlockType.BLUE_ORCHID));
            register(BlockMapping.of("minecraft:blue_stained_glass", ChunkerVanillaBlockType.BLUE_STAINED_GLASS));
            register(BlockMapping.of("minecraft:blue_stained_glass_pane", ChunkerVanillaBlockType.BLUE_STAINED_GLASS_PANE));
            register(BlockMapping.of("minecraft:blue_terracotta", ChunkerVanillaBlockType.BLUE_TERRACOTTA));
            register(BlockMapping.of("minecraft:blue_wall_banner", ChunkerVanillaBlockType.BLUE_WALL_BANNER));
            register(BlockMapping.of("minecraft:brain_coral", ChunkerVanillaBlockType.BRAIN_CORAL));
            register(BlockMapping.of("minecraft:brain_coral_block", ChunkerVanillaBlockType.BRAIN_CORAL_BLOCK));
            register(BlockMapping.of("minecraft:brain_coral_fan", ChunkerVanillaBlockType.BRAIN_CORAL_FAN));
            register(BlockMapping.of("minecraft:brain_coral_wall_fan", ChunkerVanillaBlockType.BRAIN_CORAL_WALL_FAN));
            register(BlockMapping.of("minecraft:brick_slab", ChunkerVanillaBlockType.BRICK_SLAB));
            register(BlockMapping.of("minecraft:brick_wall", ChunkerVanillaBlockType.BRICK_WALL));
            register(BlockMapping.of("minecraft:bricks", ChunkerVanillaBlockType.BRICKS));
            register(BlockMapping.of("minecraft:brown_banner", ChunkerVanillaBlockType.BROWN_BANNER));
            register(BlockMapping.of("minecraft:brown_bed", ChunkerVanillaBlockType.BROWN_BED));
            register(BlockMapping.of("minecraft:brown_carpet", ChunkerVanillaBlockType.BROWN_CARPET));
            register(BlockMapping.of("minecraft:brown_concrete", ChunkerVanillaBlockType.BROWN_CONCRETE));
            register(BlockMapping.of("minecraft:brown_concrete_powder", ChunkerVanillaBlockType.BROWN_CONCRETE_POWDER));
            register(BlockMapping.of("minecraft:brown_stained_glass", ChunkerVanillaBlockType.BROWN_STAINED_GLASS));
            register(BlockMapping.of("minecraft:brown_stained_glass_pane", ChunkerVanillaBlockType.BROWN_STAINED_GLASS_PANE));
            register(BlockMapping.of("minecraft:brown_terracotta", ChunkerVanillaBlockType.BROWN_TERRACOTTA));
            register(BlockMapping.of("minecraft:brown_wall_banner", ChunkerVanillaBlockType.BROWN_WALL_BANNER));
            register(BlockMapping.of("minecraft:bubble_column", ChunkerVanillaBlockType.BUBBLE_COLUMN));
            register(BlockMapping.of("minecraft:bubble_coral", ChunkerVanillaBlockType.BUBBLE_CORAL));
            register(BlockMapping.of("minecraft:bubble_coral_block", ChunkerVanillaBlockType.BUBBLE_CORAL_BLOCK));
            register(BlockMapping.of("minecraft:bubble_coral_fan", ChunkerVanillaBlockType.BUBBLE_CORAL_FAN));
            register(BlockMapping.of("minecraft:bubble_coral_wall_fan", ChunkerVanillaBlockType.BUBBLE_CORAL_WALL_FAN));
            register(BlockMapping.of("minecraft:campfire", ChunkerVanillaBlockType.CAMPFIRE));
            register(BlockMapping.of("minecraft:cartography_table", ChunkerVanillaBlockType.CARTOGRAPHY_TABLE));
            register(BlockMapping.of("minecraft:carved_pumpkin", ChunkerVanillaBlockType.CARVED_PUMPKIN));
            register(BlockMapping.of("minecraft:cave_air", ChunkerVanillaBlockType.CAVE_AIR));
            register(BlockMapping.of("minecraft:chain", ChunkerVanillaBlockType.CHAIN));
            register(BlockMapping.of("minecraft:chipped_anvil", ChunkerVanillaBlockType.CHIPPED_ANVIL));
            register(BlockMapping.of("minecraft:chiseled_quartz_block", ChunkerVanillaBlockType.CHISELED_QUARTZ_BLOCK));
            register(BlockMapping.of("minecraft:chiseled_red_sandstone", ChunkerVanillaBlockType.CHISELED_RED_SANDSTONE));
            register(BlockMapping.of("minecraft:chiseled_sandstone", ChunkerVanillaBlockType.CHISELED_SANDSTONE));
            register(BlockMapping.of("minecraft:chiseled_stone_bricks", ChunkerVanillaBlockType.CHISELED_STONE_BRICKS));
            register(BlockMapping.of("minecraft:coarse_dirt", ChunkerVanillaBlockType.COARSE_DIRT));
            register(BlockMapping.of("minecraft:cobblestone_slab", ChunkerVanillaBlockType.COBBLESTONE_SLAB));
            register(BlockMapping.of("minecraft:cobblestone_stairs", ChunkerVanillaBlockType.COBBLESTONE_STAIRS));
            register(BlockMapping.of("minecraft:cobweb", ChunkerVanillaBlockType.COBWEB));
            register(BlockMapping.of("minecraft:comparator", ChunkerVanillaBlockType.COMPARATOR));
            register(BlockMapping.of("minecraft:composter", ChunkerVanillaBlockType.COMPOSTER));
            register(BlockMapping.of("minecraft:conduit", ChunkerVanillaBlockType.CONDUIT));
            register(BlockMapping.of("minecraft:cornflower", ChunkerVanillaBlockType.CORNFLOWER));
            register(BlockMapping.of("minecraft:cracked_stone_bricks", ChunkerVanillaBlockType.CRACKED_STONE_BRICKS));
            register(BlockMapping.of("minecraft:creeper_head", ChunkerVanillaBlockType.CREEPER_HEAD));
            register(BlockMapping.of("minecraft:creeper_wall_head", ChunkerVanillaBlockType.CREEPER_WALL_HEAD));
            register(BlockMapping.of("minecraft:cut_red_sandstone", ChunkerVanillaBlockType.CUT_RED_SANDSTONE));
            register(BlockMapping.of("minecraft:cut_red_sandstone_slab", ChunkerVanillaBlockType.CUT_RED_SANDSTONE_SLAB));
            register(BlockMapping.of("minecraft:cut_sandstone", ChunkerVanillaBlockType.CUT_SANDSTONE));
            register(BlockMapping.of("minecraft:cut_sandstone_slab", ChunkerVanillaBlockType.CUT_SANDSTONE_SLAB));
            register(BlockMapping.of("minecraft:cyan_banner", ChunkerVanillaBlockType.CYAN_BANNER));
            register(BlockMapping.of("minecraft:cyan_bed", ChunkerVanillaBlockType.CYAN_BED));
            register(BlockMapping.of("minecraft:cyan_carpet", ChunkerVanillaBlockType.CYAN_CARPET));
            register(BlockMapping.of("minecraft:cyan_concrete", ChunkerVanillaBlockType.CYAN_CONCRETE));
            register(BlockMapping.of("minecraft:cyan_concrete_powder", ChunkerVanillaBlockType.CYAN_CONCRETE_POWDER));
            register(BlockMapping.of("minecraft:cyan_stained_glass", ChunkerVanillaBlockType.CYAN_STAINED_GLASS));
            register(BlockMapping.of("minecraft:cyan_stained_glass_pane", ChunkerVanillaBlockType.CYAN_STAINED_GLASS_PANE));
            register(BlockMapping.of("minecraft:cyan_terracotta", ChunkerVanillaBlockType.CYAN_TERRACOTTA));
            register(BlockMapping.of("minecraft:cyan_wall_banner", ChunkerVanillaBlockType.CYAN_WALL_BANNER));
            register(BlockMapping.of("minecraft:damaged_anvil", ChunkerVanillaBlockType.DAMAGED_ANVIL));
            register(BlockMapping.of("minecraft:dandelion", ChunkerVanillaBlockType.DANDELION));
            register(BlockMapping.of("minecraft:dark_oak_button", ChunkerVanillaBlockType.DARK_OAK_BUTTON));
            register(BlockMapping.of("minecraft:dark_oak_leaves", ChunkerVanillaBlockType.DARK_OAK_LEAVES));
            register(BlockMapping.of("minecraft:dark_oak_log", ChunkerVanillaBlockType.DARK_OAK_LOG));
            register(BlockMapping.of("minecraft:dark_oak_planks", ChunkerVanillaBlockType.DARK_OAK_PLANKS));
            register(BlockMapping.of("minecraft:dark_oak_pressure_plate", ChunkerVanillaBlockType.DARK_OAK_PRESSURE_PLATE));
            register(BlockMapping.of("minecraft:dark_oak_sapling", ChunkerVanillaBlockType.DARK_OAK_SAPLING));
            register(BlockMapping.of("minecraft:dark_oak_sign", ChunkerVanillaBlockType.DARK_OAK_SIGN));
            register(BlockMapping.of("minecraft:dark_oak_slab", ChunkerVanillaBlockType.DARK_OAK_SLAB));
            register(BlockMapping.of("minecraft:dark_oak_trapdoor", ChunkerVanillaBlockType.DARK_OAK_TRAPDOOR));
            register(BlockMapping.of("minecraft:dark_oak_wall_sign", ChunkerVanillaBlockType.DARK_OAK_WALL_SIGN));
            register(BlockMapping.of("minecraft:dark_oak_wood", ChunkerVanillaBlockType.DARK_OAK_WOOD));
            register(BlockMapping.of("minecraft:dark_prismarine", ChunkerVanillaBlockType.DARK_PRISMARINE));
            register(BlockMapping.of("minecraft:dark_prismarine_slab", ChunkerVanillaBlockType.DARK_PRISMARINE_SLAB));
            register(BlockMapping.of("minecraft:dark_prismarine_stairs", ChunkerVanillaBlockType.DARK_PRISMARINE_STAIRS));
            register(BlockMapping.of("minecraft:dead_brain_coral", ChunkerVanillaBlockType.DEAD_BRAIN_CORAL));
            register(BlockMapping.of("minecraft:dead_brain_coral_block", ChunkerVanillaBlockType.DEAD_BRAIN_CORAL_BLOCK));
            register(BlockMapping.of("minecraft:dead_brain_coral_fan", ChunkerVanillaBlockType.DEAD_BRAIN_CORAL_FAN));
            register(BlockMapping.of("minecraft:dead_brain_coral_wall_fan", ChunkerVanillaBlockType.DEAD_BRAIN_CORAL_WALL_FAN));
            register(BlockMapping.of("minecraft:dead_bubble_coral", ChunkerVanillaBlockType.DEAD_BUBBLE_CORAL));
            register(BlockMapping.of("minecraft:dead_bubble_coral_block", ChunkerVanillaBlockType.DEAD_BUBBLE_CORAL_BLOCK));
            register(BlockMapping.of("minecraft:dead_bubble_coral_fan", ChunkerVanillaBlockType.DEAD_BUBBLE_CORAL_FAN));
            register(BlockMapping.of("minecraft:dead_bubble_coral_wall_fan", ChunkerVanillaBlockType.DEAD_BUBBLE_CORAL_WALL_FAN));
            register(BlockMapping.of("minecraft:dead_bush", ChunkerVanillaBlockType.DEAD_BUSH));
            register(BlockMapping.of("minecraft:dead_fire_coral", ChunkerVanillaBlockType.DEAD_FIRE_CORAL));
            register(BlockMapping.of("minecraft:dead_fire_coral_block", ChunkerVanillaBlockType.DEAD_FIRE_CORAL_BLOCK));
            register(BlockMapping.of("minecraft:dead_fire_coral_fan", ChunkerVanillaBlockType.DEAD_FIRE_CORAL_FAN));
            register(BlockMapping.of("minecraft:dead_fire_coral_wall_fan", ChunkerVanillaBlockType.DEAD_FIRE_CORAL_WALL_FAN));
            register(BlockMapping.of("minecraft:dead_horn_coral", ChunkerVanillaBlockType.DEAD_HORN_CORAL));
            register(BlockMapping.of("minecraft:dead_horn_coral_block", ChunkerVanillaBlockType.DEAD_HORN_CORAL_BLOCK));
            register(BlockMapping.of("minecraft:dead_horn_coral_fan", ChunkerVanillaBlockType.DEAD_HORN_CORAL_FAN));
            register(BlockMapping.of("minecraft:dead_horn_coral_wall_fan", ChunkerVanillaBlockType.DEAD_HORN_CORAL_WALL_FAN));
            register(BlockMapping.of("minecraft:dead_tube_coral", ChunkerVanillaBlockType.DEAD_TUBE_CORAL));
            register(BlockMapping.of("minecraft:dead_tube_coral_block", ChunkerVanillaBlockType.DEAD_TUBE_CORAL_BLOCK));
            register(BlockMapping.of("minecraft:dead_tube_coral_fan", ChunkerVanillaBlockType.DEAD_TUBE_CORAL_FAN));
            register(BlockMapping.of("minecraft:dead_tube_coral_wall_fan", ChunkerVanillaBlockType.DEAD_TUBE_CORAL_WALL_FAN));
            register(BlockMapping.of("minecraft:diorite", ChunkerVanillaBlockType.DIORITE));
            register(BlockMapping.of("minecraft:diorite_slab", ChunkerVanillaBlockType.DIORITE_SLAB));
            register(BlockMapping.of("minecraft:diorite_stairs", ChunkerVanillaBlockType.DIORITE_STAIRS));
            register(BlockMapping.of("minecraft:diorite_wall", ChunkerVanillaBlockType.DIORITE_WALL));
            register(BlockMapping.of("minecraft:dragon_head", ChunkerVanillaBlockType.DRAGON_HEAD));
            register(BlockMapping.of("minecraft:dragon_wall_head", ChunkerVanillaBlockType.DRAGON_WALL_HEAD));
            register(BlockMapping.of("minecraft:dried_kelp_block", ChunkerVanillaBlockType.DRIED_KELP_BLOCK));
            register(BlockMapping.of("minecraft:end_stone_brick_slab", ChunkerVanillaBlockType.END_STONE_BRICK_SLAB));
            register(BlockMapping.of("minecraft:end_stone_brick_stairs", ChunkerVanillaBlockType.END_STONE_BRICK_STAIRS));
            register(BlockMapping.of("minecraft:end_stone_brick_wall", ChunkerVanillaBlockType.END_STONE_BRICK_WALL));
            register(BlockMapping.of("minecraft:end_stone_bricks", ChunkerVanillaBlockType.END_STONE_BRICKS));
            register(BlockMapping.of("minecraft:fern", ChunkerVanillaBlockType.FERN));
            register(BlockMapping.of("minecraft:fire_coral", ChunkerVanillaBlockType.FIRE_CORAL));
            register(BlockMapping.of("minecraft:fire_coral_block", ChunkerVanillaBlockType.FIRE_CORAL_BLOCK));
            register(BlockMapping.of("minecraft:fire_coral_fan", ChunkerVanillaBlockType.FIRE_CORAL_FAN));
            register(BlockMapping.of("minecraft:fire_coral_wall_fan", ChunkerVanillaBlockType.FIRE_CORAL_WALL_FAN));
            register(BlockMapping.of("minecraft:fletching_table", ChunkerVanillaBlockType.FLETCHING_TABLE));
            register(BlockMapping.of("minecraft:granite", ChunkerVanillaBlockType.GRANITE));
            register(BlockMapping.of("minecraft:granite_slab", ChunkerVanillaBlockType.GRANITE_SLAB));
            register(BlockMapping.of("minecraft:granite_stairs", ChunkerVanillaBlockType.GRANITE_STAIRS));
            register(BlockMapping.of("minecraft:granite_wall", ChunkerVanillaBlockType.GRANITE_WALL));
            register(BlockMapping.of("minecraft:grass_block", ChunkerVanillaBlockType.GRASS_BLOCK));
            register(BlockMapping.of("minecraft:gray_banner", ChunkerVanillaBlockType.GRAY_BANNER));
            register(BlockMapping.of("minecraft:gray_bed", ChunkerVanillaBlockType.GRAY_BED));
            register(BlockMapping.of("minecraft:gray_carpet", ChunkerVanillaBlockType.GRAY_CARPET));
            register(BlockMapping.of("minecraft:gray_concrete", ChunkerVanillaBlockType.GRAY_CONCRETE));
            register(BlockMapping.of("minecraft:gray_concrete_powder", ChunkerVanillaBlockType.GRAY_CONCRETE_POWDER));
            register(BlockMapping.of("minecraft:gray_stained_glass", ChunkerVanillaBlockType.GRAY_STAINED_GLASS));
            register(BlockMapping.of("minecraft:gray_stained_glass_pane", ChunkerVanillaBlockType.GRAY_STAINED_GLASS_PANE));
            register(BlockMapping.of("minecraft:gray_terracotta", ChunkerVanillaBlockType.GRAY_TERRACOTTA));
            register(BlockMapping.of("minecraft:gray_wall_banner", ChunkerVanillaBlockType.GRAY_WALL_BANNER));
            register(BlockMapping.of("minecraft:green_banner", ChunkerVanillaBlockType.GREEN_BANNER));
            register(BlockMapping.of("minecraft:green_bed", ChunkerVanillaBlockType.GREEN_BED));
            register(BlockMapping.of("minecraft:green_carpet", ChunkerVanillaBlockType.GREEN_CARPET));
            register(BlockMapping.of("minecraft:green_concrete", ChunkerVanillaBlockType.GREEN_CONCRETE));
            register(BlockMapping.of("minecraft:green_concrete_powder", ChunkerVanillaBlockType.GREEN_CONCRETE_POWDER));
            register(BlockMapping.of("minecraft:green_stained_glass", ChunkerVanillaBlockType.GREEN_STAINED_GLASS));
            register(BlockMapping.of("minecraft:green_stained_glass_pane", ChunkerVanillaBlockType.GREEN_STAINED_GLASS_PANE));
            register(BlockMapping.of("minecraft:green_terracotta", ChunkerVanillaBlockType.GREEN_TERRACOTTA));
            register(BlockMapping.of("minecraft:green_wall_banner", ChunkerVanillaBlockType.GREEN_WALL_BANNER));
            register(BlockMapping.of("minecraft:grindstone", ChunkerVanillaBlockType.GRINDSTONE));
            register(BlockMapping.of("minecraft:honey_block", ChunkerVanillaBlockType.HONEY_BLOCK));
            register(BlockMapping.of("minecraft:honeycomb_block", ChunkerVanillaBlockType.HONEYCOMB_BLOCK));
            register(BlockMapping.of("minecraft:horn_coral", ChunkerVanillaBlockType.HORN_CORAL));
            register(BlockMapping.of("minecraft:horn_coral_block", ChunkerVanillaBlockType.HORN_CORAL_BLOCK));
            register(BlockMapping.of("minecraft:horn_coral_fan", ChunkerVanillaBlockType.HORN_CORAL_FAN));
            register(BlockMapping.of("minecraft:horn_coral_wall_fan", ChunkerVanillaBlockType.HORN_CORAL_WALL_FAN));
            register(BlockMapping.of("minecraft:infested_chiseled_stone_bricks", ChunkerVanillaBlockType.INFESTED_CHISELED_STONE_BRICKS));
            register(BlockMapping.of("minecraft:infested_cobblestone", ChunkerVanillaBlockType.INFESTED_COBBLESTONE));
            register(BlockMapping.of("minecraft:infested_cracked_stone_bricks", ChunkerVanillaBlockType.INFESTED_CRACKED_STONE_BRICKS));
            register(BlockMapping.of("minecraft:infested_mossy_stone_bricks", ChunkerVanillaBlockType.INFESTED_MOSSY_STONE_BRICKS));
            register(BlockMapping.of("minecraft:infested_stone", ChunkerVanillaBlockType.INFESTED_STONE));
            register(BlockMapping.of("minecraft:infested_stone_bricks", ChunkerVanillaBlockType.INFESTED_STONE_BRICKS));
            register(BlockMapping.of("minecraft:jack_o_lantern", ChunkerVanillaBlockType.JACK_O_LANTERN));
            register(BlockMapping.of("minecraft:jigsaw", ChunkerVanillaBlockType.JIGSAW));
            register(BlockMapping.of("minecraft:jungle_button", ChunkerVanillaBlockType.JUNGLE_BUTTON));
            register(BlockMapping.of("minecraft:jungle_leaves", ChunkerVanillaBlockType.JUNGLE_LEAVES));
            register(BlockMapping.of("minecraft:jungle_log", ChunkerVanillaBlockType.JUNGLE_LOG));
            register(BlockMapping.of("minecraft:jungle_planks", ChunkerVanillaBlockType.JUNGLE_PLANKS));
            register(BlockMapping.of("minecraft:jungle_pressure_plate", ChunkerVanillaBlockType.JUNGLE_PRESSURE_PLATE));
            register(BlockMapping.of("minecraft:jungle_sapling", ChunkerVanillaBlockType.JUNGLE_SAPLING));
            register(BlockMapping.of("minecraft:jungle_sign", ChunkerVanillaBlockType.JUNGLE_SIGN));
            register(BlockMapping.of("minecraft:jungle_slab", ChunkerVanillaBlockType.JUNGLE_SLAB));
            register(BlockMapping.of("minecraft:jungle_trapdoor", ChunkerVanillaBlockType.JUNGLE_TRAPDOOR));
            register(BlockMapping.of("minecraft:jungle_wall_sign", ChunkerVanillaBlockType.JUNGLE_WALL_SIGN));
            register(BlockMapping.of("minecraft:jungle_wood", ChunkerVanillaBlockType.JUNGLE_WOOD));
            register(BlockMapping.of("minecraft:kelp", ChunkerVanillaBlockType.KELP));
            register(BlockMapping.of("minecraft:kelp_plant", ChunkerVanillaBlockType.KELP_PLANT));
            register(BlockMapping.of("minecraft:lantern", ChunkerVanillaBlockType.LANTERN));
            register(BlockMapping.of("minecraft:large_fern", ChunkerVanillaBlockType.LARGE_FERN));
            register(BlockMapping.of("minecraft:lectern", ChunkerVanillaBlockType.LECTERN));
            register(BlockMapping.of("minecraft:light_blue_banner", ChunkerVanillaBlockType.LIGHT_BLUE_BANNER));
            register(BlockMapping.of("minecraft:light_blue_bed", ChunkerVanillaBlockType.LIGHT_BLUE_BED));
            register(BlockMapping.of("minecraft:light_blue_carpet", ChunkerVanillaBlockType.LIGHT_BLUE_CARPET));
            register(BlockMapping.of("minecraft:light_blue_concrete", ChunkerVanillaBlockType.LIGHT_BLUE_CONCRETE));
            register(BlockMapping.of("minecraft:light_blue_concrete_powder", ChunkerVanillaBlockType.LIGHT_BLUE_CONCRETE_POWDER));
            register(BlockMapping.of("minecraft:light_blue_stained_glass", ChunkerVanillaBlockType.LIGHT_BLUE_STAINED_GLASS));
            register(BlockMapping.of("minecraft:light_blue_stained_glass_pane", ChunkerVanillaBlockType.LIGHT_BLUE_STAINED_GLASS_PANE));
            register(BlockMapping.of("minecraft:light_blue_terracotta", ChunkerVanillaBlockType.LIGHT_BLUE_TERRACOTTA));
            register(BlockMapping.of("minecraft:light_blue_wall_banner", ChunkerVanillaBlockType.LIGHT_BLUE_WALL_BANNER));
            register(BlockMapping.of("minecraft:light_gray_banner", ChunkerVanillaBlockType.LIGHT_GRAY_BANNER));
            register(BlockMapping.of("minecraft:light_gray_bed", ChunkerVanillaBlockType.LIGHT_GRAY_BED));
            register(BlockMapping.of("minecraft:light_gray_carpet", ChunkerVanillaBlockType.LIGHT_GRAY_CARPET));
            register(BlockMapping.of("minecraft:light_gray_concrete", ChunkerVanillaBlockType.LIGHT_GRAY_CONCRETE));
            register(BlockMapping.of("minecraft:light_gray_concrete_powder", ChunkerVanillaBlockType.LIGHT_GRAY_CONCRETE_POWDER));
            register(BlockMapping.of("minecraft:light_gray_glazed_terracotta", ChunkerVanillaBlockType.LIGHT_GRAY_GLAZED_TERRACOTTA));
            register(BlockMapping.of("minecraft:light_gray_shulker_box", ChunkerVanillaBlockType.LIGHT_GRAY_SHULKER_BOX));
            register(BlockMapping.of("minecraft:light_gray_stained_glass", ChunkerVanillaBlockType.LIGHT_GRAY_STAINED_GLASS));
            register(BlockMapping.of("minecraft:light_gray_stained_glass_pane", ChunkerVanillaBlockType.LIGHT_GRAY_STAINED_GLASS_PANE));
            register(BlockMapping.of("minecraft:light_gray_terracotta", ChunkerVanillaBlockType.LIGHT_GRAY_TERRACOTTA));
            register(BlockMapping.of("minecraft:light_gray_wall_banner", ChunkerVanillaBlockType.LIGHT_GRAY_WALL_BANNER));
            register(BlockMapping.of("minecraft:lilac", ChunkerVanillaBlockType.LILAC));
            register(BlockMapping.of("minecraft:lily_of_the_valley", ChunkerVanillaBlockType.LILY_OF_THE_VALLEY));
            register(BlockMapping.of("minecraft:lily_pad", ChunkerVanillaBlockType.LILY_PAD));
            register(BlockMapping.of("minecraft:lime_banner", ChunkerVanillaBlockType.LIME_BANNER));
            register(BlockMapping.of("minecraft:lime_bed", ChunkerVanillaBlockType.LIME_BED));
            register(BlockMapping.of("minecraft:lime_carpet", ChunkerVanillaBlockType.LIME_CARPET));
            register(BlockMapping.of("minecraft:lime_concrete", ChunkerVanillaBlockType.LIME_CONCRETE));
            register(BlockMapping.of("minecraft:lime_concrete_powder", ChunkerVanillaBlockType.LIME_CONCRETE_POWDER));
            register(BlockMapping.of("minecraft:lime_stained_glass", ChunkerVanillaBlockType.LIME_STAINED_GLASS));
            register(BlockMapping.of("minecraft:lime_stained_glass_pane", ChunkerVanillaBlockType.LIME_STAINED_GLASS_PANE));
            register(BlockMapping.of("minecraft:lime_terracotta", ChunkerVanillaBlockType.LIME_TERRACOTTA));
            register(BlockMapping.of("minecraft:lime_wall_banner", ChunkerVanillaBlockType.LIME_WALL_BANNER));
            register(BlockMapping.of("minecraft:loom", ChunkerVanillaBlockType.LOOM));
            register(BlockMapping.of("minecraft:magenta_banner", ChunkerVanillaBlockType.MAGENTA_BANNER));
            register(BlockMapping.of("minecraft:magenta_bed", ChunkerVanillaBlockType.MAGENTA_BED));
            register(BlockMapping.of("minecraft:magenta_carpet", ChunkerVanillaBlockType.MAGENTA_CARPET));
            register(BlockMapping.of("minecraft:magenta_concrete", ChunkerVanillaBlockType.MAGENTA_CONCRETE));
            register(BlockMapping.of("minecraft:magenta_concrete_powder", ChunkerVanillaBlockType.MAGENTA_CONCRETE_POWDER));
            register(BlockMapping.of("minecraft:magenta_stained_glass", ChunkerVanillaBlockType.MAGENTA_STAINED_GLASS));
            register(BlockMapping.of("minecraft:magenta_stained_glass_pane", ChunkerVanillaBlockType.MAGENTA_STAINED_GLASS_PANE));
            register(BlockMapping.of("minecraft:magenta_terracotta", ChunkerVanillaBlockType.MAGENTA_TERRACOTTA));
            register(BlockMapping.of("minecraft:magenta_wall_banner", ChunkerVanillaBlockType.MAGENTA_WALL_BANNER));
            register(BlockMapping.of("minecraft:magma_block", ChunkerVanillaBlockType.MAGMA_BLOCK));
            register(BlockMapping.of("minecraft:melon", ChunkerVanillaBlockType.MELON));
            register(BlockMapping.of("minecraft:mossy_cobblestone_slab", ChunkerVanillaBlockType.MOSSY_COBBLESTONE_SLAB));
            register(BlockMapping.of("minecraft:mossy_cobblestone_stairs", ChunkerVanillaBlockType.MOSSY_COBBLESTONE_STAIRS));
            register(BlockMapping.of("minecraft:mossy_cobblestone_wall", ChunkerVanillaBlockType.MOSSY_COBBLESTONE_WALL));
            register(BlockMapping.of("minecraft:mossy_stone_brick_slab", ChunkerVanillaBlockType.MOSSY_STONE_BRICK_SLAB));
            register(BlockMapping.of("minecraft:mossy_stone_brick_stairs", ChunkerVanillaBlockType.MOSSY_STONE_BRICK_STAIRS));
            register(BlockMapping.of("minecraft:mossy_stone_brick_wall", ChunkerVanillaBlockType.MOSSY_STONE_BRICK_WALL));
            register(BlockMapping.of("minecraft:mossy_stone_bricks", ChunkerVanillaBlockType.MOSSY_STONE_BRICKS));
            register(BlockMapping.of("minecraft:moving_piston", ChunkerVanillaBlockType.MOVING_PISTON_JAVA));
            register(BlockMapping.of("minecraft:mushroom_stem", ChunkerVanillaBlockType.MUSHROOM_STEM));
            register(BlockMapping.of("minecraft:nether_brick_slab", ChunkerVanillaBlockType.NETHER_BRICK_SLAB));
            register(BlockMapping.of("minecraft:nether_brick_wall", ChunkerVanillaBlockType.NETHER_BRICK_WALL));
            register(BlockMapping.of("minecraft:nether_bricks", ChunkerVanillaBlockType.NETHER_BRICKS));
            register(BlockMapping.of("minecraft:nether_portal", ChunkerVanillaBlockType.NETHER_PORTAL));
            register(BlockMapping.of("minecraft:nether_quartz_ore", ChunkerVanillaBlockType.NETHER_QUARTZ_ORE));
            register(BlockMapping.of("minecraft:note_block", ChunkerVanillaBlockType.NOTE_BLOCK));
            register(BlockMapping.of("minecraft:oak_button", ChunkerVanillaBlockType.OAK_BUTTON));
            register(BlockMapping.of("minecraft:oak_door", ChunkerVanillaBlockType.OAK_DOOR));
            register(BlockMapping.of("minecraft:oak_fence", ChunkerVanillaBlockType.OAK_FENCE));
            register(BlockMapping.of("minecraft:oak_fence_gate", ChunkerVanillaBlockType.OAK_FENCE_GATE));
            register(BlockMapping.of("minecraft:oak_leaves", ChunkerVanillaBlockType.OAK_LEAVES));
            register(BlockMapping.of("minecraft:oak_log", ChunkerVanillaBlockType.OAK_LOG));
            register(BlockMapping.of("minecraft:oak_planks", ChunkerVanillaBlockType.OAK_PLANKS));
            register(BlockMapping.of("minecraft:oak_pressure_plate", ChunkerVanillaBlockType.OAK_PRESSURE_PLATE));
            register(BlockMapping.of("minecraft:oak_sapling", ChunkerVanillaBlockType.OAK_SAPLING));
            register(BlockMapping.of("minecraft:oak_sign", ChunkerVanillaBlockType.OAK_SIGN));
            register(BlockMapping.of("minecraft:oak_slab", ChunkerVanillaBlockType.OAK_SLAB));
            register(BlockMapping.of("minecraft:oak_trapdoor", ChunkerVanillaBlockType.OAK_TRAPDOOR));
            register(BlockMapping.of("minecraft:oak_wall_sign", ChunkerVanillaBlockType.OAK_WALL_SIGN));
            register(BlockMapping.of("minecraft:oak_wood", ChunkerVanillaBlockType.OAK_WOOD));
            register(BlockMapping.of("minecraft:orange_banner", ChunkerVanillaBlockType.ORANGE_BANNER));
            register(BlockMapping.of("minecraft:orange_bed", ChunkerVanillaBlockType.ORANGE_BED));
            register(BlockMapping.of("minecraft:orange_carpet", ChunkerVanillaBlockType.ORANGE_CARPET));
            register(BlockMapping.of("minecraft:orange_concrete", ChunkerVanillaBlockType.ORANGE_CONCRETE));
            register(BlockMapping.of("minecraft:orange_concrete_powder", ChunkerVanillaBlockType.ORANGE_CONCRETE_POWDER));
            register(BlockMapping.of("minecraft:orange_stained_glass", ChunkerVanillaBlockType.ORANGE_STAINED_GLASS));
            register(BlockMapping.of("minecraft:orange_stained_glass_pane", ChunkerVanillaBlockType.ORANGE_STAINED_GLASS_PANE));
            register(BlockMapping.of("minecraft:orange_terracotta", ChunkerVanillaBlockType.ORANGE_TERRACOTTA));
            register(BlockMapping.of("minecraft:orange_tulip", ChunkerVanillaBlockType.ORANGE_TULIP));
            register(BlockMapping.of("minecraft:orange_wall_banner", ChunkerVanillaBlockType.ORANGE_WALL_BANNER));
            register(BlockMapping.of("minecraft:oxeye_daisy", ChunkerVanillaBlockType.OXEYE_DAISY));
            register(BlockMapping.of("minecraft:peony", ChunkerVanillaBlockType.PEONY));
            register(BlockMapping.of("minecraft:petrified_oak_slab", ChunkerVanillaBlockType.PETRIFIED_OAK_SLAB));
            register(BlockMapping.of("minecraft:pink_banner", ChunkerVanillaBlockType.PINK_BANNER));
            register(BlockMapping.of("minecraft:pink_bed", ChunkerVanillaBlockType.PINK_BED));
            register(BlockMapping.of("minecraft:pink_carpet", ChunkerVanillaBlockType.PINK_CARPET));
            register(BlockMapping.of("minecraft:pink_concrete", ChunkerVanillaBlockType.PINK_CONCRETE));
            register(BlockMapping.of("minecraft:pink_concrete_powder", ChunkerVanillaBlockType.PINK_CONCRETE_POWDER));
            register(BlockMapping.of("minecraft:pink_stained_glass", ChunkerVanillaBlockType.PINK_STAINED_GLASS));
            register(BlockMapping.of("minecraft:pink_stained_glass_pane", ChunkerVanillaBlockType.PINK_STAINED_GLASS_PANE));
            register(BlockMapping.of("minecraft:pink_terracotta", ChunkerVanillaBlockType.PINK_TERRACOTTA));
            register(BlockMapping.of("minecraft:pink_tulip", ChunkerVanillaBlockType.PINK_TULIP));
            register(BlockMapping.of("minecraft:pink_wall_banner", ChunkerVanillaBlockType.PINK_WALL_BANNER));
            register(BlockMapping.of("minecraft:player_head", ChunkerVanillaBlockType.PLAYER_HEAD));
            register(BlockMapping.of("minecraft:player_wall_head", ChunkerVanillaBlockType.PLAYER_WALL_HEAD));
            register(BlockMapping.of("minecraft:podzol", ChunkerVanillaBlockType.PODZOL));
            register(BlockMapping.of("minecraft:polished_andesite", ChunkerVanillaBlockType.POLISHED_ANDESITE));
            register(BlockMapping.of("minecraft:polished_andesite_slab", ChunkerVanillaBlockType.POLISHED_ANDESITE_SLAB));
            register(BlockMapping.of("minecraft:polished_andesite_stairs", ChunkerVanillaBlockType.POLISHED_ANDESITE_STAIRS));
            register(BlockMapping.of("minecraft:polished_diorite", ChunkerVanillaBlockType.POLISHED_DIORITE));
            register(BlockMapping.of("minecraft:polished_diorite_slab", ChunkerVanillaBlockType.POLISHED_DIORITE_SLAB));
            register(BlockMapping.of("minecraft:polished_diorite_stairs", ChunkerVanillaBlockType.POLISHED_DIORITE_STAIRS));
            register(BlockMapping.of("minecraft:polished_granite", ChunkerVanillaBlockType.POLISHED_GRANITE));
            register(BlockMapping.of("minecraft:polished_granite_slab", ChunkerVanillaBlockType.POLISHED_GRANITE_SLAB));
            register(BlockMapping.of("minecraft:polished_granite_stairs", ChunkerVanillaBlockType.POLISHED_GRANITE_STAIRS));
            register(BlockMapping.of("minecraft:poppy", ChunkerVanillaBlockType.POPPY));
            register(BlockMapping.of("minecraft:potted_acacia_sapling", ChunkerVanillaBlockType.POTTED_ACACIA_SAPLING));
            register(BlockMapping.of("minecraft:potted_allium", ChunkerVanillaBlockType.POTTED_ALLIUM));
            register(BlockMapping.of("minecraft:potted_azure_bluet", ChunkerVanillaBlockType.POTTED_AZURE_BLUET));
            register(BlockMapping.of("minecraft:potted_bamboo", ChunkerVanillaBlockType.POTTED_BAMBOO));
            register(BlockMapping.of("minecraft:potted_birch_sapling", ChunkerVanillaBlockType.POTTED_BIRCH_SAPLING));
            register(BlockMapping.of("minecraft:potted_blue_orchid", ChunkerVanillaBlockType.POTTED_BLUE_ORCHID));
            register(BlockMapping.of("minecraft:potted_brown_mushroom", ChunkerVanillaBlockType.POTTED_BROWN_MUSHROOM));
            register(BlockMapping.of("minecraft:potted_cactus", ChunkerVanillaBlockType.POTTED_CACTUS));
            register(BlockMapping.of("minecraft:potted_cornflower", ChunkerVanillaBlockType.POTTED_CORNFLOWER));
            register(BlockMapping.of("minecraft:potted_crimson_fungus", ChunkerVanillaBlockType.POTTED_CRIMSON_FUNGUS));
            register(BlockMapping.of("minecraft:potted_crimson_roots", ChunkerVanillaBlockType.POTTED_CRIMSON_ROOTS));
            register(BlockMapping.of("minecraft:potted_dandelion", ChunkerVanillaBlockType.POTTED_DANDELION));
            register(BlockMapping.of("minecraft:potted_dark_oak_sapling", ChunkerVanillaBlockType.POTTED_DARK_OAK_SAPLING));
            register(BlockMapping.of("minecraft:potted_dead_bush", ChunkerVanillaBlockType.POTTED_DEAD_BUSH));
            register(BlockMapping.of("minecraft:potted_fern", ChunkerVanillaBlockType.POTTED_FERN));
            register(BlockMapping.of("minecraft:potted_jungle_sapling", ChunkerVanillaBlockType.POTTED_JUNGLE_SAPLING));
            register(BlockMapping.of("minecraft:potted_lily_of_the_valley", ChunkerVanillaBlockType.POTTED_LILY_OF_THE_VALLEY));
            register(BlockMapping.of("minecraft:potted_oak_sapling", ChunkerVanillaBlockType.POTTED_OAK_SAPLING));
            register(BlockMapping.of("minecraft:potted_orange_tulip", ChunkerVanillaBlockType.POTTED_ORANGE_TULIP));
            register(BlockMapping.of("minecraft:potted_oxeye_daisy", ChunkerVanillaBlockType.POTTED_OXEYE_DAISY));
            register(BlockMapping.of("minecraft:potted_pink_tulip", ChunkerVanillaBlockType.POTTED_PINK_TULIP));
            register(BlockMapping.of("minecraft:potted_poppy", ChunkerVanillaBlockType.POTTED_POPPY));
            register(BlockMapping.of("minecraft:potted_red_mushroom", ChunkerVanillaBlockType.POTTED_RED_MUSHROOM));
            register(BlockMapping.of("minecraft:potted_red_tulip", ChunkerVanillaBlockType.POTTED_RED_TULIP));
            register(BlockMapping.of("minecraft:potted_spruce_sapling", ChunkerVanillaBlockType.POTTED_SPRUCE_SAPLING));
            register(BlockMapping.of("minecraft:potted_warped_fungus", ChunkerVanillaBlockType.POTTED_WARPED_FUNGUS));
            register(BlockMapping.of("minecraft:potted_warped_roots", ChunkerVanillaBlockType.POTTED_WARPED_ROOTS));
            register(BlockMapping.of("minecraft:potted_white_tulip", ChunkerVanillaBlockType.POTTED_WHITE_TULIP));
            register(BlockMapping.of("minecraft:potted_wither_rose", ChunkerVanillaBlockType.POTTED_WITHER_ROSE));
            register(BlockMapping.of("minecraft:powered_rail", ChunkerVanillaBlockType.POWERED_RAIL));
            register(BlockMapping.of("minecraft:prismarine_brick_slab", ChunkerVanillaBlockType.PRISMARINE_BRICK_SLAB));
            register(BlockMapping.of("minecraft:prismarine_brick_stairs", ChunkerVanillaBlockType.PRISMARINE_BRICK_STAIRS));
            register(BlockMapping.of("minecraft:prismarine_bricks", ChunkerVanillaBlockType.PRISMARINE_BRICKS));
            register(BlockMapping.of("minecraft:prismarine_slab", ChunkerVanillaBlockType.PRISMARINE_SLAB));
            register(BlockMapping.of("minecraft:prismarine_stairs", ChunkerVanillaBlockType.PRISMARINE_STAIRS));
            register(BlockMapping.of("minecraft:prismarine_wall", ChunkerVanillaBlockType.PRISMARINE_WALL));
            register(BlockMapping.of("minecraft:purple_banner", ChunkerVanillaBlockType.PURPLE_BANNER));
            register(BlockMapping.of("minecraft:purple_bed", ChunkerVanillaBlockType.PURPLE_BED));
            register(BlockMapping.of("minecraft:purple_carpet", ChunkerVanillaBlockType.PURPLE_CARPET));
            register(BlockMapping.of("minecraft:purple_concrete", ChunkerVanillaBlockType.PURPLE_CONCRETE));
            register(BlockMapping.of("minecraft:purple_concrete_powder", ChunkerVanillaBlockType.PURPLE_CONCRETE_POWDER));
            register(BlockMapping.of("minecraft:purple_stained_glass", ChunkerVanillaBlockType.PURPLE_STAINED_GLASS));
            register(BlockMapping.of("minecraft:purple_stained_glass_pane", ChunkerVanillaBlockType.PURPLE_STAINED_GLASS_PANE));
            register(BlockMapping.of("minecraft:purple_terracotta", ChunkerVanillaBlockType.PURPLE_TERRACOTTA));
            register(BlockMapping.of("minecraft:purple_wall_banner", ChunkerVanillaBlockType.PURPLE_WALL_BANNER));
            register(BlockMapping.of("minecraft:quartz_bricks", ChunkerVanillaBlockType.QUARTZ_BRICKS));
            register(BlockMapping.of("minecraft:quartz_pillar", ChunkerVanillaBlockType.QUARTZ_PILLAR));
            register(BlockMapping.of("minecraft:quartz_slab", ChunkerVanillaBlockType.QUARTZ_SLAB));
            register(BlockMapping.of("minecraft:red_banner", ChunkerVanillaBlockType.RED_BANNER));
            register(BlockMapping.of("minecraft:red_bed", ChunkerVanillaBlockType.RED_BED));
            register(BlockMapping.of("minecraft:red_carpet", ChunkerVanillaBlockType.RED_CARPET));
            register(BlockMapping.of("minecraft:red_concrete", ChunkerVanillaBlockType.RED_CONCRETE));
            register(BlockMapping.of("minecraft:red_concrete_powder", ChunkerVanillaBlockType.RED_CONCRETE_POWDER));
            register(BlockMapping.of("minecraft:red_nether_brick_slab", ChunkerVanillaBlockType.RED_NETHER_BRICK_SLAB));
            register(BlockMapping.of("minecraft:red_nether_brick_stairs", ChunkerVanillaBlockType.RED_NETHER_BRICK_STAIRS));
            register(BlockMapping.of("minecraft:red_nether_brick_wall", ChunkerVanillaBlockType.RED_NETHER_BRICK_WALL));
            register(BlockMapping.of("minecraft:red_nether_bricks", ChunkerVanillaBlockType.RED_NETHER_BRICKS));
            register(BlockMapping.of("minecraft:red_sand", ChunkerVanillaBlockType.RED_SAND));
            register(BlockMapping.of("minecraft:red_sandstone_slab", ChunkerVanillaBlockType.RED_SANDSTONE_SLAB));
            register(BlockMapping.of("minecraft:red_sandstone_wall", ChunkerVanillaBlockType.RED_SANDSTONE_WALL));
            register(BlockMapping.of("minecraft:red_stained_glass", ChunkerVanillaBlockType.RED_STAINED_GLASS));
            register(BlockMapping.of("minecraft:red_stained_glass_pane", ChunkerVanillaBlockType.RED_STAINED_GLASS_PANE));
            register(BlockMapping.of("minecraft:red_terracotta", ChunkerVanillaBlockType.RED_TERRACOTTA));
            register(BlockMapping.of("minecraft:red_tulip", ChunkerVanillaBlockType.RED_TULIP));
            register(BlockMapping.of("minecraft:red_wall_banner", ChunkerVanillaBlockType.RED_WALL_BANNER));
            register(BlockMapping.of("minecraft:redstone_wall_torch", ChunkerVanillaBlockType.REDSTONE_WALL_TORCH));
            register(BlockMapping.of("minecraft:repeater", ChunkerVanillaBlockType.REPEATER));
            register(BlockMapping.of("minecraft:rose_bush", ChunkerVanillaBlockType.ROSE_BUSH));
            register(BlockMapping.of("minecraft:sandstone_slab", ChunkerVanillaBlockType.SANDSTONE_SLAB));
            register(BlockMapping.of("minecraft:sandstone_wall", ChunkerVanillaBlockType.SANDSTONE_WALL));
            register(BlockMapping.of("minecraft:scaffolding", ChunkerVanillaBlockType.SCAFFOLDING));
            register(BlockMapping.of("minecraft:sea_pickle", ChunkerVanillaBlockType.SEA_PICKLE));
            register(BlockMapping.of("minecraft:seagrass", ChunkerVanillaBlockType.SEAGRASS));
            register(BlockMapping.of("minecraft:shulker_box", ChunkerVanillaBlockType.SHULKER_BOX));
            register(BlockMapping.of("minecraft:skeleton_skull", ChunkerVanillaBlockType.SKELETON_SKULL));
            register(BlockMapping.of("minecraft:skeleton_wall_skull", ChunkerVanillaBlockType.SKELETON_WALL_SKULL));
            register(BlockMapping.of("minecraft:slime_block", ChunkerVanillaBlockType.SLIME_BLOCK));
            register(BlockMapping.of("minecraft:smithing_table", ChunkerVanillaBlockType.SMITHING_TABLE));
            register(BlockMapping.of("minecraft:smoker", ChunkerVanillaBlockType.SMOKER));
            register(BlockMapping.of("minecraft:smooth_quartz", ChunkerVanillaBlockType.SMOOTH_QUARTZ));
            register(BlockMapping.of("minecraft:smooth_quartz_slab", ChunkerVanillaBlockType.SMOOTH_QUARTZ_SLAB));
            register(BlockMapping.of("minecraft:smooth_quartz_stairs", ChunkerVanillaBlockType.SMOOTH_QUARTZ_STAIRS));
            register(BlockMapping.of("minecraft:smooth_red_sandstone", ChunkerVanillaBlockType.SMOOTH_RED_SANDSTONE));
            register(BlockMapping.of("minecraft:smooth_red_sandstone_slab", ChunkerVanillaBlockType.SMOOTH_RED_SANDSTONE_SLAB));
            register(BlockMapping.of("minecraft:smooth_red_sandstone_stairs", ChunkerVanillaBlockType.SMOOTH_RED_SANDSTONE_STAIRS));
            register(BlockMapping.of("minecraft:smooth_sandstone", ChunkerVanillaBlockType.SMOOTH_SANDSTONE));
            register(BlockMapping.of("minecraft:smooth_sandstone_slab", ChunkerVanillaBlockType.SMOOTH_SANDSTONE_SLAB));
            register(BlockMapping.of("minecraft:smooth_sandstone_stairs", ChunkerVanillaBlockType.SMOOTH_SANDSTONE_STAIRS));
            register(BlockMapping.of("minecraft:smooth_stone", ChunkerVanillaBlockType.SMOOTH_STONE));
            register(BlockMapping.of("minecraft:smooth_stone_slab", ChunkerVanillaBlockType.SMOOTH_STONE_SLAB));
            register(BlockMapping.of("minecraft:snow_block", ChunkerVanillaBlockType.SNOW_BLOCK));
            register(BlockMapping.of("minecraft:spawner", ChunkerVanillaBlockType.SPAWNER));
            register(BlockMapping.of("minecraft:spruce_button", ChunkerVanillaBlockType.SPRUCE_BUTTON));
            register(BlockMapping.of("minecraft:spruce_leaves", ChunkerVanillaBlockType.SPRUCE_LEAVES));
            register(BlockMapping.of("minecraft:spruce_log", ChunkerVanillaBlockType.SPRUCE_LOG));
            register(BlockMapping.of("minecraft:spruce_planks", ChunkerVanillaBlockType.SPRUCE_PLANKS));
            register(BlockMapping.of("minecraft:spruce_pressure_plate", ChunkerVanillaBlockType.SPRUCE_PRESSURE_PLATE));
            register(BlockMapping.of("minecraft:spruce_sapling", ChunkerVanillaBlockType.SPRUCE_SAPLING));
            register(BlockMapping.of("minecraft:spruce_sign", ChunkerVanillaBlockType.SPRUCE_SIGN));
            register(BlockMapping.of("minecraft:spruce_slab", ChunkerVanillaBlockType.SPRUCE_SLAB));
            register(BlockMapping.of("minecraft:spruce_trapdoor", ChunkerVanillaBlockType.SPRUCE_TRAPDOOR));
            register(BlockMapping.of("minecraft:spruce_wall_sign", ChunkerVanillaBlockType.SPRUCE_WALL_SIGN));
            register(BlockMapping.of("minecraft:spruce_wood", ChunkerVanillaBlockType.SPRUCE_WOOD));
            register(BlockMapping.of("minecraft:stone_brick_slab", ChunkerVanillaBlockType.STONE_BRICK_SLAB));
            register(BlockMapping.of("minecraft:stone_brick_wall", ChunkerVanillaBlockType.STONE_BRICK_WALL));
            register(BlockMapping.of("minecraft:stone_bricks", ChunkerVanillaBlockType.STONE_BRICKS));
            register(BlockMapping.of("minecraft:stonecutter", ChunkerVanillaBlockType.STONECUTTER));
            register(BlockMapping.of("minecraft:stripped_acacia_log", ChunkerVanillaBlockType.STRIPPED_ACACIA_LOG));
            register(BlockMapping.of("minecraft:stripped_acacia_wood", ChunkerVanillaBlockType.STRIPPED_ACACIA_WOOD));
            register(BlockMapping.of("minecraft:stripped_birch_log", ChunkerVanillaBlockType.STRIPPED_BIRCH_LOG));
            register(BlockMapping.of("minecraft:stripped_birch_wood", ChunkerVanillaBlockType.STRIPPED_BIRCH_WOOD));
            register(BlockMapping.of("minecraft:stripped_dark_oak_log", ChunkerVanillaBlockType.STRIPPED_DARK_OAK_LOG));
            register(BlockMapping.of("minecraft:stripped_dark_oak_wood", ChunkerVanillaBlockType.STRIPPED_DARK_OAK_WOOD));
            register(BlockMapping.of("minecraft:stripped_jungle_log", ChunkerVanillaBlockType.STRIPPED_JUNGLE_LOG));
            register(BlockMapping.of("minecraft:stripped_jungle_wood", ChunkerVanillaBlockType.STRIPPED_JUNGLE_WOOD));
            register(BlockMapping.of("minecraft:stripped_oak_log", ChunkerVanillaBlockType.STRIPPED_OAK_LOG));
            register(BlockMapping.of("minecraft:stripped_oak_wood", ChunkerVanillaBlockType.STRIPPED_OAK_WOOD));
            register(BlockMapping.of("minecraft:stripped_spruce_log", ChunkerVanillaBlockType.STRIPPED_SPRUCE_LOG));
            register(BlockMapping.of("minecraft:stripped_spruce_wood", ChunkerVanillaBlockType.STRIPPED_SPRUCE_WOOD));
            register(BlockMapping.of("minecraft:sugar_cane", ChunkerVanillaBlockType.SUGAR_CANE));
            register(BlockMapping.of("minecraft:sunflower", ChunkerVanillaBlockType.SUNFLOWER));
            register(BlockMapping.of("minecraft:sweet_berry_bush", ChunkerVanillaBlockType.SWEET_BERRY_BUSH));
            register(BlockMapping.of("minecraft:tall_grass", ChunkerVanillaBlockType.TALL_GRASS));
            register(BlockMapping.of("minecraft:tall_seagrass", ChunkerVanillaBlockType.TALL_SEAGRASS));
            register(BlockMapping.of("minecraft:terracotta", ChunkerVanillaBlockType.TERRACOTTA));
            register(BlockMapping.of("minecraft:tube_coral", ChunkerVanillaBlockType.TUBE_CORAL));
            register(BlockMapping.of("minecraft:tube_coral_block", ChunkerVanillaBlockType.TUBE_CORAL_BLOCK));
            register(BlockMapping.of("minecraft:tube_coral_fan", ChunkerVanillaBlockType.TUBE_CORAL_FAN));
            register(BlockMapping.of("minecraft:tube_coral_wall_fan", ChunkerVanillaBlockType.TUBE_CORAL_WALL_FAN));
            register(BlockMapping.of("minecraft:turtle_egg", ChunkerVanillaBlockType.TURTLE_EGG));
            register(BlockMapping.of("minecraft:void_air", ChunkerVanillaBlockType.VOID_AIR));
            register(BlockMapping.of("minecraft:wall_torch", ChunkerVanillaBlockType.WALL_TORCH));
            register(BlockMapping.of("minecraft:wet_sponge", ChunkerVanillaBlockType.WET_SPONGE));
            register(BlockMapping.of("minecraft:white_banner", ChunkerVanillaBlockType.WHITE_BANNER));
            register(BlockMapping.of("minecraft:white_bed", ChunkerVanillaBlockType.WHITE_BED));
            register(BlockMapping.of("minecraft:white_carpet", ChunkerVanillaBlockType.WHITE_CARPET));
            register(BlockMapping.of("minecraft:white_concrete", ChunkerVanillaBlockType.WHITE_CONCRETE));
            register(BlockMapping.of("minecraft:white_concrete_powder", ChunkerVanillaBlockType.WHITE_CONCRETE_POWDER));
            register(BlockMapping.of("minecraft:white_stained_glass", ChunkerVanillaBlockType.WHITE_STAINED_GLASS));
            register(BlockMapping.of("minecraft:white_stained_glass_pane", ChunkerVanillaBlockType.WHITE_STAINED_GLASS_PANE));
            register(BlockMapping.of("minecraft:white_terracotta", ChunkerVanillaBlockType.WHITE_TERRACOTTA));
            register(BlockMapping.of("minecraft:white_tulip", ChunkerVanillaBlockType.WHITE_TULIP));
            register(BlockMapping.of("minecraft:white_wall_banner", ChunkerVanillaBlockType.WHITE_WALL_BANNER));
            register(BlockMapping.of("minecraft:wither_rose", ChunkerVanillaBlockType.WITHER_ROSE));
            register(BlockMapping.of("minecraft:wither_skeleton_skull", ChunkerVanillaBlockType.WITHER_SKELETON_SKULL));
            register(BlockMapping.of("minecraft:wither_skeleton_wall_skull", ChunkerVanillaBlockType.WITHER_SKELETON_WALL_SKULL));
            register(BlockMapping.of("minecraft:yellow_banner", ChunkerVanillaBlockType.YELLOW_BANNER));
            register(BlockMapping.of("minecraft:yellow_bed", ChunkerVanillaBlockType.YELLOW_BED));
            register(BlockMapping.of("minecraft:yellow_carpet", ChunkerVanillaBlockType.YELLOW_CARPET));
            register(BlockMapping.of("minecraft:yellow_concrete", ChunkerVanillaBlockType.YELLOW_CONCRETE));
            register(BlockMapping.of("minecraft:yellow_concrete_powder", ChunkerVanillaBlockType.YELLOW_CONCRETE_POWDER));
            register(BlockMapping.of("minecraft:yellow_stained_glass", ChunkerVanillaBlockType.YELLOW_STAINED_GLASS));
            register(BlockMapping.of("minecraft:yellow_stained_glass_pane", ChunkerVanillaBlockType.YELLOW_STAINED_GLASS_PANE));
            register(BlockMapping.of("minecraft:yellow_terracotta", ChunkerVanillaBlockType.YELLOW_TERRACOTTA));
            register(BlockMapping.of("minecraft:yellow_wall_banner", ChunkerVanillaBlockType.YELLOW_WALL_BANNER));
            register(BlockMapping.of("minecraft:zombie_head", ChunkerVanillaBlockType.ZOMBIE_HEAD));
            register(BlockMapping.of("minecraft:zombie_wall_head", ChunkerVanillaBlockType.ZOMBIE_WALL_HEAD));
        }

        // 1.16
        if (version.isGreaterThanOrEqual(1, 16, 0)) {
            register(BlockMapping.of("minecraft:ancient_debris", ChunkerVanillaBlockType.ANCIENT_DEBRIS));
            register(BlockMapping.of("minecraft:netherite_block", ChunkerVanillaBlockType.NETHERITE_BLOCK));
            register(BlockMapping.of("minecraft:crying_obsidian", ChunkerVanillaBlockType.CRYING_OBSIDIAN));
            register(BlockMapping.of("minecraft:respawn_anchor", ChunkerVanillaBlockType.RESPAWN_ANCHOR, JavaLegacyStateGroups.RESPAWN_ANCHOR));
            register(BlockMapping.of("minecraft:lodestone", ChunkerVanillaBlockType.LODESTONE));
            register(BlockMapping.of("minecraft:blackstone", ChunkerVanillaBlockType.BLACKSTONE));
            register(BlockMapping.of("minecraft:gilded_blackstone", ChunkerVanillaBlockType.GILDED_BLACKSTONE));
            register(BlockMapping.of("minecraft:polished_blackstone", ChunkerVanillaBlockType.POLISHED_BLACKSTONE));
            register(BlockMapping.of("minecraft:polished_blackstone_bricks", ChunkerVanillaBlockType.POLISHED_BLACKSTONE_BRICKS));
            register(BlockMapping.of("minecraft:cracked_polished_blackstone_bricks", ChunkerVanillaBlockType.CRACKED_POLISHED_BLACKSTONE_BRICKS));
            register(BlockMapping.of("minecraft:chiseled_polished_blackstone", ChunkerVanillaBlockType.CHISELED_POLISHED_BLACKSTONE));
            register(BlockMapping.of("minecraft:chiseled_nether_bricks", ChunkerVanillaBlockType.CHISELED_NETHER_BRICKS));
            register(BlockMapping.of("minecraft:cracked_nether_bricks", ChunkerVanillaBlockType.CRACKED_NETHER_BRICKS));
            register(BlockMapping.of("minecraft:shroomlight", ChunkerVanillaBlockType.SHROOMLIGHT));
            register(BlockMapping.of("minecraft:soul_soil", ChunkerVanillaBlockType.SOUL_SOIL));
            register(BlockMapping.of("minecraft:basalt", ChunkerVanillaBlockType.BASALT));
            register(BlockMapping.of("minecraft:polished_basalt", ChunkerVanillaBlockType.POLISHED_BASALT));
            register(BlockMapping.of("minecraft:target", ChunkerVanillaBlockType.TARGET, JavaLegacyStateGroups.POWER));
            register(BlockMapping.of("minecraft:warped_nylium", ChunkerVanillaBlockType.WARPED_NYLIUM));
            register(BlockMapping.of("minecraft:crimson_nylium", ChunkerVanillaBlockType.CRIMSON_NYLIUM));
            register(BlockMapping.of("minecraft:nether_gold_ore", ChunkerVanillaBlockType.NETHER_GOLD_ORE));
            register(BlockMapping.of("minecraft:nether_sprouts", ChunkerVanillaBlockType.NETHER_SPROUTS));
            register(BlockMapping.of("minecraft:soul_fire", ChunkerVanillaBlockType.SOUL_FIRE, JavaLegacyStateGroups.SOUL_FIRE));
            register(BlockMapping.of("minecraft:soul_torch", ChunkerVanillaBlockType.SOUL_TORCH));
            register(BlockMapping.of("minecraft:soul_wall_torch", ChunkerVanillaBlockType.SOUL_WALL_TORCH, JavaLegacyStateGroups.FACING_HORIZONTAL));
            register(BlockMapping.of("minecraft:soul_campfire", ChunkerVanillaBlockType.SOUL_CAMPFIRE, JavaLegacyStateGroups.CAMPFIRE));
            register(BlockMapping.of("minecraft:soul_lantern", ChunkerVanillaBlockType.SOUL_LANTERN, JavaLegacyStateGroups.LANTERN));
            register(BlockMapping.of("minecraft:crimson_planks", ChunkerVanillaBlockType.CRIMSON_PLANKS));
            register(BlockMapping.of("minecraft:warped_planks", ChunkerVanillaBlockType.WARPED_PLANKS));
            register(BlockMapping.of("minecraft:crimson_stem", ChunkerVanillaBlockType.CRIMSON_STEM, JavaLegacyStateGroups.AXIS));
            register(BlockMapping.of("minecraft:warped_stem", ChunkerVanillaBlockType.WARPED_STEM, JavaLegacyStateGroups.AXIS));
            register(BlockMapping.of("minecraft:crimson_hyphae", ChunkerVanillaBlockType.CRIMSON_HYPHAE, JavaLegacyStateGroups.AXIS));
            register(BlockMapping.of("minecraft:warped_hyphae", ChunkerVanillaBlockType.WARPED_HYPHAE, JavaLegacyStateGroups.AXIS));
            register(BlockMapping.of("minecraft:stripped_crimson_stem", ChunkerVanillaBlockType.STRIPPED_CRIMSON_STEM, JavaLegacyStateGroups.AXIS));
            register(BlockMapping.of("minecraft:stripped_warped_stem", ChunkerVanillaBlockType.STRIPPED_WARPED_STEM, JavaLegacyStateGroups.AXIS));
            register(BlockMapping.of("minecraft:stripped_crimson_hyphae", ChunkerVanillaBlockType.STRIPPED_CRIMSON_HYPHAE, JavaLegacyStateGroups.AXIS));
            register(BlockMapping.of("minecraft:stripped_warped_hyphae", ChunkerVanillaBlockType.STRIPPED_WARPED_HYPHAE, JavaLegacyStateGroups.AXIS));
            register(BlockMapping.of("minecraft:warped_wart_block", ChunkerVanillaBlockType.WARPED_WART_BLOCK));
            register(BlockMapping.of("minecraft:crimson_fungus", ChunkerVanillaBlockType.CRIMSON_FUNGUS));
            register(BlockMapping.of("minecraft:warped_fungus", ChunkerVanillaBlockType.WARPED_FUNGUS));
            register(BlockMapping.of("minecraft:crimson_roots", ChunkerVanillaBlockType.CRIMSON_ROOTS));
            register(BlockMapping.of("minecraft:warped_roots", ChunkerVanillaBlockType.WARPED_ROOTS));
            register(BlockMapping.of("minecraft:twisting_vines", ChunkerVanillaBlockType.TWISTING_VINES, JavaLegacyStateGroups.AGE_25));
            register(BlockMapping.of("minecraft:twisting_vines_plant", ChunkerVanillaBlockType.TWISTING_VINES_PLANT));
            register(BlockMapping.of("minecraft:weeping_vines", ChunkerVanillaBlockType.WEEPING_VINES, JavaLegacyStateGroups.AGE_25));
            register(BlockMapping.of("minecraft:weeping_vines_plant", ChunkerVanillaBlockType.WEEPING_VINES_PLANT));
            register(BlockMapping.of("minecraft:crimson_fence", ChunkerVanillaBlockType.CRIMSON_FENCE, JavaLegacyStateGroups.CONNECTABLE_HORIZONTAL));
            register(BlockMapping.of("minecraft:warped_fence", ChunkerVanillaBlockType.WARPED_FENCE, JavaLegacyStateGroups.CONNECTABLE_HORIZONTAL));
            register(BlockMapping.of("minecraft:crimson_fence_gate", ChunkerVanillaBlockType.CRIMSON_FENCE_GATE, JavaLegacyStateGroups.FENCE_GATE));
            register(BlockMapping.of("minecraft:warped_fence_gate", ChunkerVanillaBlockType.WARPED_FENCE_GATE, JavaLegacyStateGroups.FENCE_GATE));
            register(BlockMapping.of("minecraft:crimson_pressure_plate", ChunkerVanillaBlockType.CRIMSON_PRESSURE_PLATE, JavaLegacyStateGroups.POWERED));
            register(BlockMapping.of("minecraft:warped_pressure_plate", ChunkerVanillaBlockType.WARPED_PRESSURE_PLATE, JavaLegacyStateGroups.POWERED));
            register(BlockMapping.of("minecraft:polished_blackstone_pressure_plate", ChunkerVanillaBlockType.POLISHED_BLACKSTONE_PRESSURE_PLATE, JavaLegacyStateGroups.POWERED));
            register(BlockMapping.of("minecraft:crimson_button", ChunkerVanillaBlockType.CRIMSON_BUTTON, JavaLegacyStateGroups.BUTTON));
            register(BlockMapping.of("minecraft:warped_button", ChunkerVanillaBlockType.WARPED_BUTTON, JavaLegacyStateGroups.BUTTON));
            register(BlockMapping.of("minecraft:polished_blackstone_button", ChunkerVanillaBlockType.POLISHED_BLACKSTONE_BUTTON, JavaLegacyStateGroups.BUTTON));
            register(BlockMapping.of("minecraft:crimson_door", ChunkerVanillaBlockType.CRIMSON_DOOR, JavaLegacyStateGroups.DOOR));
            register(BlockMapping.of("minecraft:warped_door", ChunkerVanillaBlockType.WARPED_DOOR, JavaLegacyStateGroups.DOOR));
            register(BlockMapping.of("minecraft:crimson_trapdoor", ChunkerVanillaBlockType.CRIMSON_TRAPDOOR, JavaLegacyStateGroups.TRAPDOOR));
            register(BlockMapping.of("minecraft:warped_trapdoor", ChunkerVanillaBlockType.WARPED_TRAPDOOR, JavaLegacyStateGroups.TRAPDOOR));
            register(BlockMapping.of("minecraft:blackstone_slab", ChunkerVanillaBlockType.BLACKSTONE_SLAB, JavaLegacyStateGroups.SLAB));
            register(BlockMapping.of("minecraft:blackstone_stairs", ChunkerVanillaBlockType.BLACKSTONE_STAIRS, JavaLegacyStateGroups.STAIRS));
            register(BlockMapping.of("minecraft:blackstone_wall", ChunkerVanillaBlockType.BLACKSTONE_WALL, JavaLegacyStateGroups.WALL));
            register(BlockMapping.of("minecraft:polished_blackstone_slab", ChunkerVanillaBlockType.POLISHED_BLACKSTONE_SLAB, JavaLegacyStateGroups.SLAB));
            register(BlockMapping.of("minecraft:polished_blackstone_stairs", ChunkerVanillaBlockType.POLISHED_BLACKSTONE_STAIRS, JavaLegacyStateGroups.STAIRS));
            register(BlockMapping.of("minecraft:polished_blackstone_wall", ChunkerVanillaBlockType.POLISHED_BLACKSTONE_WALL, JavaLegacyStateGroups.WALL));
            register(BlockMapping.of("minecraft:polished_blackstone_brick_slab", ChunkerVanillaBlockType.POLISHED_BLACKSTONE_BRICK_SLAB, JavaLegacyStateGroups.SLAB));
            register(BlockMapping.of("minecraft:polished_blackstone_brick_stairs", ChunkerVanillaBlockType.POLISHED_BLACKSTONE_BRICK_STAIRS, JavaLegacyStateGroups.STAIRS));
            register(BlockMapping.of("minecraft:polished_blackstone_brick_wall", ChunkerVanillaBlockType.POLISHED_BLACKSTONE_BRICK_WALL, JavaLegacyStateGroups.WALL));
            register(BlockMapping.of("minecraft:crimson_stairs", ChunkerVanillaBlockType.CRIMSON_STAIRS, JavaLegacyStateGroups.STAIRS));
            register(BlockMapping.of("minecraft:warped_stairs", ChunkerVanillaBlockType.WARPED_STAIRS, JavaLegacyStateGroups.STAIRS));
            register(BlockMapping.of("minecraft:crimson_slab", ChunkerVanillaBlockType.CRIMSON_SLAB, JavaLegacyStateGroups.SLAB));
            register(BlockMapping.of("minecraft:warped_slab", ChunkerVanillaBlockType.WARPED_SLAB, JavaLegacyStateGroups.SLAB));
            register(BlockMapping.of("minecraft:crimson_sign", ChunkerVanillaBlockType.CRIMSON_SIGN, JavaLegacyStateGroups.SIGN));
            register(BlockMapping.of("minecraft:warped_sign", ChunkerVanillaBlockType.WARPED_SIGN, JavaLegacyStateGroups.SIGN));
            register(BlockMapping.of("minecraft:crimson_wall_sign", ChunkerVanillaBlockType.CRIMSON_WALL_SIGN));
            register(BlockMapping.of("minecraft:warped_wall_sign", ChunkerVanillaBlockType.WARPED_WALL_SIGN));
        }

        // Manual mappings above cover all modern blocks.
    }
}
