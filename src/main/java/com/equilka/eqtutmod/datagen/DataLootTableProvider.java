package com.equilka.eqtutmod.datagen;

import com.equilka.eqtutmod.datagen.loottables.LootTableBlock;
import net.minecraft.data.PackOutput;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.data.loot.LootTableProvider;
import net.minecraft.data.loot.LootTableSubProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;

import java.util.List;
import java.util.Set;
import java.util.function.Supplier;

public class DataLootTableProvider extends LootTableProvider {
    public DataLootTableProvider(PackOutput pOutput) {
        super(pOutput,
                Set.of(),
                List.of(
                        new SubProviderEntry(LootTableBlock::new, LootContextParamSets.BLOCK)
                ));
    }
}

