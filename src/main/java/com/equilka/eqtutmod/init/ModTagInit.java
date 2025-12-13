package com.equilka.eqtutmod.init;

import com.equilka.eqtutmod.EqTutMod;
import net.minecraft.core.registries.Registries;
import net.minecraft.nbt.Tag;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;
import org.stringtemplate.v4.ST;

import javax.swing.text.html.HTML;

public class ModTagInit {
    public static class Blocks {
        public static final TagKey<Block> CURSED = create("cursed");

        private static TagKey<Block> create(String name) {
            return BlockTags.create(new ResourceLocation(EqTutMod.MODID, name));
        }
    }

    public static class Items {
        public static final TagKey<Item> DAGGERS = create("daggers");

        private static TagKey<Item> create(String name) {
            return ItemTags.create(new ResourceLocation(EqTutMod.MODID, name));
        }
    }
}
