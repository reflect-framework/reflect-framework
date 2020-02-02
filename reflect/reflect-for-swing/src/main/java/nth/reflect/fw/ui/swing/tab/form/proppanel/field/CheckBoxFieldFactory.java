package nth.reflect.fw.ui.swing.tab.form.proppanel.field;

import java.util.Optional;

import nth.reflect.fw.gui.component.tab.form.propertypanel.field.PropertyField;
import nth.reflect.fw.gui.component.tab.form.propertypanel.field.factory.PropertyFieldFactoryInfo;
import nth.reflect.fw.gui.component.tab.form.valuemodel.PropertyValueModel;

public class CheckBoxFieldFactory
		extends nth.reflect.fw.gui.component.tab.form.propertypanel.field.factory.CheckBoxFieldFactory {

	@Override
	public Optional<PropertyField> create(PropertyFieldFactoryInfo info) {
		PropertyValueModel propertyValueModel = info.getPropertyValueModel();
		CheckBoxField checkBoxField = new CheckBoxField(propertyValueModel);
		return Optional.of(checkBoxField);
	}

}
