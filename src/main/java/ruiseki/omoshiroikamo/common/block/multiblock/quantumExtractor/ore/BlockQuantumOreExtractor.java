package ruiseki.omoshiroikamo.common.block.multiblock.quantumExtractor.ore;

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

public class BlockQuantumOreExtractor extends AbstractMultiBlockBlock<TEQuantumExtractor> {

    protected BlockQuantumOreExtractor() {
        super(ModObject.blockQuantumOreExtractor, TEQuantumExtractor.class);
        this.setLightLevel(0.5F);
    }

    public static BlockQuantumOreExtractor create() {
        return new BlockQuantumOreExtractor();
    }

    @Override
    public void init() {
        GameRegistry.registerBlock(this, ItemBlockQuantumOreExtractor.class, name);
        GameRegistry.registerTileEntity(TEQuantumOreExtractorT1.class, "TEQuantumOreExtractorT1TileEntity");
        GameRegistry.registerTileEntity(TEQuantumOreExtractorT2.class, "TEQuantumOreExtractorT2TileEntity");
        GameRegistry.registerTileEntity(TEQuantumOreExtractorT3.class, "TEQuantumOreExtractorT3TileEntity");
        GameRegistry.registerTileEntity(TEQuantumOreExtractorT4.class, "TEQuantumOreExtractorT4TileEntity");
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
        blockIcon = iIconRegister.registerIcon(LibResources.PREFIX_MOD + "ore_extractor");
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
                return new TEQuantumOreExtractorT4();
            case 2:
                return new TEQuantumOreExtractorT3();
            case 1:
                return new TEQuantumOreExtractorT2();
            default:
                return new TEQuantumOreExtractorT1();
        }
    }

    public static class ItemBlockQuantumOreExtractor extends ItemBlockOK {

        public ItemBlockQuantumOreExtractor(Block block) {
            super(block, block);
        }

        @Override
        public String getUnlocalizedName(ItemStack stack) {
            int tier = stack.getItemDamage() + 1;
            return super.getUnlocalizedName() + ".tier_" + tier;
        }

    }

}
