package nth.reflect.fw.javafx.control.tab.form.proppanel.field;

import java.util.Optional;

import nth.reflect.fw.gui.component.tab.form.propertypanel.field.PropertyField;
import nth.reflect.fw.gui.component.tab.form.propertypanel.field.factory.PropertyFieldFactoryInfo;

public class TableFieldFactory extends nth.reflect.fw.gui.component.tab.form.propertypanel.field.factory.TableFieldFactory {

	@Override
	public Optional<PropertyField> create(PropertyFieldFactoryInfo info) {
		ManyToOneOrManyField manyToOneOrManyField = new ManyToOneOrManyField(info);
		return Optional.of(manyToOneOrManyField);
	}

}
