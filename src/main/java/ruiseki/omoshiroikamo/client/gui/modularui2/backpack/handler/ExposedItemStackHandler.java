package ruiseki.omoshiroikamo.client.gui.modularui2.backpack.handler;

import net.minecraft.nbt.NBTTagCompound;

import ruiseki.omoshiroikamo.client.gui.modularui2.handler.UpgradeItemStackHandler;
import ruiseki.omoshiroikamo.common.item.backpack.wrapper.IBasicFilterable;
import ruiseki.omoshiroikamo.common.item.backpack.wrapper.UpgradeWrapper;
import ruiseki.omoshiroikamo.common.util.item.ItemNBTUtils;

public class ExposedItemStackHandler extends UpgradeItemStackHandler {

    private final UpgradeWrapper wrapper;

    public ExposedItemStackHandler(int size, UpgradeWrapper wrapper) {
        super(size);
        this.wrapper = wrapper;
    }

    @Override
    protected void onContentsChanged(int slot) {
        NBTTagCompound tag = ItemNBTUtils.getNBT(wrapper.getUpgrade());
        tag.setTag(IBasicFilterable.FILTER_ITEMS_TAG, this.serializeNBT());
    }
}
