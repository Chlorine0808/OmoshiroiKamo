package ruiseki.omoshiroikamo.plugin.cow;

import java.util.ArrayList;
import java.util.List;

import ruiseki.omoshiroikamo.api.entity.SpawnType;
import ruiseki.omoshiroikamo.api.entity.cow.CowsRegistryItem;

public class TinkersCows extends BaseCowHandler {

    public static CowsRegistryItem ironCow;
    public static CowsRegistryItem goldCow;
    public static CowsRegistryItem copperCow;
    public static CowsRegistryItem tinCow;
    public static CowsRegistryItem aluminiumCow;
    public static CowsRegistryItem cobaltCow;
    public static CowsRegistryItem arditeCow;
    public static CowsRegistryItem bronzeCow;
    public static CowsRegistryItem alubrassCow;
    public static CowsRegistryItem manyullynCow;
    public static CowsRegistryItem obsidianCow;
    public static CowsRegistryItem steelCow;
    public static CowsRegistryItem glassCow;
    public static CowsRegistryItem stoneCow;
    public static CowsRegistryItem emeraldCow;
    public static CowsRegistryItem nickelCow;
    public static CowsRegistryItem leadCow;
    public static CowsRegistryItem silverCow;
    public static CowsRegistryItem shinyCow;
    public static CowsRegistryItem invarCow;
    public static CowsRegistryItem electrumCow;
    public static CowsRegistryItem lumiumCow;
    public static CowsRegistryItem signalumCow;
    public static CowsRegistryItem mithrilCow;
    public static CowsRegistryItem enderiumCow;
    public static CowsRegistryItem pigironCow;

    public TinkersCows() {
        super("TConstruct", "Tinkers Construct", "textures/entity/cows/base/");
        this.setStartID(100);
    }

