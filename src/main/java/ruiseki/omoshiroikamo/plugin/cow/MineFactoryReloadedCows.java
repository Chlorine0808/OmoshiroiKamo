package ruiseki.omoshiroikamo.plugin.cow;

import java.util.ArrayList;
import java.util.List;

import ruiseki.omoshiroikamo.api.entity.SpawnType;
import ruiseki.omoshiroikamo.api.entity.cow.CowsRegistryItem;

public class MineFactoryReloadedCows extends BaseCowHandler {

    public static CowsRegistryItem steamCow;
    public static CowsRegistryItem sludgeCow;
    public static CowsRegistryItem sewageCow;
    public static CowsRegistryItem mobEssenceCow;
    public static CowsRegistryItem biofuelCow;
    public static CowsRegistryItem meatCow;
    public static CowsRegistryItem pinkSlimeCow;
    public static CowsRegistryItem chocolateMilkCow;
    public static CowsRegistryItem mushroomSoupCow;

    public MineFactoryReloadedCows() {
        super("Base", "Base", "textures/entity/cows/base/");
        this.setStartID(600);
    }

    @Override
    public List<CowsRegistryItem> registerCows() {
        List<CowsRegistryItem> allCows = new ArrayList<>();

        steamCow = tryAddCow(
            "SteamCow",
            600,
            "steam",
            0xCCCCCC,
            0xFFFFFF,
            SpawnType.NORMAL,
            new String[] { "en_US:Steam Cow", "ja_JP:スチーム牛" });
        allCows.add(steamCow);

        sludgeCow = tryAddCow(
            "SludgeCow",
            601,
            "sludge",
            0x2d2d2d,
            0x555555,
            SpawnType.HELL,
            new String[] { "en_US:Sludge Cow", "ja_JP:スラッジ牛" });
        allCows.add(sludgeCow);

        sewageCow = tryAddCow(
            "SewageCow",
            602,
            "sewage",
            0x665500,
            0xccaa33,
            SpawnType.NORMAL,
            new String[] { "en_US:Sewage Cow", "ja_JP:下水牛" });
        allCows.add(sewageCow);

        mobEssenceCow = tryAddCow(
            "MobEssenceCow",
            603,
            "mobessence",
            0x33ff33,
            0x99ff99,
            SpawnType.NORMAL,
            new String[] { "en_US:Mob Essence Cow", "ja_JP:モブエッセンス牛" });
        allCows.add(mobEssenceCow);

        biofuelCow = tryAddCow(
            "BiofuelCow",
            604,
            "biofuel",
            0x99cc00,
            0xccff66,
            SpawnType.NORMAL,
            new String[] { "en_US:Biofuel Cow", "ja_JP:バイオ燃料牛" });
        allCows.add(biofuelCow);

        meatCow = tryAddCow(
            "MeatCow",
            605,
            "meat",
            0xcc6666,
            0xff9999,
            SpawnType.NORMAL,
            new String[] { "en_US:Meat Cow", "ja_JP:肉牛" });
        allCows.add(meatCow);

        pinkSlimeCow = tryAddCow(
            "PinkSlimeCow",
            606,
            "pinkslime",
            0xff66cc,
            0xff99dd,
            SpawnType.NORMAL,
            new String[] { "en_US:Pink Slime Cow", "ja_JP:ピンクスライム牛" });
        allCows.add(pinkSlimeCow);

        chocolateMilkCow = tryAddCow(
            "ChocolateMilkCow",
            607,
            "chocolatemilk",
            0x663300,
            0xcc9966,
            SpawnType.NORMAL,
            new String[] { "en_US:Chocolate Milk Cow", "ja_JP:チョコレートミルク牛" });
        allCows.add(chocolateMilkCow);

        mushroomSoupCow = tryAddCow(
            "MushroomSoupCow",
            608,
            "mushroomsoup",
            0xccaa88,
            0xffddbb,
            SpawnType.NORMAL,
            new String[] { "en_US:Mushroom Soup Cow", "ja_JP:マッシュルームスープ牛" });
        allCows.add(mushroomSoupCow);

        return allCows;
    }
}
