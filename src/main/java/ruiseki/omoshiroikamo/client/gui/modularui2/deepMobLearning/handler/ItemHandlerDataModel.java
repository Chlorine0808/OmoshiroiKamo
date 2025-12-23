package ruiseki.omoshiroikamo.client.gui.modularui2.deepMobLearning.handler;

import net.minecraft.item.ItemStack;

import ruiseki.omoshiroikamo.client.gui.modularui2.base.handler.ItemStackHandlerBase;
import ruiseki.omoshiroikamo.common.item.deepMobLearning.ItemDataModel;

public class ItemHandlerDataModel extends ItemStackHandlerBase {

    public ItemHandlerDataModel() {
        super();
    }

    @Override
    public ItemStack insertItem(int slot, ItemStack stack, boolean simulate) {
        return stack != null && stack.getItem() instanceof ItemDataModel ? super.insertItem(slot, stack, simulate)
            : stack;
    }
}
