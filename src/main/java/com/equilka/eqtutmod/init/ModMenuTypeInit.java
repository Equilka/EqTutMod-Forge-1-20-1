package com.equilka.eqtutmod.init;

import com.equilka.eqtutmod.EqTutMod;
import com.equilka.eqtutmod.world.inventory.SimpleXpOrbsWellBlockMenu;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.MenuType;
import net.minecraftforge.common.extensions.IForgeMenuType;
import net.minecraftforge.network.IContainerFactory;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;


public class ModMenuTypeInit {
    public static final DeferredRegister<MenuType<?>> REGISTER = DeferredRegister.create(ForgeRegistries.MENU_TYPES, EqTutMod.MODID);

    public static final RegistryObject<MenuType<SimpleXpOrbsWellBlockMenu>> SIMPLE_XP_ORBS_WELL_BLOCK_MENU = REGISTER.register("simple_xp_orbs_well_block_menu",
            () -> IForgeMenuType.create(SimpleXpOrbsWellBlockMenu::new));
}
