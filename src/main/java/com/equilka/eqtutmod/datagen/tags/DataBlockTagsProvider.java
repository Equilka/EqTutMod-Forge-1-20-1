package com.equilka.eqtutmod.datagen.tags;

import com.equilka.eqtutmod.EqTutMod;
import com.equilka.eqtutmod.init.ModBlockInit;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.tags.BlockTags;
import net.minecraftforge.common.Tags;
import net.minecraftforge.common.data.BlockTagsProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

public class DataBlockTagsProvider extends BlockTagsProvider {
    public DataBlockTagsProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider, @Nullable ExistingFileHelper existingFileHelper) {
        super(output, lookupProvider, EqTutMod.MODID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider pProvider) {
        this.tag(Tags.Blocks.STONE)
                .add(
                        ModBlockInit.BOULDER_BLOCK.get(),
                        ModBlockInit.MEAT_BLOCK.get(),
                        ModBlockInit.MOJ_BLOCK.get(),
                        ModBlockInit.DEMONCORE_BLOCK.get());

        this.tag(Tags.Blocks.GLASS)
                .add(
                        ModBlockInit.SKY_BLOCK.get());

        this.tag(BlockTags.MINEABLE_WITH_PICKAXE)
                .add(
                        ModBlockInit.BOULDER_BLOCK.get(),
                        ModBlockInit.MEAT_BLOCK.get(),
                        ModBlockInit.MOJ_BLOCK.get(),
                        ModBlockInit.DEMONCORE_BLOCK.get(),
                        ModBlockInit.SKY_BLOCK.get(),
                        ModBlockInit.WAXED_WEATHERED_CUT_COPPER_VERTICAL_SLAB.get());



        this.tag(Tags.Blocks.NEEDS_WOOD_TOOL)
                .add(
                        ModBlockInit.BOULDER_BLOCK.get(),
                        ModBlockInit.WAXED_WEATHERED_CUT_COPPER_VERTICAL_SLAB.get(),
                        ModBlockInit.MEAT_BLOCK.get());

        this.tag(BlockTags.NEEDS_DIAMOND_TOOL)
                .add(
                        ModBlockInit.SKY_BLOCK.get(),
                        ModBlockInit.DEMONCORE_BLOCK.get(),
                        ModBlockInit.MOJ_BLOCK.get());
    }
}
