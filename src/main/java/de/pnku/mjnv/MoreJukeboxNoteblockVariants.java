package de.pnku.mjnv;

import de.pnku.mjnv.init.MjnvBlockInit;
import de.pnku.mjnv.init.MjnvItemInit;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.resources.ResourceLocation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MoreJukeboxNoteblockVariants implements ModInitializer {
    public static final String MODID = "quad-lolmjnv";
    public static final Logger LOGGER = LoggerFactory.getLogger("lolmjnv");
    public static boolean isVinURLLoaded = false;

    @Override
    public void onInitialize() {
        if (FabricLoader.getInstance().isModLoaded("vinurl")){ isVinURLLoaded = true; LOGGER.info("VinURL loaded.");}
        MjnvBlockInit.registerJukeboxNoteblockBlocks();
        MjnvItemInit.registerJukeboxNoteblockItems();
    }

    public static ResourceLocation asId(String path) {
        return new ResourceLocation(MODID, path);
    }
}