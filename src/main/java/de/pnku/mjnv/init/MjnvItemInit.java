package de.pnku.mjnv.init;

import de.pnku.mjnv.MoreJukeboxNoteblockVariants;
import de.pnku.mjnv.block.MoreJukeboxVariantBlock;
import de.pnku.mjnv.block.MoreNoteblockVariantBlock;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.core.Registry;
import net.minecraft.world.level.block.Block;

import static de.pnku.mjnv.init.MjnvBlockInit.*;

public class MjnvItemInit {
    public static final BlockItem BIRCH_JUKEBOX_I = itemFromBlock(BIRCH_JUKEBOX);
    public static final BlockItem DARK_OAK_JUKEBOX_I = itemFromBlock(DARK_OAK_JUKEBOX);
    public static final BlockItem OAK_JUKEBOX_I = itemFromBlock(OAK_JUKEBOX);
    public static final BlockItem SPRUCE_JUKEBOX_I = itemFromBlock(SPRUCE_JUKEBOX);
    public static final BlockItem JUNGLE_JUKEBOX_I = itemFromBlock(JUNGLE_JUKEBOX);
    public static final BlockItem ACACIA_JUKEBOX_I = itemFromBlock(ACACIA_JUKEBOX);
    public static final BlockItem MANGROVE_JUKEBOX_I = itemFromBlock(MANGROVE_JUKEBOX);
    public static final BlockItem CHERRY_JUKEBOX_I = itemFromBlock(CHERRY_JUKEBOX);
    public static final BlockItem BAMBOO_JUKEBOX_I = itemFromBlock(BAMBOO_JUKEBOX);
    public static final BlockItem CRIMSON_JUKEBOX_I = itemFromBlock(CRIMSON_JUKEBOX);
    public static final BlockItem WARPED_JUKEBOX_I = itemFromBlock(WARPED_JUKEBOX);
    public static final BlockItem BIRCH_NOTEBLOCK_I = itemFromBlock(BIRCH_NOTEBLOCK);
    public static final BlockItem DARK_OAK_NOTEBLOCK_I = itemFromBlock(DARK_OAK_NOTEBLOCK);
    public static final BlockItem OAK_NOTEBLOCK_I = itemFromBlock(OAK_NOTEBLOCK);
    public static final BlockItem SPRUCE_NOTEBLOCK_I = itemFromBlock(SPRUCE_NOTEBLOCK);
    public static final BlockItem JUNGLE_NOTEBLOCK_I = itemFromBlock(JUNGLE_NOTEBLOCK);
    public static final BlockItem ACACIA_NOTEBLOCK_I = itemFromBlock(ACACIA_NOTEBLOCK);
    public static final BlockItem MANGROVE_NOTEBLOCK_I = itemFromBlock(MANGROVE_NOTEBLOCK);
    public static final BlockItem CHERRY_NOTEBLOCK_I = itemFromBlock(CHERRY_NOTEBLOCK);
    public static final BlockItem BAMBOO_NOTEBLOCK_I = itemFromBlock(BAMBOO_NOTEBLOCK);
    public static final BlockItem CRIMSON_NOTEBLOCK_I = itemFromBlock(CRIMSON_NOTEBLOCK);
    public static final BlockItem WARPED_NOTEBLOCK_I = itemFromBlock(WARPED_NOTEBLOCK);

    public static BlockItem itemFromBlock(Block moreJukeboxNoteblockBlock) {
        return new BlockItem(moreJukeboxNoteblockBlock, setProperties(moreJukeboxNoteblockBlock));
    }

    public static Item.Properties setProperties(Block moreJukeboxNoteblockBlock) {
        return new Item.Properties()
                .setId(ResourceKey.create(Registries.ITEM,BuiltInRegistries.BLOCK.getKey(moreJukeboxNoteblockBlock))).useBlockDescriptionPrefix();
    }

    public static void registerJukeboxNoteblockItems() {
        registerJukeboxItem(BIRCH_JUKEBOX_I, Items.JUKEBOX);
        registerJukeboxItem(DARK_OAK_JUKEBOX_I, BIRCH_JUKEBOX_I);
        registerJukeboxItem(OAK_JUKEBOX_I, DARK_OAK_JUKEBOX_I);
        registerJukeboxItem(SPRUCE_JUKEBOX_I, OAK_JUKEBOX_I);
        registerJukeboxItem(JUNGLE_JUKEBOX_I, SPRUCE_JUKEBOX_I);
        registerJukeboxItem(ACACIA_JUKEBOX_I, JUNGLE_JUKEBOX_I);
        registerJukeboxItem(MANGROVE_JUKEBOX_I, ACACIA_JUKEBOX_I);
        registerJukeboxItem(CHERRY_JUKEBOX_I, MANGROVE_JUKEBOX_I);
        registerJukeboxItem(BAMBOO_JUKEBOX_I, CHERRY_JUKEBOX_I);
        registerJukeboxItem(CRIMSON_JUKEBOX_I, BAMBOO_JUKEBOX_I);
        registerJukeboxItem(WARPED_JUKEBOX_I, CRIMSON_JUKEBOX_I);
        registerNoteblockItem(BIRCH_NOTEBLOCK_I, Items.NOTE_BLOCK);
        registerNoteblockItem(DARK_OAK_NOTEBLOCK_I, BIRCH_NOTEBLOCK_I);
        registerNoteblockItem(OAK_NOTEBLOCK_I, DARK_OAK_NOTEBLOCK_I);
        registerNoteblockItem(SPRUCE_NOTEBLOCK_I, OAK_NOTEBLOCK_I);
        registerNoteblockItem(JUNGLE_NOTEBLOCK_I, SPRUCE_NOTEBLOCK_I);
        registerNoteblockItem(ACACIA_NOTEBLOCK_I, JUNGLE_NOTEBLOCK_I);
        registerNoteblockItem(MANGROVE_NOTEBLOCK_I, ACACIA_NOTEBLOCK_I);
        registerNoteblockItem(CHERRY_NOTEBLOCK_I, MANGROVE_NOTEBLOCK_I);
        registerNoteblockItem(BAMBOO_NOTEBLOCK_I, CHERRY_NOTEBLOCK_I);
        registerNoteblockItem(CRIMSON_NOTEBLOCK_I, BAMBOO_NOTEBLOCK_I);
        registerNoteblockItem(WARPED_NOTEBLOCK_I, CRIMSON_NOTEBLOCK_I);
    }

    private static void registerJukeboxItem(BlockItem jukebox, Item jukeboxAfter) {
        Registry.register(BuiltInRegistries.ITEM, MoreJukeboxNoteblockVariants.asId(((MoreJukeboxVariantBlock) jukebox.getBlock()).jukeboxWoodType + "_jukebox"), jukebox);

        ItemGroupEvents.modifyEntriesEvent(CreativeModeTabs.FUNCTIONAL_BLOCKS).register(entries -> entries.addAfter(jukeboxAfter, jukebox));
    }

    private static void registerNoteblockItem(BlockItem noteblock, Item noteblockAfter) {
        Registry.register(BuiltInRegistries.ITEM, MoreJukeboxNoteblockVariants.asId(((MoreNoteblockVariantBlock) noteblock.getBlock()).noteblockWoodType + "_noteblock"), noteblock);

        ItemGroupEvents.modifyEntriesEvent(CreativeModeTabs.FUNCTIONAL_BLOCKS).register(entries -> entries.addAfter(noteblockAfter, noteblock));
    }
}