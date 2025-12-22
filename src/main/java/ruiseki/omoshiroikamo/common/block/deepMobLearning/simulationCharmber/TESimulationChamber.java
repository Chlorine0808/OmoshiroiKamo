package ruiseki.omoshiroikamo.common.block.deepMobLearning.simulationCharmber;

import java.util.concurrent.ThreadLocalRandom;

import net.minecraft.item.ItemStack;
import net.minecraftforge.common.util.ForgeDirection;

import ruiseki.omoshiroikamo.api.crafting.CraftingState;
import ruiseki.omoshiroikamo.api.energy.IEnergySink;
import ruiseki.omoshiroikamo.api.entity.model.DataModel;
import ruiseki.omoshiroikamo.client.gui.modularui2.base.handler.ItemStackHandlerBase;
import ruiseki.omoshiroikamo.client.gui.modularui2.deepMobLearning.handler.ItemHandlerDataModel;
import ruiseki.omoshiroikamo.client.gui.modularui2.deepMobLearning.handler.ItemHandlerPolymerClay;
import ruiseki.omoshiroikamo.common.block.abstractClass.AbstractMachineTE;
import ruiseki.omoshiroikamo.common.item.deepMobLearning.ItemDataModel;
import ruiseki.omoshiroikamo.common.item.deepMobLearning.ItemPolymerClay;
import ruiseki.omoshiroikamo.config.backport.DeepMobLearningConfig;

public class TESimulationChamber extends AbstractMachineTE implements IEnergySink {

    private final ItemHandlerDataModel inputDataModel = new ItemHandlerDataModel() {

        @Override
        protected void onContentsChanged(int slot) {
            onDataModelChanged();
        }
    };
    private final ItemHandlerPolymerClay inputPolymer = new ItemHandlerPolymerClay();
    private final ItemStackHandlerBase outputLiving = new ItemStackHandlerBase();
    private final ItemStackHandlerBase outputPristine = new ItemStackHandlerBase();

    private boolean pristineSuccess = false;

    public TESimulationChamber() {
        super(
            DeepMobLearningConfig.simulationChamberEnergyCapacity,
            DeepMobLearningConfig.simulationChamberEnergyInMax);
    }

    @Override
    protected void startCrafting() {
        super.startCrafting();

        int pristineChance = DataModel.getPristineChance(getDataModel());
        int random = ThreadLocalRandom.current()
            .nextInt(100);
        pristineSuccess = (random < pristineChance);

        inputPolymer.voidItem();
    }

    @Override
    public boolean canStartCrafting() {
        return super.canStartCrafting() && hasPolymerClay() && canContinueCrafting();
    }

    @Override
    protected boolean canContinueCrafting() {
        return super.canContinueCrafting() && hasDataModel()
            && canDataModelSimulate()
            && !isLivingMatterOutputFull()
            && !isPristineMatterOutputFull();
    }

    @Override
    protected void finishCrafting() {
        ItemStack dataModel = getDataModel();

        resetCrafting();
    }

    @Override
    protected void resetCrafting() {
        super.resetCrafting();
        pristineSuccess = false;
    }

    @Override
    protected int getCraftingDuration() {
        return DeepMobLearningConfig.simulationChamberProcessingTime;
    }

    public boolean isPristineSuccess() {
        return pristineSuccess;
    }

    @Override
    public int getCraftingEnergyCost() {
        return DataModel.getSimulationEnergy(getDataModel());
    }

    @Override
    protected CraftingState updateCraftingState() {
        if (!hasDataModel()) return CraftingState.IDLE;
        else if (!canContinueCrafting() || (!this.isCrafting() && !canStartCrafting())) return CraftingState.ERROR;

        return CraftingState.RUNNING;
    }

    @Override
    public boolean isActive() {
        return isCrafting();
    }

    private void onDataModelChanged() {
        if (!worldObj.isRemote) {
            resetCrafting();
        }
    }

    public ItemStack getDataModel() {
        return inputDataModel.getStackInSlot(0);
    }

    public boolean hasDataModel() {
        return getDataModel() != null && getDataModel().getItem() instanceof ItemDataModel;
    }

    public boolean canDataModelSimulate() {
        return DataModel.canSimulate(getDataModel());
    }

    public ItemStack getPolymerClay() {
        return inputPolymer.getStackInSlot(0);
    }

    public boolean hasPolymerClay() {
        return getDataModel() != null && getPolymerClay().getItem() instanceof ItemPolymerClay;
    }

    public boolean isLivingMatterOutputFull() {
        ItemStack livingMatterStack = outputLiving.getStackInSlot(0);

        if (livingMatterStack == null) {
            return false;
        }

        boolean stackIsFull = (livingMatterStack.stackSize >= outputLiving.getSlotLimit(0));
        boolean stackMatchesDataModel = DataModel.isDataModelMatchesLivingMatter(getDataModel(), livingMatterStack);

        return (stackIsFull || !stackMatchesDataModel);
    }

    public boolean isPristineMatterOutputFull() {
        ItemStack pristineMatterStack = outputPristine.getStackInSlot(0);

        if (pristineMatterStack == null) {
            return false;
        }

        boolean stackIsFull = (pristineMatterStack.stackSize >= outputPristine.getSlotLimit(0));
        boolean stackMatchesDataModel = DataModel.isDataModelMatchesPristineMatter(getDataModel(), pristineMatterStack);

        return (stackIsFull || !stackMatchesDataModel);
    }

    @Override
    public int receiveEnergy(ForgeDirection side, int amount, boolean simulate) {
        return energyStorage.receiveEnergy(amount, simulate);
    }
}
