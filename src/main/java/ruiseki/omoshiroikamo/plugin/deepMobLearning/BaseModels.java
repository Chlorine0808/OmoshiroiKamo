package ruiseki.omoshiroikamo.plugin.deepMobLearning;

import java.util.ArrayList;
import java.util.List;

import ruiseki.omoshiroikamo.api.entity.model.ModelRegistryItem;

public class BaseModels extends BaseModelHandler {

    public BaseModels() {
        super("Base", "Base", "model/base/");
        this.setNeedsModPresent(false);
        this.setStartID(0);
    }

    @Override
    public List<ModelRegistryItem> registerModels() {
        List<ModelRegistryItem> allModels = new ArrayList<>();

        ModelRegistryItem creeper = addModel(
            "Creeper",
            this.nextID(),
            "creeper",
            10f,
            1,
            0,
            0,
            new String[] { "Will blow up your base if left unattended." },
            new String[] {
                "en_US:§bCreeper Data Model§r",
                "ja_JP:§bクリーパーデータモデル§r"
            });
        allModels.add(creeper);

        ModelRegistryItem skeleton = addModel(
            "Skeleton",
            this.nextID(),
            "skeleton",
            10f,
            1,
            0,
            0,
            new String[] { "A formidable archer, which seem to be running some sort of cheat engine",
                "A shield could prove useful" },
            new String[] {
                "en_US:§bSkeleton Data Model§r",
                "ja_JP:§bスケルトンデータモデル§r"
            });
        allModels.add(skeleton);

        return allModels;
    }
}
