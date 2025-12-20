package ruiseki.omoshiroikamo.plugin.nei.recipe.voidMiner;

import codechicken.nei.recipe.TemplateRecipeHandler;

public class QuantumOreExtractorRecipeHandlerT3 extends QuantumOreExtractorRecipeHandler {

    public QuantumOreExtractorRecipeHandlerT3() {
        super(2);
    }

    @Override
    public TemplateRecipeHandler newInstance() {
        return new QuantumOreExtractorRecipeHandlerT3();
    }
}
