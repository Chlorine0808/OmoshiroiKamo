package ruiseki.omoshiroikamo.common.block.backpack;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;

import com.cleanroommc.modularui.api.IGuiHolder;
import com.cleanroommc.modularui.factory.PlayerInventoryGuiData;
import com.cleanroommc.modularui.factory.PosGuiData;
import com.cleanroommc.modularui.factory.inventory.InventoryType;
import com.cleanroommc.modularui.factory.inventory.InventoryTypes;
import com.cleanroommc.modularui.screen.ModularPanel;
import com.cleanroommc.modularui.screen.UISettings;
import com.cleanroommc.modularui.value.sync.PanelSyncManager;

import ruiseki.omoshiroikamo.common.util.item.BaublesUtils;

public abstract class BackpackGuiHolder {

    protected final BackpackHandler handler;

    public BackpackGuiHolder(BackpackHandler backpackWrapper) {
        this.handler = backpackWrapper;
    }

    protected BackpackPanel createPanel(PanelSyncManager syncManager, UISettings settings, EntityPlayer player,
        TileEntity tileEntity, InventoryType inventoryType, Integer slotIndex) {
        return BackpackPanel.defaultPanel(syncManager, settings, player, tileEntity, handler, 0, 0);
    }

    public static final class TileEntityGuiHolder extends BackpackGuiHolder implements IGuiHolder<PosGuiData> {

        public TileEntityGuiHolder(BackpackHandler handler) {
            super(handler);
        }

        @Override
        public ModularPanel buildUI(PosGuiData data, PanelSyncManager syncManager, UISettings settings) {
            TileEntity tileEntity = data.getTileEntity();
            BackpackPanel panel = createPanel(syncManager, settings, data.getPlayer(), tileEntity, null, null);

            return panel;
        }
    }

    public static final class ItemStackGuiHolder extends BackpackGuiHolder
        implements IGuiHolder<PlayerInventoryGuiData> {

        public ItemStackGuiHolder(BackpackHandler handler) {
            super(handler);
        }

        @Override
        public ModularPanel buildUI(PlayerInventoryGuiData data, PanelSyncManager syncManager, UISettings settings) {
            BackpackPanel panel = createPanel(
                syncManager,
                settings,
                data.getPlayer(),
                null,
                data.getInventoryType(),
                data.getSlotIndex());

            syncManager.addCloseListener(player1 -> {
                if (data.getInventoryType() == InventoryTypes.PLAYER) {
                    player1.inventory.mainInventory[data.getSlotIndex()] = handler.getBackpack();
                } else if (data.getInventoryType() == InventoryTypes.BAUBLES) {
                    BaublesUtils.instance()
                        .getBaubles(player1)
                        .setInventorySlotContents(data.getSlotIndex(), handler.getBackpack());
                }

            });
            return panel;
        }
    }
}
