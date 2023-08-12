package com.danielgamer321.rotp_sf.entity.damaging.projectile.ownerbound;

import com.danielgamer321.rotp_sf.init.InitEntities;
import com.danielgamer321.rotp_sf.init.InitStands;
import com.github.standobyte.jojo.entity.damaging.projectile.ownerbound.OwnerBoundProjectileEntity;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.World;

public class SFExtendedPunchEntity extends OwnerBoundProjectileEntity {

    public SFExtendedPunchEntity(World world, LivingEntity entity) {
        super(InitEntities.SF_EXTENDED_PUNCH.get(), entity, world);
    }
    
    public SFExtendedPunchEntity(EntityType<? extends SFExtendedPunchEntity> entityType, World world) {
        super(entityType, world);   
    }

    @Override
    public boolean standDamage() {
        return true;
    }
    
    @Override
    public float getBaseDamage() {
        return 5.53846158F;
    }
    
    @Override
    protected float getMaxHardnessBreakable() {
        return 5.0F;
    }

    @Override
	public int ticksLifespan() {
        return InitStands.STONE_FREE_EXTENDED_PUNCH.get().getStandActionTicks(null, null);
    }
    
    @Override
    protected float movementSpeed() {
        return 0.3F;
    }
    
    @Override
    protected int timeAtFullLength() {
        return 4;
    }
    
    @Override
    protected float retractSpeed() {
        return movementSpeed() * 3F;
    }
    
    @Override
	public boolean isBodyPart() {
        return true;
    }

    private static final Vector3d OFFSET = new Vector3d(-0.3, -0.2, 0.55);
    @Override
    protected Vector3d getOwnerRelativeOffset() {
        return OFFSET;
    }
}
