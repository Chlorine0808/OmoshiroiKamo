package ruiseki.omoshiroikamo.plugin.nei.recipe.voidMiner;

import codechicken.nei.recipe.TemplateRecipeHandler;

public class QuantumResExtractorRecipeHandlerT2 extends QuantumResExtractorRecipeHandler {

    public QuantumResExtractorRecipeHandlerT2() {
        super(1);
    }

    @Override
    public TemplateRecipeHandler newInstance() {
        return new QuantumResExtractorRecipeHandlerT2();
    }
}
