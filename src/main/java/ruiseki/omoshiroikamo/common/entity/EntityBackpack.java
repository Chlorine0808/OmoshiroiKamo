package ruiseki.omoshiroikamo.common.entity;

import net.minecraft.entity.Entity;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class EntityBackpack extends EntityImmortalItem {

    public EntityBackpack(World world, Entity original, ItemStack stack) {
        super(world, original, stack);
    }

    @Override
    public boolean isImmortal() {
        return super.isImmortal();
    }
}
