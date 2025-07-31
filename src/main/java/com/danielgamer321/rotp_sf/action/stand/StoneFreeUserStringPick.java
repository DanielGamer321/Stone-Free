package com.danielgamer321.rotp_sf.action.stand;

import com.danielgamer321.rotp_sf.entity.damaging.projectile.ownerbound.SFUStringSpikeEntity;
import com.github.standobyte.jojo.action.ActionTarget;
import com.github.standobyte.jojo.power.impl.stand.IStandPower;

import net.minecraft.entity.LivingEntity;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;

import java.util.List;

public class StoneFreeUserStringPick extends StringAction {

    public StoneFreeUserStringPick(Builder builder) {
        super(builder);
    }

    @Override
    public void perform(World world, LivingEntity user, IStandPower power, ActionTarget target) {
        super.perform(world, user, power, target);
        if (!world.isClientSide()) {
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
