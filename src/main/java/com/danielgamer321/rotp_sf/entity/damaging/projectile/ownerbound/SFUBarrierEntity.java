package com.danielgamer321.rotp_sf.entity.damaging.projectile.ownerbound;

import java.util.Optional;

import javax.annotation.Nonnull;

import com.danielgamer321.rotp_sf.capability.entity.LivingUtilCapProvider;
import com.danielgamer321.rotp_sf.init.AddonStands;
import com.danielgamer321.rotp_sf.init.InitEntities;
import com.danielgamer321.rotp_sf.init.InitSounds;
import com.danielgamer321.rotp_sf.init.InitStands;
import com.danielgamer321.rotp_sf.entity.stand.stands.StoneFreeEntity;
import com.github.standobyte.jojo.client.ClientUtil;
import com.github.standobyte.jojo.entity.IHasHealth;
import com.github.standobyte.jojo.entity.damaging.projectile.ownerbound.OwnerBoundProjectileEntity;
import com.github.standobyte.jojo.entity.stand.StandEntity;
import com.github.standobyte.jojo.init.ModDataSerializers;
import com.github.standobyte.jojo.power.impl.stand.IStandPower;
import com.github.standobyte.jojo.power.impl.stand.StandUtil;
import com.github.standobyte.jojo.util.mc.MCUtil;
import com.github.standobyte.jojo.util.mc.damage.DamageUtil;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.PacketBuffer;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.network.datasync.IDataSerializer;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.*;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.World;
import net.minecraftforge.event.ForgeEventFactory;

public class SFUBarrierEntity extends OwnerBoundProjectileEntity implements IHasHealth {
    private static final DataParameter<Float> HEALTH = EntityDataManager.defineId(SFUBarrierEntity.class, DataSerializers.FLOAT);
    private static final float MAX_HEALTH = 3;
    protected static final DataParameter<Boolean> WAS_ALERTED = EntityDataManager.defineId(SFUBarrierEntity.class, DataSerializers.BOOLEAN);
    @SuppressWarnings("unchecked")
    protected static final DataParameter<Optional<Vector3d>> ALERTED_POINT = EntityDataManager.defineId(SFUBarrierEntity.class,
            (IDataSerializer<Optional<Vector3d>>) ModDataSerializers.OPTIONAL_VECTOR3D.get().getSerializer());
    protected static final DataParameter<Boolean> WAS_RIPPED = EntityDataManager.defineId(SFUBarrierEntity.class, DataSerializers.BOOLEAN);
    private boolean rippedHurtOwner = false;
    private IStandPower userStandPower;
    private LivingEntity standUser;
    private BlockPos originBlockPos;
    private int alertedTicks = -1;
    private boolean timeStop = false;
    private boolean ready = false;
    
    public SFUBarrierEntity(World world, LivingEntity entity, IStandPower userStand) {
        super(InitEntities.SFU_BARRIER.get(), entity, world);
        this.standUser = entity;
        this.userStandPower = userStand;
    }
    
    public SFUBarrierEntity(EntityType<? extends SFUBarrierEntity> entityType, World world) {
        super(entityType, world);
    }
    
    public void setOriginBlockPos(BlockPos blockPos) {
        this.originBlockPos = blockPos;
    }
    
    public BlockPos getOriginBlockPos() {
        return originBlockPos;
    }
    
