package ruiseki.omoshiroikamo.common.item.deepMobLearning;

import ruiseki.omoshiroikamo.api.enums.ModObject;
import ruiseki.omoshiroikamo.common.item.ItemOK;

public class ItemPristineMatter extends ItemOK {

    public ItemPristineMatter() {
        super(ModObject.itemPristineMatter.unlocalisedName);
        setMaxStackSize(64);
    }
}
