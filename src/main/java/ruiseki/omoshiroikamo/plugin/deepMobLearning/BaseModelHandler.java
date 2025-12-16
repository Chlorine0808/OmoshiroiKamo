package ruiseki.omoshiroikamo.plugin.deepMobLearning;

import java.util.List;

import net.minecraft.util.ResourceLocation;

import cpw.mods.fml.common.Loader;
import lombok.Getter;
import lombok.Setter;
import ruiseki.omoshiroikamo.api.entity.model.ModelRegistryItem;
import ruiseki.omoshiroikamo.common.util.Logger;
import ruiseki.omoshiroikamo.common.util.lib.LibMisc;
import ruiseki.omoshiroikamo.plugin.ModCompatInformation;

public abstract class BaseModelHandler {

    @Getter
    protected String modID;
    @Getter
    protected String modName;
    protected String texturesLocation;

    @Setter
    private int startID = 0;

    private boolean needsMod = true;

    public BaseModelHandler(String modID, String modName, String texturesLocation) {
        this.modID = modID;
        this.modName = modName;
        this.texturesLocation = texturesLocation;
    }

    public void setNeedsModPresent(boolean bool) {
        this.needsMod = bool;
    }

    public List<ModelRegistryItem> tryRegisterModels(List<ModelRegistryItem> allModels) {
        Logger.info("Looking for " + modName + " models...");

        if (needsMod && !Loader.isModLoaded(modID)) {
            Logger.info("Skipped " + modName + " models → required mod \"" + modID + "\" is not loaded.");
            return allModels;
        }

        Logger.info("Loading " + modName + " models...");

        return allModels = registerModels(allModels);
    }

    public abstract List<ModelRegistryItem> registerModels(List<ModelRegistryItem> allModels);

    protected int nextID() {
        return this.startID++;
    }

    protected ModelRegistryItem addModel(List<ModelRegistryItem> modelList, String registryName, int id,
        String texture) {

        ModelRegistryItem model = new ModelRegistryItem(
            id,
            registryName,
            new ResourceLocation(LibMisc.MOD_ID, this.texturesLocation + texture));

        modelList.add(model);

        ModCompatInformation.addInformation(id, new ModCompatInformation(this.getModID(), "", this.getModName()));

        return model;
    }
}
