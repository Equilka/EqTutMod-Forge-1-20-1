package com.equilka.eqtutmod;

import com.equilka.eqtutmod.init.*;
import com.mojang.logging.LogUtils;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.slf4j.Logger;

@Mod(EqTutMod.MODID)
public class EqTutMod
{
    public static final String MODID = "eqtutmod";
    private static final Logger LOGGER = LogUtils.getLogger();

    public EqTutMod() {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        ModBlockInit.REGISTER.register(modEventBus);
        ModItemInit.REGISTER.register(modEventBus);
        ModBlockEntityTypeInit.REGISTER.register(modEventBus);
        ModMenuTypeInit.REGISTER.register(modEventBus);
        ModTabInit.REGISTER.register(modEventBus);
        ModRecipesInit.REGISTER.register(modEventBus);
        ModEntityInit.REGISTER.register(modEventBus);

        MinecraftForge.EVENT_BUS.register(this);
    }
}
