package com.danielgamer321.rotp_sf.client.render.entity.renderer.damaging.extending;

import com.danielgamer321.rotp_sf.RotpStoneFreeAddon;
import com.danielgamer321.rotp_sf.client.render.entity.model.ownerbound.repeating.SFUStringSpikeModel;
import com.danielgamer321.rotp_sf.entity.damaging.projectile.ownerbound.SFUStringSpikeEntity;
import com.github.standobyte.jojo.client.render.entity.renderer.damaging.extending.ExtendingEntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.util.ResourceLocation;

public class SFUStringSpikeRenderer extends ExtendingEntityRenderer<SFUStringSpikeEntity, SFUStringSpikeModel> {

    public SFUStringSpikeRenderer(EntityRendererManager renderManager) {
        super(renderManager, new SFUStringSpikeModel(), new ResourceLocation(RotpStoneFreeAddon.MOD_ID, "textures/entity/projectiles/sf_strings.png"));
    }

}
