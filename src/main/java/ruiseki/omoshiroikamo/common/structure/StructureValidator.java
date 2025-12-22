package ruiseki.omoshiroikamo.common.structure;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import ruiseki.omoshiroikamo.common.structure.StructureDefinitionData.BlockEntry;
import ruiseki.omoshiroikamo.common.structure.StructureDefinitionData.BlockMapping;
import ruiseki.omoshiroikamo.common.structure.StructureDefinitionData.Layer;
import ruiseki.omoshiroikamo.common.structure.StructureDefinitionData.StructureEntry;

/**
 * 構造体定義のバリデーション
 */
public class StructureValidator {

    /** 収集されたエラーリスト */
    private final List<String> errors = new ArrayList<>();

    /** バリデーション対象のローダー */
    private final StructureJsonLoader loader;

    public StructureValidator(StructureJsonLoader loader) {
        this.loader = loader;
    }

    /**
     * エラーリストを取得
     */
    public List<String> getErrors() {
        return new ArrayList<>(errors);
    }

    /**
     * エラーがあるか
     */
    public boolean hasErrors() {
        return !errors.isEmpty();
    }

    /**
     * エラーを追加
     */
    private void addError(String error) {
        errors.add(error);
    }

    /**
     * 全構造体をバリデーション
     *
     * @return エラーがあればtrue
     */
    public boolean validateAll() {
        boolean hasErrors = false;

        for (String structureName : loader.getStructureNames()) {
            StructureEntry entry = loader.getStructureEntry(structureName);
            if (entry != null) {
                if (validateStructure(structureName, entry)) {
                    hasErrors = true;
                }
            }
        }

        return hasErrors;
    }

    /**
     * 構造体をバリデーション
     *
     * @param name  構造体名
     * @param entry 構造体エントリ
     * @return エラーがあればtrue
     */
    public boolean validateStructure(String name, StructureEntry entry) {
        boolean hasErrors = false;

        if (entry.layers == null || entry.layers.isEmpty()) {
            addError("[" + name + "] No layers defined");
            return true;
        }

        // レイヤーサイズの基準を取得（最初のレイヤーから）
        Layer firstLayer = entry.layers.get(0);
        if (firstLayer.rows == null || firstLayer.rows.isEmpty()) {
            addError("[" + name + "] Layer 0 has no rows");
            return true;
        }

        int expectedDepth = firstLayer.rows.size();
        int expectedWidth = firstLayer.rows.get(0)
            .length();

        // 各レイヤーをバリデーション
        for (int layerIndex = 0; layerIndex < entry.layers.size(); layerIndex++) {
            Layer layer = entry.layers.get(layerIndex);

            if (layer.rows == null || layer.rows.isEmpty()) {
                addError("[" + name + "] Layer " + layerIndex + " has no rows");
                hasErrors = true;
                continue;
            }

            // レイヤーサイズの一致を確認
            if (layer.rows.size() != expectedDepth) {
                addError(
                    "[" + name
                        + "] Layer "
                        + layerIndex
                        + " has "
                        + layer.rows.size()
                        + " rows, expected "
                        + expectedDepth);
                hasErrors = true;
            }

            // 各行の長さを確認（長方形）
            for (int rowIndex = 0; rowIndex < layer.rows.size(); rowIndex++) {
                String row = layer.rows.get(rowIndex);
                if (row.length() != expectedWidth) {
                    addError(
                        "[" + name
                            + "] Layer "
                            + layerIndex
                            + " row "
                            + rowIndex
                            + " has length "
                            + row.length()
                            + ", expected "
                            + expectedWidth);
                    hasErrors = true;
                }

                // シンボルマッピングを確認
                for (int charIndex = 0; charIndex < row.length(); charIndex++) {
                    char symbol = row.charAt(charIndex);

                    // スペースはスキップ（何もチェックしない）
                    if (symbol == ' ') {
                        continue;
                    }

                    // マッピングが存在するか確認
                    BlockMapping mapping = loader.getMapping(name, symbol);
                    if (mapping == null) {
                        addError(
                            "[" + name
                                + "] Unknown symbol '"
                                + symbol
                                + "' at layer "
                                + layerIndex
                                + " row "
                                + rowIndex
                                + " pos "
                                + charIndex);
                        hasErrors = true;
                    }
                }
            }
        }

        return hasErrors;
    }

    /**
     * マッピングされたブロックIDが有効か検証
     * 注意: この検証はゲームがロード済みの状態でのみ正しく動作する
     *
     * @param name 構造体名
     * @return エラーがあればtrue
     */
    public boolean validateBlockIds(String name) {
        boolean hasErrors = false;
        StructureEntry entry = loader.getStructureEntry(name);
        if (entry == null) return false;

        // デフォルトマッピングを検証
        Map<Character, BlockMapping> defaultMappings = loader.getDefaultMappings();
        for (Map.Entry<Character, BlockMapping> mapEntry : defaultMappings.entrySet()) {
            if (!validateBlockMapping(name, mapEntry.getKey(), mapEntry.getValue())) {
                hasErrors = true;
            }
        }

        // 構造体固有マッピングを検証
        if (entry.mappings != null) {
            for (Map.Entry<String, Object> mapEntry : entry.mappings.entrySet()) {
                if (mapEntry.getValue() instanceof BlockMapping) {
                    char symbol = mapEntry.getKey()
                        .charAt(0);
                    if (!validateBlockMapping(name, symbol, (BlockMapping) mapEntry.getValue())) {
                        hasErrors = true;
                    }
                }
            }
        }

        return hasErrors;
    }

    /**
     * ブロックマッピングを検証
     */
    private boolean validateBlockMapping(String structureName, char symbol, BlockMapping mapping) {
        if (mapping == null) return true;

        // 単一ブロックの場合
        if (mapping.block != null && !mapping.block.isEmpty()) {
            BlockResolver.ResolvedBlock resolved = BlockResolver.resolve(mapping.block);
            if (resolved == null && !"air".equalsIgnoreCase(mapping.block)) {
                addError("[" + structureName + "] Invalid block ID for symbol '" + symbol + "': " + mapping.block);
                return false;
            }
        }

        // 複数ブロックの場合
        if (mapping.blocks != null) {
            for (BlockEntry blockEntry : mapping.blocks) {
                if (blockEntry.id != null) {
                    BlockResolver.ResolvedBlock resolved = BlockResolver.resolve(blockEntry.id);
                    if (resolved == null && !"air".equalsIgnoreCase(blockEntry.id)) {
                        addError(
                            "[" + structureName + "] Invalid block ID for symbol '" + symbol + "': " + blockEntry.id);
                        return false;
                    }
                }
            }
        }

        return true;
    }
}
