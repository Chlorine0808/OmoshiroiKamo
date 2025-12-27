package ruiseki.omoshiroikamo.module.machinery.common.block;

import java.util.List;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;

import ruiseki.omoshiroikamo.core.common.block.abstractClass.AbstractBlock;
import ruiseki.omoshiroikamo.module.machinery.common.tile.TEItemInputPort;

/**
 * Item Input Port - accepts items for machine processing.
 * Can be placed at IO slot positions in machine structures.
 * 
 * TODO: Texture required -
 * assets/omoshiroikamo/textures/blocks/machinery/item_input_port.png
 */
public class BlockItemInputPort extends AbstractBlock<TEItemInputPort> {

    protected BlockItemInputPort() {
        super("modularItemInput", TEItemInputPort.class);
        setHardness(5.0F);
        setResistance(10.0F);
    }

    public static BlockItemInputPort create() {
        return new BlockItemInputPort();
    }

    @Override
    public String getTextureName() {
        return "machinery/item_input_port";
    }

    @Override
    public void getWailaInfo(List<String> tooltip, EntityPlayer player, World world, int x, int y, int z) {
        // TODO: Add WAILA info for slot contents
    }
}
