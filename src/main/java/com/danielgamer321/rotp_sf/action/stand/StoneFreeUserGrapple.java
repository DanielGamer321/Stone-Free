package com.danielgamer321.rotp_sf.action.stand;

import com.danielgamer321.rotp_sf.capability.entity.PlayerUtilCapProvider;
import com.danielgamer321.rotp_sf.entity.damaging.projectile.ownerbound.SFUGrapplingStringEntity;
import com.danielgamer321.rotp_sf.init.InitSounds;
import com.danielgamer321.rotp_sf.init.InitStands;
import com.danielgamer321.rotp_sf.power.impl.stand.type.StoneFreeStandType;
import com.github.standobyte.jojo.action.Action;
import com.github.standobyte.jojo.action.ActionConditionResult;
import com.github.standobyte.jojo.action.ActionTarget;
import com.github.standobyte.jojo.action.stand.StandAction;
import com.github.standobyte.jojo.entity.stand.StandEntity;
import com.github.standobyte.jojo.power.impl.stand.IStandPower;

import com.github.standobyte.jojo.power.impl.stand.StandUtil;
import com.github.standobyte.jojo.util.mc.MCUtil;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.SoundCategory;
import net.minecraft.world.World;

import java.util.List;

public class StoneFreeUserGrapple extends StandAction {

    public StoneFreeUserGrapple(Builder builder) {
        super(builder);
    }

    @Override
    protected Action<IStandPower> replaceAction(IStandPower power, ActionTarget target) {
        LivingEntity user = power.getUser();
        boolean grapple = user.getCapability(PlayerUtilCapProvider.CAPABILITY).map(cap -> cap.grapple(user)).orElse(false);
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
    protected ActionConditionResult checkSpecificConditions(LivingEntity user, IStandPower power, ActionTarget target) {
        int strings = ((StoneFreeStandType<?>) power.getType()).getPlacedBarriersCount(power);
        boolean userString = user.getCapability(PlayerUtilCapProvider.CAPABILITY).map(cap -> cap.userString(user)).orElse(false);
        if (strings >= 100) {
            return conditionMessage("string_limit");
        }
        if (!userString) {
            return ActionConditionResult.NEGATIVE;
        }
        return ActionConditionResult.POSITIVE;
    }

    @Override
    protected void perform(World world, LivingEntity user, IStandPower power, ActionTarget target) {
        if (!world.isClientSide()) {
            MCUtil.playSound(world, null, user, InitSounds.STONE_FREE_STRING.get(),
                    SoundCategory.AMBIENT, 1.0F, 1.0F, StandUtil::playerCanHearStands);
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

    private void grappleUnavailable() {
        return;
    }
}
