package com.danielgamer321.rotp_sf.action.stand;

import com.danielgamer321.rotp_sf.entity.stand.stands.StoneFreeEntity;
import com.danielgamer321.rotp_sf.init.InitStands;
import com.github.standobyte.jojo.action.stand.*;
import com.github.standobyte.jojo.action.stand.punch.StandEntityPunch;
import com.github.standobyte.jojo.entity.stand.StandEntity;
import com.github.standobyte.jojo.entity.stand.StandEntityTask;
import com.github.standobyte.jojo.power.impl.stand.IStandPower;
import com.github.standobyte.jojo.util.mc.damage.StandEntityDamageSource;

import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.World;

import javax.annotation.Nullable;

public class StoneFreeStringPunch extends StandEntityHeavyAttack {

    public StoneFreeStringPunch(StandEntityHeavyAttack.Builder builder) {
        super(builder);
    }

    @Override
    protected StandEntityActionModifier getRecoveryFollowup(IStandPower standPower, StandEntity standEntity) {
        return InitStands.STONE_FREE_STRING_CAPTURE.get();
    }

    private static final double SLIDE_DISTANCE = 1.8;
    @Override
    public void standTickWindup(World world, StandEntity standEntity, IStandPower userPower, StandEntityTask task) {
        int ticksLeft = task.getTicksLeft();
        double range = standEntity.getRangeEfficiency();
        if (ticksLeft == 2) {
            Vector3d targetPos = task.getTarget().getTargetPos(true);
            Vector3d slideVec;
            if (targetPos != null) {
                slideVec = targetPos.subtract(standEntity.getEyePosition(1.0F));
                slideVec = slideVec.normalize().scale(MathHelper.clamp(slideVec.length() - standEntity.getBbWidth(), 0, SLIDE_DISTANCE));
            }
            else {
                slideVec = standEntity.getLookAngle().scale(SLIDE_DISTANCE);
            }
            standEntity.setDeltaMovement(slideVec);
        }
        else if (ticksLeft == 1) {
            standEntity.setDeltaMovement(Vector3d.ZERO);
        }
    }

    @Override
    public StandEntityPunch punchEntity(StandEntity stand, Entity target, StandEntityDamageSource dmgSource) {
        return super.punchEntity(stand, target, dmgSource)
                .addKnockback(2);

    }

    @Override
    protected boolean standMovesByItself(IStandPower standPower, StandEntity standEntity) {
        Phase phase = standEntity.getCurrentTaskPhase().get();
        return phase == Phase.WINDUP && standEntity.getCurrentTask().map(StandEntityTask::getTicksLeft).get() <= 2
                || phase == Phase.PERFORM || phase == Phase.RECOVERY;
    }

    @Override
    public void standPerform(World world, StandEntity standEntity, IStandPower userPower, StandEntityTask task) {
        super.standPerform(world, standEntity, userPower, task);
        if (!world.isClientSide()) {
            StoneFreeEntity stoneFree = (StoneFreeEntity) standEntity;
            stoneFree.setCapture(true);
        }
    }

    @Override
    protected void onTaskStopped(World world, StandEntity standEntity, IStandPower standPower, StandEntityTask task, @Nullable StandEntityAction newAction) {
        StoneFreeEntity stoneFree = (StoneFreeEntity) standEntity;
        stoneFree.setCapture(false);
    }
}
