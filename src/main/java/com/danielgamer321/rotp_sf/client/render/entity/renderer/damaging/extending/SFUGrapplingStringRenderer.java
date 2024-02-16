package com.danielgamer321.rotp_sf.client.render.entity.renderer.damaging.extending;

import com.danielgamer321.rotp_sf.client.render.entity.model.ownerbound.repeating.SFStringModel;
import com.danielgamer321.rotp_sf.entity.damaging.projectile.ownerbound.SFUGrapplingStringEntity;
import net.minecraft.client.renderer.entity.EntityRendererManager;

public class SFUGrapplingStringRenderer extends SFStringAbstractRenderer<SFUGrapplingStringEntity> {

    public SFUGrapplingStringRenderer(EntityRendererManager renderManager) {
        super(renderManager, new SFStringModel<SFUGrapplingStringEntity>());
    }
}
