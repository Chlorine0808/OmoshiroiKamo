package ruiseki.omoshiroikamo.module.machinery.common.tile.energy.output;

/**
 * Energy Input Port TileEntity.
 * Accepts RF energy for machine processing.
 */
public class TEEnergyOutputPortT5 extends TEEnergyOutputPort {

    public TEEnergyOutputPortT5() {
        super(32768, 8192);
    }

    @Override
    public int getTier() {
        return 5;
    }
}
