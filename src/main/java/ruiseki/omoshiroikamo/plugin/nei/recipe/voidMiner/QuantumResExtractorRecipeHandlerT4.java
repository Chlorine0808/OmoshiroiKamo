package ruiseki.omoshiroikamo.plugin.nei.recipe.voidMiner;

import codechicken.nei.recipe.TemplateRecipeHandler;

public class QuantumResExtractorRecipeHandlerT4 extends QuantumResExtractorRecipeHandler {

    public QuantumResExtractorRecipeHandlerT4() {
        super(3);
    }

    @Override
    public TemplateRecipeHandler newInstance() {
        return new QuantumResExtractorRecipeHandlerT4();
    }
}
