package nth.reflect.fw.javafx.control.view.form.proppanel.field;

import com.jfoenix.controls.JFXTextField;

import javafx.beans.value.ObservableValue;
import nth.reflect.fw.javafx.control.style.RfxStyleSelector;
import nth.reflect.fw.javafx.control.style.RfxStyleSheet;
import nth.reflect.fw.layer5provider.reflection.info.property.PropertyInfo;
import nth.reflect.fw.ui.style.MaterialColorSetCssName;
import nth.reflect.fw.ui.style.component.PropertyFieldStyle;
import nth.reflect.fw.ui.valuemodel.PropertyValueModel;
import nth.reflect.fw.ui.view.form.propertypanel.PropertyField;
import nth.reflect.fw.ui.view.form.propertypanel.PropertyFieldWidth;

public class TextField extends JFXTextField implements PropertyField {

	private final PropertyInfo propertyInfo;
	private Object domainObject;

	public TextField(PropertyValueModel propertyValueModel) {
		addStyleClass();
		domainObject = propertyValueModel.getDomainObject();
		propertyInfo = propertyValueModel.getPropertyInfo();
		textProperty().addListener(this::onChange);
	}

	public void onChange(ObservableValue<? extends String> observable, String oldValue, String newValue) {
		propertyInfo.setValue(domainObject, newValue);
	}

	protected void addStyleClass() {
		getStyleClass().add(RfxStyleSheet.createStyleClassName(TextField.class));
	}

	public static void appendStyleGroups(RfxStyleSheet styleSheet) {
		styleSheet.addStyleGroup(RfxStyleSelector.createFor(TextField.class)).getProperties()
				.setTextFill(MaterialColorSetCssName.CONTENT.FOREGROUND1())
				.setProperty("-jfx-focus-color", MaterialColorSetCssName.ACCENT.BACKGROUND())
				.setProperty("-jfx-unfocus-color", MaterialColorSetCssName.CONTENT.FOREGROUND2())
				.setFont(PropertyFieldStyle.getFont());
				//TODO but with line over full PropertyPanel: .setPadding(0, PropertyPanelStyle.getPaddingLeftRight(), 0, PropertyPanelStyle.getPaddingLeftRight());

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

}
