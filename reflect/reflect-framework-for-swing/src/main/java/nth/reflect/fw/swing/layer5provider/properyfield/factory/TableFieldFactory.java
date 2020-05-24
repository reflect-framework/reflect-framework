package nth.reflect.fw.swing.layer5provider.properyfield.factory;

import nth.reflect.fw.gui.component.tab.form.FormTab;
import nth.reflect.fw.gui.component.tab.form.propertypanel.field.PropertyField;
import nth.reflect.fw.gui.component.tab.form.valuemodel.PropertyValueModel;

public class TableFieldFactory
		extends nth.reflect.fw.gui.layer5provider.properyfield.factory.TableFieldFactory {

	@Override
	public PropertyField create(FormTab formTab, PropertyValueModel propertyValueModel) {
		TableField tableField = new TableField(formTab, propertyValueModel);
		return tableField;
	}
}
