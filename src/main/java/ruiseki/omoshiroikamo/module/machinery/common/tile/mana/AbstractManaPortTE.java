package ruiseki.omoshiroikamo.module.machinery.common.tile.mana;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.common.util.ForgeDirection;

import ruiseki.omoshiroikamo.api.mana.ManaStorage;
import ruiseki.omoshiroikamo.api.multiblock.IModularPort;
import ruiseki.omoshiroikamo.core.common.block.abstractClass.AbstractTE;
import vazkii.botania.api.mana.spark.ISparkAttachable;
import vazkii.botania.api.mana.spark.ISparkEntity;

public abstract class AbstractManaPortTE extends AbstractTE implements IModularPort, ISparkAttachable {

    protected final IO[] sides = new IO[6];

    protected ManaStorage manaStorage;

    private static final String MANA_TAG = "mana";
    private static final String MAX_MANA_TAG = "mana";

    protected ISparkEntity spark;

    protected AbstractManaPortTE(int manaCapacity, int manaMaxReceive) {
        manaStorage = new ManaStorage(manaCapacity, manaMaxReceive) {

            @Override
            protected void onManaChanged() {
                super.onManaChanged();
                markDirty();
            }
        };
        for (int i = 0; i < 6; i++) {
            sides[i] = IO.NONE;
        }
    }

    @Override
    public int getCurrentMana() {
        return manaStorage.getManaStored();
    }

    @Override
    public boolean isFull() {
        return manaStorage.isFull();
    }

    @Override
    public int getAvailableSpaceForMana() {
        return manaStorage.getMaxManaStored() - manaStorage.getManaStored();
    }

    @Override
    public void recieveMana(int amount) {
        manaStorage.receiveMana(amount, false);
    }

    @Override
    public boolean canAttachSpark(ItemStack stack) {
        return true;
    }

    @Override
    public void attachSpark(ISparkEntity entity) {
        this.spark = entity;
    }

    @Override
    public ISparkEntity getAttachedSpark() {
        return spark;
    }

    @Override
    public boolean areIncomingTranfersDone() {
        return isFull();
    }

    @Override
    public abstract boolean canRecieveManaFromBursts();

    @Override
    public IO getSideIO(ForgeDirection side) {
        return sides[side.ordinal()];
    }

    @Override
    public void setSideIO(ForgeDirection side, IO state) {
        sides[side.ordinal()] = state;
        requestRenderUpdate();
    }

    @Override
    public boolean isActive() {
        return false;
    }

    /**
     * Extract energy for machine processing.
     *
     * @param amount requested amount
     * @return amount actually extracted
     */
    public int extractMana(int amount) {
        int extracted = Math.min(manaStorage.getManaStored(), amount);
        manaStorage.voidMana(extracted);
        return extracted;
    }

    @Override
    public boolean processTasks(boolean redstoneCheckPassed) {
        return false;
    }

    @Override
    public void writeCommon(NBTTagCompound root) {
        super.writeCommon(root);

        int[] sideData = new int[6];
        for (int i = 0; i < 6; i++) {
            sideData[i] = sides[i].ordinal();
        }
        root.setIntArray("sideIO", sideData);
        manaStorage.writeToNBT(root);
    }

    @Override
    public void readCommon(NBTTagCompound root) {
        super.readCommon(root);

        if (root.hasKey("sideIO")) {
            int[] sideData = root.getIntArray("sideIO");
            for (int i = 0; i < 6 && i < sideData.length; i++) {
                sides[i] = IO.values()[sideData[i]];
            }
        }
        manaStorage.readFromNBT(root);
    }
}
