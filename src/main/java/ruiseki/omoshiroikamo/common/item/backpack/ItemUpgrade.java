package ruiseki.omoshiroikamo.common.item.backpack;

import java.util.List;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;

import ruiseki.omoshiroikamo.api.enums.ModObject;
import ruiseki.omoshiroikamo.common.item.ItemOK;
import ruiseki.omoshiroikamo.common.item.backpack.wrapper.IUpgradeWrapperFactory;
import ruiseki.omoshiroikamo.common.item.backpack.wrapper.UpgradeWrapper;
import ruiseki.omoshiroikamo.common.util.lib.LibMisc;
import ruiseki.omoshiroikamo.common.util.lib.LibResources;

public class ItemUpgrade<T extends UpgradeWrapper> extends ItemOK implements IUpgradeWrapperFactory<T> {

    public ItemUpgrade(String name) {
        super(name);
        setNoRepair();
        setTextureName("upgrade_base");
    }

    public ItemUpgrade() {
        this(ModObject.itemUpgrade.unlocalisedName);
    }

    public boolean hasTab() {
        return false;
    }

    @Override
    public void addInformation(ItemStack itemstack, EntityPlayer entityplayer, List<String> list, boolean flag) {
        list.add(LibMisc.LANG.localize(LibResources.TOOLTIP + "backpack.upgrade_base"));
    }

    @SuppressWarnings("unchecked")
    @Override
    public T createWrapper(ItemStack stack) {
        return (T) new UpgradeWrapper(stack);
    }
}
