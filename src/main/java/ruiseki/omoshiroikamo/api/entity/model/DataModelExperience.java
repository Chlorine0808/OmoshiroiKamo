package ruiseki.omoshiroikamo.api.entity.model;

import ruiseki.omoshiroikamo.config.backport.DeepMobLearningConfig;

/**
 * Created by xt9 on 2017-06-14.
 */
public class DataModelExperience {

    private static int[] getKillMultipliers() {
        return DeepMobLearningConfig.dataModelConfig.killMultiplier;
    }

    private static int[] getKillsToTier() {
        return DeepMobLearningConfig.dataModelConfig.killsToTier;
    }

    public static int getMaxTier() {
        return Math.min(getKillMultipliers().length, getKillsToTier().length);
    }

    private static boolean isMaxTier(int tier) {
        return tier >= getMaxTier();
    }

    /* tier is CURRENT tier, kc is kill count, sc is simulation count */
    public static boolean shouldIncreaseTier(int tier, int kc, int sc) {
        if (isMaxTier(tier)) {
            return false;
        }

        int multiplier = getKillMultipliers()[tier];
        int roof = getKillsToTier()[tier] * multiplier;

        int killExperience = kc * multiplier;
        return killExperience + sc >= roof;
    }

    public static double getCurrentTierKillCountWithSims(int tier, int kc, int sc) {
        if (isMaxTier(tier)) {
            return 0;
        }

        int multiplier = getKillMultipliers()[tier];
        return kc + ((double) sc / multiplier);
    }

    public static int getCurrentTierSimulationCountWithKills(int tier, int kc, int sc) {
        if (isMaxTier(tier)) {
            return 0;
        }

        int multiplier = getKillMultipliers()[tier];
        return sc + (kc * multiplier);
    }

    public static double getKillsToNextTier(int tier, int kc, int sc) {
        if (isMaxTier(tier)) {
            return 0;
        }

        int roofKills = getKillsToTier()[tier];
        return roofKills - getCurrentTierKillCountWithSims(tier, kc, sc);
    }

    public static int getSimulationsToNextTier(int tier, int kc, int sc) {
        if (isMaxTier(tier)) {
            return 0;
        }

        int multiplier = getKillMultipliers()[tier];
        int roof = getKillsToTier()[tier] * multiplier;

        return roof - getCurrentTierSimulationCountWithKills(tier, kc, sc);
    }

    public static int getTierRoof(int tier, boolean asKills) {
        if (isMaxTier(tier)) {
            return 0;
        }

        int multiplier = getKillMultipliers()[tier];
        int roof = getKillsToTier()[tier] * multiplier;

        return asKills ? getKillsToTier()[tier] : roof;
    }

    public static int getKillMultiplier(int tier) {
        if (isMaxTier(tier)) {
            return 0;
        }
        return getKillMultipliers()[tier];
    }
}
