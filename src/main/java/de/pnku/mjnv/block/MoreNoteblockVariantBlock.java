package de.pnku.mjnv.block;

import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.NoteBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.material.MapColor;

public class MoreNoteblockVariantBlock extends NoteBlock {
    public final String noteblockWoodType;

    public MoreNoteblockVariantBlock(MapColor colour, String noteblockWoodType) {
        super(Properties.ofFullCopy(Blocks.NOTE_BLOCK).mapColor(colour));
        this.noteblockWoodType = noteblockWoodType;
    }

    public MoreNoteblockVariantBlock(MapColor colour, SoundType sound, String noteblockWoodType) {
        super(Properties.ofFullCopy(Blocks.NOTE_BLOCK).mapColor(colour).sound(sound));
        this.noteblockWoodType = noteblockWoodType;
    }
}