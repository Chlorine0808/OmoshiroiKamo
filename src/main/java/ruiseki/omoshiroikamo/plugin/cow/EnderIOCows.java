package ruiseki.omoshiroikamo.plugin.cow;

import java.util.ArrayList;
import java.util.List;

import ruiseki.omoshiroikamo.api.entity.SpawnType;
import ruiseki.omoshiroikamo.api.entity.cow.CowsRegistryItem;

public class EnderIOCows extends BaseCowHandler {

    public static CowsRegistryItem nutrientDistillationCow;
    public static CowsRegistryItem hootchCow;
    public static CowsRegistryItem rocketFuelCow;
    public static CowsRegistryItem fireWaterCow;
    public static CowsRegistryItem liquidSunshineCow;
    public static CowsRegistryItem cloudSeedCow;
    public static CowsRegistryItem cloudSeedConcentratedCow;
    public static CowsRegistryItem enderDistillationCow;
    public static CowsRegistryItem vaporOfLevityCow;

    public EnderIOCows() {
        super("EnderIO", "EnderIO", "textures/entity/cows/base/");
        this.setStartID(200);
    }

    @Override
    public List<CowsRegistryItem> registerCows() {
        List<CowsRegistryItem> allCows = new ArrayList<>();

        nutrientDistillationCow = tryAddCow(
            "NutrientDistillationCow",
            200,
            "nutrient_distillation",
            0x4e2a04,
            0xd3a156,
            SpawnType.NORMAL,
            new String[] { "en_US:Nutrient Distillation Cow", "ja_JP:栄養蒸留牛" });
        allCows.add(nutrientDistillationCow);

        hootchCow = tryAddCow(
            "HootchCow",
            201,
            "hootch",
            0x8c6239,
            0xf2d9ac,
            SpawnType.NORMAL,
            new String[] { "en_US:Hootch Cow", "ja_JP:フーチ牛" });
        allCows.add(hootchCow);

        rocketFuelCow = tryAddCow(
            "RocketFuelCow",
            202,
            "rocket_fuel",
            0xffff33,
            0xffcc00,
            SpawnType.NORMAL,
            new String[] { "en_US:Rocket Fuel Cow", "ja_JP:ロケット燃料牛" });
        allCows.add(rocketFuelCow);

        fireWaterCow = tryAddCow(
            "FireWaterCow",
            203,
            "fire_water",
            0xff3300,
            0xffff66,
            SpawnType.HELL,
            new String[] { "en_US:Fire Water Cow", "ja_JP:ファイアウォーター牛" });
        allCows.add(fireWaterCow);

        liquidSunshineCow = tryAddCow(
            "LiquidSunshineCow",
            204,
            "liquid_sunshine",
            0xffff66,
            0xffffff,
            SpawnType.NORMAL,
            new String[] { "en_US:Liquid Sunshine Cow", "ja_JP:リキッドサンシャイン牛" });
        allCows.add(liquidSunshineCow);

        cloudSeedCow = tryAddCow(
            "CloudSeedCow",
            205,
            "cloud_seed",
            0xa0c4ff,
            0xcaf0f8,
            SpawnType.SNOW,
            new String[] { "en_US:Cloud Seed Cow", "ja_JP:クラウドシード牛" });
        allCows.add(cloudSeedCow);

        cloudSeedConcentratedCow = tryAddCow(
            "CloudSeedConcentratedCow",
            206,
            "cloud_seed_concentrated",
            0x5390d9,
            0x90e0ef,
            SpawnType.SNOW,
            new String[] { "en_US:Cloud Seed Concentrated Cow", "ja_JP:濃縮クラウドシード牛" });
        allCows.add(cloudSeedConcentratedCow);

        enderDistillationCow = tryAddCow(
            "EnderDistillationCow",
            207,
            "ender_distillation",
            0x006666,
            0x33cccc,
            SpawnType.HELL,
            new String[] { "en_US:Ender Distillation Cow", "ja_JP:エンダー蒸留牛" });
        allCows.add(enderDistillationCow);

        vaporOfLevityCow = tryAddCow(
            "VaporOfLevityCow",
            208,
            "vapor_of_levity",
            0xccffff,
            0xffffff,
            SpawnType.NORMAL,
            new String[] { "en_US:Vapor of Levity Cow", "ja_JP:軽さの蒸気牛" });
        allCows.add(vaporOfLevityCow);

        return allCows;
    }
}
