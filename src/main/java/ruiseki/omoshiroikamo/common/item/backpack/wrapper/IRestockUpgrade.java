package ruiseki.omoshiroikamo.common.item.backpack.wrapper;

import net.minecraft.item.ItemStack;

public interface IRestockUpgrade {

    boolean canRestock(ItemStack stack);
}
