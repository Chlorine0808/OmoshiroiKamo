package ruiseki.omoshiroikamo.common.block.backpack;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;

import com.cleanroommc.modularui.screen.ModularPanel;
import com.cleanroommc.modularui.screen.UISettings;
import com.cleanroommc.modularui.utils.Alignment;
import com.cleanroommc.modularui.utils.item.InvWrapper;
import com.cleanroommc.modularui.value.sync.PanelSyncManager;
import com.cleanroommc.modularui.widgets.SlotGroupWidget;
import com.cleanroommc.modularui.widgets.slot.ItemSlot;

import ruiseki.omoshiroikamo.client.gui.modularui2.MGuiBuilder;

public class BackpackPanel extends ModularPanel {

    public final EntityPlayer player;
    public final TileEntity tileEntity;
    public final PanelSyncManager syncManager;
    public final UISettings settings;
    public final BackpackHandler handler;

    public BackpackPanel(EntityPlayer player, TileEntity tileEntity, PanelSyncManager syncManager, UISettings settings,
        BackpackHandler handler) {
        super("test");
        this.player = player;
        this.tileEntity = tileEntity;
        this.syncManager = syncManager;
        this.settings = settings;
        this.handler = handler;

        this.child(
            SlotGroupWidget.builder()
                .row("IIIIIIIII")
                .row("IIIIIIIII")
                .row("IIIIIIIII")
                .key('I', index -> new ItemSlot().slot(new BackpackSlot(handler.getBackpackHandler(), index, handler)))
                .build()
                .align(Alignment.TopCenter));
    }

    public static BackpackPanel defaultPanel(PanelSyncManager syncManager, UISettings settings, EntityPlayer player,
        TileEntity tileEntity, BackpackHandler handler, int width, int height) {
        BackpackPanel panel = new BackpackPanel(player, tileEntity, syncManager, settings, handler);
        // panel.size(width, height);
        InvWrapper playerInventory = new InvWrapper(player.inventory) {

            @Override
            public ItemStack extractItem(int slot, int amount, boolean simulate) {
                ItemStack stack = getStackInSlot(slot);
                if (slot == player.inventory.currentItem && stack != null
                    && stack.getItem() instanceof BlockBackpack.ItemBackpack) {
                    return null;
                }

                return super.extractItem(slot, amount, simulate);
            }
        };
        syncManager.bindPlayerInventory(player);
        panel.child(MGuiBuilder.playerInventory(playerInventory));
        return panel;
    }

}
