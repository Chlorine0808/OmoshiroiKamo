package ruiseki.omoshiroikamo.plugin.nei.recipe.voidMiner;

import codechicken.nei.recipe.TemplateRecipeHandler;

public class QuantumResExtractorRecipeHandlerT3 extends QuantumResExtractorRecipeHandler {

    public QuantumResExtractorRecipeHandlerT3() {
        super(2);
    }

    @Override
    public TemplateRecipeHandler newInstance() {
        return new QuantumResExtractorRecipeHandlerT3();
    }
}
