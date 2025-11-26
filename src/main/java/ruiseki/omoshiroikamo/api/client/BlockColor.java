package ruiseki.omoshiroikamo.api.client;

import java.util.HashMap;
import java.util.Map;

import net.minecraft.block.Block;
import net.minecraft.world.IBlockAccess;

public class BlockColor {

    private static final Map<Block, IBlockColor> HANDLERS = new HashMap<>();

    public static void registerBlockColors(IBlockColor handler, Block... blocks) {
        for (Block b : blocks) {
            HANDLERS.put(b, handler);
        }
    }

    public static int getColor(Block block, IBlockAccess world, int x, int y, int z, int tintIndex) {
        IBlockColor handler = HANDLERS.get(block);
        if (handler != null) {
            return handler.colorMultiplier(world, x, y, z, tintIndex);
        }
        return block.colorMultiplier(world, x, y, z);
    }
}
