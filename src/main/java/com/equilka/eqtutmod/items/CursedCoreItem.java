package com.equilka.eqtutmod.items;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;

public class CursedCoreItem extends Item {
    public CursedCoreItem() {
        super(new Item.Properties().stacksTo(1).rarity(Rarity.EPIC));
    }
}
