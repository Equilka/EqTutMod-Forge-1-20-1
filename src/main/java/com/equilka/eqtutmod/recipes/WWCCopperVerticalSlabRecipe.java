package com.equilka.eqtutmod.recipes;

import com.equilka.eqtutmod.init.ModBlockInit;
import com.equilka.eqtutmod.init.ModItemInit;
import com.equilka.eqtutmod.init.ModRecipesInit;
import net.minecraft.core.NonNullList;
import net.minecraft.core.RegistryAccess;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.inventory.CraftingContainer;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.crafting.CraftingBookCategory;
import net.minecraft.world.item.crafting.CustomRecipe;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.level.Level;

public class WWCCopperVerticalSlabRecipe extends CustomRecipe {
    private static final String STORED_SOUL = "StoredSoul";
    public WWCCopperVerticalSlabRecipe(ResourceLocation pId, CraftingBookCategory pCategory) {
        super(pId, pCategory);
    }

    @Override
    public boolean matches(CraftingContainer craftingContainer, Level level) {
        boolean hasSoul = false;
        boolean isVerticalColumn = false;

        for (int i = 0; i < craftingContainer.getContainerSize(); i++) {
            ItemStack item = craftingContainer.getItem(i);
            if (item.isEmpty()) continue;
            if (item.getItem() == ModItemInit.SACRIFICIALBLADE.get()) {
                if (item.hasTag() && item.getTag().contains(STORED_SOUL)) {
                    hasSoul = true;
                }
            }
        }

        int width = craftingContainer.getWidth();
        int height = craftingContainer.getHeight();

        for (int col = 0; col < width; col++) {
            boolean columnFull = true;
            for (int row = 0; row < height; row++) {
                int i = col + row * width;
                ItemStack item = craftingContainer.getItem(i);
                if (item.isEmpty() || item.getItem() != Items.WAXED_WEATHERED_CUT_COPPER) {
                    columnFull = false;
                    break;
                }
            }
            if (columnFull) {
                isVerticalColumn = true;
                break;
            }
        }

        return isVerticalColumn && hasSoul;
    }

    @Override
    public ItemStack assemble(CraftingContainer craftingContainer, RegistryAccess registryAccess) {
        return new ItemStack(ModBlockInit.WAXED_WEATHERED_CUT_COPPER_VERTICAL_SLAB.get());
    }

    @Override
    public NonNullList<ItemStack> getRemainingItems(CraftingContainer pContainer) {
        NonNullList<ItemStack> remaining = NonNullList.withSize(pContainer.getContainerSize(), ItemStack.EMPTY);

        for (int i = 0; i < pContainer.getContainerSize(); i++) {
            ItemStack stack = pContainer.getItem(i);
            if (stack.getItem() == ModItemInit.SACRIFICIALBLADE.get()) {
                remaining.set(i, new ItemStack(ModItemInit.SACRIFICIALBLADE.get()));
            }
        }
        return remaining;
    }

    @Override
    public boolean canCraftInDimensions(int i, int i1) {
        return i * i1 >= 4;
    }

    @Override
    public RecipeSerializer<?> getSerializer() {
        return ModRecipesInit.WWCCV_SLAB.get();
    }
}
