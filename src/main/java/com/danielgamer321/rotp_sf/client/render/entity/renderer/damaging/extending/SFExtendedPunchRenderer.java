package com.danielgamer321.rotp_sf.client.render.entity.renderer.damaging.extending;

import com.danielgamer321.rotp_sf.RotpStoneFreeAddon;
import com.danielgamer321.rotp_sf.client.render.entity.model.ownerbound.repeating.SFExtendedPunchModel;
import com.danielgamer321.rotp_sf.entity.damaging.projectile.ownerbound.SFExtendedPunchEntity;
import com.github.standobyte.jojo.client.render.entity.renderer.damaging.extending.ExtendingEntityRenderer;
import com.github.standobyte.jojo.client.standskin.StandSkinsManager;

import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.util.ResourceLocation;

public class SFExtendedPunchRenderer extends ExtendingEntityRenderer<SFExtendedPunchEntity, SFExtendedPunchModel> {

    public SFExtendedPunchRenderer(EntityRendererManager renderManager) {
        super(renderManager, new SFExtendedPunchModel(), new ResourceLocation(RotpStoneFreeAddon.MOD_ID, "textures/entity/projectiles/sf_strings.png"));
    }

    @Override
    public ResourceLocation getTextureLocation(SFExtendedPunchEntity entity) {
        return StandSkinsManager.getInstance()
                .getRemappedResPath(manager -> manager.getStandSkin(entity.getStandSkin()), texPath);
    }

}
