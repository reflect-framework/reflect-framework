package nth.reflect.fw.swing.layer5provider.properyfield.factory;

import nth.reflect.fw.gui.component.tab.form.FormTab;
import nth.reflect.fw.gui.component.tab.form.propertypanel.field.PropertyField;
import nth.reflect.fw.gui.component.tab.form.valuemodel.PropertyValueModel;

public class DomainObjectFieldFactory
		extends nth.reflect.fw.gui.layer5provider.properyfield.factory.DomainObjectFieldFactory {

	@Override
	public PropertyField create(FormTab formTab, PropertyValueModel propertyValueModel) {
		DomainObjectField domainObjectField = new DomainObjectField(formTab, propertyValueModel);
		return domainObjectField;
	}

}
