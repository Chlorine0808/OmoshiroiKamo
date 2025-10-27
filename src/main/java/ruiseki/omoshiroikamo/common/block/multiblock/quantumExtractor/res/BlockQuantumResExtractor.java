package ruiseki.omoshiroikamo.common.block.multiblock.quantumExtractor.res;

import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

import cpw.mods.fml.common.registry.GameRegistry;
import ruiseki.omoshiroikamo.api.enums.ModObject;
import ruiseki.omoshiroikamo.common.block.ItemBlockOK;
import ruiseki.omoshiroikamo.common.block.abstractClass.AbstractMultiBlockBlock;
import ruiseki.omoshiroikamo.common.block.multiblock.quantumExtractor.TEQuantumExtractor;
import ruiseki.omoshiroikamo.common.util.lib.LibResources;

public class BlockQuantumResExtractor extends AbstractMultiBlockBlock<TEQuantumExtractor> {

    protected BlockQuantumResExtractor() {
        super(ModObject.blockQuantumResExtractor, TEQuantumExtractor.class);
        this.setLightLevel(0.5F);
    }

    public static BlockQuantumResExtractor create() {
        return new BlockQuantumResExtractor();
    }

    @Override
    public void init() {
        GameRegistry.registerBlock(this, ItemBlockQuantumResExtractor.class, name);
        GameRegistry.registerTileEntity(TEQuantumResExtractorT1.class, "TEQuantumResExtractorT1TileEntity");
        GameRegistry.registerTileEntity(TEQuantumResExtractorT2.class, "TEQuantumResExtractorT2TileEntity");
        GameRegistry.registerTileEntity(TEQuantumResExtractorT3.class, "TEQuantumResExtractorT3TileEntity");
        GameRegistry.registerTileEntity(TEQuantumResExtractorT4.class, "TEQuantumResExtractorT4TileEntity");
    }

    @Override
    public void getSubBlocks(Item itemIn, CreativeTabs tab, List<ItemStack> list) {
        list.add(new ItemStack(this, 1, 0));
        list.add(new ItemStack(this, 1, 1));
        list.add(new ItemStack(this, 1, 2));
        list.add(new ItemStack(this, 1, 3));
    }

    @Override
    public void registerBlockIcons(IIconRegister iIconRegister) {
        blockIcon = iIconRegister.registerIcon(LibResources.PREFIX_MOD + "res_extractor");
    }

    @Override
    public int getRenderType() {
        return -1;
    }

    @Override
    public boolean isOpaqueCube() {
        return false;
    }

    @Override
    public boolean renderAsNormalBlock() {
        return false;
    }

    @Override
    public TileEntity createTileEntity(World world, int meta) {
        switch (meta) {
            case 3:
                return new TEQuantumResExtractorT4();
            case 2:
                return new TEQuantumResExtractorT3();
            case 1:
                return new TEQuantumResExtractorT2();
            default:
                return new TEQuantumResExtractorT1();
        }
    }

    public static class ItemBlockQuantumResExtractor extends ItemBlockOK {

        public ItemBlockQuantumResExtractor(Block block) {
            super(block, block);
        }

        @Override
        public String getUnlocalizedName(ItemStack stack) {
            int tier = stack.getItemDamage() + 1;
            return super.getUnlocalizedName() + ".tier_" + tier;
        }

    }

}
