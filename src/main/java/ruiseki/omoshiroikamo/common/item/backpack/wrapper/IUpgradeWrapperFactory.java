package ruiseki.omoshiroikamo.common.item.backpack.wrapper;

import net.minecraft.item.ItemStack;

public interface IUpgradeWrapperFactory<W extends UpgradeWrapper> {

    W createWrapper(ItemStack stack);
}
