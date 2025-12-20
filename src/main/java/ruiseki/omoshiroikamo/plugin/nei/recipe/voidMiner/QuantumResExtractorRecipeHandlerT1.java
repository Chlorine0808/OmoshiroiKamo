package ruiseki.omoshiroikamo.plugin.nei.recipe.voidMiner;

import codechicken.nei.recipe.TemplateRecipeHandler;

public class QuantumResExtractorRecipeHandlerT1 extends QuantumResExtractorRecipeHandler {

    public QuantumResExtractorRecipeHandlerT1() {
        super(0);
    }

    @Override
    public TemplateRecipeHandler newInstance() {
        return new QuantumResExtractorRecipeHandlerT1();
    }
}
