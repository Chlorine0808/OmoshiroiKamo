package ruiseki.omoshiroikamo.plugin.chicken;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

import ruiseki.omoshiroikamo.api.entity.SpawnType;
import ruiseki.omoshiroikamo.api.entity.chicken.ChickensRegistryItem;
import ruiseki.omoshiroikamo.api.enums.EnumDye;
import ruiseki.omoshiroikamo.common.init.ModItems;
import ruiseki.omoshiroikamo.common.util.lib.LibMisc;
import ruiseki.omoshiroikamo.common.util.lib.LibMods;

public class BaseChickens extends BaseChickenHandler {

    public static ChickensRegistryItem smartChicken;

    public static ChickensRegistryItem whiteChicken;
    public static ChickensRegistryItem yellowChicken;
    public static ChickensRegistryItem blueChicken;
    public static ChickensRegistryItem greenChicken;
    public static ChickensRegistryItem redChicken;
    public static ChickensRegistryItem blackChicken;

    public static ChickensRegistryItem pinkChicken;
    public static ChickensRegistryItem purpleChicken;
    public static ChickensRegistryItem orangeChicken;
    public static ChickensRegistryItem lightBlueChicken;
    public static ChickensRegistryItem limeChicken;
    public static ChickensRegistryItem grayChicken;
    public static ChickensRegistryItem cyanChicken;
    public static ChickensRegistryItem silverChicken;
    public static ChickensRegistryItem magentaChicken;

    public static ChickensRegistryItem flintChicken;
    public static ChickensRegistryItem quartzChicken;
    public static ChickensRegistryItem logChicken;
    public static ChickensRegistryItem sandChicken;

    public static ChickensRegistryItem stringChicken;
    public static ChickensRegistryItem glowstoneChicken;
    public static ChickensRegistryItem gunpowderChicken;
    public static ChickensRegistryItem redstoneChicken;
    public static ChickensRegistryItem glassChicken;
    public static ChickensRegistryItem ironChicken;
    public static ChickensRegistryItem coalChicken;
    public static ChickensRegistryItem brownChicken;

    public static ChickensRegistryItem goldChicken;
    public static ChickensRegistryItem snowballChicken;
    public static ChickensRegistryItem waterChicken;
    public static ChickensRegistryItem lavaChicken;
    public static ChickensRegistryItem clayChicken;
    public static ChickensRegistryItem leatherChicken;
    public static ChickensRegistryItem netherwartChicken;

    public static ChickensRegistryItem diamondChicken;
    public static ChickensRegistryItem blazeChicken;
    public static ChickensRegistryItem slimeChicken;

    public static ChickensRegistryItem enderChicken;
    public static ChickensRegistryItem ghastChicken;
    public static ChickensRegistryItem emeraldChicken;
    public static ChickensRegistryItem magmaChicken;

    public static ChickensRegistryItem xpChicken;
    public static ChickensRegistryItem pShardChicken;
    public static ChickensRegistryItem pCrystalChicken;
    public static ChickensRegistryItem soulsandChicken;
    public static ChickensRegistryItem obsidianChicken;

    public BaseChickens() {
        super("Base", "Base", "textures/entity/chicken/base/");
        this.setNeedsModPresent(false);
        this.setStartID(0);
    }

