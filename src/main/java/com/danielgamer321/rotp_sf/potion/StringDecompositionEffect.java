package com.danielgamer321.rotp_sf.potion;

import com.danielgamer321.rotp_sf.init.AddonStands;
import com.github.standobyte.jojo.potion.UncurableEffect;
import com.github.standobyte.jojo.potion.IApplicableEffect;
import com.github.standobyte.jojo.power.impl.stand.IStandPower;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.potion.EffectType;
import net.minecraftforge.common.ForgeMod;

public class StringDecompositionEffect extends UncurableEffect implements IApplicableEffect {
    
    public StringDecompositionEffect(EffectType type, int liquidColor) {
        super(type, liquidColor);
        addAttributeModifier(Attributes.ATTACK_DAMAGE, "22653b89-116e-49dc-9b6b-9971489b5be5", -0.5, AttributeModifier.Operation.MULTIPLY_TOTAL).
        addAttributeModifier(Attributes.ATTACK_SPEED, "e4d278d8-a38b-434f-9c65-20c944abcff9", -0.05, AttributeModifier.Operation.MULTIPLY_TOTAL).
        addAttributeModifier(Attributes.MOVEMENT_SPEED, "e30ee41c-6ea2-468c-99ab-fd0a7d6be8c3", -0.025, AttributeModifier.Operation.MULTIPLY_TOTAL).
        addAttributeModifier(ForgeMod.SWIM_SPEED.get(), "34dcb563-6759-4a2b-9dd8-ad2dd7e70404", -0.025, AttributeModifier.Operation.MULTIPLY_TOTAL);
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
