package com.danielgamer321.rotp_sf.util;

import com.danielgamer321.rotp_sf.RotpStoneFreeAddon;
import com.danielgamer321.rotp_sf.network.PacketManager;

import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;

@EventBusSubscriber(modid = RotpStoneFreeAddon.MOD_ID, bus = EventBusSubscriber.Bus.MOD)
public class CommonSetup {
    
    @SubscribeEvent
    public static void onFMLCommonSetup(FMLCommonSetupEvent event) {
        event.enqueueWork(() -> {
            ForgeBusEventSubscriber.registerCapabilities();
            PacketManager.init();
        });
    }

}
