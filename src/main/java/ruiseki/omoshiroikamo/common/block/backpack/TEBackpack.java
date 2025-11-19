package ruiseki.omoshiroikamo.common.block.backpack;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;

import com.cleanroommc.modularui.api.IGuiHolder;
import com.cleanroommc.modularui.factory.PosGuiData;
import com.cleanroommc.modularui.screen.ModularPanel;
import com.cleanroommc.modularui.screen.UISettings;
import com.cleanroommc.modularui.value.sync.PanelSyncManager;

import ruiseki.omoshiroikamo.common.block.abstractClass.AbstractTE;

public class TEBackpack extends AbstractTE implements ISidedInventory, IGuiHolder<PosGuiData> {

    private final BackpackHandler handler = new BackpackHandler(null, this);

    @Override
    public boolean isActive() {
        return false;
    }

    @Override
    protected boolean processTasks(boolean redstoneCheckPassed) {
        return false;
    }

    @Override
    public int[] getAccessibleSlotsFromSide(int side) {
        return handler.getAccessibleSlotsFromSide(side);
    }

    @Override
    public boolean canInsertItem(int slot, ItemStack itemstack, int side) {
        return handler.canInsertItem(slot, itemstack, side);
    }

    @Override
    public boolean canExtractItem(int slot, ItemStack itemstack, int side) {
        return handler.canExtractItem(slot, itemstack, side);
    }

    @Override
    public int getSizeInventory() {
        return handler.getSizeInventory();
    }

    @Override
    public ItemStack getStackInSlot(int slot) {
        return handler.getStackInSlot(slot);
    }

    @Override
    public ItemStack decrStackSize(int slot, int amount) {
        return handler.decrStackSize(slot, amount);
    }

    @Override
    public ItemStack getStackInSlotOnClosing(int slot) {
        return handler.getStackInSlotOnClosing(slot);
    }

    @Override
    public void setInventorySlotContents(int slot, ItemStack stack) {
        handler.setInventorySlotContents(slot, stack);
    }

    @Override
    public String getInventoryName() {
        return handler.getInventoryName();
    }

    @Override
    public boolean hasCustomInventoryName() {
        return handler.hasCustomInventoryName();
    }

    @Override
    public int getInventoryStackLimit() {
        return handler.getInventoryStackLimit();
    }

    @Override
    public boolean isUseableByPlayer(EntityPlayer player) {
        return handler.isUseableByPlayer(player);
    }

    @Override
    public void openInventory() {
        handler.openInventory();
    }

    @Override
    public void closeInventory() {

        handler.closeInventory();
    }

    @Override
    public boolean isItemValidForSlot(int slot, ItemStack stack) {
        return handler.isItemValidForSlot(slot, stack);
    }

    @Override
    protected void writeCommon(NBTTagCompound root) {
        super.writeCommon(root);
        handler.writeToNBT(root);
    }

    @Override
    protected void readCommon(NBTTagCompound root) {
        super.readCommon(root);
        handler.readFromNBT(root);
    }

    @Override
    public boolean onBlockActivated(World world, EntityPlayer player, ForgeDirection side, float hitX, float hitY,
        float hitZ) {
        openGui(player);
        return true;
    }

    @Override
    public ModularPanel buildUI(PosGuiData data, PanelSyncManager syncManager, UISettings settings) {
        return new BackpackGuiHolder.TileEntityGuiHolder(handler).buildUI(data, syncManager, settings);
    }
}
