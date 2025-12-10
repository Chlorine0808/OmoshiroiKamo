package ruiseki.omoshiroikamo.client.gui.modularui2.backpack.widget;

import ruiseki.omoshiroikamo.common.init.ModItems;
import ruiseki.omoshiroikamo.common.item.backpack.wrapper.VoidUpgradeWrapper;

public class VoidUpgradeWidget extends BasicExpandedTabWidget<VoidUpgradeWrapper> {

    public VoidUpgradeWidget(int slotIndex, VoidUpgradeWrapper wrapper) {
        super(slotIndex, wrapper, ModItems.FILTER_UPGRADE.newItemStack(), "gui.void_settings", "common_filter", 5, 80);

        this.startingRow.leftRel(0.5f)
            .height(20)
            .childPadding(2);
    }
}
