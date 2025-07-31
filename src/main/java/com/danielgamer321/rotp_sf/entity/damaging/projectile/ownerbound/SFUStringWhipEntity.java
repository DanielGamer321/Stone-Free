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

public class SFUStringWhipEntity extends OwnerBoundProjectileEntity {
    private LivingEntity user;
    private IStandPower userStandPower;
    private float yRotOffset;
    private float xRotOffset;
    private boolean dealtDamage;
    private float knockback = 0;

    public SFUStringWhipEntity(World world, LivingEntity entity, IStandPower userStand) {
        super(InitEntities.SFU_STRING_WHIP.get(), entity, world);
        this.user = entity;
        this.userStandPower = userStand;
        initXRotOffset();
    }

    public SFUStringWhipEntity(EntityType<? extends SFUStringWhipEntity> entityType, World world) {
        super(entityType, world);
    }
    
    private void initXRotOffset() {
        yRotOffset = 0;
        xRotOffset = -87.5F;
    }
    
    @Override
    protected float updateDistance() {
        if (xRotOffset < 47.5F) {
            xRotOffset = Math.min(xRotOffset + 135F / ticksLifespan(), 47.5F);
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
        return super.originOffset(yRot + yRotOffset , xRot + xRotOffset, distance);
    }
    
    @Override
    protected boolean hurtTarget(Entity target, LivingEntity owner) {
        return !dealtDamage ? super.hurtTarget(target, owner) : false;
    }

    @Override
    protected void afterEntityHit(EntityRayTraceResult entityRayTraceResult, boolean entityHurt) {
        if (entityHurt) {
            Entity target = entityRayTraceResult.getEntity();
            if (!(target instanceof SFStringEntity)) {
                dealtDamage = true;
            }
            if (knockback > 0 && target instanceof LivingEntity) {
                DamageUtil.knockback((LivingEntity) target, knockback, yRot);
            }
        }
    }

    @Override
    public float getBaseDamage() {
        return 5.6F;
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

    private static final Vector3d OFFSET = new Vector3d(-0.3D, -0.75D, 0.25D);
    @Override
    protected Vector3d getOwnerRelativeOffset() {
        return OFFSET;
    }

    @Override
    public void readSpawnData(PacketBuffer additionalData) {
        super.readSpawnData(additionalData);
        initXRotOffset();
    }
}
