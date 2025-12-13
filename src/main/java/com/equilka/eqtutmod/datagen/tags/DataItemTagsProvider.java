package com.equilka.eqtutmod.datagen.tags;

import com.equilka.eqtutmod.EqTutMod;
import com.equilka.eqtutmod.init.ModBlockInit;
import com.equilka.eqtutmod.init.ModItemInit;
import com.equilka.eqtutmod.init.ModTagInit;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.common.Tags;
import net.minecraftforge.common.data.BlockTagsProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

public class DataItemTagsProvider extends ItemTagsProvider {
    public DataItemTagsProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider, CompletableFuture<TagLookup<Block>> completableFuture, @Nullable ExistingFileHelper existingFileHelper) {
        super(output, lookupProvider, completableFuture, EqTutMod.MODID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider pProvider) {
        this.tag(ModTagInit.Items.DAGGERS)
                .add(
                        ModItemInit.SACRIFICIALBLADE.get());

        this.tag(ItemTags.PICKAXES)
                .add(
                        ModItemInit.SCREWDRIVER.get());


    }
}
