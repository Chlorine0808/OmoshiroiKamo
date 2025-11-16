package ruiseki.omoshiroikamo.api.energy;

import net.minecraft.item.ItemStack;

import cofh.api.energy.IEnergyContainerItem;

public class OKItemEnergyIO implements EnergyIO {

    private final IEnergyContainerItem handler;
    private final ItemStack itemStack;

    public OKItemEnergyIO(IEnergyContainerItem handler, ItemStack itemStack) {
        this.handler = handler;
        this.itemStack = itemStack;

    }

    @Override
    public int extract(int amount, boolean simulate) {
        return handler.extractEnergy(itemStack, amount, simulate);
    }

    @Override
    public int insert(int amount, boolean simulate) {
        return handler.receiveEnergy(itemStack, amount, simulate);
    }
}
