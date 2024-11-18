package com.danielgamer321.rotp_sf.client.render.entity.renderer.damaging.extending;

import com.danielgamer321.rotp_sf.RotpStoneFreeAddon;
import com.danielgamer321.rotp_sf.client.render.entity.model.ownerbound.repeating.SFStringModel;
import com.danielgamer321.rotp_sf.init.AddonStands;
import com.github.standobyte.jojo.client.render.entity.renderer.damaging.extending.ExtendingEntityRenderer;
import com.github.standobyte.jojo.entity.damaging.projectile.ownerbound.OwnerBoundProjectileEntity;
import com.github.standobyte.jojo.client.standskin.StandSkinsManager;
import com.github.standobyte.jojo.entity.stand.StandEntity;
import com.github.standobyte.jojo.power.impl.stand.IStandPower;

import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.ResourceLocation;

public abstract class SFStringAbstractRenderer<T extends OwnerBoundProjectileEntity> extends ExtendingEntityRenderer<T, SFStringModel<T>> {
    private static final ResourceLocation STRINGS = new ResourceLocation(RotpStoneFreeAddon.MOD_ID, "textures/entity/projectiles/sf_strings.png");
    private static ResourceLocation SKIN = STRINGS;
    
    public SFStringAbstractRenderer(EntityRendererManager renderManager, SFStringModel<T> model) {
        super(renderManager, model, SKIN);
    }

    @Override
    public ResourceLocation getTextureLocation(T entity) {
        if(entity.getOwner() != null){
            LivingEntity owner = entity.getOwner();
            if(owner instanceof StandEntity) owner = ((StandEntity) owner).getUser();
            IStandPower.getStandPowerOptional(owner).ifPresent(power -> {
                if(power.getType() == AddonStands.STONE_FREE.getStandType()){
                    SKIN = StandSkinsManager.getInstance() != null? (StandSkinsManager.getInstance().getRemappedResPath(manager -> manager
                            .getStandSkin(power.getStandInstance().get()), STRINGS)): STRINGS;
                }
                else SKIN = STRINGS;
            });
        }
        return SKIN;
    }
}
