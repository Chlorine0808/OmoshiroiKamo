package ruiseki.omoshiroikamo.plugin.nei.recipe.voidMiner;

import codechicken.nei.recipe.TemplateRecipeHandler;

public class QuantumResExtractorRecipeHandlerT6 extends QuantumResExtractorRecipeHandler {

    public QuantumResExtractorRecipeHandlerT6() {
        super(5);
    }

    @Override
    public TemplateRecipeHandler newInstance() {
        return new QuantumResExtractorRecipeHandlerT6();
    }
}
