package de.pnku.mjnv.block;

import de.pnku.mjnv.MoreJukeboxNoteblockVariants;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.JukeboxBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.MapColor;

public class MoreJukeboxVariantBlock extends JukeboxBlock {
    public final String jukeboxWoodType;

    public MoreJukeboxVariantBlock(MapColor colour, String jukeboxWoodType) {
        super(Properties.ofFullCopy(Blocks.JUKEBOX).mapColor(colour).setId(ResourceKey.create(Registries.BLOCK, MoreJukeboxNoteblockVariants.asId(jukeboxWoodType + "_jukebox"))));
        this.jukeboxWoodType = jukeboxWoodType;
        this.registerDefaultState((BlockState)((BlockState)this.stateDefinition.any()).setValue(HAS_RECORD, false));
    }

    public MoreJukeboxVariantBlock(MapColor colour, SoundType sound, String jukeboxWoodType) {
        super(Properties.ofFullCopy(Blocks.JUKEBOX).mapColor(colour).setId(ResourceKey.create(Registries.BLOCK, MoreJukeboxNoteblockVariants.asId(jukeboxWoodType + "_jukebox"))).sound(sound));
        this.jukeboxWoodType = jukeboxWoodType;
        this.registerDefaultState((BlockState)((BlockState)this.stateDefinition.any()).setValue(HAS_RECORD, false));
    }
}