package ruiseki.omoshiroikamo.plugin.nei.recipe.voidMiner;

import codechicken.nei.recipe.TemplateRecipeHandler;

public class QuantumOreExtractorRecipeHandlerT6 extends QuantumOreExtractorRecipeHandler {

    public QuantumOreExtractorRecipeHandlerT6() {
        super(5);
    }

    @Override
    public TemplateRecipeHandler newInstance() {
        return new QuantumOreExtractorRecipeHandlerT6();
    }
}
