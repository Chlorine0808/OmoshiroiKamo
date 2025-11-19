package ruiseki.omoshiroikamo.common.util.energy.capability;

public interface EnergySource {

    int extract(int amount, boolean simulate);

    boolean canConnect();
}
