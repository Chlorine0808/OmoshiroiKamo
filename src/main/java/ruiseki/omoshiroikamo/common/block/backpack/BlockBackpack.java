package ruiseki.omoshiroikamo.common.block.backpack;

import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

import com.cleanroommc.modularui.api.IGuiHolder;
import com.cleanroommc.modularui.factory.GuiFactories;
import com.cleanroommc.modularui.factory.PlayerInventoryGuiData;
import com.cleanroommc.modularui.screen.ModularPanel;
import com.cleanroommc.modularui.screen.UISettings;
import com.cleanroommc.modularui.value.sync.PanelSyncManager;

import cpw.mods.fml.common.registry.GameRegistry;
import ruiseki.omoshiroikamo.api.enums.ModObject;
import ruiseki.omoshiroikamo.common.block.ItemBlockBauble;
import ruiseki.omoshiroikamo.common.block.abstractClass.AbstractBlock;
import ruiseki.omoshiroikamo.common.util.TooltipUtils;
import ruiseki.omoshiroikamo.common.util.item.ItemNBTUtils;

public class BlockBackpack extends AbstractBlock<TEBackpack> {

    protected BlockBackpack() {
        super(ModObject.blockTest, TEBackpack.class, Material.cloth);
        setStepSound(soundTypeCloth);
        setHardness(0f);
    }

    public static BlockBackpack create() {
        return new BlockBackpack();
    }

    @Override
    public void init() {
        GameRegistry.registerBlock(this, ItemBackpack.class, name);
        GameRegistry.registerTileEntity(teClass, name + "TileEntity");
    }

    @Override
    public TileEntity createTileEntity(World world, int metadata) {
        return new TEBackpack();
    }

    public static class ItemBackpack extends ItemBlockBauble implements IGuiHolder<PlayerInventoryGuiData> {

        public ItemBackpack(Block block) {
            super(block, block);
        }

        @Override
        public String[] getBaubleTypes(ItemStack itemstack) {
            return new String[] { "body" };
        }

        @Override
        public void addInformation(ItemStack stack, EntityPlayer player, List<String> list, boolean flag) {
            TooltipUtils builder = TooltipUtils.builder();
            builder.add(
                ItemNBTUtils.getNBT(stack)
                    .toString());
            list.addAll(builder.build());
        }

        @Override
        public ItemStack onItemRightClick(ItemStack itemStackIn, World worldIn, EntityPlayer player) {
            if (!worldIn.isRemote && !player.isSneaking()) {
                GuiFactories.playerInventory()
                    .openFromMainHand(player);
            }
            return super.onItemRightClick(itemStackIn, worldIn, player);
        }

        @Override
        public ModularPanel buildUI(PlayerInventoryGuiData data, PanelSyncManager syncManager, UISettings settings) {
            ItemStack usedItem = data.getUsedItemStack();
            BackpackHandler cap = new BackpackHandler(usedItem, null);
            return new BackpackGuiHolder.ItemStackGuiHolder(cap).buildUI(data, syncManager, settings);
        }
    }
}
