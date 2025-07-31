package com.danielgamer321.rotp_sf.action.stand;

import com.danielgamer321.rotp_sf.init.InitStands;
import com.github.standobyte.jojo.action.Action;
import com.github.standobyte.jojo.action.ActionTarget;
import com.github.standobyte.jojo.action.stand.StandAction;
import com.github.standobyte.jojo.action.stand.StandEntityMeleeBarrage;
import com.github.standobyte.jojo.entity.stand.StandEntity;
import com.github.standobyte.jojo.power.impl.stand.IStandPower;

import static com.danielgamer321.rotp_sf.action.stand.StoneFreePunch.followUpAvailable;

public class StoneFreeBarrage extends StandEntityMeleeBarrage {

    public StoneFreeBarrage(StandEntityMeleeBarrage.Builder builder) {
        super(builder);
    }

    @Override
    protected Action<IStandPower> replaceAction(IStandPower power, ActionTarget target) {
        if (!(power.isActive() && power.getStandManifestation() instanceof StandEntity)) {
            return InitStands.STONE_FREE_USER_STRING_SWEEP.get();
        }
        else {
            StandEntity stand = (StandEntity) power.getStandManifestation();
            if ((stand.getCurrentTaskAction() != null && stand.getCurrentTaskAction() != this &&
                    stand.getCurrentTaskAction() != InitStands.STONE_FREE_PUNCH.get() &&
                    followUpAvailable(stand, power)) || stand.isArmsOnlyMode()) {
                return InitStands.STONE_FREE_USER_STRING_SWEEP.get();
            }
        }
        return super.replaceAction(power, target);
    }

    @Override
    public StandAction[] getExtraUnlockable() {
        return new StandAction[] { InitStands.STONE_FREE_USER_STRING_SWEEP.get(), InitStands.STONE_FREE_USER_STRING_WHIP.get() };
    }
}
