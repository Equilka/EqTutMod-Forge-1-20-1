package com.equilka.eqtutmod.init;

import com.equilka.eqtutmod.EqTutMod;
import com.equilka.eqtutmod.client.gui.screens.SimpleXpOrbsWellBlockScreen;
import com.equilka.eqtutmod.client.gui.screens.UpgradedXpOrbsWellBlockScreen;
import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

@Mod.EventBusSubscriber(modid = EqTutMod.MODID, value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModScreenInit {

    @SubscribeEvent
    public static void onClientSetup(FMLClientSetupEvent event) {
        MenuScreens.register(ModMenuTypeInit.SIMPLE_XP_ORBS_WELL_BLOCK_MENU.get(), SimpleXpOrbsWellBlockScreen::new);
        MenuScreens.register(ModMenuTypeInit.UPGRADED_XP_ORBS_WELL_BLOCK_MENU.get(), UpgradedXpOrbsWellBlockScreen::new);
    }
}
