package ruiseki.omoshiroikamo.common.structure;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import ruiseki.omoshiroikamo.common.block.multiblock.quantumBeacon.QuantumBeaconShapes;
import ruiseki.omoshiroikamo.common.block.multiblock.quantumExtractor.ore.QuantumOreExtractorShapes;
import ruiseki.omoshiroikamo.common.block.multiblock.quantumExtractor.res.QuantumResExtractorShapes;
import ruiseki.omoshiroikamo.common.block.multiblock.solarArray.SolarArrayShapes;
import ruiseki.omoshiroikamo.common.util.Logger;

/**
 * デフォルト構造体JSONファイルを生成
 */
public class DefaultStructureGenerator {

    private static final Gson GSON = new GsonBuilder().setPrettyPrinting()
        .create();

    /**
     * 全てのデフォルト構造体JSONを生成
     */
    public static void generateAllIfMissing(File configDir) {
        File structuresDir = new File(configDir, "structures");
        if (!structuresDir.exists()) {
            structuresDir.mkdirs();
        }

        // Ore Miner
        File oreMinerFile = new File(structuresDir, "ore_miner.json");
        if (!oreMinerFile.exists()) {
            generateOreMinerJson(oreMinerFile);
        }

        // Resource Miner
        File resMinerFile = new File(structuresDir, "res_miner.json");
        if (!resMinerFile.exists()) {
            generateResMinerJson(resMinerFile);
        }

        // Solar Array
        File solarFile = new File(structuresDir, "solar_array.json");
        if (!solarFile.exists()) {
            generateSolarArrayJson(solarFile);
        }

        // Quantum Beacon
        File beaconFile = new File(structuresDir, "quantum_beacon.json");
        if (!beaconFile.exists()) {
            generateBeaconJson(beaconFile);
        }
    }

    /**
     * Ore Miner JSONを生成
     */
    private static void generateOreMinerJson(File file) {
        List<Object> entries = new ArrayList<>();

        // デフォルトマッピング
        entries.add(createDefaultMappings("oreMiner"));

        // 各Tierの構造体
        entries.add(createStructureEntry("oreExtractorTier1", QuantumOreExtractorShapes.SHAPE_TIER_1));
        entries.add(createStructureEntry("oreExtractorTier2", QuantumOreExtractorShapes.SHAPE_TIER_2));
        entries.add(createStructureEntry("oreExtractorTier3", QuantumOreExtractorShapes.SHAPE_TIER_3));
        entries.add(createStructureEntry("oreExtractorTier4", QuantumOreExtractorShapes.SHAPE_TIER_4));
        entries.add(createStructureEntry("oreExtractorTier5", QuantumOreExtractorShapes.SHAPE_TIER_5));
        entries.add(createStructureEntry("oreExtractorTier6", QuantumOreExtractorShapes.SHAPE_TIER_6));

        writeJson(file, entries);
    }

    /**
     * Resource Miner JSONを生成
     */
    private static void generateResMinerJson(File file) {
        List<Object> entries = new ArrayList<>();

        entries.add(createDefaultMappings("resMiner"));

        entries.add(createStructureEntry("resExtractorTier1", QuantumResExtractorShapes.SHAPE_TIER_1));
        entries.add(createStructureEntry("resExtractorTier2", QuantumResExtractorShapes.SHAPE_TIER_2));
        entries.add(createStructureEntry("resExtractorTier3", QuantumResExtractorShapes.SHAPE_TIER_3));
        entries.add(createStructureEntry("resExtractorTier4", QuantumResExtractorShapes.SHAPE_TIER_4));
        entries.add(createStructureEntry("resExtractorTier5", QuantumResExtractorShapes.SHAPE_TIER_5));
        entries.add(createStructureEntry("resExtractorTier6", QuantumResExtractorShapes.SHAPE_TIER_6));

        writeJson(file, entries);
    }

    /**
     * Solar Array JSONを生成
     */
    private static void generateSolarArrayJson(File file) {
        List<Object> entries = new ArrayList<>();

        entries.add(createDefaultMappings("solarArray"));

        entries.add(createStructureEntry("solarArrayTier1", SolarArrayShapes.SHAPE_TIER_1));
        entries.add(createStructureEntry("solarArrayTier2", SolarArrayShapes.SHAPE_TIER_2));
        entries.add(createStructureEntry("solarArrayTier3", SolarArrayShapes.SHAPE_TIER_3));
        entries.add(createStructureEntry("solarArrayTier4", SolarArrayShapes.SHAPE_TIER_4));
        entries.add(createStructureEntry("solarArrayTier5", SolarArrayShapes.SHAPE_TIER_5));
        entries.add(createStructureEntry("solarArrayTier6", SolarArrayShapes.SHAPE_TIER_6));

        writeJson(file, entries);
    }

    /**
     * Quantum Beacon JSONを生成
     */
    private static void generateBeaconJson(File file) {
        List<Object> entries = new ArrayList<>();

        entries.add(createDefaultMappings("beacon"));

        entries.add(createStructureEntry("beaconTier1", QuantumBeaconShapes.SHAPE_TIER_1));
        entries.add(createStructureEntry("beaconTier2", QuantumBeaconShapes.SHAPE_TIER_2));
        entries.add(createStructureEntry("beaconTier3", QuantumBeaconShapes.SHAPE_TIER_3));
        entries.add(createStructureEntry("beaconTier4", QuantumBeaconShapes.SHAPE_TIER_4));
        entries.add(createStructureEntry("beaconTier5", QuantumBeaconShapes.SHAPE_TIER_5));
        entries.add(createStructureEntry("beaconTier6", QuantumBeaconShapes.SHAPE_TIER_6));

        writeJson(file, entries);
    }

