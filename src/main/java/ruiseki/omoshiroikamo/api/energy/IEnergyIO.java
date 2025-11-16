package ruiseki.omoshiroikamo.api.energy;

import net.minecraftforge.common.util.ForgeDirection;

import cofh.api.energy.IEnergyHandler;

public interface IEnergyIO extends IEnergyHandler {

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
