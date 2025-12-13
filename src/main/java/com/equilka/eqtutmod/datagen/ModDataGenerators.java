package com.equilka.eqtutmod.datagen;

import com.equilka.eqtutmod.datagen.tags.DataBlockTagsProvider;
import com.equilka.eqtutmod.datagen.tags.DataItemTagsProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.DataProvider;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.TagsProvider;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.data.event.GatherDataEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.concurrent.CompletableFuture;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModDataGenerators {

    @SubscribeEvent
    public static void gatherData(GatherDataEvent event) {
        DataGenerator generator = event.getGenerator();
        PackOutput packOutput = generator.getPackOutput();
        ExistingFileHelper existingFileHelper = event.getExistingFileHelper();
        CompletableFuture<HolderLookup.Provider> lookupProvider = event.getLookupProvider();
        
        generator.addProvider(event.includeServer(), new DataRecipeProvider(packOutput));

        generator.addProvider(event.includeServer(), new DataLootTableProvider(packOutput));

        DataBlockTagsProvider blockGenerator = generator.addProvider(event.includeServer(), new DataBlockTagsProvider(packOutput, lookupProvider, existingFileHelper));
        generator.addProvider(event.includeServer(), new DataItemTagsProvider(packOutput, lookupProvider, blockGenerator.contentsGetter(), existingFileHelper));
    }
}
