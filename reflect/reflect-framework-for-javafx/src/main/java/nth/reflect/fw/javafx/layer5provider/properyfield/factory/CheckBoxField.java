package nth.reflect.fw.javafx.layer5provider.properyfield.factory;

import java.util.Optional;

import com.jfoenix.controls.JFXCheckBox;

import javafx.beans.value.ObservableValue;
import nth.reflect.fw.gui.component.tab.form.propertypanel.field.PropertyFieldStyle;
import nth.reflect.fw.gui.component.tab.form.propertypanel.field.PropertyFieldWidth;
import nth.reflect.fw.gui.component.tab.form.valuemodel.PropertyValueModel;
import nth.reflect.fw.gui.style.ReflectColorName;
import nth.reflect.fw.javafx.control.style.StyleSelector;
import nth.reflect.fw.javafx.control.style.StyleSheet;
import nth.reflect.fw.layer1userinterface.item.Item;

public class CheckBoxField extends JFXCheckBox
		implements nth.reflect.fw.gui.layer5provider.properyfield.factory.CheckBoxField {

	private final PropertyValueModel propertyValueModel;

	public CheckBoxField(PropertyValueModel propertyValueModel) {
		getStyleClass().add(StyleSheet.createStyleClassName(CheckBoxField.class));
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

	public static void appendStyleGroups(StyleSheet styleSheet) {
		styleSheet
				.addStyleGroup(StyleSelector.createFor(CheckBoxField.class))
				.getProperties()
				.setTextFill(ReflectColorName.CONTENT.FOREGROUND())
				.setProperty("-jfx-focus-color", ReflectColorName.ACCENT.BACKGROUND())
				.setProperty("-jfx-unfocus-color", ReflectColorName.CONTENT.BACKGROUND_12())
				.setProperty("-jfx-checked-color", ReflectColorName.CONTENT.FOREGROUND())
				.setProperty("-jfx-unchecked-color", ReflectColorName.CONTENT.FOREGROUND())
				.setFont(PropertyFieldStyle.getFont());
		// PropertyPanelStyle.getPaddingLeftRight(), 0,
		// PropertyPanelStyle.getPaddingLeftRight());

	}

	@Override
	public Optional<Item> getSelectionItem() {
		return Optional.empty();
	}
}
