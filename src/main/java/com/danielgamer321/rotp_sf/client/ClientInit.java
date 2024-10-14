package com.danielgamer321.rotp_sf.client;

import com.danielgamer321.rotp_sf.RotpStoneFreeAddon;
import com.danielgamer321.rotp_sf.client.render.entity.layerrenderer.*;
import com.danielgamer321.rotp_sf.client.render.entity.renderer.damaging.extending.*;
import com.danielgamer321.rotp_sf.client.render.entity.renderer.stand.StoneFreeRenderer;
import com.danielgamer321.rotp_sf.client.ui.marker.StoneFreeBarrierDetectionMarker;
import com.danielgamer321.rotp_sf.init.AddonStands;
import com.danielgamer321.rotp_sf.init.InitEntities;
import com.github.standobyte.jojo.client.playeranim.PlayerAnimationHandler;
import com.github.standobyte.jojo.client.ui.marker.MarkerRenderer;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.LivingRenderer;
import net.minecraft.client.renderer.entity.PlayerRenderer;
import net.minecraft.client.renderer.entity.model.BipedModel;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.entity.LivingEntity;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

import java.util.Map;

@EventBusSubscriber(modid = RotpStoneFreeAddon.MOD_ID, bus = EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ClientInit {
    
    @SubscribeEvent
    public static void onFMLClientSetup(FMLClientSetupEvent event) {
        RenderingRegistry.registerEntityRenderingHandler(InitEntities.SF_STRING.get(), SFStringRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(InitEntities.SFU_STRING.get(), SFUStringRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(InitEntities.SFU_STRING_SWEEP.get(), SFUStringSweepRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(InitEntities.SFU_STRING_WHIP.get(), SFUStringWhipRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(InitEntities.SF_EXTENDED_PUNCH.get(), SFExtendedPunchRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(InitEntities.SFU_STRING_SPIKE.get(), SFUStringSpikeRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(InitEntities.SF_GRAPPLING_STRING.get(), SFGrapplingStringRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(InitEntities.SFU_GRAPPLING_STRING.get(), SFUGrapplingStringRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(InitEntities.SFU_BARRIER.get(), SFUBarrierRenderer::new);

        RenderingRegistry.registerEntityRenderingHandler(AddonStands.STONE_FREE.getEntityType(), StoneFreeRenderer::new);

        event.enqueueWork(() -> {
            Minecraft mc = event.getMinecraftSupplier().get();
            
            ClientEventHandler.init(mc);

            Map<String, PlayerRenderer> skinMap = mc.getEntityRenderDispatcher().getSkinMap();
            addLayers(skinMap.get("default"), false);
            addLayers(skinMap.get("slim"), true);
            mc.getEntityRenderDispatcher().renderers.values().forEach(ClientInit::addLayersToEntities);

            MarkerRenderer.Handler.addRenderer(new StoneFreeBarrierDetectionMarker(mc));

            PlayerAnimationHandler.initAnimator();
        });
    }

    private static void addLayers(PlayerRenderer renderer, boolean slim) {
        addLivingLayers(renderer);
        addBipedLayers(renderer);
    }

    private static <T extends LivingEntity, M extends BipedModel<T>> void addLayersToEntities(EntityRenderer<?> renderer) {
        if (renderer instanceof LivingRenderer<?, ?>) {
            addLivingLayers((LivingRenderer<T, ?>) renderer);
            if (((LivingRenderer<?, ?>) renderer).getModel() instanceof BipedModel<?>) {
                addBipedLayers((LivingRenderer<T, M>) renderer);
            }
        }
    }

    private static <T extends LivingEntity, M extends EntityModel<T>> void addLivingLayers(LivingRenderer<T, M> renderer) {
        renderer.addLayer(new StringDecompositionLayer<>(renderer));
        renderer.addLayer(new MobiusStripLayer<>(renderer));
    }

    private static <T extends LivingEntity, M extends BipedModel<T>> void addBipedLayers(LivingRenderer<T, M> renderer) {
    }
}
