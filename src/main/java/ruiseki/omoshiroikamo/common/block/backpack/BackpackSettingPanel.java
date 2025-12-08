package ruiseki.omoshiroikamo.common.block.backpack;

import com.cleanroommc.modularui.api.drawable.IKey;
import com.cleanroommc.modularui.drawable.AdaptableUITexture;
import com.cleanroommc.modularui.drawable.UITexture;
import com.cleanroommc.modularui.screen.ModularPanel;
import com.cleanroommc.modularui.screen.ModularScreen;
import com.cleanroommc.modularui.screen.RichTooltip;
import com.cleanroommc.modularui.screen.viewport.ModularGuiContext;
import com.cleanroommc.modularui.theme.WidgetTheme;
import com.cleanroommc.modularui.widgets.TextWidget;
import com.cleanroommc.modularui.widgets.layout.Column;

import ruiseki.omoshiroikamo.client.gui.modularui2.MGuiTextures;
import ruiseki.omoshiroikamo.client.gui.modularui2.backpack.widget.MemorySettingWidget;
import ruiseki.omoshiroikamo.client.gui.modularui2.backpack.widget.SortingSettingWidget;
import ruiseki.omoshiroikamo.client.gui.modularui2.backpack.widget.TabWidget;
import ruiseki.omoshiroikamo.client.gui.modularui2.backpack.widget.TabWidget.ExpandDirection;
import ruiseki.omoshiroikamo.common.util.lib.LibMisc;

public class BackpackSettingPanel extends ModularPanel {

    private static final int HEIGHT = 95;

    private static final AdaptableUITexture LAYERED_TAB_TEXTURE = (AdaptableUITexture) UITexture.builder()
        .location(LibMisc.MOD_ID, "gui/gui_controls")
        .imageSize(256, 256)
        .xy(128, 0, 124, 256)
        .adaptable(4)
        .tiled()
        .build();

    private final BackpackPanel parent;

    private final TabWidget memoryTab;
    private final TabWidget sortTab;

    public BackpackSettingPanel(BackpackPanel parent) {
        super("backpack_settings");
        this.parent = parent;

        size(parent.getArea().width, HEIGHT).relative(parent)
            .bottom(0);

        memoryTab = new TabWidget(0, 0, ExpandDirection.LEFT);
        memoryTab.tooltipStatic(
            tooltip -> tooltip.addLine(IKey.lang("gui.memory_settings"))
                .pos(RichTooltip.Pos.NEXT_TO_MOUSE));
        memoryTab.setExpandedWidget(new MemorySettingWidget(parent, this, memoryTab));
        memoryTab.setTabIcon(MGuiTextures.BRAIN_ICON);

        sortTab = new TabWidget(1, ExpandDirection.LEFT);
        sortTab.tooltipStatic(
            tooltip -> tooltip.addLine(IKey.lang("gui.sorting_settings"))
                .pos(RichTooltip.Pos.NEXT_TO_MOUSE));
        sortTab.setExpandedWidget(new SortingSettingWidget(parent, this, sortTab));
        sortTab.setTabIcon(MGuiTextures.NO_SORT_ICON);

        Column grid = (Column) new Column().size(parent.getArea().width - 14, HEIGHT - 14)
            .margin(7)
            .child(new TextWidget<>(IKey.lang("gui.configuration_tab")).leftRel(0.5f));

        child(grid).child(memoryTab)
            .child(sortTab);
    }

    public void updateTabState(int fromIndex) {
        memoryTab.setEnabled(true);
        sortTab.setEnabled(true);

        switch (fromIndex) {
            case 0:
                sortTab.setShowExpanded(false);
                parent.isSortingSettingTabOpened = false;
                sortTab.setEnabled(!memoryTab.isShowExpanded());
                break;

            case 1:
                memoryTab.setShowExpanded(false);
                parent.isMemorySettingTabOpened = false;
                break;
        }
    }

    @Override
    public boolean isDraggable() {
        return false;
    }

    @Override
    public void onOpen(ModularScreen screen) {
        super.onOpen(screen);
        parent.isMemorySettingTabOpened = memoryTab.isShowExpanded();
        parent.shouldMemorizeRespectNBT = ((MemorySettingWidget) memoryTab.getExpandedWidget()).isRespectNBT();
        parent.isSortingSettingTabOpened = sortTab.isShowExpanded();
    }

    @Override
    public void onClose() {
        super.onClose();
        parent.isMemorySettingTabOpened = false;
        parent.shouldMemorizeRespectNBT = false;
        parent.isSortingSettingTabOpened = false;
    }

    @Override
    public void postDraw(ModularGuiContext context, boolean transformed) {
        super.postDraw(context, transformed);

        // Nasty hack to draw over upgrade tabs
        LAYERED_TAB_TEXTURE.draw(
            context,
            0,
            0,
            6,
            getFlex().getArea().height,
            WidgetTheme.getDefault()
                .getTheme());
    }

}
