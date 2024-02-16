package com.danielgamer321.rotp_sf.entity.damaging.projectile.ownerbound;

import com.danielgamer321.rotp_sf.init.InitEntities;
import com.danielgamer321.rotp_sf.init.InitSounds;
import com.github.standobyte.jojo.entity.damaging.projectile.ownerbound.OwnerBoundProjectileEntity;
import com.github.standobyte.jojo.init.ModStatusEffects;
import com.github.standobyte.jojo.util.mod.JojoModUtil;

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
    private float knockback = 0;

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
                else {
                    Vector3d vecToOwner = owner.position().subtract(bound.position());
                    double length = vecToOwner.length();
                    if (length < 2) {
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
    
    public boolean isCapture() {
        return isCapture;
    }
    
    @Override
    public float getBaseDamage() {
        return 0.30769231F;
    }
    
    public void addKnockback(float knockback) {
        this.knockback = knockback;
    }
    
    @Override
    protected boolean hurtTarget(Entity target, LivingEntity owner) {
        if (getEntityAttachedTo() == null) {
            if (target instanceof LivingEntity) {
                LivingEntity livingTarget = (LivingEntity) target;
                if (!JojoModUtil.isTargetBlocking(livingTarget)) {
                    attachToEntity((LivingEntity) target);
                    playSound(InitSounds.STONE_FREE_GRAPPLE_CATCH.get(), 1.0F, 1.0F);
                    return true;
                }
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
            dealtDamage = true;
            Entity target = entityRayTraceResult.getEntity();
            if (target instanceof LivingEntity) {
                LivingEntity livingTarget = (LivingEntity) target;
                if (!JojoModUtil.isTargetBlocking(livingTarget)) {
                    attachToEntity(livingTarget);
                    livingTarget.addEffect(new EffectInstance(ModStatusEffects.IMMOBILIZE.get(), ticksLifespan() - tickCount));
                }
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
            ticks += 10;
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
