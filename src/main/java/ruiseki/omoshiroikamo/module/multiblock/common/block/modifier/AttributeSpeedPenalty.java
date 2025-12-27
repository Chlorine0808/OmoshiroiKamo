package ruiseki.omoshiroikamo.module.multiblock.common.block.modifier;

import ruiseki.omoshiroikamo.api.multiblock.IModifierAttribute;
import ruiseki.omoshiroikamo.config.backport.multiblock.QuantumExtractorConfig;

public class AttributeSpeedPenalty implements IModifierAttribute {

    private final float penaltyFactor;

    public AttributeSpeedPenalty() {
        this(1.0F);
    }

    public AttributeSpeedPenalty(float penaltyFactor) {
        this.penaltyFactor = penaltyFactor;
    }

    @Override
    public String getAttributeName() {
        return "speed_p";
    }

    @Override
    public float getMultiplier(float totalModificationFactor) {
        float basePenalty = QuantumExtractorConfig.modifiers.accuracySpeedPenalty;
        float result = (float) Math.pow(basePenalty, totalModificationFactor);
        float maxPenalty = QuantumExtractorConfig.modifiers.accuracyMaxSpeedPenalty;
        return Math.min(result, maxPenalty);
    }

    @Override
    public float getModificationFactor() {
        return penaltyFactor;
    }
}
