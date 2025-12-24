package ruiseki.omoshiroikamo.core;

import java.util.ArrayList;
import java.util.List;

import cpw.mods.fml.common.event.FMLConstructionEvent;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.event.FMLServerStartedEvent;
import cpw.mods.fml.common.event.FMLServerStartingEvent;
import ruiseki.omoshiroikamo.api.IModule;

public final class ModuleManager {

    private static final List<IModule> MODULES = new ArrayList<>();

    public static void register(IModule module) {
        MODULES.add(module);
    }

    public static void onConstruction(FMLConstructionEvent event) {
        for (IModule m : MODULES) if (m.isEnabled()) m.onConstruction(event);
    }

    public static void preInit(FMLPreInitializationEvent event) {
        for (IModule m : MODULES) if (m.isEnabled()) m.preInit(event);
    }

    public static void init(FMLInitializationEvent event) {
        for (IModule m : MODULES) if (m.isEnabled()) m.init(event);
    }

    public static void postInit(FMLPostInitializationEvent event) {
        for (IModule m : MODULES) if (m.isEnabled()) m.postInit(event);
    }

    public static void serverLoad(FMLServerStartingEvent event) {
        for (IModule m : MODULES) if (m.isEnabled()) m.serverLoad(event);
    }

    public static void serverStarted(FMLServerStartedEvent event) {
        for (IModule m : MODULES) if (m.isEnabled()) m.serverStarted(event);
    }

    public static void preInitClient(FMLPreInitializationEvent event) {
        for (IModule m : MODULES) if (m.isEnabled()) m.preInitClient(event);
    }

    public static void initClient(FMLInitializationEvent event) {
        for (IModule m : MODULES) if (m.isEnabled()) m.initClient(event);
    }

    public static void postInitClient(FMLPostInitializationEvent event) {
        for (IModule m : MODULES) if (m.isEnabled()) m.postInitClient(event);
    }
}
