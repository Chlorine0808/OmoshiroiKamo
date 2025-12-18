package ruiseki.omoshiroikamo.plugin.model;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.stream.JsonReader;

import cpw.mods.fml.common.registry.LanguageRegistry;
import ruiseki.omoshiroikamo.api.entity.model.ModelTierRegistryItem;
import ruiseki.omoshiroikamo.common.util.Logger;
import ruiseki.omoshiroikamo.common.util.lib.LibMisc;
import ruiseki.omoshiroikamo.config.ConfigUpdater;
import ruiseki.omoshiroikamo.config.backport.DeepMobLearningConfig;

public class ModelTier {

    protected String configFileName;

    public ModelTier() {
        this.configFileName = "model_tiers.json";
    }

    private static class TierJson {

        int tier;
        int killMultiplier;
        int dataToNext;
        boolean canSimulate;
        String[] lang;
    }

    public List<ModelTierRegistryItem> registerTiers() {
        List<ModelTierRegistryItem> allTiers = new ArrayList<>();

        File configFile = new File("config/" + LibMisc.MOD_ID + "/model/" + configFileName);
        if (!configFile.exists()) {
            List<ModelTierRegistryItem> defaultModels = registerModels();
            createDefaultConfig(configFile, defaultModels);
        }

        if (DeepMobLearningConfig.updateMissing) {
            updateConfigWithMissing(configFile, registerModels());
            ConfigUpdater.updateBoolean(DeepMobLearningConfig.class, "updateMissing", false);
        }

        try (FileReader fileReader = new FileReader(configFile)) {
            JsonReader reader = new JsonReader(fileReader);
            reader.setLenient(true); // Allow comments

            Gson gson = new Gson();
            Type listType = new TypeToken<ArrayList<TierJson>>() {}.getType();
            List<TierJson> models = gson.fromJson(reader, listType);
            if (models == null) {
                Logger.info(configFileName + " is empty or invalid.");
            }

            for (TierJson data : models) {
                try {

                    ModelTierRegistryItem model = addTier(
                        data.tier,
                        data.killMultiplier,
                        data.dataToNext,
                        data.canSimulate,
                        data.lang);

                    if (model != null) {
                        Logger.debug("Registering Model Tier: '" + data.tier);

                        if (data.lang != null) {
                            String langKey = "model.tier_" + data.tier + ".name";
                            for (String entry : data.lang) {
                                int splitIndex = entry.indexOf(':');
                                if (splitIndex > 0) {
                                    String lang = entry.substring(0, splitIndex)
                                        .trim();
                                    String value = entry.substring(splitIndex + 1)
                                        .trim();
                                    LanguageRegistry.instance()
                                        .addStringLocalization(langKey, lang, value);
                                }
                            }
                        }

                        allTiers.add(model);
                    }

                } catch (Exception e) {
                    Logger.error("Error registering model tier " + data.tier + ": " + e.getMessage());
                    e.printStackTrace();
                }
            }
        } catch (IOException e) {
            Logger.error("Failed to read " + configFileName + ": " + e.getMessage());
        }

        return allTiers;
    }

    public List<ModelTierRegistryItem> registerModels() {
        List<ModelTierRegistryItem> allTiers = new ArrayList<>();

        ModelTierRegistryItem tier0 = addTier(0, 1, 6, false, new String[] { "en_US:§7Faulty", "ja_JP:§7フォールティー" });
        allTiers.add(tier0);

        ModelTierRegistryItem tier1 = addTier(1, 4, 48, true, new String[] { "en_US:§aBasic", "ja_JP:§aベーシック" });
        allTiers.add(tier1);

        ModelTierRegistryItem tier2 = addTier(2, 10, 300, true, new String[] { "en_US:§9Advanced", "ja_JP:§9アドバンスド" });
        allTiers.add(tier2);

        ModelTierRegistryItem tier3 = addTier(3, 18, 900, true, new String[] { "en_US:§dSuperior", "ja_JP:§dスーペリア" });
        allTiers.add(tier3);

        ModelTierRegistryItem tier4 = addTier(4, 0, 0, true, new String[] { "en_US:§6Self-Aware", "ja_JP:§6自己認識" });
        allTiers.add(tier4);

        return allTiers;
    }

    public ModelTierRegistryItem addTier(int tier, int killMultiplier, int dataToNext, boolean canSimulate,
        String[] lang) {
        return new ModelTierRegistryItem(tier, killMultiplier, dataToNext, canSimulate, lang);
    }

    private TierJson toModelJson(ModelTierRegistryItem model) {
        if (model == null) return null;

        TierJson json = new TierJson();
        json.tier = model.getTier();
        json.killMultiplier = model.getKillMultiplier();
        json.canSimulate = model.isCanSimulate();
        json.dataToNext = model.getDataToNext();
        json.lang = model.getLang();

        return json;
    }

    public void createDefaultConfig(File file, List<ModelTierRegistryItem> allModels) {
        try {
            File parent = file.getParentFile();
            if (parent != null && !parent.exists()) parent.mkdirs();

            List<TierJson> jsonModels = new ArrayList<>();
            for (ModelTierRegistryItem model : allModels) {
                TierJson json = toModelJson(model);
                if (json != null) jsonModels.add(json);
            }

            try (Writer writer = new FileWriter(file)) {
                new GsonBuilder().setPrettyPrinting()
                    .create()
                    .toJson(jsonModels, writer);
            }

            Logger.info("Created default " + file.getPath());
        } catch (IOException e) {
            Logger.error("Failed to create default config: " + file.getPath() + " (" + e.getMessage() + ")");
        }
    }

    private void updateConfigWithMissing(File file, List<ModelTierRegistryItem> allModels) {
        List<TierJson> existing = new ArrayList<>();

        if (file.exists()) {
            try (FileReader reader = new FileReader(file)) {
                JsonReader jsonReader = new JsonReader(reader);
                jsonReader.setLenient(true);
                Type listType = new TypeToken<ArrayList<TierJson>>() {}.getType();
                List<TierJson> loaded = new Gson().fromJson(jsonReader, listType);
                if (loaded != null) existing.addAll(loaded);
            } catch (Exception e) {
                Logger.error("Failed to read existing tier config: " + e.getMessage());
            }
        } else {
            File parent = file.getParentFile();
            if (parent != null && !parent.exists()) parent.mkdirs();
        }

        boolean updated = false;
        List<Integer> addedTiers = new ArrayList<>();
        for (ModelTierRegistryItem model : allModels) {
            if (model == null) continue;

            boolean exists = existing.stream()
                .anyMatch(m -> m != null && m.tier != model.getTier());
            if (!exists) {
                TierJson json = toModelJson(model);
                if (json != null) {
                    existing.add(json);
                    addedTiers.add(model.getTier());
                    updated = true;
                }
            }
        }

        if (updated) {
            try (Writer writer = new FileWriter(file)) {
                new GsonBuilder().setPrettyPrinting()
                    .create()
                    .toJson(existing, writer);
                Logger.info("Updated model tier config with missing model tiers: " + file.getName());
                Logger
                    .info("Added " + addedTiers.size() + " model tier(s): " + String.join(", ", addedTiers.toString()));
            } catch (IOException e) {
                Logger.error("Failed to update model tier config: " + e.getMessage());
            }
        } else {
            Logger.info("No new model tiers to add to config: " + file.getName());
        }
    }
}
