package nth.reflect.fw.javafx.control.tab.form.proppanel.field;

import nth.reflect.fw.gui.component.tab.form.propertypanel.field.PropertyField;
import nth.reflect.fw.gui.component.tab.form.propertypanel.field.factory.PropertyFieldFactoryInfo;

public class OneToOneOrManyFieldFactory
		extends nth.reflect.fw.gui.component.tab.form.propertypanel.field.factory.OneToOneOrManyFieldFactory {

	@Override
	public PropertyField create(PropertyFieldFactoryInfo info) {
		OneToOneOrManyField oneToOneOrManyField = new OneToOneOrManyField(info);
		return oneToOneOrManyField;
	}

}
