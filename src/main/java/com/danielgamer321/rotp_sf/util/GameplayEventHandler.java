package com.danielgamer321.rotp_sf.util;

import com.danielgamer321.rotp_sf.RotpStoneFreeAddon;
import com.danielgamer321.rotp_sf.init.AddonStands;
import com.danielgamer321.rotp_sf.init.InitEffects;
import com.danielgamer321.rotp_sf.power.impl.stand.type.StoneFreeStandType;
import com.github.standobyte.jojo.power.impl.stand.IStandPower;

import com.github.standobyte.jojo.power.impl.stand.StandUtil;
import com.github.standobyte.jojo.power.impl.stand.type.EntityStandType;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.network.play.server.SPlayEntityEffectPacket;
import net.minecraft.network.play.server.SRemoveEntityEffectPacket;
import net.minecraft.potion.Effect;
import net.minecraft.potion.EffectInstance;
import net.minecraft.util.*;
import net.minecraft.world.server.ServerChunkProvider;
import net.minecraftforge.event.entity.living.*;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;

import java.util.Map;

@EventBusSubscriber(modid = RotpStoneFreeAddon.MOD_ID)
public class GameplayEventHandler {

    @SubscribeEvent(priority = EventPriority.HIGH)
    public static void damageReduction(LivingHurtEvent event) {
        DamageSource dmgSource = event.getSource();
        LivingEntity target = event.getEntityLiving();
        if (!dmgSource.isBypassArmor() && dmgSource.getDirectEntity() != null) {
            IStandPower.getStandPowerOptional(target).ifPresent(power -> {
                if (
                        IStandPower.getStandPowerOptional(target).map(stand ->
                                stand.hasPower() && stand.getType() == AddonStands.STONE_FREE.getStandType()).orElse(false) && ((StoneFreeStandType<?>) power.getType()).getPlacedBarriersCount(power) > 0) {
                    event.setAmount(((StoneFreeStandType<?>) power.getType()).SFReduceDamageAmount(
                            power, power.getUser(), dmgSource, event.getAmount()));
                }
            });
        }
    }

    @SubscribeEvent(priority = EventPriority.LOWEST)
    public static void onPotionAdded(PotionEvent.PotionAddedEvent event) {
        EntityStandType.giveEffectSharedWithStand(event.getEntityLiving(), event.getPotionEffect());

        Entity entity = event.getEntity();
        EffectInstance effectInstance = event.getPotionEffect();
        if (!entity.level.isClientSide()) {
            if (InitEffects.isEffectTracked(effectInstance.getEffect())) {
                ((ServerChunkProvider) entity.getCommandSenderWorld().getChunkSource()).broadcast(entity,
                        new SPlayEntityEffectPacket(entity.getId(), effectInstance));
            }
        }
    }

    @SubscribeEvent(priority = EventPriority.LOWEST)
    public static void trackedPotionRemoved(PotionEvent.PotionRemoveEvent event) {
        EntityStandType.removeEffectSharedWithStand(event.getEntityLiving(), event.getPotion());

        Entity entity = event.getEntity();
        if (!entity.level.isClientSide() && event.getPotionEffect() != null && InitEffects.isEffectTracked(event.getPotionEffect().getEffect())) {
            ((ServerChunkProvider) entity.getCommandSenderWorld().getChunkSource()).broadcast(entity,
                    new SRemoveEntityEffectPacket(entity.getId(), event.getPotion()));
        }
    }

    @SubscribeEvent(priority = EventPriority.LOWEST)
    public static void trackedPotionExpired(PotionEvent.PotionExpiryEvent event) {
        EntityStandType.removeEffectSharedWithStand(event.getEntityLiving(), event.getPotionEffect().getEffect());

        Entity entity = event.getEntity();
        if (!entity.level.isClientSide() && InitEffects.isEffectTracked(event.getPotionEffect().getEffect())) {
            ((ServerChunkProvider) entity.getCommandSenderWorld().getChunkSource()).broadcast(entity,
                    new SRemoveEntityEffectPacket(entity.getId(), event.getPotionEffect().getEffect()));
        }
    }

    @SubscribeEvent
    public static void syncTrackedEffects(PlayerEvent.StartTracking event) {
        if (event.getTarget() instanceof LivingEntity) {
            ServerPlayerEntity player = (ServerPlayerEntity) event.getPlayer();
            LivingEntity tracked = (LivingEntity) event.getTarget();
            for (Map.Entry<Effect, EffectInstance> effectEntry : tracked.getActiveEffectsMap().entrySet()) {
                if (InitEffects.isEffectTracked(effectEntry.getKey())) {
                    player.connection.send(new SPlayEntityEffectPacket(tracked.getId(), effectEntry.getValue()));
                }
            }
        }
    }
}
