package nth.reflect.fw.javafx.control.tab.form.proppanel.field;

import nth.reflect.fw.gui.component.tab.form.propertypanel.field.PropertyField;
import nth.reflect.fw.gui.component.tab.form.propertypanel.field.factory.PropertyFieldFactoryInfo;

public class CheckBoxFieldFactory extends nth.reflect.fw.gui.component.tab.form.propertypanel.field.factory.CheckBoxFieldFactory {

	@Override
	public PropertyField create(PropertyFieldFactoryInfo info) {
		CheckBoxField checkBoxField=new CheckBoxField(info.getPropertyValueModel());
		return checkBoxField;
	}

}
