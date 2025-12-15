package ruiseki.omoshiroikamo.client.gui.modularui2.backpack.widget;

import com.cleanroommc.modularui.widget.ParentWidget;
import com.cleanroommc.modularui.widgets.slot.ItemSlot;
import ruiseki.omoshiroikamo.common.item.backpack.wrapper.ITankUpgrade;

public class TankSlotWidget extends ParentWidget<TankSlotWidget> {

    public TankSlotWidget(ITankUpgrade upgrade) {
        width(ItemSlot.SIZE * 2);
    }

}
