package nth.reflect.fw.javafx.control.tab.form.proppanel.field;

import nth.reflect.fw.ui.component.tab.form.propertypanel.PropertyField;
import nth.reflect.fw.ui.component.tab.form.propertypanel.PropertyFieldWidth;
import nth.reflect.fw.ui.valuemodel.PropertyValueModel;

public class PasswordField extends RfxToDoField implements PropertyField {

	public PasswordField(PropertyValueModel propertyValueModel) {
	}

	@Override
	public PropertyFieldWidth getPropertyFieldWidth() {
		return PropertyFieldWidth.SMALL;
	}


}
