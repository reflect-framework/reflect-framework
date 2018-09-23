package nth.reflect.fw.javafx.control.view.form.proppanel.field;

import nth.reflect.fw.ui.valuemodel.PropertyValueModel;
import nth.reflect.fw.ui.view.form.propertypanel.PropertyField;
import nth.reflect.fw.ui.view.form.propertypanel.PropertyFieldWidth;

public class PasswordField extends RfxToDoField implements PropertyField {

	public PasswordField(PropertyValueModel propertyValueModel) {
	}

	@Override
	public PropertyFieldWidth getPropertyFieldWidth() {
		return PropertyFieldWidth.SMALL;
	}


}
