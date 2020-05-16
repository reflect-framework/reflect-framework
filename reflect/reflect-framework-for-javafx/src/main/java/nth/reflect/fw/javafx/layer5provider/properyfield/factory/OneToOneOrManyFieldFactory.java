package nth.reflect.fw.javafx.layer5provider.properyfield.factory;

import nth.reflect.fw.gui.component.tab.form.FormTab;
import nth.reflect.fw.gui.component.tab.form.propertypanel.field.PropertyField;
import nth.reflect.fw.gui.component.tab.form.valuemodel.PropertyValueModel;

public class OneToOneOrManyFieldFactory
		extends nth.reflect.fw.gui.layer5provider.properyfield.factory.OneToOneOrManyFieldFactory {

	@Override
	public PropertyField create(FormTab formTab, PropertyValueModel propertyValueModel) {
		OneToOneOrManyField oneToOneOrManyField = new OneToOneOrManyField(propertyValueModel);
		return oneToOneOrManyField;
	}

}
