package com.equilka.eqtutmod;

import com.equilka.eqtutmod.init.ModBlockInit;
import com.equilka.eqtutmod.init.ModItemInit;
import com.equilka.eqtutmod.init.ModTabInit;
import com.mojang.logging.LogUtils;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.slf4j.Logger;

@Mod("eqtutmod")
public class EqTutMod
{
    public static final String MODID = "eqtutmod";
    private static final Logger LOGGER = LogUtils.getLogger();

    public EqTutMod(FMLJavaModLoadingContext context)
    {
        IEventBus modEventBus = context.getModEventBus();

        ModBlockInit.REGISTER.register(modEventBus);
        ModItemInit.REGISTER.register(modEventBus);
        ModTabInit.REGISTER.register(modEventBus);

        MinecraftForge.EVENT_BUS.register(this);
    }
}
