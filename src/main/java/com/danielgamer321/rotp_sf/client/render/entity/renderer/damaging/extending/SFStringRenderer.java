package com.danielgamer321.rotp_sf.client.render.entity.renderer.damaging.extending;

import com.danielgamer321.rotp_sf.client.render.entity.model.ownerbound.repeating.SFStringModel;
import com.danielgamer321.rotp_sf.entity.damaging.projectile.ownerbound.SFStringEntity;

import net.minecraft.client.renderer.entity.EntityRendererManager;

public class SFStringRenderer extends SFStringAbstractRenderer<SFStringEntity> {

    public SFStringRenderer(EntityRendererManager renderManager) {
        super(renderManager, new SFStringModel<SFStringEntity>());
    }
}
