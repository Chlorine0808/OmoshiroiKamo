package ruiseki.omoshiroikamo.common.item.backpack.wrapper;

import net.minecraft.item.ItemStack;

public class RestockUpgradeWrapper extends BasicUpgradeWrapper implements IRestockUpgrade {

    public RestockUpgradeWrapper(ItemStack upgrade) {
        super(upgrade);
    }

    @Override
    public boolean canRestock(ItemStack stack) {
        return false;
    }
}
