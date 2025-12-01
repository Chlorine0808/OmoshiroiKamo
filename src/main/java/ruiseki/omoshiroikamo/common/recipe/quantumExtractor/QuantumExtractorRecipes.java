package ruiseki.omoshiroikamo.common.recipe.quantumExtractor;

import java.io.File;

import ruiseki.omoshiroikamo.api.enums.EnumDye;
import ruiseki.omoshiroikamo.api.item.weighted.IFocusableRegistry;
import ruiseki.omoshiroikamo.common.util.lib.LibMisc;
import ruiseki.omoshiroikamo.common.util.lib.LibResources;
import ruiseki.omoshiroikamo.config.backport.BackportConfigs;

public class QuantumExtractorRecipes {

    public static final int MAX_TIER = 6;

    public static IFocusableRegistry[] oreRegistry = new IFocusableRegistry[MAX_TIER];
    public static IFocusableRegistry[] resRegistry = new IFocusableRegistry[MAX_TIER];

    public static void init() {
        if (!BackportConfigs.useEnvironmentalTech) return;

        for (int i = 0; i < MAX_TIER; i++) {
            oreRegistry[i] = new FocusableRegistry();
            resRegistry[i] = new FocusableRegistry();
        }

        for (int t = 0; t < MAX_TIER; t++) {
            loadOreTier(t);
            loadResTier(t);
        }
    }

    private static void loadOreTier(int tier) {
        IFocusableRegistry reg = oreRegistry[tier];
        File file = new File("config/" + LibMisc.MOD_ID + "/quantumExtractor/ore_t" + (tier + 1) + ".json");

        if (file.exists()) {
            FocusableHandler.loadRegistryFromJson(file, reg);
        } else {
            FocusableHandler.FocusableList defaults = getDefaultOreList(tier);
            FocusableHandler.saveRegistryDefaultsToJson(file, defaults);
            FocusableHandler.loadIntoRegistry(defaults, reg);
        }
    }

    private static void loadResTier(int tier) {
        IFocusableRegistry reg = resRegistry[tier];
        File file = new File("config/" + LibMisc.MOD_ID + "/quantumExtractor/res_t" + (tier + 1) + ".json");

        if (file.exists()) {
            FocusableHandler.loadRegistryFromJson(file, reg);
        } else {
            FocusableHandler.FocusableList defaults = getDefaultResList(tier);
            FocusableHandler.saveRegistryDefaultsToJson(file, defaults);
            FocusableHandler.loadIntoRegistry(defaults, reg);
        }
    }

