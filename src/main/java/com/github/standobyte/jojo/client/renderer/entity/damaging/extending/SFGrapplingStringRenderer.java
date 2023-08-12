package com.github.standobyte.jojo.client.renderer.entity.damaging.extending;

import com.github.standobyte.jojo.client.model.entity.ownerbound.repeating.SFStringModel;
import com.github.standobyte.jojo.entity.damaging.projectile.ownerbound.SFGrapplingStringEntity;

import net.minecraft.client.renderer.entity.EntityRendererManager;

public class SFGrapplingStringRenderer extends SFStringAbstractRenderer<SFGrapplingStringEntity> {

    public SFGrapplingStringRenderer(EntityRendererManager renderManager) {
        super(renderManager, new SFStringModel<SFGrapplingStringEntity>());
    }
}
