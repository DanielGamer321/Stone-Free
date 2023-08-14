package com.danielgamer321.rotp_sf.action.stand;

import javax.annotation.Nullable;

import com.danielgamer321.rotp_sf.entity.damaging.projectile.ownerbound.SFStringEntity;
import com.danielgamer321.rotp_sf.init.InitStands;
import com.github.standobyte.jojo.action.stand.punch.StandEntityPunch;
import com.github.standobyte.jojo.action.ActionTarget;
import com.github.standobyte.jojo.action.stand.StandEntityAction;
import com.github.standobyte.jojo.action.stand.StandEntityHeavyAttack;
import com.github.standobyte.jojo.entity.stand.StandEntity;
import com.github.standobyte.jojo.entity.stand.StandEntityTask;
import com.github.standobyte.jojo.power.impl.stand.IStandPower;
import com.github.standobyte.jojo.util.general.MathUtil;
import com.github.standobyte.jojo.entity.stand.StandStatFormulas;
import com.github.standobyte.jojo.util.mc.damage.StandEntityDamageSource;

import net.minecraft.entity.Entity;
import net.minecraft.util.math.vector.Vector2f;
import net.minecraft.world.World;

public class StoneFreeStringAttack extends StandEntityHeavyAttack {

    public StoneFreeStringAttack(StandEntityHeavyAttack.Builder builder) {
        super(builder);
    }
    
    @Override
    public int getStandWindupTicks(IStandPower standPower, StandEntity standEntity) {
        return Math.max(super.getStandWindupTicks(standPower, standEntity) - getStandActionTicks(standPower, standEntity) / 2, 3);
    }
    
    @Override
    public void onTaskSet(World world, StandEntity standEntity, IStandPower standPower, Phase phase, StandEntityTask task, int ticks) {
        super.onTaskSet(world, standEntity, standPower, phase, task, ticks);
        if (!world.isClientSide()) {
            boolean shift = isShiftVariation();
            int n = shift ? 1 : 1;
            for (int i = 0; i < n; i++) {
                Vector2f rotOffsets = i > 0 ? MathUtil.xRotYRotOffsets((double) i / (double) n * Math.PI * 2, 10) : Vector2f.ZERO;
                addProjectile(world, standEntity, rotOffsets.y, rotOffsets.x, shift);
            }
            addProjectile(world, standEntity, 0, 0, shift);
        }
    }

    private void addProjectile(World world, StandEntity standEntity, float yRotDelta, float xRotDelta, boolean shift) {
    	SFStringEntity string = new SFStringEntity(world, standEntity, yRotDelta, xRotDelta, shift);
    	if (!shift) {
    		string.addKnockback(standEntity.guardCounter());
    	}
    	standEntity.addProjectile(string);
    }
    
    protected boolean isCancelable(IStandPower standPower, StandEntity standEntity, @Nullable StandEntityAction newAction, Phase phase) {
    	return !this.hasShiftVariation() && 
    			(newAction == InitStands.STONE_FREE_PUNCH.get()
    			|| newAction == InitStands.STONE_FREE_BARRAGE.get()
    			|| newAction == InitStands.STONE_FREE_HEAVY_PUNCH.get())
    			|| super.isCancelable(standPower, standEntity, newAction, phase);
    }
    
    @Override
    protected boolean standKeepsTarget(ActionTarget target) {
        return true;
    }

    @Override
    public StandEntityPunch punchEntity(StandEntity stand, Entity target, StandEntityDamageSource dmgSource) {
        return super.punchEntity(stand, target, dmgSource)
                .damage(StandStatFormulas.getBarrageHitDamage(stand.getAttackDamage(), stand.getPrecision()))
                .addKnockback(0);
    }
}
