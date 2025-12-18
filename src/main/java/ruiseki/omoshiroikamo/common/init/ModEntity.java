package ruiseki.omoshiroikamo.common.init;

import ruiseki.omoshiroikamo.plugin.chicken.ModChickens;
import ruiseki.omoshiroikamo.plugin.cow.ModCows;
import ruiseki.omoshiroikamo.plugin.model.ModModels;

public class ModEntity {

    public static void preInit() {
        ModChickens.preInit();
        ModCows.preInit();
        ModModels.preInit();
    }

    public static void init() {
        ModChickens.init();
        ModCows.init();
        ModModels.init();
    }
}
