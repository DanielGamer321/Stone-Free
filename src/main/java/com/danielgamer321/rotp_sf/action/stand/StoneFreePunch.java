package com.danielgamer321.rotp_sf.action.stand;

import com.danielgamer321.rotp_sf.init.InitStands;
import com.github.standobyte.jojo.action.Action;
import com.github.standobyte.jojo.action.ActionTarget;
import com.github.standobyte.jojo.action.stand.StandAction;
import com.github.standobyte.jojo.action.stand.StandEntityActionModifier;
import com.github.standobyte.jojo.action.stand.StandEntityLightAttack;
import com.github.standobyte.jojo.entity.stand.StandEntity;
import com.github.standobyte.jojo.power.impl.stand.IStandPower;
import com.github.standobyte.jojo.util.general.ObjectWrapper;

public class StoneFreePunch extends StandEntityLightAttack {

    public StoneFreePunch(StandEntityLightAttack.Builder builder) {
        super(builder);
    }

    @Override
    protected Action<IStandPower> replaceAction(IStandPower power, ActionTarget target) {
        if (!(power.isActive() && power.getStandManifestation() instanceof StandEntity)) {
            return InitStands.STONE_FREE_USER_STRING_ATTACK.get();
        }
        else {
            StandEntity stand = (StandEntity) power.getStandManifestation();
            if (stand.getCurrentTaskAction() != null && stand.getCurrentTaskAction() != this &&
                    stand.getCurrentTaskAction() != InitStands.STONE_FREE_BARRAGE.get() &&
                    followUpAvailable(stand, power)) {
                return InitStands.STONE_FREE_USER_STRING_ATTACK.get();
            }
        }
        return super.replaceAction(power, target);
    }

    public static boolean followUpAvailable(StandEntity stand, IStandPower power) {
        StandEntityActionModifier followUp = InitStands.STONE_FREE_STRING_CAPTURE.get();
        return !stand.getCurrentTask().map(task -> {
            return task.getAction() == InitStands.STONE_FREE_STRING_PUNCH.get() &&
                    !task.getModifierActions().filter(action -> action == followUp).findAny().isPresent() &&
                    power.checkRequirements(followUp, new ObjectWrapper<>(task.getTarget()), true).isPositive();
        }).orElse(false);
    }

    @Override
    public StandAction[] getExtraUnlockable() {
        return new StandAction[] { InitStands.STONE_FREE_USER_STRING_ATTACK.get(), InitStands.STONE_FREE_USER_STRING_WHIP.get() };
    }
}
