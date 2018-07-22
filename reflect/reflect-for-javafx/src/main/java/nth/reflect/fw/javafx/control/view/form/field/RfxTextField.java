package nth.reflect.fw.javafx.control.view.form.field;

import com.jfoenix.controls.JFXTextField;

import javafx.beans.value.ObservableValue;
import nth.reflect.fw.javafx.control.style.RfxFontFactory;
import nth.reflect.fw.javafx.control.style.RfxStyleSelector;
import nth.reflect.fw.javafx.control.style.RfxStyleSheet;
import nth.reflect.fw.layer5provider.reflection.info.property.PropertyInfo;
import nth.reflect.fw.ui.style.MaterialColorSetCssName;
import nth.reflect.fw.ui.style.MaterialFont;
import nth.reflect.fw.ui.valuemodel.PropertyValueModel;

public class RfxTextField extends JFXTextField {

	private static final int FONT_SIZE = 16;
	private final PropertyInfo propertyInfo;
	private Object domainObject;

	public RfxTextField(PropertyValueModel propertyValueModel) {
		addStyleClass();
		setMaxWidth(600);
		setFont(RfxFontFactory.create(MaterialFont.getRobotoRegular(FONT_SIZE)));
		domainObject=propertyValueModel.getDomainObject();
		propertyInfo = propertyValueModel.getPropertyInfo();
		setInitialValue(propertyValueModel, propertyInfo);
		setEditable(propertyValueModel.canSetValue());
		textProperty().addListener(this::onChange);
	}


	private void setInitialValue(PropertyValueModel propertyValueModel, PropertyInfo propertyInfo) {
		String text = propertyInfo.getFormatedValue(propertyValueModel.getDomainObject());
		setText(text);
	}
	
	public void onChange(ObservableValue<? extends String> observable, String oldValue, String newValue) {
		propertyInfo.setValue(domainObject, newValue);
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
