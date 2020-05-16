package nth.reflect.fw.javafx.layer5provider.properyfield.factory;

import nth.reflect.fw.gui.component.tab.form.FormTab;
import nth.reflect.fw.gui.component.tab.form.propertypanel.field.PropertyField;
import nth.reflect.fw.gui.component.tab.form.valuemodel.PropertyValueModel;

public class ManyToOneOrManyFieldFactory
		extends nth.reflect.fw.gui.layer5provider.properyfield.factory.ManyToOneOrManyFieldFactory {

	@Override
	public PropertyField create(FormTab formTab, PropertyValueModel propertyValueModel) {
		ManyToOneOrManyField manyToOneOrManyField = new ManyToOneOrManyField(formTab, propertyValueModel);
		return manyToOneOrManyField;
	}

}
