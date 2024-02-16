package com.danielgamer321.rotp_sf.util;

import com.danielgamer321.rotp_sf.RotpStoneFreeAddon;
import com.danielgamer321.rotp_sf.capability.entity.PlayerUtilCap;
import com.danielgamer321.rotp_sf.capability.entity.PlayerUtilCapProvider;
import com.danielgamer321.rotp_sf.capability.entity.PlayerUtilCapStorage;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;

@EventBusSubscriber(modid = RotpStoneFreeAddon.MOD_ID)
public class ForgeBusEventSubscriber {
    private static final ResourceLocation PLAYER_UTIL_CAP = new ResourceLocation(RotpStoneFreeAddon.MOD_ID, "player_util");

    
    @SubscribeEvent
    public static void onAttachCapabilitiesEntity(AttachCapabilitiesEvent<Entity> event) {
        Entity entity = event.getObject();
        if (entity instanceof PlayerEntity) {
            PlayerEntity player = (PlayerEntity) event.getObject();
            event.addCapability(PLAYER_UTIL_CAP, new PlayerUtilCapProvider(player));
        }
    }
    
    public static void registerCapabilities() {
        CapabilityManager.INSTANCE.register(PlayerUtilCap.class, new PlayerUtilCapStorage(), () -> new PlayerUtilCap(null));
    }
}
