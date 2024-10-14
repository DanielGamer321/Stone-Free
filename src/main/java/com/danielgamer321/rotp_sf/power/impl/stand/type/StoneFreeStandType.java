package com.danielgamer321.rotp_sf.power.impl.stand.type;

import com.danielgamer321.rotp_sf.action.stand.StoneFreeUserMobiusStrip;
import com.danielgamer321.rotp_sf.init.InitEffects;
import com.danielgamer321.rotp_sf.capability.entity.LivingUtilCapProvider;
import com.danielgamer321.rotp_sf.init.InitSounds;
import com.danielgamer321.rotp_sf.init.InitStands;
import com.danielgamer321.rotp_sf.util.AddonInteractionUtil;
import com.github.standobyte.jojo.action.stand.StandAction;
import com.github.standobyte.jojo.power.impl.stand.IStandPower;
import com.github.standobyte.jojo.power.impl.stand.StandUtil;
import com.github.standobyte.jojo.power.impl.stand.stats.StandStats;
import com.github.standobyte.jojo.power.impl.stand.type.EntityStandType;

import com.github.standobyte.jojo.util.mc.MCUtil;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.potion.EffectInstance;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.util.text.ITextComponent;

import javax.annotation.Nullable;

public class StoneFreeStandType<T extends StandStats> extends EntityStandType<T> {

    public StoneFreeStandType(int color, ITextComponent partName,
            StandAction[] attacks, StandAction[] abilities,
            Class<T> statsClass, T defaultStats, @Nullable StandTypeOptionals additions) {
        super(color, partName, attacks, abilities, statsClass, defaultStats, additions);
    }

    protected StoneFreeStandType(EntityStandType.AbstractBuilder<?, T> builder) {
        super(builder);
    }

    @Override
    public float getStaminaRegen(IStandPower power) {
        LivingEntity user = power.getUser();
        if (!user.getCapability(LivingUtilCapProvider.CAPABILITY).map(cap -> cap.userString(user)).orElse(false)) {
            return 0;
        }
        else {
            return super.getStaminaRegen(power);
        }
    }

    @Override
    public void tickUser(LivingEntity user, IStandPower power) {
        super.tickUser(user, power);
        if (!user.level.isClientSide()) {
            switch (getPlacedBarriersCount(power)){
                case 21: case 22: case 23: case 24: case 25: case 26: case 27: case 28: case 29: case 30:
                    user.addEffect(new EffectInstance(InitEffects.STRING_DECOMPOSITION.get(), 20, 0, false, false, true));
                    break;
                case 31: case 32: case 33: case 34: case 35: case 36: case 37: case 38: case 39: case 40:
                    user.addEffect(new EffectInstance(InitEffects.STRING_DECOMPOSITION.get(), 20, 1, false, false, true));
                    break;
                case 41: case 42: case 43: case 44: case 45: case 46: case 47: case 48: case 49: case 50:
                    user.addEffect(new EffectInstance(InitEffects.STRING_DECOMPOSITION.get(), 20, 2, false, false, true));
                    break;
                case 51: case 52: case 53: case 54: case 55: case 56: case 57: case 58: case 59: case 60:
                    user.addEffect(new EffectInstance(InitEffects.STRING_DECOMPOSITION.get(), 20, 3, false, false, true));
                    break;
                case 61: case 62: case 63: case 64: case 65: case 66: case 67: case 68: case 69: case 70:
                    user.addEffect(new EffectInstance(InitEffects.STRING_DECOMPOSITION.get(), 20, 4, false, false, true));
                    break;
                case 71: case 72: case 73: case 74: case 75: case 76: case 77: case 78: case 79: case 80:
                    user.addEffect(new EffectInstance(InitEffects.STRING_DECOMPOSITION.get(), 20, 5, false, false, true));
                    break;
                case 81: case 82: case 83: case 84: case 85: case 86: case 87: case 88: case 89: case 90:
                    user.addEffect(new EffectInstance(InitEffects.STRING_DECOMPOSITION.get(), 20, 6, false, false, true));
                    break;
                case 91: case 92: case 93: case 94: case 95: case 96: case 97: case 98: case 99: case 100:
                    user.addEffect(new EffectInstance(InitEffects.STRING_DECOMPOSITION.get(), 20, 7, false, false, true));
                    break;
                default:
                    user.removeEffect(InitEffects.STRING_DECOMPOSITION.get());
            }
            if (power.getResolveLevel() >= 4 && AddonInteractionUtil.getInvestedEntity(user) > 0 &&
                    user.getHealth() <= 10 && !InitStands.STONE_FREE_USER_MOBIUS_STRIP.get().isUnlocked(power)) {
                power.unlockAction(InitStands.STONE_FREE_USER_MOBIUS_STRIP.get());
                StoneFreeUserMobiusStrip.setModeServerSide(user, true);
            }
            if (AddonInteractionUtil.getInvestedEntity(user) > 0 && StoneFreeUserMobiusStrip.getMode(power) &&
                    !user.hasEffect(InitEffects.MOBIUS_STRIP.get()) && getPlacedBarriersCount(power) <= 90) {
                MCUtil.playSound(user.level, null, user, InitSounds.STONE_FREE_MOBIUS_STRIP.get(),
                        SoundCategory.AMBIENT, 1.0F, 1.0F, StandUtil::playerCanHearStands);
                user.addEffect(new EffectInstance(InitEffects.MOBIUS_STRIP.get(), 200, 0, false, false, true));
                IStandPower.getStandPowerOptional(user).ifPresent(stand -> {
                    stand.consumeStamina(25 * AddonInteractionUtil.getInvestedEntity(user));
                });
            }
        }
    }

    public float SFReduceDamageAmount(IStandPower power, LivingEntity user,
                                      DamageSource dmgSource, float dmgAmount) {
        float damageReductionMult = 0.006F * getPlacedBarriersCount(power);

        if (damageReductionMult > 0) {
            float damageReduced = dmgAmount * damageReductionMult;

            Entity sourceEntity = dmgSource.getDirectEntity();
            Vector3d sourcePos = sourceEntity.getEyePosition(1.0F);
            AxisAlignedBB userHitbox = user.getBoundingBox();
            Vector3d damagePos;
            return dmgAmount - damageReduced;
        }
        else {
            return dmgAmount;
        }
    }

    public int getPlacedBarriersCount(IStandPower power) {
        LivingEntity user = power.getUser();
        return user.getCapability(LivingUtilCapProvider.CAPABILITY).map(cap -> cap.fixPlacedBarriersCount()).orElse(0);
    }

    public static class Builder<T extends StandStats> extends EntityStandType.AbstractBuilder<Builder<T>, T> {

        @Override
        protected Builder<T> getThis() {
            return this;
        }

        @Override
        public StoneFreeStandType<T> build() {
            return new StoneFreeStandType<>(this);
        }

    }
}