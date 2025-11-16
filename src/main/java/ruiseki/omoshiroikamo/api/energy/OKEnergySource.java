package ruiseki.omoshiroikamo.api.energy;

import net.minecraftforge.common.util.ForgeDirection;

import cofh.api.energy.IEnergyProvider;

public class OKEnergySource implements EnergySource {

    private final IEnergyProvider provider;
    private final ForgeDirection side;

    public OKEnergySource(IEnergyProvider provider, ForgeDirection side) {
        this.provider = provider;
        this.side = side;
    }

    @Override
    public int extract(int amount, boolean simulate) {
        return provider.extractEnergy(side, amount, simulate);
    }
}
