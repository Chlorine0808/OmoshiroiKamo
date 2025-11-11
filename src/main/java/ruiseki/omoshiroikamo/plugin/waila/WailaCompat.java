package ruiseki.omoshiroikamo.plugin.waila;

import ruiseki.omoshiroikamo.common.util.Logger;
import ruiseki.omoshiroikamo.common.util.lib.LibMods;

public class WailaCompat {

    public static void init() {
        if (!LibMods.Waila.isLoaded()) {
            return;
        }
        EntityProvider.init();
        BlockProvider.init();
        Logger.info("Loaded WailaCompat");
    }
}