    @Override
    public void tick() {
        if (getBlockPosAttachedTo().isPresent()) {
            ready = true;
        }
        if (!timeStop) {
            if (alertedTicks > 0) {
                if (--alertedTicks == 0) {
                    if (!level.isClientSide()) {
                        RayTraceResult[] rayTrace = rayTrace();
                        for (RayTraceResult result : rayTrace) {
                            alertAtOff(result.getLocation());
                        }
                        if (wasRipped()) {
                            MCUtil.playSound(level, null, this.getX(), this.getY(), this.getZ(),
                                    InitSounds.STONE_FREE_BARRIER_RIPPED.get(), getSoundSource(), 1.0F, 1.0F, StandUtil::playerCanHearStands);
                            remove();
                        }
                        alertedTicks = -1;
                    }
                    return;
                }
                if (!level.isClientSide() && !rippedHurtOwner && wasRipped()) {
                    DamageUtil.hurtThroughInvulTicks(standUser, DamageSource.GENERIC, 0.12F);
                    rippedHurtOwner = true;
                }
                IStandPower.getStandPowerOptional(standUser).ifPresent(power -> {
                    if (IStandPower.getStandPowerOptional(standUser).map(stand -> !stand.hasPower() ||
                            stand.getType() != AddonStands.STONE_FREE.getStandType()).orElse(false)) {
                        DamageUtil.hurtThroughInvulTicks(standUser, DamageSource.GENERIC, 0.12F);
                        remove();
                    }
                });
            }
            else {
                if (standUser == null || !standUser.isAlive() || (userStandPower != null && userStandPower.getHeldAction() == InitStands.STONE_FREE_USER_REMOVE_BARRIER.get())) {
                    remove();
                    return;
                }
                super.tick();
                if (!isAlive()) {
                    return;
                }
            }
        }
        else if (!level.isClientSide()) {
            if (!wasAlerted()) {
                RayTraceResult[] rayTrace = rayTrace();
                for (RayTraceResult result : rayTrace) {
                    if (result.getType() == RayTraceResult.Type.ENTITY && !ForgeEventFactory.onProjectileImpact(this, result)) {
                        alertAtOn(result.getLocation());
                        break;
                    }
                }
            }
        }
    }

    @Override
    public void onRemovedFromWorld() {
        super.onRemovedFromWorld();
        if (!level.isClientSide() && ready) {
            standUser.getCapability(LivingUtilCapProvider.CAPABILITY).ifPresent(playerUtilCap -> playerUtilCap.addBarriersRemoved());
        }
    }

    @Override
    protected boolean moveBoundToOwner() {
        moveTo(getOwner().getEyePosition(1.0F).add(getOwnerRelativeOffset()));
        return true;
    }
    
    @Override
	public boolean isBodyPart() {
        return true;
    }

    @Override
    public Vector3d getOriginPoint(float partialTick) {
        return originBlockPos == null
                ? standUser == null ? position() : MCUtil.getEntityPosition(standUser, partialTick)
                        : Vector3d.atCenterOf(originBlockPos);
    }

    @Deprecated
    @Override
    protected Vector3d getNextOriginOffset() { return Vector3d.ZERO; }
    
    @Override
    protected void onHitBlock(BlockRayTraceResult result) {}

    @Override
    protected void defineSynchedData() {
        super.defineSynchedData();
        entityData.define(HEALTH, MAX_HEALTH);
        entityData.define(WAS_ALERTED, false);
        entityData.define(ALERTED_POINT, Optional.empty());
        entityData.define(WAS_RIPPED, false);
    }
    
    private static final Vector3d OFFSET = new Vector3d(0.15D, -1.4D, 0);
    @Override
    protected Vector3d getOwnerRelativeOffset() {
        return OFFSET;
    }

    @Override
    public void onSyncedDataUpdated(DataParameter<?> dataParameter) {
        super.onSyncedDataUpdated(dataParameter);
        if (WAS_ALERTED.equals(dataParameter) && wasAlerted()) {
            alertedTicks = 40;
        }
        if (HEALTH.equals(dataParameter)) {
            if (getHealth() <= 0.0F) {
                if (!level.isClientSide()) {
                    RayTraceResult[] rayTrace = rayTrace();
                    for (RayTraceResult result : rayTrace) {
                        ripAt(result.getLocation());
                    }
                }
            }
        }
    }

    @Override
    public float getHealth() {
        return entityData.get(HEALTH);
    }

    @Override
    public void setHealth(float health) {
        entityData.set(HEALTH, MathHelper.clamp(health, 0.0F, getMaxHealth()));
    }

    @Override
    public float getMaxHealth() {
        return MAX_HEALTH;
    }
    
