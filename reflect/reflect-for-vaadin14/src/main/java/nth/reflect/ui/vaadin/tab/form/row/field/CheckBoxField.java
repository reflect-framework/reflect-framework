package nth.reflect.ui.vaadin.tab.form.row.field;

import java.util.Optional;

import com.vaadin.flow.component.checkbox.Checkbox;

import nth.reflect.fw.gui.component.tab.form.propertypanel.field.PropertyField;
import nth.reflect.fw.gui.component.tab.form.propertypanel.field.PropertyFieldWidth;
import nth.reflect.fw.gui.component.tab.form.propertypanel.field.factory.PropertyFieldFactoryInfo;
import nth.reflect.fw.gui.component.tab.form.valuemodel.PropertyValueModel;
import nth.reflect.fw.layer1userinterface.item.Item;

public class CheckBoxField extends Checkbox implements PropertyField {

	private static final long serialVersionUID = 2122876010423229628L;
	private final PropertyValueModel propertyValueModel;

	public CheckBoxField(PropertyFieldFactoryInfo info) {
		this.propertyValueModel = info.getPropertyValueModel();
		addChangeListener(this::onChange);
	}

	public void onChange(ChangeEvent<Checkbox> event) {
		if (propertyValueModel.canSetValue()) {
			propertyValueModel.setValue(getValue());
		}
	}

	@Override
	public PropertyFieldWidth getPropertyFieldWidth() {
		return PropertyFieldWidth.SMALL;
	}

	@Override
	public void setEnabled(boolean enabled) {
		super.setEnabled(enabled) ;
	}

	@Override
	public void setValueFromDomainProperty(Object propertyValue) {
		boolean booleanValue = (boolean) propertyValue;
		setValue(booleanValue);
	}

	@Override
	public Optional<Item> getSelectionItem() {
		return Optional.empty();
	}

}
