package de.pnku.mjnv.datagen;

import de.pnku.mjnv.MoreJukeboxNoteblockVariants;
import de.pnku.mjnv.block.MoreJukeboxVariantBlock;
import de.pnku.mjnv.block.MoreNoteblockVariantBlock;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.ShapedRecipeBuilder;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Block;

import java.util.function.Consumer;

import static de.pnku.mjnv.init.MjnvBlockInit.more_jukeboxes;
import static de.pnku.mjnv.init.MjnvBlockInit.more_noteblocks;

public class MoreJukeboxNoteblockVariantRecipeGenerator extends FabricRecipeProvider {
    public MoreJukeboxNoteblockVariantRecipeGenerator(FabricDataOutput output) {
        super(output);
    }

    @Override
    public void buildRecipes(Consumer<FinishedRecipe> recipeOutput) {
        for (Block jukeboxBlock : more_jukeboxes) {
            String planksWood = ((MoreJukeboxVariantBlock) jukeboxBlock).jukeboxWoodType;
            Item jukeboxPlanks = ((MoreJukeboxVariantBlock) jukeboxBlock).getPlanksItem(planksWood);
            ShapedRecipeBuilder.shaped(RecipeCategory.MISC, jukeboxBlock)
                    .group("jukebox")
                    .unlockedBy("has_planks", has(jukeboxPlanks))
                    .unlockedBy("has_diamond", has(Items.DIAMOND))
                    .pattern("PPP")
                    .pattern("P0P")
                    .pattern("PPP")
                    .define('P', jukeboxPlanks)
                    .define('0', Items.DIAMOND)
                    .save(recipeOutput, MoreJukeboxNoteblockVariants.asId(planksWood + "_jukebox"));
        }
        for (Block noteblockBlock : more_noteblocks) {
            String planksWood = ((MoreNoteblockVariantBlock) noteblockBlock).noteblockWoodType;
            Item noteblockPlanks = ((MoreNoteblockVariantBlock) noteblockBlock).getPlanksItem(planksWood);
            ShapedRecipeBuilder.shaped(RecipeCategory.MISC, noteblockBlock)
                    .group("noteblock")
                    .unlockedBy("has_planks", has(noteblockPlanks))
                    .unlockedBy("has_redstone", has(Items.REDSTONE))
                    .pattern("PPP")
                    .pattern("P+P")
                    .pattern("PPP")
                    .define('P', noteblockPlanks)
                    .define('+', Items.REDSTONE)
                    .save(recipeOutput, MoreJukeboxNoteblockVariants.asId(planksWood + "_noteblock"));
        }
    }
}