    @Override
    public List<CowsRegistryItem> registerCows() {
        List<CowsRegistryItem> allCows = new ArrayList<>();

        ironCow = tryAddCow(
            "IronCow",
            100,
            "iron.molten",
            0xb8b8b8,
            0xffffff,
            SpawnType.NORMAL,
            new String[] { "en_US:Iron Cow", "ja_JP:鉄の牛" });
        allCows.add(ironCow);

        goldCow = tryAddCow(
            "GoldCow",
            101,
            "gold.molten",
            0xffcc00,
            0xffff66,
            SpawnType.NORMAL,
            new String[] { "en_US:Gold Cow", "ja_JP:金の牛" });
        allCows.add(goldCow);

        copperCow = tryAddCow(
            "CopperCow",
            102,
            "copper.molten",
            0xcc6600,
            0xff9955,
            SpawnType.NORMAL,
            new String[] { "en_US:Copper Cow", "ja_JP:銅の牛" });
        allCows.add(copperCow);

        tinCow = tryAddCow(
            "TinCow",
            103,
            "tin.molten",
            0xccccff,
            0xffffff,
            SpawnType.NORMAL,
            new String[] { "en_US:Tin Cow", "ja_JP:錫の牛" });
        allCows.add(tinCow);

        aluminiumCow = tryAddCow(
            "AluminiumCow",
            104,
            "aluminum.molten",
            0xddeeff,
            0xffffff,
            SpawnType.NORMAL,
            new String[] { "en_US:Aluminium Cow", "ja_JP:アルミニウムの牛" });
        allCows.add(aluminiumCow);

        cobaltCow = tryAddCow(
            "CobaltCow",
            105,
            "cobalt.molten",
            0x0022ff,
            0x6688ff,
            SpawnType.HELL,
            new String[] { "en_US:Cobalt Cow", "ja_JP:コバルトの牛" });
        allCows.add(cobaltCow);

        arditeCow = tryAddCow(
            "ArditeCow",
            106,
            "ardite.molten",
            0xff6600,
            0xffaa55,
            SpawnType.HELL,
            new String[] { "en_US:Ardite Cow", "ja_JP:アルダイトの牛" });
        allCows.add(arditeCow);

        bronzeCow = tryAddCow(
            "BronzeCow",
            107,
            "bronze.molten",
            0xcc8844,
            0xffcc99,
            SpawnType.NORMAL,
            new String[] { "en_US:Bronze Cow", "ja_JP:青銅の牛" });
        allCows.add(bronzeCow);

        alubrassCow = tryAddCow(
            "AlubrassCow",
            108,
            "aluminumbrass.molten",
            0xd4b55c,
            0xffe099,
            SpawnType.NORMAL,
            new String[] { "en_US:Alubrass Cow", "ja_JP:アルミニウム黄銅の牛" });
        allCows.add(alubrassCow);

        manyullynCow = tryAddCow(
            "ManyullynCow",
            109,
            "manyullyn.molten",
            0x550088,
            0xaa66ff,
            SpawnType.HELL,
            new String[] { "en_US:Manyullyn Cow", "ja_JP:マニュリンの牛" });
        allCows.add(manyullynCow);

        obsidianCow = tryAddCow(
            "ObsidianCow",
            110,
            "obsidian.molten",
            0x1a0f33,
            0x3d2a66,
            SpawnType.NORMAL,
            new String[] { "en_US:Obsidian Cow", "ja_JP:黒曜石の牛" });
        allCows.add(obsidianCow);

        steelCow = tryAddCow(
            "SteelCow",
            111,
            "steel.molten",
            0x555555,
            0xaaaaaa,
            SpawnType.NORMAL,
            new String[] { "en_US:Steel Cow", "ja_JP:鋼の牛" });
        allCows.add(steelCow);

        glassCow = tryAddCow(
            "GlassCow",
            112,
            "glass.molten",
            0xffffff,
            0xddeeff,
            SpawnType.NORMAL,
            new String[] { "en_US:Glass Cow", "ja_JP:ガラスの牛" });
        allCows.add(glassCow);

        stoneCow = tryAddCow(
            "StoneCow",
            113,
            "stone.seared",
            0x888888,
            0xbbbbbb,
            SpawnType.NORMAL,
            new String[] { "en_US:Stone Cow", "ja_JP:石の牛" });
        allCows.add(stoneCow);

        emeraldCow = tryAddCow(
            "EmeraldCow",
            114,
            "emerald.liquid",
            0x00cc66,
            0x66ffaa,
            SpawnType.NORMAL,
            new String[] { "en_US:Emerald Cow", "ja_JP:エメラルドの牛" });
        allCows.add(emeraldCow);

        nickelCow = tryAddCow(
            "NickelCow",
            115,
            "nickel.molten",
            0xcccc99,
            0xffffcc,
            SpawnType.NORMAL,
            new String[] { "en_US:Nickel Cow", "ja_JP:ニッケルの牛" });
        allCows.add(nickelCow);

        leadCow = tryAddCow(
            "LeadCow",
            116,
            "lead.molten",
            0x333366,
            0x666699,
            SpawnType.NORMAL,
            new String[] { "en_US:Lead Cow", "ja_JP:鉛の牛" });
        allCows.add(leadCow);

        silverCow = tryAddCow(
            "SilverCow",
            117,
            "silver.molten",
            0xcceeff,
            0xffffff,
            SpawnType.NORMAL,
            new String[] { "en_US:Silver Cow", "ja_JP:銀の牛" });
        allCows.add(silverCow);

        shinyCow = tryAddCow(
            "ShinyCow",
            118,
            "platinum.molten",
            0xe6ffff,
            0xffffff,
            SpawnType.NORMAL,
            new String[] { "en_US:Shiny Cow", "ja_JP:白金の牛" });
        allCows.add(shinyCow);

        invarCow = tryAddCow(
            "InvarCow",
            119,
            "invar.molten",
            0x99997a,
            0xccccaa,
            SpawnType.NORMAL,
            new String[] { "en_US:Invar Cow", "ja_JP:インバーの牛" });
        allCows.add(invarCow);

        electrumCow = tryAddCow(
            "ElectrumCow",
            120,
            "electrum.molten",
            0xfff2a1,
            0xffffd6,
            SpawnType.NORMAL,
            new String[] { "en_US:Electrum Cow", "ja_JP:エレクトラムの牛" });
        allCows.add(electrumCow);

        lumiumCow = tryAddCow(
            "LumiumCow",
            121,
            "lumium.molten",
            0xffffcc,
            0xffffff,
            SpawnType.NORMAL,
            new String[] { "en_US:Lumium Cow", "ja_JP:ルミウムの牛" });
        allCows.add(lumiumCow);

        signalumCow = tryAddCow(
            "SignalumCow",
            122,
            "signalum.molten",
            0xcc3300,
            0xff6644,
            SpawnType.NORMAL,
            new String[] { "en_US:Signalum Cow", "ja_JP:シグナラムの牛" });
        allCows.add(signalumCow);

        mithrilCow = tryAddCow(
            "MithrilCow",
            123,
            "mithril.molten",
            0x99ccff,
            0xccffff,
            SpawnType.NORMAL,
            new String[] { "en_US:Mithril Cow", "ja_JP:ミスリルの牛" });
        allCows.add(mithrilCow);

        enderiumCow = tryAddCow(
            "EnderiumCow",
            124,
            "enderium.molten",
            0x006666,
            0x33cccc,
            SpawnType.HELL,
            new String[] { "en_US:Enderium Cow", "ja_JP:エンデリウムの牛" });
        allCows.add(enderiumCow);

        pigironCow = tryAddCow(
            "PigIronCow",
            125,
            "pigiron.molten",
            0xff99aa,
            0xffccd5,
            SpawnType.NORMAL,
            new String[] { "en_US:Pig Iron Cow", "ja_JP:ピッグアイアンの牛" });
        allCows.add(pigironCow);

        return allCows;
    }
}
