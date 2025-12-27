package ruiseki.omoshiroikamo.module.machinery.common.block;

import static com.gtnewhorizon.gtnhlib.client.model.ModelISBRH.JSON_ISBRH_ID;

import java.util.List;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;

import ruiseki.omoshiroikamo.core.common.block.abstractClass.AbstractBlock;
import ruiseki.omoshiroikamo.module.machinery.common.tile.TEItemOutputPort;

/**
 * Item Output Port - outputs items from machine processing.
 * Can be placed at IO slot positions in machine structures.
 * Uses JSON model with base + overlay textures via GTNHLib.
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
        return "modular_machine_casing";
    }

    @Override
    public int getRenderType() {
        return JSON_ISBRH_ID;
    }

    @Override
    public void getWailaInfo(List<String> tooltip, EntityPlayer player, World world, int x, int y, int z) {
        // TODO: Add WAILA info for slot contents
    }
}
