package ruiseki.omoshiroikamo.client.gui.modularui2.backpack.container;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

import com.cleanroommc.modularui.api.inventory.ClickType;
import com.cleanroommc.modularui.screen.ModularContainer;
import com.cleanroommc.modularui.screen.NEAAnimationHandler;
import com.cleanroommc.modularui.utils.Platform;
import com.cleanroommc.modularui.utils.item.ItemHandlerHelper;
import com.cleanroommc.modularui.widgets.slot.ModularSlot;

import ruiseki.omoshiroikamo.client.gui.modularui2.backpack.slot.ModularBackpackSlot;
import ruiseki.omoshiroikamo.common.block.backpack.BackpackHandler;

public class BackPackContainer extends ModularContainer {

    private final BackpackHandler handler;
    private final Integer backpackSlotIndex;

    private static final int DROP_TO_WORLD = -999;
    private static final int LEFT_MOUSE = 0;
    private static final int RIGHT_MOUSE = 1;

    public BackPackContainer(BackpackHandler handler, Integer backpackSlotIndex) {
        this.handler = handler;
        this.backpackSlotIndex = backpackSlotIndex;
    }

    @Override
    public ItemStack slotClick(int slotId, int mouseButton, int mode, EntityPlayer player) {
        ClickType clickTypeIn = ClickType.fromNumber(mode);
        ItemStack returnable = null;
        InventoryPlayer inventoryplayer = player.inventory;
        ItemStack heldStack = inventoryplayer.getItemStack();

        if (clickTypeIn == ClickType.QUICK_CRAFT || acc().getDragEvent() != 0) {
            return super.slotClick(slotId, mouseButton, mode, player);
        }

        if ((clickTypeIn == ClickType.PICKUP || clickTypeIn == ClickType.QUICK_MOVE)
            && (mouseButton == LEFT_MOUSE || mouseButton == RIGHT_MOUSE)) {
            if (slotId == DROP_TO_WORLD) {
                return superSlotClick(slotId, mouseButton, mode, player);
            }

            // early return
            if (slotId < 0) {
                return Platform.EMPTY_STACK;
            }

            if (clickTypeIn == ClickType.QUICK_MOVE) {
                Slot fromSlot = getSlot(slotId);

                if (!fromSlot.canTakeStack(player)) {
                    return Platform.EMPTY_STACK;
                }

                if (NEAAnimationHandler.shouldHandleNEA(this)) {
                    returnable = NEAAnimationHandler.injectQuickMove(this, player, slotId, fromSlot);
                } else {
                    returnable = handleQuickMove(player, slotId, fromSlot);
                }
            } else {
                Slot clickedSlot = getSlot(slotId);

                if (clickedSlot != null) {
                    ItemStack slotStack = clickedSlot.getStack();

                    if (slotStack == null) {
                        if (heldStack != null && clickedSlot.isItemValid(heldStack)) {
                            int stackCount = mouseButton == LEFT_MOUSE ? heldStack.stackSize : 1;

                            if (stackCount > clickedSlot.getSlotStackLimit()) {
                                stackCount = clickedSlot.getSlotStackLimit();
                            }

                            clickedSlot.putStack(heldStack.splitStack(stackCount));

                            if (heldStack.stackSize == 0) {
                                inventoryplayer.setItemStack(null);
                            }
                        }
                    } else if (clickedSlot.canTakeStack(player)) {
                        if (heldStack == null) {
                            // int toRemove = mouseButton == LEFT_MOUSE ? slotStack.stackSize : (slotStack.stackSize +
                            // 1) / 2; // Removed
                            int s = Math.min(slotStack.stackSize, slotStack.getMaxStackSize());
                            int toRemove = mouseButton == LEFT_MOUSE ? s : (s + 1) / 2; // Added
                            // inventoryplayer.setItemStack(clickedSlot.decrStackSize(toRemove)); // Removed
                            inventoryplayer.setItemStack(slotStack.splitStack(toRemove)); // Added

                            if (slotStack.stackSize == 0) {
                                // clickedSlot.putStack(null); // Removed
                                slotStack = null; // Added
                            }
                            clickedSlot.putStack(slotStack); // Added

                            clickedSlot.onPickupFromSlot(player, inventoryplayer.getItemStack());
                        } else if (clickedSlot.isItemValid(heldStack)) {
                            if (slotStack.getItem() == heldStack.getItem()
                                && slotStack.getItemDamage() == heldStack.getItemDamage()
                                && ItemStack.areItemStackTagsEqual(slotStack, heldStack)) {
                                int stackCount = mouseButton == 0 ? heldStack.stackSize : 1;

                                if (stackCount > clickedSlot.getSlotStackLimit() - slotStack.stackSize) {
                                    stackCount = clickedSlot.getSlotStackLimit() - slotStack.stackSize;
                                }

                                heldStack.splitStack(stackCount);

                                if (heldStack.stackSize == 0) {
                                    inventoryplayer.setItemStack(null);
                                }

                                slotStack.stackSize += stackCount;
                                clickedSlot.putStack(slotStack); // Added
                            } else if (heldStack.stackSize <= clickedSlot.getSlotStackLimit()) {
                                if (clickedSlot.getStack().stackSize <= 64) {
                                    clickedSlot.putStack(heldStack);
                                    inventoryplayer.setItemStack(slotStack);
                                }
                            }
                        } else if (slotStack.getItem() == heldStack.getItem() && heldStack.getMaxStackSize() > 1
                            && (!slotStack.getHasSubtypes() || slotStack.getItemDamage() == heldStack.getItemDamage())
                            && ItemStack.areItemStackTagsEqual(slotStack, heldStack)) {
                                int stackCount = slotStack.stackSize;

                                if (stackCount > 0 && stackCount + heldStack.stackSize <= heldStack.getMaxStackSize()) {
                                    heldStack.stackSize += stackCount;
                                    slotStack = clickedSlot.decrStackSize(stackCount);

                                    if (slotStack.stackSize == 0) {
                                        clickedSlot.putStack(null);
                                    }

                                    clickedSlot.onPickupFromSlot(player, inventoryplayer.getItemStack());
                                }
                            }
                    }

                    clickedSlot.onSlotChanged();
                }
            }
            detectAndSendChanges();
            return returnable;
        } else if (clickTypeIn == ClickType.CLONE && player.capabilities.isCreativeMode
            && (heldStack == null || heldStack.stackSize <= 0)
            && slotId >= 0) {

                ModularSlot slot = (ModularSlot) getSlot(slotId);
                if (slot != null && slot.getStack() != null) {
                    player.inventory.setItemStack(
                        slot.getStack()
                            .copy());
                }
                return null;

            } else if (clickTypeIn == ClickType.SWAP && mouseButton >= 0
                && mouseButton < 9
                && backpackSlotIndex != null
                && backpackSlotIndex == mouseButton) {
                    return null;
                }

        return super.slotClick(slotId, mouseButton, mode, player);
    }

