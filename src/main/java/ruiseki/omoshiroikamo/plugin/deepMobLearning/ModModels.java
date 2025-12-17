package ruiseki.omoshiroikamo.plugin.deepMobLearning;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import ruiseki.omoshiroikamo.api.entity.model.ModelRegistry;
import ruiseki.omoshiroikamo.api.entity.model.ModelRegistryItem;
import ruiseki.omoshiroikamo.common.util.Logger;

public class ModModels {

    public static void preInit() {
        registerModAddons();
    }

    public static void init() {
        loadConfiguration();
    }

    public static ArrayList<BaseModelHandler> registeredModAddons = new ArrayList<>();

    private static void registerModAddons() {
        addModAddon(new BaseModels());
        addModAddon(new OriginalModels());
    }

    public static void addModAddon(BaseModelHandler addon) {
        if (addon == null) {
            Logger.error("Tried to add null mod addon");
            return;
        }

        registeredModAddons.add(addon);
    }

    private static List<ModelRegistryItem> generateDefaultModels() {
        List<ModelRegistryItem> models = new ArrayList<>();

        for (BaseModelHandler addon : registeredModAddons) {
            models = addon.tryRegisterModels(models);
        }

        return models;

    }

    private static void loadConfiguration() {
        Collection<ModelRegistryItem> allModels = generateDefaultModels();
        Logger.info("Models Loading Config...");
        for (ModelRegistryItem model : allModels) {
            ModelRegistry.INSTANCE.register(model);
        }
    }
}
