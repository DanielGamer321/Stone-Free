package com.danielgamer321.rotp_sf.entity.stand.stands;

import java.util.UUID;

import com.danielgamer321.rotp_sf.entity.damaging.projectile.ownerbound.SFUBarrierEntity;
import com.github.standobyte.jojo.entity.stand.StandEntity;
import com.github.standobyte.jojo.entity.stand.StandEntityType;

import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.ai.attributes.ModifiableAttributeInstance;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.world.World;

public class StoneFreeEntity extends StandEntity {
    private static final UUID SPEED_MODIFIER_RETRACTION_UUID = UUID.fromString("a421b1ab-85a8-4164-a9ba-dbda0bc560ce");
    private static final AttributeModifier SPEED_MODIFIER_RETRACTION = new AttributeModifier(SPEED_MODIFIER_RETRACTION_UUID, "Retraction speed boost", 2.0D, AttributeModifier.Operation.MULTIPLY_TOTAL);
    private static final DataParameter<Boolean> CAN_CAPTURE = EntityDataManager.defineId(StoneFreeEntity.class, DataSerializers.BOOLEAN);
    private static final DataParameter<Boolean> HAS_FOREARM = EntityDataManager.defineId(StoneFreeEntity.class, DataSerializers.BOOLEAN);    
    private static final DataParameter<Boolean> HAS_SHORT_FOREARM = EntityDataManager.defineId(StoneFreeEntity.class, DataSerializers.BOOLEAN);

    private SFUBarrierEntity stringToUser;
    private SFUBarrierEntity stringFromStand;

    public StoneFreeEntity(StandEntityType<StoneFreeEntity> type, World world) {
        super(type, world);
    }

    @Override
    protected void defineSynchedData() {
        super.defineSynchedData();
        entityData.define(CAN_CAPTURE, true);
        entityData.define(HAS_FOREARM, true);
        entityData.define(HAS_SHORT_FOREARM, false);
    }

    public boolean canCapture() {
        return entityData.get(CAN_CAPTURE);
    }

    public void setCapture(boolean capture) {
        entityData.set(CAN_CAPTURE, capture);
    }
    
    public boolean hasForeArm() {
        return entityData.get(HAS_FOREARM);
    }

    public void setForeArm(boolean forearm) {
        entityData.set(HAS_FOREARM, forearm);
    }
    
    public boolean hasShortForeArm() {
        return entityData.get(HAS_SHORT_FOREARM);
    }

    public void setShortForeArm(boolean shortforearm) {
        entityData.set(HAS_SHORT_FOREARM, shortforearm);
    }

    @Override
    protected void setStandFlag(StandFlag flag, boolean value) {
        super.setStandFlag(flag, value);
        if (flag == StandFlag.BEING_RETRACTED && !value && isCloseToUser()) {
            if (stringToUser != null && stringToUser.isAlive() && stringToUser.is(stringFromStand)) {
                ModifiableAttributeInstance speedAttributeInstance = getAttribute(Attributes.MOVEMENT_SPEED);
                if (speedAttributeInstance.getModifier(SPEED_MODIFIER_RETRACTION_UUID) != null) {
                    speedAttributeInstance.removeModifier(SPEED_MODIFIER_RETRACTION);
                }
                stringToUser.remove();
            }
        }
    }
}
