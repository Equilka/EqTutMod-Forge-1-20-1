package com.equilka.eqtutmod.init;

import com.equilka.eqtutmod.EqTutMod;
import com.equilka.eqtutmod.world.entity.EarthCrystalEntity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;


public class ModEntityInit {
    public static final DeferredRegister<EntityType<?>> REGISTER = DeferredRegister.create(ForgeRegistries.ENTITY_TYPES, EqTutMod.MODID);

    public static final RegistryObject<EntityType<EarthCrystalEntity>> EARTH_CRYSTAL = REGISTER.register("earth_crystal_entity",
            () -> EntityType.Builder.<EarthCrystalEntity>of(EarthCrystalEntity::new, MobCategory.MISC)
                    .sized(0.3f, 0.3f)
                    .fireImmune()
                    .build("earth_crystal_entity"));
}
