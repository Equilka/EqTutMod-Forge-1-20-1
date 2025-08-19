package com.equilka.eqtutmod.init;

import com.equilka.eqtutmod.EqTutMod;
import com.equilka.eqtutmod.items.BadAppleItem;
import com.equilka.eqtutmod.items.PlasterItem;
import com.equilka.eqtutmod.items.ScrewdriverItem;
import net.minecraft.world.item.Item;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModItemInit {
    public static final DeferredRegister<Item> REGISTER = DeferredRegister.create(ForgeRegistries.ITEMS, EqTutMod.MODID);

    public static final RegistryObject<Item> SCREWDRIVER = REGISTER.register("screwdriver_item", ScrewdriverItem::new);
    public static final RegistryObject<Item> PLASTER = REGISTER.register("plaster_item", PlasterItem::new);
    public static final RegistryObject<Item> BADAPPLE = REGISTER.register("bad_apple_item", BadAppleItem::new);
}
