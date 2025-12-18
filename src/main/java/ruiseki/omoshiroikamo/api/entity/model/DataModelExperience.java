package ruiseki.omoshiroikamo.api.entity.model;

import java.util.List;
import java.util.stream.Collectors;

public class DataModelExperience {

    public static List<ModelTierRegistryItem> getAllTiers() {
        return ModelTierRegistry.INSTANCE.getItems()
            .stream()
            .sorted((a, b) -> Integer.compare(a.getTier(), b.getTier()))
            .collect(Collectors.toList());
    }

    public static ModelTierRegistryItem getTierItem(int tier) {
        return ModelTierRegistry.INSTANCE.getByType(tier);
    }

    public static String getTierName(int tier) {
        return getTierItem(tier).getTierName();
    }

    public static int getMaxTier() {
        return getAllTiers().size();
    }

    private static boolean isMaxTier(int tier) {
        return tier >= getMaxTier();
    }

    public static boolean shouldIncreaseTier(int tier, int kc, int sc) {
        if (isMaxTier(tier)) return false;

        ModelTierRegistryItem tierItem = getTierItem(tier);
        if (tierItem == null) return false;

        int multiplier = tierItem.getKillMultiplier();
        int roof = tierItem.getDataToNext(); // dataToNext = "experience needed to next tier"

        int totalExp = kc * multiplier + sc;
        return totalExp >= roof;
    }

    public static double getCurrentTierKillCountWithSims(int tier, int kc, int sc) {
        if (isMaxTier(tier)) return 0;

        ModelTierRegistryItem tierItem = getTierItem(tier);
        int multiplier = tierItem.getKillMultiplier();

        return kc + ((double) sc / multiplier);
    }

    public static int getCurrentTierSimulationCountWithKills(int tier, int kc, int sc) {
        if (isMaxTier(tier)) return 0;

        ModelTierRegistryItem tierItem = getTierItem(tier);
        int multiplier = tierItem.getKillMultiplier();

        return sc + (kc * multiplier);
    }

    public static double getKillsToNextTier(int tier, int kc, int sc) {
        if (isMaxTier(tier)) return 0;

        ModelTierRegistryItem tierItem = getTierItem(tier);
        return tierItem.getDataToNext() - getCurrentTierKillCountWithSims(tier, kc, sc);
    }

    public static int getSimulationsToNextTier(int tier, int kc, int sc) {
        if (isMaxTier(tier)) return 0;

        ModelTierRegistryItem tierItem = getTierItem(tier);
        int multiplier = tierItem.getKillMultiplier();
        int roof = tierItem.getDataToNext();

        return roof - getCurrentTierSimulationCountWithKills(tier, kc, sc);
    }

    public static int getTierRoof(int tier, boolean asKills) {
        if (isMaxTier(tier)) return 0;

        ModelTierRegistryItem tierItem = getTierItem(tier);
        return asKills ? tierItem.getDataToNext() / Math.max(tierItem.getKillMultiplier(), 1)
            : tierItem.getDataToNext();
    }

    public static int getKillMultiplier(int tier) {
        if (isMaxTier(tier)) return 0;

        ModelTierRegistryItem tierItem = getTierItem(tier);
        return tierItem.getKillMultiplier();
    }
}
