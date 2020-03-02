package nth.reflect.fw.javafx.control.tab.form.proppanel.field;

import nth.reflect.fw.gui.component.tab.form.propertypanel.field.PropertyField;
import nth.reflect.fw.gui.component.tab.form.propertypanel.field.factory.PropertyFieldFactoryInfo;

public class ManyToOneOrManyFieldFactory extends nth.reflect.fw.gui.component.tab.form.propertypanel.field.factory.ManyToOneOrManyFieldFactory {

	@Override
	public PropertyField create(PropertyFieldFactoryInfo info) {
		ManyToOneOrManyField manyToOneOrManyField = new ManyToOneOrManyField(info);
		return manyToOneOrManyField;
	}

}
