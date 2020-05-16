package nth.reflect.fw.vaadin.layer5provider.properyfield.factory;

import nth.reflect.fw.gui.component.tab.form.FormTab;
import nth.reflect.fw.gui.component.tab.form.propertypanel.field.PropertyField;
import nth.reflect.fw.gui.component.tab.form.valuemodel.PropertyValueModel;

public class CheckBoxFieldFactory extends nth.reflect.fw.gui.layer5provider.properyfield.factory.CheckBoxFieldFactory {

	@Override
	public PropertyField create(FormTab formTab, PropertyValueModel propertyValueModel) {
		CheckBoxField checkBoxField = new CheckBoxField(propertyValueModel);
		return checkBoxField;
	}
}
