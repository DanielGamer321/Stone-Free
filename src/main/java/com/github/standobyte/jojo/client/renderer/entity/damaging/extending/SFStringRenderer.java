package com.github.standobyte.jojo.client.renderer.entity.damaging.extending;

import com.github.standobyte.jojo.client.model.entity.ownerbound.repeating.SFStringModel;
import com.github.standobyte.jojo.entity.damaging.projectile.ownerbound.SFStringEntity;

import net.minecraft.client.renderer.entity.EntityRendererManager;

public class SFStringRenderer extends SFStringAbstractRenderer<SFStringEntity> {

    public SFStringRenderer(EntityRendererManager renderManager) {
        super(renderManager, new SFStringModel<SFStringEntity>());
    }
}