    public boolean wasAlerted() {
        return entityData.get(WAS_ALERTED);
    }

    public boolean wasRipped() {
        return entityData.get(WAS_RIPPED);
    }
    
    public Optional<Vector3d> wasAlertedAt() {
        return entityData.get(ALERTED_POINT);
    }

    private void alertAtOn(@Nonnull Vector3d pos) {
        entityData.set(WAS_ALERTED, true);
        entityData.set(ALERTED_POINT, Optional.of(pos));
    }

    private void alertAtOff(@Nonnull Vector3d pos) {
        entityData.set(WAS_ALERTED, false);
        entityData.set(ALERTED_POINT, Optional.of(pos));
    }

    private void ripAt(@Nonnull Vector3d pos) {
        entityData.set(WAS_RIPPED, true);
        entityData.set(WAS_ALERTED, true);
        entityData.set(ALERTED_POINT, Optional.of(pos));
    }

    @Override
    public boolean isGlowing() {
        return level.isClientSide() && !timeStop && wasAlerted() && alertedTicks >= 0 && getOwner() != null &&
                ((LivingEntity) getOwner()) == ClientUtil.getClientPlayer() || super.isGlowing();
    }
    
    @Override
    public int getTeamColor() {
        return wasAlerted() ? 0xFF0000 : super.getTeamColor();
    }

    @Override
    protected void onHitEntity(EntityRayTraceResult entityRayTraceResult) {
        if (getBlockPosAttachedTo().isPresent()) {
            Entity target = entityRayTraceResult.getEntity();
            target.setDeltaMovement(Vector3d.ZERO);
            if (!level.isClientSide()) {
                if (!(userStandPower.isActive() && userStandPower.getStandManifestation() instanceof StoneFreeEntity)) {
                    if (target instanceof StandEntity || alertedTicks == -1) {
                        setHealth(getHealth() - 1);
                    }
                    alertAtOn(entityRayTraceResult.getLocation());
                }
                else {
                    StoneFreeEntity standOwner = (StoneFreeEntity) userStandPower.getStandManifestation();
                    if (target != standOwner) {
                        if (target instanceof StandEntity || alertedTicks == -1) {
                            setHealth(getHealth() - 1);
                        }
                        alertAtOn(entityRayTraceResult.getLocation());
                    }
                }
            }
        }
    }

    @Override
	public int ticksLifespan() {
        return Integer.MAX_VALUE;
    }

    @Override
    public boolean isPickable() {
        return false;
    }

    @Override
    protected float getMaxHardnessBreakable() {
        return 0F;
    }
    
    @Override
    protected float movementSpeed() {
        return 0F;
    }

    @Override
    public float getBaseDamage() {
        return 0F;
    }

    @Override
    public boolean standDamage() {
        return true;
    }

    @Override
    public void canUpdate(boolean canUpdate) {
        timeStop = !canUpdate;
    }
    
    @Override
    public boolean canUpdate() {
        return true;
    }

    @Override
    protected void addAdditionalSaveData(CompoundNBT nbt) {
        super.addAdditionalSaveData(nbt);
    }

    @Override
    protected void readAdditionalSaveData(CompoundNBT nbt) {
        super.readAdditionalSaveData(nbt);
    }
    
    @Override
    public void writeSpawnData(PacketBuffer buffer) {
        super.writeSpawnData(buffer);
        buffer.writeInt(standUser.getId());
        boolean isBlockOrigin = originBlockPos != null;
        buffer.writeBoolean(isBlockOrigin);
        if (isBlockOrigin) {
            buffer.writeBlockPos(originBlockPos);
        }
    }

    @Override
    public void readSpawnData(PacketBuffer additionalData) {
        super.readSpawnData(additionalData);
        Entity entity = level.getEntity(additionalData.readInt());
        if (entity instanceof LivingEntity) {
            standUser = (LivingEntity) entity;
        }
        if (additionalData.readBoolean()) {
            originBlockPos = additionalData.readBlockPos();
        }
    }

}
