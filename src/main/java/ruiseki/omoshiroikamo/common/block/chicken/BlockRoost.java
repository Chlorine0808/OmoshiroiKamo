package ruiseki.omoshiroikamo.common.block.chicken;

import com.enderio.core.common.TileEntityEnder;

import ruiseki.omoshiroikamo.api.enums.ModObject;
import ruiseki.omoshiroikamo.common.block.BlockOK;
import ruiseki.omoshiroikamo.common.block.abstractClass.AbstractBlock;
import ruiseki.omoshiroikamo.common.block.furnace.TEFurnace;

public class BlockRoost extends AbstractBlock<TEFurnace> {

    protected BlockRoost() {
        super(ModObject.blockRoost, null);
    }
}
