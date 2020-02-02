package nth.reflect.fw.ui.swing.tab.form.proppanel.field;

import java.util.Optional;

import nth.reflect.fw.gui.component.tab.form.propertypanel.field.PropertyField;
import nth.reflect.fw.gui.component.tab.form.propertypanel.field.factory.PropertyFieldFactoryInfo;

public class ManyToOneOrManyFieldFactory
		extends nth.reflect.fw.gui.component.tab.form.propertypanel.field.factory.ManyToOneOrManyFieldFactory {

	@Override
	public Optional<PropertyField> create(PropertyFieldFactoryInfo info) {
		ManyToOneOrManyField manyToOneOrManyField = new ManyToOneOrManyField(info);
		return Optional.of(manyToOneOrManyField);
	}
}
