package nth.reflect.fw.javafx.control.tab.form.proppanel.field;

import com.jfoenix.controls.JFXTextField;

import javafx.beans.value.ObservableValue;
import nth.reflect.fw.javafx.control.style.RfxStyleSelector;
import nth.reflect.fw.javafx.control.style.RfxStyleSheet;
import nth.reflect.fw.ui.style.ReflectColorName;
import nth.reflect.fw.ui.style.component.PropertyFieldStyle;
import nth.reflect.fw.ui.tab.form.propertypanel.PropertyField;
import nth.reflect.fw.ui.tab.form.propertypanel.PropertyFieldWidth;
import nth.reflect.fw.ui.valuemodel.PropertyValueModel;

public class TextField extends JFXTextField implements PropertyField {

	private final PropertyValueModel propertyValueModel;

	public TextField(PropertyValueModel propertyValueModel) {
		this.propertyValueModel = propertyValueModel;
		addStyleClass();
		textProperty().addListener(this::onChange);
	}

	public void onChange(ObservableValue<? extends String> observable, String oldValue, String newValue) {
		getPropertyValueModel().setValue(newValue);
	}

	protected void addStyleClass() {
		getStyleClass().add(RfxStyleSheet.createStyleClassName(TextField.class));
	}

	public static void appendStyleGroups(RfxStyleSheet styleSheet) {
		styleSheet.addStyleGroup(RfxStyleSelector.createFor(TextField.class)).getProperties()
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
		setText((String) propertyValue);
	}

	public PropertyValueModel getPropertyValueModel() {
		return propertyValueModel;
	}

}
