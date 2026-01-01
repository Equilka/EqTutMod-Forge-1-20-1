package com.equilka.eqtutmod.init;

import com.equilka.eqtutmod.EqTutMod;
import com.equilka.eqtutmod.blocks.entity.SimpleXpOrbsWellBlockEntity;
import com.equilka.eqtutmod.blocks.entity.UpgradedXpOrbsWellBlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModBlockEntityTypeInit {
    public static final DeferredRegister<BlockEntityType<?>> REGISTER = DeferredRegister.create(ForgeRegistries.BLOCK_ENTITY_TYPES, EqTutMod.MODID);
    public static final RegistryObject<BlockEntityType<SimpleXpOrbsWellBlockEntity>> SIMPLE_XP_ORBS_WELL_BLOCK_ENTITY = REGISTER.register(
            "simple_xp_orbs_well_block_entity",
            () -> BlockEntityType.Builder.of(
                    SimpleXpOrbsWellBlockEntity::new,
                    ModBlockInit.SIMPLE_XP_ORBS_WELL_BLOCK.get()
            ).build(null)
    );
    public static final RegistryObject<BlockEntityType<UpgradedXpOrbsWellBlockEntity>> UPGRADED_XP_ORBS_WELL_BLOCK_ENTITY = REGISTER.register(
            "upgraded_xp_orbs_well_block_entity",
            () -> BlockEntityType.Builder.of(
                    UpgradedXpOrbsWellBlockEntity::new,
                    ModBlockInit.UPGRADED_XP_ORBS_WELL_BLOCK.get()
            ).build(null)
    );
}
