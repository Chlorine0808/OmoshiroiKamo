package ruiseki.omoshiroikamo.common.item.backpack.wrapper;

import net.minecraft.item.ItemStack;

public class DepositUpgradeWrapper extends BasicUpgradeWrapper implements IDepositUpgrade {

    public DepositUpgradeWrapper(ItemStack upgrade) {
        super(upgrade);
    }

    @Override
    public boolean canDeposit(ItemStack stack) {
        return checkFilter(stack);
    }
}
