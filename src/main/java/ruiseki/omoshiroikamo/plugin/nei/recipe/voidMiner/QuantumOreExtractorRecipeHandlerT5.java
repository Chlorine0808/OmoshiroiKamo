package ruiseki.omoshiroikamo.plugin.nei.recipe.voidMiner;

import codechicken.nei.recipe.TemplateRecipeHandler;

public class QuantumOreExtractorRecipeHandlerT5 extends QuantumOreExtractorRecipeHandler {

    public QuantumOreExtractorRecipeHandlerT5() {
        super(4);
    }

    @Override
    public TemplateRecipeHandler newInstance() {
        return new QuantumOreExtractorRecipeHandlerT5();
    }
}
