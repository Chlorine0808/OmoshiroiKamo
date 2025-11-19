package ruiseki.omoshiroikamo.common.block.backpack;

import java.util.List;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

import com.cleanroommc.modularui.utils.item.ItemStackHandler;

import lombok.Getter;
import ruiseki.omoshiroikamo.common.item.backpack.ItemStackUpgrade;
import ruiseki.omoshiroikamo.common.util.item.ItemNBTUtils;
import ruiseki.omoshiroikamo.common.util.item.ItemUtils;

public class BackpackHandler implements ISidedInventory {

    @Getter
    private final ItemStack backpack;
    @Getter
    private final BackpackItemStackHandler backpackHandler;
    @Getter
    private final ItemStackHandler upgradeHandler;
    @Getter
    private static final String BACKPACK_INV = "BackpackInv";
    @Getter
    private static final String UPGRADE_INV = "UpgradeInv";

    private final int[] allSlots;
    private final TEBackpack inventory;

    public BackpackHandler(ItemStack backpack, TEBackpack inventory) {
        this.backpack = backpack;
        this.inventory = inventory;

        this.backpackHandler = new BackpackItemStackHandler(27, this) {

            @Override
            protected void onContentsChanged(int slot) {
                super.onContentsChanged(slot);
                writeToItem();
            }
        };

        this.upgradeHandler = new ItemStackHandler(7) {

            @Override
            protected void onContentsChanged(int slot) {
                super.onContentsChanged(slot);
                writeToItem();
            }
        };

        readFromItem();

        allSlots = new int[backpackHandler.getSlots()];
        for (int i = 0; i < allSlots.length; i++) {
            allSlots[i] = i;
        }
    }

    @Override
    public int getSizeInventory() {
        return backpackHandler.getSlots();
    }

    @Override
    public ItemStack getStackInSlot(int slot) {
        return backpackHandler.getStackInSlot(slot);
    }

    @Override
    public ItemStack decrStackSize(int fromSlot, int amount) {
        ItemStack fromStack = backpackHandler.getStackInSlot(fromSlot);
        if (fromStack == null) {
            return null;
        }
        if (fromStack.stackSize <= amount) {
            backpackHandler.setStackInSlot(fromSlot, null);
            return fromStack;
        }
        ItemStack result = fromStack.splitStack(amount);
        backpackHandler.setStackInSlot(fromSlot, fromStack.stackSize > 0 ? fromStack : null);
        return result;
    }

    @Override
    public ItemStack getStackInSlotOnClosing(int i) {
        return null;
    }

    @Override
    public void setInventorySlotContents(int slot, ItemStack contents) {
        if (contents == null) {
            backpackHandler.setStackInSlot(slot, null);
        } else {
            backpackHandler.setStackInSlot(slot, contents.copy());
        }

        ItemStack stack = backpackHandler.getStackInSlot(slot);
        if (stack != null && stack.stackSize > getInventoryStackLimit()) {
            stack.stackSize = getInventoryStackLimit();
            backpackHandler.setStackInSlot(slot, stack);
        }
    }

    @Override
    public String getInventoryName() {
        return "Backpack";
    }

    @Override
    public boolean hasCustomInventoryName() {
        return false;
    }

    @Override
    public int getInventoryStackLimit() {
        return 64 * getTotalStackMultiplier();
    }

    @Override
    public void markDirty() {
        inventory.markDirty();
    }

    @Override
    public boolean isUseableByPlayer(EntityPlayer player) {
        return false;
    }

    @Override
    public void openInventory() {

    }

    @Override
    public void closeInventory() {

    }

    @Override
    public boolean isItemValidForSlot(int slot, ItemStack stack) {
        return true;
    }

    @Override
    public int[] getAccessibleSlotsFromSide(int side) {
        return allSlots;
    }

    @Override
    public boolean canInsertItem(int slot, ItemStack itemstack, int side) {
        ItemStack existing = backpackHandler.getStackInSlot(slot);
        if (existing != null) {
            return ItemUtils.areStackMergable(existing, itemstack);
        }
        return isItemValidForSlot(slot, itemstack);
    }

    @Override
    public boolean canExtractItem(int slot, ItemStack itemstack, int side) {
        ItemStack existing = backpackHandler.getStackInSlot(slot);
        if (existing == null || existing.stackSize < itemstack.stackSize) {
            return false;
        }
        return itemstack.getItem() == existing.getItem();
    }

    // ---------- UPGRADE ----------
    public int getTotalStackMultiplier() {
        List<ItemStack> stacks = upgradeHandler.getStacks();
        int result = 0;

        if (stacks.isEmpty()) {
            return 1;
        }

        for (ItemStack stack : stacks) {
            if (stack == null) {
                continue;
            }
            if (stack.getItem() instanceof ItemStackUpgrade upgrade) {
                result += upgrade.multiplier(stack);
            }
        }
        return result == 0 ? 1 : result;
    }

    public boolean canAddStackUpgrade(int newMultiplier) {
        int currentMultiplier = getTotalStackMultiplier() * 64;

        try {
            Math.multiplyExact(currentMultiplier, newMultiplier);
            return true;
        } catch (ArithmeticException e) {
            return false;
        }
    }

    public boolean canRemoveStackUpgrade(int originalMultiplier) {
        return canReplaceStackUpgrade(originalMultiplier, 1);
    }

    public boolean canReplaceStackUpgrade(int oldMultiplier, int newMultiplier) {
        int newStackMultiplier = getTotalStackMultiplier() / oldMultiplier * newMultiplier;

        for (ItemStack stack : backpackHandler.getStacks()) {
            if (stack == null) {
                continue;
            }

            if (stack.stackSize > stack.getMaxStackSize() * newStackMultiplier) {
                return false;
            }
        }

        return true;
    }

    // ---------- ITEM STACK ----------
    public void writeToItem() {
        if (backpack == null) {
            return;
        }
        NBTTagCompound tag = ItemNBTUtils.getNBT(backpack);
        writeToNBT(tag);
        backpack.setTagCompound(tag);
    }

    public void readFromItem() {
        if (backpack == null) {
            return;
        }
        NBTTagCompound tag = ItemNBTUtils.getNBT(backpack);
        readFromNBT(tag);
    }

    // ---------- TILE ENTITY ----------
    public void writeToNBT(NBTTagCompound tag) {
        tag.setTag(BACKPACK_INV, backpackHandler.serializeNBT());
        tag.setTag(UPGRADE_INV, upgradeHandler.serializeNBT());
    }

    public void readFromNBT(NBTTagCompound tag) {
        if (tag.hasKey(BACKPACK_INV)) {
            backpackHandler.deserializeNBT(tag.getCompoundTag(BACKPACK_INV));
        }
        if (tag.hasKey(UPGRADE_INV)) {
            upgradeHandler.deserializeNBT(tag.getCompoundTag(UPGRADE_INV));
        }
    }
}
