package de.pnku.mjnv.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricBlockLootTableProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.world.level.block.Block;

import java.util.concurrent.CompletableFuture;

import static de.pnku.mjnv.init.MjnvBlockInit.more_jukeboxes;
import static de.pnku.mjnv.init.MjnvBlockInit.more_noteblocks;

public class MoreJukeboxNoteblockVariantLootTableGenerator extends FabricBlockLootTableProvider {
    public MoreJukeboxNoteblockVariantLootTableGenerator(FabricDataOutput dataOutput, CompletableFuture<HolderLookup.Provider> registryLookup) {
        super(dataOutput, registryLookup);
    }

    @Override
    public void generate() {
        for (Block jukeboxBlock : more_jukeboxes) {
            dropSelf(jukeboxBlock);
        }
        for (Block noteblockBlock : more_noteblocks) {
            dropSelf(noteblockBlock);
        }
    }
}
