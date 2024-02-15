package com.danielgamer321.rotp_sf.action.stand;

import com.danielgamer321.rotp_sf.entity.damaging.projectile.ownerbound.SFGrapplingStringEntity;
import com.danielgamer321.rotp_sf.power.impl.stand.type.StoneFreeStandType;
import com.github.standobyte.jojo.action.ActionConditionResult;
import com.github.standobyte.jojo.action.ActionTarget;
import com.github.standobyte.jojo.action.ActionTarget.TargetType;
import com.github.standobyte.jojo.action.stand.StandEntityAction;
import com.github.standobyte.jojo.entity.stand.StandEntity;
import com.github.standobyte.jojo.entity.stand.StandEntityTask;
import com.github.standobyte.jojo.power.impl.stand.IStandPower;

import net.minecraft.entity.LivingEntity;
import net.minecraft.world.World;

public class StoneFreeGrapple extends StandEntityAction {
    
    public StoneFreeGrapple(StandEntityAction.Builder builder) {
        super(builder);
    }

    @Override
    protected ActionConditionResult checkSpecificConditions(LivingEntity user, IStandPower power, ActionTarget target) {
        int strings = ((StoneFreeStandType<?>) power.getType()).getPlacedBarriersCount(power);
        if (strings >= 100) {
            return conditionMessage("string_limit");
        }
        return ActionConditionResult.POSITIVE;
    }

    @Override
    public void standPerform(World world, StandEntity standEntity, IStandPower userPower, StandEntityTask task) {
        if (!world.isClientSide()) {
            SFGrapplingStringEntity string = new SFGrapplingStringEntity(world, standEntity, userPower);
            string.setBindEntities(true);
            world.addFreshEntity(string);
        }
    }
    
    @Override
    public boolean standRetractsAfterTask(IStandPower standPower, StandEntity standEntity) {
    	return isShiftVariation();
    }
    
    @Override
    protected boolean standKeepsTarget(ActionTarget target) {
        return this.isShiftVariation() && target.getType() == TargetType.ENTITY;
    }
}
