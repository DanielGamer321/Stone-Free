package com.danielgamer321.rotp_sf.entity.damaging.projectile.ownerbound;

import com.danielgamer321.rotp_sf.init.InitEntities;
import com.danielgamer321.rotp_sf.init.InitSounds;
import com.github.standobyte.jojo.entity.damaging.projectile.ownerbound.OwnerBoundProjectileEntity;
import com.github.standobyte.jojo.entity.stand.StandEntity;
import com.github.standobyte.jojo.init.ModStatusEffects;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.network.PacketBuffer;
import net.minecraft.potion.EffectInstance;
import net.minecraft.util.math.EntityRayTraceResult;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.World;

public class SFStringEntity extends OwnerBoundProjectileEntity {
    private float yRotOffset;
    private float xRotOffset;
    private boolean isCapture;
    private boolean dealtDamage;

    public SFStringEntity(World world, LivingEntity entity, float angleXZ, float angleYZ, boolean isCapture) {
        super(InitEntities.SF_STRING.get(), entity, world);
        this.yRotOffset = angleXZ;
        this.xRotOffset = angleYZ;
        this.isCapture = isCapture;
    }
    
    public SFStringEntity(EntityType<? extends SFStringEntity> entityType, World world) {
        super(entityType, world);
    }

    @Override
    public void tick() {
        super.tick();
        if (!isAlive()) {
            return;
        }
        if (isCapture) {
            LivingEntity bound = getEntityAttachedTo();
            if (bound != null) {
                LivingEntity owner = getOwner();
                if (!bound.isAlive()) {
                    if (!level.isClientSide()) {
                        remove();
                    }
                }
                else if (owner != null) {
                    Vector3d vecToOwner = owner.position().subtract(bound.position());
                    Vector3d vecToUser = owner instanceof StandEntity && ((StandEntity)owner).getUser() != null ?
                            owner.position().subtract(bound.position()) : null;
                    double distance = vecToUser != null ? vecToUser.length() : 0;
                    if (vecToOwner.length() < 2 || distance < 4) {
                        bound.addEffect(new EffectInstance(ModStatusEffects.IMMOBILIZE.get(), ticksLifespan() - tickCount));
                        if (!level.isClientSide()) {
                            isCapture = false;
                        }
                    }
                    else {
                        dragTarget(bound, vecToOwner.normalize().scale(1));
                        bound.fallDistance = 0;
                    }
                }
            }
        }
    }

    @Override
    public boolean standDamage() {
        return true;
    }
    
    @Override
    public float getBaseDamage() {
        return 0.30769231F;
    }
    
    @Override
    protected boolean hurtTarget(Entity target, LivingEntity owner) {
        if (getEntityAttachedTo() == null) {
            if (target instanceof LivingEntity) {
                attachToEntity((LivingEntity) target);
                playSound(InitSounds.STONE_FREE_GRAPPLE_CATCH.get(), 1.0F, 1.0F);
                return true;
            }
        }
        return !dealtDamage ? super.hurtTarget(target, owner) : false;
    }
    
    @Override
    protected boolean shouldHurtThroughInvulTicks() {
        return true;
    }
    
    @Override
    protected void afterEntityHit(EntityRayTraceResult entityRayTraceResult, boolean entityHurt) {
        if (entityHurt) {
            Entity target = entityRayTraceResult.getEntity();
            if (!(target instanceof SFStringEntity || (target instanceof SFUStringEntity &&
                    ((SFUStringEntity)target).isBinding()))) {
                dealtDamage = true;
            }
            if (target instanceof LivingEntity) {
                LivingEntity livingTarget = (LivingEntity) target;
                attachToEntity(livingTarget);
            }
        }
    }
    
    @Override
    protected float knockbackMultiplier() {
        return 0F;
    }
    
    @Override
    protected float getMaxHardnessBreakable() {
        return 0.0F;
    }

    @Override
    public int ticksLifespan() {
        int ticks = super.ticksLifespan();
        if (isAttachedToAnEntity()) {
            ticks += 25;
        }
        return ticks;
    }
    
    @Override
    protected float movementSpeed() {
        return 16 / (float) ticksLifespan();
    }
    
    @Override
    public boolean isBodyPart() {
        return true;
    }
    
    private static final Vector3d FRONT_OFFSET = new Vector3d(-0.3D, 0.1D, 0.4D);

    @Override
    protected Vector3d getXRotOffset() {
        return FRONT_OFFSET;
    }

    @Override
    protected Vector3d originOffset(float yRot, float xRot, double distance) {
        return super.originOffset(yRot + yRotOffset, xRot + xRotOffset, distance);
    }

    @Override
    public void writeSpawnData(PacketBuffer buffer) {
        super.writeSpawnData(buffer);
        buffer.writeFloat(yRotOffset);
        buffer.writeFloat(xRotOffset);
        buffer.writeBoolean(isCapture);
    }

    @Override
    public void readSpawnData(PacketBuffer additionalData) {
        super.readSpawnData(additionalData);
        this.yRotOffset = additionalData.readFloat();
        this.xRotOffset = additionalData.readFloat();
        this.isCapture = additionalData.readBoolean();
    }
}
