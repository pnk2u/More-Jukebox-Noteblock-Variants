package de.pnku.mjnv.datagen;

import de.pnku.mjnv.block.MoreJukeboxVariantBlock;
import de.pnku.mjnv.block.MoreNoteblockVariantBlock;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricLanguageProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.world.level.block.Block;
import org.apache.commons.text.WordUtils;

import java.util.concurrent.CompletableFuture;

import static de.pnku.mjnv.init.MjnvBlockInit.more_jukeboxes;
import static de.pnku.mjnv.init.MjnvBlockInit.more_noteblocks;

public class MoreJukeboxNoteblockVariantLangGenerator extends FabricLanguageProvider{
    public MoreJukeboxNoteblockVariantLangGenerator(FabricDataOutput dataOutput) {
        super(dataOutput);
    }

    @Override
    public void generateTranslations(TranslationBuilder translationBuilder) {
        for (Block jukeboxBlock : more_jukeboxes) {
            String jukeboxName = WordUtils.capitalizeFully(((MoreJukeboxVariantBlock) jukeboxBlock).jukeboxWoodType + " Jukebox");
            translationBuilder.add(jukeboxBlock, jukeboxName);
        }
        for (Block noteblockBlock : more_noteblocks) {
            String noteblockName = WordUtils.capitalizeFully(((MoreNoteblockVariantBlock) noteblockBlock).noteblockWoodType + " Noteblock");
            translationBuilder.add(noteblockBlock, noteblockName);
        }
    }
}
