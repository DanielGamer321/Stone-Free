package com.danielgamer321.rotp_sf.action.stand;

import com.danielgamer321.rotp_sf.power.impl.stand.type.StoneFreeStandType;
import com.github.standobyte.jojo.action.ActionConditionResult;
import com.github.standobyte.jojo.action.ActionTarget;
import com.github.standobyte.jojo.action.stand.StandAction;
import com.github.standobyte.jojo.power.impl.stand.IStandPower;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.text.TranslationTextComponent;

public class StoneFreeUserRemoveBarrier extends StandAction {

    public StoneFreeUserRemoveBarrier(Builder builder) {
        super(builder);
    }
    
    @Override
    protected ActionConditionResult checkSpecificConditions(LivingEntity user, IStandPower power, ActionTarget target) {
        if (((StoneFreeStandType<?>) power.getType()).getPlacedBarriersCount(power) < 1) {
            return conditionMessage("no_barriers");
        }
        return ActionConditionResult.POSITIVE;
    }
    
    @Override
    public TranslationTextComponent getTranslatedName(IStandPower power, String key) {
        LivingEntity user = power.getUser();
        int barriers = user instanceof PlayerEntity ? ((StoneFreeStandType<?>) power.getType()).getPlacedBarriersCount(power) : 0;
        return new TranslationTextComponent (key, barriers);
    }

}
