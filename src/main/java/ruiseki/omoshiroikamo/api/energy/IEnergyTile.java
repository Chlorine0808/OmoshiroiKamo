package ruiseki.omoshiroikamo.api.energy;

import com.gtnewhorizon.gtnhlib.blockpos.BlockPos;
import net.minecraftforge.common.util.ForgeDirection;

public interface IEnergyTile {

    int getEnergyStored();

    int getMaxEnergyStored();

    void setEnergyStored(int stored);

    boolean canConnectEnergy(ForgeDirection var1);

    BlockPos getLocation();
}
