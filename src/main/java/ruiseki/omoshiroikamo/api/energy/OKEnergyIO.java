package ruiseki.omoshiroikamo.api.energy;

import net.minecraftforge.common.util.ForgeDirection;

import cofh.api.energy.IEnergyHandler;

public class OKEnergyIO implements EnergyIO {

    private final IEnergyHandler handler;
    private final ForgeDirection side;

    public OKEnergyIO(IEnergyHandler handler, ForgeDirection side) {
        this.handler = handler;
        this.side = side;
    }

    @Override
    public int extract(int amount, boolean simulate) {
        return handler.extractEnergy(side, amount, simulate);
    }

    @Override
    public int insert(int amount, boolean simulate) {
        return handler.receiveEnergy(side, amount, simulate);
    }
}
