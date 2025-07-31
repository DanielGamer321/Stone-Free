package com.danielgamer321.rotp_sf.util;

import com.danielgamer321.rotp_sf.RotpStoneFreeAddon;

import net.minecraft.entity.LivingEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;

import java.util.Comparator;

@EventBusSubscriber(modid = RotpStoneFreeAddon.MOD_ID)
public class AddonInteractionUtil {
    public static final ResourceLocation WR_BLEEDING = new ResourceLocation("rotp_wr", "bleeding");
    public static final ResourceLocation MIH_BLEEDING = new ResourceLocation("rotp_mih", "bleeding");
    public static final ResourceLocation KC_BLEEDING = new ResourceLocation("rotp_kingcrimson", "bleeding");
    public static final ResourceLocation CREAM_BLEEDING = new ResourceLocation("rotp_cream", "bleeding");
    public static final ResourceLocation KC_GAPING_WOUND = new ResourceLocation("rotp_kingcrimson", "gaping_wound");
    public static final ResourceLocation KC_MANGLED_BODY = new ResourceLocation("rotp_kingcrimson", "mangled_body");
    public static final ResourceLocation CM_INVERSION = new ResourceLocation("rotp_cm", "cm_inversion");
    public static float getWRBleeding(LivingEntity entity) {
        return Math.max(entity.getActiveEffectsMap().entrySet().stream().map(entry -> {
            if (WR_BLEEDING.equals(entry.getKey().getRegistryName())) {
                return entry.getValue().getAmplifier() + 1F;
            }
            return 0F;
        }).max(Comparator.naturalOrder()).orElse(0F), 0);
    }

    public static float getMIHBleeding(LivingEntity entity) {
        return Math.max(entity.getActiveEffectsMap().entrySet().stream().map(entry -> {
            if (MIH_BLEEDING.equals(entry.getKey().getRegistryName())) {
                return entry.getValue().getAmplifier() + 1F;
            }
            return 0F;
        }).max(Comparator.naturalOrder()).orElse(0F), 0);
    }

    public static float getKCBleeding(LivingEntity entity) {
        return Math.max(entity.getActiveEffectsMap().entrySet().stream().map(entry -> {
            if (KC_BLEEDING.equals(entry.getKey().getRegistryName())) {
                return entry.getValue().getAmplifier() + 1F;
            }
            return 0F;
        }).max(Comparator.naturalOrder()).orElse(0F), 0);
    }

    public static float getCreamBleeding(LivingEntity entity) {
        return Math.max(entity.getActiveEffectsMap().entrySet().stream().map(entry -> {
            if (CREAM_BLEEDING.equals(entry.getKey().getRegistryName())) {
                return entry.getValue().getAmplifier() + 1F;
            }
            return 0F;
        }).max(Comparator.naturalOrder()).orElse(0F), 0);
    }

    public static float getGapingWound(LivingEntity entity) {
        return Math.max(entity.getActiveEffectsMap().entrySet().stream().map(entry -> {
            if (KC_GAPING_WOUND.equals(entry.getKey().getRegistryName())) {
                return entry.getValue().getAmplifier() + 1F;
            }
            return 0F;
        }).max(Comparator.naturalOrder()).orElse(0F), 0);
    }

    public static float getMangledBody(LivingEntity entity) {
        return Math.max(entity.getActiveEffectsMap().entrySet().stream().map(entry -> {
            if (KC_MANGLED_BODY.equals(entry.getKey().getRegistryName())) {
                return entry.getValue().getAmplifier() + 1F;
            }
            return 0F;
        }).max(Comparator.naturalOrder()).orElse(0F), 0);
    }

    public static float getInvestedEntity(LivingEntity entity) {
        return Math.max(entity.getActiveEffectsMap().entrySet().stream().map(entry -> {
            if (CM_INVERSION.equals(entry.getKey().getRegistryName())) {
                return entry.getValue().getAmplifier() + 1F;
            }
            return 0F;
        }).max(Comparator.naturalOrder()).orElse(0F), 0);
    }
}
