package ruiseki.omoshiroikamo.plugin.nei.recipe.voidMiner;

import codechicken.nei.recipe.TemplateRecipeHandler;

public class QuantumOreExtractorRecipeHandlerT1 extends QuantumOreExtractorRecipeHandler {

    public QuantumOreExtractorRecipeHandlerT1() {
        super(0);
    }

    @Override
    public TemplateRecipeHandler newInstance() {
        return new QuantumOreExtractorRecipeHandlerT1();
    }
}
