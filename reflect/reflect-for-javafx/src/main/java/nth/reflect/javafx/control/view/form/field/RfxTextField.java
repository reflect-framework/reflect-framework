package nth.reflect.javafx.control.view.form.field;

import com.jfoenix.controls.JFXTextField;

import nth.introspect.ui.style.MaterialColorSetCssName;
import nth.introspect.ui.valuemodel.PropertyValueModel;
import nth.reflect.javafx.control.style.RfxStyleSelector;
import nth.reflect.javafx.control.style.RfxStyleSheet;

public class RfxTextField extends JFXTextField {

	public RfxTextField() {
		addStyleClass();
		setMaxWidth(600);
	}

	public RfxTextField(PropertyValueModel propertyValueModel) {
		this();
		//TODO bind propertyValueModel
	}
	
	protected void addStyleClass() {
		getStyleClass().add(RfxStyleSheet.createStyleClassName(RfxTextField.class));
	}

	
	public static void appendStyleGroups(RfxStyleSheet styleSheet) {
		styleSheet.addStyleGroup(RfxStyleSelector.createFor(RfxTextField.class)).getProperties()
		.setTextFill(MaterialColorSetCssName.CONTENT.FOREGROUND1())
		.setProperty("-jfx-focus-color", MaterialColorSetCssName.ACCENT.BACKGROUND())
		.setProperty("-jfx-unfocus-color", MaterialColorSetCssName.CONTENT.FOREGROUND2());
	}

	
}
