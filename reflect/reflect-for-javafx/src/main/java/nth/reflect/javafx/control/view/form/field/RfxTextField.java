package nth.reflect.javafx.control.view.form.field;

import com.jfoenix.controls.JFXTextField;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import nth.introspect.layer5provider.reflection.info.property.PropertyInfo;
import nth.introspect.ui.style.MaterialColorSetCssName;
import nth.introspect.ui.valuemodel.PropertyValueModel;
import nth.reflect.javafx.control.style.RfxStyleSelector;
import nth.reflect.javafx.control.style.RfxStyleSheet;

public class RfxTextField extends JFXTextField {

	private final PropertyInfo propertyInfo;
	private Object domainObject;

	public RfxTextField(PropertyValueModel propertyValueModel) {
		addStyleClass();
		setMaxWidth(600);
		domainObject=propertyValueModel.getDomainObject();
		propertyInfo = propertyValueModel.getPropertyInfo();
		setInitialValue(propertyValueModel, propertyInfo);
		textProperty().addListener(this::onChange);
	}


	private void setInitialValue(PropertyValueModel propertyValueModel, PropertyInfo propertyInfo) {
		Object value = propertyValueModel.getValue();
		String text = propertyInfo.getFormatedValue(value);
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
