package ruiseki.omoshiroikamo.client.gui.modularui2.backpack.container;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

import org.jetbrains.annotations.Nullable;

import com.cleanroommc.modularui.api.inventory.ClickType;
import com.cleanroommc.modularui.screen.ModularContainer;
import com.cleanroommc.modularui.screen.NEAAnimationHandler;
import com.cleanroommc.modularui.utils.Platform;
import com.cleanroommc.modularui.utils.item.ItemHandlerHelper;
import com.cleanroommc.modularui.utils.item.SlotItemHandler;
import com.cleanroommc.modularui.widgets.slot.ModularSlot;

import ruiseki.omoshiroikamo.client.gui.modularui2.backpack.slot.ModularBackpackSlot;
import ruiseki.omoshiroikamo.common.block.backpack.BackpackHandler;
import ruiseki.omoshiroikamo.common.item.backpack.wrapper.CraftingUpgradeWrapper;

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

                            int lim = stackLimit(clickedSlot, heldStack);
                            if (stackCount > lim) {
                                stackCount = lim;
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

                                int lim = stackLimit(clickedSlot, heldStack);
                                if (stackCount > lim - slotStack.stackSize) {
                                    stackCount = lim - slotStack.stackSize;
                                }

                                heldStack.splitStack(stackCount);

                                if (heldStack.stackSize == 0) {
                                    inventoryplayer.setItemStack(null);
                                }

                                slotStack.stackSize += stackCount;
                                clickedSlot.putStack(slotStack); // Added
                            } else if (heldStack.stackSize <= stackLimit(clickedSlot, heldStack)) {
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
        if (fromStack == null || fromStack.stackSize <= 0) return fromStack;

        String group = fromSlot.getSlotGroupName();

        if (group != null && group.startsWith("crafting_slots_")) {
            int craftingSlotIndex = -1;
            try {
                craftingSlotIndex = Integer.parseInt(group.substring("crafting_slots_".length()));
            } catch (NumberFormatException ignored) {}

            if (craftingSlotIndex >= 0) {
                Map<Integer, CraftingUpgradeWrapper> map = handler
                    .gatherCapabilityUpgrades(CraftingUpgradeWrapper.class);

                CraftingUpgradeWrapper wrapper = map.get(craftingSlotIndex);

                if (wrapper != null) {
                    String targetGroup = switch (wrapper.getCraftingDes()) {
                        case BACKPACK -> "backpack_inventory";
                        case INVENTORY -> "player_inventory";
                    };

                    for (ModularSlot toSlot : getShiftClickSlots()) {
                        if (!targetGroup.equals(toSlot.getSlotGroupName())) continue;
                        fromStack = transferToSlot(fromStack, toSlot);
                        if (fromStack == null || fromStack.stackSize <= 0) return null;
                    }
                    return fromStack;
                }
            }
        }

        if ("player_inventory".equals(group)) {
            List<ModularSlot> memorizedSlots = getShiftClickSlots().stream()
                .filter(s -> s instanceof ModularBackpackSlot sb && handler.isSlotMemorized(sb.getSlotIndex()))
                .collect(Collectors.toList());

            for (ModularSlot toSlot : memorizedSlots) {
                fromStack = transferToSlot(fromStack, toSlot);
                if (fromStack == null || fromStack.stackSize <= 0) return null;
            }
        }

        return super.transferItem(fromSlot, fromStack);
    }

    protected @Nullable ItemStack transferToSlot(ItemStack stack, ModularSlot toSlot) {
        if (stack == null || stack.stackSize <= 0) return null;
        if (toSlot == null || !toSlot.func_111238_b() || !toSlot.isItemValid(stack)) return stack;

        ItemStack existing = toSlot.getStack();
        int limit = toSlot.getItemStackLimit(stack);

        if (existing == null || existing.stackSize <= 0) {
            if (stack.stackSize > limit) {
                toSlot.putStack(stack.splitStack(limit));
            } else {
                toSlot.putStack(stack.splitStack(stack.stackSize));
            }
            return stack.stackSize > 0 ? stack : null;
        }

        if (ItemHandlerHelper.canItemStacksStack(stack, existing)) {
            int total = existing.stackSize + stack.stackSize;
            if (total <= limit) {
                existing.stackSize = total;
                toSlot.onSlotChanged();
                return null;
            } else {
                existing.stackSize = limit;
                toSlot.onSlotChanged();
                stack.stackSize = total - limit;
                return stack;
            }
        }

        return stack;
    }

    public static int stackLimit(Slot slot, ItemStack stack) {
        if (stack == null) return 0;
        if (slot instanceof SlotItemHandler slotItemHandler) {
            // this is triggered for modular slots
            return slotItemHandler.getItemStackLimit(stack);
        }
        // anything else is just extra safety, but will likely never be triggered
        if (slot instanceof com.gtnewhorizons.modularui.api.forge.SlotItemHandler slotItemHandler) {
            return slotItemHandler.getItemStackLimit(stack);
        }
        return Math.min(slot.getSlotStackLimit(), stack.getMaxStackSize());
    }

}
