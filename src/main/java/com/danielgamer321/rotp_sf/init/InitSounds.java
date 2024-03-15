package com.danielgamer321.rotp_sf.init;

import java.util.function.Supplier;

import com.danielgamer321.rotp_sf.RotpStoneFreeAddon;
import com.github.standobyte.jojo.init.ModSounds;
import com.github.standobyte.jojo.util.mc.OstSoundList;

import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class InitSounds {
    public static final DeferredRegister<SoundEvent> SOUNDS = DeferredRegister.create(ForgeRegistries.SOUND_EVENTS, RotpStoneFreeAddon.MOD_ID);
    
    public static final RegistryObject<SoundEvent> JOLYNE_STONE_FREE = SOUNDS.register("jolyne_stone_free",
            () -> new SoundEvent(new ResourceLocation(RotpStoneFreeAddon.MOD_ID, "jolyne_stone_free")));

    public static final Supplier<SoundEvent> STONE_FREE_SUMMON = SOUNDS.register("stone_free_summon",
            () -> new SoundEvent(new ResourceLocation(RotpStoneFreeAddon.MOD_ID, "stone_free_summon")));
    
    public static final Supplier<SoundEvent> STONE_FREE_UNSUMMON = ModSounds.STAND_UNSUMMON_DEFAULT;
    
    public static final RegistryObject<SoundEvent> STONE_FREE_PUNCH_LIGHT = SOUNDS.register("stone_free_punch_light",
            () -> new SoundEvent(new ResourceLocation(RotpStoneFreeAddon.MOD_ID, "stone_free_punch_light")));
    
    public static final RegistryObject<SoundEvent> STONE_FREE_PUNCH_HEAVY = SOUNDS.register("stone_free_punch_heavy",
            () -> new SoundEvent(new ResourceLocation(RotpStoneFreeAddon.MOD_ID, "stone_free_punch_heavy")));
    
    public static final Supplier<SoundEvent> STONE_FREE_BARRAGE = InitSounds.STONE_FREE_PUNCH_LIGHT;
    
    public static final RegistryObject<SoundEvent> STONE_FREE_ORA = SOUNDS.register("stone_free_ora", 
            () -> new SoundEvent(new ResourceLocation(RotpStoneFreeAddon.MOD_ID, "stone_free_ora")));
    
    public static final RegistryObject<SoundEvent> STONE_FREE_ORA_LONG = SOUNDS.register("stone_free_ora_long", 
            () -> new SoundEvent(new ResourceLocation(RotpStoneFreeAddon.MOD_ID, "stone_free_ora_long")));
    
    public static final RegistryObject<SoundEvent> STONE_FREE_ORA_ORA_ORA = SOUNDS.register("stone_free_ora_ora_ora", 
            () -> new SoundEvent(new ResourceLocation(RotpStoneFreeAddon.MOD_ID, "stone_free_ora_ora_ora")));
    
    public static final RegistryObject<SoundEvent> STONE_FREE_STRING = SOUNDS.register("stone_free_string", 
            () -> new SoundEvent(new ResourceLocation(RotpStoneFreeAddon.MOD_ID, "stone_free_string")));
    
    public static final RegistryObject<SoundEvent> STONE_FREE_BARRIER_PLACED = SOUNDS.register("stone_free_barrier_placed", 
            () -> new SoundEvent(new ResourceLocation(RotpStoneFreeAddon.MOD_ID, "stone_free_barrier_placed")));
    
    public static final RegistryObject<SoundEvent> STONE_FREE_BARRIER_RIPPED = SOUNDS.register("stone_free_barrier_ripped", 
            () -> new SoundEvent(new ResourceLocation(RotpStoneFreeAddon.MOD_ID, "stone_free_barrier_ripped")));
    
    public static final RegistryObject<SoundEvent> STONE_FREE_GRAPPLE_CATCH = SOUNDS.register("stone_free_grapple_catch", 
            () -> new SoundEvent(new ResourceLocation(RotpStoneFreeAddon.MOD_ID, "stone_free_grapple_catch")));
    
    static final OstSoundList STONE_FREE_OST = new OstSoundList(new ResourceLocation(RotpStoneFreeAddon.MOD_ID, "stone_free_ost"), SOUNDS);

}
