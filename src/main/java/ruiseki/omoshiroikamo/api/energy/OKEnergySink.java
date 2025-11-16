package ruiseki.omoshiroikamo.api.energy;

import net.minecraftforge.common.util.ForgeDirection;

import cofh.api.energy.IEnergyReceiver;

public class OKEnergySink implements EnergySink {

    private final IEnergyReceiver receiver;
    private final ForgeDirection side;

    public OKEnergySink(IEnergyReceiver receiver, ForgeDirection side) {
        this.receiver = receiver;
        this.side = side;
    }

    @Override
    public int insert(int amount, boolean simulate) {
        return receiver.receiveEnergy(side, amount, simulate);
    }
}
