package ruiseki.omoshiroikamo.plugin.structureLib;

import ruiseki.omoshiroikamo.common.block.multiblock.quantumBeacon.QuantumBeaconStructure;
import ruiseki.omoshiroikamo.common.block.multiblock.quantumExtractor.ore.QuantumOreExtractorStructure;
import ruiseki.omoshiroikamo.common.block.multiblock.quantumExtractor.res.QuantumResExtractorStructure;
import ruiseki.omoshiroikamo.common.block.multiblock.solarArray.SolarArrayStructure;
import ruiseki.omoshiroikamo.common.util.Logger;

public class StructureCompat {

    public static void postInit() {
        SolarArrayStructure.registerStructureInfo();
        QuantumOreExtractorStructure.registerStructureInfo();
        QuantumResExtractorStructure.registerStructureInfo();
        QuantumBeaconStructure.registerStructureInfo();
    }

    /**
     * Reload all structure definitions from StructureManager.
     * This re-registers the structures with StructureLib using updated shapes.
     */
    public static void reload() {
        SolarArrayStructure.registerStructureInfo();
        QuantumOreExtractorStructure.registerStructureInfo();
        QuantumResExtractorStructure.registerStructureInfo();
        QuantumBeaconStructure.registerStructureInfo();
        Logger.info("StructureLib definitions reloaded");
    }
}
