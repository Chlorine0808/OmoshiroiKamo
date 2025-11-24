package ruiseki.omoshiroikamo.common.item.backpack.wrapper;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

import ruiseki.omoshiroikamo.common.util.item.ItemNBTUtils;

public class ToggleableWrapper extends UpgradeWrapper implements IToggleable {

    public ToggleableWrapper(ItemStack upgrade) {
        super(upgrade);
    }

    @Override
    public boolean isEnabled() {
        NBTTagCompound tag = ItemNBTUtils.getNBT(upgrade);
        return tag.hasKey(IUpgrade.TAB_STATE_TAG) && tag.getBoolean(ENABLED_TAG);
    }

    @Override
    public void setEnabled(boolean enabled) {
        NBTTagCompound tag = ItemNBTUtils.getNBT(upgrade);
        tag.setBoolean(ENABLED_TAG, enabled);
    }

    @Override
    public void toggle() {
        setEnabled(!isEnabled());
    }
}