    @Override
    public List<ChickensRegistryItem> registerChickens() {
        List<ChickensRegistryItem> allChickens = new ArrayList<>();
        // DYE CHICKENS
        whiteChicken = addDye(EnumDye.WHITE, "WhiteChicken", new String[] { "en_US:White Chicken", "ja_JP:白いニワトリ" })
            .setDropItem(new ItemStack(Items.bone))
            .setSpawnType(SpawnType.NORMAL);
        allChickens.add(whiteChicken);

        yellowChicken = addDye(
            EnumDye.YELLOW,
            "YellowChicken",
            new String[] { "en_US:Yellow Chicken", "ja_JP:黄色いニワトリ" });
        allChickens.add(yellowChicken);

        blueChicken = addDye(EnumDye.BLUE, "BlueChicken", new String[] { "en_US:Blue Chicken", "ja_JP:青いニワトリ" });
        allChickens.add(blueChicken);

        greenChicken = addDye(EnumDye.GREEN, "GreenChicken", new String[] { "en_US:Green Chicken", "ja_JP:緑のニワトリ" });
        allChickens.add(greenChicken);

        redChicken = addDye(EnumDye.RED, "RedChicken", new String[] { "en_US:Red Chicken", "ja_JP:赤いニワトリ" });
        allChickens.add(redChicken);

        blackChicken = addDye(EnumDye.BLACK, "BlackChicken", new String[] { "en_US:Black Chicken", "ja_JP:黒いニワトリ" });
        allChickens.add(blackChicken);

        pinkChicken = addDye(EnumDye.PINK, "PinkChicken", new String[] { "en_US:Pink Chicken", "ja_JP:ピンクのニワトリ" });
        allChickens.add(pinkChicken);

        purpleChicken = addDye(
            EnumDye.PURPLE,
            "PurpleChicken",
            new String[] { "en_US:Purple Chicken", "ja_JP:紫のニワトリ" });
        allChickens.add(purpleChicken);

        orangeChicken = addDye(
            EnumDye.ORANGE,
            "OrangeChicken",
            new String[] { "en_US:Orange Chicken", "ja_JP:オレンジのニワトリ" });
        allChickens.add(orangeChicken);

        lightBlueChicken = addDye(
            EnumDye.LIGHT_BLUE,
            "LightBlueChicken",
            new String[] { "en_US:Light Blue Chicken", "ja_JP:水色のニワトリ" });
        allChickens.add(lightBlueChicken);

        limeChicken = addDye(EnumDye.LIME, "LimeChicken", new String[] { "en_US:Lime Chicken", "ja_JP:黄緑のニワトリ" });
        allChickens.add(limeChicken);

        grayChicken = addDye(EnumDye.GRAY, "GrayChicken", new String[] { "en_US:Gray Chicken", "ja_JP:灰色のニワトリ" });
        allChickens.add(grayChicken);

        cyanChicken = addDye(EnumDye.CYAN, "CyanChicken", new String[] { "en_US:Cyan Chicken", "ja_JP:シアンのニワトリ" });
        allChickens.add(cyanChicken);

        silverChicken = addDye(
            EnumDye.SILVER,
            "SilverDyeChicken",
            new String[] { "en_US:Silver Chicken", "ja_JP:薄灰色のニワトリ" });
        allChickens.add(silverChicken);

        magentaChicken = addDye(
            EnumDye.MAGENTA,
            "MagentaChicken",
            new String[] { "en_US:Magenta Chicken", "ja_JP:マゼンタのニワトリ" });
        allChickens.add(magentaChicken);

        brownChicken = addDye(EnumDye.BROWN, "BrownChicken", new String[] { "en_US:Brown Chicken", "ja_JP:茶色のニワトリ" });
        allChickens.add(brownChicken);

        // SMART CHICKEN
        smartChicken = addChicken(
            "SmartChicken",
            this.nextID(),
            "SmartChicken.png",
            new ItemStack(Items.egg),
            0xffffff,
            0xffff00,
            SpawnType.NONE,
            new String[] { "en_US:Smart Chicken", "ja_JP:スマートニワトリ" });
        allChickens.add(smartChicken);

        // BASE CHICKENS
        flintChicken = addChicken(
            "FlintChicken",
            this.nextID(),
            "FlintChicken.png",
            new ItemStack(Items.flint),
            0x6b6b47,
            0xa3a375,
            SpawnType.NONE,
            new String[] { "en_US:Flint Chicken", "ja_JP:火打石のニワトリ" });
        allChickens.add(flintChicken);

        quartzChicken = addChicken(
            "QuartzChicken",
            this.nextID(),
            "QuartzChicken.png",
            new ItemStack(Items.quartz),
            0x4d0000,
            0x1a0000,
            SpawnType.HELL,
            new String[] { "en_US:Quartz Chicken", "ja_JP:ネザークォーツのニワトリ" });
        allChickens.add(quartzChicken);

        logChicken = addChicken(
            "LogChicken",
            this.nextID(),
            "LogChicken.png",
            new ItemStack(Blocks.log),
            0x98846d,
            0x528358,
            SpawnType.NONE,
            new String[] { "en_US:Log Chicken", "ja_JP:原木のニワトリ" });
        allChickens.add(logChicken);

        sandChicken = addChicken(
            "SandChicken",
            this.nextID(),
            "SandChicken.png",
            new ItemStack(Blocks.sand),
            0xece5b1,
            0xa7a06c,
            SpawnType.NONE,
            new String[] { "en_US:Sand Chicken", "ja_JP:砂のニワトリ" });
        allChickens.add(sandChicken);

        // TIER 2
        stringChicken = addChicken(
            "StringChicken",
            this.nextID(),
            "StringChicken.png",
            new ItemStack(Items.string),
            0x331a00,
            0x800000,
            SpawnType.NONE,
            new String[] { "en_US:String Chicken", "ja_JP:糸のニワトリ" }).setDropItem(new ItemStack(Items.spider_eye));
        allChickens.add(stringChicken);

        glowstoneChicken = addChicken(
            "GlowstoneChicken",
            this.nextID(),
            "GlowstoneChicken.png",
            new ItemStack(Items.glowstone_dust),
            0xffff66,
            0xffff00,
            SpawnType.NONE,
            new String[] { "en_US:Glowstone Chicken", "ja_JP:グロウストーンのニワトリ" });
        allChickens.add(glowstoneChicken);

        gunpowderChicken = addChicken(
            "GunpowderChicken",
            this.nextID(),
            "GunpowderChicken.png",
            new ItemStack(Items.gunpowder),
            0x999999,
            0x404040,
            SpawnType.NONE,
            new String[] { "en_US:Gunpowder Chicken", "ja_JP:火薬のニワトリ" });
        allChickens.add(gunpowderChicken);

        redstoneChicken = addChicken(
            "RedstoneChicken",
            this.nextID(),
            "RedstoneChicken.png",
            new ItemStack(Items.redstone),
            0xe60000,
            0x800000,
            SpawnType.NONE,
            new String[] { "en_US:Redstone Chicken", "ja_JP:レッドストーンのニワトリ" });
        allChickens.add(redstoneChicken);

        glassChicken = addChicken(
            "GlassChicken",
            this.nextID(),
            "GlassChicken.png",
            new ItemStack(Blocks.glass),
            0xffffff,
            0xeeeeff,
            SpawnType.NONE,
            new String[] { "en_US:Glass Chicken", "ja_JP:ガラスのニワトリ" });
        allChickens.add(glassChicken);

        ironChicken = addChicken(
            "IronChicken",
            this.nextID(),
            "IronChicken.png",
            new ItemStack(Items.iron_ingot),
            0xffffcc,
            0xffcccc,
            SpawnType.NONE,
            new String[] { "en_US:Iron Chicken", "ja_JP:鉄のニワトリ" });
        allChickens.add(ironChicken);

        coalChicken = addChicken(
            "CoalChicken",
            this.nextID(),
            "CoalChicken.png",
            new ItemStack(Items.coal),
            0x262626,
            0x000000,
            SpawnType.NONE,
            new String[] { "en_US:Coal Chicken", "ja_JP:石炭のニワトリ" });
        allChickens.add(coalChicken);

        // TIER 3
        goldChicken = addChicken(
            "GoldChicken",
            this.nextID(),
            "GoldChicken.png",
            new ItemStack(Items.gold_ingot),
            0xcccc00,
            0xffff80,
            SpawnType.NONE,
            new String[] { "en_US:Gold Chicken", "ja_JP:金のニワトリ" });
        allChickens.add(goldChicken);

        snowballChicken = addChicken(
            "SnowballChicken",
            this.nextID(),
            "SnowballChicken.png",
            new ItemStack(Items.snowball),
            0x33bbff,
            0x0088cc,
            SpawnType.SNOW,
            new String[] { "en_US:Snowball Chicken", "ja_JP:雪玉のニワトリ" });
        allChickens.add(snowballChicken);

        waterChicken = addChicken(
            "WaterChicken",
            this.nextID(),
            "WaterChicken.png",
            ModItems.LIQUID_EGG.newItemStack(1, 0),
            0x000099,
            0x8080ff,
            SpawnType.NONE,
            new String[] { "en_US:Water Chicken", "ja_JP:水のニワトリ" });
        allChickens.add(waterChicken);

        lavaChicken = addChicken(
            "LavaChicken",
            this.nextID(),
            "LavaChicken.png",
            ModItems.LIQUID_EGG.newItemStack(1, 1),
            0xcc3300,
            0xffff00,
            SpawnType.HELL,
            new String[] { "en_US:Lava Chicken", "ja_JP:溶岩のニワトリ" });
        allChickens.add(lavaChicken);

        clayChicken = addChicken(
            "ClayChicken",
            this.nextID(),
            "ClayChicken.png",
            new ItemStack(Items.clay_ball),
            0xcccccc,
            0xbfbfbf,
            SpawnType.NONE,
            new String[] { "en_US:Clay Chicken", "ja_JP:粘土のニワトリ" });
        allChickens.add(clayChicken);

        leatherChicken = addChicken(
            "LeatherChicken",
            this.nextID(),
            "LeatherChicken.png",
            new ItemStack(Items.leather),
            0xA7A06C,
            0x919191,
            SpawnType.NONE,
            new String[] { "en_US:Leather Chicken", "ja_JP:革のニワトリ" });
        allChickens.add(leatherChicken);

        netherwartChicken = addChicken(
            "NetherwartChicken",
            this.nextID(),
            "NetherwartChicken.png",
            new ItemStack(Items.nether_wart),
            0x800000,
            0x331a00,
            SpawnType.NONE,
            new String[] { "en_US:Nether Wart Chicken", "ja_JP:ネザーウォートのニワトリ" });
        allChickens.add(netherwartChicken);

        // TIER 4
        diamondChicken = addChicken(
            "DiamondChicken",
            this.nextID(),
            "DiamondChicken.png",
            new ItemStack(Items.diamond),
            0x99ccff,
            0xe6f2ff,
            SpawnType.NONE,
            new String[] { "en_US:Diamond Chicken", "ja_JP:ダイヤモンドのニワトリ" });
        allChickens.add(diamondChicken);

        blazeChicken = addChicken(
            "BlazeChicken",
            this.nextID(),
            "BlazeChicken.png",
            new ItemStack(Items.blaze_rod),
            0xffff66,
            0xff3300,
            SpawnType.NONE,
            new String[] { "en_US:Blaze Chicken", "ja_JP:ブレイズのニワトリ" });
        allChickens.add(blazeChicken);

        slimeChicken = addChicken(
            "SlimeChicken",
            this.nextID(),
            "SlimeChicken.png",
            new ItemStack(Items.slime_ball),
            0x009933,
            0x99ffbb,
            SpawnType.NONE,
            new String[] { "en_US:Slime Chicken", "ja_JP:スライムのニワトリ" });
        allChickens.add(slimeChicken);

        // TIER 5
        enderChicken = addChicken(
            "EnderChicken",
            this.nextID(),
            "EnderChicken.png",
            new ItemStack(Items.ender_pearl),
            0x001a00,
            0x001a33,
            SpawnType.NONE,
            new String[] { "en_US:Ender Chicken", "ja_JP:エンダーのニワトリ" });
        allChickens.add(enderChicken);

        ghastChicken = addChicken(
            "GhastChicken",
            this.nextID(),
            "GhastChicken.png",
            new ItemStack(Items.ghast_tear),
            0xffffcc,
            0xffffff,
            SpawnType.NONE,
            new String[] { "en_US:Ghast Chicken", "ja_JP:ガストのニワトリ" });
        allChickens.add(ghastChicken);

        emeraldChicken = addChicken(
            "EmeraldChicken",
            this.nextID(),
            "EmeraldChicken.png",
            new ItemStack(Items.emerald),
            0x00cc00,
            0x003300,
            SpawnType.NONE,
            new String[] { "en_US:Emerald Chicken", "ja_JP:エメラルドのニワトリ" });
        allChickens.add(emeraldChicken);

        magmaChicken = addChicken(
            "MagmaChicken",
            this.nextID(),
            "MagmaChicken.png",
            new ItemStack(Items.magma_cream),
            0x1a0500,
            0x000000,
            SpawnType.NONE,
            new String[] { "en_US:Magma Chicken", "ja_JP:マグマのニワトリ" });
        allChickens.add(magmaChicken);

        xpChicken = addChicken(
            "XpChicken",
            this.nextID(),
            "XpChicken.png",
            new ItemStack(ModItems.SOLID_XP.getItem(), 1, 0),
            0x3dff1e,
            0x3ff123,
            SpawnType.NONE,
            new String[] { "en_US:XP Chicken", "ja_JP:経験値のニワトリ" });
        allChickens.add(xpChicken);

        if (LibMods.EtFuturum.isLoaded()) {
            pShardChicken = addChicken(
                "PShardChicken",
                this.nextID(),
                "PShardChicken.png",
                new ItemStack(ganymedes01.etfuturum.ModItems.PRISMARINE_SHARD.get(), 1, 0),
                0x43806e,
                0x9fcbbc,
                SpawnType.NONE,
                new String[] { "en_US:Prismarine Shard Chicken", "ja_JP:プリズマリンシャードのニワトリ" });
            allChickens.add(pShardChicken);

            pCrystalChicken = addChicken(
                "PCrystalChicken",
                this.nextID(),
                "PCrystalChicken.png",
                new ItemStack(ganymedes01.etfuturum.ModItems.PRISMARINE_CRYSTALS.get(), 1, 0),
                0x4e6961,
                0xdfe9dc,
                SpawnType.NONE,
                new String[] { "en_US:Prismarine Crystal Chicken", "ja_JP:プリズマリンクリスタルのニワトリ" });
            allChickens.add(pCrystalChicken);
        }

        soulsandChicken = addChicken(
            "SoulSandChicken",
            this.nextID(),
            "SoulSandChicken.png",
            new ItemStack(Blocks.soul_sand, 1, 0),
            0x453125,
            0xd52f08,
            SpawnType.HELL,
            new String[] { "en_US:Soul Sand Chicken", "ja_JP:ソウルサンドのニワトリ" });
        allChickens.add(soulsandChicken);

        obsidianChicken = addChicken(
            "ObsidianChicken",
            this.nextID(),
            "ObsidianChicken.png",
            new ItemStack(Blocks.obsidian, 1, 0),
            0x08080e,
            0x463a60,
            SpawnType.NONE,
            new String[] { "en_US:Obsidian Chicken", "ja_JP:黒曜石のニワトリ" });
        allChickens.add(obsidianChicken);

        return allChickens;
    }

