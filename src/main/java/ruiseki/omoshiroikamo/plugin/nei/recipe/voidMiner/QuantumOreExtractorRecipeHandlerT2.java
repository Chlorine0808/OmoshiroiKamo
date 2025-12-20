package ruiseki.omoshiroikamo.plugin.nei.recipe.voidMiner;

import codechicken.nei.recipe.TemplateRecipeHandler;

public class QuantumOreExtractorRecipeHandlerT2 extends QuantumOreExtractorRecipeHandler {

    public QuantumOreExtractorRecipeHandlerT2() {
        super(1);
    }

    @Override
    public TemplateRecipeHandler newInstance() {
        return new QuantumOreExtractorRecipeHandlerT2();
    }
}
