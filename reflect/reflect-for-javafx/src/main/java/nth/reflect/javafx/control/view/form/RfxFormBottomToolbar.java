package nth.reflect.javafx.control.view.form;

import javafx.geometry.Pos;
import nth.introspect.ui.style.MaterialColorSetCssName;
import nth.reflect.javafx.control.button.RfxContentButton;
import nth.reflect.javafx.control.style.RfxStyleSelector;
import nth.reflect.javafx.control.style.RfxStyleSheet;
import nth.reflect.javafx.control.toolbar.RfxToolbar;

public class RfxFormBottomToolbar extends RfxToolbar {

	public RfxFormBottomToolbar(){
		getStyleClass().add(RfxStyleSheet.createStyleClassName(RfxFormBottomToolbar.class));
		
		getChildren().add(new RfxContentButton("Test") );
	}
	
	public static void appendStyleGroups(RfxStyleSheet styleSheet) {
		styleSheet.addStyleGroup(RfxStyleSelector.createFor(RfxFormBottomToolbar.class)).getProperties()
		.setBackground(MaterialColorSetCssName.CONTENT.BACKGROUND_HIGHLIGHTED())
		.setHeight(60)
		.setMinWidth(300)
		.setPadding(0)
		.setAlignment(Pos.CENTER);
	}

}
