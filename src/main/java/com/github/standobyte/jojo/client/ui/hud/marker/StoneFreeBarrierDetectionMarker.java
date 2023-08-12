package com.github.standobyte.jojo.client.ui.hud.marker;

import java.util.List;

import com.github.standobyte.jojo.JojoMod;
import com.github.standobyte.jojo.entity.stand.stands.StoneFreeEntity;
import com.github.standobyte.jojo.init.ModStandTypes;
import com.github.standobyte.jojo.power.stand.IStandPower;

import net.minecraft.client.Minecraft;
import net.minecraft.util.ResourceLocation;

public class StoneFreeBarrierDetectionMarker extends MarkerRenderer {

    public StoneFreeBarrierDetectionMarker(Minecraft mc) {
        super(ModStandTypes.STONE_FREE.get().getColor(), new ResourceLocation(JojoMod.MOD_ID, "textures/action/stone_free_barrier.png"), mc);
    }
    
    @Override
    protected boolean shouldRender() {
        return true;
    }

    @Override
    protected void updatePositions(List<MarkerInstance> list, float partialTick) {
        IStandPower.getStandPowerOptional(mc.player).ifPresent(stand -> {
            if (stand.getStandManifestation() instanceof StoneFreeEntity) {
                // FIXME the net isn't synced to client
                ((StoneFreeEntity) stand.getStandManifestation()).getBarriersNet()
                .wasRippedAt().forEach(point -> {
                    list.add(new MarkerInstance(point, false));
                });
            }
        });
    }
}
