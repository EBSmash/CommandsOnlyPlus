package com.github.ebsmash.cop;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

@Mod(modid = CommandsOnlyPlus.MOD_ID)
public class CommandsOnlyPlus {
    public static final String MOD_ID = "cop";

    public static boolean enabled = false;

    @Mod.EventHandler
    public void onPreInit(FMLPreInitializationEvent event) {
        System.out.println("poopity scoop");
    }
}
