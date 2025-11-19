package ruiseki.omoshiroikamo.common.util.energy.capability;

public interface EnergySink {

    int insert(int amount, boolean simulate);

    boolean canConnect();
}
