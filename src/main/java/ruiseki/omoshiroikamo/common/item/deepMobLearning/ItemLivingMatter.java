package ruiseki.omoshiroikamo.common.item.deepMobLearning;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import ruiseki.omoshiroikamo.api.entity.model.LivingRegistry;
import ruiseki.omoshiroikamo.api.entity.model.LivingRegistryItem;
import ruiseki.omoshiroikamo.api.enums.ModObject;
import ruiseki.omoshiroikamo.common.item.ItemOK;
import ruiseki.omoshiroikamo.common.util.lib.LibMisc;

public class ItemLivingMatter extends ItemOK {

    private final Map<Integer, IIcon> icons = new HashMap<>();

    public ItemLivingMatter() {
        super(ModObject.itemLivingMatter.unlocalisedName);
        setMaxStackSize(64);
    }

    @Override
    public void getSubItems(Item item, CreativeTabs tabs, List<ItemStack> list) {
        for (LivingRegistryItem model : LivingRegistry.INSTANCE.getItems()) {
            list.add(new ItemStack(this, 1, model.getId()));
        }
    }

    @Override
    @SideOnly(Side.CLIENT)
    public String getItemStackDisplayName(ItemStack stack) {
        LivingRegistryItem model = LivingRegistry.INSTANCE.getByType(stack.getItemDamage());
        if (model == null) {
            return super.getItemStackDisplayName(stack);
        }
        return LibMisc.LANG.localize(model.getItemName());
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void registerIcons(IIconRegister reg) {
        for (LivingRegistryItem model : LivingRegistry.INSTANCE.getItems()) {
            int type = model.getId();
            String iconName = model.getTexture();
            IIcon icon = reg.registerIcon(iconName);
            icons.put(type, icon);
        }
    }

    @Override
    @SideOnly(Side.CLIENT)
    public IIcon getIconFromDamage(int meta) {
        return icons.get(meta);
    }
}
