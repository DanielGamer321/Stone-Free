package com.danielgamer321.rotp_sf.entity.damaging.projectile.ownerbound;

import com.danielgamer321.rotp_sf.init.InitEntities;
import com.github.standobyte.jojo.entity.damaging.projectile.ownerbound.OwnerBoundProjectileEntity;
import com.github.standobyte.jojo.power.impl.stand.IStandPower;
import com.github.standobyte.jojo.util.mc.damage.DamageUtil;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.math.EntityRayTraceResult;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.World;

public class SFUStringSweepEntity extends OwnerBoundProjectileEntity {
    private LivingEntity user;
    private IStandPower userStandPower;
    private float yRotOffset;
    private boolean dealtDamage;
    private float knockback = 0;

    public SFUStringSweepEntity(World world, LivingEntity entity, IStandPower userStand) {
        super(InitEntities.SFU_STRING_SWEEP.get(), entity, world);
        this.user = entity;
        this.userStandPower = userStand;
        initYRotOffset();
    }

    public SFUStringSweepEntity(EntityType<? extends SFUStringSweepEntity> entityType, World world) {
        super(entityType, world);
    }
    
    private void initYRotOffset() {
        yRotOffset = 67.5F;
    }
    
    @Override
    protected float updateDistance() {
        if (yRotOffset > -67.5F) {
            yRotOffset = Math.max(yRotOffset - 135F / ticksLifespan(), -67.5F);
        }
        return super.updateDistance();
    }
    
    @Override
    protected float movementSpeed() {
        return 1.5F;
    }

    @Override
    public int ticksLifespan() {
        return 10;
    }

    @Override
    protected Vector3d originOffset(float yRot, float xRot, double distance) {
        return super.originOffset(yRot + yRotOffset, xRot , distance);
    }

    public void addKnockback(float knockback) {
        this.knockback = knockback;
    }
    
    @Override
    protected boolean hurtTarget(Entity target, LivingEntity owner) {
        return super.hurtTarget(target, owner);
    }

    @Override
    protected void afterEntityHit(EntityRayTraceResult entityRayTraceResult, boolean entityHurt) {
        if (entityHurt) {
            dealtDamage = true;
            Entity target = entityRayTraceResult.getEntity();
            if (knockback > 0 && target instanceof LivingEntity) {
                DamageUtil.knockback((LivingEntity) target, knockback, yRot);
            }
        }
    }

    @Override
    public float getBaseDamage() {
        return 1F;
    }

    @Override
    protected float getMaxHardnessBreakable() {
        return 0;
    }

    @Override
    public boolean standDamage() {
        return true;
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
    public void readSpawnData(PacketBuffer additionalData) {
        super.readSpawnData(additionalData);
        initYRotOffset();
    }
}
