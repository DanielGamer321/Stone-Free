package com.danielgamer321.rotp_sf.entity.damaging.projectile.ownerbound;

import com.danielgamer321.rotp_sf.capability.entity.PlayerUtilCapProvider;
import com.danielgamer321.rotp_sf.init.InitEntities;
import com.danielgamer321.rotp_sf.power.impl.stand.type.StoneFreeStandType;
import com.github.standobyte.jojo.entity.damaging.projectile.ownerbound.OwnerBoundProjectileEntity;
import com.github.standobyte.jojo.power.impl.stand.IStandPower;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.World;

public class SFUStringSpikeEntity extends OwnerBoundProjectileEntity {
    private LivingEntity user;
    private IStandPower userStandPower;

    public SFUStringSpikeEntity(World world, LivingEntity entity, IStandPower userStand) {
        super(InitEntities.SFU_STRING_SPIKE.get(), entity, world);
        this.user = entity;
        this.userStandPower = userStand;
    }

    public SFUStringSpikeEntity(EntityType<? extends SFUStringSpikeEntity> entityType, World world) {
        super(entityType, world);   
    }

    @Override
    public boolean standDamage() {
        return true;
    }
    
    @Override
    public float getBaseDamage() {
        return 7F;
    }
    
    @Override
    protected float getMaxHardnessBreakable() {
        return 1F;
    }

    @Override
	public int ticksLifespan() {
        return 16;
    }
    
    @Override
    protected float movementSpeed() {
        return 1.5F;
    }
    
    @Override
    protected int timeAtFullLength() {
        return 4;
    }
    
    @Override
    protected float retractSpeed() {
        return 1.5F;
    }
    
    @Override
	public boolean isBodyPart() {
        return true;
    }

    private static final Vector3d OFFSET = new Vector3d(-0.22D, -0.75D, 0.25D);
    @Override
    protected Vector3d getOwnerRelativeOffset() {
        return OFFSET;
    }
}
