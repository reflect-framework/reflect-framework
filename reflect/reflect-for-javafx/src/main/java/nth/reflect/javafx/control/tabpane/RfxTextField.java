package nth.reflect.javafx.control.tabpane;

import com.jfoenix.controls.JFXTextField;

import nth.introspect.ui.style.MaterialColors;
import nth.reflect.javafx.control.style.RfxColorFactory;

public class RfxTextField extends JFXTextField {

	public RfxTextField() {
		focusColorProperty().set(RfxColorFactory.create(MaterialColors.getAccentColorSet().getBackground()));
		unFocusColorProperty().set(RfxColorFactory.create(MaterialColors.getContentColorSet().getForeground2()));
	}
	
}
