package ruiseki.omoshiroikamo.plugin.cow;

import java.util.ArrayList;
import java.util.List;

import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidStack;

import ruiseki.omoshiroikamo.api.entity.SpawnType;
import ruiseki.omoshiroikamo.api.entity.cow.CowsRegistryItem;

public class BaseCows extends BaseCowHandler {

    public static CowsRegistryItem waterCow;
    public static CowsRegistryItem lavaCow;

    public BaseCows() {
        super("Base", "Base", "textures/entity/cows/base/");
        this.setNeedsModPresent(false);
        this.setStartID(0);
    }

    @Override
    public List<CowsRegistryItem> registerCows() {
        List<CowsRegistryItem> allCows = new ArrayList<>();

        waterCow = addCow(
            "WaterCow",
            this.nextID(),
            new FluidStack(FluidRegistry.getFluid("water"), 1000),
            0x000099,
            0x8080ff,
            SpawnType.NORMAL,
            new String[] { "en_US:Water Cow", "ja_JP:ウォーター牛" } );
        allCows.add(waterCow);

        lavaCow = addCow(
            "LavaCow",
            this.nextID(),
            new FluidStack(FluidRegistry.getFluid("lava"), 1000),
            0xcc3300,
            0xffff00,
            SpawnType.HELL,
            new String[] { "en_US:Lava Cow", "ja_JP:ラヴァ牛" });
        allCows.add(lavaCow);

        return allCows;
    }
}
