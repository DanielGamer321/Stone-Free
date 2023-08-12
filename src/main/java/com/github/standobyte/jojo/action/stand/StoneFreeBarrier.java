package com.github.standobyte.jojo.action.stand;

import com.github.standobyte.jojo.action.ActionConditionResult;
import com.github.standobyte.jojo.action.ActionTarget;
import com.github.standobyte.jojo.entity.stand.StandEntity;
import com.github.standobyte.jojo.entity.stand.StandEntityTask;
import com.github.standobyte.jojo.entity.stand.stands.StoneFreeEntity;
import com.github.standobyte.jojo.power.stand.IStandManifestation;
import com.github.standobyte.jojo.power.stand.IStandPower;

import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;

public class StoneFreeBarrier extends StandEntityAction {

    public StoneFreeBarrier(StandEntityAction.Builder builder) {
        super(builder);
    }
    
    @Override
    protected ActionConditionResult checkStandConditions(StandEntity stand, IStandPower power, ActionTarget target) {
        if (stand instanceof StoneFreeEntity) {
            StoneFreeEntity stonefree = (StoneFreeEntity) stand;
            if (!stonefree.canPlaceBarrier()) {
                return conditionMessage("barrier");
            }
            return ActionConditionResult.POSITIVE;
        }
        return ActionConditionResult.NEGATIVE;
    }
    
    @Override
    public void standPerform(World world, StandEntity standEntity, IStandPower userPower, StandEntityTask task) {
        if (!world.isClientSide()) {
            StoneFreeEntity stonefree = (StoneFreeEntity) standEntity;
            stonefree.attachBarrier(task.getTarget().getBlockPos());
        }
    }
    
    public static int getMaxBarriersPlaceable(IStandPower power) {
        int level = power.getResolveLevel();
        return level >= 4 ? 25 : 5;
    }
    
    @Override
    public TranslationTextComponent getTranslatedName(IStandPower power, String key) {
        IStandManifestation stand = power.getStandManifestation();
        int barriers = stand instanceof StoneFreeEntity ? ((StoneFreeEntity) stand).getPlacedBarriersCount() : 0;
        return new TranslationTextComponent(key, barriers, getMaxBarriersPlaceable(power));
    }
    
    @Override
    public TargetRequirement getTargetRequirement() {
        return TargetRequirement.BLOCK;
    }

}
