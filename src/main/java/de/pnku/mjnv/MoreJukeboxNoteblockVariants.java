package de.pnku.mjnv;

import de.pnku.mjnv.init.MjnvBlockInit;
import de.pnku.mjnv.init.MjnvItemInit;
import net.fabricmc.api.ModInitializer;
import net.minecraft.resources.ResourceLocation;

public class MoreJukeboxNoteblockVariants implements ModInitializer {
    public static final String MODID = "quad-lolmjnv";

    @Override
    public void onInitialize() {
        MjnvBlockInit.registerJukeboxNoteblockBlocks();
        MjnvItemInit.registerJukeboxNoteblockItems();
    }

    public static ResourceLocation asId(String path) {
        return ResourceLocation.fromNamespaceAndPath(MODID, path);
    }
}