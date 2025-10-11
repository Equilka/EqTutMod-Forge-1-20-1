package com.equilka.eqtutmod.datagen.loottables;

import com.equilka.eqtutmod.init.ModBlockInit;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.AlternativesEntry;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.entries.LootPoolEntryContainer;
import net.minecraft.world.level.storage.loot.entries.LootPoolSingletonContainer;
import net.minecraft.world.level.storage.loot.functions.LootItemFunction;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;
import net.minecraftforge.registries.RegistryObject;

import java.util.Set;

public class LootTableBlock extends BlockLootSubProvider {
    public LootTableBlock() {
        super(Set.of(), FeatureFlags.REGISTRY.allFlags());
    }

    @Override
    protected void generate() {
        this.dropSelf(ModBlockInit.MOJ_BLOCK.get());
        this.dropSelf(ModBlockInit.SKY_BLOCK.get());
        this.dropSelf(ModBlockInit.DEMONCORE_BLOCK.get());
        this.dropSelf(ModBlockInit.WAXED_WEATHERED_CUT_COPPER_VERTICAL_SLAB.get());

        this.add(ModBlockInit.MEAT_BLOCK.get(), block -> createSilkTouchDispatchTable(block,
                LootItem.lootTableItem(Items.ROTTEN_FLESH)
                        .apply(SetItemCountFunction.setCount(UniformGenerator.between(3.0f, 8.0f)))));

        this.add(ModBlockInit.BOULDER_BLOCK.get(), block -> createSingleItemTableWithSilkTouch(block, Blocks.COBBLESTONE));

    }


    @Override
    protected Iterable<Block> getKnownBlocks() {
        return ModBlockInit.REGISTER.getEntries().stream().map(RegistryObject::get).toList();
    }
}
