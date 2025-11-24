package ruiseki.omoshiroikamo.common.item.backpack.wrapper;

public interface IToggleable {

    String ENABLED_TAG = "Enabled";

    boolean isEnabled();

    void setEnabled(boolean enabled);

    default void toggle() {
        setEnabled(!isEnabled());
    }

    class Impl implements IToggleable {

        @Override
        public boolean isEnabled() {
            return false;
        }

        @Override
        public void setEnabled(boolean enabled) {

        }
    }
}
