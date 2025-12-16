package ruiseki.omoshiroikamo.plugin.deepMobLearning;

import java.util.List;

import ruiseki.omoshiroikamo.api.entity.model.ModelRegistryItem;

public class BaseModels extends BaseModelHandler {

    public BaseModels() {
        super("Base", "Base", "model/base/");
        this.setNeedsModPresent(false);
        this.setStartID(0);
    }

    @Override
    public List<ModelRegistryItem> registerModels(List<ModelRegistryItem> allModels) {
        addModel(allModels, "Creeper", this.nextID(), "creeper");

        return allModels;
    }
}
