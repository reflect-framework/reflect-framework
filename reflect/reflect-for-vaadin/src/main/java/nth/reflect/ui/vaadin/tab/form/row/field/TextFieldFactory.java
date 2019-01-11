package nth.reflect.ui.vaadin.tab.form.row.field;

import nth.reflect.fw.gui.component.tab.form.FormTab;
import nth.reflect.fw.gui.component.tab.form.propertypanel.PropertyField;
import nth.reflect.fw.gui.component.tab.form.propertypanel.PropertyFieldFactory;
import nth.reflect.fw.gui.component.tab.form.valuemodel.PropertyValueModel;
import nth.reflect.fw.layer5provider.reflection.behavior.fieldmode.FieldModeType;
import nth.reflect.fw.layer5provider.reflection.info.property.PropertyInfo;
import nth.reflect.fw.layer5provider.reflection.info.type.PropertyType;

public class TextFieldFactory implements PropertyFieldFactory {

	@Override
	public PropertyField create(FormTab formTab, PropertyValueModel propertyValueModel) {
		return new TextField();
	}

	@Override
	public boolean canCreateFor(PropertyValueModel propertyValueModel) {
		PropertyInfo propertyInfo = propertyValueModel.getPropertyInfo();
		PropertyType properyType = propertyInfo.getPropertyType();
		boolean isCharSequence = CharSequence.class.isAssignableFrom(properyType.getType());
		boolean isFieldModeText = propertyInfo.getFieldMode() == FieldModeType.TEXT;
		return isCharSequence && isFieldModeText;
	}

}
