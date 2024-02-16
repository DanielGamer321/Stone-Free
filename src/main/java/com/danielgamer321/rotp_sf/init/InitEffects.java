package com.danielgamer321.rotp_sf.init;

import java.util.Set;

import com.danielgamer321.rotp_sf.RotpStoneFreeAddon;
import com.danielgamer321.rotp_sf.potion.StringDecompositionEffect;
import com.github.standobyte.jojo.entity.stand.StandEntity;
import com.google.common.collect.ImmutableSet;

import net.minecraft.potion.Effect;
import net.minecraft.potion.EffectType;
import net.minecraft.potion.Effects;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

@EventBusSubscriber(modid = RotpStoneFreeAddon.MOD_ID, bus = EventBusSubscriber.Bus.MOD)
public class InitEffects {
    public static final DeferredRegister<Effect> EFFECTS = DeferredRegister.create(ForgeRegistries.POTIONS, RotpStoneFreeAddon.MOD_ID);
    
    public static final RegistryObject<StringDecompositionEffect> STRING_DECOMPOSITION = EFFECTS.register("string_decomposition", 
            () -> new StringDecompositionEffect(EffectType.NEUTRAL, 0x80DEF7));

    private static Set<Effect> TRACKED_EFFECTS;
    @SubscribeEvent(priority = EventPriority.LOW)
    public static final void afterEffectsRegister(RegistryEvent.Register<Effect> event) {
        TRACKED_EFFECTS = ImmutableSet.of(
                STRING_DECOMPOSITION.get()
        );
    }

    public static boolean isEffectTracked(Effect effect) {
        return TRACKED_EFFECTS.contains(effect);
    }
}
