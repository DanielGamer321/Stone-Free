package com.danielgamer321.rotp_sf.action.stand;

import com.danielgamer321.rotp_sf.init.InitSounds;
import com.danielgamer321.rotp_sf.power.impl.stand.type.StoneFreeStandType;
import com.danielgamer321.rotp_sf.util.AddonInteractionUtil;
import com.github.standobyte.jojo.action.ActionConditionResult;
import com.github.standobyte.jojo.action.ActionTarget;
import com.github.standobyte.jojo.action.stand.StandAction;
import com.github.standobyte.jojo.init.ModStatusEffects;
import com.github.standobyte.jojo.power.impl.stand.IStandPower;

import com.github.standobyte.jojo.power.impl.stand.StandUtil;
import com.github.standobyte.jojo.util.mc.MCUtil;
import net.minecraft.entity.LivingEntity;
import net.minecraft.potion.Effect;
import net.minecraft.potion.EffectInstance;
import net.minecraft.util.SoundCategory;
import net.minecraft.world.World;

import java.util.Map;

import static com.danielgamer321.rotp_sf.action.stand.StoneFreeUserBarrier.MaxVarietyOfBarriers;

public class StoneFreeUserCloseWounds extends StandAction {

    public StoneFreeUserCloseWounds(StandAction.Builder builder) {
        super(builder);
    }

    @Override
    protected ActionConditionResult checkSpecificConditions(LivingEntity user, IStandPower power, ActionTarget target) {
        if (((StoneFreeStandType<?>) power.getType()).getPlacedBarriersCount(power) >= MaxVarietyOfBarriers(user)) {
            return conditionMessage("string_limit");
        }

        if (!user.hasEffect(ModStatusEffects.BLEEDING.get()) && AddonInteractionUtil.getWRBleedingEntity(user) == 0 &&
                AddonInteractionUtil.getMIHBleedingEntity(user) == 0) {
            return ActionConditionResult.NEGATIVE;
        }
        return super.checkSpecificConditions(user, power, target);
    }

    @Override
    public void startedHolding(World world, LivingEntity user, IStandPower power, ActionTarget target, boolean requirementsFulfilled) {
        if (requirementsFulfilled && !world.isClientSide()) {
            MCUtil.playSound(world, null, user, InitSounds.STONE_FREE_CLOSING_WOUND.get(),
                    SoundCategory.AMBIENT, 1.0F, 1.0F, StandUtil::playerCanHearStands);
        }
    }

    @Override
    protected void perform(World world, LivingEntity user, IStandPower power, ActionTarget target) {
        if (!world.isClientSide()) {
            MCUtil.playSound(world, null, user, InitSounds.STONE_FREE_CLOSED_WOUND.get(),
                    SoundCategory.AMBIENT, 1.0F, 1.0F, StandUtil::playerCanHearStands);
            if (user.hasEffect(ModStatusEffects.BLEEDING.get())) {
                user.removeEffect(ModStatusEffects.BLEEDING.get());
            }
            if (AddonInteractionUtil.getWRBleedingEntity(user) > 0) {
                Map<Effect, EffectInstance> map = user.getActiveEffectsMap();
                for (Effect effect : map.keySet()) {
                    if (AddonInteractionUtil.WR_BLEEDING.equals(effect.getRegistryName())) {
                        user.removeEffect(effect);
                    }
                }
            }
            if (AddonInteractionUtil.getMIHBleedingEntity(user) > 0) {
                Map<Effect, EffectInstance> map = user.getActiveEffectsMap();
                for (Effect effect : map.keySet()) {
                    if (AddonInteractionUtil.MIH_BLEEDING.equals(effect.getRegistryName())) {
                        user.removeEffect(effect);
                    }
                }
            }
        }
    }

    @Override
    public float getStaminaCost(IStandPower stand) {
        LivingEntity user = stand.getUser();
        float lvl = 0;
        if (user.hasEffect(ModStatusEffects.BLEEDING.get())) {
            EffectInstance bleeding = user.getEffect(ModStatusEffects.BLEEDING.get());
            lvl = lvl + bleeding.getAmplifier() + 1;
        }
        if (AddonInteractionUtil.getWRBleedingEntity(user) > 0) {
            lvl = lvl + AddonInteractionUtil.getWRBleedingEntity(user);
        }
        if (AddonInteractionUtil.getMIHBleedingEntity(user) > 0) {
            lvl = lvl + AddonInteractionUtil.getMIHBleedingEntity(user);
        }
        return super.getStaminaCost(stand) * lvl;
    }
}