    /**
     * デフォルトマッピングオブジェクトを作成
     */
    private static Map<String, Object> createDefaultMappings(String machineType) {
        Map<String, Object> entry = new LinkedHashMap<>();
        entry.put("name", "default");

        Map<String, Object> mappings = new LinkedHashMap<>();

        // 共通マッピング
        mappings.put("_", "air");
        mappings.put("F", "omoshiroikamo:basalt_structure:0");
        mappings.put("P", "omoshiroikamo:machine_base:0");
        mappings.put("C", "omoshiroikamo:laser_core:0");

        // マシンタイプ別のマッピング
        if ("oreMiner".equals(machineType) || "resMiner".equals(machineType)) {
            // コントローラー
            Map<String, Object> controller = new LinkedHashMap<>();
            String controllerBlock = "oreMiner".equals(machineType) ? "omoshiroikamo:quantum_ore_extractor:0"
                : "omoshiroikamo:quantum_res_extractor:0";
            controller.put("block", controllerBlock);
            controller.put("max", 1);
            mappings.put("Q", controller);

            // レンズ
            Map<String, Object> lens = new LinkedHashMap<>();
            List<Map<String, Object>> lensBlocks = new ArrayList<>();
            Map<String, Object> normalLens = new LinkedHashMap<>();
            normalLens.put("id", "omoshiroikamo:lens:0");
            normalLens.put("max", 1);
            lensBlocks.add(normalLens);
            Map<String, Object> coloredLens = new LinkedHashMap<>();
            coloredLens.put("id", "omoshiroikamo:colored_lens:*");
            coloredLens.put("max", 1);
            lensBlocks.add(coloredLens);
            lens.put("blocks", lensBlocks);
            mappings.put("L", lens);

            // モディファイア
            Map<String, Object> modifier = new LinkedHashMap<>();
            List<Map<String, Object>> modBlocks = new ArrayList<>();
            Map<String, Object> nullMod = new LinkedHashMap<>();
            nullMod.put("id", "omoshiroikamo:modifier_null:0");
            modBlocks.add(nullMod);
            Map<String, Object> accMod = new LinkedHashMap<>();
            accMod.put("id", "omoshiroikamo:modifier_accuracy:0");
            modBlocks.add(accMod);
            Map<String, Object> speedMod = new LinkedHashMap<>();
            speedMod.put("id", "omoshiroikamo:modifier_speed:0");
            modBlocks.add(speedMod);
            modifier.put("blocks", modBlocks);
            mappings.put("A", modifier);

        } else if ("solarArray".equals(machineType)) {
            Map<String, Object> controller = new LinkedHashMap<>();
            controller.put("block", "omoshiroikamo:solar_array:0");
            controller.put("max", 1);
            mappings.put("Q", controller);

            // ソーラーセル
            Map<String, Object> cell = new LinkedHashMap<>();
            List<Map<String, Object>> cellBlocks = new ArrayList<>();
            for (int i = 0; i <= 5; i++) {
                Map<String, Object> c = new LinkedHashMap<>();
                c.put("id", "omoshiroikamo:solar_cell:" + i);
                cellBlocks.add(c);
            }
            cell.put("blocks", cellBlocks);
            mappings.put("G", cell);

            // モディファイア
            Map<String, Object> modifier = new LinkedHashMap<>();
            List<Map<String, Object>> modBlocks = new ArrayList<>();
            Map<String, Object> nullMod = new LinkedHashMap<>();
            nullMod.put("id", "omoshiroikamo:modifier_null:0");
            modBlocks.add(nullMod);
            Map<String, Object> piezoMod = new LinkedHashMap<>();
            piezoMod.put("id", "omoshiroikamo:modifier_piezo:0");
            modBlocks.add(piezoMod);
            modifier.put("blocks", modBlocks);
            mappings.put("A", modifier);

        } else if ("beacon".equals(machineType)) {
            Map<String, Object> controller = new LinkedHashMap<>();
            controller.put("block", "omoshiroikamo:quantum_beacon:0");
            controller.put("max", 1);
            mappings.put("Q", controller);
        }

        entry.put("mappings", mappings);
        return entry;
    }

    /**
     * 構造体エントリを作成
     */
    private static Map<String, Object> createStructureEntry(String name, String[][] shape) {
        Map<String, Object> entry = new LinkedHashMap<>();
        entry.put("name", name);

        List<Map<String, Object>> layers = new ArrayList<>();
        for (int i = 0; i < shape.length; i++) {
            Map<String, Object> layer = new LinkedHashMap<>();
            layer.put("name", "layer" + i);

            List<String> rows = new ArrayList<>();
            for (String row : shape[i]) {
                rows.add(row);
            }
            layer.put("rows", rows);

            layers.add(layer);
        }
        entry.put("layers", layers);
        entry.put("mappings", new LinkedHashMap<>());

        return entry;
    }

    /**
     * JSONファイルに書き出し
     */
    private static void writeJson(File file, Object data) {
        try (FileWriter writer = new FileWriter(file)) {
            GSON.toJson(data, writer);
            Logger.info("Generated structure file: " + file.getName());
        } catch (IOException e) {
            Logger.error("Failed to generate structure file: " + file.getAbsolutePath(), e);
        }
    }
}
