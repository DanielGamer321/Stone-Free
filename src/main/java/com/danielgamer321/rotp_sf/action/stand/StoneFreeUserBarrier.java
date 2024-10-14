package com.danielgamer321.rotp_sf.action.stand;

import com.danielgamer321.rotp_sf.capability.entity.LivingUtilCapProvider;
import com.danielgamer321.rotp_sf.init.InitEffects;
import com.danielgamer321.rotp_sf.power.impl.stand.type.StoneFreeStandType;
import com.github.standobyte.jojo.action.ActionConditionResult;
import com.github.standobyte.jojo.action.ActionTarget;
import com.github.standobyte.jojo.action.stand.StandAction;
import com.github.standobyte.jojo.power.impl.stand.IStandPower;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;

public class StoneFreeUserBarrier extends StandAction {

    public StoneFreeUserBarrier(StandAction.Builder builder) {
        super(builder);
    }
    
    @Override
    protected ActionConditionResult checkSpecificConditions(LivingEntity user, IStandPower power, ActionTarget target) {
        int strings = ((StoneFreeStandType<?>) power.getType()).getPlacedBarriersCount(power);
        boolean canPlaceBarrier = user.getCapability(LivingUtilCapProvider.CAPABILITY).map(cap -> cap.canPlaceBarrier(power)).orElse(false);
        if (!canPlaceBarrier) {
            return conditionMessage("barrier");
        }
        if (strings >= 100) {
            return conditionMessage("string_limit");
        }
        return super.checkSpecificConditions(user, power, target);
    }

    @Override
    public void perform(World world, LivingEntity user, IStandPower power, ActionTarget target) {
        if (!world.isClientSide()) {
            user.getCapability(LivingUtilCapProvider.CAPABILITY).ifPresent(playerUtilCap -> playerUtilCap.attachBarrier(target.getBlockPos(), power));
        }
    }
    
    public static int getMaxBarriersPlaceable(IStandPower power) {
        int level = power.getResolveLevel();
        LivingEntity user = power.getUser();
        return level >= 4 ? MaxVarietyOfBarriers(user) : 25;
    }

    public static int MaxVarietyOfBarriers(LivingEntity user) {
        return user.hasEffect(InitEffects.MOBIUS_STRIP.get())? 90 : 100;
    }
    
    @Override
    public TranslationTextComponent getTranslatedName(IStandPower power, String key) {
        LivingEntity user = power.getUser();
        int barriers = user instanceof PlayerEntity ? ((StoneFreeStandType<?>) power.getType()).getPlacedBarriersCount(power) : 0;
        return new TranslationTextComponent (key, barriers, getMaxBarriersPlaceable(power));
    }
    
    @Override
    public TargetRequirement getTargetRequirement() {
        return TargetRequirement.BLOCK;
    }

}
