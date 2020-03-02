package nth.reflect.fw.ui.swing.tab.form.proppanel.field;

import nth.reflect.fw.gui.component.tab.form.FormTab;
import nth.reflect.fw.gui.component.tab.form.propertypanel.field.PropertyField;
import nth.reflect.fw.gui.component.tab.form.propertypanel.field.factory.PropertyFieldFactoryInfo;
import nth.reflect.fw.gui.component.tab.form.valuemodel.PropertyValueModel;

public class ComboBoxFieldFactory
		extends nth.reflect.fw.gui.component.tab.form.propertypanel.field.factory.ComboBoxFieldFactory {

	@Override
	public PropertyField create(PropertyFieldFactoryInfo info) {
		FormTab formTab = info.getFormTab();
		PropertyValueModel propertyValueModel = info.getPropertyValueModel();
		ComboBoxField comboBoxField = new ComboBoxField(formTab, propertyValueModel);
		return comboBoxField;
	}

}
