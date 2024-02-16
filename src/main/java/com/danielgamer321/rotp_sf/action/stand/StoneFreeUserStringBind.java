package com.danielgamer321.rotp_sf.action.stand;

import com.danielgamer321.rotp_sf.capability.entity.PlayerUtilCapProvider;
import com.danielgamer321.rotp_sf.entity.damaging.projectile.ownerbound.SFUStringEntity;
import com.danielgamer321.rotp_sf.init.InitSounds;
import com.danielgamer321.rotp_sf.power.impl.stand.type.StoneFreeStandType;
import com.github.standobyte.jojo.action.stand.StandAction;
import com.github.standobyte.jojo.action.ActionConditionResult;
import com.github.standobyte.jojo.action.ActionTarget;
import com.github.standobyte.jojo.entity.stand.StandEntity;
import com.github.standobyte.jojo.power.impl.stand.IStandPower;

import com.github.standobyte.jojo.power.impl.stand.StandUtil;
import com.github.standobyte.jojo.util.mc.MCUtil;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.SoundCategory;
import net.minecraft.world.World;

import java.util.List;
import java.util.Optional;

public class StoneFreeUserStringBind extends StandAction {

    public StoneFreeUserStringBind(StandAction.Builder builder) {
        super(builder);
    }

    @Override
    protected ActionConditionResult checkSpecificConditions(LivingEntity user, IStandPower power, ActionTarget target) {
        int string = ((StoneFreeStandType<?>) power.getType()).getPlacedBarriersCount(power);
        boolean userString = user.getCapability(PlayerUtilCapProvider.CAPABILITY).map(cap -> cap.userString(user)).orElse(null);
        if (string >= 100) {
            return conditionMessage("string_limit");
        }
        if (!userString) {
            return ActionConditionResult.NEGATIVE;
        }
        return ActionConditionResult.POSITIVE;
    }

    @Override
    public void perform(World world, LivingEntity user, IStandPower power, ActionTarget target) {
        if (!world.isClientSide()) {
            boolean isBinding = true;
            MCUtil.playSound(world, null, user, InitSounds.STONE_FREE_STRING.get(),
                    SoundCategory.AMBIENT, 1.0F, 1.0F, StandUtil::playerCanHearStands);
            addProjectile(world, power, user, 0, 0, isBinding);
        }
    }

    private void addProjectile(World world, IStandPower standPower, LivingEntity user, float yRotDelta, float xRotDelta, boolean isBinding) {
        SFUStringEntity string = new SFUStringEntity(world, user, standPower, yRotDelta, xRotDelta, isBinding);
        string.setLifeSpan(24);
        world.addFreshEntity(string);
    }

    public static boolean getLandedString(LivingEntity user) {
        List<SFUStringEntity> stringLaunched = user.level.getEntitiesOfClass(SFUStringEntity.class,
                user.getBoundingBox().inflate(16), string -> user.is(string.getOwner()));
        return !stringLaunched.isEmpty() ? true : false;
    }
}
