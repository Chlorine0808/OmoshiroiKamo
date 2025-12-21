package ruiseki.omoshiroikamo.common.structure;

import java.util.List;
import java.util.Map;

/**
 * JSON構造定義のデータクラス群
 */
public class StructureDefinitionData {

    /**
     * JSONファイル全体を表す（配列形式）
     */
    public static class StructureFile {

        public List<StructureEntry> entries;
    }

    /**
     * 1つの構造体エントリ（default または 具体的な構造体）
     */
    public static class StructureEntry {

        /** 構造体名（"default" または "oreExtractorTier1" など） */
        public String name;

        /** レイヤー配列（defaultの場合はnull） */
        public List<Layer> layers;

        /** ブロックマッピング */
        public Map<String, Object> mappings;
    }

    /**
     * 1つのレイヤー（名前付き）
     */
    public static class Layer {

        /** レイヤー名（"controller", "core", "base" など） */
        public String name;

        /** 行の配列（各行は文字列） */
        public List<String> rows;
    }

    /**
     * ブロックマッピングエントリ（単一ブロック or 複数候補）
     */
    public static class BlockMapping {

        /** 単一ブロックの場合のID */
        public String block;

        /** 複数候補の場合のリスト */
        public List<BlockEntry> blocks;

        /** シンボル全体の最小数（オプション） */
        public Integer min;

        /** シンボル全体の最大数（オプション） */
        public Integer max;
    }

    /**
     * 個別ブロックエントリ（複数候補内の1つ）
     */
    public static class BlockEntry {

        /** ブロックID（mod:block:meta 形式、*でany meta） */
        public String id;

        /** このブロックの最小数 */
        public Integer min;

        /** このブロックの最大数 */
        public Integer max;
    }
}
