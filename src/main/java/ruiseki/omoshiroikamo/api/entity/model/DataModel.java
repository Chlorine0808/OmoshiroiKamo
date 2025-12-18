package ruiseki.omoshiroikamo.api.entity.model;

import java.util.LinkedList;
import java.util.List;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityList;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ChatComponentText;

import org.jetbrains.annotations.Nullable;

import ruiseki.omoshiroikamo.common.init.ModItems;
import ruiseki.omoshiroikamo.common.util.item.ItemNBTUtils;

public class DataModel {

    private final ModelRegistryItem model;

    protected final String TIER_TAG = "tier";
    protected final String KILL_COUNT_TAG = "killCount";
    protected final String SIMULATION_COUNT_TAG = "simulationCount";
    protected final String TOTAL_KILL_COUNT_TAG = "totalKillCount";
    protected final String TOTAL_SIMULATION_COUNT_TAG = "totalSimulationCount";

    private DataModel(ModelRegistryItem model) {
        this.model = model;
    }

    public static boolean isModel(ItemStack stack) {
        return stack != null && stack.getItem() == ModItems.DATA_MODEL.getItem();
    }

    public static DataModel getDataFromStack(ItemStack stack) {
        if (!isModel(stack)) return null;
        ModelRegistryItem model = ModelRegistry.INSTANCE.getByType(stack.getItemDamage());
        return model != null ? new DataModel(model) : null;
    }

    public static DataModel getDataFromKey(String key, ItemStack stack) {
        if (!isModel(stack)) {
            return null;
        }
        ModelRegistryItem model = ModelRegistry.INSTANCE.getByName(key);
        return model != null ? new DataModel(model) : null;
    }

    public static List<DataModel> getAllModels() {
        List<DataModel> models = new LinkedList<>();
        for (ModelRegistryItem item : ModelRegistry.INSTANCE.getItems()) {
            models.add(new DataModel(item));
        }
        return models;
    }

    public int getId() {
        return model.getId();
    }

    public ModelRegistryItem getItem() {
        return model;
    }

    @Nullable
    public Class<? extends Entity> getEntityClass() {
        return (Class<? extends Entity>) EntityList.stringToClassMapping.get(model.getEntityName());
    }

    public void increaseMobKillCount(EntityPlayerMP player, ItemStack stack) {
        int tier = getTier(stack);
        int i = getKillCount(stack);

        // TODO Add GlitchSword and Trial
        i = i + 1;
        setKillCount(i, stack);
        setTotalKillCount(getTotalKillCount(stack) + 1, stack);

        if (DataModelExperience.shouldIncreaseTier(tier, i, getSimulationCount(stack))) {
            String nextTierName = DataModelExperience.getTierName(tier + 1);
            String message = model.getDisplayName() + " reached the " + nextTierName + " tier";
            player.addChatMessage(new ChatComponentText(message));
            setKillCount(0, stack);
            setSimulationCount(0, stack);
            setTier(tier + 1, stack);
        }
    }

    public void increaseSimulationCount(ItemStack stack) {
        int tier = getTier(stack);
        int i = getSimulationCount(stack);
        i = i + 1;
        setSimulationCount(i, stack);

        setTotalSimulationCount(getTotalSimulationCount(stack) + 1, stack);

        if (DataModelExperience.shouldIncreaseTier(tier, getKillCount(stack), i)) {
            setKillCount(0, stack);
            setSimulationCount(0, stack);
            setTier(tier + 1, stack);
        }
    }

    public void createTagCompound(ItemStack stack) {
        NBTTagCompound tag = ItemNBTUtils.getNBT(stack);
        tag.setInteger(TIER_TAG, getTier(stack));
        tag.setInteger(KILL_COUNT_TAG, getKillCount(stack));
        tag.setInteger(SIMULATION_COUNT_TAG, getSimulationCount(stack));
        tag.setInteger(TOTAL_KILL_COUNT_TAG, getTotalKillCount(stack));
        tag.setInteger(TOTAL_SIMULATION_COUNT_TAG, getTotalSimulationCount(stack));
    }

    public int getTier(ItemStack stack) {
        return ItemNBTUtils.getInt(stack, TIER_TAG, 0);
    }

    public int getKillCount(ItemStack stack) {
        return ItemNBTUtils.getInt(stack, KILL_COUNT_TAG, 0);
    }

    public int getSimulationCount(ItemStack stack) {
        return ItemNBTUtils.getInt(stack, SIMULATION_COUNT_TAG, 0);
    }

    public int getTotalKillCount(ItemStack stack) {
        return ItemNBTUtils.getInt(stack, TOTAL_KILL_COUNT_TAG, 0);
    }

    public int getTotalSimulationCount(ItemStack stack) {
        return ItemNBTUtils.getInt(stack, TOTAL_SIMULATION_COUNT_TAG, 0);
    }

    public void setTier(int tier, ItemStack stack) {
        ItemNBTUtils.setInt(stack, TIER_TAG, tier);
    }

    public void setKillCount(int count, ItemStack stack) {
        ItemNBTUtils.setInt(stack, KILL_COUNT_TAG, count);
    }

    public void setSimulationCount(int count, ItemStack stack) {
        ItemNBTUtils.setInt(stack, SIMULATION_COUNT_TAG, count);
    }

    public void setTotalKillCount(int count, ItemStack stack) {
        ItemNBTUtils.setInt(stack, TOTAL_KILL_COUNT_TAG, count);
    }

    public void setTotalSimulationCount(int count, ItemStack stack) {
        ItemNBTUtils.setInt(stack, TOTAL_SIMULATION_COUNT_TAG, count);
    }
}
