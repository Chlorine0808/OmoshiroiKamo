package ruiseki.omoshiroikamo.module.machinery.common.tile.energy.output;

/**
 * Energy Input Port TileEntity.
 * Accepts RF energy for machine processing.
 */
public class TEEnergyOutputPortT4 extends TEEnergyOutputPort {

    public TEEnergyOutputPortT4() {
        super(16384, 2048);
    }

    @Override
    public int getTier() {
        return 4;
    }
}
