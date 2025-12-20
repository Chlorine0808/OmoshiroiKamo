package ruiseki.omoshiroikamo.plugin.nei.recipe.voidMiner;

import codechicken.nei.recipe.TemplateRecipeHandler;

public class QuantumOreExtractorRecipeHandlerT4 extends QuantumOreExtractorRecipeHandler {

    public QuantumOreExtractorRecipeHandlerT4() {
        super(3);
    }

    @Override
    public TemplateRecipeHandler newInstance() {
        return new QuantumOreExtractorRecipeHandlerT4();
    }
}
