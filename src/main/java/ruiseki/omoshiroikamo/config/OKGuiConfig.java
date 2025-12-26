package ruiseki.omoshiroikamo.config;

import net.minecraft.client.gui.GuiScreen;

import com.gtnewhorizon.gtnhlib.config.ConfigException;
import com.gtnewhorizon.gtnhlib.config.SimpleGuiConfig;

import ruiseki.omoshiroikamo.config.backport.BackpackConfig;
import ruiseki.omoshiroikamo.config.backport.BackportConfigs;
import ruiseki.omoshiroikamo.config.backport.ChickenConfig;
import ruiseki.omoshiroikamo.config.backport.CowConfig;
import ruiseki.omoshiroikamo.config.backport.DMLConfig;
import ruiseki.omoshiroikamo.config.backport.multiblock.MultiblockWorldGenConfig;
import ruiseki.omoshiroikamo.config.backport.multiblock.QuantumBeaconConfig;
import ruiseki.omoshiroikamo.config.backport.multiblock.QuantumExtractorConfig;
import ruiseki.omoshiroikamo.config.backport.multiblock.SolarArrayConfig;
import ruiseki.omoshiroikamo.core.lib.LibMisc;

public class OKGuiConfig extends SimpleGuiConfig {

    public OKGuiConfig(GuiScreen parent) throws ConfigException {
        super(
            parent,
            LibMisc.MOD_ID,
            LibMisc.MOD_NAME,
            GeneralConfig.class,
            BackportConfigs.class,
            QuantumExtractorConfig.class,
            SolarArrayConfig.class,
            QuantumBeaconConfig.class,
            MultiblockWorldGenConfig.class,
            ChickenConfig.class,
            CowConfig.class,
            BackpackConfig.class,
            DMLConfig.class);
    }
}
