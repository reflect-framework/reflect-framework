package nth.reflect.ui.vaadin.tab.form.row.field;

import java.util.Optional;

import nth.reflect.fw.gui.component.tab.form.propertypanel.field.PropertyField;
import nth.reflect.fw.gui.component.tab.form.propertypanel.field.factory.PropertyFieldFactory;
import nth.reflect.fw.gui.component.tab.form.propertypanel.field.factory.PropertyFieldFactoryInfo;

public class ToDoFieldFactory implements PropertyFieldFactory {

	@Override
	public Optional<PropertyField> create(PropertyFieldFactoryInfo info) {
		ToDoField toDoField = new ToDoField(info);
		return Optional.of(toDoField);
	}

	@Override
	public boolean canCreate(PropertyFieldFactoryInfo info) {
		return true;
	}

}
