package com.danielgamer321.rotp_sf.action.stand;

import com.danielgamer321.rotp_sf.entity.damaging.projectile.ownerbound.SFUStringEntity;
import com.github.standobyte.jojo.action.ActionTarget;
import com.github.standobyte.jojo.power.impl.stand.IStandPower;

import net.minecraft.entity.LivingEntity;
import net.minecraft.world.World;

import java.util.List;

public class StoneFreeUserStringAttack extends StringAction {

    public StoneFreeUserStringAttack(Builder builder) {
        super(builder);
    }

    @Override
    public void perform(World world, LivingEntity user, IStandPower power, ActionTarget target) {
        super.perform(world, user, power, target);
        if (!world.isClientSide()) {
            SFUStringEntity string = new SFUStringEntity(world, user, power, 0, 0, false);
            string.setLifeSpan(16);
            world.addFreshEntity(string);
        }
    }

    public static boolean getLandedString(LivingEntity user) {
        List<SFUStringEntity> stringLaunched = user.level.getEntitiesOfClass(SFUStringEntity.class,
                user.getBoundingBox().inflate(16), string -> user.is(string.getOwner()));
        return !stringLaunched.isEmpty() ? true : false;
    }
}
