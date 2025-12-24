package ruiseki.omoshiroikamo.common.init;

import ruiseki.omoshiroikamo.plugin.dml.ModModels;

public class ModEntity {

    public static void init() {
        ModModels.init();
    }

    public static void postInit() {
        ModModels.postInit();
    }
}