    private static FocusableHandler.FocusableList getDefaultOreList(int tier) {
        FocusableHandler.FocusableList defaults = new FocusableHandler.FocusableList();
        defaults.addEntry(new FocusableHandler.FocusableOre("oreCoal", EnumDye.BLACK, 1000));
        defaults.addEntry(new FocusableHandler.FocusableOre("oreIron", EnumDye.WHITE, 750));
        defaults.addEntry(new FocusableHandler.FocusableOre("oreQuartz", EnumDye.WHITE, 560));
        defaults.addEntry(new FocusableHandler.FocusableOre("oreRedstone", EnumDye.RED, 515));
        defaults.addEntry(new FocusableHandler.FocusableOre("oreLapis", EnumDye.BLUE, 343));
        defaults.addEntry(new FocusableHandler.FocusableOre("oreGold", EnumDye.YELLOW, 311));
        defaults.addEntry(new FocusableHandler.FocusableOre("oreDiamond", EnumDye.CYAN, 218));
        defaults.addEntry(new FocusableHandler.FocusableOre("oreEmerald", EnumDye.LIME, 156));
        defaults.addEntry(new FocusableHandler.FocusableOre("glowstone", EnumDye.YELLOW, 234));
        defaults.addEntry(new FocusableHandler.FocusableOre("oreCopper", EnumDye.ORANGE, 584));
        defaults.addEntry(new FocusableHandler.FocusableOre("oreTin", EnumDye.GRAY, 602));
        defaults.addEntry(new FocusableHandler.FocusableOre("oreSilver", EnumDye.SILVER, 381));
        defaults.addEntry(new FocusableHandler.FocusableOre("oreLead", EnumDye.PURPLE, 500));
        defaults.addEntry(new FocusableHandler.FocusableOre("oreAluminum", EnumDye.WHITE, 422));
        defaults.addEntry(new FocusableHandler.FocusableOre("oreUranium", EnumDye.GREEN, 140));
        defaults.addEntry(new FocusableHandler.FocusableOre("oreYellorite", EnumDye.YELLOW, 156));
        defaults.addEntry(new FocusableHandler.FocusableOre("oreCertusQuartz", EnumDye.LIGHT_BLUE, 187));
        defaults.addEntry(new FocusableHandler.FocusableOre("oreChargedCertusQuartz", EnumDye.LIGHT_BLUE, 109));
        defaults.addEntry(new FocusableHandler.FocusableOre("oreCinnabar", EnumDye.BROWN, 190));
        defaults.addEntry(new FocusableHandler.FocusableOre("oreAmber", EnumDye.ORANGE, 184));
        defaults.addEntry(new FocusableHandler.FocusableOre("oreNickel", EnumDye.GRAY, 232));
        defaults.addEntry(new FocusableHandler.FocusableOre("orePlatinum", EnumDye.LIGHT_BLUE, 150));
        defaults.addEntry(new FocusableHandler.FocusableOre("oreMithril", EnumDye.LIGHT_BLUE, 169));
        defaults.addEntry(new FocusableHandler.FocusableOre("oreClathrateOilSand", EnumDye.BLACK, 128));
        defaults.addEntry(new FocusableHandler.FocusableOre("oreClathrateOilShale", EnumDye.BLACK, 120));
        defaults.addEntry(new FocusableHandler.FocusableOre("oreClathrateEnder", EnumDye.GREEN, 118));
        defaults.addEntry(new FocusableHandler.FocusableOre("oreClathrateGlowstone", EnumDye.YELLOW, 145));
        defaults.addEntry(new FocusableHandler.FocusableOre("oreClathrateRedstone", EnumDye.RED, 137));
        defaults.addEntry(new FocusableHandler.FocusableOre("oreSulfur", EnumDye.YELLOW, 222));
        defaults.addEntry(new FocusableHandler.FocusableOre("oreNiter", EnumDye.WHITE, 244));
        defaults.addEntry(new FocusableHandler.FocusableOre("oreFirestone", EnumDye.RED, 113));
        defaults.addEntry(new FocusableHandler.FocusableOre("oreCobalt", EnumDye.BLUE, 163));
        defaults.addEntry(new FocusableHandler.FocusableOre("oreArdite", EnumDye.ORANGE, 159));
        defaults.addEntry(new FocusableHandler.FocusableOre("oreApatite", EnumDye.LIGHT_BLUE, 200));
        defaults.addEntry(new FocusableHandler.FocusableOre("oreDraconium", EnumDye.PURPLE, 142));
        defaults.addEntry(new FocusableHandler.FocusableOre("oreGraphite", EnumDye.BLACK, 190));
        defaults.addEntry(new FocusableHandler.FocusableOre("oreQuartzBlack", EnumDye.BLACK, 290));
        defaults.addEntry(new FocusableHandler.FocusableOre("oreMagnesium", EnumDye.WHITE, 233));
        defaults.addEntry(new FocusableHandler.FocusableOre("oreLithium", EnumDye.WHITE, 201));
        defaults.addEntry(new FocusableHandler.FocusableOre("oreThorium", EnumDye.BLACK, 222));
        defaults.addEntry(new FocusableHandler.FocusableOre("oreBoron", EnumDye.SILVER, 199));
        defaults.addEntry(new FocusableHandler.FocusableOre("oreResonating", EnumDye.BROWN, 177));
        defaults.addEntry(new FocusableHandler.FocusableOre("oreZinc", EnumDye.PINK, 186));
        defaults.addEntry(new FocusableHandler.FocusableOre("oreIridium", EnumDye.WHITE, 157));
        defaults.addEntry(new FocusableHandler.FocusableOre("oreTungsten", EnumDye.BLACK, 192));
        defaults.addEntry(new FocusableHandler.FocusableOre("oreOsmium", EnumDye.LIGHT_BLUE, 251));
        defaults.addEntry(new FocusableHandler.FocusableOre("oreSalt", EnumDye.WHITE, 160));
        defaults.addEntry(new FocusableHandler.FocusableBlock("rftools:dimensional_shard_ore", 0, EnumDye.WHITE, 127));
        // defaults.addEntry(new FocusableHandler.FocusableBlock("mysticalagriculture:inferium_ore", 0, EnumDye.GREEN,
        // 190));
        // defaults.addEntry(new FocusableHandler.FocusableBlock("mysticalagriculture:prosperity_ore", 0,
        // EnumDye.SILVER, 155));

        defaults.addEntry(new FocusableHandler.FocusableOre("oreBrainstone", EnumDye.LIME, 223));
        defaults.addEntry(new FocusableHandler.FocusableOre("oreTitaniumIron", EnumDye.WHITE, 223));
        defaults.addEntry(new FocusableHandler.FocusableOre("oreSilicon", EnumDye.PURPLE, 333));
        defaults.addEntry(new FocusableHandler.FocusableOre("oreRuby", EnumDye.RED, 200));
        defaults.addEntry(new FocusableHandler.FocusableOre("orePeridot", EnumDye.GREEN, 200));
        defaults.addEntry(new FocusableHandler.FocusableOre("oreTopaz", EnumDye.ORANGE, 200));
        defaults.addEntry(new FocusableHandler.FocusableOre("oreTanzanite", EnumDye.PURPLE, 200));
        defaults.addEntry(new FocusableHandler.FocusableOre("oreMalachite", EnumDye.CYAN, 200));
        defaults.addEntry(new FocusableHandler.FocusableOre("oreSapphire", EnumDye.BLUE, 200));
        defaults.addEntry(new FocusableHandler.FocusableOre("oreGarnet", EnumDye.ORANGE, 200));
        defaults.addEntry(new FocusableHandler.FocusableOre("oreHeliodore", EnumDye.YELLOW, 200));
        defaults.addEntry(new FocusableHandler.FocusableOre("oreBeryl", EnumDye.GREEN, 200));
        defaults.addEntry(new FocusableHandler.FocusableOre("oreIndicolite", EnumDye.GREEN, 200));
        defaults.addEntry(new FocusableHandler.FocusableOre("oreAquamarine", EnumDye.BLUE, 200));
        defaults.addEntry(new FocusableHandler.FocusableOre("oreIolite", EnumDye.PURPLE, 200));
        defaults.addEntry(new FocusableHandler.FocusableOre("oreAmethyst", EnumDye.PURPLE, 200));
        defaults.addEntry(new FocusableHandler.FocusableOre("oreAgate", EnumDye.PINK, 200));
        defaults.addEntry(new FocusableHandler.FocusableOre("oreMorganite", EnumDye.PINK, 200));
        defaults.addEntry(new FocusableHandler.FocusableOre("oreOnyx", EnumDye.BLACK, 200));
        defaults.addEntry(new FocusableHandler.FocusableOre("oreOpal", EnumDye.WHITE, 200));
        defaults.addEntry(new FocusableHandler.FocusableOre("oreCarnelian", EnumDye.RED, 200));
        defaults.addEntry(new FocusableHandler.FocusableOre("oreSpinel", EnumDye.BROWN, 200));
        defaults.addEntry(new FocusableHandler.FocusableOre("oreCitrine", EnumDye.BROWN, 200));
        defaults.addEntry(new FocusableHandler.FocusableOre("oreJasper", EnumDye.YELLOW, 200));
        defaults.addEntry(new FocusableHandler.FocusableOre("oreGoldenBeryl", EnumDye.YELLOW, 200));
        defaults.addEntry(new FocusableHandler.FocusableOre("oreMoldavite", EnumDye.GREEN, 200));
        defaults.addEntry(new FocusableHandler.FocusableOre("oreTurquoise", EnumDye.CYAN, 200));
        defaults.addEntry(new FocusableHandler.FocusableOre("oreMoonstone", EnumDye.CYAN, 200));
        defaults.addEntry(new FocusableHandler.FocusableOre("oreBlueTopaz", EnumDye.BLUE, 200));
        defaults.addEntry(new FocusableHandler.FocusableOre("oreVioletSapphire", EnumDye.PURPLE, 200));
        defaults.addEntry(new FocusableHandler.FocusableOre("oreLepidolite", EnumDye.PURPLE, 200));
        defaults.addEntry(new FocusableHandler.FocusableOre("oreAmetrine", EnumDye.PURPLE, 200));
        defaults.addEntry(new FocusableHandler.FocusableOre("oreBlackDiamond", EnumDye.BLACK, 200));
        defaults.addEntry(new FocusableHandler.FocusableOre("oreAlexandrite", EnumDye.WHITE, 200));
        defaults.addEntry(new FocusableHandler.FocusableOre("oreChaos", EnumDye.WHITE, 200));
        defaults.addEntry(new FocusableHandler.FocusableOre("oreEnderEssence", EnumDye.GREEN, 200));

        // --- Crystals ---
        int[] crystalWeights;
        switch (tier) {
            case 0:
                crystalWeights = new int[] { 200, 150 };
                break;
            case 1:
                crystalWeights = new int[] { 200, 150, 120 };
                break;
            case 2:
                crystalWeights = new int[] { 200, 150, 120, 90 };
                break;
            case 3:
                crystalWeights = new int[] { 200, 150, 120, 90, 60 };
                break;
            case 4, 5:
                crystalWeights = new int[] { 200, 150, 120, 90, 60, 30 };
                break;
            default:
                crystalWeights = new int[] { 0 };
                break;
        }

        for (int i = 0; i < crystalWeights.length; i++) {
            defaults.addEntry(
                new FocusableHandler.FocusableItem(
                    LibResources.PREFIX_MOD + "crystal",
                    i,
                    EnumDye.CRYSTAL,
                    crystalWeights[i]));
        }

        return defaults;
    }

