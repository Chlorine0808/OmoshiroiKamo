package ruiseki.omoshiroikamo.module.machinery.common.tile;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ChatComponentText;

import com.gtnewhorizon.structurelib.structure.IStructureDefinition;

import ruiseki.omoshiroikamo.api.block.BlockPos;
import ruiseki.omoshiroikamo.core.common.block.abstractClass.AbstractMBModifierTE;
import ruiseki.omoshiroikamo.module.machinery.common.init.MachineryBlocks;

/**
 * Machine Controller TileEntity - manages a Modular Machinery multiblock.
 * Extends AbstractMBModifierTE to leverage existing structure validation and
 * crafting logic.
 * Corresponds to the 'Q' symbol in structure definitions.
 * 
 * TODO: This class already extends AbstractMBModifierTE which provides:
 * - CraftingState management (via AbstractMachineTE)
 * - RedstoneMode support (via AbstractEnergyTE)
 * - Energy capability system (via AbstractEnergyTE)
 * Ensure we don't duplicate these existing systems.
 */
public class TEMachineController extends AbstractMBModifierTE {

    // IO port positions tracked during structure formation
    private final List<BlockPos> itemInputPorts = new ArrayList<>();
    private final List<BlockPos> itemOutputPorts = new ArrayList<>();
    private final List<BlockPos> energyInputPorts = new ArrayList<>();

    // Structure definition (will be loaded from JSON in Phase 1)
    private static IStructureDefinition<TEMachineController> STRUCTURE_DEFINITION;

    // Structure offsets per tier (currently only tier 1)
    private static final int[][] OFFSETS = { { 1, 1, 1 } // Tier 1 offset
    };

    public TEMachineController() {
        super();
    }

    // ========== Structure Definition ==========

    @Override
    protected IStructureDefinition<TEMachineController> getStructureDefinition() {
        // TODO: Load from JSON via StructureManager
        // For MVP, return null to use simplified check
        return STRUCTURE_DEFINITION;
    }

    @Override
    public int[][] getOffSet() {
        return OFFSETS;
    }

    @Override
    public String getStructurePieceName() {
        return "main";
    }

    @Override
    public int getTier() {
        return 1;
    }

    // ========== Structure Parts Tracking ==========

    @Override
    protected void clearStructureParts() {
        itemInputPorts.clear();
        itemOutputPorts.clear();
        energyInputPorts.clear();
    }

    @Override
    protected boolean addToMachine(Block block, int meta, int x, int y, int z) {
        BlockPos pos = new BlockPos(x, y, z, worldObj);

        if (block == MachineryBlocks.ITEM_INPUT_PORT) {
            if (!itemInputPorts.contains(pos)) {
                itemInputPorts.add(pos);
            }
            return true;
        } else if (block == MachineryBlocks.ITEM_OUTPUT_PORT) {
            if (!itemOutputPorts.contains(pos)) {
                itemOutputPorts.add(pos);
            }
            return true;
        } else if (block == MachineryBlocks.ENERGY_INPUT_PORT) {
            if (!energyInputPorts.contains(pos)) {
                energyInputPorts.add(pos);
            }
            return true;
        }

        return false;
    }

    // ========== Crafting Configuration ==========

    @Override
    public int getBaseDuration() {
        return 100; // 5 seconds base
    }

    @Override
    public int getMinDuration() {
        return 20; // 1 second minimum
    }

    @Override
    public int getMaxDuration() {
        return 1200; // 1 minute maximum
    }

    @Override
    public float getSpeedMultiplier() {
        return 1.0f; // No speed modifiers yet
    }

    @Override
    public void onFormed() {
        // Called when structure is successfully formed
    }

    @Override
    protected void finishCrafting() {
        // TODO: Implement recipe output
        resetCrafting();
    }

    @Override
    public int getCraftingEnergyCost() {
        // TODO: Get from current recipe
        return 100;
    }

    // ========== Player Interaction ==========

    /**
     * Called when a player right-clicks the controller block.
     */
    public void onRightClick(EntityPlayer player) {
        if (worldObj.isRemote) {
            return;
        }

        if (isFormed) {
            player.addChatComponentMessage(
                new ChatComponentText(
                    "[Machine] Status: " + getCraftingState().name()
                        + " | Item Inputs: "
                        + itemInputPorts.size()
                        + " | Item Outputs: "
                        + itemOutputPorts.size()
                        + " | Energy Inputs: "
                        + energyInputPorts.size()));
        } else {
            // Trigger structure check manually
            setPlayer(player);
            boolean success = structureCheck(
                getStructurePieceName(),
                getOffSet()[getTier() - 1][0],
                getOffSet()[getTier() - 1][1],
                getOffSet()[getTier() - 1][2]);

            if (success) {
                player.addChatComponentMessage(new ChatComponentText("[Machine] Structure formed successfully!"));
            } else {
                // For MVP without proper structure definition, use simple check
                if (trySimpleFormStructure()) {
                    player.addChatComponentMessage(new ChatComponentText("[Machine] Structure formed successfully!"));
                } else {
                    player.addChatComponentMessage(
                        new ChatComponentText("[Machine] Invalid structure. Check block placement."));
                }
            }
        }
    }

    /**
     * Simple 3x3x3 structure check for MVP testing.
     */
    private boolean trySimpleFormStructure() {
        clearStructureParts();

        int casingCount = 0;
        for (int dx = -1; dx <= 1; dx++) {
            for (int dy = -1; dy <= 1; dy++) {
                for (int dz = -1; dz <= 1; dz++) {
                    if (dx == 0 && dy == 0 && dz == 0) {
                        continue;
                    }

                    int checkX = xCoord + dx;
                    int checkY = yCoord + dy;
                    int checkZ = zCoord + dz;
                    Block block = worldObj.getBlock(checkX, checkY, checkZ);

                    if (block == MachineryBlocks.MACHINE_CASING) {
                        casingCount++;
                    } else if (block == MachineryBlocks.ITEM_INPUT_PORT || block == MachineryBlocks.ITEM_OUTPUT_PORT
                        || block == MachineryBlocks.ENERGY_INPUT_PORT) {
                            addToMachine(block, 0, checkX, checkY, checkZ);
                            casingCount++;
                        }
                }
            }
        }

        if (casingCount >= 26) {
            isFormed = true;
            onFormed();
            return true;
        }

        return false;
    }

    // ========== Getters ==========

    public List<BlockPos> getItemInputPorts() {
        return itemInputPorts;
    }

    public List<BlockPos> getItemOutputPorts() {
        return itemOutputPorts;
    }

    public List<BlockPos> getEnergyInputPorts() {
        return energyInputPorts;
    }
}
