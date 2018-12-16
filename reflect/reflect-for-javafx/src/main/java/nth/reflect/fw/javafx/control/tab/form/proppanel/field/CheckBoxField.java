package nth.reflect.fw.javafx.control.tab.form.proppanel.field;

import com.jfoenix.controls.JFXCheckBox;

import javafx.beans.value.ObservableValue;
import nth.reflect.fw.javafx.control.style.RfxStyleSelector;
import nth.reflect.fw.javafx.control.style.RfxStyleSheet;
import nth.reflect.fw.ui.style.ReflectColorName;
import nth.reflect.fw.ui.style.component.PropertyFieldStyle;
import nth.reflect.fw.ui.tab.form.propertypanel.PropertyField;
import nth.reflect.fw.ui.tab.form.propertypanel.PropertyFieldWidth;
import nth.reflect.fw.ui.valuemodel.PropertyValueModel;

public class CheckBoxField extends JFXCheckBox implements PropertyField {

	private final PropertyValueModel propertyValueModel;

	public CheckBoxField(PropertyValueModel propertyValueModel) {
		getStyleClass().add(RfxStyleSheet.createStyleClassName(CheckBoxField.class));
		this.propertyValueModel = propertyValueModel;
		selectedProperty().addListener(this::onChange);
	}

	public void onChange(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
		propertyValueModel.setValue(newValue);
	}

	@Override
	public PropertyFieldWidth getPropertyFieldWidth() {
		return PropertyFieldWidth.SMALL;
	}

	@Override
	public void setEnabled(boolean enabled) {
		super.setDisable(!enabled);
	}

	@Override
	public void setValueFromDomainProperty(Object propertyValue) {
		if (propertyValue == null) {
			setSelected(false);
		} else {
			setSelected((boolean) propertyValue);
		}
	}

	public static void appendStyleGroups(RfxStyleSheet styleSheet) {
		styleSheet.addStyleGroup(RfxStyleSelector.createFor(CheckBoxField.class)).getProperties()
				.setTextFill(ReflectColorName.CONTENT.FOREGROUND())
				.setProperty("-jfx-focus-color", ReflectColorName.ACCENT.BACKGROUND())
				.setProperty("-jfx-unfocus-color", ReflectColorName.CONTENT.BACKGROUND_12())
				.setProperty("-jfx-checked-color", ReflectColorName.CONTENT.FOREGROUND())
				.setProperty("-jfx-unchecked-color", ReflectColorName.CONTENT.FOREGROUND())
				.setFont(PropertyFieldStyle.getFont());
		// PropertyPanelStyle.getPaddingLeftRight(), 0,
		// PropertyPanelStyle.getPaddingLeftRight());

	}
}
