package de.pnku.mjnv.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricModelProvider;
import net.minecraft.data.models.BlockModelGenerators;
import net.minecraft.data.models.ItemModelGenerators;
import net.minecraft.data.models.model.TextureMapping;
import net.minecraft.data.models.model.TexturedModel;
import net.minecraft.world.level.block.Block;

import static de.pnku.mjnv.init.MjnvBlockInit.more_jukeboxes;
import static de.pnku.mjnv.init.MjnvBlockInit.more_noteblocks;

public class MoreJukeboxNoteblockVariantBlockStateGenerator extends FabricModelProvider {
    public MoreJukeboxNoteblockVariantBlockStateGenerator(FabricDataOutput output) {
        super(output);
    }

    @Override
    public void generateBlockStateModels(BlockModelGenerators blockStateModelGenerator) {
        for (Block jukeboxBlock : more_jukeboxes) {
            blockStateModelGenerator.createTrivialBlock(jukeboxBlock, TexturedModel.CUBE_TOP);
        }
        for (Block noteblockBlock : more_noteblocks) {
            blockStateModelGenerator.createTrivialCube(noteblockBlock);
        }
    }

    @Override
    public void generateItemModels(ItemModelGenerators itemModelGenerator) {
    }
}
