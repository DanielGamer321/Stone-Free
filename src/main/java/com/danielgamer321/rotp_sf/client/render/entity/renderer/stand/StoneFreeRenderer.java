package com.danielgamer321.rotp_sf.client.render.entity.renderer.stand;

import com.danielgamer321.rotp_sf.RotpStoneFreeAddon;
import com.danielgamer321.rotp_sf.client.render.entity.model.stand.StoneFreeModel;
import com.danielgamer321.rotp_sf.entity.stand.stands.StoneFreeEntity;
import com.github.standobyte.jojo.client.render.entity.renderer.stand.StandEntityRenderer;

import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.util.ResourceLocation;

public class StoneFreeRenderer extends StandEntityRenderer<StoneFreeEntity, StoneFreeModel> {
    
    public StoneFreeRenderer(EntityRendererManager renderManager) {
        super(renderManager, new StoneFreeModel(), new ResourceLocation(RotpStoneFreeAddon.MOD_ID, "textures/entity/stand/stone_free.png"), 0);
    }
}
