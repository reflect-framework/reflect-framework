package nth.reflect.ui.vaadin.tab.form.row.field;

import java.util.Optional;

import nth.reflect.fw.gui.component.tab.form.propertypanel.field.PropertyField;
import nth.reflect.fw.gui.component.tab.form.propertypanel.field.factory.PropertyFieldFactoryInfo;

public class CheckBoxFieldFactory extends nth.reflect.fw.gui.component.tab.form.propertypanel.field.factory.CheckBoxFieldFactory {

	@Override
	public Optional<PropertyField> create(PropertyFieldFactoryInfo info) {
		CheckBoxField checkBoxField = new CheckBoxField(info);
		return Optional.of(checkBoxField);
	}
}
