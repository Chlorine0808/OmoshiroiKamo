package ruiseki.omoshiroikamo.api.energy;

import net.minecraftforge.common.util.ForgeDirection;

import cofh.api.energy.IEnergyReceiver;

public interface IEnergySink extends IEnergyReceiver {

    int getEnergyStored();

    int getMaxEnergyStored();

    void setEnergyStored(int stored);

    @Override
    default int getEnergyStored(ForgeDirection forgeDirection) {
        return getEnergyStored();
    }

    @Override
    default int getMaxEnergyStored(ForgeDirection forgeDirection) {
        return getMaxEnergyStored();
    }

}
