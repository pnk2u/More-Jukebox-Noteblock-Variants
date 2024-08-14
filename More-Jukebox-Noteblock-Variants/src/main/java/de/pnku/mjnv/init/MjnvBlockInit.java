package de.pnku.mjnv.init;

import de.pnku.mjnv.MoreJukeboxNoteblockVariants;
import de.pnku.mjnv.block.MoreJukeboxVariantBlock;
import de.pnku.mjnv.block.MoreJukeboxVariantBlockEntity;
import de.pnku.mjnv.block.MoreNoteblockVariantBlock;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.level.block.Block;
import net.minecraft.core.Registry;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.material.MapColor;

import java.util.ArrayList;
import java.util.List;

public class MjnvBlockInit {
    public static final MoreJukeboxVariantBlock BIRCH_JUKEBOX = new MoreJukeboxVariantBlock(MapColor.SAND, "birch");
    public static final MoreJukeboxVariantBlock DARK_OAK_JUKEBOX = new MoreJukeboxVariantBlock(MapColor.COLOR_BROWN, "dark_oak");
    public static final MoreJukeboxVariantBlock OAK_JUKEBOX = new MoreJukeboxVariantBlock(MapColor.WOOD, "oak");
    public static final MoreJukeboxVariantBlock SPRUCE_JUKEBOX = new MoreJukeboxVariantBlock(MapColor.PODZOL, "spruce");
    public static final MoreJukeboxVariantBlock JUNGLE_JUKEBOX = new MoreJukeboxVariantBlock(MapColor.DIRT, "jungle");
    public static final MoreJukeboxVariantBlock ACACIA_JUKEBOX = new MoreJukeboxVariantBlock(MapColor.COLOR_ORANGE, "acacia");
    public static final MoreJukeboxVariantBlock MANGROVE_JUKEBOX = new MoreJukeboxVariantBlock(MapColor.COLOR_RED, "mangrove");
    public static final MoreJukeboxVariantBlock CHERRY_JUKEBOX = new MoreJukeboxVariantBlock(MapColor.TERRACOTTA_WHITE, SoundType.CHERRY_WOOD, "cherry");
    public static final MoreJukeboxVariantBlock BAMBOO_JUKEBOX = new MoreJukeboxVariantBlock(MapColor.COLOR_YELLOW, SoundType.BAMBOO_WOOD, "bamboo");
    public static final MoreJukeboxVariantBlock CRIMSON_JUKEBOX = new MoreJukeboxVariantBlock(MapColor.CRIMSON_STEM, SoundType.NETHER_WOOD, "crimson");
    public static final MoreJukeboxVariantBlock WARPED_JUKEBOX = new MoreJukeboxVariantBlock(MapColor.WARPED_STEM, SoundType.NETHER_WOOD, "warped");
    public static final MoreNoteblockVariantBlock BIRCH_NOTEBLOCK = new MoreNoteblockVariantBlock(MapColor.SAND, "birch");
    public static final MoreNoteblockVariantBlock DARK_OAK_NOTEBLOCK = new MoreNoteblockVariantBlock(MapColor.COLOR_BROWN, "dark_oak");
    public static final MoreNoteblockVariantBlock OAK_NOTEBLOCK = new MoreNoteblockVariantBlock(MapColor.WOOD, "oak");
    public static final MoreNoteblockVariantBlock SPRUCE_NOTEBLOCK = new MoreNoteblockVariantBlock(MapColor.PODZOL, "spruce");
    public static final MoreNoteblockVariantBlock JUNGLE_NOTEBLOCK = new MoreNoteblockVariantBlock(MapColor.DIRT, "jungle");
    public static final MoreNoteblockVariantBlock ACACIA_NOTEBLOCK = new MoreNoteblockVariantBlock(MapColor.COLOR_ORANGE, "acacia");
    public static final MoreNoteblockVariantBlock MANGROVE_NOTEBLOCK = new MoreNoteblockVariantBlock(MapColor.COLOR_RED, "mangrove");
    public static final MoreNoteblockVariantBlock CHERRY_NOTEBLOCK = new MoreNoteblockVariantBlock(MapColor.TERRACOTTA_WHITE, SoundType.CHERRY_WOOD, "cherry");
    public static final MoreNoteblockVariantBlock BAMBOO_NOTEBLOCK = new MoreNoteblockVariantBlock(MapColor.COLOR_YELLOW, SoundType.BAMBOO_WOOD, "bamboo");
    public static final MoreNoteblockVariantBlock CRIMSON_NOTEBLOCK = new MoreNoteblockVariantBlock(MapColor.CRIMSON_STEM, SoundType.NETHER_WOOD, "crimson");
    public static final MoreNoteblockVariantBlock WARPED_NOTEBLOCK = new MoreNoteblockVariantBlock(MapColor.WARPED_STEM, SoundType.NETHER_WOOD, "warped");

