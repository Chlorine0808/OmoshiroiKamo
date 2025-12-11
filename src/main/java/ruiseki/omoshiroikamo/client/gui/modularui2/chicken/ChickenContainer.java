package ruiseki.omoshiroikamo.client.gui.modularui2.chicken;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.item.ItemStack;

import com.cleanroommc.modularui.screen.ModularContainer;

import ruiseki.omoshiroikamo.api.entity.chicken.DataChicken;
import ruiseki.omoshiroikamo.config.backport.ChickenConfig;

/**
 * Container used by Roost/Breeder style GUIs to ensure chicken stacks never
 * exceed the
 * configurable cap when players manipulate their inventory while the GUI is
 * open.
 */
public class ChickenContainer extends ModularContainer {

    @Override
    public ItemStack slotClick(int slotId, int mouseButton, int mode, EntityPlayer player) {
        ItemStack result = super.slotClick(slotId, mouseButton, mode, player);
        enforceChickenStackLimit(player);
        return result;
    }

    private void enforceChickenStackLimit(EntityPlayer player) {
        if (player == null || player.worldObj == null || player.worldObj.isRemote) {
            return;
        }

        InventoryPlayer inventory = player.inventory;
        if (inventory == null) {
            return;
        }

        int limit = ChickenConfig.chickenStackLimit;

        normalizeCarriedStack(player, inventory, limit);

        for (int i = 0; i < inventory.getSizeInventory(); i++) {
            ItemStack stack = inventory.getStackInSlot(i);
            if (!DataChicken.isChicken(stack) || stack.stackSize <= limit) {
                continue;
            }

            int overflow = stack.stackSize - limit;
            stack.stackSize = limit;
            inventory.setInventorySlotContents(i, stack);
            overflow = redistributeOverflow(player, inventory, stack, overflow, limit, i);

            if (overflow > 0) {
                dropOverflow(player, stack, overflow, limit);
            }
        }

        inventory.markDirty();
    }

    private void normalizeCarriedStack(EntityPlayer player, InventoryPlayer inventory, int limit) {
        ItemStack carried = inventory.getItemStack();
        if (!DataChicken.isChicken(carried) || carried.stackSize <= limit) {
            return;
        }

        int overflow = carried.stackSize - limit;
        carried.stackSize = limit;
        inventory.setItemStack(carried);
        overflow = redistributeOverflow(player, inventory, carried, overflow, limit, -1);
        if (overflow > 0) {
            dropOverflow(player, carried, overflow, limit);
        }
    }

    private int redistributeOverflow(EntityPlayer player, InventoryPlayer inventory, ItemStack template, int overflow,
            int limit, int sourceSlot) {
        if (overflow <= 0) {
            return 0;
        }

        int remaining = overflow;

        if (sourceSlot != -1) {
            remaining = fillCarriedStack(inventory, template, remaining, limit);
        }

        remaining = fillExistingSlots(inventory, template, remaining, limit, sourceSlot);
        remaining = fillEmptySlots(inventory, template, remaining, limit, sourceSlot);

        return remaining;
    }

    private int fillCarriedStack(InventoryPlayer inventory, ItemStack template, int overflow, int limit) {
        if (overflow <= 0) {
            return overflow;
        }

        ItemStack carried = inventory.getItemStack();

        if (carried == null) {
            int move = Math.min(limit, overflow);
            inventory.setItemStack(copyWithAmount(template, move));
            return overflow - move;
        }

        if (!stacksMatch(carried, template) || carried.stackSize >= limit) {
            return overflow;
        }

        int move = Math.min(limit - carried.stackSize, overflow);
        carried.stackSize += move;
        inventory.setItemStack(carried);
        return overflow - move;
    }

    private int fillExistingSlots(InventoryPlayer inventory, ItemStack template, int overflow, int limit,
            int sourceSlot) {
        if (overflow <= 0) {
            return overflow;
        }

        int remaining = overflow;
        for (int i = 0; i < inventory.getSizeInventory() && remaining > 0; i++) {
            if (i == sourceSlot) {
                continue;
            }
            ItemStack slotStack = inventory.getStackInSlot(i);
            if (!stacksMatch(slotStack, template) || slotStack.stackSize >= limit) {
                continue;
            }
            int move = Math.min(limit - slotStack.stackSize, remaining);
            slotStack.stackSize += move;
            inventory.setInventorySlotContents(i, slotStack);
            remaining -= move;
        }

        return remaining;
    }

    private int fillEmptySlots(InventoryPlayer inventory, ItemStack template, int overflow, int limit, int sourceSlot) {
        if (overflow <= 0) {
            return overflow;
        }

        int remaining = overflow;
        for (int i = 0; i < inventory.getSizeInventory() && remaining > 0; i++) {
            if (i == sourceSlot) {
                continue;
            }
            ItemStack slotStack = inventory.getStackInSlot(i);
            if (slotStack != null) {
                continue;
            }
            int move = Math.min(limit, remaining);
            inventory.setInventorySlotContents(i, copyWithAmount(template, move));
            remaining -= move;
        }

        return remaining;
    }

    private void dropOverflow(EntityPlayer player, ItemStack template, int overflow, int limit) {
        if (player == null || player.worldObj == null) {
            return;
        }

        int remaining = overflow;
        while (remaining > 0) {
            int move = Math.min(limit, remaining);
            ItemStack drop = copyWithAmount(template, move);
            if (!player.worldObj.isRemote) {
                player.dropPlayerItemWithRandomChoice(drop, false);
            }
            remaining -= move;
        }
    }

    private boolean stacksMatch(ItemStack stack, ItemStack template) {
        if (!DataChicken.isChicken(stack) || !DataChicken.isChicken(template)) {
            return false;
        }
        if (stack.getItem() != template.getItem()) {
            return false;
        }
        if (stack.getItemDamage() != template.getItemDamage()) {
            return false;
        }
        return ItemStack.areItemStackTagsEqual(stack, template);
    }

    private ItemStack copyWithAmount(ItemStack source, int amount) {
        ItemStack copy = source.copy();
        copy.stackSize = amount;
        return copy;
    }
}
