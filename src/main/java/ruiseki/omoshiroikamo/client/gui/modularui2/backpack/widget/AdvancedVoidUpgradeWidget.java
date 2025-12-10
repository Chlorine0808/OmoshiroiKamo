package ruiseki.omoshiroikamo.client.gui.modularui2.backpack.widget;

import net.minecraft.item.ItemStack;

import ruiseki.omoshiroikamo.common.init.ModItems;
import ruiseki.omoshiroikamo.common.item.backpack.wrapper.AdvancedVoidUpgradeWrapper;

public class AdvancedVoidUpgradeWidget extends AdvancedExpandedTabWidget<AdvancedVoidUpgradeWrapper> {

    public AdvancedVoidUpgradeWidget(int slotIndex, AdvancedVoidUpgradeWrapper wrapper) {
        super(
            slotIndex,
            wrapper,
            new ItemStack(ModItems.ADVANCED_FILTER_UPGRADE.get()),
            "gui.advanced_void_settings",
            "adv_common_filter",
            6,
            100);

        this.startingRow.leftRel(0.5f)
            .height(20)
            .childPadding(2);
    }

}
