package com.danielgamer321.rotp_sf.action.stand;

import com.danielgamer321.rotp_sf.capability.entity.LivingUtilCapProvider;
import com.danielgamer321.rotp_sf.entity.damaging.projectile.ownerbound.SFUGrapplingStringEntity;
import com.danielgamer321.rotp_sf.init.InitStands;
import com.github.standobyte.jojo.action.Action;
import com.github.standobyte.jojo.action.ActionTarget;
import com.github.standobyte.jojo.action.stand.StandAction;
import com.github.standobyte.jojo.entity.stand.StandEntity;
import com.github.standobyte.jojo.power.impl.stand.IStandPower;

import net.minecraft.entity.LivingEntity;
import net.minecraft.world.World;

import java.util.List;

public class StoneFreeUserGrapple extends StringAction {

    public StoneFreeUserGrapple(Builder builder) {
        super(builder);
    }

    @Override
    protected Action<IStandPower> replaceAction(IStandPower power, ActionTarget target) {
        LivingEntity user = power.getUser();
        boolean grapple = user.getCapability(LivingUtilCapProvider.CAPABILITY).map(cap -> cap.grapple(user)).orElse(false);
        if (grapple) {
            return InitStands.STONE_FREE_USER_RECOVER_STRING.get();
        }
        if (power.isActive() && power.getStandManifestation() instanceof StandEntity) {
            StandEntity stand = (StandEntity) power.getStandManifestation();
            if (stand.isManuallyControlled()) {
                return InitStands.STONE_FREE_GRAPPLE_ENTITY.get();
            }
        }
        return super.replaceAction(power, target);
    }

    @Override
    public StandAction[] getExtraUnlockable() {
        return new StandAction[] { InitStands.STONE_FREE_GRAPPLE_ENTITY.get(), InitStands.STONE_FREE_USER_RECOVER_STRING.get() };
    }

    @Override
    protected void perform(World world, LivingEntity user, IStandPower power, ActionTarget target) {
        super.perform(world, user, power, target);
        if (!world.isClientSide()) {
            SFUGrapplingStringEntity string = new SFUGrapplingStringEntity(world, user, power);
            if (isShiftVariation()) {
                string.setBindEntities(true);
            }
            world.addFreshEntity(string);
        }
    }

    public static boolean getLandedString(LivingEntity user) {
        List<SFUGrapplingStringEntity> stringLaunched = user.level.getEntitiesOfClass(SFUGrapplingStringEntity.class,
                user.getBoundingBox().inflate(96), string -> user.is(string.getOwner()));
        return !stringLaunched.isEmpty() ? true : false;
    }
}
