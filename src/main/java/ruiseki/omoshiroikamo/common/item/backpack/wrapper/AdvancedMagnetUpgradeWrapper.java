package ruiseki.omoshiroikamo.common.item.backpack.wrapper;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

import ruiseki.omoshiroikamo.common.util.item.ItemNBTUtils;

public class AdvancedMagnetUpgradeWrapper extends AdvancedPickupUpgradeWrapper implements IMagnetUpgrade {

    public AdvancedMagnetUpgradeWrapper(ItemStack upgrade) {
        super(upgrade);
    }

    @Override
    public boolean isCollectItem() {
        NBTTagCompound tag = ItemNBTUtils.getNBT(upgrade);
        return tag.hasKey(MAG_ITEM_TAG) && tag.getBoolean(MAG_ITEM_TAG);
    }

    @Override
    public void setCollectItem(boolean enabled) {
        NBTTagCompound tag = ItemNBTUtils.getNBT(upgrade);
        tag.setBoolean(MAG_ITEM_TAG, enabled);
    }

    @Override
    public boolean isCollectExp() {
        NBTTagCompound tag = ItemNBTUtils.getNBT(upgrade);
        return tag.hasKey(MAG_EXP_TAG) && tag.getBoolean(MAG_EXP_TAG);
    }

    @Override
    public void setCollectExp(boolean enabled) {
        NBTTagCompound tag = ItemNBTUtils.getNBT(upgrade);
        tag.setBoolean(MAG_EXP_TAG, enabled);
    }

    @Override
    public boolean canCollectItem(ItemStack stack) {
        return checkFilter(stack);
    }
}
