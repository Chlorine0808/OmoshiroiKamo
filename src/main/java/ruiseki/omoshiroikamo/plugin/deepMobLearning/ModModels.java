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

            boolean enabled = configuration.getBoolean("enabled", model.getEntityName(), true, "Is model enabled?");
            model.setEnabled(enabled);

            float numberOfHearts = configuration.getFloat(
                "numberOfHearts",
                model.getEntityName(),
                model.getNumberOfHearts(),
                0.0f,
                Float.MAX_VALUE,
                "Number of hearts");
            model.setNumberOfHearts(numberOfHearts);

            float interfaceScale = configuration.getFloat(
                "interfaceScale",
                model.getEntityName(),
                model.getInterfaceScale(),
                0.0f,
                Float.MAX_VALUE,
                "Scale size");
            model.setInterfaceScale(interfaceScale);

            int interfaceOffsetX = configuration
                .getInt("interfaceOffsetX", model.getEntityName(), model.getInterfaceOffsetX(), 0, 74, "");
            model.setInterfaceOffsetX(interfaceOffsetX);

            int interfaceOffsetY = configuration
                .getInt("interfaceOffsetY", model.getEntityName(), model.getInterfaceOffsetY(), 0, 100, "");
            model.setInterfaceOffsetY(interfaceOffsetY);

            String[] mobTrivia = configuration
                .getStringList("mobTrivia", model.getEntityName(), model.getMobTrivia(), "Mob Information");
            model.setMobTrivia(mobTrivia);

            ModelRegistry.INSTANCE.register(model);
        }
        configuration.save();

    }

}
