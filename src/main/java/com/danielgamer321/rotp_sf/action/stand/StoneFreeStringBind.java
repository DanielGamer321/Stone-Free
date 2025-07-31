package com.danielgamer321.rotp_sf.action.stand;

import javax.annotation.Nullable;

import com.danielgamer321.rotp_sf.entity.damaging.projectile.ownerbound.SFStringEntity;
import com.danielgamer321.rotp_sf.init.InitStands;
import com.danielgamer321.rotp_sf.power.impl.stand.type.StoneFreeStandType;
import com.github.standobyte.jojo.action.Action;
import com.github.standobyte.jojo.action.ActionConditionResult;
import com.github.standobyte.jojo.action.ActionTarget;
import com.github.standobyte.jojo.action.stand.StandAction;
import com.github.standobyte.jojo.action.stand.StandEntityAction;
import com.github.standobyte.jojo.entity.stand.StandEntity;
import com.github.standobyte.jojo.entity.stand.StandEntityTask;
import com.github.standobyte.jojo.power.impl.stand.IStandPower;

import net.minecraft.entity.LivingEntity;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;

public class StoneFreeStringBind extends  StandEntityAction {

    public StoneFreeStringBind(StandEntityAction.Builder builder) {
        super(builder);
    }

    @Override
    protected Action<IStandPower> replaceAction(IStandPower power, ActionTarget target) {
        if (!(power.isActive() && power.getStandManifestation() instanceof StandEntity)) {
            return InitStands.STONE_FREE_USER_STRING_BIND.get();
        }
        else {
            StandEntity stand = (StandEntity) power.getStandManifestation();
            if ((stand.getCurrentTaskAction() != null && stand.getCurrentTaskAction() != this &&
                    stand.getCurrentTaskAction() != InitStands.STONE_FREE_BARRAGE.get() &&
                    stand.getCurrentTaskAction() != InitStands.STONE_FREE_PUNCH.get()) ||
                    stand.isArmsOnlyMode()) {
                return InitStands.STONE_FREE_USER_STRING_BIND.get();
            }
        }
        return super.replaceAction(power, target);
    }

    @Override
    public StandAction[] getExtraUnlockable() {
        return new StandAction[] { InitStands.STONE_FREE_USER_STRING_BIND.get() };
    }

    @Override
    protected ActionConditionResult checkSpecificConditions(LivingEntity user, IStandPower power, ActionTarget target) {
        if (((StoneFreeStandType<?>) power.getType()).getPlacedBarriersCount(power) >= 100) {
            return conditionMessage("string_limit");
        }
        return ActionConditionResult.POSITIVE;
    }

    @Override
    public void standPerform(World world, StandEntity standEntity, IStandPower userPower, StandEntityTask task) {
        if (!world.isClientSide()) {
            boolean isCapture = false;
            addProjectile(world, userPower, standEntity, 0, 0, isCapture);
            InitStands.STONE_FREE_USER_STRING_BIND.get().setCooldownOnUse(userPower);
        }
    }

    private void addProjectile(World world, IStandPower standPower, StandEntity standEntity, float yRotDelta, float xRotDelta, boolean isCapture) {
    	SFStringEntity string = new SFStringEntity(world, standEntity, yRotDelta, xRotDelta, isCapture);
    	string.setLifeSpan(getStandActionTicks(standPower, standEntity));
    	standEntity.addProjectile(string);
    }

    protected boolean isCancelable(IStandPower standPower, StandEntity standEntity, @Nullable StandEntityAction newAction, Phase phase) {
    	return  (newAction == InitStands.STONE_FREE_PUNCH.get()
    			|| newAction == InitStands.STONE_FREE_BARRAGE.get()
    			|| newAction == InitStands.STONE_FREE_HEAVY_PUNCH.get())
    			|| super.isCancelable(standPower, standEntity, newAction, phase);
    }

    @Override
    protected boolean standKeepsTarget(ActionTarget target) {
        return true;
    }

    @Override
    public int getStandActionTicks(IStandPower standPower, StandEntity standEntity) {
        double speed = standEntity.getAttackSpeed() / 8;
        return MathHelper.ceil(super.getStandActionTicks(standPower, standEntity) / Math.max(speed, 0.125));
    }
}
