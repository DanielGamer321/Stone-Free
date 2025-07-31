package com.danielgamer321.rotp_sf.init;

import com.danielgamer321.rotp_sf.RotpStoneFreeAddon;
import com.danielgamer321.rotp_sf.action.stand.*;
import com.danielgamer321.rotp_sf.entity.stand.stands.StoneFreeEntity;
import com.danielgamer321.rotp_sf.power.impl.stand.type.StoneFreeStandType;
import com.github.standobyte.jojo.action.Action;
import com.github.standobyte.jojo.action.stand.*;
import com.github.standobyte.jojo.action.stand.StandEntityAction.Phase;
import com.github.standobyte.jojo.entity.stand.StandEntityType;
import com.github.standobyte.jojo.entity.stand.StandPose;
import com.github.standobyte.jojo.init.power.stand.EntityStandRegistryObject;
import com.github.standobyte.jojo.power.impl.stand.StandInstance.StandPart;
import com.github.standobyte.jojo.power.impl.stand.stats.StandStats;
import com.github.standobyte.jojo.power.impl.stand.type.StandType;

import com.github.standobyte.jojo.util.mod.StoryPart;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;

import static com.github.standobyte.jojo.init.ModEntityTypes.ENTITIES;

public class InitStands {
    @SuppressWarnings("unchecked")
    public static final DeferredRegister<Action<?>> ACTIONS = DeferredRegister.create(
            (Class<Action<?>>) ((Class<?>) Action.class), RotpStoneFreeAddon.MOD_ID);
    @SuppressWarnings("unchecked")
    public static final DeferredRegister<StandType<?>> STANDS = DeferredRegister.create(
            (Class<StandType<?>>) ((Class<?>) StandType.class), RotpStoneFreeAddon.MOD_ID);
    
 // ======================================== Stone Free ========================================

    public static final RegistryObject<StandEntityLightAttack> STONE_FREE_PUNCH = ACTIONS.register("stone_free_punch",
            () -> new StoneFreePunch(new StandEntityLightAttack.Builder()
                    .punchSound(InitSounds.STONE_FREE_PUNCH_LIGHT)
                    .standSound(Phase.WINDUP, InitSounds.STONE_FREE_ORA)));

    public static final RegistryObject<StandAction> STONE_FREE_USER_STRING_ATTACK = ACTIONS.register("stone_free_user_string_attack",
            () -> new StoneFreeUserStringAttack(new StandAction.Builder().staminaCost(20)
                    .needsFreeMainHand().swingHand()));

    public static final RegistryObject<StandEntityAction> STONE_FREE_BARRAGE = ACTIONS.register("stone_free_barrage",
            () -> new StoneFreeBarrage(new StandEntityMeleeBarrage.Builder()
                    .barrageHitSound(InitSounds.STONE_FREE_BARRAGE)
                    .standSound(InitSounds.STONE_FREE_ORA_ORA_ORA)));

    public static final RegistryObject<StandAction> STONE_FREE_USER_STRING_SWEEP = ACTIONS.register("stone_free_user_string_sweep",
            () -> new StoneFreeUserStringSweep(new StandAction.Builder().staminaCost(35)
                    .needsFreeMainHand().swingHand()));
    
    public static final RegistryObject<StandEntityHeavyAttack> STONE_FREE_STRING_PUNCH = ACTIONS.register("stone_free_string_punch",
            () -> new StoneFreeStringPunch(new StandEntityHeavyAttack.Builder()
                    .resolveLevelToUnlock(1)
                    .punchSound(InitSounds.STONE_FREE_PUNCH_HEAVY)
                    .standSound(Phase.WINDUP, InitSounds.STONE_FREE_ORA_LONG)
                    .partsRequired(StandPart.ARMS)));

