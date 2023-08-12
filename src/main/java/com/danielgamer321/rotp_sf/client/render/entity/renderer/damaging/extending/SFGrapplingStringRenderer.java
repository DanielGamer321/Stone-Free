package com.danielgamer321.rotp_sf.client.render.entity.renderer.damaging.extending;

import com.danielgamer321.rotp_sf.client.render.entity.model.ownerbound.repeating.SFStringModel;
import com.danielgamer321.rotp_sf.entity.damaging.projectile.ownerbound.SFGrapplingStringEntity;

import net.minecraft.client.renderer.entity.EntityRendererManager;

public class SFGrapplingStringRenderer extends SFStringAbstractRenderer<SFGrapplingStringEntity> {

    public SFGrapplingStringRenderer(EntityRendererManager renderManager) {
        super(renderManager, new SFStringModel<SFGrapplingStringEntity>());
    }
}
