package nth.reflect.fw.ui.swing.tab.form.proppanel.field;

import java.util.Optional;

import nth.reflect.fw.gui.component.tab.form.propertypanel.field.PropertyField;
import nth.reflect.fw.gui.component.tab.form.propertypanel.field.factory.PropertyFieldFactoryInfo;

public class OneToOneOrManyFieldFactory
		extends nth.reflect.fw.gui.component.tab.form.propertypanel.field.factory.OneToOneOrManyFieldFactory {

	@Override
	public Optional<PropertyField> create(PropertyFieldFactoryInfo info) {
		OneToOneOrManyField oneToOneOrManyField = new OneToOneOrManyField(info);
		return Optional.of(oneToOneOrManyField);
	}

}
