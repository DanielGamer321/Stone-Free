package com.danielgamer321.rotp_sf.util;

import com.danielgamer321.rotp_sf.RotpStoneFreeAddon;
import com.danielgamer321.rotp_sf.capability.entity.LivingUtilCap;
import com.danielgamer321.rotp_sf.capability.entity.LivingUtilCapProvider;
import com.danielgamer321.rotp_sf.capability.entity.LivingUtilCapStorage;

import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;

@EventBusSubscriber(modid = RotpStoneFreeAddon.MOD_ID)
public class ForgeBusEventSubscriber {
    private static final ResourceLocation LIVING_UTIL_CAP = new ResourceLocation(RotpStoneFreeAddon.MOD_ID, "living_util");

    
    @SubscribeEvent
    public static void onAttachCapabilitiesEntity(AttachCapabilitiesEvent<Entity> event) {
        Entity entity = event.getObject();
        if (entity instanceof LivingEntity) {
            event.addCapability(LIVING_UTIL_CAP, new LivingUtilCapProvider((LivingEntity) entity));
        }
    }
    
    public static void registerCapabilities() {
        CapabilityManager.INSTANCE.register(LivingUtilCap.class, new LivingUtilCapStorage(), () -> new LivingUtilCap(null));
    }
}
