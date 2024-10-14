package com.danielgamer321.rotp_sf.network.packets.fromserver;

import java.util.function.Supplier;

import com.danielgamer321.rotp_sf.capability.entity.LivingUtilCapProvider;
import com.github.standobyte.jojo.client.ClientUtil;
import com.github.standobyte.jojo.network.packets.IModPacketHandler;

import net.minecraft.entity.Entity;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.fml.network.NetworkEvent;

public class TrPlacedBarriersCountPacket {
    private final int entityId;
    private final int barriers;

    public TrPlacedBarriersCountPacket(int entityId, int barriers) {
        this.entityId = entityId;
        this.barriers = barriers;
    }
    
    
    
    public static class Handler implements IModPacketHandler<TrPlacedBarriersCountPacket> {

        @Override
        public void encode(TrPlacedBarriersCountPacket msg, PacketBuffer buf) {
            buf.writeInt(msg.entityId);
            buf.writeVarInt(msg.barriers);
        }

        @Override
        public TrPlacedBarriersCountPacket decode(PacketBuffer buf) {
            return new TrPlacedBarriersCountPacket(buf.readInt(), buf.readVarInt());
        }

        @Override
        public void handle(TrPlacedBarriersCountPacket msg, Supplier<NetworkEvent.Context> ctx) {
            Entity entity = ClientUtil.getEntityById(msg.entityId);
            if (entity != null) {
                entity.getCapability(LivingUtilCapProvider.CAPABILITY).ifPresent(cap -> cap.setPlacedBarriersCount(msg.barriers));
            }
        }

        @Override
        public Class<TrPlacedBarriersCountPacket> getPacketClass() {
            return TrPlacedBarriersCountPacket.class;
        }
    }

}
