package ruiseki.omoshiroikamo.plugin.deepMobLearning;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import net.minecraftforge.common.config.Configuration;

import ruiseki.omoshiroikamo.api.entity.model.ModelRegistry;
import ruiseki.omoshiroikamo.api.entity.model.ModelRegistryItem;
import ruiseki.omoshiroikamo.common.util.Logger;
import ruiseki.omoshiroikamo.common.util.lib.LibMisc;

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

        File configDirectory = new File("config/" + LibMisc.MOD_ID + "/model");
        if (!configDirectory.exists()) {
            configDirectory.mkdir();
        }

        File configFile = new File(configDirectory, "base.cfg");
        Configuration configuration = new Configuration(configFile);

        Collection<ModelRegistryItem> allModels = generateDefaultModels();

        configuration.addCustomCategoryComment(
            "0",
            "It is Ideal to regenerate this file after updates as your config files may overwrite changes made to core.");

        Logger.info("Models Loading Config...");
        for (ModelRegistryItem model : allModels) {

            boolean enabled = configuration.getBoolean("enabled", model.getEntityName(), true, "Is chicken enabled?");
            model.setEnabled(enabled);

            ModelRegistry.INSTANCE.register(model);
        }
        configuration.save();

    }

}
