package com.danielgamer321.rotp_sf;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.danielgamer321.rotp_sf.init.InitEntities;
import com.danielgamer321.rotp_sf.init.InitSounds;
import com.danielgamer321.rotp_sf.init.InitStands;
import com.danielgamer321.rotp_sf.init.InitEffects;

import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(RotpStoneFreeAddon.MOD_ID)
public class RotpStoneFreeAddon {
    // The value here should match an entry in the META-INF/mods.toml file
    public static final String MOD_ID = "rotp_sf";
    private static final Logger LOGGER = LogManager.getLogger();

    public RotpStoneFreeAddon() {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        InitEntities.ENTITIES.register(modEventBus);
        InitSounds.SOUNDS.register(modEventBus);
        InitStands.ACTIONS.register(modEventBus);
        InitStands.STANDS.register(modEventBus);
        InitEffects.EFFECTS.register(modEventBus);
    }

    public static Logger getLogger() {
        return LOGGER;
    }
}