    @Override
    public void registerAllParents(List<ChickensRegistryItem> all) {

        // === Dye ===
        setParents(pinkChicken, redChicken, whiteChicken);
        setParents(purpleChicken, blueChicken, redChicken);
        setParents(orangeChicken, redChicken, yellowChicken);
        setParents(lightBlueChicken, whiteChicken, blueChicken);
        setParents(limeChicken, greenChicken, whiteChicken);
        setParents(grayChicken, blackChicken, whiteChicken);
        setParents(cyanChicken, blueChicken, greenChicken);
        setParents(silverChicken, grayChicken, whiteChicken);
        setParents(magentaChicken, purpleChicken, pinkChicken);

        // === T2 ===
        setParents(stringChicken, blackChicken, logChicken);
        setParents(glowstoneChicken, quartzChicken, yellowChicken);
        setParents(gunpowderChicken, sandChicken, flintChicken);
        setParents(redstoneChicken, redChicken, sandChicken);
        setParents(glassChicken, quartzChicken, redstoneChicken);
        setParents(ironChicken, flintChicken, whiteChicken);
        setParents(coalChicken, flintChicken, logChicken);
        setParents(brownChicken, redChicken, greenChicken);

        // === T3 ===
        setParents(goldChicken, ironChicken, yellowChicken);
        setParents(snowballChicken, blueChicken, logChicken);
        setParents(waterChicken, gunpowderChicken, snowballChicken);
        setParents(lavaChicken, coalChicken, quartzChicken);
        setParents(clayChicken, snowballChicken, sandChicken);
        setParents(leatherChicken, stringChicken, brownChicken);
        setParents(netherwartChicken, brownChicken, glowstoneChicken);

        // === T4 ===
        setParents(diamondChicken, glassChicken, goldChicken);
        setParents(blazeChicken, goldChicken, lavaChicken);
        setParents(slimeChicken, clayChicken, greenChicken);

        // === T5 ===
        setParents(enderChicken, diamondChicken, netherwartChicken);
        setParents(ghastChicken, whiteChicken, blazeChicken);
        setParents(emeraldChicken, diamondChicken, greenChicken);
        setParents(magmaChicken, slimeChicken, blazeChicken);

        // extra
        setParents(xpChicken, emeraldChicken, greenChicken);
        setParents(obsidianChicken, waterChicken, lavaChicken);
        setParents(pShardChicken, waterChicken, blueChicken);
        setParents(pCrystalChicken, waterChicken, emeraldChicken);
    }

    private ChickensRegistryItem addDye(EnumDye color, String name, String[] lang) {
        return new ChickensRegistryItem(
            this.nextID(),
            name,
            new ResourceLocation(LibMisc.MOD_ID, texturesLocation + name + ".png"),
            new ItemStack(Items.dye, 1, color.ordinal()),
            0xf2f2f2,
            color.getColor(),
            lang).setSpawnType(SpawnType.NONE);
    }

}
