package com.danielgamer321.rotp_sf.entity.damaging.projectile.ownerbound;

import com.danielgamer321.rotp_sf.init.InitEntities;
import com.danielgamer321.rotp_sf.init.InitSounds;
import com.danielgamer321.rotp_sf.init.InitStands;
import com.github.standobyte.jojo.entity.damaging.projectile.ownerbound.OwnerBoundProjectileEntity;
import com.github.standobyte.jojo.entity.stand.StandEntity;
import com.github.standobyte.jojo.power.impl.stand.IStandPower;
import com.github.standobyte.jojo.util.mod.JojoModUtil;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.MoverType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;

public class SFUGrapplingStringEntity extends OwnerBoundProjectileEntity {
    private IStandPower userStandPower;
    private boolean bindEntities;
    private LivingEntity user;
    private boolean caughtAnEntity = false;

    public SFUGrapplingStringEntity(World world, LivingEntity user, IStandPower userStand) {
        super(InitEntities.SFU_GRAPPLING_STRING.get(), user, world);
        this.user = user;
        this.userStandPower = userStand;
    }

    public SFUGrapplingStringEntity(EntityType<? extends SFUGrapplingStringEntity> entityType, World world) {
        super(entityType, world);
    }
    
    @Override
    public void tick() {
        super.tick();
        LivingEntity owner = getOwner();
        if (owner != null) {
            double distanceSqr = distanceToSqr(owner);
            if (!level.isClientSide() && distanceSqr > 576 && owner instanceof PlayerEntity) {
                double horizontalDistSqr = distanceSqr - Math.pow(getY() - owner.getY(), 2);
                int warningDistance = ((ServerWorld) level).getServer().getPlayerList().getViewDistance() * 16 - 4;
                if (horizontalDistSqr > warningDistance * warningDistance) {
                    remove();
                }
            }
            getUserStandPower().ifPresent(stand -> {
                stand.consumeStamina(1, true);
            });
        }
        if (!isAlive()) {
            return;
        }
        if (!level.isClientSide() && (userStandPower == null || userStandPower.getHeldAction() ==
                InitStands.STONE_FREE_USER_RECOVER_STRING.get())) {
            remove();
            return;
        }
        LivingEntity bound = getEntityAttachedTo();
        if (bound != null) {
            if (!bound.isAlive()) {
                if (!level.isClientSide()) {
                    remove();
                }
            }
            else if (owner != null) {
                Vector3d vecToOwner = owner.position().subtract(bound.position());
                double length = vecToOwner.length();
                if (length < 2) {
                	if (!level.isClientSide()) {
                		remove();
                	}
                }
            }
        }
    }

    public void setBindEntities(boolean bindEntities) {
        this.bindEntities = bindEntities;
    }
    
    @Override
    protected boolean moveToBlockAttached() {   
        if (super.moveToBlockAttached()) {
            LivingEntity owner = getOwner();
            Vector3d vecFromOwner = position().subtract(owner.position());
            if (vecFromOwner.lengthSqr() > 4) {
                Vector3d grappleVec = vecFromOwner.normalize().scale(2);
                Entity entity = owner;
                if (user == null && owner instanceof LivingEntity) {
                    user = (LivingEntity) owner;
                }
                if (user != null) {
                    entity = user;
                }
                entity = entity.getRootVehicle();
                entity.setDeltaMovement(grappleVec);
                entity.fallDistance = 0;
            }
            else if (!level.isClientSide()) {
            	remove();
            }
            return true;
        }
        return false;
    }

    @Override
    protected boolean moveToEntityAttached() {
        if (super.moveToEntityAttached()) {
            LivingEntity owner = getOwner();
            Vector3d vecFromOwner = position().subtract(owner.position());
            if (vecFromOwner.lengthSqr() > 4) {
                Vector3d grappleVec = vecFromOwner.normalize().scale(1.5);
                Entity entity = owner;
                if (user == null && owner instanceof LivingEntity) {
                    user = (LivingEntity) owner;
                }
                if (user != null) {
                    entity = user;
                }
                entity = entity.getRootVehicle();
                entity.setDeltaMovement(grappleVec);
                entity.fallDistance = 0;
            }
            else if (!level.isClientSide()) {
                remove();
            }
            return true;
        }
        return false;
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

    @Override
	public int ticksLifespan() {
        return getEntityAttachedTo() == null && !getBlockPosAttachedTo().isPresent() ? 40 : Integer.MAX_VALUE;
    }
    
    @Override
    protected float movementSpeed() {
        return 4F;
    }
    
    @Override
    protected boolean canHitEntity(Entity entity) {
    	LivingEntity owner = getOwner();
    	if (entity.is(owner) || !(entity instanceof LivingEntity)) {
    		return false;
    	}
    	if (owner instanceof StandEntity) {
    		StandEntity stand = (StandEntity) getOwner();
    		return !entity.is(stand.getUser()) || !stand.isFollowingUser();
    	}
        return true;
    }
    
    @Override
    protected boolean hurtTarget(Entity target, LivingEntity owner) {
        if (getEntityAttachedTo() == null && bindEntities) {
            if (target instanceof LivingEntity) {
                LivingEntity livingTarget = (LivingEntity) target;
                if (!JojoModUtil.isTargetBlocking(livingTarget)) {
                    attachToEntity((LivingEntity) target);
                    playSound(InitSounds.STONE_FREE_GRAPPLE_CATCH.get(), 1.0F, 1.0F);
            	    caughtAnEntity = true;
                    return true;
                }
            }
        }
        return false;
    }
    
    @Override
    protected void updateMotionFlags() {}

    @Override
    protected void afterBlockHit(BlockRayTraceResult blockRayTraceResult, boolean brokenBlock) {
        if (!brokenBlock && !bindEntities) {
            if (!getBlockPosAttachedTo().isPresent()) {
                playSound(InitSounds.STONE_FREE_GRAPPLE_CATCH.get(), 1.0F, 1.0F);
                attachToBlockPos(blockRayTraceResult.getBlockPos());
            }
        }
    }

    @Override
    protected void defineSynchedData() {
        super.defineSynchedData();
    }

    @Override
    public float getBaseDamage() {
        return 0;
    }

    @Override
    protected float getMaxHardnessBreakable() {
        return 0;
    }

    @Override
    public boolean standDamage() {
        return true;
    }
}
