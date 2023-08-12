package com.danielgamer321.rotp_sf.client;

import com.danielgamer321.rotp_sf.RotpStoneFreeAddon;
import com.danielgamer321.rotp_sf.client.render.entity.renderer.damaging.extending.SFBarrierRenderer;
import com.danielgamer321.rotp_sf.client.render.entity.renderer.damaging.extending.SFExtendedPunchRenderer;
import com.danielgamer321.rotp_sf.client.render.entity.renderer.damaging.extending.SFGrapplingStringRenderer;
import com.danielgamer321.rotp_sf.client.render.entity.renderer.damaging.extending.SFStringRenderer;
import com.danielgamer321.rotp_sf.client.render.entity.renderer.stand.StoneFreeRenderer;
import com.danielgamer321.rotp_sf.init.AddonStands;
import com.danielgamer321.rotp_sf.init.InitEntities;

import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

@EventBusSubscriber(modid = RotpStoneFreeAddon.MOD_ID, bus = EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ClientInit {
    
    @SubscribeEvent
    public static void onFMLClientSetup(FMLClientSetupEvent event) {
        RenderingRegistry.registerEntityRenderingHandler(InitEntities.SF_EXTENDED_PUNCH.get(), SFExtendedPunchRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(InitEntities.SF_STRING.get(), SFStringRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(InitEntities.SF_GRAPPLING_STRING.get(), SFGrapplingStringRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(InitEntities.SF_BARRIER.get(), SFBarrierRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(AddonStands.STONE_FREE.getEntityType(), StoneFreeRenderer::new);
    }
}
