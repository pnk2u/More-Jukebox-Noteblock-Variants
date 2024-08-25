package de.pnku.mjnv.block;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.NoteBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.material.MapColor;

public class MoreNoteblockVariantBlock extends NoteBlock {
    public final String noteblockWoodType;

    public MoreNoteblockVariantBlock(MapColor colour, String noteblockWoodType) {
        super(Properties.copy(Blocks.NOTE_BLOCK).mapColor(colour));
        this.noteblockWoodType = noteblockWoodType;
    }

    public MoreNoteblockVariantBlock(MapColor colour, SoundType sound, String noteblockWoodType) {
        super(Properties.copy(Blocks.NOTE_BLOCK).mapColor(colour).sound(sound));
        this.noteblockWoodType = noteblockWoodType;
    }

    public Item getPlanksItem(String planksWood) {
        switch (planksWood) {
            case "acacia" -> {
                return Items.ACACIA_PLANKS;
            }
            case "bamboo" -> {
                return Items.BAMBOO_PLANKS;
            }
            case "birch" -> {
                return Items.BIRCH_PLANKS;
            }
            case "cherry" -> {
                return Items.CHERRY_PLANKS;
            }
            case "crimson" -> {
                return Items.CRIMSON_PLANKS;
            }
            case "dark_oak" -> {
                return Items.DARK_OAK_PLANKS;
            }
            case "oak" -> {
                return Items.OAK_PLANKS;
            }
            case "jungle" -> {
                return Items.JUNGLE_PLANKS;
            }
            case "mangrove" -> {
                return Items.MANGROVE_PLANKS;
            }
            case "spruce" -> {
                return Items.SPRUCE_PLANKS;
            }
            case "warped" -> {
                return Items.WARPED_PLANKS;
            }

        }
        return null;
    }
}