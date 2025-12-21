package ruiseki.omoshiroikamo.common.structure;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import ruiseki.omoshiroikamo.common.structure.StructureDefinitionData.BlockMapping;
import ruiseki.omoshiroikamo.common.util.Logger;
import ruiseki.omoshiroikamo.common.util.lib.LibMisc;

/**
 * カスタムストラクチャーシステムのメインマネージャー
 */
public class StructureManager {

    private static StructureManager INSTANCE;

    private final Map<String, StructureJsonLoader> loaders = new HashMap<>();
    private File configDir;
    private boolean initialized = false;

    private StructureManager() {}

    public static StructureManager getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new StructureManager();
        }
        return INSTANCE;
    }

    /**
     * 初期化（PreInitで呼び出し）
     */
    public void initialize(File minecraftDir) {
        if (initialized) return;

        this.configDir = new File(minecraftDir, "config/" + LibMisc.MOD_ID);
        if (!configDir.exists()) {
            configDir.mkdirs();
        }

        // デフォルトJSONを生成
        DefaultStructureGenerator.generateAllIfMissing(configDir);

        // JSONファイルをロード
        loadStructureFile("ore_miner");
        loadStructureFile("res_miner");
        loadStructureFile("solar_array");
        loadStructureFile("quantum_beacon");

        initialized = true;
        Logger.info("StructureManager initialized");
    }

    /**
     * 構造体JSONファイルをロード
     */
    private void loadStructureFile(String name) {
        File file = new File(configDir, "structures/" + name + ".json");
        StructureJsonLoader loader = new StructureJsonLoader();

        if (loader.loadFromFile(file)) {
            loaders.put(name, loader);
        }
    }

    /**
     * 構造体の形状を取得
     * 
     * @param fileKey       ファイルキー（"ore_miner", "res_miner" など）
     * @param structureName 構造体名（"oreExtractorTier1" など）
     * @return String[][] 形状、見つからない場合はnull
     */
    public String[][] getShape(String fileKey, String structureName) {
        StructureJsonLoader loader = loaders.get(fileKey);
        if (loader == null) {
            Logger.warn("Structure loader not found: " + fileKey);
            return null;
        }

        String[][] shape = loader.getShape(structureName);
        if (shape == null) {
            Logger.warn("Structure not found: " + structureName + " in " + fileKey);
        }

        return shape;
    }

    /**
     * ブロックマッピングを取得
     */
    public BlockMapping getMapping(String fileKey, String structureName, char symbol) {
        StructureJsonLoader loader = loaders.get(fileKey);
        if (loader == null) {
            return null;
        }
        return loader.getMapping(structureName, symbol);
    }

    /**
     * ファイルを再読み込み
     */
    public void reload() {
        loaders.clear();
        loadStructureFile("ore_miner");
        loadStructureFile("res_miner");
        loadStructureFile("solar_array");
        loadStructureFile("quantum_beacon");
        Logger.info("StructureManager reloaded");
    }

    // ===== 便利メソッド =====

    /**
     * Ore Miner の形状を取得
     */
    public static String[][] getOreMinerShape(int tier) {
        return getInstance().getShape("ore_miner", "oreExtractorTier" + tier);
    }

    /**
     * Resource Miner の形状を取得
     */
    public static String[][] getResMinerShape(int tier) {
        return getInstance().getShape("res_miner", "resExtractorTier" + tier);
    }

    /**
     * Solar Array の形状を取得
     */
    public static String[][] getSolarArrayShape(int tier) {
        return getInstance().getShape("solar_array", "solarArrayTier" + tier);
    }

    /**
     * Quantum Beacon の形状を取得
     */
    public static String[][] getBeaconShape(int tier) {
        return getInstance().getShape("quantum_beacon", "beaconTier" + tier);
    }
}
