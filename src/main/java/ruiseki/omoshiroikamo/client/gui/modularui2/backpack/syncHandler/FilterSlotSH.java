package ruiseki.omoshiroikamo.client.gui.modularui2.backpack.syncHandler;

import com.cleanroommc.modularui.network.NetworkUtils;
import com.cleanroommc.modularui.value.sync.ItemSlotSH;

import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketBuffer;
import ruiseki.omoshiroikamo.client.gui.modularui2.backpack.slot.ModularFilterSlot;

import java.io.IOException;

public class FilterSlotSH extends ItemSlotSH {

    public static final int SYNC_ITEM_SIMPLE = 102;

    public FilterSlotSH(ModularFilterSlot slot) {
        super(slot);
    }

    @Override
    public void readOnServer(int id, PacketBuffer buf) throws IOException {
        super.readOnServer(id, buf);
        if (id == SYNC_ITEM_SIMPLE) {
            ItemStack itemStack = NetworkUtils.readItemStack(buf);
            this.getSlot().putStack(itemStack);
        }
    }

    public void updateFromClient(ItemStack stack) {
        syncToServer(SYNC_ITEM_SIMPLE, buf -> {
            NetworkUtils.writeItemStack(buf, stack);
        });
    }
}
