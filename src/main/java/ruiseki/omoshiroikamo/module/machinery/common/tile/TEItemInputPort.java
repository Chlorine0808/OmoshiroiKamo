package ruiseki.omoshiroikamo.module.machinery.common.tile;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;

import com.cleanroommc.modularui.factory.PosGuiData;
import com.cleanroommc.modularui.screen.ModularPanel;
import com.cleanroommc.modularui.screen.UISettings;
import com.cleanroommc.modularui.value.sync.PanelSyncManager;
import com.cleanroommc.modularui.widgets.SlotGroupWidget;
import com.cleanroommc.modularui.widgets.slot.ItemSlot;
import com.cleanroommc.modularui.widgets.slot.ModularSlot;

import ruiseki.omoshiroikamo.api.io.SlotDefinition;
import ruiseki.omoshiroikamo.core.common.block.abstractClass.AbstractStorageTE;

/**
 * Item Input Port TileEntity.
 * Holds a single slot for inputting items into machine processing.
 * Extends AbstractStorageTE to leverage existing inventory management system.
 *
 * TODO: Texture required -
 * assets/omoshiroikamo/textures/blocks/machinery/item_input_port.png
 */
public class TEItemInputPort extends AbstractStorageTE {

    public TEItemInputPort() {
        this(0);
    }

    public TEItemInputPort(int numInputs) {
        super(new SlotDefinition().setItemSlots(numInputs, 0));
    }

    @Override
    public boolean isActive() {
        return false;
    }

    @Override
    public boolean isItemValidForSlot(int slot, ItemStack stack) {
        return slotDefinition.isInputSlot(slot);
    }

    @Override
    public boolean onBlockActivated(World world, EntityPlayer player, ForgeDirection side, float hitX, float hitY,
        float hitZ) {
        openGui(player);
        return true;
    }

    @Override
    public ModularPanel buildUI(PosGuiData data, PanelSyncManager syncManager, UISettings settings) {
        ModularPanel panel = new ModularPanel("input_port_gui");
        int slots = slotDefinition.getItemInputs();

        int cols;
        if (slots <= 9) {
            cols = (int) Math.ceil(Math.sqrt(slots));
        } else if (slots <= 16) {
            cols = 4;
        } else if (slots <= 32) {
            cols = 8;
        } else {
            cols = 9;
        }

        int rows = (int) Math.ceil((double) slots / cols);

        panel.height(rows * 18 + 114);
        SlotGroupWidget widget = new SlotGroupWidget().coverChildren()
            .alignX(0.5f)
            .topRel(0.15f);

        for (int i = 0; i < slots; i++) {
            int x = (i % cols) * 18;
            int y = (i / cols) * 18;
            widget.child(
                new ItemSlot().slot(new ModularSlot(inv, i))
                    .pos(x, y));
        }

        panel.child(widget);

        panel.bindPlayerInventory();
        return panel;
    }
}
