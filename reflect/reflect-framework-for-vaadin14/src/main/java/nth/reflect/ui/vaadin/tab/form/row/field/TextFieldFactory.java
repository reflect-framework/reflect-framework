package nth.reflect.ui.vaadin.tab.form.row.field;

import nth.reflect.fw.gui.component.tab.form.propertypanel.field.PropertyField;
import nth.reflect.fw.gui.component.tab.form.propertypanel.field.factory.PropertyFieldFactoryInfo;
import nth.reflect.fw.gui.component.tab.form.valuemodel.PropertyValueModel;

public class TextFieldFactory
		extends nth.reflect.fw.gui.component.tab.form.propertypanel.field.factory.TextFieldFactory {

	@Override
	public PropertyField create(PropertyFieldFactoryInfo info) {
		PropertyValueModel propertyValueModel = info.getPropertyValueModel();
		TextField textField = new TextField(propertyValueModel);
		return textField;
	}

}