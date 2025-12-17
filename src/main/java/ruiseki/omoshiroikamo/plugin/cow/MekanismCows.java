package ruiseki.omoshiroikamo.plugin.cow;

import java.util.ArrayList;
import java.util.List;

import ruiseki.omoshiroikamo.api.entity.SpawnType;
import ruiseki.omoshiroikamo.api.entity.cow.CowsRegistryItem;

public class MekanismCows extends BaseCowHandler {

    public static CowsRegistryItem heavywaterCow;
    public static CowsRegistryItem brineCow;
    public static CowsRegistryItem lithiumCow;

    public MekanismCows() {
        super("Mekanism", "Mekanism", "textures/entity/cows/base/");
        this.setStartID(400);
    }

    @Override
    public List<CowsRegistryItem> registerCows() {
        List<CowsRegistryItem> allCows = new ArrayList<>();

        heavywaterCow = tryAddCow(
            "HeavyWaterCow",
            400,
            "heavywater",
            0x1b2aff,
            0x9dbdff,
            SpawnType.NORMAL,
            new String[] { "en_US:Heavy Water Cow", "ja_JP:重水牛" });
        allCows.add(heavywaterCow);

        brineCow = tryAddCow(
            "BrineCow",
            401,
            "brine",
            0xe8e084,
            0xffffcc,
            SpawnType.NORMAL,
            new String[] { "en_US:Brine Cow", "ja_JP:塩水牛" });
        allCows.add(brineCow);

        lithiumCow = tryAddCow(
            "LithiumCow",
            402,
            "lithium",
            0x0a2a7a,
            0x4f7bd5,
            SpawnType.NORMAL,
            new String[] { "en_US:Lithium Cow", "ja_JP:リチウム牛" });
        allCows.add(lithiumCow);

        return allCows;
    }
}
