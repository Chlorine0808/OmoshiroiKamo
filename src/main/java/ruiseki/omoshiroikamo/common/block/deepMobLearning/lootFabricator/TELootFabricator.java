package ruiseki.omoshiroikamo.common.block.deepMobLearning.lootFabricator;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;

import org.jetbrains.annotations.Nullable;

import com.cleanroommc.modularui.factory.PosGuiData;
import com.cleanroommc.modularui.screen.ModularPanel;
import com.cleanroommc.modularui.screen.UISettings;
import com.cleanroommc.modularui.value.sync.PanelSyncManager;

import lombok.Getter;
import ruiseki.omoshiroikamo.api.crafting.CraftingState;
import ruiseki.omoshiroikamo.api.energy.IEnergySink;
import ruiseki.omoshiroikamo.api.entity.model.ModelRegistryItem;
import ruiseki.omoshiroikamo.api.item.ItemNBTUtils;
import ruiseki.omoshiroikamo.client.gui.modularui2.base.handler.ItemStackHandlerBase;
import ruiseki.omoshiroikamo.client.gui.modularui2.deepMobLearning.handler.ItemHandlerPristineMatter;
import ruiseki.omoshiroikamo.common.block.abstractClass.AbstractMachine;
import ruiseki.omoshiroikamo.common.item.deepMobLearning.ItemPristineMatter;
import ruiseki.omoshiroikamo.common.util.Logger;
import ruiseki.omoshiroikamo.config.backport.DeepMobLearningConfig;

public class TELootFabricator extends AbstractMachine implements IEnergySink {

    public final ItemHandlerPristineMatter input = new ItemHandlerPristineMatter() {

        @Override
        protected void onMetadataChanged() {
            if (this.pristineMatterData != null && !isValidOutputItem()) outputItem = null;
            resetCrafting();
        }
    };

    public final ItemStackHandlerBase output = new ItemStackHandlerBase(16);

    @Getter
    public ItemStack outputItem = null;

    public TELootFabricator() {
        super(getEnergyCapacity(), getEnergyPerTick());
    }

    private static int getEnergyCapacity() {
        int energyCost = DeepMobLearningConfig.lootFabricatorRfCost;
        long energyCapacity = 1_000_000L * (energyCost / 100);
        return (int) Math.min(energyCapacity, Integer.MAX_VALUE);
    }

    private static int getEnergyPerTick() {
        int energyCost = DeepMobLearningConfig.lootFabricatorRfCost;
        long energyPerTick = 100L * energyCost;
        return (int) Math.min(energyPerTick, Integer.MAX_VALUE);
    }

    @Override
    public boolean processTasks(boolean redstoneCheckPassed) {
        if (input.getStackInSlot(0) != null && !isValidOutputItem()) {
            outputItem = null;
            resetCrafting();
        }

        return super.processTasks(redstoneCheckPassed);
    }

    @Override
    public boolean canStartCrafting() {
        return super.canStartCrafting() && hasPristineMatter() && hasRoomForOutput() && isValidOutputItem();
    }

    @Override
    protected void finishCrafting() {
        resetCrafting();

        if (outputItem == null) {
            Logger.warn("Loot Fabricator at {} crafted without selected output!", getPos().toString());
            return;
        }

        if (!isValidOutputItem()) {
            Logger.warn("Loot Fabricator at {} crafted with invalid output selection!", getPos().toString());
            outputItem = null;
            return;
        }

        output.addItemToAvailableSlots(outputItem.copy());
        input.voidItem();
    }

    @Override
    protected int getCraftingDuration() {
        return DeepMobLearningConfig.lootFabricatorPrecessingTime;
    }

    @Override
    public int getCraftingEnergyCost() {
        return DeepMobLearningConfig.lootFabricatorRfCost;
    }

    @Override
    protected CraftingState updateCraftingState() {
        if (!crafting && !hasPristineMatter()) {
            return CraftingState.IDLE;
        } else if (!canContinueCrafting() || (!this.isCrafting() && !canStartCrafting())) {
            return CraftingState.ERROR;
        }

        return CraftingState.RUNNING;
    }

    private boolean isValidOutputItem() {
        ModelRegistryItem pristineMatterMetadata = getPristineMatterData();
        return outputItem != null && pristineMatterMetadata != null && pristineMatterMetadata.hasLootItem(outputItem);
    }

    @Nullable
    public ModelRegistryItem getPristineMatterData() {
        return input.getPristineMatterData();
    }

    public void setOutputItem(ItemStack outputItem) {
        this.outputItem = outputItem;

        if (!isValidOutputItem()) {
            this.outputItem = null;
        }
    }

    public boolean hasPristineMatter() {
        ItemStack stack = input.getStackInSlot(0);
        return stack != null && stack.stackSize > 0 && stack.getItem() instanceof ItemPristineMatter;
    }

    public boolean hasRoomForOutput() {
        return output.hasRoomForItem(outputItem);
    }

    @Override
    public boolean isActive() {
        return isCrafting();
    }

    @Override
    public int receiveEnergy(ForgeDirection side, int amount, boolean simulate) {
        return energyStorage.receiveEnergy(amount, simulate);
    }

    @Override
    public void writeCommon(NBTTagCompound root) {
        super.writeCommon(root);
        NBTTagCompound inventory = new NBTTagCompound();
        inventory.setTag("input", input.serializeNBT());
        inventory.setTag("output", output.serializeNBT());
        root.setTag("inventory", inventory);

        if (outputItem != null) {
            NBTTagCompound crafting = root.getCompoundTag("crafting");
            crafting.setTag("outputItem", ItemNBTUtils.stackToNbt(outputItem));
            root.setTag("crafting", crafting);
        }

    }

    @Override
    public void readCommon(NBTTagCompound root) {
        super.readCommon(root);
        NBTTagCompound inventory = root.getCompoundTag("inventory");
        input.deserializeNBT(inventory.getCompoundTag("input"));
        output.deserializeNBT(inventory.getCompoundTag("output"));

        NBTTagCompound crafting = root.getCompoundTag("crafting");
        outputItem = ItemNBTUtils.nbtToStack(crafting.getCompoundTag("outputItem"));

    }

    @Override
    public boolean onBlockActivated(World world, EntityPlayer player, ForgeDirection side, float hitX, float hitY,
        float hitZ) {
        openGui(player);
        return true;
    }

    @Override
    public ModularPanel buildUI(PosGuiData data, PanelSyncManager syncManager, UISettings settings) {
        return new LootFabricatorPanel(this, data, syncManager, settings);
    }
}
