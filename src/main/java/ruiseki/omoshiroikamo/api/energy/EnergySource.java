package ruiseki.omoshiroikamo.api.energy;

public interface EnergySource {

    int extract(int amount, boolean simulate);
}
