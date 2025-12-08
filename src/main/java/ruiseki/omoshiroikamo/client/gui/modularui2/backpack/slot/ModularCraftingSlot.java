package ruiseki.omoshiroikamo.client.gui.modularui2.backpack.slot;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemHoe;
import net.minecraft.item.ItemPickaxe;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.stats.AchievementList;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.player.PlayerDestroyItemEvent;

import com.cleanroommc.modularui.utils.Platform;
import com.cleanroommc.modularui.utils.item.IItemHandler;
import com.cleanroommc.modularui.widgets.slot.InventoryCraftingWrapper;
import com.cleanroommc.modularui.widgets.slot.ModularSlot;

import cpw.mods.fml.common.FMLCommonHandler;

public class ModularCraftingSlot extends ModularSlot {

    private InventoryCraftingWrapper craftMatrix;
    private int amountCrafted;

    public ModularCraftingSlot(IItemHandler itemHandler, int index) {
        super(itemHandler, index);
    }

    /**
     * Check if the stack is allowed to be placed in this slot, used for armor slots as well as furnace fuel.
     */
    @Override
    public boolean isItemValid(ItemStack stack) {
        return false;
    }

    /**
     * Decrease the size of the stack in slot (first int arg) by the amount of the second int arg. Returns the new
     * stack.
     */
    @Override
    public ItemStack decrStackSize(int amount) {
        if (this.getHasStack()) {
            this.amountCrafted += Math.min(amount, this.getStack().stackSize);
        }

        return super.decrStackSize(amount);
    }

    /**
     * the itemStack passed in is the output - ie, iron ingots, and pickaxes, not ore and wood. Typically increases an
     * internal count then calls onCrafting(item).
     */
    @Override
    protected void onCrafting(ItemStack stack, int amount) {
        this.amountCrafted += amount;
        this.onCrafting(stack);
    }

    /**
     * the itemStack passed in is the output - ie, iron ingots, and pickaxes, not ore and wood.
     */
    @Override
    protected void onCrafting(ItemStack p_75208_1_) {
        p_75208_1_.onCrafting(getPlayer().worldObj, getPlayer(), this.amountCrafted);
        this.amountCrafted = 0;

        if (p_75208_1_.getItem() == Item.getItemFromBlock(Blocks.crafting_table)) {
            getPlayer().addStat(AchievementList.buildWorkBench, 1);
        }

        if (p_75208_1_.getItem() instanceof ItemPickaxe) {
            getPlayer().addStat(AchievementList.buildPickaxe, 1);
        }

        if (p_75208_1_.getItem() == Item.getItemFromBlock(Blocks.furnace)) {
            getPlayer().addStat(AchievementList.buildFurnace, 1);
        }

        if (p_75208_1_.getItem() instanceof ItemHoe) {
            getPlayer().addStat(AchievementList.buildHoe, 1);
        }

        if (p_75208_1_.getItem() == Items.bread) {
            getPlayer().addStat(AchievementList.makeBread, 1);
        }

        if (p_75208_1_.getItem() == Items.cake) {
            getPlayer().addStat(AchievementList.bakeCake, 1);
        }

        if (p_75208_1_.getItem() instanceof ItemPickaxe
            && ((ItemPickaxe) p_75208_1_.getItem()).func_150913_i() != Item.ToolMaterial.WOOD) {
            getPlayer().addStat(AchievementList.buildBetterPickaxe, 1);
        }

        if (p_75208_1_.getItem() instanceof ItemSword) {
            getPlayer().addStat(AchievementList.buildSword, 1);
        }

        if (p_75208_1_.getItem() == Item.getItemFromBlock(Blocks.enchanting_table)) {
            getPlayer().addStat(AchievementList.enchantments, 1);
        }

        if (p_75208_1_.getItem() == Item.getItemFromBlock(Blocks.bookshelf)) {
            getPlayer().addStat(AchievementList.bookcase, 1);
        }
    }

    @Override
    public void onCraftShiftClick(EntityPlayer player, ItemStack stack) {
        if (!Platform.isStackEmpty(stack)) {
            player.dropPlayerItemWithRandomChoice(stack, false);
        }
    }

    @Override
    public void onPickupFromSlot(EntityPlayer player, ItemStack stack) {
        FMLCommonHandler.instance()
            .firePlayerCraftingEvent(player, stack, craftMatrix);
        onCrafting(stack);

        for (int i = 0; i < this.craftMatrix.getSizeInventory(); ++i) {
            ItemStack itemstack1 = this.craftMatrix.getStackInSlot(i);

            if (itemstack1 != null) {
                itemstack1.stackSize--;
                this.craftMatrix.setInventorySlotContents(i, itemstack1.stackSize <= 0 ? null : itemstack1);

                if (itemstack1 != null && itemstack1.getItem()
                    .hasContainerItem(itemstack1)) {
                    ItemStack cont = itemstack1.getItem()
                        .getContainerItem(itemstack1);

                    if (cont != null && cont.isItemStackDamageable() && cont.getItemDamage() > cont.getMaxDamage()) {
                        MinecraftForge.EVENT_BUS.post(new PlayerDestroyItemEvent(player, cont));
                        continue;
                    }

                    if (!itemstack1.getItem()
                        .doesContainerItemLeaveCraftingGrid(itemstack1)
                        || !player.inventory.addItemStackToInventory(cont)) {

                        if (this.craftMatrix.getStackInSlot(i) == null) {
                            this.craftMatrix.setInventorySlotContents(i, cont);
                        } else {
                            player.dropPlayerItemWithRandomChoice(cont, false);
                        }
                    }
                }
            }
        }
        this.craftMatrix.notifyContainer();

    }

    public void updateResult(ItemStack stack) {
        putStack(stack);
        getSyncHandler().forceSyncItem();
    }

    public void setCraftMatrix(InventoryCraftingWrapper craftMatrix) {
        this.craftMatrix = craftMatrix;
    }
}
