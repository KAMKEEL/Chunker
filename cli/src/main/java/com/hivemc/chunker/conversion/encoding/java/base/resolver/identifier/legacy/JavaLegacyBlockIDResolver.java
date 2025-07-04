package com.hivemc.chunker.conversion.encoding.java.base.resolver.identifier.legacy;

import com.hivemc.chunker.conversion.encoding.base.Version;
import com.hivemc.chunker.resolver.Resolver;
import com.hivemc.chunker.util.InvertibleMap;

import java.util.Optional;

/**
 * Resolver to convert between legacy block identifiers and the block ID.
 */
public class JavaLegacyBlockIDResolver implements Resolver<Integer, String> {
    /**
     * Placeholder ID used when a block identifier is missing from the mapping.
     * This value must fit within the 12-bit limit used by the legacy chunk
     * format. 306 was chosen so it does not collide with vanilla 1.12 IDs and
     * leaves room for additional mappings while remaining below the 4096 ID
     * ceiling.
     */
    // Use IDs outside the vanilla 1.12 range so they can be replaced later
    // when converting to a modded environment. 3050 is safely above the last
    // vanilla ID (2267) but still fits in the 12-bit limit (4095).
    public static final int PLACEHOLDER_START_ID = 3050;
    private final InvertibleMap<String, Integer> mapping = InvertibleMap.intValues();
    private final java.util.Map<String, Integer> placeholderIds = new java.util.HashMap<>();
    private int nextPlaceholder = PLACEHOLDER_START_ID;

    public int placeholder(String identifier) {
        return placeholderIds.computeIfAbsent(identifier, k -> nextPlaceholder++);
    }

