package ruiseki.omoshiroikamo.api.entity.model;

import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

import lombok.Getter;
import lombok.Setter;

public class ModelRegistryItem {

    @Getter
    protected final int id;
    @Getter
    protected final String entityName;
    @Getter
    protected ResourceLocation texture;
    @Getter
    @Setter
    protected boolean enabled;
    @Getter
    protected int numberOfHearts;
    @Getter
    protected int interfaceScale;
    @Getter
    protected int interfaceOffsetX;
    @Getter
    protected int interfaceOffsetY;
    @Getter
    protected ItemStack livingMatter;
    @Getter
    protected ItemStack pristineMatter;

    public ModelRegistryItem(int id, String entityName, ResourceLocation texture) {
        this.id = id;
        this.entityName = entityName;
        this.texture = texture;
    }

    public String getDisplayName() {
        return "entity." + entityName + ".name";
    }
}
