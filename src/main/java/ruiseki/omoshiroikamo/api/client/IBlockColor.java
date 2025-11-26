package ruiseki.omoshiroikamo.api.client;

import net.minecraft.world.IBlockAccess;

public interface IBlockColor {

    int colorMultiplier(IBlockAccess world, int x, int y, int z, int tintIndex);
}
