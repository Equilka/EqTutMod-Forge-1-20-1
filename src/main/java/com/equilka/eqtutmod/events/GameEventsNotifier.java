package com.equilka.eqtutmod.events;

import net.minecraft.network.chat.Component;
import net.minecraftforge.event.level.BlockEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.List;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.FORGE)
public class GameEventsNotifier {

    @SubscribeEvent
    public static void onBlockBreak(BlockEvent.BreakEvent event){
        event.getPlayer().sendSystemMessage(Component.literal(Component.translatable("system_message.block_break").getString()
                + event.getState().getBlock().getName().getString()));
    }

}
