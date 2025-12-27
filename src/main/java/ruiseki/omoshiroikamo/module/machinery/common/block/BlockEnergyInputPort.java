package ruiseki.omoshiroikamo.module.machinery.common.block;

import java.util.List;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;

import ruiseki.omoshiroikamo.core.common.block.abstractClass.AbstractBlock;
import ruiseki.omoshiroikamo.module.machinery.common.tile.TEEnergyInputPort;

/**
 * Energy Input Port - accepts RF energy for machine processing.
 * Can be placed at IO slot positions in machine structures.
 * 
 * TODO: Texture required -
 * assets/omoshiroikamo/textures/blocks/machinery/energy_input_port.png
 */
public class BlockEnergyInputPort extends AbstractBlock<TEEnergyInputPort> {

    protected BlockEnergyInputPort() {
        super("modularEnergyInput", TEEnergyInputPort.class);
        setHardness(5.0F);
        setResistance(10.0F);
    }

    public static BlockEnergyInputPort create() {
        return new BlockEnergyInputPort();
    }

    @Override
    public String getTextureName() {
        return "machinery/energy_input_port";
    }

    @Override
    public void getWailaInfo(List<String> tooltip, EntityPlayer player, World world, int x, int y, int z) {
        // TODO: Add WAILA info for energy stored
    }
}
