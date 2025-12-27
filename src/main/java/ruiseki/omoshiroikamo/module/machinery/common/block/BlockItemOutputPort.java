package ruiseki.omoshiroikamo.module.machinery.common.block;

import java.util.List;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;

import ruiseki.omoshiroikamo.core.common.block.abstractClass.AbstractBlock;
import ruiseki.omoshiroikamo.module.machinery.common.tile.TEItemOutputPort;

/**
 * Item Output Port - outputs processed items from machines.
 * Can be placed at IO slot positions in machine structures.
 * 
 * TODO: Texture required -
 * assets/omoshiroikamo/textures/blocks/machinery/item_output_port.png
 */
public class BlockItemOutputPort extends AbstractBlock<TEItemOutputPort> {

    protected BlockItemOutputPort() {
        super("modularItemOutput", TEItemOutputPort.class);
        setHardness(5.0F);
        setResistance(10.0F);
    }

    public static BlockItemOutputPort create() {
        return new BlockItemOutputPort();
    }

    @Override
    public String getTextureName() {
        return "machinery/item_output_port";
    }

    @Override
    public void getWailaInfo(List<String> tooltip, EntityPlayer player, World world, int x, int y, int z) {
        // TODO: Add WAILA info for slot contents
    }
}
