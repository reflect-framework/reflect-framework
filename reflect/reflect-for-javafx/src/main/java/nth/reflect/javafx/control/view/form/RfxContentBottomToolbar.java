package nth.reflect.javafx.control.view.form;

import javafx.geometry.Pos;
import nth.introspect.ui.style.MaterialColorSetCssName;
import nth.reflect.javafx.control.button.RfxContentBottomToolbarButton;
import nth.reflect.javafx.control.button.RfxContentButton;
import nth.reflect.javafx.control.style.RfxStyleSelector;
import nth.reflect.javafx.control.style.RfxStyleSheet;
import nth.reflect.javafx.control.toolbar.RfxToolbar;

public class RfxContentBottomToolbar extends RfxToolbar {

	private static final int MIN_HEIGHT = 42;//Button height dense=32 * 1 1/3= 42
	private static final int SPACING = 8;

	public RfxContentBottomToolbar(){
		getStyleClass().add(RfxStyleSheet.createStyleClassName(RfxContentBottomToolbar.class));
	}
	
	public static void appendStyleGroups(RfxStyleSheet styleSheet) {
		styleSheet.addStyleGroup(RfxStyleSelector.createFor(RfxContentBottomToolbar.class)).getProperties()
		.setBackground(MaterialColorSetCssName.CONTENT.BACKGROUND_HIGHLIGHTED())
		.setMinHeight(MIN_HEIGHT)
		.setSpacing(SPACING)
		.setAlignment(Pos.CENTER);
	}

}
