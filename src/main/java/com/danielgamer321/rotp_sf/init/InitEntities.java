package com.danielgamer321.rotp_sf.init;

import com.danielgamer321.rotp_sf.RotpStoneFreeAddon;
import com.danielgamer321.rotp_sf.entity.damaging.projectile.ownerbound.SFBarrierEntity;
import com.danielgamer321.rotp_sf.entity.damaging.projectile.ownerbound.SFExtendedPunchEntity;
import com.danielgamer321.rotp_sf.entity.damaging.projectile.ownerbound.SFGrapplingStringEntity;
import com.danielgamer321.rotp_sf.entity.damaging.projectile.ownerbound.SFStringEntity;

import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class InitEntities {
    public static final DeferredRegister<EntityType<?>> ENTITIES = DeferredRegister.create(ForgeRegistries.ENTITIES, RotpStoneFreeAddon.MOD_ID);
    
    
    public static final RegistryObject<EntityType<SFExtendedPunchEntity>> SF_EXTENDED_PUNCH = ENTITIES.register("sf_extended_punch", 
            () -> EntityType.Builder.<SFExtendedPunchEntity>of(SFExtendedPunchEntity::new, EntityClassification.MISC).sized(0.25F, 0.25F).noSummon().noSave().setUpdateInterval(20)
            .build(new ResourceLocation(RotpStoneFreeAddon.MOD_ID, "sf_extended_punch").toString()));
    
    public static final RegistryObject<EntityType<SFStringEntity>> SF_STRING = ENTITIES.register("sf_string", 
            () -> EntityType.Builder.<SFStringEntity>of(SFStringEntity::new, EntityClassification.MISC).sized(0.25F, 0.25F).noSummon().noSave().setUpdateInterval(20)
            .build(new ResourceLocation(RotpStoneFreeAddon.MOD_ID, "sf_string").toString()));
    
    public static final RegistryObject<EntityType<SFGrapplingStringEntity>> SF_GRAPPLING_STRING = ENTITIES.register("sf_grappling_string", 
            () -> EntityType.Builder.<SFGrapplingStringEntity>of(SFGrapplingStringEntity::new, EntityClassification.MISC).sized(0.25F, 0.25F).noSummon().noSave().setUpdateInterval(20)
            .build(new ResourceLocation(RotpStoneFreeAddon.MOD_ID, "sf_grappling_string").toString()));
    
    public static final RegistryObject<EntityType<SFBarrierEntity>> SF_BARRIER = ENTITIES.register("sf_barrier", 
            () -> EntityType.Builder.<SFBarrierEntity>of(SFBarrierEntity::new, EntityClassification.MISC).sized(0.25F, 0.25F).noSummon().noSave().setShouldReceiveVelocityUpdates(false).setUpdateInterval(Integer.MAX_VALUE)
            .build(new ResourceLocation(RotpStoneFreeAddon.MOD_ID, "sf_barrier").toString()));
}
