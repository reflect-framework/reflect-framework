package nth.reflect.fw.javafx.control.tab.form.proppanel.field;

import nth.reflect.fw.ui.tab.form.FormTab;
import nth.reflect.fw.ui.tab.form.propertypanel.PropertyFieldWidth;
import nth.reflect.fw.ui.valuemodel.PropertyValueModel;

public class OneToOneOrManyField extends RfxToDoField {

	public OneToOneOrManyField(FormTab formTab, PropertyValueModel propertyValueModel) {
		
	}

	@Override
	public PropertyFieldWidth getPropertyFieldWidth() {
		return PropertyFieldWidth.FULL;
	}

}
