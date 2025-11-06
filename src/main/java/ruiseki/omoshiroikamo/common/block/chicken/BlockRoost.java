package ruiseki.omoshiroikamo.common.block.chicken;

import ruiseki.omoshiroikamo.api.enums.ModObject;
import ruiseki.omoshiroikamo.common.block.abstractClass.AbstractBlock;
import ruiseki.omoshiroikamo.common.block.furnace.TEFurnace;

public class BlockRoost extends AbstractBlock<TEFurnace> {

    protected BlockRoost() {
        super(ModObject.blockRoost, null);
    }
}
