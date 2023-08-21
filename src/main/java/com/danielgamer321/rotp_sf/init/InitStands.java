package com.danielgamer321.rotp_sf.init;

import com.danielgamer321.rotp_sf.RotpStoneFreeAddon;
import com.danielgamer321.rotp_sf.action.stand.StoneFreeBarrier;
import com.danielgamer321.rotp_sf.action.stand.StoneFreeExtendedPunch;
import com.danielgamer321.rotp_sf.action.stand.StoneFreeGrapple;
import com.danielgamer321.rotp_sf.action.stand.StoneFreeStringAttack;
import com.danielgamer321.rotp_sf.entity.stand.stands.StoneFreeEntity;
import com.github.standobyte.jojo.action.Action;
import com.github.standobyte.jojo.action.stand.StandAction;
import com.github.standobyte.jojo.action.stand.StandEntityAction;
import com.github.standobyte.jojo.action.stand.StandEntityAction.Phase;
import com.github.standobyte.jojo.action.stand.StandEntityBlock;
import com.github.standobyte.jojo.action.stand.StandEntityHeavyAttack;
import com.github.standobyte.jojo.action.stand.StandEntityLightAttack;
import com.github.standobyte.jojo.action.stand.StandEntityMeleeBarrage;
import com.github.standobyte.jojo.entity.stand.StandEntityType;
import com.github.standobyte.jojo.entity.stand.StandPose;
import com.github.standobyte.jojo.init.power.stand.EntityStandRegistryObject;
import com.github.standobyte.jojo.init.power.stand.ModStandsInit;
import com.github.standobyte.jojo.power.impl.stand.StandInstance.StandPart;
import com.github.standobyte.jojo.power.impl.stand.stats.StandStats;
import com.github.standobyte.jojo.power.impl.stand.type.EntityStandType;
import com.github.standobyte.jojo.power.impl.stand.type.StandType;

import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;

public class InitStands {
    @SuppressWarnings("unchecked")
    public static final DeferredRegister<Action<?>> ACTIONS = DeferredRegister.create(
            (Class<Action<?>>) ((Class<?>) Action.class), RotpStoneFreeAddon.MOD_ID);
    @SuppressWarnings("unchecked")
    public static final DeferredRegister<StandType<?>> STANDS = DeferredRegister.create(
            (Class<StandType<?>>) ((Class<?>) StandType.class), RotpStoneFreeAddon.MOD_ID);
    
 // ======================================== Stone Free ========================================
    
    public static final RegistryObject<StandEntityAction> STONE_FREE_PUNCH = ACTIONS.register("stone_free_punch", 
            () -> new StandEntityLightAttack(new StandEntityLightAttack.Builder()
                    .punchSound(InitSounds.STONE_FREE_PUNCH_LIGHT)
                    .standSound(Phase.WINDUP, InitSounds.STONE_FREE_ORA)));

    public static final RegistryObject<StandEntityAction> STONE_FREE_STRING_ATTACK = ACTIONS.register("stone_free_attack", 
            () -> new StoneFreeStringAttack(new StandEntityHeavyAttack.Builder().staminaCost(75).standPerformDuration(10)
                    .standSound(InitSounds.STONE_FREE_STRING)
                    .partsRequired(StandPart.ARMS)));
    
    public static final RegistryObject<StandEntityAction> STONE_FREE_BARRAGE = ACTIONS.register("stone_free_barrage", 
            () -> new StandEntityMeleeBarrage(new StandEntityMeleeBarrage.Builder()
                    .barrageHitSound(InitSounds.STONE_FREE_BARRAGE)
                    .standSound(InitSounds.STONE_FREE_ORA_ORA_ORA)));
    
    public static final RegistryObject<StandEntityHeavyAttack> STONE_FREE_HEAVY_PUNCH = ACTIONS.register("stone_free_heavy_punch", 
            () -> new StandEntityHeavyAttack(new StandEntityHeavyAttack.Builder()
                    .punchSound(InitSounds.STONE_FREE_PUNCH_HEAVY)
                    .standSound(Phase.WINDUP, InitSounds.STONE_FREE_ORA_LONG)
                    .partsRequired(StandPart.ARMS)));
    
