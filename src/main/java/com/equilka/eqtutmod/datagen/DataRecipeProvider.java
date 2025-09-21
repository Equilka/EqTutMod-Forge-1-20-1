package com.equilka.eqtutmod.datagen;

import com.equilka.eqtutmod.init.ModBlockInit;
import com.equilka.eqtutmod.init.ModItemInit;
import net.minecraft.advancements.CriterionTriggerInstance;
import net.minecraft.advancements.critereon.EntityHurtPlayerTrigger;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.*;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraftforge.fml.common.Mod;

import java.util.function.Consumer;

public class DataRecipeProvider extends RecipeProvider {
    public DataRecipeProvider(PackOutput pOutput) {
        super(pOutput);
    }

    @Override
    protected void buildRecipes(Consumer<FinishedRecipe> consumer) {

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlockInit.MEAT_BLOCK.get())
                .define('#', Items.ROTTEN_FLESH)
                .define('@', Items.LAPIS_BLOCK)
                .pattern("###")
                .pattern("#@#")
                .pattern("###")
                .unlockedBy("has_lapis_block", has(Items.LAPIS_BLOCK))
                .save(consumer);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlockInit.DEMONCORE_BLOCK.get())
                .define('#', Items.SMOOTH_STONE)
                .define('@', ModItemInit.CURSEDCORE.get())
                .define('-', ModItemInit.SCREWDRIVER.get())
                .pattern("###")
                .pattern("-@ ")
                .pattern("###")
                .unlockedBy("has_cursed_core", has(ModItemInit.CURSEDCORE.get()))
                .save(consumer);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItemInit.SCREWDRIVER.get())
                .define('#', Items.LAPIS_LAZULI)
                .define('-', Items.IRON_NUGGET)
                .pattern("#-")
                .unlockedBy("has_cursed_core", has(ModItemInit.CURSEDCORE.get()))
                .save(consumer);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.COMBAT, ModItemInit.PLASTER.get())
                .requires(Items.STRING, 5)
                .unlockedBy("player_was_hurt", EntityHurtPlayerTrigger.TriggerInstance.entityHurtPlayer())
                .save(consumer);

        SimpleCookingRecipeBuilder.blasting(
                Ingredient.of(ModBlockInit.MEAT_BLOCK.get()),
                RecipeCategory.MISC,
                ModItemInit.CURSEDCORE.get(),
                3f,
                300)
                .unlockedBy("has_meat_block", has(ModBlockInit.MEAT_BLOCK.get()))
                .save(consumer);

    }
}
