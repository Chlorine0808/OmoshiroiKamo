package ruiseki.omoshiroikamo.module.cows.common.registries;

import java.util.ArrayList;
import java.util.List;

import ruiseki.omoshiroikamo.api.entity.SpawnType;
import ruiseki.omoshiroikamo.api.entity.cow.CowsRegistryItem;

public class BuildCraftCows extends BaseCowHandler {

    public static CowsRegistryItem oilCow;
    public static CowsRegistryItem fuelCow;
    public static CowsRegistryItem redplasmaCow;

    public BuildCraftCows() {
        super("BuildCraft", "BuildCraft", "textures/entity/cows/base/");
        this.setStartID(300);
    }

    @Override
    public List<CowsRegistryItem> registerCows() {
        List<CowsRegistryItem> allCows = new ArrayList<>();

        oilCow = addCow("OilCow", 300, 0x1F1A12, 0x3A352A, SpawnType.NORMAL).setFluidString("oil")
            .setLang("en_US", "Oil Cow")
            .setLang("ja_JP", "石油ウシ");
        allCows.add(oilCow);

        fuelCow = addCow("FuelCow", 301, 0xE5CC00, 0xFFF280, SpawnType.NORMAL).setFluidString("fuel")
            .setLang("en_US", "Fuel Cow")
            .setLang("ja_JP", "燃料ウシ");
        allCows.add(fuelCow);

        redplasmaCow = addCow("RedPlasmaCow", 302, 0xCC0000, 0xFF6666, SpawnType.HELL).setFluidString("redplasma")
            .setLang("en_US", "Red Plasma Cow")
            .setLang("ja_JP", "レッドプラズマウシ");
        allCows.add(redplasmaCow);

        return allCows;
    }
}
