package ruiseki.omoshiroikamo.module.machinery.common.tile.energy.output;

/**
 * Energy Input Port TileEntity.
 * Accepts RF energy for machine processing.
 */
public class TEEnergyOutputPortT6 extends TEEnergyOutputPort {

    public TEEnergyOutputPortT6() {
        super(131072, 32768);
    }

    @Override
    public int getTier() {
        return 6;
    }
}
