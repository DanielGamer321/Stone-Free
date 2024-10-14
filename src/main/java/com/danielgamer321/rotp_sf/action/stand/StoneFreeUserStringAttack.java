package com.danielgamer321.rotp_sf.action.stand;

import com.danielgamer321.rotp_sf.capability.entity.LivingUtilCapProvider;
import com.danielgamer321.rotp_sf.entity.damaging.projectile.ownerbound.SFUStringEntity;
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

import static com.danielgamer321.rotp_sf.action.stand.StoneFreeUserBarrier.MaxVarietyOfBarriers;

public class StoneFreeUserStringAttack extends StandAction {

    public StoneFreeUserStringAttack(StandAction.Builder builder) {
        super(builder);
    }

    @Override
    protected ActionConditionResult checkSpecificConditions(LivingEntity user, IStandPower power, ActionTarget target) {
        if (((StoneFreeStandType<?>) power.getType()).getPlacedBarriersCount(power) >= MaxVarietyOfBarriers(user)) {
            return conditionMessage("string_limit");
        }
        if (!user.getCapability(LivingUtilCapProvider.CAPABILITY).map(cap -> cap.userString(user)).orElse(false)) {
            return ActionConditionResult.NEGATIVE;
        }
        return super.checkSpecificConditions(user, power, target);
    }

    @Override
    public void perform(World world, LivingEntity user, IStandPower power, ActionTarget target) {
        if (!world.isClientSide()) {
            boolean isBinding = false;
//            float intensity = Math.min(1.0F, 4F);
//            float volume =  intensity * 2 * 1;
//            world.playSound(null, user, InitSounds.STONE_FREE_STRING.get(), SoundCategory.AMBIENT, Math.min(volume, 1.0F), 1.0F + (world.random.nextFloat() - 0.5F) * 0.15F);
            MCUtil.playSound(world, null, user, InitSounds.STONE_FREE_STRING.get(),
                    SoundCategory.AMBIENT, 1.0F, 1.0F, StandUtil::playerCanHearStands);
            addProjectile(world, power, user, 0, 0, isBinding);
        }
    }

    private void addProjectile(World world, IStandPower standPower, LivingEntity user, float yRotDelta, float xRotDelta, boolean isBinding) {
        SFUStringEntity string = new SFUStringEntity(world, user, standPower, yRotDelta, xRotDelta, isBinding);
        string.setLifeSpan(16);
        world.addFreshEntity(string);
    }

    public static boolean getLandedString(LivingEntity user) {
        List<SFUStringEntity> stringLaunched = user.level.getEntitiesOfClass(SFUStringEntity.class,
                user.getBoundingBox().inflate(16), string -> user.is(string.getOwner()));
        return !stringLaunched.isEmpty() ? true : false;
    }
}