    public static final RegistryObject<StandEntityActionModifier> STONE_FREE_STRING_CAPTURE = ACTIONS.register("stone_free_string_capture",
            () -> new StoneFreeStringCapture(new StandEntityActionModifier.Builder().staminaCost(75).cooldown(24, 100, 0.5F)
                    .partsRequired(StandPart.ARMS)));
    
    public static final RegistryObject<StandEntityAction> STONE_FREE_HEAVY_PUNCH = ACTIONS.register("stone_free_heavy_punch", 
            () -> new StandEntityHeavyAttack(new StandEntityHeavyAttack.Builder()
                    .punchSound(InitSounds.STONE_FREE_PUNCH_HEAVY)
                    .standSound(Phase.WINDUP, InitSounds.STONE_FREE_ORA_LONG)
                    .partsRequired(StandPart.ARMS)
                    .setFinisherVariation(STONE_FREE_STRING_PUNCH)
                    .shiftVariationOf(STONE_FREE_PUNCH).shiftVariationOf(STONE_FREE_BARRAGE)));

    public static final RegistryObject<StandAction> STONE_FREE_USER_STRING_WHIP = ACTIONS.register("stone_free_user_string_whip",
            () -> new StoneFreeUserStringWhip(new StandAction.Builder().staminaCost(40).cooldown(10, 20)
                    .needsFreeMainHand().swingHand()
                    .shiftVariationOf(STONE_FREE_USER_STRING_ATTACK).shiftVariationOf(STONE_FREE_USER_STRING_SWEEP)));
    
    public static final RegistryObject<StandEntityAction> STONE_FREE_STRING_BIND = ACTIONS.register("stone_free_attack_binding",
            () -> new StoneFreeStringBind(new StandEntityAction.Builder().staminaCost(35).standPerformDuration(24).cooldown(24, 100, 0.5F)
                    .standSound(InitSounds.STONE_FREE_STRING)
                    .resolveLevelToUnlock(1)
                    .partsRequired(StandPart.ARMS)));

    public static final RegistryObject<StandAction> STONE_FREE_USER_STRING_BIND = ACTIONS.register("stone_free_user_attack_binding",
            () -> new StoneFreeUserStringBind(new StandAction.Builder().staminaCost(35).cooldown(24, 100, 0.5F)
                    .needsFreeMainHand().swingHand()));
    
    public static final RegistryObject<StandEntityAction> STONE_FREE_EXTENDED_PUNCH = ACTIONS.register("stone_free_extended_punch", 
            () -> new StoneFreeExtendedPunch(new StandEntityAction.Builder().staminaCost(375).standPerformDuration(10).cooldown(10, 100)
                    .resolveLevelToUnlock(3)
                    .standOffsetFront().standPose(StandPose.RANGED_ATTACK).standSound(InitSounds.STONE_FREE_STRING)
                    .partsRequired(StandPart.ARMS)));

    public static final RegistryObject<StandAction> STONE_FREE_USER_STRING_PICK = ACTIONS.register("stone_free_user_string_pick",
            () -> new StoneFreeUserStringPick(new StandAction.Builder().holdToFire(5, false).staminaCost(35).cooldown(16, 100)
                    .needsFreeMainHand().swingHand()));
    
    public static final RegistryObject<StandEntityAction> STONE_FREE_BLOCK = ACTIONS.register("stone_free_block", 
            () -> new StandEntityBlock());

    public static final RegistryObject<StandAction> STONE_FREE_USER_GRAPPLE = ACTIONS.register("stone_free_user_grapple",
            () -> new StoneFreeUserGrapple(new StandAction.Builder()
                    .needsFreeMainHand().swingHand()
                    .resolveLevelToUnlock(2)));

    public static final RegistryObject<StandAction> STONE_FREE_USER_GRAPPLE_ENTITY = ACTIONS.register("stone_free_user_grapple_entity",
            () -> new StoneFreeUserGrapple(new StandAction.Builder()
                    .needsFreeMainHand().swingHand()
                    .shiftVariationOf(STONE_FREE_USER_GRAPPLE)));