    /**
     * Create a new legacy block ID resolver.
     *
     * @param version the java version.
     */
    public JavaLegacyBlockIDResolver(Version version) {
        mapping.put("minecraft:air", 0);
        mapping.put("minecraft:stone", 1);
        mapping.put("minecraft:grass", 2);
        mapping.put("minecraft:dirt", 3);
        mapping.put("minecraft:cobblestone", 4);
        mapping.put("minecraft:planks", 5);
        mapping.put("minecraft:sapling", 6);
        mapping.put("minecraft:bedrock", 7);
        mapping.put("minecraft:flowing_water", 8);
        mapping.put("minecraft:water", 9);
        mapping.put("minecraft:flowing_lava", 10);
        mapping.put("minecraft:lava", 11);
        mapping.put("minecraft:sand", 12);
        mapping.put("minecraft:gravel", 13);
        mapping.put("minecraft:gold_ore", 14);
        mapping.put("minecraft:iron_ore", 15);
        mapping.put("minecraft:coal_ore", 16);
        mapping.put("minecraft:log", 17);
        mapping.put("minecraft:leaves", 18);
        mapping.put("minecraft:sponge", 19);
        mapping.put("minecraft:glass", 20);
        mapping.put("minecraft:lapis_ore", 21);
        mapping.put("minecraft:lapis_block", 22);
        mapping.put("minecraft:dispenser", 23);
        mapping.put("minecraft:sandstone", 24);
        mapping.put("minecraft:noteblock", 25);
        mapping.put("minecraft:bed", 26);
        mapping.put("minecraft:golden_rail", 27);
        mapping.put("minecraft:detector_rail", 28);
        mapping.put("minecraft:sticky_piston", 29);
        mapping.put("minecraft:web", 30);
        mapping.put("minecraft:tallgrass", 31);
        mapping.put("minecraft:deadbush", 32);
        mapping.put("minecraft:piston", 33);
        mapping.put("minecraft:piston_head", 34);
        mapping.put("minecraft:wool", 35);
        mapping.put("minecraft:piston_extension", 36);
        mapping.put("minecraft:yellow_flower", 37);
        mapping.put("minecraft:red_flower", 38);
        mapping.put("minecraft:brown_mushroom", 39);
        mapping.put("minecraft:red_mushroom", 40);
        mapping.put("minecraft:gold_block", 41);
        mapping.put("minecraft:iron_block", 42);
        mapping.put("minecraft:double_stone_slab", 43);
        mapping.put("minecraft:stone_slab", 44);
        mapping.put("minecraft:brick_block", 45);
        mapping.put("minecraft:tnt", 46);
        mapping.put("minecraft:bookshelf", 47);
        mapping.put("minecraft:mossy_cobblestone", 48);
        mapping.put("minecraft:obsidian", 49);
        mapping.put("minecraft:torch", 50);
        mapping.put("minecraft:fire", 51);
        mapping.put("minecraft:mob_spawner", 52);
        mapping.put("minecraft:oak_stairs", 53);
        mapping.put("minecraft:chest", 54);
        mapping.put("minecraft:redstone_wire", 55);
        mapping.put("minecraft:diamond_ore", 56);
        mapping.put("minecraft:diamond_block", 57);
        mapping.put("minecraft:crafting_table", 58);
        mapping.put("minecraft:wheat", 59);
        mapping.put("minecraft:farmland", 60);
        mapping.put("minecraft:furnace", 61);
        mapping.put("minecraft:lit_furnace", 62);
        mapping.put("minecraft:standing_sign", 63);
        mapping.put("minecraft:wooden_door", 64);
        mapping.put("minecraft:ladder", 65);
        mapping.put("minecraft:rail", 66);
        mapping.put("minecraft:stone_stairs", 67);
        mapping.put("minecraft:wall_sign", 68);
        mapping.put("minecraft:lever", 69);
        mapping.put("minecraft:stone_pressure_plate", 70);
        mapping.put("minecraft:iron_door", 71);
        mapping.put("minecraft:wooden_pressure_plate", 72);
        mapping.put("minecraft:redstone_ore", 73);
        mapping.put("minecraft:lit_redstone_ore", 74);
        mapping.put("minecraft:unlit_redstone_torch", 75);
        mapping.put("minecraft:redstone_torch", 76);
        mapping.put("minecraft:stone_button", 77);
        mapping.put("minecraft:snow_layer", 78);
        mapping.put("minecraft:ice", 79);
        mapping.put("minecraft:snow", 80);
        mapping.put("minecraft:cactus", 81);
        mapping.put("minecraft:clay", 82);
        mapping.put("minecraft:reeds", 83);
        mapping.put("minecraft:jukebox", 84);
        mapping.put("minecraft:fence", 85);
        mapping.put("minecraft:pumpkin", 86);
        mapping.put("minecraft:netherrack", 87);
        mapping.put("minecraft:soul_sand", 88);
        mapping.put("minecraft:glowstone", 89);
        mapping.put("minecraft:portal", 90);
        mapping.put("minecraft:lit_pumpkin", 91);
        mapping.put("minecraft:cake", 92);
        mapping.put("minecraft:unpowered_repeater", 93);
        mapping.put("minecraft:powered_repeater", 94);
        mapping.put("minecraft:stained_glass", 95);
        mapping.put("minecraft:trapdoor", 96);
        mapping.put("minecraft:monster_egg", 97);
        mapping.put("minecraft:stonebrick", 98);
        mapping.put("minecraft:brown_mushroom_block", 99);
        mapping.put("minecraft:red_mushroom_block", 100);
        mapping.put("minecraft:iron_bars", 101);
        mapping.put("minecraft:glass_pane", 102);
        mapping.put("minecraft:melon_block", 103);
        mapping.put("minecraft:pumpkin_stem", 104);
        mapping.put("minecraft:melon_stem", 105);
        mapping.put("minecraft:vine", 106);
        mapping.put("minecraft:fence_gate", 107);
        mapping.put("minecraft:brick_stairs", 108);
        mapping.put("minecraft:stone_brick_stairs", 109);
        mapping.put("minecraft:mycelium", 110);
        mapping.put("minecraft:waterlily", 111);
        mapping.put("minecraft:nether_brick", 112);
        mapping.put("minecraft:nether_brick_fence", 113);
        mapping.put("minecraft:nether_brick_stairs", 114);
        mapping.put("minecraft:nether_wart", 115);
        mapping.put("minecraft:enchanting_table", 116);
        mapping.put("minecraft:brewing_stand", 117);
        mapping.put("minecraft:cauldron", 118);
        mapping.put("minecraft:end_portal", 119);
        mapping.put("minecraft:end_portal_frame", 120);
        mapping.put("minecraft:end_stone", 121);
        mapping.put("minecraft:dragon_egg", 122);
        mapping.put("minecraft:redstone_lamp", 123);
        mapping.put("minecraft:lit_redstone_lamp", 124);
        mapping.put("minecraft:double_wooden_slab", 125);
        mapping.put("minecraft:wooden_slab", 126);
        mapping.put("minecraft:cocoa", 127);
        mapping.put("minecraft:sandstone_stairs", 128);
        mapping.put("minecraft:emerald_ore", 129);
        mapping.put("minecraft:ender_chest", 130);
        mapping.put("minecraft:tripwire_hook", 131);
        mapping.put("minecraft:tripwire", 132);
        mapping.put("minecraft:emerald_block", 133);
        mapping.put("minecraft:spruce_stairs", 134);
        mapping.put("minecraft:birch_stairs", 135);
        mapping.put("minecraft:jungle_stairs", 136);
        mapping.put("minecraft:command_block", 137);
        mapping.put("minecraft:beacon", 138);
        mapping.put("minecraft:cobblestone_wall", 139);
        mapping.put("minecraft:flower_pot", 140);
        mapping.put("minecraft:carrots", 141);
        mapping.put("minecraft:potatoes", 142);
        mapping.put("minecraft:wooden_button", 143);
        mapping.put("minecraft:skull", 144);
        mapping.put("minecraft:anvil", 145);
        mapping.put("minecraft:trapped_chest", 146);
        mapping.put("minecraft:light_weighted_pressure_plate", 147);
        mapping.put("minecraft:heavy_weighted_pressure_plate", 148);
        mapping.put("minecraft:unpowered_comparator", 149);
        mapping.put("minecraft:powered_comparator", 150);
        mapping.put("minecraft:daylight_detector", 151);
        mapping.put("minecraft:redstone_block", 152);
        mapping.put("minecraft:quartz_ore", 153);
        mapping.put("minecraft:hopper", 154);
        mapping.put("minecraft:quartz_block", 155);
        mapping.put("minecraft:quartz_stairs", 156);
        mapping.put("minecraft:activator_rail", 157);
        mapping.put("minecraft:dropper", 158);
        mapping.put("minecraft:stained_hardened_clay", 159);
        mapping.put("minecraft:stained_glass_pane", 160);
        mapping.put("minecraft:leaves2", 161);
        mapping.put("minecraft:log2", 162);
        mapping.put("minecraft:acacia_stairs", 163);
        mapping.put("minecraft:dark_oak_stairs", 164);
        mapping.put("minecraft:slime", 165);
        mapping.put("minecraft:barrier", 166);
        mapping.put("minecraft:iron_trapdoor", 167);
        mapping.put("minecraft:prismarine", 168);
        mapping.put("minecraft:sea_lantern", 169);
        mapping.put("minecraft:hay_block", 170);
        mapping.put("minecraft:carpet", 171);
        mapping.put("minecraft:hardened_clay", 172);
        mapping.put("minecraft:coal_block", 173);
        mapping.put("minecraft:packed_ice", 174);
        mapping.put("minecraft:double_plant", 175);
        mapping.put("minecraft:standing_banner", 176);
        mapping.put("minecraft:wall_banner", 177);
        mapping.put("minecraft:daylight_detector_inverted", 178);
        mapping.put("minecraft:red_sandstone", 179);
        mapping.put("minecraft:red_sandstone_stairs", 180);
        mapping.put("minecraft:double_stone_slab2", 181);
        mapping.put("minecraft:stone_slab2", 182);
        mapping.put("minecraft:spruce_fence_gate", 183);
        mapping.put("minecraft:birch_fence_gate", 184);
        mapping.put("minecraft:jungle_fence_gate", 185);
        mapping.put("minecraft:dark_oak_fence_gate", 186);
        mapping.put("minecraft:acacia_fence_gate", 187);
        mapping.put("minecraft:spruce_fence", 188);
        mapping.put("minecraft:birch_fence", 189);
        mapping.put("minecraft:jungle_fence", 190);
        mapping.put("minecraft:dark_oak_fence", 191);
        mapping.put("minecraft:acacia_fence", 192);
        mapping.put("minecraft:spruce_door", 193);
        mapping.put("minecraft:birch_door", 194);
        mapping.put("minecraft:jungle_door", 195);
        mapping.put("minecraft:acacia_door", 196);
        mapping.put("minecraft:dark_oak_door", 197);

        // 1.9
        if (version.isGreaterThanOrEqual(1, 9, 0)) {
            mapping.put("minecraft:end_rod", 198);
            mapping.put("minecraft:chorus_plant", 199);
            mapping.put("minecraft:chorus_flower", 200);
            mapping.put("minecraft:purpur_block", 201);
            mapping.put("minecraft:purpur_pillar", 202);
            mapping.put("minecraft:purpur_stairs", 203);
            mapping.put("minecraft:purpur_double_slab", 204);
            mapping.put("minecraft:purpur_slab", 205);
            mapping.put("minecraft:end_bricks", 206);
            mapping.put("minecraft:beetroots", 207);
            mapping.put("minecraft:grass_path", 208);
            mapping.put("minecraft:end_gateway", 209);
            mapping.put("minecraft:repeating_command_block", 210);
            mapping.put("minecraft:chain_command_block", 211);
            mapping.put("minecraft:frosted_ice", 212);
            mapping.put("minecraft:structure_block", 255);
        }

        // 1.10.0
        if (version.isGreaterThanOrEqual(1, 10, 0)) {
            mapping.put("minecraft:magma", 213);
            mapping.put("minecraft:nether_wart_block", 214);
            mapping.put("minecraft:red_nether_brick", 215);
            mapping.put("minecraft:bone_block", 216);
            mapping.put("minecraft:structure_void", 217);
        }

        // 1.11.0
        if (version.isGreaterThanOrEqual(1, 11, 0)) {
            mapping.put("minecraft:observer", 218);
            mapping.put("minecraft:white_shulker_box", 219);
            mapping.put("minecraft:orange_shulker_box", 220);
            mapping.put("minecraft:magenta_shulker_box", 221);
            mapping.put("minecraft:light_blue_shulker_box", 222);
            mapping.put("minecraft:yellow_shulker_box", 223);
            mapping.put("minecraft:lime_shulker_box", 224);
            mapping.put("minecraft:pink_shulker_box", 225);
            mapping.put("minecraft:gray_shulker_box", 226);
            mapping.put("minecraft:silver_shulker_box", 227);
            mapping.put("minecraft:cyan_shulker_box", 228);
            mapping.put("minecraft:purple_shulker_box", 229);
            mapping.put("minecraft:blue_shulker_box", 230);
            mapping.put("minecraft:brown_shulker_box", 231);
            mapping.put("minecraft:green_shulker_box", 232);
            mapping.put("minecraft:red_shulker_box", 233);
            mapping.put("minecraft:black_shulker_box", 234);
        }

        // 1.12.0
        if (version.isGreaterThanOrEqual(1, 12, 0)) {
            mapping.put("minecraft:white_glazed_terracotta", 235);
            mapping.put("minecraft:orange_glazed_terracotta", 236);
            mapping.put("minecraft:magenta_glazed_terracotta", 237);
            mapping.put("minecraft:light_blue_glazed_terracotta", 238);
            mapping.put("minecraft:yellow_glazed_terracotta", 239);
            mapping.put("minecraft:lime_glazed_terracotta", 240);
            mapping.put("minecraft:pink_glazed_terracotta", 241);
            mapping.put("minecraft:gray_glazed_terracotta", 242);
            mapping.put("minecraft:silver_glazed_terracotta", 243);
            mapping.put("minecraft:cyan_glazed_terracotta", 244);
            mapping.put("minecraft:purple_glazed_terracotta", 245);
            mapping.put("minecraft:blue_glazed_terracotta", 246);
            mapping.put("minecraft:brown_glazed_terracotta", 247);
            mapping.put("minecraft:green_glazed_terracotta", 248);
            mapping.put("minecraft:red_glazed_terracotta", 249);
            mapping.put("minecraft:black_glazed_terracotta", 250);
            mapping.put("minecraft:concrete", 251);
            mapping.put("minecraft:concrete_powder", 252);
        }

        // 1.13 blocks
        if (version.isGreaterThanOrEqual(1, 13, 0)) {
            mapping.put("minecraft:seagrass", 3000);
            mapping.put("minecraft:tall_seagrass", 3001);
            mapping.put("minecraft:kelp", 3002);
            mapping.put("minecraft:kelp_plant", 3003);
            mapping.put("minecraft:dried_kelp_block", 3004);
            mapping.put("minecraft:turtle_egg", 3005);
            mapping.put("minecraft:bubble_column", 3006);
            mapping.put("minecraft:conduit", 3007);
            mapping.put("minecraft:prismarine_stairs", 3008);
            mapping.put("minecraft:prismarine_wall", 3009);
            mapping.put("minecraft:blue_ice", 3010);
            mapping.put("minecraft:acacia_pressure_plate", 3050);
            mapping.put("minecraft:birch_pressure_plate", 3051);
            mapping.put("minecraft:jungle_pressure_plate", 3052);
            mapping.put("minecraft:dark_oak_pressure_plate", 3053);
            mapping.put("minecraft:spruce_pressure_plate", 3054);
            mapping.put("minecraft:acacia_trapdoor", 3055);
            mapping.put("minecraft:birch_trapdoor", 3056);
            mapping.put("minecraft:jungle_trapdoor", 3057);
            mapping.put("minecraft:dark_oak_trapdoor", 3058);
            mapping.put("minecraft:spruce_trapdoor", 3059);
            mapping.put("minecraft:prismarine_brick_slab", 3060);
            mapping.put("minecraft:prismarine_slab", 3061);
            mapping.put("minecraft:dark_prismarine_slab", 3062);
            mapping.put("minecraft:prismarine_brick_stairs", 3063);
            mapping.put("minecraft:dark_prismarine_stairs", 3064);
            mapping.put("minecraft:sea_pickle", 3065);
            mapping.put("minecraft:brain_coral_block", 3066);
            mapping.put("minecraft:bubble_coral_block", 3067);
            mapping.put("minecraft:fire_coral_block", 3068);
            mapping.put("minecraft:horn_coral_block", 3069);
            mapping.put("minecraft:tube_coral_block", 3070);
            mapping.put("minecraft:dead_brain_coral_block", 3071);
            mapping.put("minecraft:dead_bubble_coral_block", 3072);
            mapping.put("minecraft:dead_fire_coral_block", 3073);
            mapping.put("minecraft:dead_horn_coral_block", 3074);
            mapping.put("minecraft:dead_tube_coral_block", 3075);
            mapping.put("minecraft:brain_coral", 3076);
            mapping.put("minecraft:bubble_coral", 3077);
            mapping.put("minecraft:fire_coral", 3078);
            mapping.put("minecraft:horn_coral", 3079);
            mapping.put("minecraft:tube_coral", 3080);
            mapping.put("minecraft:dead_brain_coral", 3081);
            mapping.put("minecraft:dead_bubble_coral", 3082);
            mapping.put("minecraft:dead_fire_coral", 3083);
            mapping.put("minecraft:dead_horn_coral", 3084);
            mapping.put("minecraft:dead_tube_coral", 3085);
            mapping.put("minecraft:brain_coral_fan", 3086);
            mapping.put("minecraft:bubble_coral_fan", 3087);
            mapping.put("minecraft:fire_coral_fan", 3088);
            mapping.put("minecraft:horn_coral_fan", 3089);
            mapping.put("minecraft:tube_coral_fan", 3090);
            mapping.put("minecraft:dead_brain_coral_fan", 3091);
            mapping.put("minecraft:dead_bubble_coral_fan", 3092);
            mapping.put("minecraft:dead_fire_coral_fan", 3093);
            mapping.put("minecraft:dead_horn_coral_fan", 3094);
            mapping.put("minecraft:dead_tube_coral_fan", 3095);
            mapping.put("minecraft:mushroom_stem", 3096);
            mapping.put("minecraft:carved_pumpkin", 3097);
            mapping.put("minecraft:smooth_quartz", 3098);
            mapping.put("minecraft:smooth_red_sandstone", 3099);
            mapping.put("minecraft:smooth_sandstone", 3100);
            mapping.put("minecraft:smooth_stone", 3101);
            mapping.put("minecraft:petrified_oak_slab", 3102);
            mapping.put("minecraft:stripped_oak_log", 3103);
            mapping.put("minecraft:stripped_spruce_log", 3104);
            mapping.put("minecraft:stripped_birch_log", 3105);
            mapping.put("minecraft:stripped_jungle_log", 3106);
            mapping.put("minecraft:stripped_acacia_log", 3107);
            mapping.put("minecraft:stripped_dark_oak_log", 3108);
            mapping.put("minecraft:stripped_oak_wood", 3109);
            mapping.put("minecraft:stripped_spruce_wood", 3110);
            mapping.put("minecraft:stripped_birch_wood", 3111);
            mapping.put("minecraft:stripped_jungle_wood", 3112);
            mapping.put("minecraft:stripped_acacia_wood", 3113);
            mapping.put("minecraft:stripped_dark_oak_wood", 3114);
            mapping.put("minecraft:acacia_wood", 3115);
            mapping.put("minecraft:birch_wood", 3116);
            mapping.put("minecraft:dark_oak_wood", 3117);
            mapping.put("minecraft:jungle_wood", 3118);
            mapping.put("minecraft:oak_wood", 3119);
            mapping.put("minecraft:spruce_wood", 3120);
        }

        // 1.14 blocks
        if (version.isGreaterThanOrEqual(1, 14, 0)) {
            mapping.put("minecraft:barrel", 3011);
            mapping.put("minecraft:smoker", 3012);
            mapping.put("minecraft:blast_furnace", 3013);
            mapping.put("minecraft:cartography_table", 3014);
            mapping.put("minecraft:fletching_table", 3015);
            mapping.put("minecraft:grindstone", 3016);
            mapping.put("minecraft:lectern", 3017);
            mapping.put("minecraft:loom", 3018);
            mapping.put("minecraft:smithing_table", 3019);
            mapping.put("minecraft:composter", 3020);
            mapping.put("minecraft:bell", 3021);
            mapping.put("minecraft:lantern", 3022);
            mapping.put("minecraft:campfire", 3023);
            mapping.put("minecraft:stonecutter", 3024);
        }

        // 1.15 blocks
        if (version.isGreaterThanOrEqual(1, 15, 0)) {
            mapping.put("minecraft:bee_nest", 3025);
            mapping.put("minecraft:beehive", 3026);
            mapping.put("minecraft:honey_block", 3027);
            mapping.put("minecraft:honeycomb_block", 3028);
        }

        // 1.16 blocks
        if (version.isGreaterThanOrEqual(1, 16, 0)) {
            mapping.put("minecraft:ancient_debris", 3029);
            mapping.put("minecraft:netherite_block", 3030);
            mapping.put("minecraft:lodestone", 3031);
            mapping.put("minecraft:target", 3032);
            mapping.put("minecraft:basalt", 3033);
            mapping.put("minecraft:blackstone", 3034);
            mapping.put("minecraft:polished_blackstone", 3035);
            mapping.put("minecraft:polished_blackstone_bricks", 3036);
            mapping.put("minecraft:gilded_blackstone", 3037);
            mapping.put("minecraft:crimson_nylium", 3038);
            mapping.put("minecraft:warped_nylium", 3039);
            mapping.put("minecraft:soul_soil", 3040);
            mapping.put("minecraft:soul_fire", 3041);
            mapping.put("minecraft:soul_torch", 3042);
            mapping.put("minecraft:soul_wall_torch", 3043);
            mapping.put("minecraft:soul_campfire", 3044);
            mapping.put("minecraft:soul_lantern", 3045);
            mapping.put("minecraft:crimson_fence", 3046);
            mapping.put("minecraft:warped_fence", 3047);
            mapping.put("minecraft:crimson_door", 3048);
            mapping.put("minecraft:warped_door", 3049);
        }
    }

    @Override
    public Optional<String> to(Integer input) {
        return Optional.ofNullable(mapping.inverse().get(input));
    }

    @Override
    public Optional<Integer> from(String input) {
        if (!input.contains(":")) {
            input = "minecraft:" + input;
        }
        return Optional.ofNullable(mapping.forward().get(input));
    }

    /**
     * Get the backing map for the resolver.
     *
     * @return the bimap of identifier to block ID.
     */
    public InvertibleMap<String, Integer> getMapping() {
        return mapping;
    }
}