    public static BlockEntityType<MoreJukeboxVariantBlockEntity> MORE_JUKEBOX_VARIANT_BLOCK_ENTITY;

    public static final List<Block> more_jukeboxes = new ArrayList<>();
    public static final List<Block> more_noteblocks = new ArrayList<>();


    public static void registerJukeboxNoteblockBlocks() {
        registerJukeboxBlock(BIRCH_JUKEBOX);
        registerJukeboxBlock(DARK_OAK_JUKEBOX);
        registerJukeboxBlock(OAK_JUKEBOX);
        registerJukeboxBlock(SPRUCE_JUKEBOX);
        registerJukeboxBlock(JUNGLE_JUKEBOX);
        registerJukeboxBlock(ACACIA_JUKEBOX);
        registerJukeboxBlock(MANGROVE_JUKEBOX);
        registerJukeboxBlock(CHERRY_JUKEBOX);
        registerJukeboxBlock(BAMBOO_JUKEBOX);
        registerJukeboxBlock(CRIMSON_JUKEBOX);
        registerJukeboxBlock(WARPED_JUKEBOX);
        registerNoteblockBlock(BIRCH_NOTEBLOCK);
        registerNoteblockBlock(DARK_OAK_NOTEBLOCK);
        registerNoteblockBlock(OAK_NOTEBLOCK);
        registerNoteblockBlock(SPRUCE_NOTEBLOCK);
        registerNoteblockBlock(JUNGLE_NOTEBLOCK);
        registerNoteblockBlock(ACACIA_NOTEBLOCK);
        registerNoteblockBlock(MANGROVE_NOTEBLOCK);
        registerNoteblockBlock(CHERRY_NOTEBLOCK);
        registerNoteblockBlock(BAMBOO_NOTEBLOCK);
        registerNoteblockBlock(CRIMSON_NOTEBLOCK);
        registerNoteblockBlock(WARPED_NOTEBLOCK);

        MORE_JUKEBOX_VARIANT_BLOCK_ENTITY = Registry.register(BuiltInRegistries.BLOCK_ENTITY_TYPE, MoreJukeboxNoteblockVariants.asId("more_smoker_variant"), BlockEntityType.Builder.of(MoreJukeboxVariantBlockEntity::new, more_jukeboxes.toArray(Block[]::new)).build(null));

    }

    private static void registerJukeboxBlock(MoreJukeboxVariantBlock jukebox) {
        Registry.register(BuiltInRegistries.BLOCK, MoreJukeboxNoteblockVariants.asId(jukebox.jukeboxWoodType + "_jukebox"), jukebox);
        more_jukeboxes.add(jukebox);
    }

    private static void registerNoteblockBlock(MoreNoteblockVariantBlock noteblock) {
        Registry.register(BuiltInRegistries.BLOCK, MoreJukeboxNoteblockVariants.asId(noteblock.noteblockWoodType + "_noteblock"), noteblock);
        more_noteblocks.add(noteblock);
    }
}
