package ruiseki.omoshiroikamo.api;

import cpw.mods.fml.common.event.FMLConstructionEvent;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.event.FMLServerStartedEvent;
import cpw.mods.fml.common.event.FMLServerStartingEvent;

public interface IModule {

    String getId();

    boolean isEnabled();

    void onConstruction(FMLConstructionEvent event);

    void preInit(FMLPreInitializationEvent event);

    void init(FMLInitializationEvent event);

    void postInit(FMLPostInitializationEvent event);

    void serverLoad(FMLServerStartingEvent event);

    void serverStarted(FMLServerStartedEvent event);

    void preInitClient(FMLPreInitializationEvent event);

    void initClient(FMLInitializationEvent event);

    void postInitClient(FMLPostInitializationEvent event);
}
