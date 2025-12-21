package ruiseki.omoshiroikamo.common.structure;

import net.minecraft.block.Block;

import cpw.mods.fml.common.registry.GameRegistry;

/**
 * ブロックID文字列からBlockオブジェクトを解決するユーティリティ
 */
public class BlockResolver {

    /**
     * "mod:block:meta" 形式の文字列からBlockとmetaを解決
     * 
     * @param blockId "mod:block:meta" 形式（例: "minecraft:iron_block:0"）
     * @return 解決結果、失敗時はnull
     */
    public static ResolvedBlock resolve(String blockId) {
        if (blockId == null || blockId.isEmpty()) {
            return null;
        }

        // "air" の特別処理
        if ("air".equalsIgnoreCase(blockId)) {
            return new ResolvedBlock(null, 0, true);
        }

        String[] parts = blockId.split(":");
        if (parts.length < 2) {
            return null;
        }

        String modId = parts[0];
        String blockName = parts[1];
        int meta = 0;
        boolean anyMeta = false;

        if (parts.length >= 3) {
            if ("*".equals(parts[2])) {
                anyMeta = true;
            } else {
                try {
                    meta = Integer.parseInt(parts[2]);
                } catch (NumberFormatException e) {
                    return null;
                }
            }
        }

        Block block = GameRegistry.findBlock(modId, blockName);
        if (block == null) {
            return null;
        }

        return new ResolvedBlock(block, meta, anyMeta);
    }

    /**
     * 解決されたブロック情報
     */
    public static class ResolvedBlock {

        public final Block block;
        public final int meta;
        public final boolean anyMeta;
        public final boolean isAir;

        public ResolvedBlock(Block block, int meta, boolean anyMeta) {
            this.block = block;
            this.meta = meta;
            this.anyMeta = anyMeta;
            this.isAir = (block == null);
        }
    }
}
