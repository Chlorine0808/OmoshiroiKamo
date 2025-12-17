package ruiseki.omoshiroikamo.common.item.deepMobLearning;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.util.ResourceLocation;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import ruiseki.omoshiroikamo.api.entity.model.DataModel;
import ruiseki.omoshiroikamo.api.entity.model.ModelRegistryItem;
import ruiseki.omoshiroikamo.api.enums.ModObject;
import ruiseki.omoshiroikamo.common.item.ItemOK;
import ruiseki.omoshiroikamo.common.util.KeyboardUtils;
import ruiseki.omoshiroikamo.common.util.Logger;
import ruiseki.omoshiroikamo.common.util.lib.LibMisc;

public class ItemDataModel extends ItemOK {

    private final Map<Integer, IIcon> icons = new HashMap<>();

    public ItemDataModel() {
        super(ModObject.itemDataModel.unlocalisedName);
        setMaxStackSize(1);
    }

    @Override
    public void getSubItems(Item item, CreativeTabs tabs, List<ItemStack> list) {
        for (DataModel model : DataModel.getAllModels()) {
            list.add(new ItemStack(this, 1, model.getId()));
        }
    }

    @Override
    @SideOnly(Side.CLIENT)
    public String getItemStackDisplayName(ItemStack stack) {
        DataModel model = DataModel.getDataFromStack(stack);
        if (model == null) {
            return super.getItemStackDisplayName(stack);
        }
        return LibMisc.LANG.localize(
            model.getItem()
                .getDisplayName());
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void registerIcons(IIconRegister reg) {
        for (DataModel model : DataModel.getAllModels()) {
            int type = model.getId();
            ModelRegistryItem item = model.getItem();

            ResourceLocation tex = item.getTexture();
            String path = tex.getResourcePath();
            String iconName = tex.getResourceDomain() + ":" + path;

            IIcon icon = reg.registerIcon(iconName);
            icons.put(type, icon);
        }
    }

    @Override
    @SideOnly(Side.CLIENT)
    public IIcon getIconFromDamage(int meta) {
        IIcon icon = icons.get(meta);
        if (icon == null) {
            icon = icons.get(0);
        }
        return icon;
    }

    @Override
    public void addInformation(ItemStack stack, EntityPlayer player, List<String> list, boolean flag) {
        // if(DataModel.hasExtraTooltip(stack)) {
        // list.add(DataModel.getExtraTooltip(stack));
        // }

        if (!KeyboardUtils.isHoldingShift()) {
            list.add(LibMisc.LANG.localize("tooltip.holdshift"));
        } else {
            DataModel model = DataModel.getDataFromStack(stack);
            // list.add(LibMisc.LANG.localize("deepmoblearning.data_model.tier", DataModel.getTierName(stack, false)));
            int tier = model.getTier();
            // if (tier != DeepConstants.DATA_MODEL_MAXIMUM_TIER) {
            // list.add(
            // LibMisc.LANG.localize(
            // "deepmoblearning.data_model.data.collected",
            // DataModel.getCurrentTierSimulationCountWithKills(stack),
            // DataModel.getTierRoof(stack)));
            // list.add(
            // LibMisc.LANG.localize(
            // "deepmoblearning.data_model.data.killmultiplier",
            // DataModelExperience.getKillMultiplier(DataModel.getTier(stack))));
            // }
            // list.add(
            // LibMisc.LANG.localize("deepmoblearning.data_model.rfcost", model.getSimulationTickCost(stack)));
            // list.add(LibMisc.LANG.localize("deepmoblearning.data_model.type", model.getMatterTypeName(stack)));
        }
    }
}
