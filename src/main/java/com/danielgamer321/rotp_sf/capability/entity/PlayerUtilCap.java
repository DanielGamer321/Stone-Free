package com.danielgamer321.rotp_sf.capability.entity;

import com.danielgamer321.rotp_sf.action.stand.*;
import com.danielgamer321.rotp_sf.entity.damaging.projectile.ownerbound.SFUBarrierEntity;
import com.danielgamer321.rotp_sf.init.InitSounds;
import com.danielgamer321.rotp_sf.network.packets.fromserver.TrPlacedBarriersCountPacket;
import com.danielgamer321.rotp_sf.network.packets.fromserver.TrBarriersRemovedCountPacket;
import com.danielgamer321.rotp_sf.network.PacketManager;
import com.danielgamer321.rotp_sf.util.SFBarriersNet;
import com.github.standobyte.jojo.power.impl.stand.IStandPower;
import com.github.standobyte.jojo.power.impl.stand.StandUtil;
import com.github.standobyte.jojo.util.mc.MCUtil;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;

public class PlayerUtilCap {
    private final PlayerEntity player;
    private int PLACED_BARRIERS;
    private int BARRIERS_REMOVED;
    private int removePlacedBarriersTime;
    private int removeBarriersRemovedTime;
    private SFUBarrierEntity stringToUser;
    private SFUBarrierEntity stringFromStand;
    private SFBarriersNet placedBarriers = new SFBarriersNet();
    private SFBarriersNet barriersRemoved = new SFBarriersNet();
    private boolean newNet = false;
    private boolean userString = true;
    private boolean grapple = false;

    public PlayerUtilCap(PlayerEntity player) {
        this.player = player;
    }

    public void tick() {
        if (!player.level.isClientSide()) {
            tickPlacedBarriersRemoval();
            tickBarriersRemovedRemoval();
            placedBarriers.tick();
            barriersRemoved.tick();
            setPlacedBarriersCount(placedBarriers.getSize());
            setBarriersRemovedCount(barriersRemoved.getNegativeSize());
        }
    }

    public boolean userString(LivingEntity user) {
        if (StoneFreeUserStringAttack.getLandedString(user) ||StoneFreeUserStringSweep.getLandedString(user) ||StoneFreeUserStringWhip.getLandedString(user) || StoneFreeUserStringBind.getLandedString(user) || StoneFreeUserStringPick.getLandedString(user) || StoneFreeUserGrapple.getLandedString(user)) {
            userString = false;
        }
        else {
            userString = true;
        }
        return userString;
    }

    public boolean grapple(LivingEntity user) {
        if (StoneFreeUserGrapple.getLandedString(user)) {
            grapple = true;
        }
        else {
            grapple = false;
        }
        return grapple;
    }

    public SFBarriersNet getBarriersNet() {
        return placedBarriers;
    }

    public boolean canPlaceBarrier(IStandPower power) {
        return getPlacedBarriersCount() < StoneFreeUserBarrier.getMaxBarriersPlaceable(power);
    }

    public void createUserString(IStandPower power) {
        LivingEntity user = power.getUser();
        if (!user.level.isClientSide()) {
            if (stringToUser == null || !stringToUser.isAlive()) {
                boolean mode = false;
                stringToUser = new SFUBarrierEntity(user.level, user, power);
                if (stringFromStand != null && stringFromStand.isAlive()) {
                    stringFromStand.remove();
                }
                stringFromStand = stringToUser;
                user.level.addFreshEntity(stringFromStand);
            }
        }
    }

    public boolean hasBarrierAttached() {
        return getPlacedBarriersCount() > 0 ||
                stringToUser != null && stringToUser.isAlive() && stringToUser != stringFromStand;
    }

    public void attachBarrier(BlockPos blockPos, IStandPower power) {
        LivingEntity user = power.getUser();
        if (!user.level.isClientSide()) {
            if (!canPlaceBarrier(power)) {
                return;
            }
            if (stringToUser != null && stringToUser.isAlive()) {
                if (blockPos.equals(stringToUser.getOriginBlockPos())) {
                    return;
                }
                stringToUser.attachToBlockPos(blockPos);
                placedBarriers.add(stringToUser);
                setPlacedBarriersCount(placedBarriers.getSize());
            }
            stringToUser = new SFUBarrierEntity(user.level, user, power);
            stringToUser.setOriginBlockPos(blockPos);
            user.level.addFreshEntity(stringToUser);
            MCUtil.playSound(user.level, null, user, InitSounds.STONE_FREE_BARRIER_PLACED.get(),
                    SoundCategory.AMBIENT, 1.0F, 1.0F, StandUtil::playerCanHearStands);
        }
    }

    public int fixPlacedBarriersCount() {
        return getPlacedBarriersCount() - getBarriersRemovedCount();
    }

    public void setPlacedBarriersCount(int barriers) {
        barriers = Math.max(barriers, 0);
        if (this.PLACED_BARRIERS != barriers) {
            this.PLACED_BARRIERS = barriers;
            if (!player.level.isClientSide()) {
                PacketManager.sendToClientsTrackingAndSelf(new TrPlacedBarriersCountPacket(player.getId(), barriers), player);
            }
        }
    }
    
    public void addPlacedBarriers() {
        setPlacedBarriersCount(PLACED_BARRIERS + 1);
    }
    
    public int getPlacedBarriersCount() {
        return PLACED_BARRIERS;
    }
    
    private void tickPlacedBarriersRemoval() {
        if (PLACED_BARRIERS > 0) {
            if (removePlacedBarriersTime <= 0) {
                removePlacedBarriersTime = 20 * (30 - PLACED_BARRIERS);
            }
            removePlacedBarriersTime--;
            if (removePlacedBarriersTime <= 0) {
                setPlacedBarriersCount(PLACED_BARRIERS - 1);
            }
        }
    }

    public void setBarriersRemovedCount(int barriers) {
        barriers = Math.max(barriers, 0);
        if (this.BARRIERS_REMOVED != barriers) {
            this.BARRIERS_REMOVED = barriers;
            if (!player.level.isClientSide()) {
                PacketManager.sendToClientsTrackingAndSelf(new TrBarriersRemovedCountPacket(player.getId(), barriers), player);
            }
        }
    }

    public void addBarriersRemoved() {
        setBarriersRemovedCount(BARRIERS_REMOVED + 1);
    }

    public int getBarriersRemovedCount() {
        return BARRIERS_REMOVED;
    }

    private void tickBarriersRemovedRemoval() {
        if (BARRIERS_REMOVED > 0) {
            if (removeBarriersRemovedTime <= 0) {
                removeBarriersRemovedTime = 20 * (30 - BARRIERS_REMOVED);
            }
            removeBarriersRemovedTime--;
            if (removeBarriersRemovedTime <= 0) {
                setBarriersRemovedCount(BARRIERS_REMOVED - 1);
            }
        }
    }


    public void onTracking(ServerPlayerEntity tracking) {
        PacketManager.sendToClient(new TrPlacedBarriersCountPacket(player.getId(), PLACED_BARRIERS), tracking);
        PacketManager.sendToClient(new TrBarriersRemovedCountPacket(player.getId(), BARRIERS_REMOVED), tracking);
    }
}