    public static final RegistryObject<StandAction> STONE_FREE_USER_RECOVER_STRING = ACTIONS.register("stone_free_user_recover_string",
            () -> new StoneFreeUserRecoverString(new StandAction.Builder().holdType()));

    public static final RegistryObject<StandEntityAction> STONE_FREE_GRAPPLE_ENTITY = ACTIONS.register("stone_free_grapple_entity",
            () -> new StoneFreeGrapple(new StandEntityAction.Builder().staminaCostTick(1).holdType().standUserWalkSpeed(1.0F)
                    .standPose(StandPose.RANGED_ATTACK).standOffsetFromUser(-0.5, 0.25)
                    .partsRequired(StandPart.ARMS)));

    public static final RegistryObject<StandAction> STONE_FREE_USER_CLOSE_WOUNDS = ACTIONS.register("stone_free_user_close_wounds",
            () -> new StoneFreeUserCloseWounds(new StandAction.Builder().staminaCost(20).holdToFire(10, false)
                    .resolveLevelToUnlock(2)));
    
    public static final RegistryObject<StandAction> STONE_FREE_USER_BARRIER = ACTIONS.register("stone_free_user_barrier",
            () -> new StoneFreeUserBarrier(new StandAction.Builder()
                    .needsFreeMainHand().swingHand()
                    .resolveLevelToUnlock(3)));

    public static final RegistryObject<StandAction> STONE_FREE_USER_REMOVE_BARRIER = ACTIONS.register("stone_free_user_remove_barrier",
            () -> new StoneFreeUserRemoveBarrier(new StandAction.Builder().holdType()
                    .resolveLevelToUnlock(3)
                    .shiftVariationOf(STONE_FREE_USER_BARRIER)));

    public static final RegistryObject<StandAction> STONE_FREE_USER_MOBIUS_STRIP = ACTIONS.register("stone_free_user_mobius_strip",
            () -> new StoneFreeUserMobiusStrip(new StandAction.Builder()
                    .noResolveUnlock()));


    public static final EntityStandRegistryObject<StoneFreeStandType<StandStats>, StandEntityType<StoneFreeEntity>> STAND_STONE_FREE =
            new EntityStandRegistryObject<>("stone_free",
                    STANDS,
                    () -> new StoneFreeStandType.Builder<>()
                            .color(0x80DEF7)
                            .storyPartName(StoryPart.STONE_OCEAN.getName())
                            .leftClickHotbar(
                                    STONE_FREE_PUNCH.get(),
                                    STONE_FREE_BARRAGE.get(),
                                    STONE_FREE_STRING_BIND.get(),
                                    STONE_FREE_EXTENDED_PUNCH.get()
                            )
                            .rightClickHotbar(
                                    STONE_FREE_BLOCK.get(),
                                    STONE_FREE_USER_GRAPPLE.get(),
                                    STONE_FREE_USER_CLOSE_WOUNDS.get(),
                                    STONE_FREE_USER_BARRIER.get(),
                                    STONE_FREE_USER_MOBIUS_STRIP.get()
                            )
                            .defaultStats(StandStats.class, new StandStats.Builder()
                                    .power(14.0, 15.0)
                                    .speed(11.0, 12.0)
                                    .range(2.0, 10.0)
                                    .durability(14.0, 16.0)
                                    .precision(10.0)
                                    .randomWeight(2)
                            )
                            .addSummonShout(InitSounds.JOLYNE_STONE_FREE)
                            .addOst(InitSounds.STONE_FREE_OST)
                            .build(),

                    ENTITIES,
                    () -> new StandEntityType<StoneFreeEntity>(StoneFreeEntity::new, 0.65F, 1.95F)
                            .summonSound(InitSounds.STONE_FREE_SUMMON)
                            .unsummonSound(InitSounds.STONE_FREE_UNSUMMON))
                    .withDefaultStandAttributes();
}
