package ruiseki.omoshiroikamo.api.entity.model;

import lombok.Getter;

public class ModelTierRegistryItem {

    @Getter
    protected final int tier;
    @Getter
    protected final int killMultiplier;
    @Getter
    protected final int dataToNext;
    @Getter
    protected final boolean canSimulate;
    @Getter
    protected final String[] lang;

    public ModelTierRegistryItem(int tier, int killMultiplier, int dataToNext, boolean canSimulate, String[] lang) {
        this.tier = tier;
        this.killMultiplier = killMultiplier;
        this.dataToNext = dataToNext;
        this.canSimulate = canSimulate;
        this.lang = lang;
    }

    public String getTierName() {
        return "model.tier_" + tier + ".name";
    }
}
