package com.danielgamer321.rotp_sf.action.stand;

import com.danielgamer321.rotp_sf.entity.damaging.projectile.ownerbound.SFStringEntity;
import com.danielgamer321.rotp_sf.entity.stand.stands.StoneFreeEntity;
import com.danielgamer321.rotp_sf.init.InitSounds;
import com.danielgamer321.rotp_sf.init.InitStands;
import com.danielgamer321.rotp_sf.power.impl.stand.type.StoneFreeStandType;
import com.github.standobyte.jojo.action.ActionConditionResult;
import com.github.standobyte.jojo.action.ActionTarget;
import com.github.standobyte.jojo.action.stand.StandEntityActionModifier;
import com.github.standobyte.jojo.entity.stand.StandEntity;
import com.github.standobyte.jojo.entity.stand.StandEntityTask;
import com.github.standobyte.jojo.power.impl.stand.IStandPower;
import com.github.standobyte.jojo.power.impl.stand.StandUtil;
import com.github.standobyte.jojo.util.general.MathUtil;
import com.github.standobyte.jojo.util.mc.MCUtil;

import net.minecraft.entity.LivingEntity;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.vector.Vector2f;
import net.minecraft.world.World;

import static com.danielgamer321.rotp_sf.action.stand.StoneFreeUserBarrier.MaxVarietyOfBarriers;

public class StoneFreeStringCapture extends StandEntityActionModifier {

    public StoneFreeStringCapture(Builder builder) {
        super(builder);
    }
    
    @Override
    public boolean isUnlocked(IStandPower power) {
        return InitStands.STONE_FREE_STRING_BIND.get().isUnlocked(power);
    }
    
    @Override
    protected ActionConditionResult checkSpecificConditions(LivingEntity user, IStandPower power, ActionTarget target) {
        if (((StoneFreeStandType<?>) power.getType()).getPlacedBarriersCount(power) >= MaxVarietyOfBarriers(user)) {
            return conditionMessage("string_limit");
        }
        return ActionConditionResult.POSITIVE;
    }
    
    @Override
    public void standTickRecovery(World world, StandEntity standEntity, IStandPower userPower, StandEntityTask task) {
        StoneFreeEntity stoneFree = (StoneFreeEntity) standEntity;
        if (!world.isClientSide() && stoneFree.canCapture()) {
            boolean isCapture = true;
            int strings = ((StoneFreeStandType<?>) userPower.getType()).getPlacedBarriersCount(userPower);
            int n = strings >= 97 ? 99 - strings : 3;
            MCUtil.playSound(world, null, standEntity, InitSounds.STONE_FREE_STRING.get(),
                    SoundCategory.AMBIENT, 1.0F, 1.0F, StandUtil::playerCanHearStands);
            for (int i = 0; i < n; i++) {
                Vector2f rotOffsets = MathUtil.xRotYRotOffsets((double) i / (double) n * Math.PI * 2, 10);
                addProjectile(world, standEntity, rotOffsets.y, rotOffsets.x, isCapture);
            }
            addProjectile(world, standEntity, 0, 0, isCapture);
            stoneFree.setCapture(false);
        }
    }

    private void addProjectile(World world, StandEntity standEntity, float yRotDelta, float xRotDelta, boolean isBinding) {
        SFStringEntity string = new SFStringEntity(world, standEntity, yRotDelta, xRotDelta, isBinding);
        string.setLifeSpan(24);
        standEntity.addProjectile(string);
    }
}
