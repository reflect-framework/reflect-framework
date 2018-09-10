package nth.reflect.ui.vaadin.view.form.field;

import nth.reflect.fw.layer5provider.reflection.behavior.fieldmode.FieldModeType;
import nth.reflect.fw.layer5provider.reflection.info.property.PropertyInfo;
import nth.reflect.fw.layer5provider.reflection.info.type.PropertyType;
import nth.reflect.fw.ui.valuemodel.PropertyValueModel;
import nth.reflect.fw.ui.view.FormFieldSubFactory;

public class TextFieldSubFactory implements FormFieldSubFactory<FormFieldForVaadin> {

	
	@Override
	public FormFieldForVaadin create(PropertyValueModel propertyValueModel) {
		return new TextField(propertyValueModel);
	}

	@Override
	public boolean canCreate(PropertyValueModel propertyValueModel) {
		PropertyInfo propertyInfo = propertyValueModel.getPropertyInfo();
		PropertyType properyType = propertyInfo.getPropertyType();
		boolean isCharSequence = CharSequence.class.isAssignableFrom(properyType.getType());
		boolean isFieldModeText = propertyInfo.getFieldMode()==FieldModeType.TEXT;
		return isCharSequence && isFieldModeText;
	}

}
