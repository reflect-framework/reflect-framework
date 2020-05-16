package nth.reflect.fw.vaadin.layer5provider.properyfield.factory;

import nth.reflect.fw.gui.component.tab.form.FormTab;
import nth.reflect.fw.gui.component.tab.form.propertypanel.field.PropertyField;
import nth.reflect.fw.gui.component.tab.form.valuemodel.PropertyValueModel;
import nth.reflect.fw.gui.layer5provider.properyfield.factory.PropertyFieldFactory;

public class ToDoFieldFactory implements PropertyFieldFactory {

	@Override
	public PropertyField create(FormTab formTab, PropertyValueModel propertyValueModel) {
		ToDoField toDoField = new ToDoField(propertyValueModel);
		return toDoField;
	}

	@Override
	public boolean canCreate(FormTab formTab, PropertyValueModel propertyValueModel) {
		return true;
	}

}
