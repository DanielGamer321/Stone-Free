package com.github.standobyte.jojo.client.renderer.entity.damaging.extending;

import com.github.standobyte.jojo.client.model.entity.ownerbound.repeating.SFStringModel;
import com.github.standobyte.jojo.entity.damaging.projectile.ownerbound.SFBarrierEntity;

import net.minecraft.client.renderer.entity.EntityRendererManager;

public class SFBarrierRenderer extends SFStringAbstractRenderer<SFBarrierEntity> {

    public SFBarrierRenderer(EntityRendererManager renderManager) {
        super(renderManager, new SFStringModel<SFBarrierEntity>());
    }
}
