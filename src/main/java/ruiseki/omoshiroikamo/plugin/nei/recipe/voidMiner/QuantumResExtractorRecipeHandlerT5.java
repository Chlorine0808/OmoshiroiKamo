package ruiseki.omoshiroikamo.plugin.nei.recipe.voidMiner;

import codechicken.nei.recipe.TemplateRecipeHandler;

public class QuantumResExtractorRecipeHandlerT5 extends QuantumResExtractorRecipeHandler {

    public QuantumResExtractorRecipeHandlerT5() {
        super(4);
    }

    @Override
    public TemplateRecipeHandler newInstance() {
        return new QuantumResExtractorRecipeHandlerT5();
    }
}
