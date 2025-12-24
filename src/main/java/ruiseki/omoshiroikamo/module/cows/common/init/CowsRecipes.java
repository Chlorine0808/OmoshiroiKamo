package ruiseki.omoshiroikamo.module.cows.common.init;

import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.ShapedOreRecipe;

import cpw.mods.fml.common.registry.GameRegistry;

public class CowsRecipes {

    public static void init() {
        blockRecipes();
        itemRecipes();
    }

    public static void blockRecipes() {
        // Stall
        GameRegistry.addRecipe(
            new ShapedOreRecipe(
                CowsBlocks.STALL.getItem(),
                "B B",
                "BHB",
                "GGG",
                'B',
                Blocks.iron_bars,
                'H',
                Blocks.hay_block,
                'G',
                new ItemStack(Blocks.stained_hardened_clay, 1, 7)));
    }

    public static void itemRecipes() {

        // Cow Halter
        GameRegistry.addRecipe(
            new ShapedOreRecipe(
                CowsItems.COW_HALTER.getItem(),
                "  L",
                " S ",
                "S  ",
                'L',
                Items.leather,
                'S',
                Items.stick));
    }
}
