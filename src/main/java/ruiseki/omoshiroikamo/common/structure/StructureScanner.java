package ruiseki.omoshiroikamo.common.structure;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import net.minecraft.block.Block;
import net.minecraft.world.World;

import ruiseki.omoshiroikamo.common.util.Logger;

/**
 * ワールド内の構造をスキャンしてJSONに変換するユーティリティ
 */
public class StructureScanner {

    // シンボルに使用する文字（順番に割り当て）
    private static final String SYMBOLS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";

    /**
     * 指定範囲をスキャンしてJSONファイルに保存
     *
     * @param world     ワールド
     * @param name      構造体名
     * @param x1,       y1, z1 開始座標
     * @param x2,       y2, z2 終了座標
     * @param configDir 設定ディレクトリ
     * @return 成功時true
     */
    public static ScanResult scan(World world, String name, int x1, int y1, int z1, int x2, int y2, int z2,
        File configDir) {

        // 座標を正規化（小さい方を開始点に）
        int minX = Math.min(x1, x2);
        int minY = Math.min(y1, y2);
        int minZ = Math.min(z1, z2);
        int maxX = Math.max(x1, x2);
        int maxY = Math.max(y1, y2);
        int maxZ = Math.max(z1, z2);

        // ブロック→シンボルのマッピング
        Map<String, Character> blockToSymbol = new LinkedHashMap<>();
        Map<Character, String> symbolToBlock = new LinkedHashMap<>();
        int symbolIndex = 0;

        // レイヤー（Y軸ごと）
        List<List<String>> layers = new ArrayList<>();
        int overflowCount = 0;

        // 上から下にスキャン（既存のJSON形式に合わせる）
        for (int y = maxY; y >= minY; y--) {
            List<String> layer = new ArrayList<>();

            for (int z = minZ; z <= maxZ; z++) {
                StringBuilder row = new StringBuilder();

                for (int x = minX; x <= maxX; x++) {
                    Block block = world.getBlock(x, y, z);
                    int meta = world.getBlockMetadata(x, y, z);

                    // 空気ブロックはスペースとして出力（チェックしない）
                    String blockName = Block.blockRegistry.getNameForObject(block);
                    if (blockName == null || "minecraft:air".equals(blockName)) {
                        row.append(' ');
                        continue;
                    }

                    // ブロックIDを取得
                    String blockId = getBlockId(block, meta);

                    // シンボルを割り当て
                    Character symbol = blockToSymbol.get(blockId);
                    if (symbol == null) {
                        if (symbolIndex < SYMBOLS.length()) {
                            // シンボル割り当て可能
                            symbol = SYMBOLS.charAt(symbolIndex++);
                            blockToSymbol.put(blockId, symbol);
                            symbolToBlock.put(symbol, blockId);
                            row.append(symbol);
                        } else {
                            // シンボル溢れ：ブロックIDを{}で直接記述
                            row.append("{")
                                .append(blockId)
                                .append("}");
                            overflowCount++;
                        }
                    } else {
                        row.append(symbol);
                    }
                }

                layer.add(row.toString());
            }

            layers.add(layer);
        }

        // JSONを生成
        StringBuilder json = new StringBuilder();
        json.append("[\n");
        json.append("  {\n");
        json.append("    \"name\": \"")
            .append(name)
            .append("\",\n");

        // レイヤー
        json.append("    \"layers\": [\n");
        for (int i = 0; i < layers.size(); i++) {
            List<String> layer = layers.get(i);
            json.append("      [\n");
            for (int j = 0; j < layer.size(); j++) {
                json.append("        \"")
                    .append(layer.get(j))
                    .append("\"");
                if (j < layer.size() - 1) json.append(",");
                json.append("\n");
            }
            json.append("      ]");
            if (i < layers.size() - 1) json.append(",");
            json.append("\n");
        }
        json.append("    ],\n");

        // マッピング
        json.append("    \"mappings\": {\n");
        int count = 0;
        for (Map.Entry<Character, String> entry : symbolToBlock.entrySet()) {
            json.append("      \"")
                .append(entry.getKey())
                .append("\": \"")
                .append(entry.getValue())
                .append("\"");
            if (++count < symbolToBlock.size()) json.append(",");
            json.append("\n");
        }
        json.append("    }\n");

        json.append("  }\n");
        json.append("]\n");

        // ファイルに保存
        File structuresDir = new File(configDir, "structures");
        if (!structuresDir.exists()) {
            structuresDir.mkdirs();
        }

        File outputFile = new File(structuresDir, "scanned_" + name + ".json");

        try (PrintWriter writer = new PrintWriter(new FileWriter(outputFile))) {
            writer.print(json.toString());
            Logger.info("Scanned structure saved to: " + outputFile.getName());

            String message = "Saved to " + outputFile.getName();
            if (overflowCount > 0) {
                message += " (WARNING: " + overflowCount + " blocks used direct IDs)";
                Logger.warn("Structure scan: " + overflowCount + " blocks exceeded symbol limit and used direct IDs");
            }
            return new ScanResult(true, message, outputFile);
        } catch (IOException e) {
            Logger.error("Failed to save scanned structure", e);
            return new ScanResult(false, "Failed to save: " + e.getMessage(), null);
        }
    }

    /**
     * ブロックIDを文字列で取得
     * 注意: 空気ブロックはこのメソッドに到達する前にスペースとして処理される
     */
    private static String getBlockId(Block block, int meta) {
        String blockName = Block.blockRegistry.getNameForObject(block);
        if (blockName == null) {
            return "unknown:block:0";
        }

        // メタデータが0の場合は省略可能
        if (meta == 0) {
            return blockName;
        }

        return blockName + ":" + meta;
    }

    /**
     * スキャン結果
     */
    public static class ScanResult {

        public final boolean success;
        public final String message;
        public final File outputFile;

        public ScanResult(boolean success, String message, File outputFile) {
            this.success = success;
            this.message = message;
            this.outputFile = outputFile;
        }
    }
}
