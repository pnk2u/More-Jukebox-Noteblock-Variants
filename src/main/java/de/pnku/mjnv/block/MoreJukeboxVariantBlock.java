package de.pnku.mjnv.block;

import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.JukeboxBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.MapColor;

public class MoreJukeboxVariantBlock extends JukeboxBlock {
    public final String jukeboxWoodType;

    public MoreJukeboxVariantBlock(MapColor colour, String jukeboxWoodType) {
        super(Properties.copy(Blocks.JUKEBOX).mapColor(colour));
        this.jukeboxWoodType = jukeboxWoodType;
    }

    public MoreJukeboxVariantBlock(MapColor colour, SoundType sound, String jukeboxWoodType) {
        super(Properties.copy(Blocks.JUKEBOX).mapColor(colour).sound(sound));
        this.jukeboxWoodType = jukeboxWoodType;
    }
}