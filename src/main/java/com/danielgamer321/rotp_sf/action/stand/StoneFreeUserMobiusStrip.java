package com.danielgamer321.rotp_sf.action.stand;

import com.danielgamer321.rotp_sf.capability.entity.LivingUtilCapProvider;
import com.danielgamer321.rotp_sf.init.InitEffects;
import com.danielgamer321.rotp_sf.network.PacketManager;
import com.danielgamer321.rotp_sf.network.packets.fromserver.TrSetMobiusStripPacket;
import com.github.standobyte.jojo.action.ActionTarget;
import com.github.standobyte.jojo.action.stand.StandAction;
import com.github.standobyte.jojo.power.impl.stand.IStandPower;

import com.github.standobyte.jojo.util.general.LazySupplier;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.stream.Stream;

public class StoneFreeUserMobiusStrip extends StandAction {
    public StoneFreeUserMobiusStrip(StandAction.Builder builder) {
        super(builder);
    }

    @Override
    protected void perform(World world, LivingEntity user, IStandPower power, ActionTarget target) {
        if (!world.isClientSide()) {
            if (!getMode(power)) {
                setModeServerSide(user, true);
            }
            else {
                setModeServerSide(user, false);
                user.removeEffect(InitEffects.MOBIUS_STRIP.get());
            }
        }
    }

    public static void setModeServerSide(LivingEntity user, boolean status) {
        user.getCapability(LivingUtilCapProvider.CAPABILITY).ifPresent(cap -> cap.setMobiusStripMode(status));
        if (!user.level.isClientSide()) {
            PacketManager.sendToClientsTrackingAndSelf(new TrSetMobiusStripPacket(user.getId(), status), user);
        }
    }

    private final LazySupplier<ResourceLocation> offTex =
            new LazySupplier<>(() -> makeIconVariant(this, "_off"));
    @Override
    public ResourceLocation getIconTexturePath(@Nullable IStandPower power) {
        if (power != null && !getMode(power)) {
            return offTex.get();
        }
        else {
            return super.getIconTexturePath(power);
        }
    }

    public static boolean getMode(IStandPower power) {
        LivingEntity user = power.getUser();
        return user.getCapability(LivingUtilCapProvider.CAPABILITY).map(cap -> cap.getMobiusStripMode()).orElse(false);
    }



    @Deprecated
    private ResourceLocation offTexPath;
    @Deprecated
    @Override
    public ResourceLocation getTexture(IStandPower power) {
        ResourceLocation resLoc = getRegistryName();
        if (!getMode(power)) {
            if (offTexPath == null) {
                offTexPath = new ResourceLocation(resLoc.getNamespace(), resLoc.getPath() + "_off");
            }
            resLoc = offTexPath;
        }
        return resLoc;
    }

    @Deprecated
    @Override
    public Stream<ResourceLocation> getTexLocationstoLoad() {
        ResourceLocation resLoc = getRegistryName();
        return Stream.of(resLoc, new ResourceLocation(resLoc.getNamespace(), resLoc.getPath() + "_off"));
    }
}
