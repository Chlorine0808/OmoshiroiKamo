package ruiseki.omoshiroikamo.client.gui.modularui2.backpack.widget;

import java.util.Arrays;
import java.util.List;

import com.cleanroommc.modularui.api.drawable.IKey;
import com.cleanroommc.modularui.widget.ParentWidget;
import com.cleanroommc.modularui.widgets.SlotGroupWidget;
import com.cleanroommc.modularui.widgets.layout.Column;
import com.cleanroommc.modularui.widgets.slot.ItemSlot;

import ruiseki.omoshiroikamo.client.gui.modularui2.MGuiTextures;
import ruiseki.omoshiroikamo.client.gui.modularui2.backpack.syncHandler.UpgradeSlotSH;
import ruiseki.omoshiroikamo.common.init.ModItems;
import ruiseki.omoshiroikamo.common.item.backpack.wrapper.CraftingUpgradeWrapper;
import ruiseki.omoshiroikamo.common.item.backpack.wrapper.ICraftingUpgrade;

public class CraftingUpgradeWidget extends ExpandedUpgradeTabWidget<CraftingUpgradeWrapper> {

    private static final List<CyclicVariantButtonWidget.Variant> INTO_VARIANTS = Arrays.asList(
        new CyclicVariantButtonWidget.Variant(IKey.lang("gui.into_backpack"), MGuiTextures.INTO_BACKPACK),
        new CyclicVariantButtonWidget.Variant(IKey.lang("gui.into_inventory"), MGuiTextures.INTO_INVENTORY));

    private final CraftingUpgradeWrapper wrapper;
    private final CyclicVariantButtonWidget craftingDesButton;

    public CraftingUpgradeWidget(int slotIndex, CraftingUpgradeWrapper wrapper) {
        super(slotIndex, 5, ModItems.CRAFTING_UPGRADE.newItemStack(), "gui.crafting_settings");
        this.wrapper = wrapper;

        this.syncHandler("upgrades", slotIndex);

        this.craftingDesButton = new CyclicVariantButtonWidget(
            INTO_VARIANTS,
            wrapper.getCraftingDes()
                .ordinal(),
            index -> {
                wrapper.setCraftingDes(ICraftingUpgrade.CraftingDestination.values()[index]);
                if (getSlotSyncHandler() != null) {
                    getSlotSyncHandler().syncToServer(
                        UpgradeSlotSH.UPDATE_CRAFTING_DES,
                        buf -> {
                            buf.writeInt(
                                wrapper.getCraftingDes()
                                    .ordinal());
                        });
                }
            }).size(20, 20);

        SlotGroupWidget craftingGroupsWidget = new SlotGroupWidget().name("crafting_matrix")
            .width(64)
            .coverChildren()
            .top(26);

        ItemSlot[] craftingMatrix = new ItemSlot[9];
        for (int i = 0; i < 9; i++) {
            ItemSlot itemSlot = new ItemSlot().syncHandler("crafting_slot_" + slotIndex, i)
                .pos(i % 3 * 18, i / 3 * 18)
                .name("crafting_slot_" + i);

            craftingGroupsWidget.child(itemSlot);
            craftingMatrix[i] = itemSlot;
        }

        ItemSlot craftingResult = new ItemSlot().syncHandler("crafting_result_" + slotIndex, 9)
            .pos(18, 18 * 3 + 9)
            .name("crafting_result_" + slotIndex);
        craftingGroupsWidget.child(craftingResult);

        ParentWidget<?> widget = new ParentWidget<>().name("crafting_widget")
            .width(64)
            .coverChildrenHeight();

        widget.child(craftingDesButton)
            .child(craftingGroupsWidget);

        Column column = (Column) new Column().pos(8, 28)
            .width(64)
            .childPadding(2)
            .child(widget);

        child(column);
    }

    @Override
    protected CraftingUpgradeWrapper getWrapper() {
        return wrapper;
    }

}
