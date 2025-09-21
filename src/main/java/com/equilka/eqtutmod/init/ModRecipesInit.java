package com.equilka.eqtutmod.init;

import com.equilka.eqtutmod.EqTutMod;
import com.equilka.eqtutmod.recipes.WWCCopperVerticalSlabRecipe;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.SimpleCraftingRecipeSerializer;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class ModRecipesInit {
    public static final DeferredRegister<RecipeSerializer<?>> REGISTER = DeferredRegister.create(Registries.RECIPE_SERIALIZER, EqTutMod.MODID);

    public static final RegistryObject<RecipeSerializer<WWCCopperVerticalSlabRecipe>> WWCCV_SLAB =
            REGISTER.register("waxed_weathered_cut_copper_vertical_slab", () -> new SimpleCraftingRecipeSerializer<>(WWCCopperVerticalSlabRecipe::new));
}
