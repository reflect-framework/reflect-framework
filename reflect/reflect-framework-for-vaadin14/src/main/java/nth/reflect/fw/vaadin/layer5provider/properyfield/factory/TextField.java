package nth.reflect.fw.vaadin.layer5provider.properyfield.factory;

import java.util.Optional;

import nth.reflect.fw.gui.component.tab.form.propertypanel.field.PropertyField;
import nth.reflect.fw.gui.component.tab.form.propertypanel.field.PropertyFieldWidth;
import nth.reflect.fw.gui.component.tab.form.valuemodel.PropertyValueModel;
import nth.reflect.fw.layer1userinterface.item.Item;
import nth.reflect.fw.vaadin.css.StyleBuilder;

@SuppressWarnings("serial")
public class TextField extends com.vaadin.flow.component.textfield.TextField implements PropertyField {

	private final PropertyValueModel propertyValueModel;

	public TextField(PropertyValueModel propertyValueModel) {
		this.propertyValueModel = propertyValueModel;
		new StyleBuilder().setPadding(0).setFor(this);
		addChangeListener(this::onChange);
	}

	public void onChange(ChangeEvent<com.vaadin.flow.component.textfield.TextField> event) {
		if (propertyValueModel.canSetValue()) {
			propertyValueModel.setValue(getValue());
		}
	}

	@Override
	public void setEnabled(boolean enabled) {
		super.setEnabled(enabled);
	}

	@Override
	public void setValueFromDomainProperty(Object propertyValue) {
		if (propertyValue == null) {
			propertyValue = "";
		}
		//TODO use StringConverter
		super.setValue(propertyValue.toString());
	}

	@Override
	public PropertyFieldWidth getPropertyFieldWidth() {
		return PropertyFieldWidth.SMALL;
	}

	@Override
	public Optional<Item> getSelectionItem() {
		return Optional.empty();
	}

}
