package ruiseki.omoshiroikamo.client.gui.modularui2.backpack.widget;

import com.cleanroommc.modularui.drawable.AdaptableUITexture;
import com.cleanroommc.modularui.drawable.UITexture;
import com.cleanroommc.modularui.screen.viewport.ModularGuiContext;
import com.cleanroommc.modularui.theme.WidgetThemeEntry;
import com.cleanroommc.modularui.widget.ParentWidget;
import com.cleanroommc.modularui.widgets.layout.Column;
import com.cleanroommc.modularui.widgets.slot.ItemSlot;
import ruiseki.omoshiroikamo.common.item.backpack.wrapper.IBatteryUpgrade;
import ruiseki.omoshiroikamo.common.util.lib.LibMisc;

public class BatterySlotWidget extends Column {

    public BatterySlotWidget(IBatteryUpgrade upgrade) {
        width(ItemSlot.SIZE * 2);
        child(new BatteryWidget(upgrade).height(this.getArea().height));
    }

    public static class BatteryWidget extends ParentWidget<BatteryWidget> {

        public static final AdaptableUITexture TOP_TEXTURE = (AdaptableUITexture) UITexture.builder()
            .location(LibMisc.MOD_ID, "gui/gui_controls")
            .imageSize(256, 256)
            .xy(47, 48, 63, 52)
            .adaptable(1)
            .tiled()
            .build();

        public static final AdaptableUITexture BOTTOM_TEXTURE = (AdaptableUITexture) UITexture.builder()
            .location(LibMisc.MOD_ID, "gui/gui_controls")
            .imageSize(256, 256)
            .xy(47, 52, 63, 55)
            .adaptable(1)
            .tiled()
            .build();

        private final IBatteryUpgrade upgrade;

        public BatteryWidget(IBatteryUpgrade upgrade) {
            this.upgrade = upgrade;
        }

        @Override
        public void drawOverlay(ModularGuiContext context, WidgetThemeEntry<?> widgetTheme) {
            super.drawOverlay(context, widgetTheme);
            TOP_TEXTURE.draw(0, 0,16, 4);
            BOTTOM_TEXTURE.draw(0, this.getArea().height - 3,16, 3);
        }
    }

}
