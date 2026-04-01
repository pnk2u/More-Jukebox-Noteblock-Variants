package de.pnku.mjnv.block;

import de.pnku.mjnv.MoreJukeboxNoteblockVariants;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.NoteBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.material.MapColor;

public class MoreNoteblockVariantBlock extends NoteBlock {
    public final String noteblockWoodType;

    public MoreNoteblockVariantBlock(MapColor colour, String noteblockWoodType) {
        super(Properties.ofFullCopy(Blocks.NOTE_BLOCK).mapColor(colour).setId(ResourceKey.create(Registries.BLOCK, MoreJukeboxNoteblockVariants.asId(noteblockWoodType + "_noteblock"))));
        this.noteblockWoodType = noteblockWoodType;
    }

    public MoreNoteblockVariantBlock(MapColor colour, SoundType sound, String noteblockWoodType) {
        super(Properties.ofFullCopy(Blocks.NOTE_BLOCK).mapColor(colour).setId(ResourceKey.create(Registries.BLOCK, MoreJukeboxNoteblockVariants.asId(noteblockWoodType + "_noteblock"))).sound(sound));
        this.noteblockWoodType = noteblockWoodType;
    }
}