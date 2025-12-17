package ruiseki.omoshiroikamo.plugin.chicken;

import java.util.ArrayList;
import java.util.List;

import ruiseki.omoshiroikamo.api.entity.SpawnType;
import ruiseki.omoshiroikamo.api.entity.chicken.ChickensRegistryItem;

public class BigReactorsChickens extends BaseChickenHandler {

    public static ChickensRegistryItem yelloriumChicken = null;
    public static ChickensRegistryItem cyaniteChicken = null;
    public static ChickensRegistryItem blutoniumChicken = null;
    public static ChickensRegistryItem ludicriteChicken = null;
    public static ChickensRegistryItem graphiteChicken = null;

    public BigReactorsChickens() {
        super("BigReactors", "Big Reactors", "textures/entity/chicken/bigreactors/");
        setStartID(800);
    }

    @Override
    public List<ChickensRegistryItem> registerChickens() {
        List<ChickensRegistryItem> allChickens = new ArrayList<>();

        yelloriumChicken = addChicken(
            "YelloriumChicken",
            this.nextID(),
            "YelloriumChicken.png",
            this.getFirstOreDictionary("ingotYellorium"),
            0xA5B700,
            0xD7EF00,
            SpawnType.NONE,
            new String[] { "en_US:Yellorium Chicken", "ja_JP:イエロリウムのニワトリ" });
        allChickens.add(yelloriumChicken);

        graphiteChicken = addChicken(
            "GraphiteChicken",
            this.nextID(),
            "GraphiteChicken.png",
            this.getFirstOreDictionary("ingotGraphite"),
            0x41453F,
            0x595959,
            SpawnType.NONE,
            new String[] { "en_US:Graphite Chicken", "ja_JP:グラファイトのニワトリ" });
        allChickens.add(graphiteChicken);

        cyaniteChicken = addChicken(
            "CyaniteChicken",
            this.nextID(),
            "CyaniteChicken.png",
            this.getFirstOreDictionary("ingotCyanite"),
            0x0068B4,
            0x5CAFDB,
            SpawnType.NONE,
            new String[] { "en_US:Cyanite Chicken", "ja_JP:シアナイトのニワトリ" });
        allChickens.add(cyaniteChicken);

        blutoniumChicken = addChicken(
            "BlutoniumChicken",
            this.nextID(),
            "BlutoniumChicken.png",
            this.getFirstOreDictionary("ingotBlutonium"),
            0x4642D6,
            0xf5fcf1,
            SpawnType.NONE,
            new String[] { "en_US:Blutonium Chicken", "ja_JP:ブルトニウムのニワトリ" });
        allChickens.add(blutoniumChicken);

        ludicriteChicken = addChicken(
            "LudicriteChicken",
            this.nextID(),
            "LudicriteChicken.png",
            this.getFirstOreDictionary("ingotLudicrite"),
            0xC63BE5,
            0xF27CFF,
            SpawnType.NONE,
            new String[] { "en_US:Ludicrite Chicken", "ja_JP:ルディクライトのニワトリ" });
        allChickens.add(ludicriteChicken);

        return allChickens;
    }

    @Override
    public void registerAllParents(List<ChickensRegistryItem> allChickens) {
        setParents(yelloriumChicken, BaseChickens.glowstoneChicken, BaseChickens.enderChicken);

        setParents(graphiteChicken, BaseChickens.coalChicken, BaseChickens.blackChicken);

        setParents(cyaniteChicken, yelloriumChicken, BaseChickens.sandChicken);

        setParents(blutoniumChicken, cyaniteChicken, BaseChickens.waterChicken);

        setParents(ludicriteChicken, blutoniumChicken, BaseChickens.magmaChicken);

    }
}