    @Override
    public ItemStack transferItem(ModularSlot fromSlot, ItemStack fromStack) {
        if ("player_inventory".equals(fromSlot.getSlotGroupName())) {
            List<ModularSlot> memorizedSlots = new ArrayList<>();
            for (Object obj : getShiftClickSlots()) {
                if (!(obj instanceof ModularBackpackSlot s)) continue;
                if (handler.isSlotMemorized(s.getSlotIndex())) {
                    memorizedSlots.add(s);
                }
            }

            for (ModularSlot toSlot : memorizedSlots) {
                if (toSlot.getSlotGroup() != fromSlot.getSlotGroup() && toSlot.func_111238_b()
                    && toSlot.isItemValid(fromStack)) {
                    ItemStack toStack = toSlot.getStack() != null ? toSlot.getStack()
                        .copy() : null;
                    if (toSlot.isPhantom()) {
                        if (toStack == null || (ItemHandlerHelper.canItemStacksStack(fromStack, toStack)
                            && toStack.stackSize < toSlot.getItemStackLimit(toStack))) {
                            toSlot.putStack(fromStack.copy());
                            return fromStack;
                        }
                    } else if (toStack != null && ItemHandlerHelper.canItemStacksStack(fromStack, toStack)) {
                        int j = toStack.stackSize + fromStack.stackSize;
                        int maxSize = toSlot.getItemStackLimit(fromStack);

                        if (j <= maxSize) {
                            fromStack.stackSize = 0;
                            toStack.stackSize = j;
                            toSlot.putStack(toStack);
                        } else if (toStack.stackSize < maxSize) {
                            fromStack.stackSize -= (maxSize - toStack.stackSize);
                            toStack.stackSize = maxSize;
                            toSlot.putStack(toStack);
                        }

                        if (fromStack.stackSize <= 0) return fromStack;
                    }
                }
            }

            for (ModularSlot emptySlot : memorizedSlots) {
                if (emptySlot.getSlotGroup() != fromSlot.getSlotGroup() && emptySlot.func_111238_b()
                    && (emptySlot.getStack() == null || emptySlot.getStack().stackSize <= 0)
                    && emptySlot.isItemValid(fromStack)) {
                    int limit = emptySlot.getItemStackLimit(fromStack);
                    if (fromStack.stackSize > limit) {
                        emptySlot.putStack(fromStack.splitStack(limit));
                    } else {
                        emptySlot.putStack(fromStack.splitStack(fromStack.stackSize));
                    }
                    if (fromStack.stackSize <= 0) return fromStack;
                }
            }
        }

        return super.transferItem(fromSlot, fromStack);
    }
}
