package ruiseki.omoshiroikamo.plugin.nei;

import codechicken.nei.api.API;
import codechicken.nei.api.IConfigureNEI;
import ruiseki.omoshiroikamo.client.gui.modularui2.backpack.container.BackpackGuiContainer;
import ruiseki.omoshiroikamo.common.init.ModBlocks;
import ruiseki.omoshiroikamo.common.util.Logger;
import ruiseki.omoshiroikamo.common.util.lib.LibMisc;
import ruiseki.omoshiroikamo.config.backport.BackportConfigs;
import ruiseki.omoshiroikamo.plugin.nei.overlay.BackpackOverlay;
import ruiseki.omoshiroikamo.plugin.nei.positioner.BackpackPositioner;
import ruiseki.omoshiroikamo.plugin.nei.recipe.chicken.ChickenBreedingRecipeHandler;
import ruiseki.omoshiroikamo.plugin.nei.recipe.chicken.ChickenDropsRecipeHandler;
import ruiseki.omoshiroikamo.plugin.nei.recipe.chicken.ChickenLayingRecipeHandler;
import ruiseki.omoshiroikamo.plugin.nei.recipe.chicken.ChickenThrowsRecipeHandler;
import ruiseki.omoshiroikamo.plugin.nei.recipe.cow.CowBreedingRecipeHandler;
import ruiseki.omoshiroikamo.plugin.nei.recipe.cow.CowMilkingRecipeHandler;
import ruiseki.omoshiroikamo.plugin.nei.recipe.voidMiner.*;

@SuppressWarnings("unused")
public class NEIConfig implements IConfigureNEI {

    @Override
    public void loadConfig() {
        Logger.info("Loading NeiConfig: " + getName());
        if (BackportConfigs.useEnvironmentalTech) {
            Logger.info("[NEIConfig] Registering QuantumExtractor handlers");

            // Register Ore Extractors - each tier as a separate class
            QuantumOreExtractorRecipeHandlerT1 oreT1 = new QuantumOreExtractorRecipeHandlerT1();
            QuantumOreExtractorRecipeHandlerT2 oreT2 = new QuantumOreExtractorRecipeHandlerT2();
            QuantumOreExtractorRecipeHandlerT3 oreT3 = new QuantumOreExtractorRecipeHandlerT3();
            QuantumOreExtractorRecipeHandlerT4 oreT4 = new QuantumOreExtractorRecipeHandlerT4();
            QuantumOreExtractorRecipeHandlerT5 oreT5 = new QuantumOreExtractorRecipeHandlerT5();
            QuantumOreExtractorRecipeHandlerT6 oreT6 = new QuantumOreExtractorRecipeHandlerT6();

            registerHandler(oreT1);
            registerHandler(oreT2);
            registerHandler(oreT3);
            registerHandler(oreT4);
            registerHandler(oreT5);
            registerHandler(oreT6);

            // Register Res Extractors - each tier as a separate class
            QuantumResExtractorRecipeHandlerT1 resT1 = new QuantumResExtractorRecipeHandlerT1();
            QuantumResExtractorRecipeHandlerT2 resT2 = new QuantumResExtractorRecipeHandlerT2();
            QuantumResExtractorRecipeHandlerT3 resT3 = new QuantumResExtractorRecipeHandlerT3();
            QuantumResExtractorRecipeHandlerT4 resT4 = new QuantumResExtractorRecipeHandlerT4();
            QuantumResExtractorRecipeHandlerT5 resT5 = new QuantumResExtractorRecipeHandlerT5();
            QuantumResExtractorRecipeHandlerT6 resT6 = new QuantumResExtractorRecipeHandlerT6();

            registerHandler(resT1);
            registerHandler(resT2);
            registerHandler(resT3);
            registerHandler(resT4);
            registerHandler(resT5);
            registerHandler(resT6);

            // Register Recipe Catalysts (tab icons on the left side of the tab)
            API.addRecipeCatalyst(ModBlocks.QUANTUM_ORE_EXTRACTOR.newItemStack(1, 0), oreT1.getRecipeID());
            API.addRecipeCatalyst(ModBlocks.QUANTUM_ORE_EXTRACTOR.newItemStack(1, 1), oreT2.getRecipeID());
            API.addRecipeCatalyst(ModBlocks.QUANTUM_ORE_EXTRACTOR.newItemStack(1, 2), oreT3.getRecipeID());
            API.addRecipeCatalyst(ModBlocks.QUANTUM_ORE_EXTRACTOR.newItemStack(1, 3), oreT4.getRecipeID());
            API.addRecipeCatalyst(ModBlocks.QUANTUM_ORE_EXTRACTOR.newItemStack(1, 4), oreT5.getRecipeID());
            API.addRecipeCatalyst(ModBlocks.QUANTUM_ORE_EXTRACTOR.newItemStack(1, 5), oreT6.getRecipeID());

            API.addRecipeCatalyst(ModBlocks.QUANTUM_RES_EXTRACTOR.newItemStack(1, 0), resT1.getRecipeID());
            API.addRecipeCatalyst(ModBlocks.QUANTUM_RES_EXTRACTOR.newItemStack(1, 1), resT2.getRecipeID());
            API.addRecipeCatalyst(ModBlocks.QUANTUM_RES_EXTRACTOR.newItemStack(1, 2), resT3.getRecipeID());
            API.addRecipeCatalyst(ModBlocks.QUANTUM_RES_EXTRACTOR.newItemStack(1, 3), resT4.getRecipeID());
            API.addRecipeCatalyst(ModBlocks.QUANTUM_RES_EXTRACTOR.newItemStack(1, 4), resT5.getRecipeID());
            API.addRecipeCatalyst(ModBlocks.QUANTUM_RES_EXTRACTOR.newItemStack(1, 5), resT6.getRecipeID());
        }
        if (BackportConfigs.useChicken) {
            registerHandler(new ChickenLayingRecipeHandler());
            registerHandler(new ChickenBreedingRecipeHandler());
            registerHandler(new ChickenDropsRecipeHandler());
            registerHandler(new ChickenThrowsRecipeHandler());
        }

        if (BackportConfigs.useCow) {
            registerHandler(new CowBreedingRecipeHandler());
            registerHandler(new CowMilkingRecipeHandler());
        }

        if (BackportConfigs.useBackpack) {
            API.registerGuiOverlay(BackpackGuiContainer.class, "crafting", new BackpackPositioner());
            API.registerGuiOverlayHandler(BackpackGuiContainer.class, new BackpackOverlay(), "crafting");
        }
    }

    protected static void registerHandler(IRecipeHandlerBase handler) {
        // Debug to verify NEI handler uniqueness keys
        String recipeIdLog = (handler instanceof RecipeHandlerBase)
            ? ((RecipeHandlerBase) handler).getRecipeID()
            : "<no-recipeId>";
        String handlerIdLog = (handler instanceof RecipeHandlerBase)
            ? ((RecipeHandlerBase) handler).getHandlerId()
            : "<no-handlerId>";

        Logger.info("[NEIConfig] registering handler class=" + handler.getClass().getName()
            + ", recipeId=" + recipeIdLog
            + ", handlerId=" + handlerIdLog);
        handler.prepare();
        API.registerRecipeHandler(handler);
        API.registerUsageHandler(handler);
    }

    @Override
    public String getName() {
        return LibMisc.MOD_NAME;
    }

    @Override
    public String getVersion() {
        return LibMisc.VERSION;
    }
}
