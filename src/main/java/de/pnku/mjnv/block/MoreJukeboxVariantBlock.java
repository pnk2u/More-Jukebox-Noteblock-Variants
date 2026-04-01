package de.pnku.mjnv.block;

import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.JukeboxBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.MapColor;

public class MoreJukeboxVariantBlock extends JukeboxBlock {
    public final String jukeboxWoodType;

    public MoreJukeboxVariantBlock(MapColor colour, String jukeboxWoodType) {
        super(Properties.ofFullCopy(Blocks.JUKEBOX).mapColor(colour));
        this.jukeboxWoodType = jukeboxWoodType;
        this.registerDefaultState((BlockState)((BlockState)this.stateDefinition.any()).setValue(HAS_RECORD, false));
    }

    public MoreJukeboxVariantBlock(MapColor colour, SoundType sound, String jukeboxWoodType) {
        super(Properties.ofFullCopy(Blocks.JUKEBOX).mapColor(colour).sound(sound));
        this.jukeboxWoodType = jukeboxWoodType;
        this.registerDefaultState((BlockState)((BlockState)this.stateDefinition.any()).setValue(HAS_RECORD, false));
    }
}