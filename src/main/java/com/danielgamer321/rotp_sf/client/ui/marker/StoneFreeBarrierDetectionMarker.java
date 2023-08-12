package com.danielgamer321.rotp_sf.client.ui.marker;

import java.util.List;

import com.danielgamer321.rotp_sf.RotpStoneFreeAddon;
import com.danielgamer321.rotp_sf.init.AddonStands;
import com.github.standobyte.jojo.client.ui.marker.MarkerRenderer;

import net.minecraft.client.Minecraft;
import net.minecraft.util.ResourceLocation;

public class StoneFreeBarrierDetectionMarker extends MarkerRenderer {

    public StoneFreeBarrierDetectionMarker(Minecraft mc) {
        super(AddonStands.STONE_FREE.getStandType().getColor(), new ResourceLocation(RotpStoneFreeAddon.MOD_ID, "textures/action/stone_free_barrier.png"), mc);
    }
    
    @Override
    protected boolean shouldRender() {
        return true;
    }

    @Override
    protected void updatePositions(List<MarkerInstance> list, float partialTick) {
//        IStandPower.getStandPowerOptional(mc.player).ifPresent(stand -> {
//            if (stand.getStandManifestation() instanceof StoneFreeEntity) {
//                // FIXME the net isn't synced to client
//                ((StoneFreeEntity) stand.getStandManifestation()).getBarriersNet()
//                .wasRippedAt().forEach(point -> {
//                    // FIXME the constructor isn't visible
//                    list.add(new MarkerInstance(point, false));
//                });
//            }
//        });
    }
}
