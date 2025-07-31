package com.danielgamer321.rotp_sf.entity.damaging.projectile.ownerbound;

import com.danielgamer321.rotp_sf.init.InitEntities;
import com.danielgamer321.rotp_sf.init.InitSounds;
import com.danielgamer321.rotp_sf.init.InitStands;
import com.github.standobyte.jojo.entity.damaging.projectile.ownerbound.OwnerBoundProjectileEntity;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.EntityRayTraceResult;
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
        return 5.1429F;
    }
    
    @Override
    protected float getMaxHardnessBreakable() {
        return 5.0F;
    }

    @Override
    protected void afterBlockHit(BlockRayTraceResult blockRayTraceResult, boolean blockDestroyed) {
        playSound(InitSounds.STONE_FREE_PUNCH_LIGHT.get(), 1.0F, 1.0F);
        setIsRetracting(true);
    }

    @Override
    protected boolean hurtTarget(Entity target, LivingEntity owner) {
        playSound(InitSounds.STONE_FREE_PUNCH_LIGHT.get(), 1.0F, 1.0F);
        return super.hurtTarget(target, owner);
    }

    @Override
    protected void afterEntityHit(EntityRayTraceResult entityRayTraceResult, boolean entityHurt) {
        super.afterEntityHit(entityRayTraceResult, entityHurt);
        Entity target = entityRayTraceResult.getEntity();
        if (!(target instanceof SFStringEntity || (target instanceof SFUStringEntity &&
                ((SFUStringEntity)target).isBinding()))) {
            setIsRetracting(true);
        }
    }

    @Override
	public int ticksLifespan() {
        return InitStands.STONE_FREE_EXTENDED_PUNCH.get().getStandActionTicks(null, null);
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

    private static final Vector3d OFFSET = new Vector3d(-0.3D, -0.2D, 0.25D);
    @Override
    protected Vector3d getOwnerRelativeOffset() {
        return OFFSET;
    }
}
