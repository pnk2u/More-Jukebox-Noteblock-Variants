package de.pnku.mjnv;

import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import de.pnku.mjnv.datagen.*;

public class MoreJukeboxNoteblockVariantsDatagen implements DataGeneratorEntrypoint {

    @Override
    public void onInitializeDataGenerator(FabricDataGenerator generator) {
        FabricDataGenerator.Pack pack = generator.createPack();

        pack.addProvider(MoreJukeboxNoteblockVariantRecipeGenerator::new);
//        pack.addProvider(MoreJukeboxNoteblockVariantBlockStateGenerator::new);
//        pack.addProvider(MoreJukeboxNoteblockVariantLootTableGenerator::new);
//        pack.addProvider(MoreJukeboxNoteblockVariantLangGenerator::new);
    }
}
