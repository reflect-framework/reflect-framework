package nth.reflect.fw.javafx.control.tab.form.proppanel.field;

import java.text.Format;
import java.util.Optional;

import com.jfoenix.controls.JFXTextField;

import javafx.beans.value.ObservableValue;
import nth.reflect.fw.gui.component.tab.form.propertypanel.PropertyField;
import nth.reflect.fw.gui.component.tab.form.propertypanel.PropertyFieldStyle;
import nth.reflect.fw.gui.component.tab.form.propertypanel.PropertyFieldWidth;
import nth.reflect.fw.gui.component.tab.form.valuemodel.PropertyValueModel;
import nth.reflect.fw.gui.style.ReflectColorName;
import nth.reflect.fw.javafx.control.style.StyleSelector;
import nth.reflect.fw.javafx.control.style.StyleSheet;
import nth.reflect.fw.layer1userinterface.item.Item;

public class TextField extends JFXTextField implements PropertyField {

	private final PropertyValueModel propertyValueModel;

	public TextField(PropertyValueModel propertyValueModel) {
		this.propertyValueModel = propertyValueModel;
		addStyleClass();
		textProperty().addListener(this::onChange);
	}

	public void onChange(ObservableValue<? extends String> observable, String oldValue, String newValue) {
		if (propertyValueModel.canSetValue()) {
			getPropertyValueModel().setValue(newValue);
		}
	}

	protected void addStyleClass() {
		getStyleClass().add(StyleSheet.createStyleClassName(TextField.class));
	}

	public static void appendStyleGroups(StyleSheet styleSheet) {
		styleSheet.addStyleGroup(StyleSelector.createFor(TextField.class)).getProperties()
				.setTextFill(ReflectColorName.CONTENT.FOREGROUND())
				.setProperty("-jfx-focus-color", ReflectColorName.ACCENT.BACKGROUND())
				.setProperty("-jfx-unfocus-color", ReflectColorName.CONTENT.BACKGROUND_12())
				.setFont(PropertyFieldStyle.getFont());
		// TODO but with line over full PropertyPanel: .setPadding(0,
		// PropertyPanelStyle.getPaddingLeftRight(), 0,
		// PropertyPanelStyle.getPaddingLeftRight());

	}

	@Override
	public PropertyFieldWidth getPropertyFieldWidth() {
		return PropertyFieldWidth.SMALL;
	}

	@Override
	public void setEnabled(boolean enabled) {
		setEditable(enabled);
	}

	@Override
	public void setValueFromDomainProperty(Object propertyValue) {
		Format format = propertyValueModel.getPropertyInfo().getFormat();
		if (propertyValue == null) {
			setText("");
		} else {
			String stringValue = format.format(propertyValue);
			setText(stringValue);
		}
	}

	public PropertyValueModel getPropertyValueModel() {
		return propertyValueModel;
	}

	@Override
	public Optional<Item> getSelectionItem() {
		return Optional.empty();
	}

}
