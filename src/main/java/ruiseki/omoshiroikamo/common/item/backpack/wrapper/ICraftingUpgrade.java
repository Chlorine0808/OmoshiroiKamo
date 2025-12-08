package ruiseki.omoshiroikamo.common.item.backpack.wrapper;

import ruiseki.omoshiroikamo.client.gui.modularui2.backpack.handler.CraftingStackHandler;

public interface ICraftingUpgrade {

    String MATRIX_TAG = "Matrix";
    String CRAFTING_DEST_TAG = "CraftingDest";

    CraftingStackHandler getMatrix();

    void setMatrix(CraftingStackHandler handler);

    CraftingDestination getCraftingDes();

    void setCraftingDes(CraftingDestination type);

    enum CraftingDestination {
        BACKPACK,
        INVENTORY;
    }
}
