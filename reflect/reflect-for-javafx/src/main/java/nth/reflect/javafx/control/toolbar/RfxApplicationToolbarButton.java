package nth.reflect.javafx.control.toolbar;

import javafx.geometry.Insets;
import nth.introspect.ui.style.MaterialColors;
import nth.reflect.javafx.control.RfxControl;
import nth.reflect.javafx.control.button.RfxButton;
import nth.reflect.javafx.control.fonticon.RfxFontIconName;

public class RfxApplicationToolbarButton extends RfxButton implements RfxControl {

	private static final double ICON_HEIGHT = 34;

	public RfxApplicationToolbarButton(RfxFontIconName iconName) {
		super(iconName);
		setButtonType(ButtonType.FLAT);
		setColorSet(MaterialColors.getPrimaryColorSet());
		setMinHeight(ICON_HEIGHT);
		setPadding(new Insets(0, 16, 0, 16));
	}
}
