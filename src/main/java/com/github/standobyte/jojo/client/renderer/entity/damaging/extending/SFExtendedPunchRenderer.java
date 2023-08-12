package com.github.standobyte.jojo.client.renderer.entity.damaging.extending;

import com.github.standobyte.jojo.JojoMod;
import com.github.standobyte.jojo.client.model.entity.ownerbound.repeating.SFExtendedPunchModel;
import com.github.standobyte.jojo.entity.damaging.projectile.ownerbound.SFExtendedPunchEntity;

import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.util.ResourceLocation;

public class SFExtendedPunchRenderer extends ExtendingEntityRenderer<SFExtendedPunchEntity, SFExtendedPunchModel> {

    public SFExtendedPunchRenderer(EntityRendererManager renderManager) {
        super(renderManager, new SFExtendedPunchModel(), new ResourceLocation(JojoMod.MOD_ID, "textures/entity/projectiles/sf_extended_punch.png"));
    }

}
