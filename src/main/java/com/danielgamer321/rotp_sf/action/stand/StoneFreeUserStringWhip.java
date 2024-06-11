package com.danielgamer321.rotp_sf.action.stand;

import com.danielgamer321.rotp_sf.capability.entity.PlayerUtilCapProvider;
import com.danielgamer321.rotp_sf.entity.damaging.projectile.ownerbound.SFUStringWhipEntity;
import com.danielgamer321.rotp_sf.init.InitSounds;
import com.danielgamer321.rotp_sf.power.impl.stand.type.StoneFreeStandType;
import com.github.standobyte.jojo.action.stand.StandAction;
import com.github.standobyte.jojo.action.ActionConditionResult;
import com.github.standobyte.jojo.action.ActionTarget;
import com.github.standobyte.jojo.power.impl.stand.IStandPower;

import com.github.standobyte.jojo.power.impl.stand.StandUtil;
import com.github.standobyte.jojo.util.mc.MCUtil;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.SoundCategory;
import net.minecraft.world.World;

import java.util.List;

public class StoneFreeUserStringWhip extends StandAction {

    public StoneFreeUserStringWhip(StandAction.Builder builder) {
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
    protected void perform(World world, LivingEntity user, IStandPower power, ActionTarget target) {
        if (!world.isClientSide()) {
            MCUtil.playSound(world, null, user, InitSounds.STONE_FREE_STRING.get(),
                    SoundCategory.AMBIENT, 1.0F, 1.0F, StandUtil::playerCanHearStands);
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
