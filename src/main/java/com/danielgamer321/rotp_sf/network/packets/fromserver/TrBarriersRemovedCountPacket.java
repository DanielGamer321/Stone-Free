package com.danielgamer321.rotp_sf.network.packets.fromserver;

import com.danielgamer321.rotp_sf.capability.entity.LivingUtilCapProvider;
import com.github.standobyte.jojo.client.ClientUtil;
import com.github.standobyte.jojo.network.packets.IModPacketHandler;

import net.minecraft.entity.Entity;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.fml.network.NetworkEvent;

import java.util.function.Supplier;

public class TrBarriersRemovedCountPacket {
    private final int entityId;
    private final int barriers;

    public TrBarriersRemovedCountPacket(int entityId, int barriers) {
        this.entityId = entityId;
        this.barriers = barriers;
    }
    
    
    
    public static class Handler implements IModPacketHandler<TrBarriersRemovedCountPacket> {

        @Override
        public void encode(TrBarriersRemovedCountPacket msg, PacketBuffer buf) {
            buf.writeInt(msg.entityId);
            buf.writeVarInt(msg.barriers);
        }

        @Override
        public TrBarriersRemovedCountPacket decode(PacketBuffer buf) {
            return new TrBarriersRemovedCountPacket(buf.readInt(), buf.readVarInt());
        }

        @Override
        public void handle(TrBarriersRemovedCountPacket msg, Supplier<NetworkEvent.Context> ctx) {
            Entity entity = ClientUtil.getEntityById(msg.entityId);
            if (entity != null) {
                entity.getCapability(LivingUtilCapProvider.CAPABILITY).ifPresent(cap -> cap.setBarriersRemovedCount(msg.barriers));
            }
        }

        @Override
        public Class<TrBarriersRemovedCountPacket> getPacketClass() {
            return TrBarriersRemovedCountPacket.class;
        }
    }

}
