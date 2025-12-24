package ruiseki.omoshiroikamo.module.dml.common.init;

import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraftforge.oredict.ShapedOreRecipe;
import net.minecraftforge.oredict.ShapelessOreRecipe;

import cpw.mods.fml.common.registry.GameRegistry;

public class DMLRecipes {

    public static void init() {
        blockRecipes();
        itemRecipes();
    }

    public static void blockRecipes() {

        GameRegistry.addRecipe(
            new ShapedOreRecipe(
                DMLBlocks.MACHINE_CASING.getItem(),
                "PIP",
                "ICI",
                "PIP",
                'P',
                DMLItems.SOOT_COVERED_PLATE.getItem(),
                'I',
                "ingotIron",
                'C',
                DMLItems.SOOT_COVERED_REDSTONE.getItem()));

        GameRegistry.addRecipe(
            new ShapedOreRecipe(
                DMLBlocks.LOOT_FABRICATOR.getItem(),
                " G ",
                "DMD",
                "YCY",
                'M',
                DMLBlocks.MACHINE_CASING.getBlock(),
                'G',
                "ingotGold",
                'D',
                "gemDiamond",
                'Y',
                "dyeYellow",
                'C',
                Items.comparator));

        GameRegistry.addRecipe(
            new ShapedOreRecipe(
                DMLBlocks.SIMULATION_CHAMBER.getItem(),
                " G ",
                "PMP",
                "DCD",
                'M',
                DMLBlocks.MACHINE_CASING.getBlock(),
                'P',
                "pearlEnder",
                'G',
                Blocks.glass_pane,
                'C',
                Items.comparator,
                'D',
                "gemLapis"));
    }

    public static void itemRecipes() {

        // Deep Learner
        GameRegistry.addRecipe(
            new ShapedOreRecipe(
                DMLItems.DEEP_LEARNER.newItemStack(),
                "ORO",
                "RSR",
                "ODO",
                'O',
                DMLItems.SOOT_COVERED_PLATE.getItem(),
                'D',
                DMLItems.SOOT_COVERED_REDSTONE.getItem(),
                'S',
                Blocks.glass_pane,
                'R',
                Items.repeater));

        // Data Model Blank
        GameRegistry.addRecipe(
            new ShapedOreRecipe(
                DMLItems.DATA_MODEL_BLANK.newItemStack(),
                "CEC",
                "RSR",
                "CGC",
                'E',
                Items.repeater,
                'R',
                DMLItems.SOOT_COVERED_REDSTONE.getItem(),
                'G',
                "ingotGold",
                'C',
                "gemLapis",
                'S',
                "stone"));

        // Polymer Clay
        GameRegistry.addRecipe(
            new ShapedOreRecipe(
                DMLItems.POLYMER_CLAY.newItemStack(16),
                "GC ",
                "CDC",
                " CI",
                'I',
                "ingotIron",
                'G',
                "ingotGold",
                'D',
                "gemLapis",
                'C',
                "itemClay"));

        // Soot Covered Plate
        GameRegistry.addRecipe(
            new ShapelessOreRecipe(
                DMLItems.SOOT_COVERED_PLATE.newItemStack(8),
                DMLItems.SOOT_COVERED_REDSTONE.getItem(),
                "blockObsidian",
                "blockObsidian",
                "blockObsidian"));
    }
}