    private static FocusableHandler.FocusableList getDefaultResList(int tier) {
        FocusableHandler.FocusableList defaults = new FocusableHandler.FocusableList();
        defaults.addEntry(new FocusableHandler.FocusableBlock("minecraft:stone", 0, EnumDye.GRAY, 32));
        defaults.addEntry(new FocusableHandler.FocusableBlock("etfuturum:stone", 1, EnumDye.PINK, 30));
        defaults.addEntry(new FocusableHandler.FocusableBlock("etfuturum:stone", 3, EnumDye.WHITE, 30));
        defaults.addEntry(new FocusableHandler.FocusableBlock("etfuturum:stone", 5, EnumDye.GRAY, 30));
        defaults.addEntry(new FocusableHandler.FocusableBlock("minecraft:gravel", 0, EnumDye.SILVER, 30));
        defaults.addEntry(new FocusableHandler.FocusableBlock("minecraft:grass", 0, EnumDye.GREEN, 10));
        defaults.addEntry(new FocusableHandler.FocusableBlock("minecraft:dirt", 0, EnumDye.BROWN, 20));
        defaults.addEntry(new FocusableHandler.FocusableBlock("etfuturum:coarse_dirt", 0, EnumDye.BROWN, 10));
        defaults.addEntry(new FocusableHandler.FocusableBlock("minecraft:sand", 0, EnumDye.YELLOW, 30));
        defaults.addEntry(new FocusableHandler.FocusableBlock("minecraft:sand", 1, EnumDye.YELLOW, 24));
        defaults.addEntry(new FocusableHandler.FocusableBlock("minecraft:sand_stone", 0, EnumDye.YELLOW, 10));
        defaults.addEntry(new FocusableHandler.FocusableBlock("minecraft:cobblestone", 0, EnumDye.GRAY, 28));
        defaults.addEntry(new FocusableHandler.FocusableBlock("minecraft:mossy_cobblestone", 0, EnumDye.GREEN, 6));
        defaults.addEntry(new FocusableHandler.FocusableBlock("minecraft:obsidian", 0, EnumDye.PURPLE, 10));
        defaults.addEntry(new FocusableHandler.FocusableBlock("minecraft:clay", 0, EnumDye.SILVER, 12));
        defaults.addEntry(new FocusableHandler.FocusableBlock("minecraft:netherrack", 0, EnumDye.RED, 28));
        defaults.addEntry(new FocusableHandler.FocusableBlock("minecraft:soul_sand", 0, EnumDye.BROWN, 16));
        defaults.addEntry(new FocusableHandler.FocusableBlock("minecraft:mycelium", 0, EnumDye.PURPLE, 8));
        defaults.addEntry(new FocusableHandler.FocusableBlock("minecraft:end_stone", 0, EnumDye.WHITE, 13));
        defaults.addEntry(new FocusableHandler.FocusableBlock("minecraft:hardened_clay", 0, EnumDye.RED, 12));

        defaults.addEntry(new FocusableHandler.FocusableBlock("minecraft:stained_hardened_clay", 0, EnumDye.WHITE, 2));
        defaults.addEntry(new FocusableHandler.FocusableBlock("minecraft:stained_hardened_clay", 1, EnumDye.ORANGE, 2));
        defaults
            .addEntry(new FocusableHandler.FocusableBlock("minecraft:stained_hardened_clay", 2, EnumDye.MAGENTA, 2));
        defaults
            .addEntry(new FocusableHandler.FocusableBlock("minecraft:stained_hardened_clay", 3, EnumDye.LIGHT_BLUE, 2));
        defaults.addEntry(new FocusableHandler.FocusableBlock("minecraft:stained_hardened_clay", 4, EnumDye.YELLOW, 2));
        defaults.addEntry(new FocusableHandler.FocusableBlock("minecraft:stained_hardened_clay", 5, EnumDye.LIME, 2));
        defaults.addEntry(new FocusableHandler.FocusableBlock("minecraft:stained_hardened_clay", 6, EnumDye.PINK, 2));
        defaults.addEntry(new FocusableHandler.FocusableBlock("minecraft:stained_hardened_clay", 7, EnumDye.GRAY, 2));
        defaults.addEntry(new FocusableHandler.FocusableBlock("minecraft:stained_hardened_clay", 8, EnumDye.SILVER, 2));
        defaults.addEntry(new FocusableHandler.FocusableBlock("minecraft:stained_hardened_clay", 9, EnumDye.CYAN, 2));
        defaults
            .addEntry(new FocusableHandler.FocusableBlock("minecraft:stained_hardened_clay", 10, EnumDye.PURPLE, 2));
        defaults.addEntry(new FocusableHandler.FocusableBlock("minecraft:stained_hardened_clay", 11, EnumDye.BLUE, 2));
        defaults.addEntry(new FocusableHandler.FocusableBlock("minecraft:stained_hardened_clay", 12, EnumDye.BROWN, 2));
        defaults.addEntry(new FocusableHandler.FocusableBlock("minecraft:stained_hardened_clay", 13, EnumDye.GREEN, 2));
        defaults.addEntry(new FocusableHandler.FocusableBlock("minecraft:stained_hardened_clay", 14, EnumDye.RED, 2));
        defaults.addEntry(new FocusableHandler.FocusableBlock("minecraft:stained_hardened_clay", 15, EnumDye.BLACK, 2));

        defaults.addEntry(new FocusableHandler.FocusableBlock("minecraft:sponge", 0, EnumDye.YELLOW, 28));
        defaults
            .addEntry(new FocusableHandler.FocusableBlock(LibResources.PREFIX_MOD + "alabaster", 0, EnumDye.WHITE, 12));
        defaults
            .addEntry(new FocusableHandler.FocusableBlock(LibResources.PREFIX_MOD + "basalt", 0, EnumDye.BLACK, 30));
        defaults.addEntry(
            new FocusableHandler.FocusableBlock(LibResources.PREFIX_MOD + "hardened_stone", 0, EnumDye.GRAY, 20));
        defaults.addEntry(new FocusableHandler.FocusableBlock("chisel:limestone", 0, EnumDye.LIME, 30));
        defaults.addEntry(new FocusableHandler.FocusableBlock("chisel:marble", 0, EnumDye.WHITE, 30));
        defaults.addEntry(new FocusableHandler.FocusableBlock("TConstruct:CraftedSoil", 5, EnumDye.GREEN, 2));
        defaults.addEntry(new FocusableHandler.FocusableBlock("TConstruct:slime.grass", 0, EnumDye.GREEN, 1));
        defaults.addEntry(
            new FocusableHandler.FocusableBlock("appliedenergistics2:tile.BlockSkyStone", 0, EnumDye.BLACK, 2));

        int micaWeight;
        switch (tier) {
            case 0:
                micaWeight = 2;
                break;
            case 1:
                micaWeight = 3;
                break;
            case 2:
                micaWeight = 4;
                break;
            case 3:
                micaWeight = 5;
                break;
            case 4:
                micaWeight = 6;
                break;
            case 5:
                micaWeight = 7;
                break;
            default:
                micaWeight = 0;
                break;
        }

        defaults.addEntry(
            new FocusableHandler.FocusableBlock(LibResources.PREFIX_MOD + "mica", 0, EnumDye.WHITE, micaWeight));

        return defaults;
    }
}
