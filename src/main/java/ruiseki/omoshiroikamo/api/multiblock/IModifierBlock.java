package ruiseki.omoshiroikamo.api.multiblock;

import java.util.List;

public interface IModifierBlock extends IMBBlock {

    String getModifierName();

    List<IModifierAttribute> getAttributes();
}
