package com.danielgamer321.rotp_sf.potion;

import com.danielgamer321.rotp_sf.init.AddonStands;
import com.danielgamer321.rotp_sf.init.InitEffects;
import com.danielgamer321.rotp_sf.util.AddonInteractionUtil;
import com.github.standobyte.jojo.potion.IApplicableEffect;
import com.github.standobyte.jojo.potion.UncurableEffect;
import com.github.standobyte.jojo.power.impl.stand.IStandPower;

import net.minecraft.entity.LivingEntity;
import net.minecraft.potion.Effect;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.EffectType;

import java.util.Map;

public class MobiusStripEffect extends UncurableEffect implements IApplicableEffect {

    public MobiusStripEffect(EffectType type, int liquidColor) {
        super(type, liquidColor);
    }
    
    @Override
    public void applyEffectTick(LivingEntity livingEntity, int amplifier) {
        super.applyEffectTick(livingEntity,amplifier);
        if (AddonInteractionUtil.getInvestedEntity(livingEntity) > 0) {
            Effect inversion = null;
            Map<Effect, EffectInstance> map = livingEntity.getActiveEffectsMap();
            for (Effect effect : map.keySet()) {
                if (AddonInteractionUtil.CM_INVERSION.equals(effect.getRegistryName())) {
                    inversion = effect;
                }
            }
            if (inversion != null)
                livingEntity.removeEffect(inversion);
            IStandPower.getStandPowerOptional(livingEntity).ifPresent(stand -> {
                stand.consumeStamina(10 * AddonInteractionUtil.getInvestedEntity(livingEntity));
            });
            EffectInstance mobiusStip = livingEntity.getEffect(this);
            if (mobiusStip != null) {
                livingEntity.addEffect(new EffectInstance(InitEffects.MOBIUS_STRIP.get(), mobiusStip.getDuration() + 40, 0, false, false, true));
            }
        }
    }
    
    @Override
    public boolean isDurationEffectTick(int duration, int amplifier) {
        return true;
    }

    @Override
    public boolean isApplicable(LivingEntity entity) {
         return IStandPower.getStandPowerOptional(entity).map(stand ->
                 stand.hasPower() && stand.getType() == AddonStands.STONE_FREE.getStandType()).orElse(false);
    }
}
