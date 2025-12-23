package ruiseki.omoshiroikamo.common.item.deepMobLearning;

import ruiseki.omoshiroikamo.api.enums.ModObject;
import ruiseki.omoshiroikamo.common.item.ItemOK;

public class ItemPolymerClay extends ItemOK {

    public ItemPolymerClay() {
        super(ModObject.itemPolymerClay.unlocalisedName);
        setMaxStackSize(64);
        setTextureName("polymer_clay");
    }
}
