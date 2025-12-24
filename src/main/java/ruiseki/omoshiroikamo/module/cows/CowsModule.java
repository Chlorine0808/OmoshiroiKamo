package ruiseki.omoshiroikamo.module.cows;

import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import ruiseki.omoshiroikamo.api.IModuleMod;
import ruiseki.omoshiroikamo.config.backport.BackportConfigs;
import ruiseki.omoshiroikamo.module.cows.common.init.CowsBlocks;
import ruiseki.omoshiroikamo.module.cows.common.init.CowsItems;
import ruiseki.omoshiroikamo.module.cows.common.init.CowsRecipes;
import ruiseki.omoshiroikamo.module.cows.common.registries.ModCows;

public class CowsModule implements IModuleMod {

    @Override
    public String getId() {
        return "Cows";
    }

    @Override
    public boolean isEnabled() {
        return BackportConfigs.useCow;
    }

    @Override
    public void preInit(FMLPreInitializationEvent event) {
        CowsBlocks.preInit();
        CowsItems.preInit();
        ModCows.preInit();
    }

    @Override
    public void init(FMLInitializationEvent event) {
        CowsRecipes.init();
        ModCows.init();
    }

    @Override
    public void postInit(FMLPostInitializationEvent event) {
        ModCows.postInit();
    }

    @Override
    public void preInitClient(FMLPreInitializationEvent event) {
        IModuleMod.super.preInitClient(event);
    }

    @Override
    public void initClient(FMLInitializationEvent event) {
        IModuleMod.super.initClient(event);
    }

    @Override
    public void postInitClient(FMLPostInitializationEvent event) {
        IModuleMod.super.postInitClient(event);
    }
}
