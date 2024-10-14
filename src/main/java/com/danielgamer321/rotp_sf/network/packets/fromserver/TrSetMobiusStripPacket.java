package com.danielgamer321.rotp_sf.network.packets.fromserver;

import com.danielgamer321.rotp_sf.capability.entity.LivingUtilCapProvider;
import com.github.standobyte.jojo.client.ClientUtil;
import com.github.standobyte.jojo.network.packets.IModPacketHandler;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.fml.network.NetworkEvent;

import java.util.function.Supplier;

public class TrSetMobiusStripPacket {
    private final int entityId;
    private final boolean status;

    public TrSetMobiusStripPacket(int entityId, boolean status) {
        this.entityId = entityId;
        this.status = status;
    }
    
    
    
    public static class Handler implements IModPacketHandler<TrSetMobiusStripPacket> {

        @Override
        public void encode(TrSetMobiusStripPacket msg, PacketBuffer buf) {
            buf.writeInt(msg.entityId);
            buf.writeBoolean(msg.status);
        }

        @Override
        public TrSetMobiusStripPacket decode(PacketBuffer buf) {
            return new TrSetMobiusStripPacket(buf.readInt(), buf.readBoolean());
        }

        @Override
        public void handle(TrSetMobiusStripPacket msg, Supplier<NetworkEvent.Context> ctx) {
            Entity entity = ClientUtil.getEntityById(msg.entityId);
            if (entity != null) {
                LivingEntity user = (LivingEntity) entity;
                user.getCapability(LivingUtilCapProvider.CAPABILITY).ifPresent(cap -> cap.setMobiusStripMode(msg.status));
            }
        }

        @Override
        public Class<TrSetMobiusStripPacket> getPacketClass() {
            return TrSetMobiusStripPacket.class;
        }
    }

}
