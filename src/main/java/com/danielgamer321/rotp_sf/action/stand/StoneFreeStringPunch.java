package com.danielgamer321.rotp_sf.action.stand;

import com.danielgamer321.rotp_sf.entity.stand.stands.StoneFreeEntity;
import com.danielgamer321.rotp_sf.init.InitStands;
import com.github.standobyte.jojo.action.stand.*;
import com.github.standobyte.jojo.action.stand.punch.StandEntityPunch;
import com.github.standobyte.jojo.entity.stand.StandEntity;
import com.github.standobyte.jojo.entity.stand.StandEntityTask;
import com.github.standobyte.jojo.entity.stand.StandRelativeOffset;
import com.github.standobyte.jojo.power.impl.stand.IStandPower;
import com.github.standobyte.jojo.util.mc.damage.StandEntityDamageSource;

import net.minecraft.entity.Entity;
import net.minecraft.world.World;

import javax.annotation.Nullable;

public class StoneFreeStringPunch extends StoneFreeHeavyPunch {

    public StoneFreeStringPunch(StandEntityHeavyAttack.Builder builder) {
        super(builder);
    }

    @Override
    protected StandEntityActionModifier getRecoveryFollowup(IStandPower standPower, StandEntity standEntity) {
        return InitStands.STONE_FREE_STRING_CAPTURE.get();
    }

    @Override
    public double getSlideDistance() {
        return 1.5;
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
    public StandEntityPunch punchEntity(StandEntity stand, Entity target, StandEntityDamageSource dmgSource) {
        return super.punchEntity(stand, target, dmgSource)
                .addKnockback(2);

    }

    @Override
    public StandRelativeOffset getOffsetFromUser(IStandPower standPower, StandEntity standEntity, StandEntityTask task) {
        return StandRelativeOffset.noYOffset(0, 0.5);
    }

    @Override
    protected void onTaskStopped(World world, StandEntity standEntity, IStandPower standPower, StandEntityTask task, @Nullable StandEntityAction newAction) {
        StoneFreeEntity stoneFree = (StoneFreeEntity) standEntity;
        stoneFree.setCapture(false);
    }
}
