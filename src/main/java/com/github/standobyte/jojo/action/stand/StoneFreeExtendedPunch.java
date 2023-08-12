package com.github.standobyte.jojo.action.stand;

import javax.annotation.Nullable;

import com.github.standobyte.jojo.action.ActionConditionResult;
import com.github.standobyte.jojo.action.ActionTarget;
import com.github.standobyte.jojo.entity.damaging.projectile.ownerbound.SFExtendedPunchEntity;
import com.github.standobyte.jojo.entity.stand.StandEntity;
import com.github.standobyte.jojo.entity.stand.StandEntityTask;
import com.github.standobyte.jojo.entity.stand.stands.StoneFreeEntity;
import com.github.standobyte.jojo.power.stand.IStandPower;

import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.world.World;

public class StoneFreeExtendedPunch extends StandEntityAction {

    public StoneFreeExtendedPunch(StandEntityAction.Builder builder) {
        super(builder);
    }

    @Override
    protected ActionConditionResult checkStandConditions(StandEntity stand, IStandPower power, ActionTarget target) {
        if ((stand instanceof StoneFreeEntity && !((StoneFreeEntity) stand).hasForeArm()) && (stand instanceof StoneFreeEntity && !((StoneFreeEntity) stand).hasShortForeArm())) {
            return conditionMessage("stonefree_rightforearm");
        }
        return super.checkStandConditions(stand, power, target);
    }
    
    @Override
    public void onTaskSet(World world, StandEntity standEntity, IStandPower standPower, Phase phase, StandEntityTask task, int ticks) {
        super.onTaskSet(world, standEntity, standPower, phase, task, ticks);
        if (!world.isClientSide()) {
        	StoneFreeEntity stonefree = (StoneFreeEntity) standEntity;
        	SFExtendedPunchEntity rightforearm = new SFExtendedPunchEntity(world, standEntity);
        	if (stonefree.isFollowingUser()) {
	        	LivingEntity user = standPower.getUser();
                }
                standEntity.addProjectile(new SFExtendedPunchEntity(world, standEntity));
                stonefree.setForeArm(false);
            	stonefree.setShortForeArm(true);
        }
    }
    
    @Override
    protected void onTaskStopped(World world, StandEntity standEntity, IStandPower standPower, StandEntityTask task, @Nullable StandEntityAction newAction) {
        if (!world.isClientSide()) {
        	StoneFreeEntity stonefree = (StoneFreeEntity) standEntity;
                takeForeArm(stonefree);
            }
        }
    
    public void takeForeArm(StoneFreeEntity stonefree) {
        stonefree.setForeArm(true);
        stonefree.setShortForeArm(false);
    }
}