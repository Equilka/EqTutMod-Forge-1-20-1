package com.equilka.eqtutmod.init;

import com.equilka.eqtutmod.EqTutMod;
import com.equilka.eqtutmod.items.*;
import net.minecraft.world.item.Item;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModItemInit {
    public static final DeferredRegister<Item> REGISTER = DeferredRegister.create(ForgeRegistries.ITEMS, EqTutMod.MODID);

    public static final RegistryObject<Item> SCREWDRIVER = REGISTER.register("screwdriver_item", ScrewdriverItem::new);
    public static final RegistryObject<Item> PLASTER = REGISTER.register("plaster_item", PlasterItem::new);
    public static final RegistryObject<Item> BADAPPLE = REGISTER.register("bad_apple_item", BadAppleItem::new);
    public static final RegistryObject<Item> CURSEDCORE = REGISTER.register("cursed_core_item", CursedCoreItem::new);
    public static final RegistryObject<Item> SACRIFICIALBLADE = REGISTER.register("sacrificial_blade_item", SacrificialBladeItem::new);
}
