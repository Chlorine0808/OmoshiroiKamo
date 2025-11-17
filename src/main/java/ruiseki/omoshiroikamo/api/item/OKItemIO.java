package ruiseki.omoshiroikamo.api.item;

import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.ISidedInventory;
import net.minecraftforge.common.util.ForgeDirection;

import org.jetbrains.annotations.NotNull;

import com.gtnewhorizon.gtnhlib.item.InventoryIterator;
import com.gtnewhorizon.gtnhlib.item.SimpleItemIO;
import com.gtnewhorizon.gtnhlib.item.StandardInventoryIterator;

public class OKItemIO extends SimpleItemIO {

    private final IInventory inv;
    private final ForgeDirection side;

    public OKItemIO(IInventory inv, ForgeDirection side) {
        this.inv = inv;
        this.side = side;
    }

    public OKItemIO(IInventory inv) {
        this(inv, ForgeDirection.UNKNOWN);
    }

    public static int[] getInventorySlotIndices(IInventory inv, ForgeDirection side) {
        if (inv instanceof ISidedInventory sided) {
            return sided.getAccessibleSlotsFromSide(side.ordinal());
        } else {
            int[] slots = new int[inv.getSizeInventory()];

            for (int i = 0; i < slots.length; i++) {
                slots[i] = i;
            }

            return slots;
        }
    }

    @Override
    protected @NotNull InventoryIterator iterator(int[] allowedSlots) {
        return new StandardInventoryIterator(inv, side, getInventorySlotIndices(inv, side));
    }
}
