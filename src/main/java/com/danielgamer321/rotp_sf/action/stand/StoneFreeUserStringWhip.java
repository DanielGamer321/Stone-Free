package com.danielgamer321.rotp_sf.action.stand;

import com.danielgamer321.rotp_sf.entity.damaging.projectile.ownerbound.SFUStringWhipEntity;
import com.github.standobyte.jojo.action.ActionTarget;
import com.github.standobyte.jojo.power.impl.stand.IStandPower;

import net.minecraft.entity.LivingEntity;
import net.minecraft.world.World;

import java.util.List;

public class StoneFreeUserStringWhip extends StringAction {

    public StoneFreeUserStringWhip(Builder builder) {
        super(builder);
    }

    @Override
    protected void perform(World world, LivingEntity user, IStandPower power, ActionTarget target) {
        super.perform(world, user, power, target);
        if (!world.isClientSide()) {
            SFUStringWhipEntity string = new SFUStringWhipEntity(world, user, power);
            world.addFreshEntity(string);
        }
    }

    public static boolean getLandedString(LivingEntity user) {
        List<SFUStringWhipEntity> stringLaunched = user.level.getEntitiesOfClass(SFUStringWhipEntity.class,
                user.getBoundingBox().inflate(16), string -> user.is(string.getOwner()));
        return !stringLaunched.isEmpty() ? true : false;
    }
}
