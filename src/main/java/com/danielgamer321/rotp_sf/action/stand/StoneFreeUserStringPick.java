package com.danielgamer321.rotp_sf.action.stand;

import com.danielgamer321.rotp_sf.capability.entity.PlayerUtilCapProvider;
import com.danielgamer321.rotp_sf.entity.damaging.projectile.ownerbound.SFUStringSpikeEntity;
import com.danielgamer321.rotp_sf.init.InitSounds;
import com.danielgamer321.rotp_sf.power.impl.stand.type.StoneFreeStandType;
import com.github.standobyte.jojo.action.ActionConditionResult;
import com.github.standobyte.jojo.action.ActionTarget;
import com.github.standobyte.jojo.action.stand.StandAction;
import com.github.standobyte.jojo.power.impl.stand.IStandPower;

import com.github.standobyte.jojo.power.impl.stand.StandUtil;
import com.github.standobyte.jojo.util.mc.MCUtil;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;

import java.util.List;

public class StoneFreeUserStringPick extends StandAction {

    public StoneFreeUserStringPick(Builder builder) {
        super(builder);
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
        return super.checkSpecificConditions(user, power, target);
    }

    @Override
    public void perform(World world, LivingEntity user, IStandPower power, ActionTarget target) {
        if (!world.isClientSide()) {
            MCUtil.playSound(world, null, user, InitSounds.STONE_FREE_STRING.get(),
                    SoundCategory.AMBIENT, 1.0F, 1.0F, StandUtil::playerCanHearStands);
            SFUStringSpikeEntity string = new SFUStringSpikeEntity(world, user, power);
            world.addFreshEntity(string);
        }
    }

    @Override
    protected boolean autoSummonStand(IStandPower power) {
        LivingEntity user = power.getUser();
        return super.autoSummonStand(power) || user.getHealth() <= 5;
    }

    @Override
    public int getHoldDurationToFire(IStandPower power) {
        int hold = super.getHoldDurationToFire(power);
        return shortedHoldDuration(power, super.getHoldDurationToFire(power));
    }

    private int shortedHoldDuration(IStandPower power, int ticks) {
        LivingEntity user = power.getUser();
        int hold = 40;
        if (!power.isUserCreative() && power.getUser() != null) {
            hold = MathHelper.ceil((float) hold / (8 * (user.getMaxHealth() / user.getHealth())));
        }
        return ticks > 0 && user.getHealth() < user.getMaxHealth() ? hold : ticks;
    }

    public static boolean getLandedString(LivingEntity user) {
        List<SFUStringSpikeEntity> stringLaunched = user.level.getEntitiesOfClass(SFUStringSpikeEntity.class,
                user.getBoundingBox().inflate(16), string -> user.is(string.getOwner()));
        return !stringLaunched.isEmpty() ? true : false;
    }
}
