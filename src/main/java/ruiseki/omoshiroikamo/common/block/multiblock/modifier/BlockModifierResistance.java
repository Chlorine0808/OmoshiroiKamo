package ruiseki.omoshiroikamo.common.block.multiblock.modifier;

import java.util.ArrayList;
import java.util.List;

import ruiseki.omoshiroikamo.common.util.LangUtils;

import ruiseki.omoshiroikamo.api.enums.ModObject;
import ruiseki.omoshiroikamo.api.multiblock.AttributeEnergyCostFixed;
import ruiseki.omoshiroikamo.api.multiblock.IModifierAttribute;
import ruiseki.omoshiroikamo.common.init.ModifierAttribute;

public class BlockModifierResistance extends BlockModifier {

    protected BlockModifierResistance() {
        super(ModObject.blockModifierResistance.unlocalisedName, "resistance");
        setTextureName("modifier_resistance");
    }

    public static BlockModifierResistance create() {
        return new BlockModifierResistance();
    }

    @Override
    public void addAttributes(List<IModifierAttribute> list) {
        list.add(ModifierAttribute.P_RESISTANCE.getAttribute());
        list.add(new AttributeEnergyCostFixed(128));
    }

    @Override
    public List<String> getTooltipLines() {
        List<String> list = new ArrayList<>();
        list.add(LangUtils.translateToLocal("tooltip.modifier.resistance.effect"));
        list.add(LangUtils.translateToLocal("tooltip.modifier.beacon.energy"));
        return list;
    }
}
