package ruiseki.omoshiroikamo.module.machinery.common.tile.energy.output;

/**
 * Energy Input Port TileEntity.
 * Accepts RF energy for machine processing.
 */
public class TEEnergyOutputPortT1 extends TEEnergyOutputPort {

    public TEEnergyOutputPortT1() {
        super(2048, 128);
    }

    @Override
    public int getTier() {
        return 1;
    }
}