    public static final RegistryObject<StandEntityAction> STONE_FREE_STRING_BIND = ACTIONS.register("stone_free_attack_binding", 
            () -> new StoneFreeStringAttack(new StandEntityHeavyAttack.Builder().staminaCost(75).standPerformDuration(25).cooldown(25, 100, 0.5F)
                    .partsRequired(StandPart.ARMS)
                    .punchSound(InitSounds.SILENCIO)
                    .standOffsetFront().standPose(StandPose.RANGED_ATTACK).standSound(InitSounds.STONE_FREE_STRING)
                    .setFinisherVariation(STONE_FREE_HEAVY_PUNCH)
                    .shiftVariationOf(STONE_FREE_PUNCH).shiftVariationOf(STONE_FREE_BARRAGE)));
    
    public static final RegistryObject<StandEntityAction> STONE_FREE_EXTENDED_PUNCH = ACTIONS.register("stone_free_extended_punch", 
            () -> new StoneFreeExtendedPunch(new StandEntityAction.Builder().staminaCost(375).standPerformDuration(20).cooldown(20, 60)
                    .ignoresPerformerStun().resolveLevelToUnlock(3)
                    .standOffsetFront().standPose(StandPose.RANGED_ATTACK)
                    .partsRequired(StandPart.ARMS)));
    
    public static final RegistryObject<StandEntityAction> STONE_FREE_BLOCK = ACTIONS.register("stone_free_block", 
            () -> new StandEntityBlock());
    
    public static final RegistryObject<StandEntityAction> STONE_FREE_GRAPPLE = ACTIONS.register("stone_free_grapple", 
            () -> new StoneFreeGrapple(new StandEntityAction.Builder().staminaCostTick(1).holdType().standUserWalkSpeed(1.0F)
                    .resolveLevelToUnlock(2)
                    .standPose(StandPose.RANGED_ATTACK).standOffsetFromUser(-0.5, 0.25)
                    .partsRequired(StandPart.ARMS)));
    
    public static final RegistryObject<StandEntityAction> STONE_FREE_GRAPPLE_ENTITY = ACTIONS.register("stone_free_grapple_entity", 
            () -> new StoneFreeGrapple(new StandEntityAction.Builder().staminaCostTick(1).holdType().standUserWalkSpeed(1.0F)
                    .resolveLevelToUnlock(2)
                    .standPose(StandPose.RANGED_ATTACK)
                    .partsRequired(StandPart.ARMS)
                    .shiftVariationOf(STONE_FREE_GRAPPLE)));
    
    public static final RegistryObject<StandEntityAction> STONE_FREE_BARRIER = ACTIONS.register("stone_free_barrier", 
            () -> new StoneFreeBarrier(new StandEntityAction.Builder()
                    .resolveLevelToUnlock(3)
                    .partsRequired(StandPart.MAIN_BODY)));
    
    
    public static final EntityStandRegistryObject<EntityStandType<StandStats>, StandEntityType<StoneFreeEntity>> STAND_STONE_FREE = 
            new EntityStandRegistryObject<>("stone_free", 
                    STANDS, 
                    () -> new EntityStandType<StandStats>(
                            0x80DEF7, ModStandsInit.PART_6_NAME,

                            new StandAction[] {
                                    STONE_FREE_PUNCH.get(), 
                                    STONE_FREE_BARRAGE.get(), 
                                    STONE_FREE_EXTENDED_PUNCH.get()
                                    },
                            new StandAction[] {
                                    STONE_FREE_BLOCK.get(), 
                                    STONE_FREE_GRAPPLE.get(), 
                                    STONE_FREE_BARRIER.get()
                                    },

                            StandStats.class, new StandStats.Builder()
                            .tier(5)
                            .power(14.0)
                            .speed(12.0)
                            .range(2.0, 10.0)
                            .durability(16.0)
                            .precision(8.0)
                            .build("Stone Free"), 

                            new StandType.StandTypeOptionals()
                            .addSummonShout(InitSounds.JOLYNE_STONE_FREE)
                            .addOst(InitSounds.STONE_FREE_OST)), 

                    InitEntities.ENTITIES, 
                    () -> new StandEntityType<StoneFreeEntity>(StoneFreeEntity::new, 0.65F, 1.95F)
                    .summonSound(InitSounds.STONE_FREE_SUMMON)
                    .unsummonSound(InitSounds.STONE_FREE_UNSUMMON))
            .withDefaultStandAttributes();
}
