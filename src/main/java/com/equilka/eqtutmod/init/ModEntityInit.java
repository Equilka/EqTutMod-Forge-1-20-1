package com.equilka.eqtutmod.init;

import com.equilka.eqtutmod.EqTutMod;
import com.equilka.eqtutmod.blocks.*;
import com.equilka.eqtutmod.world.entity.EarthCrystal;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Supplier;


public class ModEntityInit {
    public static final DeferredRegister<EntityType<?>> REGISTER = DeferredRegister.create(ForgeRegistries.ENTITY_TYPES, EqTutMod.MODID);

    public static final RegistryObject<EntityType<Entity>> EARTH_CRYSTAL = REGISTER.register("earth_crystal",
            () -> EntityType.Builder.of(
                    EarthCrystal::new,
                    MobCategory.MISC
            ).build(null));
}
