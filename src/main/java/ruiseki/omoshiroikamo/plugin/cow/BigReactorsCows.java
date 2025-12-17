package ruiseki.omoshiroikamo.plugin.cow;

import java.util.ArrayList;
import java.util.List;

import ruiseki.omoshiroikamo.api.entity.SpawnType;
import ruiseki.omoshiroikamo.api.entity.cow.CowsRegistryItem;

public class BigReactorsCows extends BaseCowHandler {

    public static CowsRegistryItem yelloriumCow;
    public static CowsRegistryItem cyaniteCow;
    public static CowsRegistryItem steamCow;

    public BigReactorsCows() {
        super("BigReactors", "Big Reactors", "textures/entity/cows/base/");
        this.setStartID(500);
    }

    @Override
    public List<CowsRegistryItem> registerCows() {
        List<CowsRegistryItem> allCows = new ArrayList<>();

        yelloriumCow = tryAddCow(
            "YelloriumCow",
            500,
            "yellorium",
            0xE5FF00,
            0xA6A600,
            SpawnType.NORMAL,
            new String[] { "en_US:Yellorium Cow", "ja_JP:イエローリウム牛" });
        allCows.add(yelloriumCow);

        cyaniteCow = tryAddCow(
            "CyaniteCow",
            501,
            "cyanite",
            0x66CCFF,
            0x3399CC,
            SpawnType.NORMAL,
            new String[] { "en_US:Cyanite Cow", "ja_JP:シアナイト牛" });
        allCows.add(cyaniteCow);

        steamCow = tryAddCow(
            "SteamCow",
            502,
            "steam",
            0xCCCCCC,
            0xFFFFFF,
            SpawnType.NORMAL,
            new String[] { "en_US:Steam Cow", "ja_JP:スチーム牛" });
        allCows.add(steamCow);

        return allCows;
    }
}
