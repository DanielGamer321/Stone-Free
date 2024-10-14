package com.danielgamer321.rotp_sf.util;

import com.github.standobyte.jojo.JojoMod;

import net.minecraft.entity.LivingEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;

import java.util.Comparator;

@EventBusSubscriber(modid = JojoMod.MOD_ID)
public class AddonInteractionUtil {
    public static final ResourceLocation WR_BLEEDING = new ResourceLocation("rotp_wr", "bleeding");
    public static final ResourceLocation MID_BLEEDING = new ResourceLocation("rotp_mid", "bleeding");
    public static final ResourceLocation CM_INVERSION = new ResourceLocation("rotp_cm", "cm_inversion");
    public static float getWRBleedingEntity(LivingEntity entity) {
        return Math.min(entity.getActiveEffectsMap().entrySet().stream().map(entry -> {
            if (WR_BLEEDING.equals(entry.getKey().getRegistryName())) {
                return entry.getValue().getAmplifier() + 1F;
            }
            return 0F;
        }).max(Comparator.naturalOrder()).orElse(0F), 1);
    }

    public static float getMIDBleedingEntity(LivingEntity entity) {
        return Math.min(entity.getActiveEffectsMap().entrySet().stream().map(entry -> {
            if (MID_BLEEDING.equals(entry.getKey().getRegistryName())) {
                return entry.getValue().getAmplifier() + 1F;
            }
            return 0F;
        }).max(Comparator.naturalOrder()).orElse(0F), 1);
    }

    public static float getInvestedEntity(LivingEntity entity) {
        return Math.min(entity.getActiveEffectsMap().entrySet().stream().map(entry -> {
            if (CM_INVERSION.equals(entry.getKey().getRegistryName())) {
                return entry.getValue().getAmplifier() + 1F;
            }
            return 0F;
        }).max(Comparator.naturalOrder()).orElse(0F), 1);
    }
}
