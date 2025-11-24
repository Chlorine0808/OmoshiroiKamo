package ruiseki.omoshiroikamo.common.item.backpack.wrapper;

import net.minecraft.item.ItemStack;

public class AdvancedFilterUpgradeWrapper extends AdvancedUpgradeWrapper implements IFilterUpgrade {

    public AdvancedFilterUpgradeWrapper(ItemStack upgrade) {
        super(upgrade);
    }

    @Override
    public FilterWayType filterWay() {
        return null;
    }

    @Override
    public void setFilterWay(FilterWayType filterWay) {

    }

    @Override
    public boolean canInsert(ItemStack stack) {
        return false;
    }

    @Override
    public boolean canExtract(ItemStack stack) {
        return false;
    }
}
