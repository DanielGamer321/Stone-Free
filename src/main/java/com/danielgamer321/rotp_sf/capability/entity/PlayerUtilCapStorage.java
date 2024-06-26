package com.danielgamer321.rotp_sf.capability.entity;

import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.INBT;
import net.minecraft.util.Direction;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.Capability.IStorage;

public class PlayerUtilCapStorage implements IStorage<PlayerUtilCap> {

    @Override
    public INBT writeNBT(Capability<PlayerUtilCap> capability, PlayerUtilCap instance, Direction side) {
        CompoundNBT cnbt = new CompoundNBT();
        return cnbt;
    }

    @Override
    public void readNBT(Capability<PlayerUtilCap> capability, PlayerUtilCap instance, Direction side, INBT nbt) {
        CompoundNBT cnbt = (CompoundNBT) nbt;
        
        if (cnbt.contains("NotificationsSent", 10)) {
            CompoundNBT notificationsMap = cnbt.getCompound("NotificationsSent");
        }
    }
}